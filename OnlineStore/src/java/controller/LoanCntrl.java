package controller;

import DB.*;
import model.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoanCntrl", urlPatterns = {"/LoanCntrl"})
public class LoanCntrl extends HttpServlet {

    Library lib;
    public void init()
    {
        LibraryDB myDB = new LibraryDB();
        lib = myDB.getSettings();      
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        LoanDB myDB = new LoanDB();
        String url = "";
        
        if (requestURI.endsWith("/addLoan")) {
            url = addLoan(request, response, myDB);
        }
        else if (requestURI.endsWith("/addBookToCart")) {
            url = addBookToCart(request, response, myDB);
        }
        else if (requestURI.endsWith("/removeBookFromCart")) {
            url = removeBookFromCart(request, response, myDB);
        }
        else if(requestURI.endsWith("/returnLoan")){
            url = returnLoan(request, response, myDB);
        }
        else if(requestURI.endsWith("/returnBook")){
            url = returnBook(request, response, myDB);
        }
        else if(requestURI.endsWith("/showUserLoans")){
            url = showUserLoans(request, response, myDB);
        }
        else if(requestURI.endsWith("/finishLoan")){
            url = finishLoan(request, response, myDB);
        }
        else if(requestURI.endsWith("/cancelLoan")){
            url = cancelLoan(request, response, myDB);
        }
        
        
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(url);
        requestDispatcher.forward(request, response);
    }

    private String addLoan(HttpServletRequest request, HttpServletResponse response, LoanDB myDB){
        String redirectTo = "";
        HttpSession session = request.getSession();
        StudentDB studentDB = new StudentDB();
        BookDB bookDB = new BookDB();
        
        User user = (User)session.getAttribute("user");
        String studentID = request.getParameter("studentID");
        String bookISbnFromSearch = (String)session.getAttribute("bookISbnFromSearch");
        
        Loan sessionLoan = new Loan();
        Loan inSessionLoan = (Loan)session.getAttribute("sessionLoan");
        
        Student student = studentDB.getStudentById(studentID);
        BookCopy bc = null;
        Book b = bookDB.getBookByIsbn(bookISbnFromSearch);
        
        if(user!=null && user.getPrivilege().equals("student"))
            student = util.UserUtil.resolveUserToStudent(user);
        
        
        if(student==null)
        {
            redirectTo = "/loanPages/messageToClientLoan.jsp";
            session.setAttribute("messageType", "Loan add");
            String message = "Student with given ID wasn't found";
            session.setAttribute("messageContent", message);
            session.setAttribute("messageRedirect", "/Hw4/loanPages/addLoan.jsp");
        }
        else if(student.getFine()>lib.getMaxFine())
        {
            redirectTo = "/mixPages/messageToClient.jsp";
            session.setAttribute("messageType", "Loan add");
            String message = "The student: "+student.getStdName()+", have fine that he must cover before he can loan again";
            session.setAttribute("messageContent", message);
        }
        else if(inSessionLoan!=null && inSessionLoan.getStudentID().equals(student.getStudetId())){
            redirectTo = "/loanPages/showCart.jsp";
        }
        else
        {
            GregorianCalendar startDate = new GregorianCalendar();
            GregorianCalendar endDate = new GregorianCalendar();
            endDate.add(GregorianCalendar.DAY_OF_MONTH, 30);
            
            sessionLoan.setStartDate(startDate);
            sessionLoan.setEndDate(endDate);
            sessionLoan.setStudentID(student.getStudetId());
            
            String loanID = myDB.addLoan(sessionLoan);
            if(!loanID.equals("fail"))
            {
                ArrayList<BookInLoan> myList = new ArrayList<BookInLoan>();
                sessionLoan.setLoanID(loanID);
                session.setAttribute("sessionLoan", sessionLoan);
                
                if(bookISbnFromSearch!=null)
                {
                    BookInLoan bil = new BookInLoan();
                    bc = bookDB.getFreeCopyID(b.getIsbn());
                    bil.setBook(b);
                    bil.setCopy(bc);
                    bil.setLoanID(loanID);
                    myList.add(bil);
                }
                session.setAttribute("loanCart", myList);
                session.setAttribute("loanStudent", student);
                redirectTo = "/loanPages/showCart.jsp";
            }
            else{
                redirectTo = "/mixPages/messageToClient.jsp";
                session.setAttribute("messageType", "Loan add");
                String message = "Error occurred, loan wasn't created!";
                session.setAttribute("messageContent", message);
            }
        }
        
        return redirectTo;
    }
    private String addBookToCart(HttpServletRequest request, HttpServletResponse response, LoanDB myDB){
        String redirectTo = "";
        HttpSession session = request.getSession();
        BookDB bookDB = new BookDB();
        String bookIsbn = request.getParameter("bookIsbn");
        
        Loan sessionLoan = (Loan) session.getAttribute("sessionLoan");
        User user = (User)session.getAttribute("user");
        
        if(user!=null && user.getPrivilege().equals("student") && sessionLoan==null)
        {
            session.setAttribute("bookISbnFromSearch", bookIsbn);
            redirectTo = addLoan(request,response,myDB);
        }
        else if(sessionLoan==null)
        {
            session.setAttribute("bookISbnFromSearch", bookIsbn);
            redirectTo = "/loanPages/addLoan.jsp";
        }
        else
        {
            ArrayList<BookInLoan> myList = (ArrayList < BookInLoan >)session.getAttribute("loanCart");
            Book b = bookDB.getBookByIsbn(bookIsbn);
            BookCopy bc = bookDB.getFreeCopyID(b.getIsbn());
            BookInLoan bil = new BookInLoan();
            
            bil.setBook(b);
            bil.setCopy(bc);
            bil.setLoanID(sessionLoan.getLoanID());
            myList.add(bil);
            session.setAttribute("loanCart", myList);
            redirectTo = "/loanPages/showCart.jsp";
        }
        
        return redirectTo;
    }
    private String removeBookFromCart(HttpServletRequest request, HttpServletResponse response, LoanDB myDB){
        String redirectTo = "/loanPages/showCart.jsp";
        HttpSession session = request.getSession();
        BookDB bookDB = new BookDB();
        String bookIsbn = request.getParameter("bookIsbn");
        String copyID = request.getParameter("copyID");
        
        ArrayList<BookInLoan> myList = (ArrayList < BookInLoan >)session.getAttribute("loanCart");
        ArrayList<BookInLoan> newList = new ArrayList<BookInLoan>();
        
        for(BookInLoan bil : myList)
        {
            if(!bil.getCopy().getCopyID().equals(copyID) && !bil.getBook().getIsbn().equals(bookIsbn))
            {
                newList.add(bil);
            }
            else
            {
                BookCopy bc = bookDB.getCopyByID(copyID);
                bookDB.returnCopy(copyID, bc.getCopyCondition(),true);
            }
        }
        session.setAttribute("loanCart", newList);

        return redirectTo;
    }
    private String returnLoan(HttpServletRequest request, HttpServletResponse response, LoanDB myDB){
        String redirectTo = "";
        HttpSession session = request.getSession();
        StudentDB studentDB = new StudentDB();
        BookDB bookDB = new BookDB();
        
        String studentID = request.getParameter("studentID");
        Loan sessionLoan = (Loan)session.getAttribute("sessionLoan");
        Student student = studentDB.getStudentById(studentID);
        
        if(student==null)
        {
            redirectTo = "/loanPages/messageToClientLoan.jsp";
            session.setAttribute("messageType", "Loan return");
            String message = "Student with given ID wasn't found";
            session.setAttribute("messageContent", message);
            session.setAttribute("messageRedirect", "/Hw4/loanPages/returnLoan.jsp");
        }
        else if(sessionLoan!=null && sessionLoan.getStudentID().equals(student.getStudetId()))
        {
            redirectTo = "/loanPages/messageToClientLoan.jsp";
            session.setAttribute("messageType", "Loan return");
            String message = "Loan session with the same student ID in progress, close session before continue";
            session.setAttribute("messageContent", message);
            session.setAttribute("messageRedirect", "/Hw4/loanPages/showCart.jsp");
        }
        else
        {
            ArrayList<Loan> loanList = myDB.getActiveLoans(student.getStudetId());
            request.setAttribute("loanList", loanList);
            request.setAttribute("currStudent", student);
            redirectTo = "/loanPages/showActiveLoans.jsp";
        }
        
        return redirectTo;
    }
    private String returnBook(HttpServletRequest request, HttpServletResponse response, LoanDB myDB){
        String redirectTo = "";
        HttpSession session = request.getSession();
        StudentDB studentDB = new StudentDB();
        BookDB bookDB = new BookDB();
        
        String studentID = request.getParameter("studentID");
        String[] copyID = request.getParameterValues("copyID");
        String[] condition = request.getParameterValues("condition");
        String[] allConditionsID = request.getParameterValues("allConditionsID");
        
        Student student = studentDB.getStudentById(studentID);
        
        if(copyID==null)
        {
            redirectTo = "/loanPages/messageToClientLoan.jsp";
            session.setAttribute("messageType", "Book return");
            session.setAttribute("messageContent", "No book was chosen to return");
            session.setAttribute("messageRedirect", "/Hw4/loan/returnLoan?studentID="+studentID);
        }
        else
        {
            Loan l = null;
            int damageFine = 0;
            int lateFine = 0;
            int newfine = student.getFine();
            
            for(String idRunner : copyID)
            {
                String[] tmpStr = idRunner.split(":");
                BookCopy bc = bookDB.getCopyByID(tmpStr[1]);
                l = myDB.getLoanById(tmpStr[0]);
                
                int newCondition=1;
                int late = util.DateUtil.checkIfLate(l.getEndDate());
                
                for(int i=0;i<allConditionsID.length;i++)
                {
                    if(allConditionsID[i].equals(tmpStr[1]))
                    {
                        newCondition = Integer.parseInt(condition[i]);
                        break;
                    }
                }
                
                if(late>0)
                {
                    lateFine += late*lib.getLateFine();
                    newfine +=late*lib.getLateFine();
                }
                if(newCondition>bc.getCopyCondition())
                {
                    damageFine += (newCondition-bc.getCopyCondition())*lib.getDamageFine();
                    newfine +=(newCondition-bc.getCopyCondition())*lib.getDamageFine();
                }
                
                studentDB.updateFine(student.getStudetId(), newfine);
                if(newCondition==5)
                    bookDB.returnCopy(bc.getCopyID(), newCondition, false);
                else
                    bookDB.returnCopy(bc.getCopyID(), newCondition, true);
                
                myDB.returnBook(l.getLoanID(), bc.getCopyID());
            }
            
            request.setAttribute("damageFine", damageFine);
            request.setAttribute("lateFine", lateFine);
            request.setAttribute("sessionLoan", l);
            request.setAttribute("loanStudent", student);
            redirectTo = "/loanPages/finishReturn.jsp";
        }
        
        return redirectTo;
    }
    private String showUserLoans(HttpServletRequest request, HttpServletResponse response, LoanDB myDB){
        String redirectTo = "/loanPages/showStudentLoans.jsp";
        HttpSession session = request.getSession();
        StudentDB studentDB = new StudentDB();
        
        String studentID = request.getParameter("studentID");
        
        Student student = studentDB.getStudentById(studentID);
        User user = (User)session.getAttribute("user");
        
        if(user.getPrivilege().equals("student"))
            student = util.UserUtil.resolveUserToStudent(user);
        
        if(student==null)
        {
            redirectTo = "/loanPages/messageToClientLoan.jsp";
            session.setAttribute("messageType", "Show loans");
            String message = "Student with given ID wasn't found";
            session.setAttribute("messageContent", message);
            session.setAttribute("messageRedirect", "/Hw4/loanPages/studentLoans.jsp");
        }
        else{
            ArrayList<Loan> loanList = myDB.getStudentLoans(student.getStudetId());
            request.setAttribute("loanList", loanList);
            request.setAttribute("currStudent", student);
        }
        
        return redirectTo;
    }
    private String finishLoan(HttpServletRequest request, HttpServletResponse response, LoanDB myDB){
        String redirectTo = "";
        HttpSession session = request.getSession();
        
        ArrayList<BookInLoan> myList = (ArrayList < BookInLoan >)session.getAttribute("loanCart");
        Loan l = (Loan)session.getAttribute("sessionLoan");
        Student student = (Student)session.getAttribute("loanStudent");
        
        if(myList.isEmpty())
        {
            cancelLoan(request,response,myDB);
            
            redirectTo = "/mixPages/messageToClient.jsp";
            session.setAttribute("messageType", "Loan finished");
            String message = "No books in loan, loan wasn't added!";
            session.setAttribute("messageContent", message);
        }
        else if(myList.size()>student.getNumOfBooksAllowed())
        {
            redirectTo = "/loanPages/showCart.jsp";
        }
        else
        {
            myDB.finishLoan(myList);
            request.setAttribute("sessionLoan", l);
            request.setAttribute("loanStudent", student);
            
            session.setAttribute("loanCart", null);
            session.setAttribute("loanStudent", null);
            session.setAttribute("sessionLoan", null);
            
            redirectTo = "/loanPages/finishLoan.jsp";
        }
        
        return redirectTo;
    }
    private String cancelLoan(HttpServletRequest request, HttpServletResponse response, LoanDB myDB){
        String redirectTo = "/welcomePage.jsp";
        HttpSession session = request.getSession();
        BookDB bookDB = new BookDB();
        
        ArrayList<BookInLoan> myList = (ArrayList < BookInLoan >)session.getAttribute("loanCart");
        Loan l = (Loan)session.getAttribute("sessionLoan");
        myDB.deleteLoan(l);
        
        session.setAttribute("loanCart", null);
        session.setAttribute("loanStudent", null);
        session.setAttribute("sessionLoan", null);
        
        for(BookInLoan bil : myList)
        {
            bookDB.returnCopy(bil.getCopy().getCopyID(), bil.getCopy().getCopyCondition(),true);
        }
        
        return redirectTo;
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

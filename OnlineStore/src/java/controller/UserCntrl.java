
package controller;

import model.*;
import DB.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UserCntrl", urlPatterns = {"/UserCntrl"})
public class UserCntrl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String requestURI = request.getRequestURI();
        
        if (requestURI.endsWith("/login")) {
            login(request, response);
        }
        else if(requestURI.endsWith("/logout"))
            logout(request, response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        User tmpUser = UserDB.getUSer(username);
        
        if(username==null && password==null)
            response.sendRedirect("/Hw4/mixPages/loginPage.jsp");
        else if(tmpUser!=null && tmpUser.getPassword().equals(password))
        {
            HttpSession session = request.getSession();
            session.setAttribute("user", tmpUser);
            response.sendRedirect("/Hw4/welcomePage.jsp");
        }
        else 
        {
            request.setAttribute("messageContent", "you entered wrong username or password");
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/mixPages/loginPage.jsp");
            requestDispatcher.forward(request, response);
        }
        
    }
    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        session.setAttribute("user", null);
        if(session.getAttribute("sessionLoan")!=null)
            loanCancel(request,response);
        
        response.sendRedirect("/Hw4/welcomePage.jsp");
    }
    
    private void loanCancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        BookDB bookDB = new BookDB();
        
        ArrayList<BookInLoan> myList = (ArrayList < BookInLoan >)session.getAttribute("loanCart");
        Loan l = (Loan)session.getAttribute("sessionLoan");
        LoanDB myDB = new LoanDB();
        myDB.deleteLoan(l);
        
        session.setAttribute("loanCart", null);
        session.setAttribute("loanStudent", null);
        session.setAttribute("sessionLoan", null);
        
        for(BookInLoan bil : myList)
        {
            bookDB.returnCopy(bil.getCopy().getCopyID(), bil.getCopy().getCopyCondition(),true); 
        }
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

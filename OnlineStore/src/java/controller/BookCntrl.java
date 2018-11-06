package controller;

import model.*;
import DB.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "BookCntrl", urlPatterns = {"/BookCntrl"})
public class BookCntrl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        BookDB myDB = new BookDB();
        String url = "";
        
        if (requestURI.endsWith("/add")) {
            url = add(request, response, myDB);
        }
        else if(requestURI.endsWith("/remove")){
            url = remove(request, response, myDB);
        }
        else if(requestURI.endsWith("/search")){
            url = search(request, response, myDB);
        }
        else if(requestURI.endsWith("/showAll")){
            url = showAll(request, response, myDB);  
        }
        else if(requestURI.endsWith("/edit")){
            url = edit(request, response, myDB);  
        }
        else if(requestURI.endsWith("/updateBook")){
            url = updateBook(request, response, myDB);
        }
        else if(requestURI.endsWith("/addCopies")){
            url = addCopies(request, response, myDB);
        }
        
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(url);
        requestDispatcher.forward(request, response);
    }

    private String add(HttpServletRequest request, HttpServletResponse response, BookDB myDB){
        String redirectTo = "/mixPages/messageToClient.jsp";
        String bookName = request.getParameter("bookName");
        String authorName = request.getParameter("authorName");
        String category = request.getParameter("category");
        String bIsbn = request.getParameter("bIsbn");
        String releaseDate = request.getParameter("releaseDate");
        String pic = request.getParameter("pic");
        String numOfCopies = request.getParameter("numOfCopies");
        
        Book book = new Book();
        book.setBookName(bookName);
        book.setAuthor(authorName);
        book.setCategory(category);
        book.setIsbn(bIsbn);
        book.setReleaseDate(Integer.parseInt(releaseDate));
        book.setBookPic(pic);
        book.setNumOfCopies(Integer.parseInt(numOfCopies));
                
        HttpSession session = request.getSession();
        session.setAttribute("messageType", "Book add");
        
        String sqlMessage = myDB.addBook(book);
        if(sqlMessage.contains("duplicate key"))
        {
            String message = "Book with the same isbn already exist";
            session.setAttribute("messageContent", message);
        }
        else if(sqlMessage.equals("success"))
        {
            String message = "The book was added successfully";
            session.setAttribute("messageContent", message);
        }
        else 
        {
            String message = "Error occurred, book wasn't added";
            session.setAttribute("messageContent", message);
        } 
        
        return redirectTo;
    }
    private String remove(HttpServletRequest request, HttpServletResponse response, BookDB myDB){
        String redirectTo = "/mixPages/messageToClient.jsp";
        String bIsbn = request.getParameter("bIsbn");
        
        HttpSession session = request.getSession();
        session.setAttribute("messageType", "Book remove");
        
        if(myDB.checkIfBooksInLoan(bIsbn))
        {
            String message = "Book with his copies in loan cant be deleted!";
            session.setAttribute("messageContent", message);
        }
        else
        {
            String message = myDB.deleteBook(bIsbn);
            session.setAttribute("messageContent", message);
        }
        return redirectTo;
    }
    private String search(HttpServletRequest request, HttpServletResponse response, BookDB myDB){
        String redirectTo = "/bookPages/searchBookResult.jsp";
        String bookName = request.getParameter("bookName");
        String authorName = request.getParameter("authorName");
        String isbn = request.getParameter("bIsbn");
        String[] catArray = request.getParameterValues("category");
        ArrayList<Book> myList;
        if(authorName==null && isbn==null)  
            myList = myDB.searchByKeyWord("", bookName, "", null);
        else 
            myList = myDB.searchByKeyWord(isbn, bookName, authorName, catArray);
        request.setAttribute("bookList", myList);
        
        return redirectTo;
    }
    private String showAll(HttpServletRequest request, HttpServletResponse response, BookDB myDB){
        String redirectTo = "/bookPages/showAllBooks.jsp";
        ArrayList<Book> myList = myDB.getAllBooks();
        
        request.setAttribute("bookList", myList);
        
        return redirectTo;
    }
    private String edit(HttpServletRequest request, HttpServletResponse response, BookDB myDB){
        String redirectTo = "/bookPages/editBook.jsp";
        String bookIsbn = request.getParameter("bookIsbn");
        
        Book editBook = myDB.getBookByIsbn(bookIsbn);
        if(editBook!=null)  
            request.setAttribute("bookToEdit", editBook);
        else{
            HttpSession session = request.getSession();
            session.setAttribute("messageType", "Edit Book");
            String message = "Book with given isbn wasn't found";
            session.setAttribute("messageContent", message);
            redirectTo = "/mixPages/messageToClient.jsp";
        }
        
        return redirectTo;
    }
    private String updateBook(HttpServletRequest request, HttpServletResponse response, BookDB myDB){
        String redirectTo = "/mixPages/messageToClient.jsp";
        String bookName = request.getParameter("bookName");
        String authorName = request.getParameter("authorName");
        String category = request.getParameter("category");
        String bIsbn = request.getParameter("bIsbn");
        String releaseDate = request.getParameter("releaseDate");
        String pic = request.getParameter("pic");
        String submitValue = request.getParameter("submitValue");
        
        Book book = new Book();
        book.setBookName(bookName);
        book.setAuthor(authorName);
        book.setCategory(category);
        book.setIsbn(bIsbn);
        book.setReleaseDate(Integer.parseInt(releaseDate));
        book.setBookPic(pic);
        
        if(submitValue.equals("add"))
        {
            redirectTo=addCopies(request,response,myDB);
        }
        else 
        {
            HttpSession session = request.getSession();
            session.setAttribute("messageType", "Edit Book");

            String sqlMessage = myDB.updateBook(book);
            if(sqlMessage.equals("success"))
            {
                String message = "The book was updated successfully";
                session.setAttribute("messageContent", message);
            }
            else 
            {
                String message = "Error occurred, book wasn't updated";
                session.setAttribute("messageContent", message);
            } 
        }
        return redirectTo;
    }
    
    private String addCopies(HttpServletRequest request, HttpServletResponse response, BookDB myDB){
        String redirectTo = "/mixPages/messageToClient.jsp";
        String numOfCopies = request.getParameter("numOfCopies");
        String bIsbn = request.getParameter("bIsbn");
        
        Book book = myDB.getBookByIsbn(bIsbn);
        book.setNumOfCopies(Integer.parseInt(numOfCopies));
        myDB.addBookCopies(book);
        
        HttpSession session = request.getSession();
        session.setAttribute("messageType", "Edit Book");
        String message = numOfCopies+" copies was added successfully";
        session.setAttribute("messageContent", message);
        
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

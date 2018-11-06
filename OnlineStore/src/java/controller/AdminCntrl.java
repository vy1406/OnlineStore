package controller;

import model.*;
import DB.*;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AdminCntrl", urlPatterns = {"/AdminCntrl"})
public class AdminCntrl extends HttpServlet {

    Library lib;
    public void init()
    {
        LibraryDB myDB = new LibraryDB();
        lib = myDB.getSettings();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        LibraryDB myDB = new LibraryDB();
        
        String url = "";
        
        if (requestURI.endsWith("/edit")) {
            url = edit(request, response, myDB);
        }
        else if(requestURI.endsWith("/update")){
            url = update(request, response, myDB);
        }
        
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(url);
        requestDispatcher.forward(request, response);
    }
    
    private String edit(HttpServletRequest request, HttpServletResponse response, LibraryDB myDB){
        String redirectTo = "/settings/editSettings.jsp";
        Library lib = myDB.getSettings();
        
        request.setAttribute("librarySettings", lib);
        
        return redirectTo;
    }
    
    private String update(HttpServletRequest request, HttpServletResponse response, LibraryDB myDB){
        String redirectTo = "/mixPages/messageToClient.jsp";
        String numOfLoans = request.getParameter("numOfLoans");
        String damageFine = request.getParameter("damageFine");
        String lateFine = request.getParameter("lateFine");
        String maxFine = request.getParameter("maxFine");
        
        Library lib = new Library();
        lib.setNumOfLoans(Integer.parseInt(numOfLoans));
        lib.setDamageFine(Integer.parseInt(damageFine));
        lib.setLateFine(Integer.parseInt(lateFine));
        lib.setMaxFine(Integer.parseInt(maxFine));
        
        myDB.updateSettings(lib);
        
        HttpSession session = request.getSession();
        session.setAttribute("messageType", "Settings edit");
        String message = "Library settings were updated successfully";
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

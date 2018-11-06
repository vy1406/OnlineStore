package controller;

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
import model.*;

@WebServlet(name = "StudentCntrl", urlPatterns = {"/StudentCntrl"})
public class StudentCntrl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        StudentDB myDB = new StudentDB();
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
        else if(requestURI.endsWith("/updateStudent")){
            url = updateStudent(request, response, myDB);
        }
        else if(requestURI.endsWith("/addUser")){
            url = addUser(request, response, myDB);
        }
        else if(requestURI.endsWith("/payFine")){
            url = payFine(request, response, myDB);
        }
        else if(requestURI.endsWith("/updateFine")){
            url = updateFine(request, response, myDB);
        }
        
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(url);
        requestDispatcher.forward(request, response);
    }
        
    private String add(HttpServletRequest request, HttpServletResponse response, StudentDB myDB){
        String redirectTo = "/mixPages/messageToClient.jsp";
        String studentName = request.getParameter("studentName");
        String studentSurname = request.getParameter("studentSurname");
        String studentID = request.getParameter("studentID");
        String gender = request.getParameter("gender");
        String sEmail = request.getParameter("sEmail");
        String phoneNumber = request.getParameter("phoneNumber");
        String user = request.getParameter("user");
        
        Student std = new Student();
        std.setStdName(studentName);
        std.setStdSurName(studentSurname);
        std.setStudetId(studentID);
        std.setGender(gender);
        std.setStdEmail(sEmail);
        std.setPhoneNumber(phoneNumber);
        
        HttpSession session = request.getSession();
        session.setAttribute("messageType", "Student add");
        
        if(myDB.getStudentById(studentID)!=null)
        {
            String message = "Student with the same id already exist";
            session.setAttribute("messageContent", message);
        }
        else if(myDB.addStudent(std))
        {
            if(!user.equals("")){
                UserDB.addStudentUser(std.getStudetId());
            }
            String message = "Student added successfully";
            session.setAttribute("messageContent", message);
        }
        else 
        {
            String message = "Error occurred Student wasn't added";
            session.setAttribute("messageContent", message);
        } 
        
        return redirectTo;
    }
    private String remove(HttpServletRequest request, HttpServletResponse response, StudentDB myDB){
        String redirectTo = "/mixPages/messageToClient.jsp";
        String studentID = request.getParameter("studentID");
        
        HttpSession session = request.getSession();
        session.setAttribute("messageType", "Student remove");
        
        Student rmvStudent = myDB.getStudentById(studentID);
        if(rmvStudent==null)
        {
            String message = "Student with the given id wasn't found";
            session.setAttribute("messageContent", message);
        }
        else if(rmvStudent.getFine()>0)
        {
            String message = "Student with fine cant be deleted, please cover the fine first";
            session.setAttribute("messageContent", message);
        }
        else if(myDB.checkIfLoansExist(studentID))
        {
            String message = "Student with active loans can't be deleted";
            session.setAttribute("messageContent", message);
        }
        else if(myDB.deleteStudent(studentID))
        {
            UserDB.deleteUser(studentID);
            String message = "Student was deleted successfully";
            session.setAttribute("messageContent", message);
        }
        else 
        {
            String message = "Error occurred, student wasn't deleted";
            session.setAttribute("messageContent", message);
        } 
                
        return redirectTo;
    }
    private String search(HttpServletRequest request, HttpServletResponse response, StudentDB myDB){
        String redirectTo = "/studentPages/searchStudentResult.jsp";
        String studentName = request.getParameter("studentName");
        String studentSurname = request.getParameter("studentSurname");
        String studentID = request.getParameter("studentID");
        
        Table myTable = new Table();
        myTable.setTable(myDB.searchByKeyWordHTML(studentName, studentSurname, studentID));
        request.setAttribute("studentsTable", myTable);
        
        return redirectTo;
    }
    private String showAll(HttpServletRequest request, HttpServletResponse response, StudentDB myDB){
        String redirectTo = "/studentPages/showAllStudents.jsp";
        Table myTable = new Table();
        myTable.setTable(myDB.getAllStudentsHTML());
        
        request.setAttribute("studentsTable", myTable);
        
        return redirectTo;
    }
    private String edit(HttpServletRequest request, HttpServletResponse response, StudentDB myDB){
        String redirectTo = "/studentPages/editStudent.jsp";
        String studentID = request.getParameter("studentID");
        
        Student editStudent = myDB.getStudentById(studentID);
        if(editStudent!=null)  
            request.setAttribute("studentToEdit", editStudent);
        else{
            HttpSession session = request.getSession();
            session.setAttribute("messageType", "Edit Student");
            String message = "Student with given ID wasn't found";
            session.setAttribute("messageContent", message);
            redirectTo = "/mixPages/messageToClient.jsp";
        }
        
        return redirectTo;
    }
    private String updateStudent(HttpServletRequest request, HttpServletResponse response, StudentDB myDB) {
        String redirectTo = "/mixPages/messageToClient.jsp";
        String studentName = request.getParameter("studentName");
        String studentSurname = request.getParameter("studentSurname");
        String studentID = request.getParameter("studentID");
        String gender = request.getParameter("gender");
        String sEmail = request.getParameter("sEmail");
        String phoneNumber = request.getParameter("phoneNumber");
        
        Student student = new Student();
        
        student.setStudetId(studentID);
        student.setStdName(studentName);
        student.setStdSurName(studentSurname);
        student.setStdEmail(sEmail);
        student.setPhoneNumber(phoneNumber);
        student.setGender(gender);
 
        HttpSession session = request.getSession();
        session.setAttribute("messageType", "Edit Student");
        
        String sqlMessage = myDB.updateStudent(student);
        if(sqlMessage.equals("success"))
        {
            String message = "Student information was updated successfully";
            session.setAttribute("messageContent", message);
        }
        else 
        {
            //String message = "Error occurred, student wasn't updated";
            session.setAttribute("messageContent", sqlMessage);
        } 
        return redirectTo;
    }
    
    private String addUser(HttpServletRequest request, HttpServletResponse response, StudentDB myDB) {
        String redirectTo = "/mixPages/messageToClient.jsp";
        String studentID = request.getParameter("studentID");
        
        HttpSession session = request.getSession();
        session.setAttribute("messageType", "User Add");
        
        if(UserDB.addStudentUser(studentID))
        {
            String message = "Student user was added successfully";
            session.setAttribute("messageContent", message);
        }
        else 
        {
            String message = "Student already have a user";
            session.setAttribute("messageContent", message);
        }
        
        return redirectTo;
    }
    private String payFine(HttpServletRequest request, HttpServletResponse response, StudentDB myDB) {
        String redirectTo = "";
        String studentID = request.getParameter("studentID");
        
        HttpSession session = request.getSession();
        session.setAttribute("messageType", "Pay fine");
        
        Student student = myDB.getStudentById(studentID);
        
        if(student==null)
        {
            String message = "Student with given ID wasn't found";
            session.setAttribute("messageContent", message);
            redirectTo = "/mixPages/messageToClient.jsp";
        }
        else if(student!=null && student.getFine()==0)
        {
            String message = "Student has no fine to pay";
            session.setAttribute("messageContent", message);
            redirectTo = "/mixPages/messageToClient.jsp";
        }
        else
        {
            request.setAttribute("student", student);
            redirectTo = "/studentPages/payFine.jsp";
        }
        
        return redirectTo;
    }
    private String updateFine(HttpServletRequest request, HttpServletResponse response, StudentDB myDB) {
        String redirectTo = "/mixPages/messageToClient.jsp";
        String studentID = request.getParameter("studentID");
        int sumToPay = Integer.parseInt(request.getParameter("sumToPay"));
        
        HttpSession session = request.getSession();
        session.setAttribute("messageType", "Pay fine");
        
        Student student = myDB.getStudentById(studentID);
        student.setFine(student.getFine()-sumToPay);
        
        String sqlMessage = myDB.updateFine(studentID,student.getFine());
        if(sqlMessage.equals("success"))
        {
            String message = "Student fine payed successfully, "+student.getFine()+" shekels left to pay";
            session.setAttribute("messageContent", message);
        }
        else 
        {
            String message = "Error occurred, student wasn't updated";
            session.setAttribute("messageContent", message);
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

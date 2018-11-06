package DB;

import util.HTMLResultSet;
import java.sql.*;
import java.util.ArrayList;
import model.*;
import util.*;

public class StudentDB {
    private Connection connection;
    HTMLResultSet myHRS;
    
    public StudentDB() {
       connection = DBUtil.getConnection();
    }
    
    public boolean addStudent(Student std) {
        int nor = 0;
        try {
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("insert into Students values (?, ?, ?, ?, ?, ?, ?)");
            pStatement.setString(1, std.getStudetId());
            pStatement.setString(2, std.getStdName());
            pStatement.setString(3, std.getStdSurName());
            pStatement.setString(4, std.getStdEmail());
            pStatement.setString(5, std.getPhoneNumber());
            pStatement.setString(6, std.getGender());
            pStatement.setInt(7, std.getFine());
            
            nor = pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if(nor>0)
            return true;
        else return false;
    }
    
    public Boolean deleteStudent(String studentID) {
        int nor=0;
        try {
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("delete from Students where studentid=?");
            pStatement.setString(1, studentID);
            nor = pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if(nor>0)
            return true;
        else return false;
    }

    public Student getStudentById(String studentID)
    {
        Student searchedStudent=null;
        try{
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("select * from Students where studentid=?");
            pStatement.setString(1, studentID);
            ResultSet rs = pStatement.executeQuery();

            if (rs.next()) {
                searchedStudent = new Student();
                searchedStudent.setStudetId(rs.getString("studentid"));
                searchedStudent.setStdName(rs.getString("sname"));
                searchedStudent.setStdSurName(rs.getString("surname"));
                searchedStudent.setStdEmail(rs.getString("email"));
                searchedStudent.setPhoneNumber(rs.getString("phonenumber"));
                searchedStudent.setGender(rs.getString("gender"));
                searchedStudent.setFine(rs.getInt("fine"));
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
        if(searchedStudent!=null)
        {
            try{
                int counter=0;
                PreparedStatement pStatement;
                pStatement = connection.prepareStatement("SELECT * \n" +
                                                        "FROM   booksinloan bil, \n" +
                                                        "       loans l \n" +
                                                        "WHERE  bil.loanid = l.loanid \n" +
                                                        "       AND l.studentid = ? \n" +
                                                        "       AND inloan = ? ");
                pStatement.setString(1, studentID);
                pStatement.setString(2, "true");
                ResultSet rs = pStatement.executeQuery();

                while (rs.next()) {
                    counter++;
                }
                LibraryDB db = new LibraryDB();
                Library lib = db.getSettings();
                
                searchedStudent.setNumOfBooksAllowed(lib.getNumOfLoans()-counter);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return searchedStudent;
    }
    
    public Boolean checkIfLoansExist(String studentID)
    {
        Boolean loansExist = false;
        try{
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("SELECT bil.bookisbn, \n" +
                                                    "       bil.copyid \n" +
                                                    "FROM   loans l, \n" +
                                                    "       booksinloan bil \n" +
                                                    "WHERE  l.studentid = ? \n" +
                                                    "       AND l.loanid = bil.loanid \n" +
                                                    "       AND bil.inloan = ? ");
            pStatement.setString(1, studentID);
            pStatement.setString(2, "true");
            ResultSet rs = pStatement.executeQuery();

            if (rs.next()) {
                loansExist=true;
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return loansExist;
    }
    
    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<Student>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from students");
            while (rs.next()) {
                Student std = new Student();
                std.setStudetId(rs.getString("studentid"));
                std.setStdName(rs.getString("sname"));
                std.setStdSurName(rs.getString("surname"));
                std.setStdEmail(rs.getString("email"));
                std.setPhoneNumber(rs.getString("phonenumber"));
                std.setGender(rs.getString("gender"));
                std.setFine(rs.getInt("fine"));
                students.add(std);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
    
    public String getAllStudentsHTML() {
        String table="";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from students");
            myHRS = new HTMLResultSet(rs);
            table = myHRS.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return table;
    }
    
    public String searchByKeyWordHTML(String studentName, String studentSurname, String studentID) {
        String table="";
        String query =  "SELECT * \n" +
                        "FROM   students \n" +
                        "WHERE  studentid LIKE ? \n" +
                        "       AND sname LIKE ? \n" +
                        "       AND surname LIKE ? ";
        try {
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1, "%"+studentID+"%");
            pStatement.setString(2, "%"+studentName+"%");
            pStatement.setString(3, "%"+studentSurname+"%");
            ResultSet rs = pStatement.executeQuery();
            
            myHRS = new HTMLResultSet(rs);
            table = myHRS.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return table;
    }
    
    public String updateStudent(Student student) {
        String message = "";
        
        try {
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("UPDATE students \n" +
                                                    "SET    sname = ?, \n" +
                                                    "       surname = ?, \n" +
                                                    "       email = ?, \n" +
                                                    "       phonenumber = ?, \n" +
                                                    "       gender = ? \n" +
                                                    "WHERE  studentid = ? ");
            pStatement.setString(1, student.getStdName());
            pStatement.setString(2, student.getStdSurName());
            pStatement.setString(3, student.getStdEmail());
            pStatement.setString(4, student.getPhoneNumber());
            pStatement.setString(5, student.getGender());
            pStatement.setString(6, student.getStudetId());
            pStatement.executeUpdate();
            message = "success";
        } catch (SQLException e) {
            message = e.getMessage();
        }
        
        return message;
    } 
    
    public String updateFine(String studentID, int newFine)
    {
        String message = "";
        
        try {
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("UPDATE students \n" +
                                                    "SET    fine = ? \n" +
                                                    "WHERE  studentid = ? ");
            pStatement.setInt(1, newFine);
            pStatement.setString(2, studentID);
            pStatement.executeUpdate();
            message = "success";
        } catch (SQLException e) {
            message = e.getMessage();
        }
        
        return message;
    }
}

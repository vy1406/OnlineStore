package DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import model.*;
import util.*;

public class LoanDB {
    private Connection connection;

    public LoanDB() {
       connection = DBUtil.getConnection();
    }
    
    public String addLoan(Loan l) {
        String message = "";
        
        try {
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("insert into loans (STUDENTID, STARTDATE, ENDDATE) values (?, ?, ?)");
            pStatement.setString(1, l.getStudentID());
            pStatement.setString(2,util.DateUtil.returnDateFormatForDB(l.getStartDate()));
            pStatement.setString(3,util.DateUtil.returnDateFormatForDB(l.getEndDate()));

            pStatement.executeUpdate();
            message = "success";
        } catch (SQLException e) {
            message = e.getMessage();
        }
        
        if(!message.equals("success"))
        {
            return "fail";
        }
        
        try {
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("select max(loanid) as lastLoan from loans where studentid=?");
            pStatement.setString(1, l.getStudentID());

            ResultSet rs = pStatement.executeQuery();
            if(rs.next())
            {
                message = rs.getString("lastLoan");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            message = "fail";
        }
        
        return message;
    }
    public Loan getLoanById(String loanID){
        Loan l = null;
        
        try {
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("select * from loans where loanid=?");
            pStatement.setString(1, loanID);
            ResultSet rs = pStatement.executeQuery();
            
            if (rs.next()) {
                l = new Loan();
                l.setLoanID(rs.getString("loanid"));
                l.setStudentID(loanID);
                l.setStartDate(util.DateUtil.returnGregorianDate(rs.getString("startdate")));
                l.setEndDate(util.DateUtil.returnGregorianDate(rs.getString("enddate")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return l;
    }
    public String finishLoan(ArrayList<BookInLoan> myList) {
        String message = "";
        
        try {
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("insert into booksinloan values (?,?,?,?,?)");
            for(BookInLoan bil : myList)
            {
                pStatement.setString(1, bil.getCopy().getCopyID());
                pStatement.setString(2, bil.getBook().getIsbn());
                pStatement.setString(3, null);
                pStatement.setString(4, "true");
                pStatement.setString(5, bil.getLoanID());

                pStatement.executeUpdate();
            }
            message = "success";
        } catch (SQLException e) {
            message = e.getMessage();
        }
        
        return message;
    }
    
    public String deleteLoan(Loan l) {
        String message = "";
        
        try {
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("delete from loans where loanid=?");
            
            pStatement.setString(1, l.getLoanID());
            pStatement.executeUpdate();
            
            message = "success";
        } catch (SQLException e) {
            message = e.getMessage();
        }
        
        return message;
    }
    
    public ArrayList<Loan> getStudentLoans(String studentID) {
        ArrayList<Loan> loanList = new ArrayList<Loan>();
        
        try {
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("SELECT * \n" +
                                                    "FROM   loans l, \n" +
                                                    "       booksinloan bil, \n" +
                                                    "       books b, \n" +
                                                    "       bookcopy bc \n" +
                                                    "WHERE  l.loanid = bil.loanid \n" +
                                                    "       AND bil.bookisbn = b.isbn \n" +
                                                    "       AND bil.copyid = bc.copyid \n" +
                                                    "       AND l.studentid = ? ");
            pStatement.setString(1, studentID);
            ResultSet rs = pStatement.executeQuery();
            
            Boolean rsFlag = rs.next();
            
            while(rsFlag)
            {
                Loan l = new Loan();
                l.setStudentID(studentID);
                l.setLoanID(rs.getString("loanid"));
                l.setStartDate(util.DateUtil.returnGregorianDate(rs.getString("startdate")));
                l.setEndDate(util.DateUtil.returnGregorianDate(rs.getString("enddate")));
                
                ArrayList<BookInLoan> bilList = new ArrayList<BookInLoan>();
                
                while(rsFlag && rs.getString("loanid").equals(l.getLoanID())){
                    BookInLoan bil = new BookInLoan();
                    Book book = new Book();
                    BookCopy bc = new BookCopy();
                    
                    book = new Book();
                    book.setIsbn(rs.getString("isbn"));
                    book.setBookName(rs.getString("bookname"));
                    book.setAuthor(rs.getString("author"));
                    book.setCategory(rs.getString("category"));
                    book.setReleaseDate(rs.getInt("releaseyear"));
                    book.setBookPic(rs.getString("picurl"));
                    
                    bc = new BookCopy();
                    bc.setBookIsbn(rs.getString("bookisbn"));
                    bc.setCopyID(rs.getString("copyid"));
                    bc.setCopyCondition(rs.getInt("copycondition"));
                    bc.setInLoan(rs.getBoolean("inloan"));
                    
                    bil.setLoanID(rs.getString("loanid"));
                    bil.setBook(book);
                    bil.setCopy(bc);
                    bil.setInLoan(rs.getBoolean("inloan"));
                    
                    bilList.add(bil);
                    rsFlag = rs.next();
                }
                
                l.setBil(bilList);
                loanList.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return loanList;
    }
    
    public ArrayList<Loan> getActiveLoans(String studentID) {
        ArrayList<Loan> loanList = new ArrayList<Loan>();
        
        try {
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("SELECT * \n" +
                                                    "FROM   loans l, \n" +
                                                    "       booksinloan bil, \n" +
                                                    "       books b, \n" +
                                                    "       bookcopy bc \n" +
                                                    "WHERE  l.loanid = bil.loanid \n" +
                                                    "       AND bil.bookisbn = b.isbn \n" +
                                                    "       AND bil.copyid = bc.copyid \n" +
                                                    "       AND l.studentid = ? \n" +
                                                    "       AND bil.inloan = ? ");
            pStatement.setString(1, studentID);
            pStatement.setString(2, "true");
            ResultSet rs = pStatement.executeQuery();
            
            Boolean rsFlag = rs.next();
            
            while(rsFlag)
            {
                Loan l = new Loan();
                l.setStudentID(studentID);
                l.setLoanID(rs.getString("loanid"));
                l.setStartDate(util.DateUtil.returnGregorianDate(rs.getString("startdate")));
                l.setEndDate(util.DateUtil.returnGregorianDate(rs.getString("enddate")));
                
                ArrayList<BookInLoan> bilList = new ArrayList<BookInLoan>();
                
                while(rsFlag && rs.getString("loanid").equals(l.getLoanID())){
                    BookInLoan bil = new BookInLoan();
                    Book book = new Book();
                    BookCopy bc = new BookCopy();
                    
                    book = new Book();
                    book.setIsbn(rs.getString("isbn"));
                    book.setBookName(rs.getString("bookname"));
                    book.setAuthor(rs.getString("author"));
                    book.setCategory(rs.getString("category"));
                    book.setReleaseDate(rs.getInt("releaseyear"));
                    book.setBookPic(rs.getString("picurl"));
                    
                    bc = new BookCopy();
                    bc.setBookIsbn(rs.getString("bookisbn"));
                    bc.setCopyID(rs.getString("copyid"));
                    bc.setCopyCondition(rs.getInt("copycondition"));
                    bc.setInLoan(true);
                    
                    bil.setLoanID(rs.getString("loanid"));
                    bil.setBook(book);
                    bil.setCopy(bc);
                    bil.setInLoan(rs.getBoolean("inloan"));
                    
                    bilList.add(bil);
                    rsFlag = rs.next();
                }
                
                l.setBil(bilList);
                loanList.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return loanList;
    }
    
    public void returnBook(String loanID, String copyID){
        
        try {
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("update booksinloan set actualreturndate=?,inloan=? where loanid=? and copyid=?");
            pStatement.setString(1, util.DateUtil.returnDateFormatForDB(new GregorianCalendar()));
            pStatement.setBoolean(2, false);
            pStatement.setString(3, loanID);
            pStatement.setString(4, copyID);
            
            pStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

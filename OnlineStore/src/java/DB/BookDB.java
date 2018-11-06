package DB;

import java.sql.*;
import java.util.ArrayList;
import model.*;
import util.*;

public class BookDB {
    private Connection connection;

    public BookDB() {
       connection = DBUtil.getConnection();
    }
    
    public String addBook(Book book) {
        String message = "";
        
        try {
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("insert into books values (?, ?, ?, ?, ?, ?)");
            pStatement.setString(1, book.getIsbn());
            pStatement.setString(2, book.getBookName());
            pStatement.setString(3, book.getAuthor());
            pStatement.setString(4, book.getCategory());
            pStatement.setInt(5, book.getReleaseDate());
            pStatement.setString(6, book.getBookPic());
            pStatement.executeUpdate();
            message = "success";
        } catch (SQLException e) {
            message = e.getMessage();
        }
        addBookCopies(book);
        
        return message;
    }
    
    public void addBookCopies(Book book) {
        
        try {
            PreparedStatement pStatement;
            int numOfCopies = book.getNumOfCopies();
            pStatement = connection.prepareStatement("insert into bookcopy (bookisbn,inloan,copycondition,canloan) values(?,?,?,?)");
            pStatement.setString(1, book.getIsbn());
            pStatement.setString(2, "false");
            pStatement.setInt(3, 1);
            pStatement.setBoolean(4, true);
            
            while((numOfCopies--)>0)
                pStatement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String deleteBook(String booksibn) {
        int nor=0;
        try {
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("delete from books where isbn=?");
            pStatement.setString(1, booksibn);
            nor = pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if(nor>0)
            return "The book was removed successfully";
        else 
            return "Boos with given isbn wasn't found";
    }
    
    public Boolean checkIfBooksInLoan(String bookName)
    {
        Boolean flag=false;
        
        try {
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("SELECT * \n" +
                                                    "FROM   bookcopy AS bc, \n" +
                                                    "       books AS b \n" +
                                                    "WHERE  bc.bookisbn = b.isbn \n" +
                                                    "       AND bc.inloan = ? \n" +
                                                    "       AND bc.bookisbn = ? ");
            pStatement.setString(1, "true");
            pStatement.setString(2, bookName);
            ResultSet rs = pStatement.executeQuery();

            if (rs.next()) {
                flag=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return flag;
    }
    public Book getBookByIsbn(String isbn){
        Book book = null;
        
        try {
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("select * from books where isbn=?");
            pStatement.setString(1, isbn);
            ResultSet rs = pStatement.executeQuery();
            if (rs.next()) {
                book = new Book();
                book.setIsbn(rs.getString("isbn"));
                book.setBookName(rs.getString("bookname"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setReleaseDate(rs.getInt("releaseyear"));
                book.setBookPic(rs.getString("picurl"));
                book.setNumOfCopiesAvailable(getNumOfCopiesAvailable(isbn));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return book;
    }
    public BookCopy getCopyByID(String copyID){
        BookCopy bc = null;
        
        try {
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("select * from bookcopy where copyid=?");
            pStatement.setString(1, copyID);
            ResultSet rs = pStatement.executeQuery();
            
            if (rs.next()) {
                bc = new BookCopy();
                bc.setCopyID(rs.getString("copyid"));
                bc.setBookIsbn(rs.getString("bookisbn"));
                bc.setInLoan(rs.getBoolean("inloan"));
                bc.setCopyCondition(rs.getInt("copycondition"));
                bc.setCanLoan(rs.getBoolean("canLoan"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return bc;
    }
    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> books = new ArrayList<Book>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from books");
            while (rs.next()) {
                Book book = new Book();
                book.setIsbn(rs.getString("isbn"));
                book.setBookName(rs.getString("bookname"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setReleaseDate(rs.getInt("releaseyear"));
                book.setBookPic(rs.getString("picurl"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    
    public ArrayList<Book> searchByKeyWord(String isbn, String bookName, String authorName, String[] catArray) {
        ArrayList<Book> books = new ArrayList<Book>();
        String query =  "SELECT * \n" +
                        "FROM   books \n" +
                        "WHERE  isbn LIKE ? \n" +
                        "       AND bookname LIKE ? \n" +
                        "       AND author LIKE ? ";
        try {
            if(catArray!=null)
            {
                query += "And category in(";
                for(int i=0; i<catArray.length;i++)
                {
                    if(i<catArray.length-1)
                        query+="'"+catArray[i]+"'"+",";
                    else 
                        query+="'"+catArray[i]+"'";
                }
                query+=")";
            }
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1, "%"+isbn+"%");
            pStatement.setString(2, "%"+bookName+"%");
            pStatement.setString(3, "%"+authorName+"%");
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setIsbn(rs.getString("isbn"));
                book.setBookName(rs.getString("bookname"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setReleaseDate(rs.getInt("releaseyear"));
                book.setBookPic(rs.getString("picurl"));
                book.setNumOfCopiesAvailable(getNumOfCopiesAvailable(book.getIsbn()));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public String updateBook(Book book) {
        String message = "";
        
        try {
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("UPDATE books \n" +
                                                    "SET    bookname = ?, \n" +
                                                    "       author = ?, \n" +
                                                    "       category = ?, \n" +
                                                    "       releaseyear = ?, \n" +
                                                    "       picurl = ? \n" +
                                                    "WHERE  isbn = ? ");
            pStatement.setString(1, book.getBookName());
            pStatement.setString(2, book.getAuthor());
            pStatement.setString(3, book.getCategory());
            pStatement.setInt(4, book.getReleaseDate());
            pStatement.setString(5, book.getBookPic());
            pStatement.setString(6, book.getIsbn());
            pStatement.executeUpdate();
            message = "success";
        } catch (SQLException e) {
            message = e.getMessage();
        }
        
        return message;
    }
  
    public BookCopy getFreeCopyID(String isbn){
        BookCopy bc = null;
        
        try {
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("select * from bookcopy where bookisbn=? and inloan=? and canLoan=?");
            pStatement.setString(1, isbn);
            pStatement.setString(2, "false");
            pStatement.setString(3, "true");
            
            ResultSet rs = pStatement.executeQuery();
            if (rs.next()) {
                bc = new BookCopy();
                bc.setBookIsbn(isbn);
                bc.setCopyID(rs.getString("copyid"));
                bc.setCopyCondition(rs.getInt("copycondition"));
                bc.setInLoan(true);
                bc.setCanLoan(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if(bc!=null)
        {
            try {
                PreparedStatement pStatement;
                pStatement = connection.prepareStatement("update bookcopy set inloan=? where copyid=? and bookisbn=?");
                pStatement.setString(1, "true");
                pStatement.setString(2, bc.getCopyID());
                pStatement.setString(3, bc.getBookIsbn());

                pStatement.executeUpdate();
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bc;
    }
    
    public int getNumOfCopiesAvailable(String isbn){
        int counter=0;
        
        try {
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("select * from bookcopy where bookisbn=? and inloan=? and canloan=?");
            pStatement.setString(1, isbn);
            pStatement.setString(2, "false");
            pStatement.setBoolean(3, true);
            
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return counter;
    }
    
    public void returnCopy(String copyID, int newCondition, Boolean canLoan){
        
        try {
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("update bookcopy set inloan=?,copycondition=?,canloan=? where copyid=?");
            pStatement.setString(1, "false");
            pStatement.setInt(2, newCondition);
            pStatement.setBoolean(3, canLoan);
            pStatement.setString(4, copyID);

            pStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
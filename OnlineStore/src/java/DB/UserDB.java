package DB;

import java.sql.*;
import model.*;
import util.*;

public class UserDB {
       
    public static User getUSer(String username) {
        Connection connection = DBUtil.getConnection();
        User user = null;
        try {
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("select * from Users where username=?");
            pStatement.setString(1, username);
            ResultSet rs = pStatement.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setPrivilege(rs.getString("privilege"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    
    public static boolean addStudentUser(String studentID) {
        Connection connection = DBUtil.getConnection();
        int nor = 0;
        try {
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("insert into Users values (?, ?, ?)");
            pStatement.setString(1, studentID);
            pStatement.setString(2, "1234");
            pStatement.setString(3, "student");
            
            nor = pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if(nor>0)
            return true;
        else return false;
    }
    
    public static boolean deleteUser(String userName){
        Connection connection = DBUtil.getConnection();
        int nor = 0;
        try {
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("delete from users where username=?");
            pStatement.setString(1, userName);
            
            nor = pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if(nor>0)
            return true;
        else return false;
    }
}

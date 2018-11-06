package DB;

import java.sql.*;
import java.util.ArrayList;
import model.*;
import util.*;

public class CategoryDB {
    private Connection connection;

    public CategoryDB() {
       connection = DBUtil.getConnection();
    }
    
    public Boolean addCategory(String cat) {
        int nor = 0;
        try {
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("insert into Category (categoryName) values(?)");
            pStatement.setString(1, cat);
            nor  = pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if(nor>0)
            return true;
        else return false;
    }
    
    public ArrayList<Category> showAll() {
        ArrayList<Category> myList = new ArrayList<Category>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from category");
            while (rs.next()) {
                Category cat = new Category();
                cat.setCatName(rs.getString("categoryname"));
                myList.add(cat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return myList;
    }
    
    public Boolean isExist(String catName)
    {
        Category cat = null;
        
        try {
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("select * from category where categoryname=?");
            pStatement.setString(1, catName);
            ResultSet rs = pStatement.executeQuery();

            if (rs.next()) {
                cat = new Category();
                cat.setCatName(rs.getString("categoryname"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if(cat!=null)
            return true;
        else 
            return false;
    }
}
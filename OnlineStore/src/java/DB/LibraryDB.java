package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.*;
import util.DBUtil;

public class LibraryDB {
    private Connection connection;

    public LibraryDB() {
       connection = DBUtil.getConnection();
    }
    
    public Library getSettings(){
        Library lib = null;
        
        try {
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("select * from librarysettings");
            
            ResultSet rs = pStatement.executeQuery();
            if (rs.next()) {
                lib = new Library();
                lib.setNumOfLoans(rs.getInt("numofloans"));
                lib.setDamageFine(rs.getInt("damagefine"));
                lib.setLateFine(rs.getInt("latefine"));
                lib.setMaxFine(rs.getInt("maxfine"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return lib;
    }
    
    public Library updateSettings(Library lib){
        
        try {
            PreparedStatement pStatement;
            pStatement = connection.prepareStatement("update librarysettings set numofloans=?,damagefine=?,latefine=?,maxfine=?");
            pStatement.setInt(1, lib.getNumOfLoans());
            pStatement.setInt(2, lib.getDamageFine());
            pStatement.setInt(3, lib.getLateFine());
            pStatement.setInt(4, lib.getMaxFine());
            
            pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return lib;
    }
}

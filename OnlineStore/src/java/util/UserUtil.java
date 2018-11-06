
package util;

import DB.StudentDB;
import model.*;

public class UserUtil {
    
    public static String resolveUser(User user){
        StudentDB myDB = new StudentDB();
        String userName="";
        
        if(user==null)
            return "guest";
        else if(user.getUserName().equals("admin") || user.getUserName().equals("librarian"))
            return user.getUserName();
        else {
            userName = myDB.getStudentById(user.getUserName()).getStdName();
        }

        return userName;
    }
    
    public static Student resolveUserToStudent(User user){
        StudentDB myDB = new StudentDB();
        Student student = null;
        
        if(user==null)
            return student;
        else {
            student = myDB.getStudentById(user.getUserName());
        }

        return student;
    }
}

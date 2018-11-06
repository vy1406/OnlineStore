package util;

import model.*;
import DB.*;
import java.util.ArrayList;

public class TagUtil {
    
    public static String selectCategory(String catName) { 
        StringBuffer out = new StringBuffer();
        CategoryDB myDB = new CategoryDB();
        ArrayList<Category> catList = myDB.showAll();
        
        out.append("<select name=\"category\">\n");
        for(Category cat : catList){
            if(catName.equals(cat.getCatName()))
                out.append("<option value=\""+cat.getCatName()+"\" selected>"+cat.getCatName()+"</option>\n");
            else
                out.append("<option value=\""+cat.getCatName()+"\">"+cat.getCatName()+"</option>\n");
        }
        
        out.append("</select><br/>\n");
        return out.toString();
    }
    
    public static String checkBoxCategory() { 
        StringBuffer out = new StringBuffer();
        CategoryDB myDB = new CategoryDB();
        ArrayList<Category> catList = myDB.showAll();
        
        for(Category cat : catList){
            out.append("<input type=\"checkbox\" name=\"category\" value=\""+cat.getCatName()+"\">"+cat.getCatName()+"<br/>\n");
        }
        
        return out.toString();
    }
    
    public static String checkBoxGender(String gender)
    {
        StringBuffer out = new StringBuffer();
        out.append("<input type=\"radio\" name=\"gender\" value=\""+gender+"\" checked=\"checked\"/>"+gender+"<br/>");
        if(gender.equals("male"))
            out.append("<input type=\"radio\" name=\"gender\" value=\"female\"/>female<br/>");
        else 
            out.append("<input type=\"radio\" name=\"gender\" value=\"male\"/>male<br/>");
        
        return out.toString();
    }
}

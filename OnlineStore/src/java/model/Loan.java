package model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Loan {
   
    private String loanID;
    private String studentID;
    private GregorianCalendar startDate;
    private GregorianCalendar endDate;
    private ArrayList<BookInLoan> bil;
    
    public String getLoanID() {
        return loanID;
    }

    public void setLoanID(String loanID) {
        this.loanID = loanID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public GregorianCalendar getStartDate() {
        return startDate;
    }

    public void setStartDate(GregorianCalendar startDate) {
        this.startDate = startDate;
    }

    public GregorianCalendar getEndDate() {
        return endDate;
    }

    public void setEndDate(GregorianCalendar endDate) {
        this.endDate = endDate;
    }

    public ArrayList<BookInLoan> getBil() {
        return bil;
    }

    public void setBil(ArrayList<BookInLoan> bil) {
        this.bil = bil;
    }
    
    
}


package model;


public class Student {
    private String studetId;
    private String stdName;
    private String stdSurName;
    private String stdEmail;
    private String gender;
    private String phoneNumber;
    private int fine;
    private int numOfBooksAllowed;

    public String getStudetId() {
        return studetId;
    }

    public void setStudetId(String studetId) {
        this.studetId = studetId;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public String getStdSurName() {
        return stdSurName;
    }

    public void setStdSurName(String stdSurName) {
        this.stdSurName = stdSurName;
    }

    public String getStdEmail() {
        return stdEmail;
    }

    public void setStdEmail(String stdEmail) {
        this.stdEmail = stdEmail;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public int getNumOfBooksAllowed() {
        return numOfBooksAllowed;
    }

    public void setNumOfBooksAllowed(int numOfBooksAllowed) {
        if(numOfBooksAllowed>=0)
            this.numOfBooksAllowed = numOfBooksAllowed;
        else
            this.numOfBooksAllowed = 0;
    }
    
}

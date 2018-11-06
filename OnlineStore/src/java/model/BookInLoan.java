
package model;

import java.sql.Date;

public class BookInLoan {
    private String loanID;
    private Book book;
    private BookCopy copy;
    private Date actualReturnDate;
    private Boolean inLoan;

    public String getLoanID() {
        return loanID;
    }

    public void setLoanID(String loanID) {
        this.loanID = loanID;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public BookCopy getCopy() {
        return copy;
    }

    public void setCopy(BookCopy copy) {
        this.copy = copy;
    }

    public Date getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActualReturnDate(Date actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public Boolean getInLoan() {
        return inLoan;
    }

    public void setInLoan(Boolean inLoan) {
        this.inLoan = inLoan;
    }

    
    
}


package model;

public class BookCopy {
    private String bookIsbn;
    private String copyID;
    private int copyCondition;
    private boolean inLoan;
    private boolean canLoan;

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public String getCopyID() {
        return copyID;
    }

    public void setCopyID(String copyID) {
        this.copyID = copyID;
    }

    public int getCopyCondition() {
        return copyCondition;
    }

    public void setCopyCondition(int copyCondition) {
        this.copyCondition = copyCondition;
    }

    public boolean isInLoan() {
        return inLoan;
    }

    public void setInLoan(boolean inLoan) {
        this.inLoan = inLoan;
    }

    public boolean isCanLoan() {
        return canLoan;
    }

    public void setCanLoan(boolean canLoan) {
        this.canLoan = canLoan;
    }
    
    
}

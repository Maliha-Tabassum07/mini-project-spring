package com.maliha.miniproject.model;


import java.time.LocalDate;

public class BorrowBook {
    private Integer borrowId;
    private LocalDate dueDate;
    private LocalDate returnDate;
    public BorrowBook() {
    }
    public BorrowBook(Integer borrowId, LocalDate dueDate, LocalDate returnDate) {
        this.borrowId = borrowId;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }
    public Integer getBorrowId() {
        return borrowId;
    }
    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    public LocalDate getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}

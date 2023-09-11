package com.maliha.miniproject.entity;

import java.util.Date;

public class BorrowBookEntity {
    private Integer borrow_id;
    private Integer book_id;
    private Integer user_id;
    private Date due_date;
    private Date return_date;
    public BorrowBookEntity() {
    }

    public BorrowBookEntity(Integer borrow_id, Integer book_id, Integer user_id, Date due_date, Date return_date) {
        this.borrow_id = borrow_id;
        this.book_id = book_id;
        this.user_id = user_id;
        this.due_date = due_date;
        this.return_date = return_date;
    }

    public Integer getBorrow_id() {
        return borrow_id;
    }

    public void setBorrow_id(Integer borrow_id) {
        this.borrow_id = borrow_id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }
}

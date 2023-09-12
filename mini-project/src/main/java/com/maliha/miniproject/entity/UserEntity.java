package com.maliha.miniproject.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String role;
//    @OneToMany
//    private List<BorrowBookEntity> borrowBookEntityList;
//
//    @OneToMany
//    private List<ReviewBookEntity> reviewBookEntityList;

    public UserEntity() {
    }
    public UserEntity(Integer userId, String firstName, String lastName, String email, String password, String address) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
    }

//    public UserEntity(Integer user_id, String firstName, String lastName, String email, String password, String address,  String role,List<BorrowBookEntity> borrowBookEntityList,List<ReviewBookEntity> reviewBookEntityList) {
//        this.user_id = user_id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.password = password;
//        this.address = address;
//        this.role = role;
//        this.borrowBookEntityList=borrowBookEntityList;
//        this.reviewBookEntityList=reviewBookEntityList;
//    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public  String getRole() {
        return role;
    }

    public void setRole( String role) {
        this.role = role;
    }

//    public List<BorrowBookEntity> getBorrowBookEntityList() {
//        return borrowBookEntityList;
//    }
//
//    public void setBorrowBookEntityList(List<BorrowBookEntity> borrowBookEntityList) {
//        this.borrowBookEntityList = borrowBookEntityList;
//    }
//
//    public List<ReviewBookEntity> getReviewBookEntityList() {
//        return reviewBookEntityList;
//    }
//
//    public void setReviewBookEntityList(List<ReviewBookEntity> reviewBookEntityList) {
//        this.reviewBookEntityList = reviewBookEntityList;
//    }
}

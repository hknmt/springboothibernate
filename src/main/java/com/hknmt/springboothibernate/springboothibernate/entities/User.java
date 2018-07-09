package com.hknmt.springboothibernate.springboothibernate.entities;

import javax.persistence.*;

@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id = 0;
    @Column(name = "USERNAME")
    private String userName;
    @Column(name = "PASSWORD")
    private String passWord;
    @Column(name = "ROLE")
    private int role;
    public User() {

    }
    public User(String userName, String passWord, int role) {
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
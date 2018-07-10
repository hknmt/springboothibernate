package com.hknmt.springboothibernate.springboothibernate.dao;

import com.hknmt.springboothibernate.springboothibernate.entities.User;

public interface UserDAO {
   public void insertUser(User user);

    public User getUser(int user_id);

    public boolean checkLogin(String userName, String passWord);

    public boolean checkUserName(String userName);
}

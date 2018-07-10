package com.hknmt.springboothibernate.springboothibernate.controllers;

import com.hknmt.springboothibernate.springboothibernate.dao.UserDAOImpl;
import com.hknmt.springboothibernate.springboothibernate.entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
    private UserDAOImpl userDAO = new UserDAOImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");

        String url = "/login";
        String userName_err = "", passWord_err = "";

        if (userName.isEmpty() || passWord.isEmpty()) {
            userName_err += "Vui lòng nhập tài khoản!";
            passWord_err += "Vui lòng nhập mật khẩu!";

        }else {
            if (userDAO.checkUserName(userName)) {
                userName_err += "Tài khoản đã tồn tại!";
            }
        }

        if (passWord.length()<5) {
            passWord_err += "Mật khẩu quá ngắn!";
        }
        if (userName_err.length() > 0) {
            request.setAttribute("userName_err", userName_err);
            request.setAttribute("userName", userName);
        }
        if (passWord_err.length() > 0) {
            request.setAttribute("passWord_err", passWord_err);
        }
        try {
            if (userName_err.isEmpty()||passWord_err.isEmpty()) {
                userDAO.insertUser(new User(0,userName, passWord,2));
                HttpSession session = request.getSession();
                session.getAttribute(userName);
                url = "/index";
            } else url = "/login";
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
            requestDispatcher.forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

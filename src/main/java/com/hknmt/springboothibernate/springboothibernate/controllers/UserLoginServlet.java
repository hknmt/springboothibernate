package com.hknmt.springboothibernate.springboothibernate.controllers;

import com.hknmt.springboothibernate.springboothibernate.dao.UserDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
    private UserDAOImpl userDAO = new UserDAOImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");

        String err = "";
        if (userName.isEmpty() || passWord.isEmpty()) {
            err += "Vui lòng nhập mật khẩu!";
        } else {
            if (!userDAO.checkLogin(userName, passWord)) {
                err += "Tài khoản hoặc mặt khẩu không đúng!";
            }
        }
        if (err.length()>0) {
            request.setAttribute("userName",userName);
            request.setAttribute("err",err);
        }
        String url = "/login";
        try {
            if (err.isEmpty()) {
                HttpSession session = request.getSession();
                session.setAttribute("userName",userName);
                url = "/index";
            }else url = "/login";
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
            requestDispatcher.forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

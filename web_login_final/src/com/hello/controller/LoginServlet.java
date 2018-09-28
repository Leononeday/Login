package com.hello.controller;

import com.hello.domain.User;
import com.hello.model.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //1.接收数据
            response.setCharacterEncoding("UTF-8");
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            //2.封装数据
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            //3.处理数据
            UserModel userModel = new UserModel();
            User existUser = userModel.login(user);
            //4.验证码校验
            String checkcode1 = request.getParameter("checkcode");
            String checkcode2 = (String)request.getSession().getAttribute("checkcode");
            request.getSession().removeAttribute("checkcode");
            if(!checkcode1.equalsIgnoreCase(checkcode2)){
                request.setAttribute("msg","验证码输入错误");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
                return;
            }
            //判断用户名
            if(existUser == null){
                request.setAttribute("msg","用户名或密码错误");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }else {
                //获取当前用户
                HttpSession session = request.getSession();
                session.setAttribute("existUser",existUser);
                //记住用户名
                String remember = request.getParameter("remember");
                if("true".equals(remember)){
                    Cookie cookie = new Cookie("remember",existUser.getUsername());
                    cookie.setPath("/");
                    cookie.setMaxAge(60*60*24);
                    response.addCookie(cookie);
                }

                response.sendRedirect("/success.jsp");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

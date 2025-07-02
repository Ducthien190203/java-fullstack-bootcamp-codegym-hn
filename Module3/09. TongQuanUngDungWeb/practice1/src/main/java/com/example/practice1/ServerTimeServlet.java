package com.example.practice1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "ServerTimeServlet", urlPatterns = "/index")
public class ServerTimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");  // 👈 Khai báo loại dữ liệu trả về
        PrintWriter writer = response.getWriter();

        Date today = new Date();

        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head><title>Server Time</title></head>");
        writer.println("<body>");
        writer.println("<h1>Server Time: " + today + "</h1>");
        writer.println("</body>");
        writer.println("</html>");
    }


//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
}
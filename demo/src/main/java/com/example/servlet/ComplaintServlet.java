package com.example.servlet;

import com.example.model.Complaint;
import com.example.dao.ComplaintDAO;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/submit")
public class ComplaintServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Complaint complaint = new Complaint();
        complaint.setName(req.getParameter("name"));
        complaint.setEmail(req.getParameter("email"));
        complaint.setMessage(req.getParameter("message"));

        new ComplaintDAO().saveComplaint(complaint);

        res.sendRedirect("index.jsp?status=success");
    }
}

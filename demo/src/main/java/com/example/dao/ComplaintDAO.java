package com.example.dao;

import com.example.model.Complaint;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComplaintDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/complaint_db?useUnicode=true&characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASS = "#Nikss@3003";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveComplaint(Complaint c) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String query = "INSERT INTO complaints (name, email, message) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, c.getName());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getMessage());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Complaint> fetchAllComplaints() {
        List<Complaint> complaints = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Explicitly load the MySQL driver
            try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
                conn.setAutoCommit(true); // Ensure auto-commit is enabled
                System.out.println("Database connection successful!");
                String query = "SELECT name, email, message FROM complaints";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Complaint c = new Complaint();
                    c.setName(rs.getString("name"));
                    c.setEmail(rs.getString("email"));
                    c.setMessage(rs.getString("message"));
                    System.out.println("Fetched Complaint: " + c.getName() + ", " + c.getEmail() + ", " + c.getMessage());
                    complaints.add(c);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return complaints;
    }

    public void deleteComplaintByDetails(String name, String email, String message) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String query = "DELETE FROM complaints WHERE name = ? AND email = ? AND message = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, message);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

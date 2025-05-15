package com.example.admin;

import com.example.dao.ComplaintDAO;
import com.example.model.Complaint;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminDashboard {
    private static final Logger logger = Logger.getLogger(AdminDashboard.class.getName());
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private ComplaintDAO complaintDAO;
    private List<Integer> complaintIds; // To map table rows to complaint IDs

    public AdminDashboard() {
        complaintDAO = new ComplaintDAO();
        complaintIds = new ArrayList<>();
        frame = new JFrame("Complaints");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        tableModel = new DefaultTableModel(new Object[]{"Name", "Email", "Message"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table cells non-editable
            }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton showButton = new JButton("Show Complaints");
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadComplaints();
            }
        });
        buttonPanel.add(showButton);

        JButton doneButton = new JButton("Mark as Done");
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markSelectedComplaintsDone();
            }
        });
        buttonPanel.add(doneButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void loadComplaints() {
        try {
            List<Complaint> complaints = complaintDAO.fetchAllComplaints();
            tableModel.setRowCount(0); // Clear existing rows
            for (Complaint c : complaints) {
                tableModel.addRow(new Object[]{c.getName(), c.getEmail(), c.getMessage()});
            }
            System.out.println("Fetched " + complaints.size() + " complaints from DB.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error loading complaints", e);
            JOptionPane.showMessageDialog(frame, "Failed to load complaints: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void markSelectedComplaintsDone() {
        int[] selectedRows = table.getSelectedRows();
        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(frame, "Please select at least one complaint to mark as done.",
                    "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(frame,
                "Are you sure you want to mark the selected complaints as done? This will delete them.",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            for (int row : selectedRows) {
                String name = (String) tableModel.getValueAt(row, 0);
                String email = (String) tableModel.getValueAt(row, 1);
                String message = (String) tableModel.getValueAt(row, 2);

                // Delete complaint based on name, email, and message
                complaintDAO.deleteComplaintByDetails(name, email, message);
            }
            loadComplaints();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting complaints", e);
            JOptionPane.showMessageDialog(frame, "Failed to delete complaints: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminDashboard());
    }
}

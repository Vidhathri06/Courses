/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sqliteproject;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
/**
 *
 * @author gvidh
 */
public class CoursesGUI extends JFrame {
    private JTextField progIdField,idField, nameField, codeField, categoryField, typeField, preReqField, coReqField, progressiveField, plsField;
    private JTable courseTable;
    private DefaultTableModel tableModel;


    public CoursesGUI() {
        setTitle("Course Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(210, 230, 255));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;
        
        addLabel("Program ID:", 0, y, gbc);
        progIdField = addTextField(1, y++, gbc);
        
        addLabel("Course ID:", 0, y, gbc);
        idField = addTextField(1, y++, gbc);
        
        addLabel("Course Name:", 0, y, gbc);
        nameField = addTextField(1, y++, gbc);
        
        addLabel("Course Code:", 0, y, gbc);
        codeField = addTextField(1, y++, gbc);
        
        addLabel("Category:", 0, y, gbc);
        categoryField = addTextField(1, y++, gbc);
        
        addLabel("Type:", 0, y, gbc);
        typeField = addTextField(1, y++, gbc);
        
        addLabel("Pre-Req:", 0, y, gbc);
        preReqField = addTextField(1, y++, gbc);
        
        addLabel("Co-Req:", 0, y, gbc);
        coReqField = addTextField(1, y++, gbc);
        
        addLabel("Progressive:", 0, y, gbc);
        progressiveField = addTextField(1, y++, gbc);
        
        addLabel("PLS:", 0, y, gbc);
        plsField = addTextField(1, y++, gbc);
        
        gbc.gridwidth = 1;
        gbc.gridy = y;
        gbc.gridx = 0;
//        JButton createButton = new JButton("Create");
//        createButton.addActionListener(e -> createCourse());
        JButton createButton = createStyledButton("Create", e -> createCourse());
        add(createButton, gbc);
        
        gbc.gridx = 1;
        JButton retrieveButton = createStyledButton("Retrieve", e -> retrieveCourse());
//        JButton retrieveButton = new JButton("Retrieve");
//        retrieveButton.addActionListener(e -> retrieveCourse());
        add(retrieveButton, gbc);
        
        gbc.gridx = 2;
        JButton updateButton = createStyledButton("Update", e -> updateCourse());
//        JButton updateButton = new JButton("Update");
//        updateButton.addActionListener(e -> updateCourse());
        add(updateButton, gbc);
        
        gbc.gridx = 3;
        JButton deleteButton = createStyledButton("Delete", e -> deleteCourse());
//        JButton deleteButton = new JButton("Delete");
//        deleteButton.addActionListener(e -> deleteCourse());
        add(deleteButton, gbc);
        gbc.gridx = 4; // placing to the right of Delete
        JButton clearButton = createStyledButton("Clear", e -> clearFields());
        add(clearButton, gbc);
        
        String[] columnNames = {"ID", "Program ID", "Code", "Name", "Category", "Type", "Pre-Req", "Co-Req", "Progressive", "PLS"};
        tableModel = new DefaultTableModel(columnNames, 0);
        courseTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(courseTable);

        GridBagConstraints tableGbc = new GridBagConstraints();
        tableGbc.gridx = 0;
        tableGbc.gridy = GridBagConstraints.RELATIVE;
        tableGbc.gridwidth = GridBagConstraints.REMAINDER;
        tableGbc.fill = GridBagConstraints.BOTH;
        tableGbc.weightx = 1.0;
        tableGbc.weighty = 1.0;
        add(scrollPane, tableGbc);
        courseTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = courseTable.getSelectedRow();
                if (selectedRow != -1) {
                    idField.setText(tableModel.getValueAt(selectedRow, 0).toString());
                    progIdField.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    codeField.setText(tableModel.getValueAt(selectedRow, 2).toString());
                    nameField.setText(tableModel.getValueAt(selectedRow, 3).toString());
                    categoryField.setText(tableModel.getValueAt(selectedRow, 4).toString());
                    typeField.setText(tableModel.getValueAt(selectedRow, 5).toString());
                    preReqField.setText(tableModel.getValueAt(selectedRow, 6).toString());
                    coReqField.setText(tableModel.getValueAt(selectedRow, 7).toString());
                    progressiveField.setText(tableModel.getValueAt(selectedRow, 8).toString());
                    plsField.setText(tableModel.getValueAt(selectedRow, 9).toString());
                }
            }
        });
        setLocationRelativeTo(null);
        setVisible(true);
        loadCoursesIntoTable();
    }

    private void addLabel(String text, int x, int y, GridBagConstraints gbc) {
        JLabel label = new JLabel(text);
        label.setForeground(new Color(0, 51, 102)); // Dark blue
        label.setFont(new Font("Arial", Font.BOLD, 12));
       
        gbc.gridx = x;
        gbc.gridy = y;
        add(label, gbc);
//        add(new JLabel(text), gbc);
        
    }
    
    private JTextField addTextField(int x, int y, GridBagConstraints gbc) {
        JTextField field = new JTextField(15);
        field.setBorder(new LineBorder(Color.GRAY, 1));
        gbc.gridx = x;
        gbc.gridy = y;
        add(field, gbc);
        return field;
    }
    private JButton createStyledButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setBackground(new Color(0, 102, 204)); // Dark blue
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.addActionListener(action);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 153, 255)); // Lighter blue on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 102, 204));
            }
        });
        return button;
    }

    private void createCourse() {
        if (progIdField.getText().isEmpty() || nameField.getText().isEmpty() || codeField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠ Program ID, Course Name and Code are required!");
            return;
        }
        
        int progId = Integer.parseInt(progIdField.getText());
        String name = nameField.getText();
        String code = codeField.getText();
        String category = categoryField.getText();
        String type = typeField.getText();
        String preReq = preReqField.getText();
        String coReq = coReqField.getText();
        String progressive = progressiveField.getText();
        String pls = plsField.getText();

        SteveRogers_courses.connect();
        SteveRogers_courses.AP22110010167_courses_create(progId, code, name, category, type, preReq, coReq, progressive, pls);
        JOptionPane.showMessageDialog(this, "✅ Course Added Successfully!");
        clearFields();
        loadCoursesIntoTable();
    }

    private void retrieveCourse() {
        String courseID = idField.getText().trim();
        if (courseID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠ Please enter a Course ID to retrieve.");
            return;
        }

        String query = "SELECT * FROM courses WHERE ID = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/gvidh/Desktop/Apps/mydatabase.db");
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, courseID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                progIdField.setText(rs.getString("prog_id"));
                idField.setText(rs.getString("ID"));
                nameField.setText(rs.getString("cour_name"));
                codeField.setText(rs.getString("cour_code"));
                categoryField.setText(rs.getString("cour_category"));
                typeField.setText(rs.getString("cour_type"));
                preReqField.setText(rs.getString("cour_pre_req"));
                coReqField.setText(rs.getString("cour_co_prereq"));
                progressiveField.setText(rs.getString("cour_progressive"));
                plsField.setText(rs.getString("cour_pls"));
            } else {
                JOptionPane.showMessageDialog(this, "❌ No course found with ID: " + courseID);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "⚠ Database Error: " + e.getMessage());
        }
    }

    private void updateCourse() {
        try{
            if (idField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠ Course ID is required for updating.");
            return;
            }
            int id = Integer.parseInt(idField.getText().trim());
            Integer progId = progIdField.getText().trim().isEmpty() ? null : Integer.parseInt(progIdField.getText().trim());
            String code = codeField.getText().trim().isEmpty() ? null : codeField.getText().trim();
            String name = nameField.getText().trim().isEmpty() ? null : nameField.getText().trim();
            String category = categoryField.getText().trim().isEmpty() ? null : categoryField.getText().trim();
            String type = typeField.getText().trim().isEmpty() ? null : typeField.getText().trim();
            String preReq = preReqField.getText().trim().isEmpty() ? null : preReqField.getText().trim();
            String coReq = coReqField.getText().trim().isEmpty() ? null : coReqField.getText().trim();
            String progressive = progressiveField.getText().trim().isEmpty() ? null : progressiveField.getText().trim();
            String pls = plsField.getText().trim().isEmpty() ? null : plsField.getText().trim();

            SteveRogers_courses.connect();
            SteveRogers_courses.AP22110010181_courses_update(id, progId, code, name, category, type, preReq, coReq, progressive, pls);
            JOptionPane.showMessageDialog(this, "✅ Course Updated!");
            loadCoursesIntoTable();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "⚠ Invalid numeric value in ID.");
        }
    }

    private void deleteCourse() {
        try{
            if (idField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠ Course ID is required for deletion.");
            return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this course?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) return;
             
            int id = Integer.parseInt(idField.getText().trim());
            SteveRogers_courses.connect();
            SteveRogers_courses.AP22110010606_courses_delete(id);
            JOptionPane.showMessageDialog(this, "✅ Course Deleted!");
            clearFields();
            loadCoursesIntoTable();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "⚠ Invalid Course ID.");
            
        }
    }
    private void clearFields() {
        progIdField.setText("");
        idField.setText("");
        nameField.setText("");
        codeField.setText("");
        categoryField.setText("");
        typeField.setText("");
        preReqField.setText("");
        coReqField.setText("");
        progressiveField.setText("");
        plsField.setText("");
    }
    private void loadCoursesIntoTable() {
        tableModel.setRowCount(0); // Clear previous data
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/gvidh/Desktop/Apps/mydatabase.db");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM courses")) {
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("ID"),
                    rs.getInt("prog_id"),
                    rs.getString("cour_code"),
                    rs.getString("cour_name"),
                    rs.getString("cour_category"),
                    rs.getString("cour_type"),
                    rs.getString("cour_pre_req"),
                    rs.getString("cour_co_prereq"),
                    rs.getString("cour_progressive"),
                    rs.getString("cour_pls")
                };
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "⚠ Error loading courses: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new CoursesGUI();
    }
}

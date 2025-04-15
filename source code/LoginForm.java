package com.mycompany.sqliteproject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginForm() {
        setTitle("Login");
        setSize(350, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Username:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        usernameField = new JTextField(15);
        add(usernameField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Password:"), gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        passwordField = new JPasswordField(15);
        add(passwordField, gbc);
        
        JPanel buttonPanel = new JPanel();
        JButton loginButton = new JButton("Login");
        JButton resetButton = new JButton("Reset");
        
        loginButton.addActionListener(e -> authenticateUser());
        resetButton.addActionListener(e -> {
            usernameField.setText("");
            passwordField.setText("");
        });

        buttonPanel.add(loginButton);
        buttonPanel.add(resetButton);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        setVisible(true);
    }

    private void authenticateUser() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠ Please enter both username and password.");
            return;
        }

        if (isValidUser(username, password)) {
            JOptionPane.showMessageDialog(this, "✅ Login Successful!");
            dispose();
            new CoursesGUI();
        } else {
            JOptionPane.showMessageDialog(this, "❌ Invalid Username or Password.");
        }
    }

    private boolean isValidUser(String username, String password) {
        boolean isValid = false;
        String url = "jdbc:sqlite:C:/Users/gvidh/Desktop/Apps/mydatabase.db";
        String query = "SELECT password FROM users WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                System.out.println("Stored Password: " + storedPassword);
                System.out.println("Entered Password: " + password);

                isValid = storedPassword.equals(password); 
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "⚠ Database Error: " + e.getMessage());
        }
        return isValid;
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}

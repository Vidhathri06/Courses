/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sqliteproject;
import java.sql.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


/**
 *
 * @author gvidh
 */
public class SteveRogers_courses {
    private static final String DB_URL = "jdbc:sqlite:C:/Users/gvidh/Desktop/Apps/mydatabase.db";
    private static Connection conn = null;

    public static void connect() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(DB_URL);
                System.out.println("✅ Connected to SQLite database.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Connection failed: " + e.getMessage());
        }
    }

    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("🔌 Database connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("⚠️ Error closing connection: " + e.getMessage());
        }
    }

    public static void AP22110010167_courses_create(int progId, String code, String name,
            String category, String type, String preReq, String coPrereq, String progressive, String pls) {
        String sql = "INSERT INTO courses (prog_id, cour_code, cour_name, cour_category, "+"cour_type, cour_pre_req, cour_co_prereq, cour_progressive, cour_pls) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, progId);
            pstmt.setString(2, code);
            pstmt.setString(3, name);
            pstmt.setString(4, category);
            pstmt.setString(5, type);
            pstmt.setString(6, preReq);
            pstmt.setString(7, coPrereq);
            pstmt.setString(8, progressive);
            pstmt.setString(9, pls);
            pstmt.executeUpdate();
            System.out.println("✅ Course added successfully.");
        } catch (SQLException e) {
            System.out.println("❌ Error inserting course: " + e.getMessage());
        }
    }

    public static void AP22110010180_courses_retrieve() {
        String sql = "SELECT * FROM courses";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                System.out.println("\n📌 Course ID: " + rs.getInt("ID") +
                        "\n📌 Program ID: " + rs.getInt("prog_id") +
                        "\n📌 Code: " + rs.getString("cour_code") +
                        "\n📌 Name: " + rs.getString("cour_name") +
                        "\n📌 Category: " + rs.getString("cour_category") +
                        "\n📌 Type: " + rs.getString("cour_type") +
                        "\n📌 Pre-req: " + rs.getString("cour_pre_req") +
                        "\n📌 Co-prereq: " + rs.getString("cour_co_prereq") +
                        "\n📌 Progressive: " + rs.getString("cour_progressive") +
                        "\n📌 PLS: " + rs.getString("cour_pls") + "\n-------------------");
            }
            if (!hasData) {
                System.out.println("⚠️ No courses found.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error retrieving courses: " + e.getMessage());
        }
    }

    public static void AP22110010181_courses_update(int id, Integer progId, String code, String name, String category, String type, String preReq, String coReq, String progressive, String pls) {
        StringBuilder sql = new StringBuilder("UPDATE courses SET ");
        List<Object> values = new ArrayList<>();
        if (progId != null) {
            sql.append("prog_id = ?, ");
            values.add(progId);
        }
        if (code != null && !code.isEmpty()) {
            sql.append("cour_code = ?, ");
            values.add(code);
        }
        if (name != null && !name.isEmpty()) {
            sql.append("cour_name = ?, ");
            values.add(name);
        }
        if (category != null && !category.isEmpty()) {
            sql.append("cour_category = ?, ");
            values.add(category);
        }
        if (type != null && !type.isEmpty()) {
            sql.append("cour_type = ?, ");
            values.add(type);
        }
        if (preReq != null && !preReq.isEmpty()) {
            sql.append("cour_pre_req = ?, ");
            values.add(preReq);
        }
        if (coReq != null && !coReq.isEmpty()) {
            sql.append("cour_co_prereq = ?, ");
            values.add(coReq);
        }
        if (progressive != null && !progressive.isEmpty()) {
            sql.append("cour_progressive = ?, ");
            values.add(progressive);
        }
        if (pls != null && !pls.isEmpty()) {
            sql.append("cour_pls = ?, ");
            values.add(pls);
        }
        if (values.isEmpty()) {
            System.out.println("⚠️ No fields provided for update.");
            return;
        }
        sql.setLength(sql.length() - 2); 
        sql.append(" WHERE ID = ?");
        values.add(id);
        
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/gvidh/Desktop/Apps/mydatabase.db");
            PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < values.size(); i++) {
                pstmt.setObject(i + 1, values.get(i));
            }
            int updated = pstmt.executeUpdate();
            System.out.println(updated > 0 ? "✅ Updated successfully!" : "❌ No such course with ID " + id);
            } catch (SQLException e) {
                System.out.println("❌ SQL Error: " + e.getMessage());
            }
    }

            

    public static void AP22110010606_courses_delete(int id) {
        String sql = "DELETE FROM courses WHERE ID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Course deleted successfully.");
            } else {
                System.out.println("⚠️ Course not found.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error deleting course: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        connect();

        while (true) {
            System.out.println("\n📌 CRUD Operations: 1. Create  2. Retrieve  3. Update  4. Delete  5. Exit");
            System.out.print("➡️ Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Program ID: ");
                    int progId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Course Code: ");
                    String code = scanner.nextLine();
                    System.out.print("Enter Course Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Course Category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter Course Type: ");
                    String type = scanner.nextLine();
                    System.out.print("Enter Course Pre-Req: ");
                    String preReq = scanner.nextLine();
                    System.out.print("Enter Course Co-Prereq: ");
                    String coPrereq = scanner.nextLine();
                    System.out.print("Enter Course Progressive: ");
                    String progressive = scanner.nextLine();
                    System.out.print("Enter Course PLS: ");
                    String pls = scanner.nextLine();
                    AP22110010167_courses_create(progId, code, name, category, type, preReq, coPrereq, progressive, pls);
                    break;

                case 2:
                    AP22110010180_courses_retrieve();
                    break;

                case 3:
                    System.out.print("Enter Course ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    String fetchSql = "SELECT * FROM courses WHERE ID = ?";
                    try (PreparedStatement fetchStmt = conn.prepareStatement(fetchSql)) {
                        fetchStmt.setInt(1, updateId);
                        ResultSet rs = fetchStmt.executeQuery();
                        if(rs.next()){
                            int currProgId = rs.getInt("prog_id");
                            String currCode = rs.getString("cour_code");
                            String currName = rs.getString("cour_name");
                            String currCategory = rs.getString("cour_category");
                            String currType = rs.getString("cour_type");
                            String currPreReq = rs.getString("cour_pre_req");
                            String currCoPrereq = rs.getString("cour_co_prereq");
                            String currProgressive = rs.getString("cour_progressive");
                            String currPls = rs.getString("cour_pls");
                            
                            System.out.print("Enter new Program ID (or -1 to keep current): ");
                            int newProgId = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Enter new Course Code (leave blank to keep current): ");
                            String newCode = scanner.nextLine();
                            if (newCode.isEmpty()) newCode = currCode;

                            System.out.print("Enter new Course Name (leave blank to keep current): ");
                            String newName = scanner.nextLine();
                            if (newName.isEmpty()) newName = currName;

                            System.out.print("Enter new Course Category (leave blank to keep current): ");
                            String newCategory = scanner.nextLine();
                            if (newCategory.isEmpty()) newCategory = currCategory;
                            System.out.print("Enter new Course Type (leave blank to keep current): ");
                            String newType = scanner.nextLine();
                            if (newType.isEmpty()) newType = currType;

                            System.out.print("Enter new Course Pre-Req (leave blank to keep current): ");
                            String newPreReq = scanner.nextLine();
                            if (newPreReq.isEmpty()) newPreReq = currPreReq;

                            System.out.print("Enter new Course Co-Prereq (leave blank to keep current): ");
                            String newCoPrereq = scanner.nextLine();
                            if (newCoPrereq.isEmpty()) newCoPrereq = currCoPrereq;

                            System.out.print("Enter new Course Progressive (leave blank to keep current): ");
                            String newProgressive = scanner.nextLine();
                            if (newProgressive.isEmpty()) newProgressive = currProgressive;
                            System.out.print("Enter new Course PLS (leave blank to keep current): ");
                            String newPls = scanner.nextLine();
                            if (newPls.isEmpty()) newPls = currPls;
                            if (newProgId == -1) newProgId = currProgId;
                            AP22110010181_courses_update(updateId, newProgId, newCode, newName, newCategory, newType, newPreReq, newCoPrereq, newProgressive, newPls);
                        } else {
                            System.out.println("❌ Course not found with ID: " + updateId);
                        }
                    } catch (SQLException e) {
                        System.out.println("❌ Error fetching course for update: " + e.getMessage());
                    }
                    break;
                   
                    
                case 4:
                    System.out.print("Enter Course ID to delete: ");
                    int deleteId = scanner.nextInt();
                    AP22110010606_courses_delete(deleteId);
                    break;

                case 5:
                    closeConnection();
                    System.out.println("👋 Exiting...");
                    return;

                default:
                    System.out.println("❌ Invalid choice, try again.");
            }
        }
    }
}

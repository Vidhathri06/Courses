/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sqliteproject;

/**
 *
 * @author gvidh
 */
public class SQLiteProject {

    public static void main(String[] args) {
        SteveRogers_courses.connect();  // Connect to SQLite
        SteveRogers_courses.AP22110010180_courses_retrieve();  // Fetch & display courses
        new LoginForm();
    }
}

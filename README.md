-> A Java Swing-based desktop application for managing university course settings, with user login authentication. 
-> This system is connected to an SQLite database for persistent storage of user and course information.

**Features**

**Login Authentication:**

-> User credentials stored securely in SQLite.

-> Password validation during login.

**Course Management (CRUD):**

-> Create, Update, Delete, and Retrieve course details.

-> Uses JTable to display course data.

-> Only accessible after successful login.

**Database:**

-> SQLite database integration.

-> Tables: users, courses.

**Project Structure:**

->  ├── src/ │ └── com/mycompany/sqliteproject/ │ ├── LoginForm.java // Handles user login UI & logic │ ├── SteveRogers_courses.java // Main course management GUI │ ├── mydatabase.db // SQLite database file ├── README.md // This file └── …

 **Requirements**

-> Java JDK 8 or higher

-> SQLite JDBC Driver

-> Any Java IDE (e.g., Eclipse, NetBeans)

**How to Run**

-> Clone or download this repository.

-> Open the project in your IDE.

-> Make sure the SQLite JDBC jar is included in your classpath.

**Check that your database file (mydatabase.db) contains the following:**

**users table:** CREATE TABLE users ( id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT NOT NULL UNIQUE, password TEXT NOT NULL );

**courses table:** CREATE TABLE courses ( id INTEGER PRIMARY KEY AUTOINCREMENT, code TEXT NOT NULL, name TEXT NOT NULL, credits INTEGER, semester INTEGER );

-> Add a test user into the database: INSERT INTO users (username, password) VALUES ('admin', 'admin123');

-> Run the LoginForm.java file.

**Note:**
->Make sure to update the path to your SQLite database in LoginForm.java and SteveRogers_courses.java: jdbc:sqlite:C:/Users/your_path/mydatabase.db
->This is a beginner-friendly project designed for university coursework and desktop practice.












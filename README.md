# Complaint Management System

## Project Overview

This is a simple Complaint Management System built using Java, JSP, and MySQL. It allows users to submit complaints via a web form and provides an admin dashboard to view and manage these complaints.

### Features

- **Complaint Submission:** Users can submit complaints with their name, email, and message through a web form.
- **Admin Dashboard:** Admins can view all submitted complaints in a desktop application.
- **Mark as Done:** Admins can mark complaints as resolved, which deletes them from the database.

## Project Structure

- `src/main/java/com/example/model/Complaint.java` - Complaint data model.
- `src/main/java/com/example/dao/ComplaintDAO.java` - Data Access Object for database operations.
- `src/main/java/com/example/servlet/ComplaintServlet.java` - Servlet to handle complaint submissions.
- `src/main/java/com/example/admin/AdminDashboard.java` - Java Swing application for admin dashboard.
- `src/main/webapp/index.jsp` - Web form for submitting complaints.

## How It Works

1. Users submit complaints via the web form (`index.jsp`).
2. The form posts data to `ComplaintServlet`, which saves the complaint to the MySQL database using `ComplaintDAO`.
3. The admin runs the `AdminDashboard` Java Swing application to view complaints.
4. Admin can manually fetch complaints using the "Show Complaints" button.
5. Admin can select complaints and mark them as done, which deletes them from the database.

## Prerequisites

- Java JDK 8 or higher
- Maven
- MySQL database with a schema named `complaint_db` and a table `complaints` with columns:
  - `name` (varchar)
  - `email` (varchar)
  - `message` (text)

## How to Launch the Project

Open two terminal windows.

### Terminal 1: Run the web server

```bash
mvn clean compile
mvn jetty:run
```

This will start the web server and host the complaint submission form.

### Terminal 2: Build and run the admin dashboard

```bash
mvn clean install
mvn dependency:copy-dependencies
java -cp "target/classes;target/dependency/*" com.example.admin.AdminDashboard
```

This will launch the admin dashboard desktop application.

## Notes

- Make sure the MySQL database is running and accessible with the credentials specified in `ComplaintDAO.java`.
- The admin dashboard fetches complaints from the database and allows managing them.
- Use the "Show Complaints" button in the admin dashboard to manually refresh the complaint list.

## Troubleshooting

- If no complaints appear in the admin dashboard, verify the database connection details and ensure the `complaints` table contains data.
- Check console output for any errors or debug messages.

---

This project demonstrates a simple full-stack Java web application with a desktop admin client.

package com.ganesh.cooliez.data.dto.repository;

import com.ganesh.cooliez.data.dto.Employee;
import java.sql.*;
import java.util.*;

public class EmployeeRepository {
    private static EmployeeRepository instance;

    private EmployeeRepository() {
    }

    public static EmployeeRepository getInstance() {
        if (instance == null) {
            instance = new EmployeeRepository();
        }
        return instance;
    }

    public boolean saveEmployee(Employee employee) {
        String insertUser = "insert into users (name, email, dob, role, status) values (?, ?, ?, ?, ?)";
        String insertCredential = "insert into userLogin (employeeId, email, password) values (?, ?, ?)";

        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt1 = conn.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS)) {
                pstmt1.setString(1, employee.getName());
                pstmt1.setString(2, employee.getEmail());
                pstmt1.setLong(3, employee.getDob());
                String role = (employee.getRole() != null) ? employee.getRole().name() : "EMPLOYEE";
                String status = (employee.getStatus() != null) ? employee.getStatus().name() : "ACTIVE";
                pstmt1.setString(4, role);
                pstmt1.setString(5, status);

                int affectedRows = pstmt1.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                }

                long newId = -1;
                try (ResultSet rs = pstmt1.getGeneratedKeys()) {
                    if (rs.next()) {
                        newId = rs.getLong(1);
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }

                try (PreparedStatement pstmt2 = conn.prepareStatement(insertCredential)) {
                    pstmt2.setLong(1, newId);
                    pstmt2.setString(2, employee.getEmail());
                    pstmt2.setString(3, employee.getPassword());
                    pstmt2.executeUpdate();
                }

                conn.commit();
                return true;
            } catch (SQLException e) {
                if (conn != null) {
                    conn.rollback();
                }
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("Error saving employee to database: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean checkEmployee(String userEmail, String userPassword) {
        String checkQuery = "select employeeId from userLogin where email=?";
        String getQuery = "select * from userLogin where employeeId=?";
        long employeeId = -1;
        String password = null;
        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement statement1 = connection.prepareStatement(checkQuery)) {
                statement1.setString(1, userEmail);
                ResultSet rs1 = statement1.executeQuery();
                while (rs1.next()) {
                    employeeId = rs1.getLong("employeeId");
                }
            }
                try (PreparedStatement statement2 = connection.prepareStatement(getQuery)) {
                    statement2.setLong(1, employeeId);
                    ResultSet rs2 = statement2.executeQuery();
                    while (rs2.next()) {
                        password = rs2.getString("password");
                    }
                }
            } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return userPassword.equals(password);
    }

    public Employee.Role getRole(String userEmail) {
        String roleQuery = "select role from users where email=?";
        Employee.Role userRole = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(roleQuery)) {
            statement.setString(1,userEmail);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String roleStr = rs.getString("role");
                if (roleStr != null) {
                    // Convert String to Enum
                    userRole = Employee.Role.valueOf(roleStr);

                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userRole;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM users where role='EMPLOYEE'";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getString("name"),
                        rs.getString("email"),
                        null,
                        rs.getLong("dob"),
                        Employee.Role.valueOf(rs.getString("role")),
                        Employee.Status.valueOf(rs.getString("status"))
                );
                employee.setEmployeeId(rs.getLong("employeeId"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching employees: " + e.getMessage());
        }
        return employees;
    }

    public List<Employee> getEmployeeDetails(String email) {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM users where email=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1,email);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getString("name"),
                        rs.getString("email"),
                        null,
                        rs.getLong("dob"),
                        Employee.Role.valueOf(rs.getString("role")),
                        Employee.Status.valueOf(rs.getString("status"))
                );
                employee.setEmployeeId(rs.getLong("employeeId"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching employees: " + e.getMessage());
        }
        return employees;
    }

    public List<Employee> getEmployeeDetails(long id) {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM users where employeeId=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setLong(1,id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getString("name"),
                        rs.getString("email"),
                        null,
                        rs.getLong("dob"),
                        Employee.Role.valueOf(rs.getString("role")),
                        Employee.Status.valueOf(rs.getString("status"))
                );
                employee.setEmployeeId(rs.getLong("employeeId"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching employees: " + e.getMessage());
        }
        return employees;
    }

    public long getEmployeeIdByEmail(String email) {
        String query = "SELECT employeeId FROM users WHERE email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getLong("employeeId");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching employee ID: " + e.getMessage());
        }
        return -1;
    }

    public Employee getEmployeeStatus(String email) {
        String query = "select name, employeeId, status, email from users where email=?";

        Employee employee = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                long employeeId = rs.getLong("employeeId");
                Employee.Status status = Employee.Status.valueOf(rs.getString("status"));
                String dbEmail = rs.getString("email");
                employee = new Employee(employeeId, name, status, dbEmail);
            }
        } catch (Exception e) {
            System.err.println("Error in getEmployeeStatus: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return employee;
    }

    public boolean changeEmployeeStatus(Employee employee) {
        String query = "UPDATE users SET status = ? WHERE email = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, employee.getStatus().name());
            statement.setString(2, employee.getEmail());
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            System.err.println("Error in changeEmployeeStatus: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public boolean deleteEmployee(String email) {
        String deleteLogin = "DELETE FROM userLogin WHERE employeeId = (SELECT employeeId FROM users WHERE email = ?)";
        String deleteUser = "DELETE FROM users WHERE email = ?";

        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt1 = conn.prepareStatement(deleteLogin)) {
                pstmt1.setString(1, email);
                pstmt1.executeUpdate();
            }

            try (PreparedStatement pstmt2 = conn.prepareStatement(deleteUser)) {
                pstmt2.setString(1, email);
                int affectedRows = pstmt2.executeUpdate();
                if (affectedRows > 0) {
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                    return false;
                }
            }
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            System.err.println("Error deleting employee: " + e.getMessage());
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



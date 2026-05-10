package com.ganesh.cooliez.data.dto.repository;

import com.ganesh.cooliez.data.dto.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        String sql = "INSERT INTO employees (name, email, dob, role, status) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getEmail());
            pstmt.setLong(3, employee.getDob());
            pstmt.setString(4, employee.getRole().toString());
            pstmt.setString(5, employee.getStatus().toString());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Error saving employee to database.");
            e.printStackTrace();
            return false;
        }
    }
}

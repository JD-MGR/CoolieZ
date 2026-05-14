package com.ganesh.cooliez.data.dto.repository;

import com.ganesh.cooliez.data.dto.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private static TaskRepository taskRepository;

    private TaskRepository() {
    }

    public static TaskRepository getInstance() {
        if (taskRepository == null) {
            taskRepository = new TaskRepository();
        }
        return taskRepository;
    }

    public boolean addTask(Task task) {
        String query = "insert into tasks (taskName,assignedBy,assignedTo,createdAt,dueDate,status,description) values (?,?,?,?,?,?,?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, task.getTaskName());
            statement.setLong(2, task.getAssignedBy());
            statement.setLong(3, task.getAssignedTo());
            statement.setLong(4, task.getCreatedAt());
            statement.setLong(5, task.getDueDate());
            String status = (task.getStatus() != null) ? task.getStatus().name() : "CREATED";
            statement.setString(6, status);
            statement.setString(7, task.getDescription());
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteTask(long taskId) {
        String query = "DELETE FROM tasks WHERE taskId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, taskId);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            System.err.println("Error deleting task: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<Task> allTasks() {
        String query = "select * from tasks";
        List<Task> taskList = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long taskId = rs.getLong("taskId");
                String taskName = rs.getString("taskName");
                long assignedBy = rs.getLong("assignedBy");
                long assignedTo = rs.getLong("assignedTo");
                long createdAt = rs.getLong("createdAt");
                long dueDate = rs.getLong("dueDate");
                Task.Status status = Task.Status.valueOf(rs.getString("status"));
                String description = rs.getString("description");
                Task task = new Task(taskName, description, status, createdAt, dueDate, assignedBy, assignedTo);
                task.setTaskId(taskId);
                taskList.add(task);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return taskList;
    }

    public List<Task> getTasksByEmployeeId(long employeeId) {
        String query = "SELECT * FROM tasks WHERE assignedTo = ?";
        List<Task> taskList = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, employeeId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long taskId = rs.getLong("taskId");
                String taskName = rs.getString("taskName");
                long assignedBy = rs.getLong("assignedBy");
                long assignedTo = rs.getLong("assignedTo");
                long createdAt = rs.getLong("createdAt");
                long dueDate = rs.getLong("dueDate");
                Task.Status status = Task.Status.valueOf(rs.getString("status"));
                String description = rs.getString("description");
                Task task = new Task(taskName, description, status, createdAt, dueDate, assignedBy, assignedTo);
                task.setTaskId(taskId);
                taskList.add(task);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return taskList;
    }

    public boolean updateTaskStatus(long taskId, Task.Status status) {
        String query = "UPDATE tasks SET status = ? WHERE taskId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, status.name());
            statement.setLong(2, taskId);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            System.err.println("Error updating task status: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

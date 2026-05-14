package com.ganesh.cooliez.features.Employee;

import com.ganesh.cooliez.data.dto.Task;
import com.ganesh.cooliez.features.home.EmployeeProfile.EmployeeProfileView;
import com.ganesh.cooliez.util.ScannerHelper;

import java.util.List;
import java.util.Scanner;

public class EmployeeFeaturesView {
    private EmployeeFeaturesModel employeeFeaturesModel;
    private Scanner scanner;
    private EmployeeProfileView employeeProfileView;

    public EmployeeFeaturesView() {
        this.employeeFeaturesModel = new EmployeeFeaturesModel(this);
        this.scanner = ScannerHelper.getScannerHelper();
        this.employeeProfileView = new EmployeeProfileView();
    }

    public void showEmployeeDashboard(String email) {
        while (true) {
            System.out.println("\n-----Welcome to Employee Section-----");
            System.out.println("""
                    1. View My Tasks
                    2. Update Task Status
                    3. View Notifications
                    4. View My Profile
                    5. Logout
                    Enter your choice:""");

            if (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Invalid choice! Please enter a number.");
                continue;
            }

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> viewMyTasks(email);
                case 2 -> updateTaskStatus();
                case 3 -> viewNotifications(email);
                case 4 -> employeeFeaturesModel.viewProfile(email);
                case 5 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void viewMyTasks(String email) {
        List<Task> tasks = employeeFeaturesModel.getMyTasks(email);
        if (tasks == null || tasks.isEmpty()) {
            System.out.println("No tasks assigned to you.");
            return;
        }
        System.out.println("\n--- My Tasks ---");
        for (Task task : tasks) {
            System.out.println("ID: " + task.getTaskId() + " | Name: " + task.getTaskName() + " | Status: " + task.getStatus());
        }
    }

    private void updateTaskStatus() {
        System.out.println("Enter Task ID to update:");
        if (scanner.hasNextLong()) {
            long taskId = scanner.nextLong();
            System.out.println("Enter new status (1. INPROGRESS, 2. COMPLETED):");
            int statusChoice = scanner.nextInt();
            Task.Status status = (statusChoice == 1) ? Task.Status.INPROGRESS : Task.Status.COMPLETED;
            if (employeeFeaturesModel.updateTaskStatus(taskId, status)) {
                System.out.println("Task status updated successfully!");
            } else {
                System.out.println("Failed to update task status. Please check Task ID.");
            }
        } else {
            scanner.next();
            System.out.println("Invalid ID!");
        }
    }

    private void viewNotifications(String email) {
        System.out.println("Feature coming soon!");
    }

    public void showProfileDetails(String email) {
        employeeProfileView.showEmployeeDetails(email);
    }
}

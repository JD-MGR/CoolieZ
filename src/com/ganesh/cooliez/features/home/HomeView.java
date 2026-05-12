package com.ganesh.cooliez.features.home;

import com.ganesh.cooliez.data.dto.Employee;
import com.ganesh.cooliez.util.ScannerHelper;

import java.util.List;
import java.util.Scanner;

public class HomeView {
    private Scanner scanner;
    private HomeModel homeModel;
   public HomeView(){
        scanner= ScannerHelper.getScannerHelper();
        homeModel=new HomeModel(this);
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
                case 1 -> homeModel.viewAssignedTasks(email);
                case 2 -> {
                    System.out.println("Enter Task ID to update:");
                    if (scanner.hasNextLong()) {
                        long taskId = scanner.nextLong();
                        homeModel.updateTaskStatus(taskId);
                    } else {
                        scanner.next();
                        System.out.println("Invalid ID!");
                    }
                }
                case 3 -> homeModel.viewAlerts(email);
                case 4 -> homeModel.viewProfile(email);
                case 5 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    public void showManagerDashboard(String email) {
        while (true) {
            System.out.println("\n-----Welcome to Manager Section-----");
            System.out.println("""
                    1. View All Employees
                    2. Assign New Task
                    3. Monitor Task Progress
                    4. Manage Alerts
                    5. Remove/Suspend User
                    6. Logout
                    Enter your choice:""");

            if (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Invalid choice! Please enter a number.");
                continue;
            }

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> homeModel.listAllEmployees();
                case 2 -> homeModel.assignNewTask(email);
                case 3 -> homeModel.viewAllTaskProgress();
                case 4 -> homeModel.manageAlerts();
                case 5 -> {
                    showDescisionBoard();
                }
                case 6 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void showDescisionBoard() {
        System.out.println("""
                            Select any option
                            1.Update Status
                            2.Delete Employee""");
        int decision =scanner.nextInt();
        if(decision==1) {
          homeModel.updateEmployeeStatus();
        }
        else if(decision==2) {
          homeModel.deleteEmployee();
        }
        else {
            System.out.println("Please Enter a valid choice");
        }
    }
}

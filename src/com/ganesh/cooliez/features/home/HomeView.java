package com.ganesh.cooliez.features.home;

import com.ganesh.cooliez.data.dto.Employee;
import com.ganesh.cooliez.features.Manager.ManagerFeaturesView;
import com.ganesh.cooliez.features.Employee.EmployeeFeaturesView;
import com.ganesh.cooliez.util.ScannerHelper;

import java.util.List;
import java.util.Scanner;

public class HomeView {
    private Scanner scanner;
    private HomeModel homeModel;
    private ManagerFeaturesView managerFeaturesView;
    private EmployeeFeaturesView employeeFeaturesView;
   public HomeView(){
        scanner= ScannerHelper.getScannerHelper();
        homeModel=new HomeModel(this);
        managerFeaturesView=new ManagerFeaturesView();
        employeeFeaturesView=new EmployeeFeaturesView();
    }
    public void showEmployeeDashboard(String email) {
        employeeFeaturesView.showEmployeeDashboard(email);
    }

    public void showManagerDashboard(String email) {
        while (true) {
            System.out.println("\n-----Welcome to Manager Section-----");
            System.out.println("""
                    1. View All Employees
                    2. Assign New Task
                    3. Monitor Task Progress
                    4. Delete Tasks
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
                case 4 -> homeModel.deleteTask();
                case 5 -> {
                    managerFeaturesView.showDescisionBoard();
                }
                case 6 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}

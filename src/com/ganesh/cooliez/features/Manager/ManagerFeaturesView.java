package com.ganesh.cooliez.features.Manager;

import com.ganesh.cooliez.data.dto.Employee;
import com.ganesh.cooliez.data.dto.Task;
import com.ganesh.cooliez.features.home.EmployeeProfile.EmployeeProfileModel;
import com.ganesh.cooliez.features.home.EmployeeProfile.EmployeeProfileView;
import com.ganesh.cooliez.util.ParseHelper;
import com.ganesh.cooliez.util.ScannerHelper;

import java.util.Scanner;



public class ManagerFeaturesView {
    private ManagerFeaturesModel managerFeaturesModel;
    private Scanner scanner;
    private EmployeeProfileView employeeProfileView;

    public ManagerFeaturesView() {
        this.managerFeaturesModel = new ManagerFeaturesModel(this);
        this.scanner = ScannerHelper.getScannerHelper();
        this.employeeProfileView = new EmployeeProfileView();
    }

    public void showDescisionBoard() {
        System.out.println("""
                Select any option
                1.Update Status
                2.Delete Employee""");
        int decision = scanner.nextInt();
        if (decision == 1) {
            promptUpdate();
        } else if (decision == 2) {
            promptDeletion();
        } else {
            System.out.println("Please Enter a valid choice");
        }
    }

    public void promptUpdate() {
        System.out.println("Enter Employee email : ");
        String email = scanner.next();
        if (managerFeaturesModel.getEmployeeStatus(email)) {
            System.out.println("Status Updated Successfully");
        }
    }

    public boolean changeEmployeeStatus(Employee employee) {
        System.out.println();
        System.out.println("Employee Name : " + employee.getName());
        System.out.println("Employee Id : " + employee.getEmployeeId());
        System.out.println("Employee Current Status : " + employee.getStatus().toString());
        System.out.println();
        System.out.println("Do you want to change the Employee Status ?(y/n)");
        String choice = scanner.next();
        return choice.equalsIgnoreCase("y");
    }

    public void promptDeletion() {
        System.out.println();
        System.out.println("Enter Employee Email : ");
        String email = scanner.next();
        if (showEmployeeDetails(email)) {
            System.out.println("Do you really want to delete this account? (y/n) ");
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("y")) {
                if (managerFeaturesModel.deleteEmployee(email)) {
                    System.out.println("Employee Deleted Successfully");
                }
            }
        } else {
            System.out.println("Please check if you entered the correct email");
        }
    }

    public boolean showEmployeeDetails(String email) {
        return employeeProfileView.showEmployeeDetails(email);
    }
    void display(String s){
        System.out.println(s);
    }
}

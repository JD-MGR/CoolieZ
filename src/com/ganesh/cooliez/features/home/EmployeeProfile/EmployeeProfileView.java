package com.ganesh.cooliez.features.home.EmployeeProfile;

import com.ganesh.cooliez.data.dto.Employee;
import com.ganesh.cooliez.util.ScannerHelper;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class EmployeeProfileView {
    private EmployeeProfileModel employeeProfileModel;
    private Scanner scanner;
    public EmployeeProfileView() {
        employeeProfileModel = new EmployeeProfileModel(this);
        scanner= ScannerHelper.getScannerHelper();
    }
    public void showAllEmployees() {
      employeeProfileModel.getAllEmployees();
    }
    void displayAllEmployees(List<Employee> employeeList) {
        for(Employee employee:employeeList){
            System.out.println();
            System.out.println("Employee Name : "+employee.getName());
            System.out.println("Employee Id : "+employee.getEmployeeId());
            System.out.println("Employee Mail Id : "+employee.getEmail());
            System.out.println("Employee date of birth : "+toDate(employee.getDob()));
            System.out.println("Employee Status : "+employee.getStatus().toString() );
            System.out.println();
        }
    }

    public void showEmployeeDetails(String email) {
        employeeProfileModel.getEmployeeDetails(email);
    }

   

    String toDate(long date){
        LocalDate dateOnly = Instant.ofEpochMilli(date)
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        String formattedDate = dateOnly.format(formatter);
        return formattedDate;
    }

    public void promptUpdate() {
        System.out.println("Enter Employee email : ");
        String email=scanner.next();
        if(employeeProfileModel.getEmployeeStatus(email)) System.out.println("Status Updates Successfully");
    }

    public boolean changeEmployeeStatus(Employee employee) {
        System.out.println();
        System.out.println("Employee Name : "+employee.getName());
        System.out.println("Employee Id : "+employee.getEmployeeId());
        System.out.println("Employee Current Status : "+employee.getStatus().toString());
        System.out.println();
        System.out.println("Do you want to change the Employee Status ?(y/n)");
        String choice=scanner.next();
        return choice.equalsIgnoreCase("y");
    }

    public void promptDeletion() {
        System.out.println();
        System.out.println("Enter Employee Email : ");
        String email= scanner.next();
        if(employeeProfileModel.getEmployeeDetails(email)){
            System.out.println("Do you really want to delete this account? (y/n) ");
            String choice=scanner.next();
            if(choice.equalsIgnoreCase("y")) {
                if(employeeProfileModel.deleteEmployee(email))
                    System.out.println("Employee Deleted Successfully");
            } else
                return;
        }
    else {
            System.out.println("Please check if you entered the correct email");
        }
    }
}

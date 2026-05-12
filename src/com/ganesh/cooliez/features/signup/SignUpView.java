package com.ganesh.cooliez.features.signup;

import com.ganesh.cooliez.data.dto.Employee;
import com.ganesh.cooliez.util.ScannerHelper;
import java.util.Scanner;

public class SignUpView {
    private SignUpModel signUpModel;
    private Scanner scanner;

    public SignUpView(){
        signUpModel=new SignUpModel(this);
        scanner= ScannerHelper.getScannerHelper();
    }

    public void init(){
        showSignUpDashBoard();
    }

    private void showSignUpDashBoard() {
        System.out.println("-----Welcome to SignUp-----");
        String name = promptName();
        String email = promptEmail();
        String password=promptPassword();
        long dob = promptDob();
        Employee.Role role = promptRole();
        Employee.Status status= Employee.Status.ACTIVE;
        
        if (signUpModel.signUp(name, email,password, dob, role,status)) {
            System.out.println("Registration Successful for: " + name);
        } else {
            System.out.println("Registration failed. Database error.");
        }
    }


    public void display(String message){
        System.out.println(message);
    }

    private String promptName(){
        while(true) {
            System.out.println("Enter your Name : ");
            String name = scanner.nextLine();
            if(signUpModel.validateName(name)) return name.trim();
        }
    }

    private String promptEmail(){
        while(true) {
            System.out.println("Enter your Email : ");
            String email = scanner.next();
            if(signUpModel.validateEmail(email)) return email.trim();
        }
    }

    private String promptPassword() {
        while ((true)) {
            System.out.println("Enter your Password : ");
            String password= scanner.next();
            if(signUpModel.validatePassword(password)) return password.trim();
        }
    }
    private long promptDob(){
        while(true) {
            System.out.println("Enter your Dob (dd-mm-yyyy): ");
            String dobStr = scanner.next();
            long dobLong = signUpModel.parseDOB(dobStr);
            if(dobLong != -1 && signUpModel.validateDOB(dobLong)) return dobLong;
        }
    }

    private Employee.Role promptRole(){
        while(true) {
            System.out.println(""" 
                Choose your Role
                1.Manager
                2.Employee""");
            try {
                if (!scanner.hasNextInt()) {
                    scanner.next(); 
                    System.out.println("Please enter 1 or 2.");
                    continue;
                }
                int choice = scanner.nextInt();
                Employee.Role role = signUpModel.assignRole(choice);
                if(role != null) return role;
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scanner.next();
            }
        }
    }
}

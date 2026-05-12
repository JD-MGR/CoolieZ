package com.ganesh.cooliez.features.signin;

import com.ganesh.cooliez.data.dto.Employee;
import com.ganesh.cooliez.features.home.HomeView;
import com.ganesh.cooliez.util.ScannerHelper;

import java.util.Scanner;

public class SignInView {
    private Scanner scanner;
    private SignInModel signInModel;
    private HomeView homeView;

    public SignInView() {
        scanner = ScannerHelper.getScannerHelper();
        signInModel = new SignInModel(this);
        homeView=new HomeView();
    }

    public void init() {
        startSignIn();
    }

    private void startSignIn() {
        System.out.println("-----Welcome to Login Page-----");
        String userEmail = promptUserEmail();
        String userPassword = promptUserPassword();
        if(signInModel.isUserAllowed(userEmail,userPassword)){
            System.out.println("User logged in Successfully ,");
           if(signInModel.getRole(userEmail).equals(Employee.Role.EMPLOYEE)){
                homeView.showEmployeeDashboard();
           }
           if(signInModel.getRole(userEmail).equals(Employee.Role.MANAGER)){
               homeView.showManagerDashboard();
           }
        }
         else System.out.println("Sign in failed !");
    }


    private String promptUserEmail() {
        while (true) {
            System.out.println("Enter your Registered Email : ");
            String email = scanner.next();
            if (signInModel.validateEmail(email)) return email.trim();
        }
    }

    private String promptUserPassword() {
        while (true) {
            System.out.println("Enter your Password : ");
            String password = scanner.next();
            if(signInModel.validatePassword(password)) return password.trim();
        }
    }

    public void display(String s) {
        System.out.println(s);
    }
}

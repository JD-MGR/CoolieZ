package com.ganesh.cooliez;

import com.ganesh.cooliez.features.signin.SignInView;
import com.ganesh.cooliez.features.signup.SignUpView;
import com.ganesh.cooliez.util.ScannerHelper;
import java.util.Scanner;

public class CoolieZApp {
    public static final int VERSION_NO = 1;
    public static final String VERSION_NAME = "0.0.1";

    public static void main(String[] args) {
        System.out.println("============[ CoolieZ app ]=============");
        System.out.println("Version No : " + VERSION_NAME);
        showLandingMenu();
    }

    private static void showLandingMenu() {
        Scanner scanner = ScannerHelper.getScannerHelper();
        while (true) {
            System.out.println("\n-----Welcome to the Cooliez App------");
            System.out.println("""
                    Choose an option : 
                    1.SignUp
                    2.SignIn
                    3.Exit
                    Enter your choice : """);
            
            if (!scanner.hasNextInt()) {
                scanner.next(); 
                System.out.println("Invalid Choice please try again");
                continue;
            }
            
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    new SignUpView().init();
                }
                case 2 -> {
                    new SignInView().init();
                }
                case 3 -> {
                    System.out.println("Thankyou for using CoolieZ app");
                    return;
                }
                default -> {
                    System.out.println("Invalid Choice please try again");
                }
            }
        }
    }
}

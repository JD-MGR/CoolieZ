package com.ganesh.cooliez.features.signup;

import com.ganesh.cooliez.util.ScannerHelper;

import java.util.Scanner;

public class SignUpView {
    private SignUpModel signUpModel;
    private Scanner scanner;
    SignUpView(){
        signUpModel=new SignUpModel(this);
        scanner= ScannerHelper.getScannerHelper();
    }
    public void init(){
        showSignUpDashBoard();
    }

    private void showSignUpDashBoard() {
        System.out.println("-----Welcome to SignUp-----");
        String name=promptName();
    }
}

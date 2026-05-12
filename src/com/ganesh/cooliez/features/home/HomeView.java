package com.ganesh.cooliez.features.home;

import com.ganesh.cooliez.util.ScannerHelper;

import java.util.Scanner;

public class HomeView {
    private Scanner scanner;
    private HomeModel homeModel;
   public HomeView(){
        scanner= ScannerHelper.getScannerHelper();
        homeModel=new HomeModel(this);
    }
    public void showEmployeeDashboard() {
        System.out.println();
        System.out.println("-----Welcome to Employee Section-----");

    }

    public void showManagerDashboard() {
        System.out.println();
        System.out.println("-----Welcome to Manager Section-----");
    }
}

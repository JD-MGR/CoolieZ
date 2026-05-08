package com.ganesh.cooliez.util;

import java.util.Scanner;

public class ScannerHelper {
    private ScannerHelper(){}
    private static final Scanner scannerHelper=new Scanner(System.in);
    public static  Scanner getScannerHelper(){
        return scannerHelper;
    }
}

package com.ganesh.cooliez.features.Manager.taskmanagement;

import com.ganesh.cooliez.util.ScannerHelper;

import java.util.Scanner;

public class TaskDeletionView {
    private TaskDeletionModel taskDeletionModel;
    private Scanner scanner;
    public TaskDeletionView(){
        this.scanner= ScannerHelper.getScannerHelper();
        this.taskDeletionModel=new TaskDeletionModel(this);
    }

    public void promptTaskDeletion() {
        taskDeletionModel.getAllTasks();
    }

    public long confirmDeletion() {
        System.out.println("Enter the task id you want to delete : ");
        long id= scanner.nextLong();
        return id;
    }
}

package com.ganesh.cooliez.features.Manager.taskmanagement;

import com.ganesh.cooliez.data.dto.Task;
import com.ganesh.cooliez.util.ParseHelper;
import com.ganesh.cooliez.util.ScannerHelper;

import java.util.Scanner;

public class TaskCreationView {
    private Scanner scanner;
    private TaskCreationModel taskCreationModel;

    public TaskCreationView() {
        this.scanner = ScannerHelper.getScannerHelper();
        taskCreationModel = new TaskCreationModel(this);
    }

    public void promptNewTask(String managerEmail) {
        scanner.nextLine(); // Clear buffer
        String taskName = prompTaskName();
        String description = promptDescription();
        Task.Status status = Task.Status.CREATED;
        long createdAt = System.currentTimeMillis();
        long dueDate = promptDueDate();
        String assignedToEmail = promptAssignedToEmail();
        
        long assignedBy = taskCreationModel.getEmployeeId(managerEmail);
        long assignedTo = taskCreationModel.getEmployeeId(assignedToEmail);

        if (assignedBy == -1 || assignedTo == -1) {
            System.out.println("Error: Could not find employee details for assignment.");
            return;
        }

        Task task = new Task(taskName, description, status, createdAt, dueDate, assignedBy, assignedTo);
        taskCreationModel.createTask(task);
    }

    private String prompTaskName() {
        while (true) {
            System.out.println("Enter Task name : ");
            String taskName = scanner.nextLine().trim();
            if (taskCreationModel.checkTaskName(taskName)) {
                return taskName;
            }
        }
    }

    private String promptDescription() {
        while (true) {
            System.out.println("Enter Task Description : ");
            String description = scanner.nextLine().trim();
            if (taskCreationModel.checkTaskDescription(description)) {
                return description;
            }
        }
    }

    private long promptDueDate() {
        while (true) {
            System.out.println("Enter the due date in format(dd-MM-yy) : ");
            String dueDateString = scanner.next();
            long dueDate = ParseHelper.parseDateToLong(dueDateString);
            if (taskCreationModel.checkDueDate(dueDate)) {
                return dueDate;
            }
        }
    }
    private String promptAssignedToEmail(){
        while (true){
            System.out.println("Enter employee email to assign task : ");
            String assignedTo = scanner.next();
            if(taskCreationModel.checkAssignedToEmail(assignedTo)){
                return assignedTo;
            }
        }
    }
    void display(String s){
        System.out.println(s);
    }
}

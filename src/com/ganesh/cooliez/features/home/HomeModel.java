package com.ganesh.cooliez.features.home;

import com.ganesh.cooliez.features.Manager.taskmanagement.TaskCreationView;
import com.ganesh.cooliez.features.Manager.taskmanagement.TaskDeletionView;
import com.ganesh.cooliez.features.home.EmployeeProfile.EmployeeProfileModel;
import com.ganesh.cooliez.features.home.EmployeeProfile.EmployeeProfileView;
import com.ganesh.cooliez.features.Manager.ManagerFeaturesView;

public class HomeModel {
    private HomeView homeView;
    private EmployeeProfileView employeeProfileView;
    private TaskCreationView taskCreationView;
    private TaskDeletionView taskDeletionView;

    HomeModel(HomeView homeView) {
        this.homeView = homeView;
        employeeProfileView = new EmployeeProfileView();
        taskCreationView=new TaskCreationView();
        taskDeletionView=new TaskDeletionView();
    }

    public void listAllEmployees() {
        employeeProfileView.showAllEmployees();
    }

    public void assignNewTask(String managerEmail) {
        taskCreationView.promptNewTask(managerEmail);
    }

    public void viewAllTaskProgress() {

    }

    public void manageAlerts() {
    }

    public void deleteTask() {
        taskDeletionView.promptTaskDeletion();
    }
}

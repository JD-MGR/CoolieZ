package com.ganesh.cooliez.features.home;

import com.ganesh.cooliez.features.home.EmployeeProfile.EmployeeProfileModel;
import com.ganesh.cooliez.features.home.EmployeeProfile.EmployeeProfileView;

public class HomeModel {
    private HomeView homeView;
    private EmployeeProfileView employeeProfileView;
    private EmployeeProfileModel employeeProfileModel;

    HomeModel(HomeView homeView) {
        this.homeView = homeView;
        employeeProfileView = new EmployeeProfileView();
    }

    public void viewAssignedTasks(String email) {

    }

    public void updateTaskStatus(long taskId) {

    }

    public void viewAlerts(String email) {

    }

    public void viewProfile(String email) {
        employeeProfileView.showEmployeeDetails(email);
    }

    public void listAllEmployees() {
        employeeProfileView.showAllEmployees();
    }

    public void assignNewTask(String managerEmail) {

    }

    public void viewAllTaskProgress() {

    }

    public void manageAlerts() {

    }

    public void updateEmployeeStatus() {
        employeeProfileView.promptUpdate();
    }

    public void deleteEmployee() {
        employeeProfileView.promptDeletion();
    }
}

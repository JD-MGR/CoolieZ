package com.ganesh.cooliez.features.Employee;

import com.ganesh.cooliez.data.dto.Employee;
import com.ganesh.cooliez.data.dto.Task;
import com.ganesh.cooliez.data.dto.repository.EmployeeRepository;
import com.ganesh.cooliez.data.dto.repository.TaskRepository;

import java.util.List;

public class EmployeeFeaturesModel {
    private EmployeeFeaturesView employeeFeaturesView;
    private EmployeeRepository employeeRepository;
    private TaskRepository taskRepository;

    public EmployeeFeaturesModel(EmployeeFeaturesView employeeFeaturesView) {
        this.employeeFeaturesView = employeeFeaturesView;
        this.employeeRepository = EmployeeRepository.getInstance();
        this.taskRepository = TaskRepository.getInstance();
    }

    public List<Task> getMyTasks(String email) {
        long employeeId = employeeRepository.getEmployeeIdByEmail(email);
        if (employeeId == -1) return null;
        return taskRepository.getTasksByEmployeeId(employeeId);
    }

    public boolean updateTaskStatus(long taskId, Task.Status status) {
        return taskRepository.updateTaskStatus(taskId, status);
    }

    public void viewProfile(String email) {
        employeeFeaturesView.showProfileDetails(email);
    }
}

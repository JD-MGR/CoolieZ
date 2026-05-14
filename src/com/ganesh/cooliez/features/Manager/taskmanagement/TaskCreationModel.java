package com.ganesh.cooliez.features.Manager.taskmanagement;

import com.ganesh.cooliez.data.dto.Task;
import com.ganesh.cooliez.data.dto.repository.EmployeeRepository;
import com.ganesh.cooliez.data.dto.repository.TaskRepository;

public class TaskCreationModel {
    private TaskCreationView taskCreationView;
    private EmployeeRepository employeeRepository;
    private TaskRepository taskRepository;
    TaskCreationModel(TaskCreationView taskCreationView){
        this.taskCreationView=taskCreationView;
        this.employeeRepository=EmployeeRepository.getInstance();
        this.taskRepository=TaskRepository.getInstance();
    }
    boolean checkTaskName(String next) {
        if(next==null||next.length()<3){
            taskCreationView.display("Taskname canot be empty or less than 3 characters");
            return false;
        }
        return true;
    }

    boolean checkTaskDescription(String next) {
        if(next==null||next.length()<5){
            taskCreationView.display("Description cannot be null or less than 5 characters");
            return false;
        }
      return true;
    }

    boolean checkDueDate(long dueDate) {
        if(dueDate<=System.currentTimeMillis()){
            taskCreationView.display("Due date cannot be past or present");
            return false;
        }
       return true;
    }

    boolean checkAssignedToEmail(String next) {
        if(next==null){
            taskCreationView.display("Employee mail cannot be Empty");
            return false;
        }
        var employee=employeeRepository.getEmployeeDetails(next);
        if(employee==null||employee.isEmpty()){
            taskCreationView.display("Employee doesn't Exist!");
            return false;
        }
       return true;
    }

    public long getEmployeeId(String email) {
        return employeeRepository.getEmployeeIdByEmail(email);
    }

    public void createTask(Task task) {
        if(taskRepository.addTask(task)){
            System.out.println("Task created Successfully");
        }
        else System.out.println("Task creation failed");
    }
}

package com.ganesh.cooliez.features.task;

import com.ganesh.cooliez.data.dto.Task;
import com.ganesh.cooliez.util.ParseHelper;

import java.util.List;
import java.util.Scanner;

public class TaskView {
    private TaskModel taskModel;
    private Scanner scanner;
    public TaskView(){

    }
    public void displayAllTasks(List<Task> taskList){
    for(Task task:taskList){
        System.out.println("Task id : "+task.getTaskId());
        System.out.println("Task name : "+task.getTaskName());
        System.out.println("Task Description : " +task.getDescription());
        System.out.println("Task Created at : "+ ParseHelper.formatDateTime(task.getCreatedAt()));
        System.out.println("Task due date : "+ParseHelper.formatDateTime(task.getDueDate()));
    }
    }
}

package com.ganesh.cooliez.features.Manager.taskmanagement;

import com.ganesh.cooliez.data.dto.Task;
import com.ganesh.cooliez.data.dto.repository.TaskRepository;
import com.ganesh.cooliez.features.task.TaskView;

import java.util.List;

public class TaskDeletionModel {
    private TaskDeletionView taskDeletionView;
    private TaskRepository taskRepository;
    private TaskView taskView;
    TaskDeletionModel(TaskDeletionView taskDeletionView){
        this.taskDeletionView=taskDeletionView;
        this.taskRepository=TaskRepository.getInstance();
        this.taskView=new TaskView();
    }

    public void getAllTasks() {
        List<Task> taskList=taskRepository.allTasks();
        taskView.displayAllTasks(taskList);
        long id= taskDeletionView.confirmDeletion();
        taskRepository.deleteTask(id);
    }
}

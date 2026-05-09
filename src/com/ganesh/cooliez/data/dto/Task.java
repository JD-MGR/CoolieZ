package com.ganesh.cooliez.data.dto;

import java.util.List;

public class Task {
    private long taskId;
    private String description;
    private String taskName;
    private Status status;
    private long createdAt;
    private long dueDate;
    private long assignedBy;
    private List<Long> assignedTo;

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getDueDate() {
        return dueDate;
    }

    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }

    public long getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(long assignedBy) {
        this.assignedBy = assignedBy;
    }

    public List<Long> getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(List<Long> assignedTo) {
        this.assignedTo = assignedTo;
    }

    public enum Status{
        COMPLETED,INPROGRESS,NOT_COMPLETED
    }
}

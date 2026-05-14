package com.ganesh.cooliez.data.dto;

public class Task {
    private long taskId;
    private String description;
    private String taskName;
    private Status status;
    private long createdAt;
    private long dueDate;

    public Task(String taskName, String description, Status status, long createdAt, long dueDate, long assignedBy, long assignedTo) {
        this.description = description;
        this.taskName = taskName;
        this.status = status;
        this.createdAt = createdAt;
        this.dueDate = dueDate;
        this.assignedBy = assignedBy;
        this.assignedTo = assignedTo;
    }

    public Task(long taskId, String description, String taskName, Status status, long createdAt, long dueDate, long assignedBy, long assignedTo) {
        this.taskId = taskId;
        this.description = description;
        this.taskName = taskName;
        this.status = status;
        this.createdAt = createdAt;
        this.dueDate = dueDate;
        this.assignedBy = assignedBy;
        this.assignedTo = assignedTo;
    }

    private long assignedBy;
    private long assignedTo;

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

    public long getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(long assignedTo) {
        this.assignedTo = assignedTo;
    }

    public enum Status{
        COMPLETED,INPROGRESS,CREATED
    }
}

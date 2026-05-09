package com.ganesh.cooliez.data.dto;

import java.util.List;

public class Alert {
    private long alertId;
    private List<Long> employeeId;
    private long taskId;
    private String message;
    private Type type;

    public enum Type{
        ASSIGNMENT,STATUS,REMAINDER
    }

    public long getAlertId() {
        return alertId;
    }

    public void setAlertId(long alertId) {
        this.alertId = alertId;
    }

    public List<Long> getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(List<Long> employeeId) {
        this.employeeId = employeeId;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}

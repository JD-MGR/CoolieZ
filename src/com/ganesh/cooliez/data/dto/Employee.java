package com.ganesh.cooliez.data.dto;

public class Employee {
    private long employeeId;
    private String name;
    private long dob;
    private Status status;
    private Role role;
    private String email;
    private String password;

    public Employee(String name, String email, String password, long dob, Role role,Status status){
        this.name=name;
        this.email=email;
        this.dob=dob;
        this.password=password;
        this.role=role;
        this.status=status;
    }

    public Employee(long employeeId, String name, Status status,String email) {
        this.employeeId = employeeId;
        this.name = name;
        this.status = status;
        this.email=email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public enum Status{
        ACTIVE,INACTIVE
    }
    public enum Role{
        MANAGER,EMPLOYEE
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDob() {
        return dob;
    }

    public void setDob(long dob) {
        this.dob = dob;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public void setEmail(String email){
        this.email=email;
    }

    public String getEmail(){
        return email;
    }

}

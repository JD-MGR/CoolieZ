package com.ganesh.cooliez.features.home.EmployeeProfile;

import com.ganesh.cooliez.data.dto.Employee;
import com.ganesh.cooliez.data.dto.repository.EmployeeRepository;

import java.util.List;

public class EmployeeProfileModel {
    private EmployeeProfileView employeeProfileView;
    private EmployeeRepository employeeRepository;
    EmployeeProfileModel(EmployeeProfileView employeeProfileView){
        this.employeeProfileView=employeeProfileView;
        employeeRepository=EmployeeRepository.getInstance();
    }
    public void getAllEmployees() {
        List<Employee> employeeList = employeeRepository.getAllEmployees();
        employeeProfileView.displayAllEmployees(employeeList);
    }

    public boolean getEmployeeDetails(String email) {
        List<Employee> employee=employeeRepository.getEmployeeDetails(email);
        employeeProfileView.displayAllEmployees(employee);
        return !employee.isEmpty();
    }
    public boolean getEmployeeDetails(long id) {
        List<Employee> employee=employeeRepository.getEmployeeDetails(id);
        employeeProfileView.displayAllEmployees(employee);
        return !employee.isEmpty();
    }

    public boolean getEmployeeStatus(String email) {
        Employee employee = employeeRepository.getEmployeeStatus(email);
        if (employee == null) {
            System.out.println("No employee found with email: " + email);
            return false;
        }
        if (employeeProfileView.changeEmployeeStatus(employee)) {
            if(employee.getStatus().toString().equals("ACTIVE")){
                employee.setStatus(Employee.Status.INACTIVE);
            }
            else if(employee.getStatus().toString().equals("INACTIVE")){
                employee.setStatus(Employee.Status.ACTIVE);
            }
           return employeeRepository.changeEmployeeStatus(employee);
        }
        return false;
    }

    public boolean deleteEmployee(String email) {
        return employeeRepository.deleteEmployee(email);
    }
}

package com.ganesh.cooliez.features.Manager;

import com.ganesh.cooliez.data.dto.Employee;
import com.ganesh.cooliez.data.dto.Task;
import com.ganesh.cooliez.data.dto.repository.EmployeeRepository;
import com.ganesh.cooliez.util.ParseHelper;

public class ManagerFeaturesModel {
    private ManagerFeaturesView managerFeaturesView;
    private EmployeeRepository employeeRepository;

    public ManagerFeaturesModel(ManagerFeaturesView managerFeaturesView) {
        this.managerFeaturesView = managerFeaturesView;
        this.employeeRepository = EmployeeRepository.getInstance();
    }

    public boolean getEmployeeStatus(String email) {
        Employee employee = employeeRepository.getEmployeeStatus(email);
        if (employee == null) {
            System.out.println("No employee found with email: " + email);
            return false;
        }
        if (managerFeaturesView.changeEmployeeStatus(employee)) {
            if (employee.getStatus().toString().equals("ACTIVE")) {
                employee.setStatus(Employee.Status.INACTIVE);
            } else if (employee.getStatus().toString().equals("INACTIVE")) {
                employee.setStatus(Employee.Status.ACTIVE);
            }
            return employeeRepository.changeEmployeeStatus(employee);
        }
        return false;
    }

    public boolean deleteEmployee(String email) {
        return employeeRepository.deleteEmployee(email);
    }

    public boolean getEmployeeDetails(String email) {
        return managerFeaturesView.showEmployeeDetails(email);
    }
}

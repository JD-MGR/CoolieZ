package com.ganesh.cooliez.features.signup;

import com.ganesh.cooliez.data.dto.Employee;
import com.ganesh.cooliez.data.dto.Task;
import com.ganesh.cooliez.data.dto.repository.EmployeeRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpModel {
	private SignUpView signUpView;

	private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern PATTERN = Pattern.compile(EMAIL_REGEX);

	SignUpModel(SignUpView signUpView){
		this.signUpView = signUpView;
	}

	public boolean signUp(String name, String email, String password, long dob, Employee.Role role, Employee.Status status) {
		Employee employee = new Employee(name, email, password,dob, role,status);
		return EmployeeRepository.getInstance().saveEmployee(employee);
	}

	boolean validateName(String name){
		if(name == null || name.trim().length() < 3){
			signUpView.display("Name cannot be less than 3 letters");
			return false;
		}
		if(name.matches(".*\\d.*")){
			signUpView.display("Name cannot contain Numbers");
			return false;
		}
		return true;
	}

	boolean validateEmail(String email){
		if(email == null || email.length() < 6){
			signUpView.display("Email cannot be of less than 6 characters");
			return false;
		}
		Matcher matcher = PATTERN.matcher(email);
		if(!matcher.matches()){
			signUpView.display("Email pattern doesn't match !");
			return false;
		}
		return true;
	}
	boolean validatePassword(String password) {
		if(password==null||password.length()<8){
			signUpView.display("Password cannot be less than 8 characters !");
			return false;
		}
		return true;
	}

	long parseDOB(String dobStr){
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate date = LocalDate.parse(dobStr, formatter);
			return date.atStartOfDay(ZoneId.systemDefault())
					   .toInstant()
					   .toEpochMilli();
		} catch (Exception e) {
			signUpView.display("Invalid date format. Please use dd-mm-yyyy");
			return -1;
		}
	}

	boolean validateDOB(long dob){
		long now = System.currentTimeMillis();
		if(dob <= 0) return false;
		if(dob >= now){
			signUpView.display("Date of birth must be in the past");
			return false;
		}
		return true;
	}

	Employee.Role assignRole(int input){
		if(input == 1) return Employee.Role.MANAGER;
		else if(input == 2) return Employee.Role.EMPLOYEE;
		else {
			signUpView.display("Invalid role selected");
			return null;
		}
	}
}

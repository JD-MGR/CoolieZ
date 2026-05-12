package com.ganesh.cooliez.features.signin;

import com.ganesh.cooliez.data.dto.Employee;
import com.ganesh.cooliez.data.dto.repository.EmployeeRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignInModel {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern PATTERN = Pattern.compile(EMAIL_REGEX);
    private SignInView signInView;
    public SignInModel(SignInView signInView){
        this.signInView=signInView;
    }

    public boolean validateEmail(String email) {
        if(email == null || email.length() < 6){
            signInView.display("Email cannot be of less than 6 characters");
            return false;
        }
        Matcher matcher = PATTERN.matcher(email);
        if(!matcher.matches()){
            signInView.display("Email pattern doesn't match !");
            return false;
        }
        return true;
    }

    public boolean validatePassword(String password) {
        if(password==null||password.length()<8){
            signInView.display("Password cannot be less than 8 letters !");
            return false;
        }
        return true;
    }

    public boolean isUserAllowed(String userEmail, String userPassword) {
        return EmployeeRepository.getInstance().checkEmployee(userEmail,userPassword);
    }

    public Employee.Role getRole(String userEmail) {
        return EmployeeRepository.getInstance().getRole(userEmail);
    }
}

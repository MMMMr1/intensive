package com.hospital.core.exception.employee;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException() {
    }
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}

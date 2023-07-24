package com.hospital.core.exception.employee;

public class ValidationEmployeeException extends IllegalArgumentException {
    public ValidationEmployeeException() {
    }
    public ValidationEmployeeException(String message) {
        super(message);
    }
}

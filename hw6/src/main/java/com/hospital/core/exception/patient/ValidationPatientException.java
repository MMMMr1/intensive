package com.hospital.core.exception.patient;

public class ValidationPatientException extends IllegalArgumentException {
    public ValidationPatientException() {
    }
    public ValidationPatientException(String message) {
        super(message);
    }
}

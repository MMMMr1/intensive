package com.hospital.core.exception.patient;

public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException() {
    }
    public PatientNotFoundException(String message) {
        super(message);
    }
}

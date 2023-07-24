package com.hospital.core.exception.medical_history;

public class MedicalHistoryNotFoundException extends RuntimeException {
    public MedicalHistoryNotFoundException() {
    }
    public MedicalHistoryNotFoundException(String message) {
        super(message);
    }
}

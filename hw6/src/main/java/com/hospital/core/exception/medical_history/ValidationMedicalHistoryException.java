package com.hospital.core.exception.medical_history;

public class ValidationMedicalHistoryException extends IllegalArgumentException {
    public ValidationMedicalHistoryException() {
    }
    public ValidationMedicalHistoryException(String message) {
        super(message);
    }
}

package com.hospital.web.handler;

import com.hospital.core.dto.error.ExceptionErrorDTO;
import com.hospital.core.dto.error.ExceptionListDTO;
import com.hospital.core.dto.error.ExceptionStructuredDTO;
import com.hospital.core.exception.InvalidVersionException;
import com.hospital.core.exception.employee.EmployeeNotFoundException;
import com.hospital.core.exception.employee.ValidationEmployeeException;
import com.hospital.core.exception.medical_history.MedicalHistoryNotFoundException;
import com.hospital.core.exception.medical_history.ValidationMedicalHistoryException;
import com.hospital.core.exception.patient.PatientNotFoundException;
import com.hospital.core.exception.patient.ValidationPatientException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice(basePackages = "com.hospital.web.controllers")
public class ExceptionGlobal  {

    //400 @Validated
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionListDTO> onMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        List<ExceptionStructuredDTO> error = e.getBindingResult().getFieldErrors().stream()
                .map(s -> new ExceptionStructuredDTO(s.getField(), s.getDefaultMessage()))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionListDTO(error));
    }
    //    400
    @ExceptionHandler(value = {ValidationEmployeeException.class, ValidationMedicalHistoryException.class, ValidationPatientException.class})
    public ResponseEntity<List<ExceptionErrorDTO>>  ArgumentNotValidException( RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(List.of(new ExceptionErrorDTO(e.getMessage())));
    }

    @ExceptionHandler(value = {EmployeeNotFoundException.class, MedicalHistoryNotFoundException.class, PatientNotFoundException.class})
    public ResponseEntity<List<ExceptionErrorDTO>>  ArgumentUserNotFoundException(
            RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(List.of(new ExceptionErrorDTO(e.getMessage())));
    }
    //    409
    @ExceptionHandler(value = {InvalidVersionException.class})
    public ResponseEntity<List<ExceptionErrorDTO>> ArgumentInvalidVersionException(
            RuntimeException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(List.of(new ExceptionErrorDTO(e.getMessage())));
    }

    //    500
    @ExceptionHandler()
    public ResponseEntity<List<ExceptionErrorDTO>> onHttpMessageNotReadableException(
            HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(List.of(new ExceptionErrorDTO(e.getMessage())));
    }
    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class,
            MethodArgumentConversionNotSupportedException.class})
    public ResponseEntity<List<ExceptionErrorDTO>> onArgumentTypeMismatchException(
            HttpMessageNotReadableException e ) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(List.of(new ExceptionErrorDTO(e.getMessage())));
    }
    @ExceptionHandler
    public ResponseEntity<List<ExceptionErrorDTO>> handler(Throwable e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(List.of(new ExceptionErrorDTO(e.getMessage())));
    }
}
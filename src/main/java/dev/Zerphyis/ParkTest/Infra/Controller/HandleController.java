package dev.Zerphyis.ParkTest.Infra.Controller;

import dev.Zerphyis.ParkTest.Infra.Exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class HandleController {

    private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String error, String message) {
        ErrorResponse body = new ErrorResponse(
                Instant.now(),
                status.value(),
                error,
                message
        );
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(DuplicatePlateException.class)
    public ResponseEntity<ErrorResponse> handleDuplicatePlate(DuplicatePlateException ex) {
        return buildResponse(HttpStatus.CONFLICT, "Duplicate Plate", ex.getMessage());
    }

    @ExceptionHandler(ErrorDuplicateEntryPlate.class)
    public ResponseEntity<ErrorResponse> handleErrorDuplicateEntryPlate(ErrorDuplicateEntryPlate ex) {
        return buildResponse(HttpStatus.CONFLICT, "Plate already in entry list", ex.getMessage());
    }

    @ExceptionHandler(FileNotGenerateException.class)
    public ResponseEntity<ErrorResponse> handleFileNotGenerate(FileNotGenerateException ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "File generation error", ex.getMessage());
    }

    @ExceptionHandler(PlateExistOtherList.class)
    public ResponseEntity<ErrorResponse> handlePlateExistOtherList(PlateExistOtherList ex) {
        return buildResponse(HttpStatus.CONFLICT, "Plate exists in another list", ex.getMessage());
    }

    @ExceptionHandler(VehiclePlateNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleVehiclePlateNotFound(VehiclePlateNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, "Vehicle plate not found", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error", ex.getMessage());
    }
}

package dev.Zerphyis.ParkTest.Infra.Exceptions;

public class ErrorDuplicateEntryPlate extends RuntimeException {
    public ErrorDuplicateEntryPlate(String message) {
        super(message);
    }
}

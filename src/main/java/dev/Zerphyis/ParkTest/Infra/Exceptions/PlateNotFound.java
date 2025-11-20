package dev.Zerphyis.ParkTest.Infra.Exceptions;

public class PlateNotFound extends RuntimeException {
    public PlateNotFound(String message) {
        super(message);
    }
}

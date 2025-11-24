package dev.Zerphyis.ParkTest.Infra.Exceptions;

public class DuplicatePlateException extends RuntimeException {
    public DuplicatePlateException(String message) {
        super(message);
    }
}

package dev.Zerphyis.ParkTest.Infra.Exceptions;

public class FileNotGenerateException extends RuntimeException {
    public FileNotGenerateException(String message) {
        super(message);
    }

    public FileNotGenerateException(String message, Throwable cause) {
        super(message, cause);
    }
}

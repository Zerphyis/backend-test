package dev.Zerphyis.ParkTest.Infra.Exceptions;

public class VehiclePlateNotFoundException extends RuntimeException {
    public VehiclePlateNotFoundException(String message) {
        super(message);
    }
}

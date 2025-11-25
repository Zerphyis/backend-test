package dev.Zerphyis.ParkTest.Infra.Exceptions;

public class PlateExistOtherList extends RuntimeException {
    public PlateExistOtherList(String message) {
        super(message);
    }
}

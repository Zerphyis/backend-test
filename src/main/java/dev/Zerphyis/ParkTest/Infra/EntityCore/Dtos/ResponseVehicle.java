package dev.Zerphyis.ParkTest.Infra.EntityCore.Dtos;

import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.Enums.TypesVehicles;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record ResponseVehicle(
        String plate,
        TypesVehicles type,
        String entryTime,
        String exitTime,
        String accumulatedTime,
        BigDecimal pending
) {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static ResponseVehicle fromDomain(VehicleDomain domain) {

        String formattedDuration = formatDuration(domain.getAccumulatedTime());

        return new ResponseVehicle(
                domain.getPlate(),
                domain.getType(),
                formatTime(domain.getEntry(), TIME_FORMATTER),
                formatTime(domain.getExit(), TIME_FORMATTER),
                formattedDuration,
                domain.getPending()
        );
    }


    private static String formatTime(LocalDateTime time, DateTimeFormatter formatter) {
        return time != null ? time.format(formatter) : null;
    }


    private static String formatDuration(Duration duration) {
        if (duration == null || duration.isZero()) {
            return "0h 0m";
        }
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        return String.format("%dh %dm", hours, minutes);
    }
}
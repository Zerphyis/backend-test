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

        return new ResponseVehicle(
                domain.getPlate(),
                domain.getType(),
                formatTime(domain.getEntry()),
                formatTime(domain.getExit()),
                formatDuration(domain.getAccumulatedTime()),
                domain.getPending()
        );
    }

    private static String formatTime(LocalDateTime time) {
        return time != null ? time.format(TIME_FORMATTER) : null;
    }


    private static String formatDuration(Duration duration) {
        if (duration == null) {
            return "00:00:00";
        }

        long totalSeconds = duration.getSeconds();

        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}

package dev.Zerphyis.ParkTest.Domain.Entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VehicleDomain {

    private final String plate;
    private final LocalDateTime entry;

    private LocalDateTime exit;
    private BigDecimal pending;

    public VehicleDomain(String plate) {
        this.plate = plate.toUpperCase();
        this.entry = LocalDateTime.now();
        this.pending = BigDecimal.ZERO;
    }

    public String getPlate() {
        return plate;
    }

    public LocalDateTime getEntry() {
        return entry;
    }

    public LocalDateTime getExit() {
        return exit;
    }

    public BigDecimal getPending() {
        return pending;
    }

    public void registerExit() {
        this.exit = LocalDateTime.now();
    }

    public void updatePending(BigDecimal value) {
        this.pending = value;
    }
}

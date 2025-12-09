package dev.Zerphyis.ParkTest.Domain.Entity;

import dev.Zerphyis.ParkTest.Domain.Enums.TypesVehicles;
import dev.Zerphyis.ParkTest.Infra.Exceptions.VehiclePlateNotFoundException;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class VehicleDomain {

    private Long id;
    private final String plate;
    private TypesVehicles type;

    private LocalDateTime entry;
    private LocalDateTime exit;

    private Duration accumulatedTime;
    private BigDecimal pending;

    public VehicleDomain(String plate, TypesVehicles type) {
        this.id = null;
        this.plate = Objects.requireNonNull(plate).toUpperCase();
        this.type = Objects.requireNonNull(type);
        this.entry = null;
        this.exit = null;
        this.accumulatedTime = Duration.ZERO;
        this.pending = BigDecimal.ZERO;
    }

    public VehicleDomain(Long id,
                         String plate,
                         TypesVehicles type,
                         LocalDateTime entry,
                         LocalDateTime exit,
                         Duration accumulatedTime,
                         BigDecimal pending) {

        this.id = id;
        this.plate = Objects.requireNonNull(plate).toUpperCase();
        this.type = Objects.requireNonNull(type);
        this.entry = entry;
        this.exit = exit;
        this.accumulatedTime = accumulatedTime == null ? Duration.ZERO : accumulatedTime;
        this.pending = pending == null ? BigDecimal.ZERO : pending;
    }


    public void assertNotInside() {
        if (this.entry != null && this.exit == null) {
            throw new VehiclePlateNotFoundException("Veículo já tem entrada registrada.");
        }
    }

    public void assertHasEntry() {
        if (this.entry == null) {
            throw new VehiclePlateNotFoundException("Não existe entrada registrada.");
        }
    }


    public void markEntryNow() {
        this.entry = LocalDateTime.now();
        this.exit = null;
    }

    public void markExitNow() {
        if (this.entry == null) {
            throw new VehiclePlateNotFoundException("Não existe entrada registrada.");
        }

        this.exit = LocalDateTime.now();

        Duration stay = Duration.between(this.entry, this.exit);

        this.addAccumulated(stay);

        BigDecimal cost = calculateCost(stay);

        this.pending = this.pending.add(cost);
    }

    public Duration currentVisitDuration() {
        if (this.entry == null) return Duration.ZERO;
        LocalDateTime stop = (this.exit != null) ? this.exit : LocalDateTime.now();
        return Duration.between(this.entry, stop);
    }


    public void addAccumulated(Duration d) {
        if (d != null) {
            this.accumulatedTime = this.accumulatedTime.plus(d);
        }
    }

    public void resetAccumulatedTime() {
        this.accumulatedTime = Duration.ZERO;
    }


    private BigDecimal calculateCost(Duration stay) {
        if (stay == null || stay.isZero() || stay.isNegative()) {
            return BigDecimal.ZERO;
        }

        long minutes = stay.toMinutes();

        if (minutes <= 0) minutes = 1;

        BigDecimal rate = switch (this.type) {
            case RESIDENTS -> BigDecimal.ZERO;
            case OFFICIAL -> BigDecimal.ZERO;
            case NORESIDENTS -> new BigDecimal("0.50");
        };

        return rate.multiply(BigDecimal.valueOf(minutes));
    }


    public boolean isResident() {
        return this.type == TypesVehicles.RESIDENTS;
    }

    public boolean isOfficial() {
        return this.type == TypesVehicles.OFFICIAL;
    }

    public void increaseParkingTimeForMonthStart(Duration bonusDuration) {
        if (this.isResident()) {
            this.addAccumulated(bonusDuration);
        }
    }

    public Long getId() { return id; }
    public String getPlate() { return plate; }
    public LocalDateTime getEntry() { return entry; }
    public LocalDateTime getExit() { return exit; }
    public Duration getAccumulatedTime() { return accumulatedTime; }
    public BigDecimal getPending() { return pending; }
    public TypesVehicles getType() { return type; }

    public void setType(TypesVehicles t) { this.type = t; }

    public void setPending(BigDecimal pending) {
        this.pending = pending == null ? BigDecimal.ZERO : pending;
    }
}

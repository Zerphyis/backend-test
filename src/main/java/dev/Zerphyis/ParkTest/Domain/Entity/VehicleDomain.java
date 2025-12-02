package dev.Zerphyis.ParkTest.Domain.Entity;

import dev.Zerphyis.ParkTest.Domain.Enums.TypesVehicles;
import dev.Zerphyis.ParkTest.Infra.Exceptions.VehiclePlateNotFoundException;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class VehicleDomain {

    private Long id; // novo campo
    private final String plate;
    private TypesVehicles type;

    private LocalDateTime entry;
    private LocalDateTime exit;

    private Duration accumulatedTime;
    private BigDecimal pending;

    // construtor sem ID (para criação de novos veículos)
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
        this.exit = LocalDateTime.now();
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

    public void clearEntryExit() {
        this.entry = null;
        this.exit = null;
    }

    public void setPending(BigDecimal pending) {
        this.pending = pending == null ? BigDecimal.ZERO : pending;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPlate() { return plate; }
    public LocalDateTime getEntry() { return entry; }
    public LocalDateTime getExit() { return exit; }
    public Duration getAccumulatedTime() { return accumulatedTime; }
    public BigDecimal getPending() { return pending; }
    public TypesVehicles getType() { return type; }
    public void setType(TypesVehicles t) { this.type = t; }
}

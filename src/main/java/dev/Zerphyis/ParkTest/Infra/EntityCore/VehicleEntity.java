package dev.Zerphyis.ParkTest.Infra.EntityCore;

import dev.Zerphyis.ParkTest.Domain.Enums.TypesVehicles;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "vehicles")
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String plate;

    private LocalDateTime entry;

    @Column(name = "exit_time")
    private LocalDateTime exit;

    private Long accumulatedTimeSeconds;

    private BigDecimal pending;

    @Enumerated(EnumType.STRING)
    private TypesVehicles type;

    public Duration getAccumulatedTime() {
        return accumulatedTimeSeconds == null ? Duration.ZERO : Duration.ofSeconds(accumulatedTimeSeconds);
    }

    public void setAccumulatedTime(Duration d) {
        this.accumulatedTimeSeconds = d == null ? 0L : d.getSeconds();
    }

    public long getAccumulatedTimeSecondsSafe() {
        return accumulatedTimeSeconds == null ? 0L : accumulatedTimeSeconds;
    }

    public void setAccumulatedTimeSeconds(Long seconds) {
        this.accumulatedTimeSeconds = seconds == null ? 0L : seconds;
    }

    public BigDecimal getPendingSafe() {
        return pending == null ? BigDecimal.ZERO : pending;
    }

    public void setPendingSafe(BigDecimal p) {
        this.pending = p == null ? BigDecimal.ZERO : p;
    }

    public void normalizePlate() {
        if (this.plate != null)
            this.plate = this.plate.toUpperCase();
    }
}

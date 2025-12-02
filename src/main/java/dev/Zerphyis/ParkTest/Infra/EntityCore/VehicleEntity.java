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
@Table(
        name = "vehicles",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "plate")
        }
)
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String plate;

    private LocalDateTime entry;

    @Column(name = "exit_time")
    private LocalDateTime exit;

    private Long accumulatedTimeSeconds;

    private BigDecimal pending;

    @Enumerated(EnumType.STRING)
    private TypesVehicles type;

    @PrePersist
    @PreUpdate
    public void normalizePlate() {
        if (plate != null) {
            plate = plate.toUpperCase();
        }
    }

    public Duration getAccumulatedTime() {
        return accumulatedTimeSeconds == null ? Duration.ZERO : Duration.ofSeconds(accumulatedTimeSeconds);
    }

    public void setAccumulatedTime(Duration d) {
        this.accumulatedTimeSeconds = (d == null ? 0 : d.getSeconds());
    }

    public BigDecimal getPendingSafe() {
        return pending == null ? BigDecimal.ZERO : pending;
    }
}
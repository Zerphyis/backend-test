package dev.Zerphyis.ParkTest.Infra.EntityCore;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class VehicleEntity {

    @NotBlank
    @Pattern(
            regexp = "^[A-Z]{3}[0-9][A-Z][0-9]{2}$|^[A-Z]{3}-?[0-9]{4}$",
            message = "Placa inválida. Use padrão antigo (AAA-1234) ou Mercosul (AAA1A23)."
    )
    private String plate;

    private LocalDateTime entry;
    private LocalDateTime exit;
    private BigDecimal pending;

    public VehicleEntity(String plate) {
        this.plate = plate.toUpperCase();
        this.entry = LocalDateTime.now();
        this.pending = BigDecimal.ZERO;
    }
}

    package dev.Zerphyis.ParkTest.Infra.EntityCore;

    import dev.Zerphyis.ParkTest.Infra.EntityCore.TypeVehicle.TypesVehicles;
    import jakarta.persistence.*;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.Pattern;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.math.BigDecimal;
    import java.time.LocalDateTime;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public class VehicleEntity {
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        private  Long id;

        @NotBlank
        @Pattern(
                regexp = "^[A-Z]{3}[0-9][A-Z][0-9]{2}$|^[A-Z]{3}-?[0-9]{4}$",
                message = "Placa inválida. Use padrão antigo (AAA-1234) ou Mercosul (AAA1A23)."
        )
        private String plate;

        private LocalDateTime entry;
        private LocalDateTime exit;
        private BigDecimal pending;

        @Enumerated(EnumType.STRING)
        private TypesVehicles typesVehicles;

        public VehicleEntity(String plate) {
            this.plate = plate.toUpperCase();
            this.entry = LocalDateTime.now();
            this.pending = BigDecimal.ZERO;

        }
    }

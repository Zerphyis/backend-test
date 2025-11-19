package dev.Zerphyis.ParkTest.Infra.EntityCore.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PlateVehicle(

        @NotBlank
        @Pattern(
                regexp = "^[A-Z]{3}[0-9][A-Z][0-9]{2}$|^[A-Z]{3}-?[0-9]{4}$",
                message = "Placa inválida. Use padrão antigo (AAA-1234) ou Mercosul (AAA1A23)."
        )
        String plate

){}

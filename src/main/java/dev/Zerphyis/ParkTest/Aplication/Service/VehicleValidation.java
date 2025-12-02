package dev.Zerphyis.ParkTest.Aplication.Service;

import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.Enums.TypesVehicles;
import dev.Zerphyis.ParkTest.Domain.repositorys.VehicleRepository;
import dev.Zerphyis.ParkTest.Infra.Exceptions.PlateExistOtherList;

import java.util.Optional;

public class VehicleValidation {

    private final VehicleRepository repository;

    public VehicleValidation(VehicleRepository repository) {
        this.repository = repository;
    }

    /**
     * Regras finais:
     *
     * - Se NÃO existir → permitido.
     * - Se existir como NORESIDENTS → permitido (pois é o valor padrão).
     * - Se existir com o MESMO tipo → permitido.
     * - Se existir com OUTRO tipo → lançar PlateExistOtherList.
     */
    public void assertPlateNotExists(String plate, TypesVehicles newType) {
        String normalized = plate.toUpperCase();

        Optional<VehicleDomain> existing = repository.findByPlate(normalized);

        if (existing.isEmpty()) {
            return; // placa não existe: permitido
        }

        VehicleDomain vehicle = existing.get();
        TypesVehicles currentType = vehicle.getType();

        // Se o tipo atual é NORESIDENTS → pode migrar para qualquer tipo
        if (currentType.equals(TypesVehicles.NORESIDENTS)) {
            return;
        }

        // Se é o mesmo tipo → permitido
        if (currentType.equals(newType)) {
            return;
        }

        // Caso exista com outro tipo diferente → proibido
        throw new PlateExistOtherList(
                "A placa '" + normalized + "' já está cadastrada como " + currentType + "."
        );
    }
}

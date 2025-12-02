package dev.Zerphyis.ParkTest.Aplication.UseCases;

import dev.Zerphyis.ParkTest.Domain.Entity.VehicleDomain;
import dev.Zerphyis.ParkTest.Domain.Enums.TypesVehicles;
import dev.Zerphyis.ParkTest.Domain.Enums.CurrencyType;
import dev.Zerphyis.ParkTest.Domain.Interfaces.ResidentPaymentRepository;
import dev.Zerphyis.ParkTest.Domain.repositorys.VehicleRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GenerateResidentPaymentsReport {

    private final VehicleRepository vehicleRepository;
    private final ResidentPaymentRepository residentPaymentRepository;

    public GenerateResidentPaymentsReport(
            VehicleRepository vehicleRepository,
            ResidentPaymentRepository residentPaymentRepository
    ) {
        this.vehicleRepository = vehicleRepository;
        this.residentPaymentRepository = residentPaymentRepository;
    }

    public void execute(String filename, CurrencyType currency) {

        List<VehicleDomain> residents = vehicleRepository.findAll()
                .stream()
                .filter(v -> v.getType() == TypesVehicles.RESIDENTS)
                .collect(Collectors.toList());

        StringBuilder report = new StringBuilder();
        report.append("Relatório de Pagamentos dos Residentes\n");
        report.append("Moeda utilizada: ").append(currency)
                .append(" (").append(currency.symbol()).append(")\n\n");

        for (VehicleDomain resident : residents) {
            report.append("Placa: ").append(resident.getPlate()).append("\n");
            report.append("Minutos acumulados: ").append(resident.getAccumulatedTime().toMinutes()).append("\n");
            report.append("Valor devido: ").append(currency.symbol()).append(resident.getPending()).append("\n");
            report.append("---------------------------\n");
        }

        residentPaymentRepository.saveMonthlyReport(filename, report.toString());
    }
}

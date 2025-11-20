package dev.Zerphyis.ParkTest.Infra.EntityCore.InterfaceImpls;

import dev.Zerphyis.ParkTest.Domain.Interfaces.ResidentPaymentRepository;
import dev.Zerphyis.ParkTest.Infra.Exceptions.FileNotGenerateException;

import java.io.FileWriter;
import java.io.IOException;

public class ResidentPaymentRepositoryFileImpl implements ResidentPaymentRepository {
    @Override
    public void saveMonthlyReport(String fileName, String content) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
        } catch (IOException e) {
            throw new FileNotGenerateException("Erro ao gerar relatório de residentes", e);
        }
    }
}

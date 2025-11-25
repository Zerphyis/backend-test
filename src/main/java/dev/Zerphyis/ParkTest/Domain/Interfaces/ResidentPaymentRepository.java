package dev.Zerphyis.ParkTest.Domain.Interfaces;

public interface ResidentPaymentRepository {
    void saveMonthlyReport(String fileName, String content);
}

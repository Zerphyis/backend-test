package dev.Zerphyis.ParkTest.Domain.Enums;

public enum CurrencyType {
    BRL("R$"),
    USD("$"),
    EUR("€");

    private final String symbol;

    CurrencyType(String symbol) { this.symbol = symbol; }

    public String symbol() { return symbol; }
}

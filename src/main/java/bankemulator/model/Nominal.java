package bankemulator.model;

import java.util.Comparator;

public enum Nominal {
    FIVE(5),
    TEN(10),
    FIFTY(50),
    HUNDRED(100),
    FIVE_HUNDRED(500),
    ONE_THOUSAND(1000),
    TWO_THOUSAND(2000),
    FIVE_THOUSAND(5000);

    private final int nominal;

    Nominal(int nominal) {
        this.nominal = nominal;
    }

    public int getNominal() {
        return this.nominal;
    }

    public static Comparator<Nominal> reverseComparator() {
        return Comparator.comparingInt(Nominal::getNominal).reversed();
    }
}
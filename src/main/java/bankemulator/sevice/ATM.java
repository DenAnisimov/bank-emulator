package bankemulator.sevice;

import bankemulator.model.Nominal;

public interface ATM {
    void put(Nominal nominal, int count);
    Nominal[] get(int withdrawal);
}

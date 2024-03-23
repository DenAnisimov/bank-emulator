package bankemulator.sevice;

import bankemulator.exception.ATMIsEmptyException;
import bankemulator.exception.LimitExceedingException;
import bankemulator.exception.MoneyInATMIsLessException;
import bankemulator.model.Nominal;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class ATMImpl implements ATM {
    private final int WITHDRAWAL_LIMIT = 700_000;
    private final int NOMINAL_LIMIT = 200;
    private TreeMap<Nominal, Integer> money;

    public ATMImpl() {
        money = new TreeMap<>(Nominal.reverseComparator());
    }

    public ATMImpl(TreeMap<Nominal, Integer> money) {
        this.money = new TreeMap<>(Nominal.reverseComparator());
        this.money.putAll(money);
    }

    @Override
    public void put(Nominal nominal, int count) {
        money.put(nominal, count);
    }

    @Override
    public Nominal[] get(int withdrawal) {
        if (money.isEmpty()) {
            throw new ATMIsEmptyException();
        }

        if (withdrawal > WITHDRAWAL_LIMIT) {
            throw new LimitExceedingException();
        }

        int moneyInATM = getMoneyInATM();

        if (withdrawal > moneyInATM) {
            throw new MoneyInATMIsLessException();
        }

        Nominal[] withdrawalMoney = new Nominal[200];


        int i =0;
        for (Map.Entry<Nominal, Integer> nominal : money.entrySet()) {
            if (withdrawal <= 0) {
                break;
            }

            int nominalValue = nominal.getKey().getNominal();
            int nominalCount = nominal.getValue();

            for (int j = 0; j <= nominalCount; j++) {
                if (withdrawal >= nominalValue && withdrawal - nominalValue >= 0
                        && nominalCount > 0) {
                    withdrawal -= nominalValue;
                    withdrawalMoney[i] = nominal.getKey();

                    money.put(nominal.getKey(), nominalCount - 1);

                    i++;
                } else {
                    break;
                }
            }
        }

        int length = findLastIndexOfNominals(withdrawalMoney);

        withdrawalMoney = Arrays.copyOf(withdrawalMoney, length);

        return withdrawalMoney;
    }

    private int findLastIndexOfNominals(Nominal[] nominals) {
        int lastIndex = 0;

        for (int i = 0; i < nominals.length; i++) {
            if (nominals[i] == null) {
                lastIndex = i;
                break;
            }
        }

        return lastIndex;
    }

    private int getMoneyInATM() {
        int moneyInATM = 0;

        for (Map.Entry<Nominal, Integer> nominal : money.entrySet()) {
            moneyInATM += nominal.getKey().getNominal() * nominal.getValue();
        }

        return moneyInATM;
    }
}

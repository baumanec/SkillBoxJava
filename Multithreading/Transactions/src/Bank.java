import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class Bank {

    private static Map<String, Account> accounts;
    private final List<String> blackList;
    private final Random random = new Random();
    private static final long FRAUD_LIMIT = 50000;

    public Bank(Map<String, Account> accounts, List<String> blackList) {
        this.accounts = accounts;
        this.blackList = blackList;
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {

        Account sender = accounts.get(fromAccountNum);
        Account recipient = accounts.get(toAccountNum);
        System.out.println("Попытка перевести средства\nС аккаунта: " + fromAccountNum
                + "\nНа аккаунт: " + toAccountNum
                + "\nВ размере: " + amount);
        if (amount <= 0) {
            System.out.println("Недопустимая сумма перевода!");
            return;
        }
        if (!blackList.contains(fromAccountNum) & !blackList.contains(toAccountNum)) {
            if (amount > getBalance(fromAccountNum).get()) {
                System.out.println("Операция невозможна. Недостаточно средств на счёте :(");
                return;
            }
            if ((amount > FRAUD_LIMIT) & isFraud(fromAccountNum, toAccountNum, amount)) {
                System.out.println("Зафиксирован ФРОД!\n"
                        + "Аккаунты "
                        + fromAccountNum
                        + ", "
                        + toAccountNum
                        + " перемещены в чёрный список!");
                blackList.add(fromAccountNum);
                blackList.add(toAccountNum);
            } else {
                recipient.getMoney().addAndGet(amount);
                sender.getMoney().addAndGet(-amount);
                System.out.println("Операция выполнена успешно!"
                        + "\nИмя отправителя: " + fromAccountNum
                        + "\nБаланс отправителя: " + getBalance(fromAccountNum)
                        + "\nБаланс получателя: " + getBalance(toAccountNum)
                        + "\nИмя получателя: " + toAccountNum);
            }
        } else {
            System.out.println("Операция невозможна. Один или оба аккаунта заблокированы.");
        }

    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public static AtomicLong getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }

    public long getSumAllAccounts() {

        long sumAllAccounts = 0;
        for (String accNumber : accounts.keySet()) {
            sumAllAccounts += accounts.get(accNumber).getMoney().get();
        }

        return sumAllAccounts;
    }

}

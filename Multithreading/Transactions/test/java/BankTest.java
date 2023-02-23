import junit.framework.TestCase;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class BankTest extends TestCase {
    Map<String, Account> accounts = new TreeMap<>();
    List<String> blackList = new ArrayList<>();
    Bank bank;

    @Override
    protected void setUp() {
        bank = new Bank(accounts, blackList);
        for (int i = 1; i <= 10; i++) {
            Account account = new Account();
            account.setAccNumber(String.valueOf(i));
            account.setMoney(new AtomicLong(i * 10000));
            accounts.put(account.getAccNumber(), account);
        }
    }

    public void testGetBalance() {
        AtomicLong actual = bank.getBalance(accounts.get(String.valueOf(1)).getAccNumber());
        AtomicLong expected = new AtomicLong(10000);
        assertEquals(expected.get(), actual.get());
    }

    public void testTransfer() throws Exception {
        bank.transfer(String.valueOf(1), String.valueOf(2), 1000);
        AtomicLong actual = bank.getBalance(accounts.get(String.valueOf(1)).getAccNumber());
        AtomicLong expected = new AtomicLong(9000);
        assertEquals(expected.get(), actual.get());
    }

    public void testTransferLessZero() throws Exception {
        bank.transfer(String.valueOf(1), String.valueOf(2), -1000);
        AtomicLong actual = bank.getBalance(accounts.get(String.valueOf(1)).getAccNumber());
        AtomicLong expected = new AtomicLong(10000);
        assertEquals(expected.get(), actual.get());
    }

    public void testTransferMoreThenAccountBalance() throws Exception {
        bank.transfer(String.valueOf(1), String.valueOf(2), 100000);
        AtomicLong actual = bank.getBalance(accounts.get(String.valueOf(1)).getAccNumber());
        AtomicLong expected = new AtomicLong(10000);
        assertEquals(expected.get(), actual.get());
    }

    public void testCallFraud() throws Exception {
        bank.transfer(String.valueOf(10), String.valueOf(2), 60000);
        String[] senderAndRecipient = {accounts.get(String.valueOf(10)).getAccNumber(), accounts.get(String.valueOf(2)).getAccNumber()};
        AtomicLong actual = bank.getBalance(accounts.get(String.valueOf(10)).getAccNumber());
        AtomicLong expected = new AtomicLong(100000);
        assertEquals(expected.get(), actual.get());
        boolean actualBlacklist = blackList.containsAll(new ArrayList<>(Arrays.asList(senderAndRecipient)));
        boolean expectedBlackList = true;
        assertEquals(expectedBlackList, actualBlacklist);
    }

    public void testSumAllAccountsWithFraud() throws Exception {
        long before = bank.getSumAllAccounts();

        bank.transfer(String.valueOf(1), String.valueOf(2), 1000);
        bank.transfer(String.valueOf(2), String.valueOf(1), 5000);
        bank.transfer(String.valueOf(3), String.valueOf(6), 10000);
        bank.transfer(String.valueOf(4), String.valueOf(6), 10000);
        bank.transfer(String.valueOf(4), String.valueOf(5), 20000);
        bank.transfer(String.valueOf(5), String.valueOf(2), 55000);
        bank.transfer(String.valueOf(6), String.valueOf(5), 60000);
        bank.transfer(String.valueOf(7), String.valueOf(6), 57000);
        bank.transfer(String.valueOf(8), String.valueOf(7), 58000);
        bank.transfer(String.valueOf(9), String.valueOf(2), 51000);
        bank.transfer(String.valueOf(10), String.valueOf(2), 100000);

        long after = bank.getSumAllAccounts();

        long actual = after - before;
        long expected = 0;
        assertEquals(expected, actual);

    }

    public void testSumAllAccountsWithoutFraud() throws Exception {
        long before = bank.getSumAllAccounts();

        bank.transfer(String.valueOf(1), String.valueOf(2), 1000);
        bank.transfer(String.valueOf(2), String.valueOf(1), 5000);
        bank.transfer(String.valueOf(3), String.valueOf(6), 10000);
        bank.transfer(String.valueOf(4), String.valueOf(6), 10000);
        bank.transfer(String.valueOf(4), String.valueOf(5), 20000);
        bank.transfer(String.valueOf(5), String.valueOf(2), 30000);
        bank.transfer(String.valueOf(6), String.valueOf(5), 40000);
        bank.transfer(String.valueOf(7), String.valueOf(6), 50000);
        bank.transfer(String.valueOf(8), String.valueOf(7), 38000);
        bank.transfer(String.valueOf(9), String.valueOf(2), 41000);
        bank.transfer(String.valueOf(10), String.valueOf(2), 48000);

        long after = bank.getSumAllAccounts();

        long actual = after - before;
        long expected = 0;
        assertEquals(expected, actual);

    }

}

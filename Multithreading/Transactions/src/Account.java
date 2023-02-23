import java.util.concurrent.atomic.AtomicLong;

public class Account {

    private AtomicLong money = new AtomicLong();
    private String accNumber;

    public AtomicLong getMoney() {
        return money;
    }

    public void setMoney(AtomicLong money) {
        this.money = money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }
}

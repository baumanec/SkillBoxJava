import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RedisStorage {
    private RedissonClient redisson;
    private RScoredSortedSet<String> users;
    private final static String KEY = "USERS";

    private double getTime() {
        return (double) new Date().getTime() / 1000;
    }

    void displayUsers() throws InterruptedException {
        Random random = new Random();
        while (true) {
            // В одном из 10 случаев случайный пользователь оплачивает услугу
            if (random.nextInt(10) == 0) {
                String premiumUser = String.valueOf(random.nextInt(20) + 1);
                payForSubscription(premiumUser);
            } else {
                String user = users.pollFirst();
                System.out.println("— На главной странице показываем пользователя " + user);
                users.add(getTime(), user);
            }
            TimeUnit.SECONDS.sleep(1);
        }
    }

    void payForSubscription(String user) {
        System.out.println("> Пользователь " + user + " оплатил платную услугу");
        System.out.println("— На главной странице показываем пользователя " + user);
        users.add(getTime(), user);
    }

    void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            System.out.println("Не удалось подключиться к Redis");
            System.out.println(Exc.getMessage());
        }
        // Объект для работы с ключами
        RKeys rKeys = redisson.getKeys();
        users = redisson.getScoredSortedSet(KEY);
        rKeys.delete(KEY);
    }

    void shutdown() {
        redisson.shutdown();
    }

    void logPageVisit() {
        for (int userId = 1; userId <= 20; userId++) {
            users.add(getTime(), String.valueOf(userId));
        }
    }
}
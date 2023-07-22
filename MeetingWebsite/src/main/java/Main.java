public class Main {
    public static void main(String[] args) throws InterruptedException {

        RedisStorage redisStorage = new RedisStorage();
        redisStorage.init();
        redisStorage.logPageVisit();
        redisStorage.displayUsers();

    }
}

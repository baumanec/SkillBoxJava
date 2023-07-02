import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Loader {

    private static final String FILE_PATH_FOR_THREAD1 = "res/numbers1.txt";
    private static final String FILE_PATH_FOR_THREAD2 = "res/numbers2.txt";
    private static final char[] LETTERS = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        CarNumberGenerator carNumberGenerator1 = new CarNumberGenerator(FILE_PATH_FOR_THREAD1, LETTERS, start);
        CarNumberGenerator carNumberGenerator2 = new CarNumberGenerator(FILE_PATH_FOR_THREAD2, LETTERS, start);
        ThreadPoolExecutor executor1 = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
        ThreadPoolExecutor executor2 = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
        executor1.submit(carNumberGenerator1::start);
        executor2.submit(carNumberGenerator2::start);
        executor1.close();
        executor2.close();
    }

}
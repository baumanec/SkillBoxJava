import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CarNumberGenerator extends Thread {

    private final String filePath;
    private final char[] letters;
    private final long start;

    public CarNumberGenerator(String filePath, char[] letters, long start) {
        this.filePath = filePath;
        this.letters = letters;
        this.start = start;
    }

    @Override
    public void run() {

        PrintWriter writer;
        try {
            writer = new PrintWriter(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (int regionCode = 1; regionCode < 100; regionCode++) {
            StringBuilder builder = new StringBuilder();
            for (int number = 1; number < 1000; number++) {
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {

                            builder.append(firstLetter)
                                    .append(padNumber(number, 3))
                                    .append(secondLetter)
                                    .append(thirdLetter)
                                    .append(padNumber(regionCode, 2))
                                    .append("\n");
                        }
                    }
                }
            }
            writer.write(builder.toString());
        }

        writer.flush();
        writer.close();
        System.out.println("Finished after start: " + (System.currentTimeMillis() - start) + " ms");

    }

    private static String padNumber(int number, int numberLength) {
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();

        for (int i = 0; i < padSize; i++) {
            numberStr = "0".concat(numberStr);
        }

        return numberStr;
    }

}

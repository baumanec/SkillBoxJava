import java.io.File;

public class Main {

    private static int newWidth = 300;
    private static int newHeight = 300;
    private static int availableProcessors = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {

        String srcFolder = "C:/Users/AlexArt/Desktop/src";
        String dstFolder = "C:/Users/AlexArt/Desktop/dst";
        String scalrDstFolder = "C:/Users/AlexArt/Desktop/scalr";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        try {
            if (files != null) {
                int part = files.length / availableProcessors;

                for (int i = 0; i < availableProcessors; i++) {
                    File[] files1 = new File[part];
                    System.arraycopy(files, part * i, files1, 0, files1.length);
                    ImageResizer resizer = new ImageResizer(files1, newWidth, dstFolder, start);
                    new Thread(resizer).start();
                }

                for (int i = 0; i < availableProcessors; i++) {
                    File[] files1 = new File[part];
                    System.arraycopy(files, part * i, files1, 0, files1.length);
                    ScalrResizer resizer = new ScalrResizer(files1, newWidth, newHeight, scalrDstFolder, start);
                    new Thread(resizer).start();
                }
            } else {
                System.out.println("Файлы отсутствуют!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

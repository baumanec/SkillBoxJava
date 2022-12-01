import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.File;

public class ScalrResizer implements Runnable {

    private File[] files;
    private int targetWidth;
    private int targetHeight;
    private String dstFolder;
    private long start;

    public ScalrResizer(File[] files, int targetWidth, int targetHeight, String dstFolder, long start) {
        this.files = files;
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;
        this.dstFolder = dstFolder;
        this.start = start;
    }

    @Override
    public void run() {
        try {
            for (File file : files) {
                BufferedImage src = ImageIO.read(file);
                if (src == null) {
                    continue;
                }

                BufferedImage dst = resize(src, targetWidth, targetHeight);

                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(dst, "jpg", newFile);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Finished after start: " + (System.currentTimeMillis() - start) + " ms");

    }

    private static BufferedImage resize(BufferedImage src, int targetWidth, int targetHeight)
            throws IllegalArgumentException, ImagingOpException {

        return Scalr.resize(src, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.AUTOMATIC, targetWidth, targetHeight);

    }
}

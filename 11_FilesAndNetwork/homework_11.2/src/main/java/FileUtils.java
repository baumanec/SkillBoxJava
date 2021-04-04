import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUtils {

    public static void copyFolder(String sourceDirectory, String destinationDirectory) {
        try {
            Path source = Paths.get(sourceDirectory);
            Path destination = Paths.get(destinationDirectory);
            Files.walk(source)
                .forEach(s -> copy(s, destination.resolve(source.relativize(s))));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static void copy(Path source, Path destination) {
        try {
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void copyDirectory(String sourceDirectory, String destinationDirectory) {
        try {
            File source = new File(sourceDirectory);
            File destination = new File(destinationDirectory);
            org.apache.commons.io.FileUtils.copyDirectory(source, destination);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

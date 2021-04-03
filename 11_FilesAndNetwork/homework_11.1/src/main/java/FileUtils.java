import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    public static long calculateFolderSize(String path) throws IOException {
        Path folder = Paths.get(path);
        return Files.walk(folder)
            .map(Path::toFile)
            .filter(File::isFile)
            .mapToLong(File::length)
            .sum();
    }
}

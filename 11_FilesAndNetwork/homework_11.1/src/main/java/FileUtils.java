import java.io.File;

public class FileUtils {

    public static long calculateFolderSize(String path) {
        long size = 0L;
        File folder = new File(path);
        File[] files = folder.listFiles();
        assert files != null;
        for (File file : files) {
            size += calculateFileSize(file);
        }
        return size;
    }

    private static long calculateFileSize(File file) {
        long fileSize = 0L;
        if (file.isDirectory()) {
            File[] newDirectory = file.listFiles();
            assert newDirectory != null;
            for (File newFile : newDirectory) {
                fileSize += calculateFileSize(newFile);
            }
        } else {
            fileSize = file.length();
        }
        return fileSize;
    }
}

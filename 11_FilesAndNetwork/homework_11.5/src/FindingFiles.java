import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FindingFiles extends RecursiveTask<List<File>>
{
    private Node node;

    public FindingFiles(Node node) {
        this.node = node;
    }

    private List<File> findingFiles = new ArrayList<>();

    @Override
    protected List<File> compute() {
        File folder = node.getFolder();
        if (folder.isDirectory()) {
            List<FindingFiles> subTasks = new LinkedList<>();
            File[] files = folder.listFiles();

            try {
                for (File file : files) {
                    Node child = new Node(file);
                    FindingFiles task = new FindingFiles(child);
                    task.fork();
                    subTasks.add(task);
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            for (FindingFiles task : subTasks) {
                task.join();
            }

        } else if((folder.getName().toLowerCase().endsWith(".json")) | (folder.getName().toLowerCase().endsWith(".csv"))) {
            findingFiles.add(folder);

        }

            return findingFiles;
        }


}
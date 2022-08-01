import java.io.File;

public class Node
{
    private File folder;

    public Node(File folder)
    {
        this.folder = folder;
    }

    public File getFolder()
    {
        return folder;
    }
}
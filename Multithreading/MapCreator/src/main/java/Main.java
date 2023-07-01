import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ForkJoinPool;


public class Main {

    private static final String URL = "https://lenta.ru/";
    private static final String FILE_PATH = "map.txt";
    private static Set<String> visitedLinks = new HashSet<>();


    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        UrlParser urlParser = new UrlParser(URL, visitedLinks);
        visitedLinks = ForkJoinPool.commonPool().invoke(urlParser);

        MapCreator mapCreator = new MapCreator(visitedLinks);
        ArrayList<Node> listOfNodes = mapCreator.getListOfNodes();

        Collections.sort(listOfNodes);
        ArrayList<Node> formattedListOfNodes = mapCreator.formatNodesHierarchy(listOfNodes);

        writeNodesToFile(formattedListOfNodes, FILE_PATH);

        long duration = System.currentTimeMillis() - start;
        System.out.println(duration / 60000 + " min");

    }

    public static void writeNodesToFile(List<Node> listOfNodes, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Node node : listOfNodes) {
                writer.write(node.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
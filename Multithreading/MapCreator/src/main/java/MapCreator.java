import java.util.ArrayList;
import java.util.Set;

public class MapCreator {

    private final ArrayList<Node> listOfNodes;
    private final Set<String> visitedLinks;

    public MapCreator(Set<String> visitedLinks) {
        this.visitedLinks = visitedLinks;
        listOfNodes = new ArrayList<>();
    }

    /**
     * Получает список узлов, создавая экземпляр Node для каждой посещенной ссылки.
     *
     * @return Список узлов, представляющий посещенные ссылки в формате ArrayList.
     */
    public ArrayList<Node> getListOfNodes() {
        for (String link : visitedLinks) {
            Node node = new Node(link);
            listOfNodes.add(node);
        }
        return listOfNodes;
    }

    /**
     * Форматирует иерархию узлов на основе URL-адресов.
     * Удаляет дубликаты и определяет дочерние узлы для каждого узла.
     *
     * @param listOfNodes Список узлов для форматирования.
     * @return Отформатированный список узлов с корректной иерархией.
     */
    public ArrayList<Node> formatNodesHierarchy(ArrayList<Node> listOfNodes) {

        ArrayList<Node> formattedArray = new ArrayList<>();

        for (int i = 0; i < listOfNodes.size(); i++) {
            outerLoop:
            for (int j = i + 1; j < listOfNodes.size(); j++) {

                Node parent = listOfNodes.get(i);
                Node child = listOfNodes.get(j);
                if (child.getUrl().startsWith(parent.getUrl())) {
                    ArrayList<Node> listOfChildren = parent.getChildren();
                    for (Node childrenOfChild : listOfChildren) {
                        if (child.getUrl().startsWith(childrenOfChild.getUrl())) {
                            childrenOfChild.addChild(child);
                            listOfNodes.remove(j);
                            j--;
                            continue outerLoop;
                        }
                    }
                    listOfNodes.get(i).addChild(listOfNodes.get(j));
                    listOfNodes.remove(j);
                    j--;
                }
            }
            formattedArray.add(listOfNodes.get(i));
        }

        return formattedArray;

    }
}
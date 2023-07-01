import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class Node implements Comparable<Node> {

    @Getter
    @Setter
    private String url;

    @Getter
    @Setter
    private ArrayList<Node> children;

    @Getter
    @Setter
    private int level;

    /**
     * Создает новый узел на основе заданного URL-адреса.
     *
     * @param url URL-адрес для создания узла.
     */
    public Node(String url) {
        this.url = url;
        this.level = StringUtils.countMatches(url, '/') - 2;
        children = new ArrayList<>();
    }

    /**
     * Добавляет указанный узел в список дочерних узлов данного узла.
     *
     * @param node Дочерний узел для добавления.
     */
    public void addChild(Node node) {
        node.setLevel(level + 1);
        children.add(node);
    }

    /**
     * Возвращает строковое представление узла и его дочерних узлов.
     *
     * @return Строковое представление узла.
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(url)
                .append("\n");
        for (Node child : children) {
            builder.append("\t".repeat(level - 1))
                    .append(child.toString());
        }
        return builder.toString();
    }

    @Override
    public int compareTo(Node node) {
        return Integer.compare(this.getLevel(), node.getLevel());
    }
}
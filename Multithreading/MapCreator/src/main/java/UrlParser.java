import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.concurrent.RecursiveTask;

public class UrlParser extends RecursiveTask<Set<String>> {

    private final Set<String> visitedLinks;

    private final String link;

    private HashSet<String> pageLinks;

    /**
     * Создает новый экземпляр парсера для заданной ссылки и списка посещенных ссылок.
     *
     * @param link          Ссылка для парсинга.
     * @param visitedLinks  Список посещенных ссылок.
     */
    public UrlParser(String link, Set<String> visitedLinks) {
        this.link = link;
        this.visitedLinks = Collections.synchronizedSet(visitedLinks);
    }

    @SneakyThrows
    @Override
    protected Set<String> compute() {

        List<UrlParser> subTasks = new LinkedList<>();

        createSetOfLinks(link);

        for (String link : pageLinks) {
            if (!visitedLinks.contains(link)) {
                visitedLinks.add(link);
                UrlParser task = new UrlParser(link, visitedLinks);
                task.fork();
                subTasks.add(task);
            }
        }

        for (UrlParser task : subTasks) {
            task.join();
        }

        return visitedLinks;

    }

    @SneakyThrows
    private void createSetOfLinks(String link) {

        pageLinks = new HashSet<>();

        Thread.sleep(100 + (int) (Math.random() * 50));
        Document html = Jsoup.connect(link)
                .maxBodySize(0)
                .userAgent("Opera/96.0.4693.50")
                .referrer("http://www.google.com")
                .ignoreContentType(true)
                .ignoreHttpErrors(true)
                .followRedirects(false)
                .get();

        Elements links = html.select("a[href]");

        for (Element element : links) {
            String absoluteLink = element.attr("abs:href");
            absoluteLink = StringUtils.removeEnd(absoluteLink, "/");
            if (isValidUrl(absoluteLink, pageLinks)) {
                pageLinks.add(absoluteLink);
            }
        }
    }

    /**
     * Проверяет, является ли указанный URL-адрес допустимым для добавления в список ссылок.
     *
     * @param absoluteLink URL-адрес для проверки.
     * @param pageLinks    Список уже обработанных ссылок.
     * @return true, если URL-адрес допустим, иначе false.
     */
    private boolean isValidUrl(String absoluteLink, HashSet<String> pageLinks) {
        return !absoluteLink.contains("#") &&
                !absoluteLink.equals(link) &&
                absoluteLink.startsWith(link) &&
                !absoluteLink.endsWith(".pdf") &&
                !pageLinks.contains(absoluteLink);
    }
}
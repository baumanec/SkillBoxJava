import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main {

  private static final String folderPath = "images";

  public static void main(String[] args) throws IOException {
    Document html = Jsoup.connect("https://lenta.ru").get();
    Elements images = html.select("img");
    images.forEach(element -> {
      String src = element.absUrl("src");
      try {
        getImages(src);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    });
  }

  private static void getImages(String src) throws IOException {

    int indexName = src.lastIndexOf("/");
    if (indexName == src.length()) {
      src = src.substring(1, indexName);
      indexName = src.lastIndexOf("/");
    }

    String name = src.substring(indexName);
    System.out.println(name);
    if (!name.contains(".jpg")) {
      System.out.println("File: " + name + " is not image");
    } else {
      URL url = new URL(src);
      InputStream in = url.openStream();
      OutputStream out = new BufferedOutputStream(new FileOutputStream(folderPath + name));
      for (int b; (b = in.read()) != -1; ) {
        out.write(b);
      }
      out.close();
      in.close();
    }
  }
}

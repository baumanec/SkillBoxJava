public class Main {

  public static void main(String[] args) {
    String safe = searchAndReplaceDiamonds("5484 4578 <4589> 5485 <5777> 5896", "***");
    System.out.println(safe);
  }

  public static String searchAndReplaceDiamonds(String text, String placeholder) {
    for (int i = 0; i < text.length(); i++) {
      if (!(text.contains("<") && text.contains(">"))) {
        return text;
      }
      if (text.isEmpty()) {
        return "";
      }
      text = text.replaceAll("<.+?>", placeholder);
    }
    return text;
  }

}
public class Main {

  public static void main(String[] args) {
    String safe = searchAndReplaceDiamonds("", "***");
    System.out.println(safe);
  }

  public static String searchAndReplaceDiamonds(String text, String placeholder) {
    String textCopy = "";
    String before = "";
    String after = "";
    for (int i = 0; i < text.length(); i++) {
      if (!(text.contains("<") && text.contains(">"))) {
        return text;
      }
      if (text.isEmpty()) {
        return "";
      }
      if (text.charAt(i) == '<') {
        before = text.substring(0, i);
        textCopy = textCopy + before;
      }
      if (text.charAt(i) == '>') {
        textCopy = textCopy + placeholder;
        text = text.substring(i + 1);
        for (int j = 0; j < text.length(); j++) {
          if (text.contains("<") && text.contains(">")) {
            if (text.charAt(j) == '<') {
              before = text.substring(0, j);
              textCopy = textCopy + before;
            }
            if (text.charAt(j) == '>') {
              textCopy = textCopy + placeholder;
              after = text.substring(j + 1);
              text = after.trim();
            }
            textCopy = textCopy + after;
          } else {
            after = text;
            textCopy = textCopy + after;
            break;
          }
        }
      }
    }
    text = textCopy;
    return text;
  }
}
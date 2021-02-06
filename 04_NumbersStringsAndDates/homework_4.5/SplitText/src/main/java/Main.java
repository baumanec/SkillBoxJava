public class Main {

  public static void main(String[] args) {
    String text = "To those who can hear me I say, 'Do not despair.' The misery that is now upon us is but the passing of greed, the bitterness of men who fear the way of human progress. The hate of men will pass and dictators die; and the power they took from the people will return to the people and so long as men die, liberty will never perish. Soldiers! Don’t give yourselves to brutes, men who despise you, enslave you, who regiment your lives, tell you what to do, what to think and what to feel; who drill you, diet you, treat you like cattle, use you as cannon fodder. Don’t give yourselves to these unnatural men, machine men, with machine minds and machine hearts! You are not machines! You are not cattle! You are men! You have the love of humanity in your hearts. You don’t hate; only the unloved hate, the unloved and the unnatural. Soldiers! Don’t fight for slavery! Fight for liberty! In the seventeenth chapter of Saint Luke it is written, 'the kingdom of God is within man' — not one man, nor a group of men, but in all men, in you, you the people have the power, the power to create machines, the power to create happiness. You the people have the power to make this life free and beautiful, to make this life a wonderful adventure. Then, in the name of democracy, let us use that power! Let us all unite!! Let us fight for a new world, a decent world that will give men a chance to work, that will give you the future and old age a security. By the promise of these things, brutes have risen to power, but they lie! They do not fulfill their promise; they never will. Dictators free themselves, but they enslave the people!! Now, let us fight to fulfill that promise!! Let us fight to free the world, to do away with national barriers, to do away with greed, with hate and intolerance. Let us fight for a world of reason, a world where science and progress will lead to all men’s happiness. Soldiers! In the name of democracy, let us all unite!!!";
    System.out.println(splitTextIntoWords(text));
  }

  public static String splitTextIntoWords(String text) {
    if (text.equals("")) {
      return "";
    }
    text = text.replaceAll("[^a-zA-Z\\s\\-’]", "");
    text = text.replaceAll("[\\-]", " ");
    String[] words = text.split("\\s+");
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < words.length; i++) {
      if (i < words.length - 1) {
        words[i] += "\n";
      }
      builder.append(words[i]);
    }
    return String.valueOf(builder);
  }
}
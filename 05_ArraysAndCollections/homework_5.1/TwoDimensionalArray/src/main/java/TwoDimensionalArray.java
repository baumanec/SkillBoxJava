public class TwoDimensionalArray {

  public static char symbol = 'X';

  public static char[][] getTwoDimensionalArray(int size) {

    char[][] twoDimensionalArray = new char[size][size];
    for (int i = 0; i < twoDimensionalArray.length; i++) {
      for (int j = 0; j < twoDimensionalArray[i].length; j++) {
        if ((i == j) | (i + j == size - 1)) {
          twoDimensionalArray[i][j] = symbol;
        } else {
          twoDimensionalArray[i][j] = ' ';
        }
      }
    }

    return twoDimensionalArray;
  }

  public static String getArray(char[][] twoDimensional) {

    StringBuilder builder = new StringBuilder();

      for (char[] chars : twoDimensional) {
          for (int j = 0; j < chars.length; j++) {
              if (j == 0) {
                  builder.append("[");
              }
              builder.append(chars[j]);
              if (j < chars.length - 1) {
                  builder.append(", ");
              }
              if (j == chars.length - 1) {
                  builder.append("]").append(System.lineSeparator());
              }
          }
      }

    return String.valueOf(builder);
  }
}

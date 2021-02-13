public class ReverseArray {

    //TODO: Напишите код, который меняет порядок расположения элементов внутри массива на обратный.
    public static String[] reverse (String[] strings){
        String buffer;
        for (int i = 0; i < strings.length/2; i++)
        {
            buffer = strings[strings.length - i - 1];
            strings[strings.length - i - 1] = strings[i];
            strings[i] = buffer;
        }
        return strings;
    }
}

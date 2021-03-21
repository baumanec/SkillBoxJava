import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;
        Pattern patternForEmail = Pattern.compile("[\\s<({—=$!|})?*+>_]");
        Pattern patternForPhone = Pattern.compile("[^+\\d]");

        String[] components = data.split("\\s+");
        Matcher matcherInEmail = patternForEmail.matcher(components[INDEX_EMAIL]);
        Matcher matcherInPhone = patternForPhone.matcher(components[INDEX_PHONE]);
        if (components.length != 4){
            throw new IllegalArgumentException("Wrong format. Correct format: \n" +
                "add Василий Петров " +
                "vasily.petrov@gmail.com +79215637722");
        }
        if (matcherInEmail.find() | !components[INDEX_EMAIL].contains(".") | !components[INDEX_EMAIL].contains("@")){
            throw new IllegalArgumentException("Wrong email format. Correct format: \n" +
                "vasily.petrov@gmail.com");
        }
        if (matcherInPhone.find() | components[INDEX_PHONE].length() != 12){
            throw new IllegalArgumentException("Wrong phone format. Correct format: \n" +
                "+79215637722");
        }
        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}
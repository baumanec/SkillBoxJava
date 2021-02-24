import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final String WRONG_INPUT = "Неверный формат ввода";
    private static final PhoneBook contacts = new PhoneBook();

    public static void main(String[] args) {
        Pattern patternForPhone = Pattern.compile("[0-9]");
        Pattern patternForName = Pattern.compile("[а-яА-Я]");
        Scanner scanner = new Scanner(System.in);
        String name;
        String phone;
        while (true) {
            System.out.println("Введите номер, имя или команду:");
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            Matcher matcherPhone = patternForPhone.matcher(input);
            Matcher matcherName = patternForName.matcher(input);
            if (matcherName.find()) {
                name = input.trim();
                if (!contacts.isContainsName(name)) {
                    System.out.println(
                        "Такого имени в телефонной книге нет.\nВведите номер телефона для абонента \""
                            + name + "\":");
                    contacts.addContact(scanner.nextLine(), name);
                    System.out.println("Контакт сохранён!");
                } else {
                    System.out.println(
                        "Абонент с таким именем есть в телефонной книге.\nНомер абонента:");
                    System.out.println(contacts.getPhonesByName(name));
                }
                continue;
            }
            if (matcherPhone.find()) {
                phone = input.trim();
                if (!contacts.isContainsPhone(phone)) {
                    System.out.println(
                        "Такого номера в телефонной книге нет.\nВведите имя абонента для номера \""
                            + phone + "\":");
                    String buffer = scanner.nextLine();
                    if (contacts.isContainsName(buffer)) {
                        phone = contacts.getPhoneByName(buffer) + ", " + phone;
                    }
                    contacts.addContact(phone, buffer);
                    System.out.println("Контакт сохранён!");
                } else {
                    System.out
                        .println("Абонент с таким номером есть в телефонной книге.\nИмя абонента:");
                    System.out.println(contacts.getNameByPhone(phone));
                }
                continue;
            }
            if (!matcherName.find() | !matcherPhone.find()) {
                System.out.println(WRONG_INPUT);
            }
            if (input.equals("LIST")) {
                System.out.println(contacts.getAllContacts());
            }
        }
    }
}
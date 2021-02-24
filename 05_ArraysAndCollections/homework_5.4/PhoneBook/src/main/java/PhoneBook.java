import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook {

  TreeMap<String, String> phoneBook = new TreeMap<>();
  Set<Map.Entry<String, String>> entrySet = phoneBook.entrySet();

  public void addContact(String phone, String name) {
    // проверьте корректность формата имени и телефона
    // если такой номер уже есть в списке, то перезаписать имя абонента
    Pattern patternForPhone = Pattern.compile("[0-9]");
    Pattern patternForName = Pattern.compile("[а-яА-Я]");
    Matcher matcherPhone = patternForPhone.matcher(phone);
    Matcher matcherName = patternForName.matcher(name);
    if (matcherPhone.find() && matcherName.find()) {
      if (phoneBook.containsValue(phone)) {
        for (Map.Entry<String, String> key : entrySet) {
          phoneBook.remove(key.getKey(), phone);
          phoneBook.put(name, phone);
        }
      } else {
        phoneBook.put(name, phone);
      }
    } else {
      System.out.println("Неверный формат ввода" + System.lineSeparator());
    }
  }

  public String getNameByPhone(String phone) {
    // формат одного контакта "Имя - Телефон"
    // если контакт не найдены - вернуть пустую строку
    StringBuilder builder = new StringBuilder();
    if (phoneBook.containsValue(phone)) {
      for (Map.Entry<String, String> key : entrySet) {
        if (key.getValue().equals(phone)) {
          builder.append(key.getKey())
              .append(" - ")
              .append(phone);
        }
      }
    }
    return String.valueOf(builder);
  }

  public Set<String> getPhonesByName(String name) {
    // формат одного контакта "Имя - Телефон"
    // если контакт не найден - вернуть пустой TreeSet
    TreeSet<String> phones = new TreeSet<>();
    if (phoneBook.containsKey(name)) {
      phones.add(name + " - " + phoneBook.get(name));
      return phones;
    } else {
      return new TreeSet<>();
    }
  }

  public Set<String> getAllContacts() {
    // формат одного контакта "Имя - Телефон"
    // если контактов нет в телефонной книге - вернуть пустой TreeSet
    TreeSet<String> allContacts = new TreeSet<>();
    for (Map.Entry<String, String> key : entrySet) {
      allContacts.add(key.getKey() + " - " + key.getValue());
    }
    if (allContacts.isEmpty()) {
      return new TreeSet<>();
    } else {
      return allContacts;
    }
  }

  public boolean isContainsName(String name) {
    return phoneBook.containsKey(name);
  }

  public boolean isContainsPhone(String phone) {
    return phoneBook.containsValue(phone);
  }

  public String getPhoneByName(String name) {
    String phone = "";
    if (phoneBook.containsKey(name)) {
      phone = phoneBook.get(name);
    }
    return phone;
  }
}
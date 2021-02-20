import java.util.ArrayList;

public class TodoList {

  ArrayList<String> todoList = new ArrayList<>();
  String buffer = "";

  public void add(String todo) {
    todoList.add(todo);
    System.out.println("Добавлено дело \"" + todo + "\"");
  }

  public void add(int index, String todo) {
    if (index < 0 | index > todoList.size()) {
      System.out.println("Невозможно добавить дело на указанный номер");
      if (!buffer.equals(todo)) {
        buffer = todo;
      }
    } else {
      todoList.add(index, todo);
      System.out.println("Добавлено дело \"" + todo + "\"" + " под номером " + index);
    }
  }

  public void edit(String todo, int index) {
    if (index < 0 | index > todoList.size()) {
      System.out.println("Дело с таким номером не существует");
    } else {
      System.out.println(
          "Дело \"" + todoList.get(index) + "\"" + " заменено на \"" + todo + "\"");
      todoList.remove(index);
      todoList.add(index, todo);
    }
  }

  public void delete(int index) {
    if (index < 0 | index >= todoList.size()) {
      System.out.println("Дело с таким номером не существует");
    } else {
      System.out.println("Дело \"" + todoList.get(index) + "\" " + "удалено");
      todoList.remove(index);
    }
  }

  public ArrayList<String> getTodos() {
    String todos;
    ArrayList<String> list = new ArrayList<>();
    for (int i = 0; i < todoList.size(); i++) {
      todos = todoList.get(i);
      list.add(todos);
      if (!buffer.isEmpty() && i == todoList.size() - 1) {
        list.add(buffer);
      }
    }
    return list;
  }

}
import java.util.Scanner;

public class Main {

    private static TodoList todoList = new TodoList();
    static String command = "";
    static String todo = "";
    static int index = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        outerLoop:
        while (true) {
            String text = scanner.nextLine();
            String[] words = text.split("\\s", 2);
            for (int i = 0; i < words.length; i++) {
                if (words[i].equals("ADD") | words[i].equals("LIST") | words[i].equals("EDIT")
                    | words[i].equals("DELETE")) {
                    command = words[i];
                }
                if (!(command.equals("ADD") | command.equals("LIST") | command.equals("EDIT")
                    | command.equals("DELETE"))) {
                    System.out.println(
                        "Введите команду ADD(добавление), EDIT(изменение) или DELETE(удаление)");
                    break;
                }
                if (words.length < 2 && !command.equals("LIST")) {
                    System.out.println("Неверный формат ввода");
                    break outerLoop;
                }
                if (i == 1) {
                    todo = words[i];
                }
            }

            if (command.equals("ADD")) {
                if (Character.isDigit(words[1].charAt(0))) {
                    String[] modifiedTodo = words[1].split("\\s", 2);
                    for (int j = 0; j < modifiedTodo.length; j++) {
                        if (j == 0) {
                            index = Integer.parseInt(modifiedTodo[j]);
                        }
                        if (j == 1) {
                            todo = modifiedTodo[j];
                        }
                    }
                    todoList.add(index, todo);
                    if (index > todoList.getTodos().size() | index < 0) {
                        continue;
                    }
                } else {
                    todoList.add(todo);
                }
            }
            if (command.equals("LIST")) {
                for (int i = 0; i < todoList.getTodos().size(); i++) {
                    System.out.println(
                        i + " - " + todoList.getTodos().get(i));
                }
            }
            if (command.equals("EDIT")) {
                String[] modifiedTodo = words[1].split("\\s", 2);
                for (int i = 0; i < modifiedTodo.length; i++) {
                    if (i == 0) {
                        index = Integer.parseInt(modifiedTodo[i]);
                    }
                    if (i == 1) {
                        todo = modifiedTodo[i];
                    }
                }
                todoList.edit(todo, index);
                if (index > todoList.getTodos().size() | index < 0) {
                    continue;
                }
            }
            if (command.equals("DELETE")) {
                todoList.delete(Integer.parseInt(words[1]));
                if (Integer.parseInt(words[1]) > todoList.getTodos().size()
                    | Integer.parseInt(words[1]) < 0) {
                }
            }
        }
    }
}
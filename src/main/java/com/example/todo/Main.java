package main.java.com.example.todo;

import main.java.com.example.todo.service.TaskService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        TaskService service = new TaskService();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Здраствуйте, это мой первый проект toDoList. Выберите действие:\n\n" +
                "1. Добавить задачу.\n" +
                "2. Показать все задачи.\n" +
                "3. Удалить задачу.\n" +
                "4. Изменить задачу.\n" +
                "5. Показать одну задачу по ID.\n" +
                "\nВведите номер команды: ");

        int choiseMenu = scanner.nextInt();
        scanner.nextLine();

        switch(choiseMenu)
        {
            case 1:
                System.out.println("Введите название файла: ");
                String nameFile = scanner.nextLine();
                if(nameFile.isBlank())
                {
                    System.out.println("Вы ничего не ввели.");
                    return;
                }
                List<String> tasks = new ArrayList<>();
                System.out.println("Введите ваши задачи:");
                while(true)
                {
                    String line = scanner.nextLine();
                    if(line.equals("stop")) break;

                    tasks.add(line);
                }
                service.addTask(nameFile, tasks);
                break;

            case 2:
                System.out.println("Вот все ваши файлы:\n");
                service.showAll();
                break;

            case 3:
                System.out.println("Введите название файла который хотите удалить: ");

                String nameFileToDelete = scanner.nextLine();
                if(nameFileToDelete.isBlank())
                {
                    System.out.println("Вы ничего не ввели.");
                    return;
                }

                service.showFile(nameFileToDelete);
                System.out.println("Введите строку которую хотите удалить: ");
                int idToDelete = scanner.nextInt();

                service.deleteTask(nameFileToDelete, idToDelete);
                break;

            case 4:
                System.out.println("Введите название файла который вы хотите изменить: ");
                String nameFileToChange = scanner.nextLine();

                if(nameFileToChange.isBlank())
                {
                    System.out.println("Вы ничего не ввели.");
                    return;
                }
                service.showFile(nameFileToChange);

                System.out.println("Какую вы хотите изменить строку? (Enter - выход)");
                String input = scanner.nextLine();

                System.out.println("Введите новый текст строки:");
                String newText = scanner.nextLine();

                service.changeTask(nameFileToChange, input, newText);
                break;

            case 5:
                System.out.println("Введите название файла который вы хотите посмотреть: ");
                String nameFileToCheck = scanner.nextLine();

                if(nameFileToCheck.isBlank())
                {
                    System.out.println("Вы ничего не ввели.");
                    return;
                }

                System.out.println("Введите ID которое хотите увидеть: ");
                String textID = scanner.nextLine();
                if(textID.isBlank()) return;

                service.showOneTask(nameFileToCheck, textID);

                break;

            default:
                System.out.println("Вы ввели неправильную команду.");
                return;
        }
    }
}

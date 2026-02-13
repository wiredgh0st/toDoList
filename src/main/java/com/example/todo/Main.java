package main.java.com.example.todo;

import main.java.com.example.todo.service.TaskService;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Здраствуйте, это мой первый проект toDoList. Выберите действие:\n\n" +
                "1. Добавить задачу.\n" +
                "2. Показать все задачи.\n" +
                "3. Удалить задачу.\n" +
                "4. Изменить задачу.\n" +
                "5. Показать одну задачу по ID.\n" +
                "\nВведите номер команды: ");

        int ChoiseMenu = scanner.nextInt();
        scanner.nextLine();

        switch(ChoiseMenu)
        {
            case 1:
                System.out.println("Введите название файла: ");
                String nameFile = scanner.nextLine();

                TaskService taskToAdd = new TaskService(nameFile);
                taskToAdd.addTask();
                break;

            case 2:
                break;

            case 3:
                System.out.println("Введите название файла который хотите удалить: ");
                String nameFileToDelete = scanner.nextLine();

                TaskService taskToDelete = new TaskService(nameFileToDelete);
                taskToDelete.deleteTask();
                break;

            case 4:
                break;
            case 5:
                break;

            default:
                System.out.println("Вы ввели неправильную команду.");
                return;
        }
    }
}

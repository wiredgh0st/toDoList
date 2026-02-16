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

                if(nameFile.isBlank())
                    System.out.println("Вы ничего не ввели.");
                else
                    TaskService.addTask(nameFile);
                break;

            case 2:
                System.out.println("Вот все ваши файлы:\n");
                TaskService.showAll();
                break;

            case 3:
                System.out.println("Введите название файла который хотите удалить: ");
                String nameFileToDelete = scanner.nextLine();

                if(nameFileToDelete.isBlank())
                    System.out.println("Вы ничего не ввели.");
                else
                    TaskService.deleteTask(nameFileToDelete);
                break;

            case 4:
                System.out.println("Введите название файла который вы хотите изменить: ");
                String nameFileToChange = scanner.nextLine();

                if(nameFileToChange.isBlank())
                    System.out.println("Вы ничего не ввели.");
                else
                    TaskService.changeTask(nameFileToChange);
                break;

            case 5:
                System.out.println("Введите название файла который вы хотите посмотреть: ");
                String nameFileToCheck = scanner.nextLine();

                if(nameFileToCheck.isBlank())
                    System.out.println("Вы ничего не ввели.");
                else
                    TaskService.showOneTask(nameFileToCheck);
                break;

            default:
                System.out.println("Вы ввели неправильную команду.");
                return;
        }
    }
}

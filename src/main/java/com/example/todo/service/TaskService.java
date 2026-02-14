package main.java.com.example.todo.service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class TaskService
{
    public static void addTask(String nameFile)
    {

        try(BufferedWriter buff = new BufferedWriter(new FileWriter("D:\\ToDoList_wiredgh0st\\data\\" + nameFile + ".txt"))){
            Scanner scanner = new Scanner(System.in);

            String task = null;
            int idTask = 1;

            System.out.println("Введите \"stop\" если хотите закончить ввод.");
            while(true)
            {
                task = scanner.nextLine();

                if(task.equals("stop"))
                    break;

                buff.write(idTask + ": " + task + "\n");
                idTask++;
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Ошибка! Не удалось создать файл.");
            return;
        }
    }

    public static void showAll() {

        Path folderPath = Path.of("D:\\ToDoList_wiredgh0st\\data\\");

        try (Stream<Path> paths = Files.list(folderPath)) {

            paths
                    .filter(Files::isRegularFile)
                    .forEach(path -> {

                        System.out.println("Файл: " + path.getFileName());

                        try {
                            List<String> lines = Files.readAllLines(path);
                            lines.forEach(System.out::println);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        System.out.println("------------------");
                    });

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



    public static void deleteTask(String nameFile)
    {
        try(Scanner scanner = new Scanner(System.in)){

            List<String> lines = Files.readAllLines(Path.of("D:\\ToDoList_wiredgh0st\\data\\" + nameFile + ".txt"));

            System.out.println("Введите строку которую хотите удалить: ");
            int idToDelete = scanner.nextInt();
            lines.removeIf(line -> line.startsWith(idToDelete + ":"));

            List<String> updated = new ArrayList<>();
            int newId = 1;

            for(String line: lines)
            {
                String[] parts = line.split(": ");
                parts[0] = String.valueOf(newId);

                updated.add(String.join(": ", parts));
                newId++;
            }
            Files.write(Path.of("D:\\ToDoList_wiredgh0st\\data\\" + nameFile + ".txt"), updated);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void changeTask(String nameFile)
    {
        try {
            Scanner scanner = new Scanner(System.in);
            List<String> lines = Files.readAllLines(Path.of("D:\\ToDoList_wiredgh0st\\data\\" + nameFile + ".txt"));

            System.out.println("Вот ваш файл:\n");
            for(String line: lines)
                System.out.println(line);

                System.out.println("Какую вы хотите изменить строку? (Enter - выход)");
                String input = scanner.nextLine();

                if(!input.isBlank())
                {
                    int idToEdit = Integer.parseInt(input);

                    for(int i = 0; i < lines.size(); i++)
                    {
                        String[] parts = lines.get(i).split(": ", 3);
                        int currectId = Integer.parseInt(parts[0]);

                        if(currectId == idToEdit)
                        {
                            System.out.println("Введите новый текст строки:");
                            String newText = scanner.nextLine();

                            parts[1] = newText;

                            lines.set(i, String.join(": ", parts));
                            break;
                        }
                }
                    Files.write(Path.of("D:\\ToDoList_wiredgh0st\\data\\" + nameFile + ".txt"), lines);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

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

    public void addTask(String nameFile, List<String> tasks)
    {
        try(BufferedWriter buff = new BufferedWriter(new FileWriter("data/" + nameFile + ".txt", true))){

            int startID = 1;
            for(String line: tasks)
            {
                buff.write(startID + ": " + line + "\n");
                startID++;
            }

        } catch (IOException e) {
            System.out.println("Ошибка! Не удалось создать файл.");
            return;
        }
    }

    public void showAll() {

        Path folderPath = Path.of("data");

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



    public void deleteTask(String nameFile, int idToDelete)
    {
        try {
            List<String> lines = Files.readAllLines(getPath(nameFile));
            lines.removeIf(line -> line.startsWith(idToDelete + ": "));

            List<String> updated = new ArrayList<>();
            int newId = 1;

            for(String line: lines)
            {
                String[] parts = line.split(": ", 2);
                parts[0] = String.valueOf(newId);

                updated.add(String.join(": ", parts));
                newId++;
            }
            Files.write(getPath(nameFile), updated);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeTask(String nameFile, String input, String newText)
    {
        try {
            List<String> lines = Files.readAllLines(getPath(nameFile));

                if(!input.isBlank())
                {
                    int idToEdit = Integer.parseInt(input);

                    for(int i = 0; i < lines.size(); i++)
                    {
                        String[] parts = lines.get(i).split(": ", 2);
                        int currectId = Integer.parseInt(parts[0]);

                        if(currectId == idToEdit)
                        {
                            parts[1] = newText;

                            lines.set(i, String.join(": ", parts));
                            break;
                        }
                }
                    Files.write(getPath(nameFile), lines);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showOneTask(String nameFile, String textID)
    {
        try {
            List<String> lines = Files.readAllLines(getPath(nameFile));
            int ID = Integer.parseInt(textID);
            boolean found = false;

            for(String line: lines)
            {
                String[] parts = line.split(": ", 2);
                int currectId = Integer.parseInt(parts[0]);

                if(currectId == ID)
                {
                    System.out.println("Найдена задача:");
                    System.out.println(line);
                    found = true;
                    break;
                }
            }

            if(!found)
                System.out.println("Задача не найдена.");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showFile(String nameFile)
    {
        try {

            List<String> lines = Files.readAllLines(getPath(nameFile));
            System.out.println("Вот ваш файл:\n");
            for(String line: lines)
                System.out.println(line);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Path getPath(String nameFile)
    {
        return Path.of("data/" + nameFile + ".txt");
    }
}

package main.java.com.example.todo.service;

import main.java.com.example.todo.model.Status;
import main.java.com.example.todo.model.Task;
import main.java.com.example.todo.repository.TaskRepository;
import main.java.com.example.todo.util.FileManager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class TaskService
{
    private TaskRepository taskRepository = new TaskRepository();

    public void addTask(String nameFile, List<String> lines)
    {
        List<Task> tasks = taskRepository.findAll(nameFile);
        int nextID = tasks.size() + 1;

        for(String line: lines)
        {
            tasks.add(new Task(nextID, line, Status.NEW));
            nextID++;
        }
        taskRepository.saveAll(nameFile, tasks);
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

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTask(String nameFile, int idToDelete)
    {
        List<Task> tasks = taskRepository.findAll(nameFile);
        tasks.removeIf(task -> task.getId() == idToDelete);
        int i = 1;
        for(Task task: tasks)
        {
            task.setId(i);
            i++;
        }

        taskRepository.saveAll(nameFile, tasks);
    }

    public void changeTask(String nameFile, String input, String newText)
    {
        if(!input.isBlank())
        {
            int idToEdit = Integer.parseInt(input);
            List<Task> tasks = taskRepository.findAll(nameFile);

            for(Task task: tasks)
                if(task.getId() == idToEdit)
                    task.setTitle(newText);

            taskRepository.saveAll(nameFile, tasks);
        }
    }

    public void showOneTask(String nameFile, String textID)
    {
            int ID = Integer.parseInt(textID);
            boolean found = false;
            List<Task> tasks = taskRepository.findAll(nameFile);

            for (Task task : tasks)
            {
                if (task.getId() == ID)
                {
                    System.out.println("Найдена задача:");
                    System.out.println(task);
                    found = true;
                    break;
                }
            }

        if (!found)
            System.out.println("Задача не найдена.");
    }

    public void changeStatus(String nameFile, String statusID, int choiseStatus)
    {
            int ID = Integer.parseInt(statusID);
            List<Task> tasks = taskRepository.findAll(nameFile);

            for(Task task: tasks)
            {
                if(task.getId() == ID)
                {
                    Status newStatus = Status.fromNumber(choiseStatus);
                    task.setStatus(newStatus);
                }
            }
            taskRepository.saveAll(nameFile, tasks);
    }

    public void showFile(String nameFile)
    {
        try {

            List<String> lines = Files.readAllLines(FileManager.getPath(nameFile));
            System.out.println("Вот ваш файл:\n");
            for(String line: lines)
                System.out.println(line);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

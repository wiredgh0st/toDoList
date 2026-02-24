package main.java.com.example.todo.repository;

import main.java.com.example.todo.model.Status;
import main.java.com.example.todo.model.Task;
import main.java.com.example.todo.util.FileManager;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository
{
    List<Task> findAll(String nameFile)
    {
        try {
            List<String> lines = Files.readAllLines(FileManager.getPath(nameFile));
            List<Task> tasks = new ArrayList<>();
            for(int i = 0; i < lines.size(); i++)
            {
                String line = lines.get(i);
                String[] parts = line.split(": ", 3);

                int ID = Integer.parseInt(parts[0]);
                Status status = Status.valueOf(parts[2]);

                Task task = new Task(ID, parts[1], status);
                tasks.add(task);
            }
            return tasks;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveAll(String nameFile, List<Task> tasks)
    {
        try {
            List<String> lines = new ArrayList<>();

            for(Task task: tasks)
            {
                String line = task.toString();
                lines.add(line);
            }

            Files.write(FileManager.getPath(nameFile), lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

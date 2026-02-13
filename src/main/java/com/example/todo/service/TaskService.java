package main.java.com.example.todo.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskService
{
    private String nameFile;

    public TaskService(String nameFile)
    {
        setNameFile(nameFile);
    }

    public void setNameFile(String nameFile) {
        if(nameFile == null || nameFile.isEmpty())
            this.nameFile = "Task";
        else
            this.nameFile = nameFile;
    }

    public void addTask()
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

    public void deleteTask()
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

    public void changeTask()
    {

    }
}

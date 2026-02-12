package main.java.com.example.todo.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Task
{
    private String nameFile;

    public Task(String nameFile)
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
        Scanner scanner = new Scanner(System.in);
        try(BufferedWriter buff = new BufferedWriter(new FileWriter("D:\\ToDoList_wiredgh0st\\data\\" + nameFile + ".txt"))){

            String task = null;
            int numberTask = 1;

            System.out.println("Введите \"stop\" если хотите закончить ввод.");
            while(true)
            {
                task = scanner.nextLine();

                if(task.equals("stop"))
                    break;

                buff.write(numberTask + ": " + task + "\n");
                numberTask++;
            }
        } catch (IOException e) {
            System.out.println("Ошибка! Не удалось создать файл.");
            return;
        }
    }
}

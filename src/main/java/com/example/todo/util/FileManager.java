package main.java.com.example.todo.util;

import java.nio.file.Path;

public class FileManager
{
    public static Path getPath(String nameFile)
    {
        return Path.of("data/" + nameFile + ".txt");
    }
}

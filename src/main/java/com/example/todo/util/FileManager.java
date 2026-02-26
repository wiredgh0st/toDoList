package main.java.com.example.todo.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager
{
    public static Path getPath(String nameFile)
    {
        Path folder = Path.of("data");

        if(!Files.exists(folder))
            try {
                Files.createDirectories(folder);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        return folder.resolve(nameFile + ".txt");
    }

}

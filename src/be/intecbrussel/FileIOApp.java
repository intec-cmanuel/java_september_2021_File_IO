package be.intecbrussel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileIOApp {
    public static void main(String[] args) {
        Path path = Paths.get("files");
        path = path.resolve("awesomefiles");
        path = path.resolve("MyAwesomeFile.txt");

        // path = files/awesomefiles/MyAwesomeFile.txt
        // path.getParent() = files/awesomefiles
        // path.getParent().resolve(...) = files/awesomefiles/MyAwesomeCopy.txt
        Path copiedPath = path.getParent().resolve("MyAwesomeCopy.txt");

        System.out.println(path);

        createOurFile(path);
        writeDataToFile(path);
        readDataFromFile(path);
        copyFile(path, copiedPath);
        deleteFile(path);
    }

    private static void createOurFile(Path path) {
        try {
            Files.createDirectories(path.getParent());

            if (Files.notExists(path)) {
                Files.createFile(path);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private static void writeDataToFile(Path path) {
        List<String> lines = new ArrayList<>();
        lines.add("Today we are monday");
        lines.add("We are not money");

        try {
            Files.write(path, lines);
        } catch (IOException ioException){
            ioException.printStackTrace();
        }

    }

    private static void readDataFromFile(Path path) {
        try {
            List<String> lines = Files.readAllLines(path);
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyFile(Path path, Path copiedPath) {
        try {
            Files.copy(path, copiedPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deleteFile(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

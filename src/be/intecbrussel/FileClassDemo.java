package be.intecbrussel;

import java.io.File;
import java.nio.file.Path;

public class FileClassDemo {
    public static void main(String[] args) {
        File file = new File("files/awesomefiles/MyAwesomeFile.txt");
        Path path = file.toPath();
        file = path.toFile();
        System.out.println(file);
    }
}

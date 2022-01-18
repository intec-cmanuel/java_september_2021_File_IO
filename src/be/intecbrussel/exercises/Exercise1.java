package be.intecbrussel.exercises;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.List;

public class Exercise1 {
    public static void main(String[] args) {
        try {
            Files.createDirectories(Paths.get("files/exercises"));
            Path myFile = Paths.get("files/exercises/exercise1.txt");
            if (Files.notExists(myFile)) {
                Files.createFile(myFile);
            }

            writeDataToFile(myFile);
            DosFileAttributes attributes = Files.readAttributes(myFile, DosFileAttributes.class);
            System.out.println(attributes.lastAccessTime());

            readFromFile(myFile);
            fileOwner(myFile);
            fileOwnerByErik(myFile);
            renameFile(myFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void renameFile(Path myFile) {
        try {
            Files.move(myFile, myFile.resolveSibling("exerciseNewName.txt"), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fileOwnerByErik(Path myFile) {
        try {
            System.out.println("Owner of file: " + Files.getOwner(myFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fileOwner(Path myFile) {
        FileOwnerAttributeView foav = Files.getFileAttributeView(myFile, FileOwnerAttributeView.class);
        try {
            UserPrincipal user = foav.getOwner();
            System.out.println("The owner is: " + user.getName());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private static void readFromFile(Path myFile) {
        try (FileReader fileReader = new FileReader(myFile.toFile())) {
            int character;
            while ((character = fileReader.read()) != -1) {
                System.out.println((char) character);
            }
        } catch (IOException ioException) {
             ioException.printStackTrace();
        }
    }

    private static void writeDataToFile(Path path) {
        List<String> lines = new ArrayList<>();
        lines.add("hello");
        lines.add("world");

        try {
            Files.write(path, lines);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}

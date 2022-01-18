package be.intecbrussel.exercises;

import be.intecbrussel.IOProcessingStreamApp;

import java.io.*;
import java.nio.file.*;

import static be.intecbrussel.IOProcessingStreamApp.createFile;

public class Exercise2 {
    public static void main(String[] args) {
        Path path = Paths.get("files/exercises/exercise2/exercise2.txt");
        createFile(path);
        writeDataToFile(path);
        readFile(path);
    }

    private static void readFile(Path path) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {

            String line;
            while ((line = bufferedReader.readLine()) != null  ) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private static void writeDataToFile(Path path) {
        try (FileWriter fileWriter = new FileWriter(path.toFile(),true);
        BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {

//            path.toFile().setReadOnly();

            bufferedWriter.write("hello\n");        // hello\n
            bufferedWriter.write("world" + "\n");   // world\n

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


}

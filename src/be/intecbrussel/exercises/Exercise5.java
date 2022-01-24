package be.intecbrussel.exercises;

import be.intecbrussel.IOProcessingStreamApp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class Exercise5 {
    public static void main(String[] args) {
        Path path = Paths.get("files/exercises/exercise5/exercise5.txt");
        IOProcessingStreamApp.createFile(path);
        writeData(path);
        readData(path);
    }

    private static void writeData(Path path) {
        String toWrite = "I am writing this to a file";
        String toWrite2 = "I am also writing this";

        try (DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(new FileOutputStream(path.toFile()))) {
            deflaterOutputStream.write((toWrite + "\n") .getBytes(StandardCharsets.UTF_8));
            deflaterOutputStream.write((toWrite2 + "\n") .getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readData(Path path) {
        try (FileInputStream fileInputStream = new FileInputStream(path.toFile());
             InflaterInputStream inflaterInputStream = new InflaterInputStream(fileInputStream)) {

            int character;
            while ((character = inflaterInputStream.read()) != -1) {
                System.out.print((char)character);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

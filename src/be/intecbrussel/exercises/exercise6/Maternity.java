package be.intecbrussel.exercises.exercise6;

import be.intecbrussel.IOProcessingStreamApp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class Maternity {
    public static void main(String[] args) {
        Person person = new Person("Jean", "Pierre", LocalDate.now());

        Path path = Paths.get("files/exercises/exercise6/" + person.getFirstName() + ".txt");
        IOProcessingStreamApp.createFile(path);

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {

            objectOutputStream.writeObject(person);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

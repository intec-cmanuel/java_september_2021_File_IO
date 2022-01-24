package be.intecbrussel.bytestreams;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class PersonApp {
    public static void main(String[] args) {
        Path path = Paths.get("files/bytesteams/people.ser");
        Person person = new Person("Jean-Pierre","happy");
        person.setTimeLoggedOff(Duration.ofDays(5));

        // CREATE FILE IF NOT EXIST
        try {
            Files.createDirectories(path.getParent());
            if (Files.notExists(path)) {
                Files.createFile(path);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        // WRITE PERSON TO FILE
        try (FileOutputStream fileOutputStream = new FileOutputStream(path.toFile());
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(person);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // READ PERSON TO FILE
        try (FileInputStream fileInputStream = new FileInputStream(path.toFile());
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            Person jeanpierre = (Person) objectInputStream.readObject();
            System.out.println(jeanpierre);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}

package be.intecbrussel.asssignement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;

public class UserSentenceProcessorApp {
    public static void main(String[] args) {
        System.out.println("Enter sentences. Type exit to stop");

        Scanner scanner = new Scanner(System.in);
        Path path = Paths.get("files/sentences.txt");
        String sentence = "";

        createFile(path);

        while (scanner.hasNextLine() && !(sentence = scanner.nextLine()).equals("exit")) {
            if (sentence.trim().isEmpty()) {
                continue;
            }

            writeToFile(path, sentence);
        }


    }

    private static void writeToFile(Path path, String sentence) {
        try (FileWriter fileWriter = new FileWriter(path.toFile(), true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            sentence = processSentence(sentence);
            bufferedWriter.write(sentence + "\n");

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private static String processSentence(String sentence) {
        String lowerCase = sentence.toLowerCase();
        String firstLetterCapital = lowerCase.substring(0,1).toUpperCase().concat(lowerCase.substring(1));

        if ( !(firstLetterCapital.endsWith(".") || firstLetterCapital.endsWith("?") || firstLetterCapital.endsWith("!")) ) {
            firstLetterCapital = firstLetterCapital.concat(".");
        }

        return firstLetterCapital;
    }

    private static void createFile(Path path) {
        try {
            Files.createDirectories(path.getParent());
            if (Files.notExists(path)) {
                Files.createFile(path);
            } else {
                emptyFile(path);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void emptyFile(Path path) {
        try (FileWriter fileWriter = new FileWriter(path.toFile())){
            fileWriter.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

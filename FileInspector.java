import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileInspector {

    static void main() {
        File srcDir = new File("C:\\Users\\micha\\IdeaProjects\\Lab12\\src");
        JFileChooser chooser = new JFileChooser(srcDir);

        int result = chooser.showOpenDialog(null);

        if (result != JFileChooser.APPROVE_OPTION) {
            System.out.println("No file was selected. Exiting.");
            return;
        }

        File selectedFile = chooser.getSelectedFile();
        Path path = selectedFile.toPath();

        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        try{
            List<String> lines = Files.readAllLines(path);

            for (String line : lines) {
                System.out.println(line);
                lineCount++;

                String trimmedLine = line.trim();
                if (!trimmedLine.isEmpty()) {
                    wordCount += trimmedLine.split("\\s+").length;
                }

                charCount += line.length();

            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        System.out.println();
        System.out.println("--- Report ---");
        System.out.println("File name: " + selectedFile.getName());
        System.out.println("Number of lines: " + lineCount);
        System.out.println("Number of words: " + wordCount);
        System.out.println("Number of characters: " + charCount);

    }

}
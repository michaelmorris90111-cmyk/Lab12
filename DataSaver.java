import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSaver {

    static void main() {
        Scanner in = new Scanner(System.in);
        ArrayList<String> records = new ArrayList<>();
        int idCounter = 1;
        boolean addAnother;

        do {
            String firstName = SafeInput.getNonZeroLenString(in, "Enter first name");
            String lastName  = SafeInput.getNonZeroLenString(in, "Enter last name");

            String idNumber = String.format("%06d", idCounter);
            System.out.println("Assigned ID number: " + idNumber);
            idCounter++;

            String email = SafeInput.getRegExString(in, "Enter email address", "^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$");
            int yearOfBirth = SafeInput.getRangedInt(in, "Enter year of birth", 1900, 2026);

            String record = firstName + ", " + lastName + ", " + idNumber + ", " + email + ", " + yearOfBirth;
            records.add(record);

            addAnother = SafeInput.getYNConfirm(in, "Do you want to enter another record");

        } while (addAnother);

        String fileName = SafeInput.getNonZeroLenString(in, "Enter the file name (without extension)");
        if (!fileName.toLowerCase().endsWith(".csv")) {
            fileName += ".csv";
        }

        // Save in the src directory of the IntelliJ project
        File outFile = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + fileName);

        try {
            Files.write(outFile.toPath(), records, StandardCharsets.UTF_8);
            System.out.println("Saved " + records.size() + " record(s) to " + outFile.getPath());
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

}


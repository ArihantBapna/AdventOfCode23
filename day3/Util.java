import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;

public class Util {
    static boolean readFile(String fileName, List<String> linesList) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                linesList.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return true;
        }
        return false;
    }
}

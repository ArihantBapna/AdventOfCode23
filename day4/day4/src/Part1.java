import java.util.ArrayList;
import java.util.List;

public class Part1 {

    public static void main(String[] args) {
        // Read the file
        List<String> lines = new ArrayList<>();
        if (Util.readFile("src/input.txt", lines)) {
            System.out.println("Error reading file");
            return;
        }

        // Create a list of scratch cards
        List<ScratchCard> cards = new ArrayList<>();
        for (String line : lines) {
            cards.add(new ScratchCard(line));
        }

        // Print the cards
        int totalPoints = 0;
        for (ScratchCard card : cards) {
            totalPoints += card.points;
        }
        System.out.println("Total points: " + totalPoints);


    }
}
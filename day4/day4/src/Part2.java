import java.util.ArrayList;
import java.util.List;

public class Part2 {
    public static void main(String[] args) {
        // Read the file
        List<String> lines = new ArrayList<>();
        if (Util.readFile("src/input.txt", lines)) {
            System.out.println("Error reading file");
            return;
        }

        // Create a list of scratch cards
        List<ScratchCard_Part2> cards = new ArrayList<>();
        for (String line : lines) {
            cards.add(new ScratchCard_Part2(line));
        }

        // Iterate over each card
        for (int i=0; i<cards.size(); i++) {
            System.out.println("Progress: " + i + "/" + cards.size());
            ScratchCard_Part2 card = cards.get(i);
            card.reevaluatePoints();

            int points = card.points;

            for (int k=0; k<card.copies; k++){
                // Points is the number of copies we win of the next few cards
                for (int j=i+1; j<Math.min(i+points+1, cards.size()); j++) {
                    cards.get(j).copies++;
                }
            }
        }

        int total_copies = 0;
        for (ScratchCard_Part2 card : cards) {
            total_copies += card.copies;
        }
        System.out.println("Total copies: " + total_copies);
    }
}

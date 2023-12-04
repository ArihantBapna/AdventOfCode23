import java.util.ArrayList;
import java.util.List;

public class ScratchCard {
    // The numbers present on the card
    List<Integer> numbers;

    // The numbers that are winners for this card
    List<Integer> winningNumbers;

    // The id of the card
    int id;

    int points = 0;

    int copies = 1;

    public ScratchCard(String line) {
        // The line is of the form Card id: 1 2 3 ... n | 1 2 3 ... n
        // The numbers to the left of the | are the winning numbers
        // The numbers to the right of the | are the numbers on the card

        // First get the card id
        String[] parts = line.split(":");
        assert parts.length == 2;

        id = Integer.parseInt(parts[0].replaceAll("Card", "").trim());

        // Get the two sets of numbers
        String[] numbersStr = parts[1].split("\\|");
        assert numbersStr.length == 2;

        // Get the winning numbers
        String[] winningNumbersStr = numbersStr[0].trim().split(" ");
        winningNumbers = new ArrayList<>();
        for (String numStr : winningNumbersStr) {
            if (numStr.isEmpty()) {
                continue;
            }
            winningNumbers.add(Integer.parseInt(numStr));
        }

        // Get the numbers on the card
        String[] numbersOnCardStr = numbersStr[1].trim().split(" ");
        numbers = new ArrayList<>();
        for (String numStr : numbersOnCardStr) {
            if (numStr.isEmpty()) {
                continue;
            }
            numbers.add(Integer.parseInt(numStr));
        }

        reevaluatePoints();
    }


    public void reevaluatePoints() {
        points = 0;
        for (int num : numbers) {
            if (winningNumbers.contains(num)) {
                if (points == 0) {
                    points = 1;
                } else {
                    points *= 2;
                }
            }
        }
    }


    @Override
    public String toString() {
        return "Card " + id + ": " + numbers + " | " + winningNumbers;
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PartTwo {

    public static void main(String[] args) {
        String fileName = "input.txt";
        List<String> linesList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                linesList.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        int totalGearRatio = 0;
        for (int i = 0; i < linesList.size(); i++) {
            for (int j = 0; j < linesList.get(i).length(); j++) {
                if (linesList.get(i).charAt(j) == '*') {
                    List<NumberWithPosition> adjacentNumbers = getAdjacentNumbers(linesList, i, j);
                    if (adjacentNumbers.size() == 2) {
                        int gearRatio = Integer.parseInt(adjacentNumbers.get(0).number) * Integer.parseInt(adjacentNumbers.get(1).number);
                        System.out.println("Index: (" + (adjacentNumbers.get(0).start + "," +adjacentNumbers.get(0).end) + ") - (" +(adjacentNumbers.get(1).start + "," +adjacentNumbers.get(1).end) + "), Numbers: [" + adjacentNumbers.get(0).number + ", " + adjacentNumbers.get(1).number + "]");
                        totalGearRatio += gearRatio;
                    }
                }
            }
        }

        System.out.println("The total gear ratio is: " + totalGearRatio);
    }

    private static List<NumberWithPosition>  getAdjacentNumbers(List<String> lines, int row, int col) {
        List<NumberWithPosition> adjacentNumbers = new ArrayList<>();
        int[] dRow = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dCol = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int k = 0; k < dRow.length; k++) {
            int newRow = row + dRow[k];
            int newCol = col + dCol[k];

            if (newRow >= 0 && newRow < lines.size() && newCol >= 0 && newCol < lines.get(newRow).length() &&
                    !(newRow != row && Math.abs(newCol - col) > 1)) {
                char charAtPos = lines.get(newRow).charAt(newCol);
                if (Character.isDigit(charAtPos)) {
                    NumberWithPosition numberWithPosition = extractFullNumber(lines.get(newRow), newCol);
                    if (!adjacentNumbers.contains(numberWithPosition)) {
                        adjacentNumbers.add(numberWithPosition);
                    }

                }
            }
        }

        if (adjacentNumbers.size() == 2) {
            return adjacentNumbers;
        }

        return new ArrayList<>();
    }



    private static NumberWithPosition extractFullNumber(String line, int digitIndex) {
        int start = digitIndex;
        while (start > 0 && (Character.isDigit(line.charAt(start - 1)))) {
            start--;
        }

        int end = digitIndex + 1;
        while (end < line.length() && (Character.isDigit(line.charAt(end)))) {
            end++;
        }

        return new NumberWithPosition(line.substring(start, end), start, end);
    }
}
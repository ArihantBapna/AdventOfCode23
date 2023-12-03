import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartOne {

    public static void main(String[] args) {
        String fileName = "input.txt";
        List<String> linesList = new ArrayList<>();
        Pattern numberPattern = Pattern.compile("\\d+");
        List<Integer> numbersToAdd = new ArrayList<>();
        if (Util.readFile(fileName, linesList)) return;

        for (int i = 0; i < linesList.size(); i++) {
            String currentLine = linesList.get(i);
            Matcher matcher = numberPattern.matcher(currentLine);
            while (matcher.find()) {
                StringBuilder surroundingSymbols = getStringBuilder(matcher, i, linesList);

                System.out.println("Number: " + matcher.group() + ", Surrounding Symbols: " + surroundingSymbols);
                String symbols = surroundingSymbols.toString();
                symbols = symbols.replaceAll("\\.", "");
                if (!symbols.isBlank()){
                    numbersToAdd.add(Integer.parseInt(matcher.group()));
                }
            }
        }

        int sum = 0;
        for (Integer number : numbersToAdd) {
            sum += number;
        }
        System.out.println("The sum is: " + sum);
    }
    private static StringBuilder getStringBuilder(Matcher matcher, int i, List<String> linesList) {
        int start = matcher.start();
        int end = matcher.end();

        StringBuilder surroundingSymbols = new StringBuilder();

        for (int row = i - 1; row <= i + 1; row++) {
            for (int col = start - 1; col <= end; col++) {
                if (row >= 0 && row < linesList.size() && col >= 0 && col < linesList.get(row).length() && !(row == i && col >= start && col < end)) {
                    surroundingSymbols.append(linesList.get(row).charAt(col));
                }
            }
        }
        return surroundingSymbols;
    }
}

package Day04;

import java.util.*;

public class Da04_Scratchcards extends Puzzle {
    public static void main(String[] args) {
        Puzzle puzzle = new Da04_Scratchcards();
        puzzle.execute(args);
    }

    protected void solution(Scanner inputReader) {
        Set<Integer> winningNums;
        Set<Integer> ourNums;
        String line;
        long sumP1 = 0;
        int sumP2 = 0;
        List<Integer> coppies = new ArrayList<>();
        int card = 0;
        while (inputReader.hasNextLine()) {
            card++;
            if(coppies.size() < card)
                coppies.add(1);
            line = inputReader.nextLine();
            line = line.substring(line.indexOf(":") + 2)
                    .replace("  ", " ").trim().replace(" | ", "|");
            winningNums = extractNumbers(line.substring(0, line.indexOf("|")).trim());
            ourNums = extractNumbers(line.substring(line.indexOf("|") + 1).trim());
            int matches = calculateMatches(winningNums, ourNums);
            for(int i=0; i < matches; i++) {
                if(coppies.size() <= card + i) {
                    coppies.add(1 + coppies.get(card-1));
                } else {
                    coppies.set(card+i, coppies.get(card+i) + coppies.get(card-1));
                }
            }
            sumP1 += Math.pow(2, matches - 1);
            sumP2 += coppies.get(card-1);
        }

        setAnswerPartOne(String.valueOf(sumP1));
        setAnswerPartTwo(String.valueOf(sumP2));
    }

    private Set<Integer> extractNumbers(String str) {
        Set<Integer> nums = new HashSet<>();
        String[] numStr = str.split(" ", -1);
        for (String s : numStr)
            nums.add(Integer.parseInt(s));
        return nums;
    }

    private int calculateMatches(Set<Integer> winning, Set<Integer> ours) {
        int count = 0;
        for(int num : winning)
            if(ours.contains(num))
                count++;
        return count;
    }
}

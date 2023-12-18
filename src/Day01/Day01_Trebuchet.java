package Day01;

import java.util.*;

public class Day01_Trebuchet extends Puzzle {

    public static void main(String[] args) {
        Puzzle puzzle = new Day01_Trebuchet();
        puzzle.execute(args);
    }

    protected void solution(Scanner inputReader) {
        int first_p1, last_p1, first_p2, last_p2;
        int sum = 0;
        int sum2 = 0;
        String line;
        while (inputReader.hasNextLine()) {
            line = inputReader.nextLine();
            first_p1 = -1; last_p1 = -1;
            first_p2 = -1; last_p2 = -1;
            for(int i = 0; i < line.length(); i++) {
                last_p1 = getDigit_Part1(line.substring(i));
                last_p2 = getDigit_Part2(line.substring(i));
                if(last_p2 > -1 && first_p2 < 0)
                    first_p2 = last_p2;
                if(last_p1 > -1 && first_p1 < 0)
                    first_p1 = last_p1;
            }
            //TODO - CHECK RESULTS
            sum += (first_p1*10 + last_p1);
            sum2 += (first_p2*10 + last_p2);
        }

        this.setAnswerPartOne(String.valueOf(sum));
        this.setAnswerPartTwo(String.valueOf(sum2));
    }


    private int getDigit_Part1(String str) {
        try {
            return Integer.parseInt(String.valueOf(str.charAt(0)));
        } catch (Exception e) {
            return -1;
        }
    }
    private int getDigit_Part2(String str) {
        if(str.startsWith("one"))
            return 1;
        else if(str.startsWith("two"))
            return 2;
        else if(str.startsWith("three"))
            return 3;
        else if(str.startsWith("four"))
            return 4;
        else if(str.startsWith("five"))
            return 5;
        else if(str.startsWith("six"))
            return 6;
        else if(str.startsWith("seven"))
            return 7;
        else if(str.startsWith("eight"))
            return 8;
        else if(str.startsWith("nine"))
            return 9;
        else return getDigit_Part1(str);
    }
}


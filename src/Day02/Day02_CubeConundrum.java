package Day02;

import Base.Puzzle;
import java.util.Scanner;

public class Day02_CubeConundrum extends Puzzle {
    final static int[] content = new int[] {12, 13, 14};
    public static void main(String[] args) {
        Puzzle puzzle = new Day02_CubeConundrum();
        puzzle.execute(args);
    }

    protected void solution(Scanner inputReader) {
        String line;
        int gamesSum_p1 = 0;
        int gamesSum_p2 = 0;
        while (inputReader.hasNextLine()) {
            line = inputReader.nextLine().replace("Game ", "");
            int game = Integer.parseInt(line.substring(0, line.indexOf(":")));
            line = line.substring(line.indexOf(":") + 2);
            if(isAllFitting_Part1(line.split(" ")))
                gamesSum_p1 += game;
            gamesSum_p2 += getSetsPower(line.split(" "));
        }

        this.setAnswerPartOne(String.valueOf(gamesSum_p1));
        this.setAnswerPartOne(String.valueOf(gamesSum_p2));
    }

    boolean isAllFitting_Part1(String[] sets) {
        for(int i = 0; i < sets.length; i+=2) {
            int num = Integer.parseInt(sets[i]);
            String color = sets[i + 1];

            if (color.startsWith("red") && (num > content[0]))
                return false;
            else if (color.startsWith("green") && (num > content[1]))
                return false;
            else if (color.startsWith("blue") && (num > content[2]))
                return false;
        }

        return true;
    }

    int getSetsPower(String[] sets) {
        int[] minimum = new int[] {0, 0, 0};
        for(int i = 0; i < sets.length; i+=2) {
            int num = Integer.parseInt(sets[i]);
            String color = sets[i + 1];
            if (color.startsWith("red") && (num > minimum[0]))
                minimum[0] = num;
            else if (color.startsWith("green") && (num > minimum[1]))
                minimum[1] = num;
            else if (color.startsWith("blue") && (num > minimum[2]))
                minimum[2] = num;
        }

        return minimum[0] * minimum[1] * minimum[2];
    }
}
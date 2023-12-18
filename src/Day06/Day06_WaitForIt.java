package Day06;

import java.util.Arrays;
import java.util.Scanner;

public class Day06_WaitForIt extends Puzzle {
    public static void main(String[] args) {
        Puzzle puzzle = new Day06_WaitForIt();
        puzzle.execute(args);
    }

    protected void solution(Scanner inputReader) {
        String line;
        String[] time = Arrays.stream(inputReader.nextLine().replace("Time:","").trim()
                .split(" ")).filter(e -> !e.trim().isEmpty()).toArray(String[]::new);
        long timeP2 = Long.parseLong(String.join("", time));
        String[] distance = Arrays.stream(inputReader.nextLine().replace("Distance:","").trim()
                .split(" ")).filter(e -> !e.trim().isEmpty()).toArray(String[]::new);
        long distanceP2 = Long.parseLong(String.join("", distance));
        int raceNum = time.length;
        Integer[] ways = new Integer[raceNum];
        for(int i=0; i<raceNum; i++) {
            int tt = Integer.parseInt(time[i]);
            int dd = Integer.parseInt(distance[i]);
            ways[i] = 0;
            for(int j = 1; j < tt; j++) {
                if(j * (tt - j) > dd) {
                    ways[i] = tt - j - j + 1;
                    break;
                }
            }
        }

        long prodP1 = 1;
        for(int i : ways)
            prodP1 *= i;
        setAnswerPartOne(String.valueOf(prodP1));

        for(int j = 1; j < timeP2; j++) {
            if(j * (timeP2 - j) > distanceP2) {
                setAnswerPartTwo(String.valueOf(timeP2 - j - j + 1));
                break;
            }
        }
    }
}

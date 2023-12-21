package Day08;

import Base.Puzzle;
import java.util.*;

public class Day08_HauntedWasteland extends Puzzle {
    public static void main(String[] args) {
        Puzzle puzzle = new Day08_HauntedWasteland();
        puzzle.execute(args);
    }

    protected void solution(Scanner inputReader) {
        char[] path = inputReader.nextLine().toCharArray();
        inputReader.nextLine();
        Map<String, String[]> map = new HashMap<>();
        String line;
        List<String> current = new ArrayList<>();
        while (inputReader.hasNextLine()) {
            line = inputReader.nextLine().replace(" = (", ", ")
                    .replace(")", "");
            String[] piece = line.split(", ");
            map.put(piece[0], piece);
            if(piece[0].endsWith("A"))
                current.add(piece[0]);
        }

        int step = 0;
        int pathL = path.length;
        long commonSteps = 1;
        for(String c : current) {
            while (!c.endsWith("Z")) {
                c = path[(int) (step%pathL)] == 'L' ?
                        map.get(c)[1] : map.get(c)[2];
                step++;
            }
            commonSteps = lcm(commonSteps, step);
            step = 0;
        }

        setAnswerPartOne(String.valueOf(commonSteps));
        setAnswerPartOne(String.valueOf(commonSteps));
    }
    public static long lcm(long number1, long number2) {
        if (number1 == 0 || number2 == 0) {
            return 0;
        }
        long absNumber1 = Math.abs(number1);
        long absNumber2 = Math.abs(number2);
        long absHigherNumber = Math.max(absNumber1, absNumber2);
        long absLowerNumber = Math.min(absNumber1, absNumber2);
        long lcm = absHigherNumber;
        while (lcm % absLowerNumber != 0) {
            lcm += absHigherNumber;
        }
        return lcm;
    }
}

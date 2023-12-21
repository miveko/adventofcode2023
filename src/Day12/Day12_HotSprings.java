package Day12;

import Base.Puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day12_HotSprings extends Puzzle {
    List<String> codes;
    List<List<Integer>> schemes;
    int numArrs;
    public static void main(String[] args) {
        Puzzle puzzle = new Day12_HotSprings();
        puzzle.execute(args);
    }

    protected void solution(Scanner inputReader) {
        codes = new ArrayList<>();
        schemes = new ArrayList<>();
        while (inputReader.hasNextLine()) {
            codes.add(inputReader.next());
            String s = inputReader.nextLine();
            List<Integer> a = Arrays.stream(s.trim().split(","))
                    .map(el -> Integer.parseInt(el)).collect(Collectors.toList());
            schemes.add(a);
        }


        numArrs = 0;
        for(int i=0; i<codes.size(); i++) {
            calcArrangements(codes.get(i), schemes.get(i));
        }
    }

    private void calcArrangements(String code, List<Integer> scheme) {
        int indxLastQ = code.lastIndexOf("?");
        if(indxLastQ < 0) {
            if(areMatching(code, scheme)) {
                numArrs++;
                //System.out.println(code + " : " + scheme);
            }
            return;
        } else {
            int indxLastDot = code.lastIndexOf('.', indxLastQ);
        }

        int indQuestion = code.indexOf("?");
    }

    private boolean areMatching(String code, List<Integer> scheme) {
        return false;
    }
}

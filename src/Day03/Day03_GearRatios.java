package Day03;

import java.awt.*;
import java.util.*;
import java.util.List;
import static java.lang.Character.isDigit;
import Base.Puzzle;

public class Day03_GearRatios extends Puzzle {
    int height, width;
    List<String> schematic;
    boolean[][] covered;
    Map<Point, List<Integer>> gear;

    public static void main(String[] args) {
        Puzzle puzzle = new Day03_GearRatios();
        puzzle.execute(args);
    }

    protected void solution(Scanner inputReader) {
        schematic = new ArrayList<>();
        while (inputReader.hasNextLine()) {
            schematic.add(inputReader.nextLine());
        }

        height = schematic.size();
        width = schematic.get(0).length();

        setAnswerPartOne(String.valueOf(getSumOfThePartNumbers()));
        setAnswerPartTwo(String.valueOf(getSumOfGearRatios()));
    }

    private long getSumOfThePartNumbers() {
        covered = new boolean[height][width];
        for(int i = 0; i < height; i++)
            for(int j = 0; j < width; j++)
            {
                char c = schematic.get(i).charAt(j);
                if(c != '.' && !isDigit(c)) {
                    for(int k=i-1; k<i+2; k++)
                        for(int l=j-1; l<j+2; l++)
                            if(k >= 0 && k < height && l>=0 && l<width)
                                covered[k][l] = true;
                }
            }

        boolean isPartNumber = false;
        boolean numberStarted = false;
        StringBuilder strB = new StringBuilder();
        int sumP1 = 0;
        for(int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                char c = schematic.get(i).charAt(j);
                if(!isDigit(c)) {
                    if(numberStarted) {
                        int num = Integer.parseInt(strB.toString());
                        strB = new StringBuilder();
                        numberStarted = false;
                        if (isPartNumber) {
                            sumP1 += num;
                            isPartNumber = false;
                        }
                    }
                } else {
                    strB.append(c);
                    numberStarted = true;
                    if(covered[i][j])
                        isPartNumber = true;
                }
            }
        return sumP1;
    }
    private long getSumOfGearRatios() {
        covered = new boolean[height][width];
        gear = new HashMap<>();
        for(int i = 0; i < height; i++)
            for(int j = 0; j < width; j++)
            {
                char c = schematic.get(i).charAt(j);
                if(c == '*') {
                    gear.put(new Point(i, j), new ArrayList<>());
                }
            }

        Set<Point> adjacentGears = new HashSet<>();
        boolean numberStarted = false;
        StringBuilder strB = new StringBuilder();
        for(int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                char c = schematic.get(i).charAt(j);
                if(!isDigit(c)) {
                    if(numberStarted) {
                        int num = Integer.parseInt(strB.toString());
                        strB = new StringBuilder();
                        numberStarted = false;
                        if (adjacentGears.size() > 0) {
                            for(Point p : adjacentGears)
                                gear.get(p).add(num);
                            adjacentGears = new HashSet<>();
                        }
                    }
                } else {
                    strB.append(c);
                    numberStarted = true;
                    AddAdjacentGears(i,j, adjacentGears);
                }
            }

        return CalculateGearRatiosSum(gear);
    }

    private void AddAdjacentGears(int x, int y, Set<Point> gearsSet) {
        for(int k=x-1; k<x+2; k++)
            for(int l=y-1; l<y+2; l++) {
                if(gear.containsKey(new Point(k, l)))
                    gearsSet.add(new Point(k, l));
            }
    }

    private long CalculateGearRatiosSum(Map<Point, List<Integer>> gears) {
        long sum = 0;
        for(Point p : gears.keySet()) {
            if(gears.get(p).size() == 2)
                sum += gears.get(p).get(0) * gears.get(p).get(1);
        }
        return sum;
    }
}

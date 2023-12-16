package Day11;

import Base.Puzzle;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day11_CosmicExpansion extends Puzzle {
    List<List<Character>> map;
    List<Integer> emptyIs, emptyJs;
    public static void main(String[] args) {
        Puzzle puzzle = new Day11_CosmicExpansion();
        puzzle.execute(args);
    }

    protected void solution(Scanner inputReader) {
        map = new ArrayList<>();
        while (inputReader.hasNext())
            map.add(inputReader.nextLine().chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        //printMap();
        emptyIs = getEmptyRows();
        //printMap();
        emptyJs = getEmptyColumns();
        //printMap();
        List<Point> point = extractSharps();
        setAnswerPartOne(String.valueOf(calcDistSum(point, 2)));
        setAnswerPartTwo(String.valueOf(calcDistSum(point, 1000000)));
    }

    private long calcDistSum(List<Point> ps, int multiplier) {
        long sum = 0;
        for(int i=0; i<ps.size(); i++)
            for(int j=i+1; j<ps.size(); j++)
                sum += calcDist(ps.get(i), ps.get(j), multiplier);
        return sum;
    }

    private long calcDist(Point a, Point b, int multplier) {
        long dist = 0;
        for(int i : emptyIs)
            if(Math.min(a.x, b.x) < i && Math.max(a.x, b.x) > i)
                dist += multplier - 1;
        for(int j : emptyJs)
            if(Math.min(a.y, b.y) < j && Math.max(a.y, b.y) > j)
                dist += multplier - 1;
        return dist + (Math.abs(a.x - b.x) + Math.abs(a.y - b.y));
    }
    private List<Point> extractSharps() {
        List<Point> ps = new ArrayList<>();
        for(int i=0; i<map.size(); i++)
            for(int j=0; j<map.get(i).size();j++)
                if(map.get(i).get(j) == '#')
                    ps.add(new Point(i,j));
        return ps;
    }
    private List<Integer> getEmptyColumns() {
        List<Integer> emptyColumns = new ArrayList<>();
        for(int j=0; j<map.get(0).size(); j++) {
            boolean noSharp = true;
            for (List<Character> characters : map)
                if (characters.get(j) == '#') {
                    noSharp = false;
                    break;
                }

            if(noSharp)
                emptyColumns.add(j);
        }
        return emptyColumns;
    }

    private List<Integer> getEmptyRows() {
        List<Integer> emptyRow = new ArrayList<>();
        for(int i=0; i<map.size(); i++)
            if(!String.valueOf(map.get(i)).contains("#"))
                emptyRow.add(i);
        return emptyRow;
    }

}

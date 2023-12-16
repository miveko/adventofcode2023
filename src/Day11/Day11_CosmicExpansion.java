package Day11;

import Base.Puzzle;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day11_CosmicExpansion extends Puzzle {
    List<List<Character>> map;
    public static void main(String[] args) {
        Puzzle puzzle = new Day11_CosmicExpansion();
        puzzle.execute(args);
    }

    protected void solution(Scanner inputReader) {
        map = new ArrayList<>();
        while (inputReader.hasNext())
            map.add(inputReader.nextLine().chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        //printMap();
        expandWidth();
        //printMap();
        expandHeight();
        //printMap();
        List<Point> point = extractSharps();
        long sum = calcDistSum(point);
        setAnswerPartOne(String.valueOf(sum));
    }

    private long calcDistSum(List<Point> ps) {
        long sum = 0;
        for(int i=0; i<ps.size(); i++)
            for(int j=i+1; j<ps.size(); j++)
                sum += calcDist(ps.get(i), ps.get(j));
        return sum;
    }

    private int calcDist(Point a, Point b) {
        return (Math.abs(a.x - b.x) + Math.abs(a.y - b.y));
    }
    private List<Point> extractSharps() {
        List<Point> ps = new ArrayList<>();
        for(int i=0; i<map.size(); i++)
            for(int j=0; j<map.get(i).size();j++)
                if(map.get(i).get(j) == '#')
                    ps.add(new Point(i,j));
        return ps;
    }
    private void expandWidth() {
        for(int j=0; j<map.get(0).size(); j++) {
            boolean noSharp = true;
            for(int i=0; i<map.size(); i++) {
                if(map.get(i).get(j) == '#') {
                    noSharp = false;
                    break;
                }
            }

            if(noSharp) {
                for(int i=0; i<map.size(); i++)
                    map.get(i).add(j, '.');
                j++;
            }
        }
    }

    private void expandHeight() {
        for(int i=0; i<map.size(); i++) {
            if(!String.valueOf(map.get(i)).contains("#")) {
                map.add(i+1, map.get(i));
                i++;
            }
        }
    }

    private void printMap() {
        for(List<Character> l : map )
            System.out.println(l);
        System.out.println();
        System.out.println();
    }
}

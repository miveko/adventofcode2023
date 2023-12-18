package Day10;

import java.awt.*;
import java.util.Scanner;

public class Day10_PipeMaze extends Puzzle {
    char[][] labirint;
    Point sPos, last, next;
    public static void main(String[] args) {

        Puzzle puzzle = new Day10_PipeMaze();
        puzzle.execute(args);
    }

    protected void solution(Scanner inputReader) {
        String line = inputReader.nextLine();
        labirint = new char[line.length()][line.length()];
        int size = 0;
        do {
            labirint[size] = line.toCharArray();
            if(line.contains("S"))
                sPos = new Point(size, line.indexOf("S"));
            size++;
            line = inputReader.nextLine();
        } while (line.length() > 2);


        setAnswerPartOne(String.valueOf(findPathLength_p1()));
    }

    private int findPathLength_p1() {
        int pathLength = 1;
        last = new Point(sPos);
        next = findNextPosition(sPos);
        char current = labirint[next.x][next.y];
        while (next.equals(sPos)) {
            Point currPos = next;
            next = findNext(current, last);
            pathLength++;
            last = currPos;
        }

        return  (pathLength) / 2;
    }

    private Point findNextPosition(Point s) {
        if(this.labirint[s.y][s.x+1] == 'J' || labirint[s.y][s.x+1] == '-' || labirint[s.y][s.x+1] == '7')
            return new Point(s.x+1, s.y);
        if(labirint[s.y][s.x-1] == 'L' || labirint[s.y][s.x+1] == 'F')
            return new Point(s.x-1, s.y);
        return new Point(s.x, s.y-1);
    }

    private Point findNext(char c, Point l) {
        Point next = new Point(l.x, l.y-1);
        if((c == 'L' || c == '|' || c == 'J') && (!l.equals(next)))
            return next;
        next.y -= 2;
        if((c == '|' || c == '7' || c == 'F') && (!l.equals(next)))
            return next;
        next.y++;
        next.x--;
        if((c == '-' || c == 'J' || c == '7') && (!l.equals(next)))
            return next;
        next.x += 2;
        return next;
    }
}

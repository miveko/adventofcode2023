package Day10;

import Base.Puzzle;

import java.awt.*;
import java.util.Scanner;

public class Day10_PipeMaze extends Puzzle {
    char[][] labirint, lab2;
    int sizeX, sizeY;
    Point sPos, prev, next;
    public static void main(String[] args) {
        Puzzle puzzle = new Day10_PipeMaze();
        puzzle.execute(args);
    }

    protected void solution(Scanner inputReader) {
        String line = inputReader.nextLine();
        sizeY = line.length();
        labirint = new char[sizeY][sizeY];
        lab2 = new char[sizeY][sizeY];
        sizeX = 0;
        do {
            labirint[sizeX] = line.toCharArray();
            if(line.contains("S"))
                sPos = new Point(sizeX, line.indexOf("S"));
            sizeX++;
            line = inputReader.nextLine();
        } while (line.length() > 2);

        setAnswerPartOne(String.valueOf(findPathLength_p1()));

        String dirs = "J7LF|-";
        for(int i = 0; i< sizeX; i++)
            for(int j=0;j<sizeY;j++) {
                if(dirs.indexOf(lab2[i][j]) == -1)
                    lab2[i][j] = ' ';
            }

        lab2[sPos.x][sPos.y] = findS();

        setAnswerPartTwo(String.valueOf(countINs()));
    }

    private int findPathLength_p1() {
        int pathLength = 1;
        prev = new Point(sPos);
        next = findNextPosition(sPos);
        while (!next.equals(sPos)) {
            Point currPos = next;
            char current = labirint[next.x][next.y];
            lab2[next.x][next.y] = labirint[next.x][next.y]; //needed in part two
            next = findNext(current, currPos, prev);
            pathLength++;
            prev = currPos;
        }

         return  (pathLength) / 2;
    }

    private Point findNextPosition(Point s) {
        if(this.labirint[s.x][s.y+1] == 'J' || labirint[s.x][s.y+1] == '-' || labirint[s.x][s.y+1] == '7')
            return new Point(s.x, s.y+1);
        if(labirint[s.x][s.y-1] == 'L' || labirint[s.x][s.y+1] == 'F')
            return new Point(s.x, s.y-1);
        return new Point(s.x+1, s.y);
    }

    private Point findNext(char c, Point last, Point prev) {
        Point next = new Point(last.x - 1, last.y);
        if((c == 'L' || c == '|' || c == 'J') && (!prev.equals(next)) && next.x >= 0)
            return next;
        next.x += 2;
        if((c == '|' || c == '7' || c == 'F') && (!prev.equals(next)) && next.x < sizeX)
            return next;
        next.x--;
        next.y++;
        if((c == '-' || c == 'L' || c == 'F') && (!prev.equals(next)) && next.y < sizeY)
            return next;
        next.y -= 2;
        return next;
    }

    private char findS() {
        if(lab2[sPos.x][sPos.y + 1] == ' ') {
            if(lab2[sPos.x + 1][sPos.y +1] == ' ')
                return 'J';
            else if(lab2[sPos.x][sPos.y -1] == ' ')
                    return '|';
            else return '7';
        } else if(lab2[sPos.x + 1][sPos.y +1] != ' ')
            return 'F';
        else if(lab2[sPos.x][sPos.y -1] != ' ')
            return '_';
        else return 'L';
    }

    private int countINs() {
        int count = 0;
        String crossHorz = "|LJ";
        for(int i = 0; i< sizeX; i++) {
            boolean isIn = false;
            for (int j = 0; j < sizeY; j++) {
                if (crossHorz.indexOf(lab2[i][j]) > -1)
                    isIn = !isIn;
                else if(lab2[i][j] == ' ' && isIn)
                    count++;
            }
        }
        return count;
    }
}

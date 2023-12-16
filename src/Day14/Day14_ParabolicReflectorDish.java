package Day14;

import Base.Puzzle;

import java.awt.*;
import java.io.Console;
import java.util.*;
import java.util.List;

public class Day14_ParabolicReflectorDish extends Puzzle {

    char[][] map;
    int size;
    public static void main(String[] args) {
        Puzzle puzzle = new Day14_ParabolicReflectorDish();
        puzzle.execute(args);
    }

    protected void solution(Scanner inputReader) {
        String line = inputReader.nextLine();
        size = line.length();
        map = new char[size][size];
        int row = 0;
        map[row] = line.toCharArray();
        while(inputReader.hasNext()) {
            row++;
            line = inputReader.nextLine();
            map[row] = line.toCharArray();
        }

        setAnswerPartOne(String.valueOf(findTotalLoad_p1_1()));
        setAnswerPartOne(String.valueOf(findTotalLoad_p1_2()));
     }

     private long findTotalLoad_p1_2() {
        long totalLoad = 0;
        for(int j=0; j<size;j++) {
            int sharpPos = -1;
            int numOs = 0;
            for (int i = 0; i < size; i++) {
                if (map[i][j] == 'O') {
                    numOs++;
                } else if (map[i][j] == '#') {
                    int strt = size - sharpPos - 1;
                    int sum = (strt + strt - numOs + 1) * numOs / 2;
                    totalLoad += sum;
                    sharpPos = i;
                    numOs = 0;
                }
            }

            int strt = size - sharpPos - 1;
            int sum = (strt + strt - numOs + 1) * numOs / 2;
            totalLoad += sum;
        }
        return totalLoad;
     }

     private long findTotalLoad_p1_1() {
         //   printMap();
         for(int i = 1; i < size; i++)
             for (int j = 0; j < size; j++) {
                 int k = i-1;
                 if(map[i][j] == 'O')
                     while (k > -1 && map[k][j] == '.') {
                         map[k][j] = 'O';
                         map[k+1][j] = '.';
                         k--;
                     }
             }
         //printMap();
         long totalLoad = 0;
         for(int i = 0; i < size; i++)
             for (int j = 0; j < size; j++)
                 if(map[i][j] == 'O')
                     totalLoad += size - i;
         return totalLoad;
     }

     private  void printMap () {
         for(int i = 0; i < size; i++) {
             for (int j = 0; j < size; j++)
                 System.out.print(map[i][j]);
             System.out.println();
         }

         System.out.println();
         System.out.println();
     }
}

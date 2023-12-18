package Day09;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day09_MirageMaintenance extends Puzzle {
    public static void main(String[] args) {
        Puzzle puzzle = new Day09_MirageMaintenance();
        puzzle.execute(args);
    }

    protected void solution(Scanner inputReader) {
        String[] line;
        long sum_p1 = 0;
        long sum_p2 = 0;
        while (inputReader.hasNextLine()){
            line = inputReader.nextLine().split(" ");
            sum_p1 += getValueFromLine_p1(line);
            sum_p2 += getValueFromLine_p2(line);
        }

        setAnswerPartOne(String.valueOf(sum_p1));
        setAnswerPartTwo(String.valueOf(sum_p2));
    }

    private long getValueFromLine_p1(String[] line) {
        List<Long> nums = new ArrayList<>();
        for(String s : line)
            nums.add(Long.parseLong(s));

        boolean allZeros = false;
        int size = nums.size();
        while (!allZeros && size > 1) {
            for(int i = 0; i < size-1; i++) {
                nums.set(i, nums.get(i+1) - nums.get(i));
            }
            size--;
            allZeros = true;
            for(int i = 0; i < size; i++) {
                if (nums.get(i) != 0) {
                    allZeros = false;
                    break;
                }
            }
        }
        //Adding zero at the end (extrapolate
        long sum = 0;
        for(int i = size - 1; i<nums.size(); i++)
            sum += nums.get(i);
        return sum;
    }
    private long getValueFromLine_p2(String[] line) {
        List<Long> nums = new ArrayList<>();
        for(String s : line)
            nums.add(Long.parseLong(s));

        boolean allZeros = false;
        int frst = 0;
        while (!allZeros && frst < nums.size() - 1) {
            for(int i = nums.size() - 1; i > frst; i--) {
                nums.set(i, nums.get(i) - nums.get(i-1));
            }
            frst++;
            allZeros = true;
            for(int i = nums.size() - 1; i > frst; i--) {
                if (nums.get(i) != 0) {
                    allZeros = false;
                    break;
                }
            }
        }

        long sum = 0;
        for(int i = 0; i<frst; i++)
            sum += (long) ((Math.pow(-1, i)) * nums.get(i));
        return sum;
    }
}

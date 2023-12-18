package Day05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day05_IfYouGiveASeedAFertilizer extends Puzzle {
    List<Long> seed;
    List<Long[]>[] maps = new ArrayList[7];
    public static void main(String[] args) {
        Puzzle puzzle = new Day05_IfYouGiveASeedAFertilizer();
        puzzle.execute(args);
    }

    protected void solution(Scanner inputReader) {
        int mapIndx = -1;
        String line;
        seed = new ArrayList<>();
        String[] numStr = inputReader.nextLine().replace("seeds: ", "").split(" ");
        for(String s : numStr)
            seed.add(Long.parseLong(s));
        inputReader.nextLine();
        while (inputReader.hasNextLine()) {
            line = inputReader.nextLine();
            if(line.contains("map")) {
                mapIndx++;
                maps[mapIndx] = new ArrayList<>();
            } else if(line.length() > 5) {
                long destination = Long.parseLong(line.split(" ")[0]);
                long source = Long.parseLong(line.split(" ")[1]);
                long range = Long.parseLong(line.split(" ")[2]);
                Long[] mapItem = new Long[3];
                mapItem[0] = source;
                mapItem[1] = source + range - 1;
                mapItem[2] = destination - source;
                maps[mapIndx].add(mapItem);
                //TODO Do sorting of the mapItems - now part 2 ends in 414 secs
            }
        }
        setAnswerPartOne(String.valueOf(getMinSeed_part1()));
        setAnswerPartTwo(String.valueOf(getMinSeed_part2()));
    }

    private long getMinSeed_part1() {
        long sMin = Long.MAX_VALUE;
        for(long l : seed) {
            long loc = ExecuteMap(maps, l);
            if(loc < sMin)
                sMin = loc;
        }
        return sMin;
    }

    private long getMinSeed_part2() {

        //Transform from start-range-start-range-start...
        //            to start-end-start-end-start...
        for(int i=0; i<seed.size(); i+=2)
            seed.set(i+1,  seed.get(i) + seed.get(i+1));
        //optimize seed intervals
        for(int i=2; i<seed.size(); i+=2)
            for(int j=0; j<i; j+=2)
                if(seed.get(i) < seed.get(j+1) && seed.get(i) > seed.get(j) && seed.get(i+1) > seed.get(j+1)) {
                    seed.set(j+1, seed.get(i+1));
                    seed.remove(i); seed.remove(i+1);
                    i -= 2; j -= 2;
                } else if(seed.get(i+1) < seed.get(j+1) && seed.get(i+1) > seed.get(j) && seed.get(i) < seed.get(j)) {
                    seed.set(j, seed.get(i));
                    seed.remove(i); seed.remove(i+1);
                    i -=2; j-=2;
                }

        //Find the minimum seed location
        long minSeed = Long.MAX_VALUE;
        for(int i=0; i<seed.size(); i+=2)
            for(long k=seed.get(i); k<seed.get(i+1);k++) {
                long ss = ExecuteMap(maps, k);
                if(ss < minSeed)
                    minSeed = ss;
            }
        return minSeed;
    }

    private long ExecuteMap(List<Long[]>[] map, long seed) {
        for(int i=0; i<7; i++)
            for (Long[] mapItem : map[i]) {
                if (seed >= mapItem[0] && seed <= mapItem[1]) {
                    seed += mapItem[2];
                    break;
                }
            }
        return seed;
    }
}

package Day07;

import java.util.*;

public class Day07_CamelCards extends Puzzle {
    public static void main(String[] args) {
        Puzzle puzzle = new Day07_CamelCards();
        puzzle.execute(args);
    }

    protected void solution(Scanner inputReader) {
        String line;
        List<Pair> game_p1 = new ArrayList<>();
        List<Pair> game_p2 = new ArrayList<>();
        while (inputReader.hasNextLine()) {
            String hand = replaceSymbols(inputReader.next());
            int bid = Integer.parseInt(inputReader.next());
            game_p1.add(new Pair(addValue_p1(hand), bid));
            game_p2.add(new Pair(addValue_p2(hand), bid));
        }

        setAnswerPartOne(String.valueOf(calcScore(game_p1)));
        setAnswerPartOne(String.valueOf(calcScore(game_p2)));
    }

    private String replaceSymbols(String hand) {
        //A, K, Q, J, T, 9, 8, 7, 6, 5, 4, 3, or 2
        //E, D, C, B, A, 9, 8, 7, 6, 5, 4, 3, or 2
        return hand.replace("A", "E").replace("K", "D")
                .replace("Q", "C").replace("J", "B")
                .replace("T","A");
    }

    private String addValue_p1(String hand) {
        StringBuilder str = new StringBuilder(hand);
        int value = 0;
        while (str.length() > 0) {
            int r = 0;
            char c = str.charAt(0);
            for(int i = 1; i<str.length(); i++) {
                if(c == str.charAt(i)) {
                    r++;
                    str.deleteCharAt(i);
                    i--;
                }
            }
            str.deleteCharAt(0);
            value += Math.pow(3, r);
        }
        //Five, Four,   FullHouse,    Three, TwoPairs,   OnePair,    High
        //L,    K,      J,            I,     H,          G,          F
        switch (value) {
            case 5: return 'F' + hand;
            case 6: return 'G' + hand;
            case 7: return 'H' + hand;
            case 11: return 'I'+ hand;
            case 12: return 'J'+ hand;
            case 28: return 'K'+ hand;
            case 81: return 'L'+ hand;
            default: return "FuCK";
        }
    }
    private String addValue_p2(String hand) {
        hand = hand.replace("B", "1");
        StringBuilder str = new StringBuilder(hand.replace("1", ""));
        int value = 0;
        int num1s = 5 - str.length();
        while (str.length() > 0) {
            int r = 0;
            char c = str.charAt(0);
            for(int i = 1; i<str.length(); i++) {
                if(c == str.charAt(i)) {
                    r++;
                    str.deleteCharAt(i);
                    i--;
                }
            }
            str.deleteCharAt(0);
            value += Math.pow(3, r);
        }

        char addCh;
        //Five, Four,   FullHouse,    Three, TwoPairs,   OnePair,    High
        //L,    K,      J,            I,     H,          G,          F
        if((value == 0) || (value == 1) || (value == 3 && num1s == 3) || (value == 9) || (value == 27) || (value == 81))
            addCh = 'L';
        else if((value == 2) || (value == 4 && num1s == 2) || (value == 28) || (value == 10))
            addCh = 'K';
        else if((value == 4 && num1s == 1) || (value == 6 && num1s == 0))
            addCh = 'G';  // 1 x 1s and 1 x other and 1 x other and 1 x other and 1 x other;
        else if(value == 5 && num1s == 0)
            addCh = 'F';
        else if((value == 5 && num1s == 1) || (value == 3 && num1s == 2) || (value == 11))
            addCh = 'I';
        else if((value == 6 && num1s == 1) || (value == 12))
            addCh = 'J';
        else if(value == 7)
            addCh = 'H';
        else addCh = 'F';

        return addCh + hand;
    }

    private long calcScore(List<Pair> game) {
        game.sort(Comparator.comparing(p -> p.hand));
        long score = 0;
        for(int i = 0; i<game.size();i++) {
            score += (i + 1) * game.get(i).bid;
        }
        return score;
    }
}

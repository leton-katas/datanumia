package org.codingdojo.yatzy1;

import java.util.Arrays;

public class Yatzy1 {

    private final int[] counts;
    private final int[] dice;

    public Yatzy1(int d1, int d2, int d3, int d4, int d5) {
        dice = new int[5];
        dice[0] = d1;
        dice[1] = d2;
        dice[2] = d3;
        dice[3] = d4;
        dice[4] = d5;
        counts = countsOccurrenceOfDiceNumberInTheRoll();
    }

    public int largeStraight() {

        if (counts[1] == 1 &&
            counts[2] == 1 &&
            counts[3] == 1 &&
            counts[4] == 1
            && counts[5] == 1) {
            return 20;
        }
        return 0;
    }

    public int fullHouse() {
        boolean _2 = false;
        int i;
        int _2_at = 0;
        boolean _3 = false;
        int _3_at = 0;

        for (i = 0; i != 6; i += 1) {
            if (counts[i] == 2) {
                _2 = true;
                _2_at = i + 1;
            }
        }

        for (i = 0; i != 6; i += 1) {
            if (counts[i] == 3) {
                _3 = true;
                _3_at = i + 1;
            }
        }

        if (_2 && _3) {
            return _2_at * 2 + _3_at * 3;
        }

        return 0;
    }

    public int smallStraight() {
        if (counts[0] == 1 &&
            counts[1] == 1 &&
            counts[2] == 1 &&
            counts[3] == 1 &&
            counts[4] == 1) {
            return 15;
        }
        return 0;
    }

    public int threeOfAKind() {
        for (int i = 0; i < 6; i++) {
            if (counts[i] >= 3) {
                return (i + 1) * 3;
            }
        }
        return 0;
    }

    public int fourOfAKind() {
        for (int i = 0; i < 6; i++) {
            if (counts[i] >= 4) {
                return (i + 1) * 4;
            }
        }
        return 0;
    }

    private int[] countsOccurrenceOfDiceNumberInTheRoll() {
        int[] counts = new int[6];
        counts[dice[0] - 1]++;
        counts[dice[1] - 1]++;
        counts[dice[2] - 1]++;
        counts[dice[3] - 1]++;
        counts[dice[4] - 1]++;
        return counts;
    }

    public int twoPairs() {
        int numberOfPairs = 0;
        int sum = 0;
        for (int i = 0; i < counts.length; i += 1)
            if (counts[6 - i - 1] >= 2) {
                numberOfPairs++;
                sum += (6 - i);
            }
        if (numberOfPairs == 2) {
            return sum * 2;
        }

        return 0;
    }

    public int yatzy() {
        for (int i = 0; i < 6; i++) {
            if (counts[i] == 5) {
                return 50;
            }
        }
        return 0;
    }

    public int chance() {
        return Arrays.stream(dice).sum();
    }

    public int ones() {
        return scoreSumOfDiceWithTheSameNumber(1);
    }

    public int twos() {
        return scoreSumOfDiceWithTheSameNumber(2);
    }

    public int threes() {
        return scoreSumOfDiceWithTheSameNumber(3);
    }

    public int fours() {
        return scoreSumOfDiceWithTheSameNumber(4);
    }

    public int fives() {
        return scoreSumOfDiceWithTheSameNumber(5);
    }

    public int sixes() {
        return scoreSumOfDiceWithTheSameNumber(6);
    }


    private int scoreSumOfDiceWithTheSameNumber(int diceNumber) {
        return Arrays.stream(dice).filter(die -> die == diceNumber).sum();
    }

    public int pair() {
        for (int i = 0; i < counts.length; i++) {
            if (counts[6 - i - 1] >= 2) {
                return (6 - i) * 2;
            }
        }
        return 0;
    }
}




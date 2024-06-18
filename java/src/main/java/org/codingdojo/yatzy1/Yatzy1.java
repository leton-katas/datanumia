package org.codingdojo.yatzy1;

import java.util.Arrays;
import java.util.List;

public class Yatzy1 {

    private final int[] counts;
    private final List<Integer> dice;

    public Yatzy1(int d1, int d2, int d3, int d4, int d5) {
        dice = Arrays.asList(d1, d2, d3, d4, d5);
        counts = countsOccurrenceOfDiceNumberInTheRoll();
    }

    public int largeStraight() {
        if (isLargeStraightRoll()) {
            return 20;
        }
        return 0;
    }

    public int smallStraight() {
        if (isSmallStraightRoll()) {
            return 15;
        }
        return 0;
    }

    public int fullHouse() {
        boolean foundTwoOfAKind = false;
        int i;
        int twoOfAKind = 0;
        boolean foundThreeOfAKind = false;
        int threeOfAKind = 0;

        for (i = 0; i < counts.length; i += 1) {
            if (counts[i] == 2) {
                foundTwoOfAKind = true;
                twoOfAKind = i + 1;
            }
        }

        for (i = 0; i < counts.length; i += 1) {
            if (counts[i] == 3) {
                foundThreeOfAKind = true;
                threeOfAKind = i + 1;
            }
        }

        if (foundTwoOfAKind && foundThreeOfAKind) {
            return twoOfAKind * 2 + threeOfAKind * 3;
        }

        return 0;
    }

    public int threeOfAKind() {
        return scoresSumOfAllDiceMatchingXTimesOfAKind(3);
    }


    public int fourOfAKind() {
        return scoresSumOfAllDiceMatchingXTimesOfAKind(4);
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
        if (isYatzyRoll()) {
            return 50;
        }
        return 0;
    }

    private boolean isYatzyRoll() {
        return Arrays
            .stream(counts)
            .anyMatch(dieCount -> dieCount == 5);
    }

    public int chance() {
        return dice
            .stream()
            .mapToInt(Integer::intValue)
            .sum();
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
        return dice.stream()
            .filter(die -> die == diceNumber)
            .mapToInt(Integer::intValue)
            .sum();
    }

    public int pair() {
        for (int i = 0; i < counts.length; i++) {
            if (counts[6 - i - 1] >= 2) {
                return (6 - i) * 2;
            }
        }
        return 0;
    }

    private int[] countsOccurrenceOfDiceNumberInTheRoll() {
        int[] counts = new int[6];
        for (var die : dice) {
            counts[die - 1]++;
        }
        return counts;
    }

    private int scoresSumOfAllDiceMatchingXTimesOfAKind(int timesOfAKind) {
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] >= timesOfAKind) {
                return (i + 1) * timesOfAKind;
            }
        }
        return 0;
    }


    private boolean isSmallStraightRoll() {
        return counts[0] == 1 &&
            counts[1] == 1 &&
            counts[2] == 1 &&
            counts[3] == 1 &&
            counts[4] == 1;
    }


    private boolean isLargeStraightRoll() {
        return counts[1] == 1 &&
            counts[2] == 1 &&
            counts[3] == 1 &&
            counts[4] == 1
            && counts[5] == 1;
    }
}




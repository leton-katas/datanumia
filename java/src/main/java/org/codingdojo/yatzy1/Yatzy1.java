package org.codingdojo.yatzy1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Yatzy1 {

    public static final List<Integer> TIMES_OF_FULL_HOUSE_KIND = Arrays.asList(2, 3);
    public static final List<Integer> DICE_NUMBERS = Arrays.asList(6, 5, 4, 3, 2, 1);
    private final List<Integer> dice;
    private final Map<Integer, Integer> occurrenceOfDiceNumberInRoll;

    public Yatzy1(int d1, int d2, int d3, int d4, int d5) {
        dice = Arrays.asList(d1, d2, d3, d4, d5);
        occurrenceOfDiceNumberInRoll = countsOccurrenceOfDiceNumberInTheRoll();
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
        return TIMES_OF_FULL_HOUSE_KIND.stream().reduce(0, (score, times) -> {
            if (!occurrenceOfDiceNumberInRoll.containsValue(times)) {
                return 0;
            }
            return score + foundDiceNumberMatchingXTimesOfAKind(times) * times;
        });
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
        for (var die : DICE_NUMBERS) {
            if (occurrenceOfDiceNumberInRoll.getOrDefault(die, 0) >= 2) {
                numberOfPairs++;
                sum += die;
            }
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
        return occurrenceOfDiceNumberInRoll.containsValue(5);
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
        for (var die : DICE_NUMBERS) {
            if (occurrenceOfDiceNumberInRoll.getOrDefault(die, 0) >= 2) {
                return die * 2;
            }
        }
        return 0;
    }

    private Map<Integer, Integer> countsOccurrenceOfDiceNumberInTheRoll() {
        Map<Integer, Integer> occurrenceOfDiceNumberInRoll = new HashMap<>();
        for (var die : dice) {
            if (!occurrenceOfDiceNumberInRoll.containsKey(die)) {
                occurrenceOfDiceNumberInRoll.put(die, 0);
            }
            occurrenceOfDiceNumberInRoll.put(die, occurrenceOfDiceNumberInRoll.get(die) + 1);
        }
        return occurrenceOfDiceNumberInRoll;
    }

    private int foundDiceNumberMatchingXTimesOfAKind(int timesOfAKind) {
        for (var die : dice) {
            if (occurrenceOfDiceNumberInRoll.get(die) == timesOfAKind) {
                return die;
            }
        }
        return 0;
    }

    private int scoresSumOfAllDiceMatchingXTimesOfAKind(int timesOfAKind) {
        for (var die : dice) {
            if (occurrenceOfDiceNumberInRoll.get(die) >= timesOfAKind) {
                return die * timesOfAKind;
            }
        }
        return 0;
    }

    private boolean isSmallStraightRoll() {
        return foundAllUniqueDiceExceptExcluded(6);
    }

    private boolean isLargeStraightRoll() {
        return foundAllUniqueDiceExceptExcluded(1);
    }


    private boolean foundAllUniqueDiceExceptExcluded(int excludedKind) {
        Integer totalOfDiceNumberThatOccursOnce = occurrenceOfDiceNumberInRoll
            .values()
            .stream()
            .filter(value -> value == 1)
            .reduce(0, Integer::sum);
        return totalOfDiceNumberThatOccursOnce == 5 && occurrenceOfDiceNumberInRoll
            .getOrDefault(excludedKind, 0) == 0;
    }
}




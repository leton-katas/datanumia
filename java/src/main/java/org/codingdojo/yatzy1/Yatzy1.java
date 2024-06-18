package org.codingdojo.yatzy1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


public class Yatzy1 {



    private static final List<Integer> TIMES_OF_FULL_HOUSE_KIND = Arrays.asList(2, 3);
    private static final List<Dice> DICE_NUMBERS = Arrays.asList(Dice.SIX, Dice.FIVE, Dice.FOUR, Dice.THREE, Dice.TWO, Dice.ONE);
    private final List<Dice> dice;
    private final Map<Dice, Integer> occurrenceOfDiceNumberInRoll;

    public Yatzy1(int d1, int d2, int d3, int d4, int d5) {
        dice = Stream.of(d1, d2, d3, d4, d5)
            .map(Dice::findByNumber).toList();
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
                sum += die.getNumber();
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
            .mapToInt(Dice::getNumber)
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
            .filter(die -> die.getNumber().equals(diceNumber))
            .mapToInt(Dice::getNumber)
            .sum();
    }

    public int pair() {
        for (var die : DICE_NUMBERS) {
            if (occurrenceOfDiceNumberInRoll.getOrDefault(die, 0) >= 2) {
                return die.getNumber() * 2;
            }
        }
        return 0;
    }

    private Map<Dice, Integer> countsOccurrenceOfDiceNumberInTheRoll() {
        Map<Dice, Integer> occurrenceOfDiceNumberInRoll = new HashMap<>();
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
                return die.getNumber();
            }
        }
        return 0;
    }

    private int scoresSumOfAllDiceMatchingXTimesOfAKind(int timesOfAKind) {
        for (var die : dice) {
            if (occurrenceOfDiceNumberInRoll.get(die) >= timesOfAKind) {
                return die.getNumber() * timesOfAKind;
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




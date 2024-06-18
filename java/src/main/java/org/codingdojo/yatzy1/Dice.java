package org.codingdojo.yatzy1;

public enum Dice {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6);

    private final Integer number;

    Dice(Integer number) {
        this.number = number;
    }

    public static Dice findByNumber(Integer number) {
        for (Dice dieNumber : values()) {
            if (dieNumber.getNumber().equals(number)) {
                return dieNumber;
            }
        }
        return null;
    }

    Integer getNumber() {
        return number;
    }
}

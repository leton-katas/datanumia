package org.codingdojo;

import org.codingdojo.yatzy1.Yatzy1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Yatzy1Test {

    @Test
    public void chance_should_scores_sum_of_all_dice_when_placed_on_chance() {
        int expected = 15;
        int actual = new Yatzy1(2, 3, 4, 5, 1).chance();
        assertEquals(expected, actual);
        assertEquals(16, new Yatzy1(3, 3, 4, 5, 1).chance());
    }

    @Test
    public void yatzy_should_scores_50_when_all_dice_have_the_same_number_and_placed_on_yatzy() {
        int expected = 50;
        int actual = new Yatzy1(4, 4, 4, 4, 4).yatzy();
        assertEquals(expected, actual);
        assertEquals(50, new Yatzy1(6, 6, 6, 6, 6).yatzy());
        assertEquals(0, new Yatzy1(6, 6, 6, 6, 3).yatzy());
    }

    @Test
    public void ones_should_scores_sum_of_all_dice_that_reads_one_when_placed_on_ones() {
        assertEquals(1, new Yatzy1(1, 2, 3, 4, 5).ones());
        assertEquals(2, new Yatzy1(1, 2, 1, 4, 5).ones());
        assertEquals(0, new Yatzy1(6, 2, 2, 4, 5).ones());
        assertEquals(4, new Yatzy1(1, 2, 1, 1, 1).ones());
    }

    @Test
    public void twos_should_scores_sum_of_all_dice_that_reads_two_when_placed_on_twos() {
        assertEquals(4, new Yatzy1(1, 2, 3, 2, 6).twos());
        assertEquals(10, new Yatzy1(2, 2, 2, 2, 2).twos());
    }

    @Test
    public void threes_should_scores_sum_of_all_dice_that_reads_three_when_placed_on_threes() {
        assertEquals(6, new Yatzy1(1, 2, 3, 2, 3).threes());
        assertEquals(12, new Yatzy1(2, 3, 3, 3, 3).threes());
    }

    @Test
    public void fours_should_scores_sum_of_all_dice_that_reads_fours_when_placed_on_fours() {
        assertEquals(12, new Yatzy1(4, 4, 4, 5, 5).fours());
        assertEquals(8, new Yatzy1(4, 4, 5, 5, 5).fours());
        assertEquals(4, new Yatzy1(4, 5, 5, 5, 5).fours());
    }

    @Test
    public void fives_should_scores_sum_of_all_dice_that_reads_five_when_placed_on_fives() {
        assertEquals(10, new Yatzy1(4, 4, 4, 5, 5).fives());
        assertEquals(15, new Yatzy1(4, 4, 5, 5, 5).fives());
        assertEquals(20, new Yatzy1(4, 5, 5, 5, 5).fives());
    }

    @Test
    public void sixes_should_scores_sum_of_all_dice_that_reads_six_when_placed_on_sixes() {
        assertEquals(0, new Yatzy1(4, 4, 4, 5, 5).sixes());
        assertEquals(6, new Yatzy1(4, 4, 6, 5, 5).sixes());
        assertEquals(18, new Yatzy1(6, 5, 6, 6, 5).sixes());
    }

    @Test
    public void pair_should_scores_sum_of_two_highest_matching_dice_when_placed_on_pair() {
        assertEquals(6, new Yatzy1(3, 4, 3, 5, 6).pair());
        assertEquals(10, new Yatzy1(5, 3, 3, 3, 5).pair());
        assertEquals(12, new Yatzy1(5, 3, 6, 6, 5).pair());
    }

    @Test
    public void twoPairs_should_scores_sum_of_dice_matching_two_pairs_of_dice_with_same_number_when_placed_two_pairs() {
        assertEquals(16, new Yatzy1(3, 3, 5, 4, 5).twoPairs());
        assertEquals(16, new Yatzy1(3, 3, 5, 5, 5).twoPairs());
    }

    @Test
    public void threeOfAKind_should_scores_sum_of_three_with_the_same_number_when_placed_on_three_of_a_kind() {
        assertEquals(9, new Yatzy1(3, 3, 3, 4, 5).threeOfAKind());
        assertEquals(15, new Yatzy1(5, 3, 5, 4, 5).threeOfAKind());
        assertEquals(9, new Yatzy1(3, 3, 3, 3, 5).threeOfAKind());
    }

    @Test
    public void fourOfAKind_should_scores_sum_of_four_with_the_same_number_when_placed_on_four_of_a_kind() {
        assertEquals(12, new Yatzy1(3, 3, 3, 3, 5).fourOfAKind());
        assertEquals(20, new Yatzy1(5, 5, 5, 4, 5).fourOfAKind());
        assertEquals(9, new Yatzy1(3, 3, 3, 3, 3).threeOfAKind());
    }

    @Test
    public void smallStraight_should_scores_sum_of_dice_matching_all_dice_number_except_number_six_when_placed_on_small_straight() {
        assertEquals(15, new Yatzy1(1, 2, 3, 4, 5).smallStraight());
        assertEquals(15, new Yatzy1(2, 3, 4, 5, 1).smallStraight());
        assertEquals(0, new Yatzy1(1, 2, 2, 4, 5).smallStraight());
    }

    @Test
    public void largeStraight_should_scores_sum_of_dice_matching_all_dice_number_except_number_one_when_placed_on_small_straight() {
        assertEquals(20, new Yatzy1(6, 2, 3, 4, 5).largeStraight());
        assertEquals(20, new Yatzy1(2, 3, 4, 5, 6).largeStraight());
        assertEquals(0, new Yatzy1(1, 2, 2, 4, 5).largeStraight());
    }

    @Test
    public void fullHouse_should_scores_sum_of_all_dice_matching_two_with_same_number_and_three_of_other_same_number_when_placed_on_full_house() {
        assertEquals(18, new Yatzy1(6, 2, 2, 2, 6).fullHouse());
        assertEquals(0, new Yatzy1(2, 3, 4, 5, 6).fullHouse());
    }
}

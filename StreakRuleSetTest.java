import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Adrian Schrader
 * @version 1.0.0
 */
public class StreakRuleSetTest {

    public StreakRuleSetTest() {

    }

    @Test
    public void getLength() throws Exception {
        StreakRuleSet rules = new StreakRuleSet(2);
        assertEquals(rules.getLength(), 2);
    }

    @Test
    public void isWinnerDeterminedWithTie() {
        Player player1 = new PlayerImpl();
        Player player2 = new PlayerImpl();
        RuleSet rules = new StreakRuleSet(2);

        rules.registerWin(player1);
        rules.registerWin(player2);
        assertEquals(rules.isWinnerDetermined(), false);
    }

    @Test
    public void isWinnerDeterminedWithBrokenStreak() {
        Player player1 = new PlayerImpl();
        Player player2 = new PlayerImpl();
        RuleSet rules = new StreakRuleSet(2);

        rules.registerWin(player1);
        rules.registerWin(player2);
        rules.registerWin(player1);
        assertEquals(rules.isWinnerDetermined(), false);
    }

    @Test
    public void isWinnerDeterminedWithStreak() {
        Player player1 = new PlayerImpl();
        Player player2 = new PlayerImpl();
        RuleSet rules = new StreakRuleSet(2);

        rules.registerWin(player1);
        rules.registerWin(player2);
        rules.registerWin(player2);
        assertEquals(rules.isWinnerDetermined(), true);
    }

    @Test
    public void isWinnerDeterminedWithHigherStreak() {
        Player player2 = new PlayerImpl();
        RuleSet rules = new StreakRuleSet(2);

        rules.registerWin(player2);
        rules.registerWin(player2);
        rules.registerWin(player2);
        assertEquals(rules.isWinnerDetermined(), true);
    }

    @Test
    public void isWinnerDeterminedWithOverriddenStreak() {
        Player player1 = new PlayerImpl();
        Player player2 = new PlayerImpl();
        RuleSet rules = new StreakRuleSet(2);

        rules.registerWin(player2);
        rules.registerWin(player2);
        rules.registerWin(player1);
        assertEquals(rules.isWinnerDetermined(), true);
    }

    @Test
    public void getWinner() {
        Player player1 = new PlayerImpl();
        Player player2 = new PlayerImpl();
        RuleSet rules = new StreakRuleSet(2);

        rules.registerWin(player1);
        rules.registerWin(player2);
        rules.registerWin(player2);
        assertEquals(rules.getWinner(), player2);
    }
}
 

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BestOfRuleSetTest {

    @Test
    public void getRoundsLeft() throws Exception {
        PlayerImpl player = new PlayerImpl();
        BestOfRuleSet rules = new BestOfRuleSet(3);

        assertEquals(rules.getRoundsLeft(), 3);
        rules.registerWin(player);
        assertEquals(rules.getRoundsLeft(), 2);
    }

    @Test
    public void registerWin() throws Exception {
        PlayerImpl player = new PlayerImpl();
        BestOfRuleSet rules = new BestOfRuleSet(3);

        rules.registerWin(player);
        assertTrue(rules.getScoreBoard().containsKey(player));
        assertEquals((int) rules.getScoreBoard().get(player), 1);
    }

    @Test
    public void isWinnerDeterminedWithNotAllRounds() throws Exception {
        PlayerImpl player1 = new PlayerImpl();
        PlayerImpl player2 = new PlayerImpl();
        BestOfRuleSet rules = new BestOfRuleSet(3);

        rules.registerWin(player1);
        rules.registerWin(player2);

        assertEquals(rules.isWinnerDetermined(), false);
    }

    @Test
    public void isWinnerDeterminedWithMajority() throws Exception {
        PlayerImpl player2 = new PlayerImpl();
        BestOfRuleSet rules = new BestOfRuleSet(3);

        rules.registerWin(player2);
        rules.registerWin(player2);

        assertEquals(rules.isWinnerDetermined(), true);
    }

    @Test
    public void isWinnerDeterminedWithAllRounds() throws Exception {
        PlayerImpl player1 = new PlayerImpl();
        PlayerImpl player2 = new PlayerImpl();
        BestOfRuleSet rules = new BestOfRuleSet(3);

        rules.registerWin(player1);
        rules.registerWin(player2);
        rules.registerWin(player1);

        assertEquals(rules.isWinnerDetermined(), true);
    }

    @Test
    public void isWinnerDeterminedWithAllRoundsAndTie() throws Exception {
        PlayerImpl player1 = new PlayerImpl();
        PlayerImpl player2 = new PlayerImpl();
        BestOfRuleSet rules = new BestOfRuleSet(4);

        rules.registerWin(player1);
        rules.registerWin(player2);
        rules.registerWin(player2);
        rules.registerWin(player1);

        assertEquals(rules.isWinnerDetermined(), false);
    }

    @Test
    public void getWinnerOneKey() throws Exception {
        PlayerImpl player1 = new PlayerImpl();
        BestOfRuleSet rules = new BestOfRuleSet(1);

        rules.registerWin(player1);

        assertEquals(rules.getWinner(), player1);
    }

    @Test
    public void getWinnerByHighestScore() throws Exception {
        PlayerImpl player1 = new PlayerImpl();
        PlayerImpl player2 = new PlayerImpl();
        BestOfRuleSet rules = new BestOfRuleSet(2);

        rules.registerWin(player1);
        rules.registerWin(player2);
        rules.registerWin(player2);

        assertEquals(rules.getWinner(), player2);
    }

    @Test
    public void implementationTest() throws Exception {
        PlayerImpl player1 = new PlayerImpl();
        PlayerImpl player2 = new PlayerImpl();
        BestOfRuleSet rules = new BestOfRuleSet(3);

        Player winner = player1.playGame(player2, rules);
        assertEquals(rules.getRoundsLeft(), 0);
        assertEquals(winner, player1);
    }
}
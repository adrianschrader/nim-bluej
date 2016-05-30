import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    public PlayerTest() {

    }

    @Test
    public void getName() throws Exception {
        Player player = new PlayerImpl();
        assertEquals(player.getName(), "John Doe");
    }

    @Test
    public void playDeterminesWinner() throws Exception {
        Player playerA = new PlayerImpl(),
                playerB = new PlayerImpl();
        MatchBox matchBox = new MatchBox(10);

        // Draw until there is one match in the box
        matchBox.draw(3);
        matchBox.draw(3);
        matchBox.draw(3);

        Player winner = playerA.play(matchBox, playerB);
        assertEquals(winner, playerB);
    }

    @Test
    public void playGameWithPerfectPlayers() {
        Player player1 = new AIPlayer("");
        Player player2 = new AIPlayer("");
        RuleSet rules = new StreakRuleSet(2);

        // Throws overflow exception if there are too many rounds
        player1.playGame(player2, rules);
    }

    @Test
    public void playRemovesMatches() throws Exception {
        Player playerA = new PlayerImpl();
        MatchBox matchBox = new MatchBox(10);

        playerA.play(matchBox);
        assertEquals(matchBox.count(), 9);
    }

    @Test
    public void playRound() throws Exception {
        Player playerA = new PlayerImpl();
        Player playerB = new PlayerImpl();
        Player winner = playerA.playRound(playerB);
        // Because each PlayerImpl removes one match per round and chooses a matchbox of size 10
        assertEquals(winner, playerA);
    }
}
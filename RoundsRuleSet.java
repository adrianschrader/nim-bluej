import java.util.HashMap;
import java.util.Map;

public class RoundsRuleSet implements RuleSet {

    private int rounds;
    private int currentRound;
    private Map<Player, Integer> scores;

    public RoundsRuleSet(int rounds) {
        this.rounds = rounds;
        this.currentRound = 0;
        this.scores = new HashMap<Player, Integer>();
    }

    public int getRoundsLeft() {
        return this.rounds - this.currentRound;
    }

    @Override
    public void registerWin(Player player) {
        // Increment the score by one point per win.
        if (this.scores.containsKey(player))
            this.scores.put(player, this.scores.get(player) + 1);
        else
            this.scores.put(player, 1);

        // Increment the number of rounds
        this.currentRound++;
    }

    @Override
    public boolean isWinnerDetermined() {
        return (!this.scores.isEmpty() && this.currentRound == this.rounds);
    }
    
    @Override
    public Player getWinner() {
        if (this.scores.isEmpty())
            return null;

        // Determine the highest entry in the map
        Map.Entry<Player, Integer> highestScore = null;
        for (Map.Entry<Player, Integer> entry : this.scores.entrySet()) {
            if (highestScore == null || entry.getValue() > highestScore.getValue()) {
                highestScore = entry;
            }
        }
        // Return it to the outside world
        return highestScore.getKey();
    }

    public Map<Player, Integer> getScoreBoard() {
        return scores;
    }

}


import java.util.HashMap;
import java.util.Map;

public class BestOfRuleSet implements RuleSet {

    private int rounds;
    private int currentRound;
    private Map<Player, Integer> scores;

    public BestOfRuleSet(int rounds) {
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
        // There is no win registered, therefore no winner to be determined
        if (this.scores.isEmpty()) return false;
            // No other player can win, because a majority has already been established
        else if (this.hasMajorityWinner()) return true;
            // No more rounds to run
        else if (this.getRoundsLeft() < 1 && !hasDuplicateScores()) return true;

        return false;
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

    private boolean hasMajorityWinner() {
        int majorityScore = (int) Math.floor(rounds / 2.0d) + 1;
        for (int i = majorityScore; i < rounds; i++) {
            if (this.scores.containsValue(i))
                return true;
        }
        return false;
    }

    private boolean hasDuplicateScores() {
        final int MAXZIP = 99999;
        boolean[] bitmap = new boolean[MAXZIP + 1];  // Java guarantees init to false
        for (int item : this.scores.values())
            if (!(bitmap[item] ^= true)) return true;
        return false;
    }
}

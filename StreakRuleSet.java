public class StreakRuleSet implements RuleSet {
    public static final int MAX_ROUNDS = 100;

    private int rounds;
    private int winningLength;
    private int streakLength;
    private Player streakPlayer;

    public StreakRuleSet(int length) {
        super();
        this.winningLength = length;
        this.streakLength = 0;
        this.streakPlayer = null;
        this.rounds = 0;
    }

    public int getLength() {
        return this.winningLength;
    }

    @Override
    public void registerWin(Player player) {
        // The first player to get a streak wins
        if (this.isWinnerDetermined())
            return;

        // Increment the number of rounds
        this.rounds++;

        if (this.streakPlayer != null) {
            if (this.streakPlayer.equals(player)) {
                // Begin or continue streak and end
                this.streakLength++;
                return;
            }
        }

        // Break streak and begin a new one
        this.streakLength = 1;
        this.streakPlayer = player;
    }

    @Override
    public boolean isWinnerDetermined() {
        // When a player gets a streak or the max number of rounds is reached (to prevent overflow)
        return (streakLength >= winningLength) || rounds > MAX_ROUNDS;
    }

    @Override
    public Player getWinner() {
        if (this.isWinnerDetermined())
            return this.streakPlayer;
        else
            return null;
    }
}

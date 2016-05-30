

/**
 * Abstract class Player - write a description of the class here
 *
 * @author Adrian Schrader
 * @version 1.0.0
 */
public abstract class Player {

    private final String name;

    /**
     * Create a new Player with a unique name
     *
     * @param name identifier on the leaderboard
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * @return identifier on the leaderboard
     */
    public final String getName() {
        return this.name;
    }

    /**
     * Starts a game that may contain many rounds. The winner and the number of rounds is determined by the given ruleset.
     *
     * @param nextPlayer he next player to invoke the recursive method on
     * @param rules      determines how the winner is decided
     * @return the winner of the game
     */
    public final Player playGame(Player nextPlayer, RuleSet rules) {
        // Play one round and mark the result
        rules.registerWin(this.playRound(nextPlayer));

        if (rules.isWinnerDetermined()) {
            // Give back the winner
            return rules.getWinner();
        } else {
            // Start next round (the other player may begin)
            return nextPlayer.playGame(this, rules);
        }
    }

    /**
     * Starts a round of nim recursively with a partner. The MatchBox will be automatically created. Recommended option
     * to playRound a single round
     *
     * @param nextPlayer the next player to invoke the recursive method on
     * @return the winner of the round
     */
    public final Player playRound(Player nextPlayer) {
        // The other player may choose the MatchBox size
        MatchBox matchBox = nextPlayer.generateMatchBox();

        // Player 1 may begin
        return this.play(matchBox, nextPlayer);
    }

    /**
     * Continues a round of nim when the matchbox is already created.
     *
     * @param matchBox the matchbox to draw from
     * @return whether a the player has won
     */
    public final boolean play(MatchBox matchBox) {
        // If the other player had to take the last match, you win.
        if (matchBox.count() == 0) {
            return true;
        }

        // Let the player make his decision
        int numberOfMatches = this.drawMatches(matchBox.clone());

        // Make sure that the player can invoke the draw(int n) method just once
        // Validation happens in the MatchBox class
        matchBox.draw(numberOfMatches);

        // End the turn and return the result
        return false;
    }

    /**
     * Continues a round of nim when the matchbox is already created.
     *
     * @param matchBox   the matchbox to draw from
     * @param nextPlayer the next player to invoke the recursive method on
     * @return the winner of the round
     */
    public final Player play(MatchBox matchBox, Player nextPlayer) {
        boolean won = this.play(matchBox);

        // Invoke method calls to the subclasses
        if (won) {
            this.afterRound(true);
            nextPlayer.afterRound(false);
        }

        // If a winner could not be determined, try again.
        return won ? this : nextPlayer.play(matchBox, this);
    }

    /**
     * Determines the number of matches that should be drawn by the playRound method. Is implemented by the actual game logic
     * in the subclasses.
     *
     * @param box copy of the matchbox for inspection
     * @return # of matches to remove
     */
    protected abstract int drawMatches(final MatchBox box);

    /**
     * At the begin of a round, Player B may choose the size of the matchbox.
     *
     * @return Matchbox object used in the next round
     */
    public abstract MatchBox generateMatchBox();

    public abstract void afterRound(boolean won);
}

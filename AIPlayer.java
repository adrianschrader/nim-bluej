 

/**
 * @author Adrian Schrader
 * @version 1.0.0
 */
public class AIPlayer extends Player {
    public AIPlayer(String name) {
        super(name);
    }

    /**
     * There are special numbers of matches for which the AI is more likely to win.
     *
     * @param box Copy of the used matchbox (final)
     * @return # of matches to be removes (between 1 and 3)
     */
    @Override
    protected int drawMatches(final MatchBox box) {
        return box.count() - getNextKeyNumber(box.count());
    }

    /**
     * @return The matchbox the player is allowed to pick in the beginning of every second round.
     */
    @Override
    public MatchBox generateMatchBox() {
        return new MatchBox(13);
    }

    /**
     * The formula for "key numbers" is (4*n)+1 or MIN+(MIN+MAX)*n for the bounds of available draw moves. This method
     * tries to find the next smallest number fitting the pattern.
     *
     * @param sticks # of matches in the box
     * @return the next key number you should end up with at the end of the turn
     */
    int getNextKeyNumber(int sticks) {
        // Get the index for the number of sticks (will be non-integer for non-key numbers)
        double n = 1.0d * (sticks - MatchBox.DRAW_MIN) / (MatchBox.DRAW_MIN + MatchBox.DRAW_MAX);

        // Get the smaller key number by flooring the result
        return (int) Math.floor(n) * (MatchBox.DRAW_MIN + MatchBox.DRAW_MAX) + MatchBox.DRAW_MIN;
    }

    @Override
    public void afterRound(boolean won) {

    }
}

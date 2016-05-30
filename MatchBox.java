 

/**
 * The MatchBox is filled once with matches. They can be drawn in sets of one to three.
 *
 * @author Adrian Schrader
 * @version 1.0.0
 */
public class MatchBox implements Cloneable {
    static final int MATCHES_MIN = 10;
    static final int MATCHES_MAX = 40;

    static final int DRAW_MIN = 1;
    static final int DRAW_MAX = 3;

    private int matches;

    /**
     * Creates a MatchBox with a specific configuration of matches.
     * @param matches # of matches in the box (between 10 and 40, will be clipped)
     */
    public MatchBox(int matches) {
        this.matches = this.setBounds(matches, MATCHES_MIN, MATCHES_MAX);
    }

    /**
     * Removes one to three matches from the box. Should only be called once for every player and turn.
     * @param sticks # of matches to be removed (between 1 and 3, will be clipped)
     */
    public void draw(int sticks) {
        this.matches -= this.setBounds(sticks, DRAW_MIN, DRAW_MAX);

        if (this.matches < 0)
            this.matches = 0;
    }

    /**
     * @return # of matches in the box
     */
    public int count() {
        return this.matches;
    }

    /**
     * Lets the object be cloned, so you can pass inspection copies
     *
     * @return clone from the MatchBox instance
     * @see Object#clone()
     */
    @Override
    public MatchBox clone() {
        try {
            return (MatchBox) super.clone();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    private int setBounds(int n, int lower, int upper) {
        return (n < lower) ? lower : (n > upper) ? upper : n;
    }
}

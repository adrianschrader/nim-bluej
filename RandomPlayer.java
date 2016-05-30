 
/**
 * Write a description of class AutomatedPlayer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RandomPlayer extends Player {
    public RandomPlayer(String name) {
        super(name);
    }

    @Override
    protected int drawMatches(final MatchBox box) {
        return (int) Math.floor(Math.random() * 3.0d + 1.0d);
    }

    @Override
    public MatchBox generateMatchBox() {
        return new MatchBox(12);
    }

    @Override
    public void afterRound(boolean won) {

    }
}

 

public class PlayerImpl extends Player {
    public PlayerImpl() {
        super("John Doe");
    }

    @Override
    protected int drawMatches(MatchBox box) {
        return 1;
    }

    @Override
    public MatchBox generateMatchBox() {
        return new MatchBox(10);
    }

    @Override
    public void afterRound(boolean won) {

    }
}
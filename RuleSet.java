
public interface RuleSet {
    void registerWin(Player player);

    boolean isWinnerDetermined();

    Player getWinner();
}

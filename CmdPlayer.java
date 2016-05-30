import java.util.Scanner;

public class CmdPlayer extends Player {

    Scanner scanner;

    public CmdPlayer(String name) {
        super(name);
        scanner = new Scanner(System.in);
    }

    @Override
    protected int drawMatches(MatchBox box) {
        System.out.println("Es sind noch " + box.count() + " Streichhölzer in der Schachtel. Wie viele möchtest du ziehen?");
        return scanner.nextInt();
    }

    @Override
    public MatchBox generateMatchBox() {
        System.out.println("Wie viele Hölzer möchstest du in die Streichholzschachtel legen?");
        return new MatchBox(scanner.nextInt());
    }

    @Override
    public void afterRound(boolean won) {
        System.out.println("Du hast " + (won ? "gewonnen" : "verlohren") + "!");
    }
}

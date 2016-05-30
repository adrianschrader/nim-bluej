import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class PlayersTest {

    private Class<?> playerType;

    public PlayersTest(Class<?> playerType) {
        this.playerType = playerType;
    }

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(RandomPlayer.class, AIPlayer.class);
    }

    private Player generatePlayer() throws Exception {
        return (Player) this.playerType.getConstructor(new Class<?>[]{String.class}).newInstance("John Doe");
    }
    
    // BlueJ does not support parameterized testing
    //@Test
    public void drawMatches() throws Exception {
        Player player = generatePlayer();
        int matches = player.drawMatches(new MatchBox(10));

        if (matches < 1) {
            fail("Player has to draw at least one match");
        } else if (matches > 3) {
            fail("Player has to draw less than or equal to three matches");
        }
    }
    
    // BlueJ does not support parameterized testing
    //@Test
    public void generateMatchBox() throws Exception {
        Player player = generatePlayer();
        if (player.generateMatchBox().count() < 10)
            fail("MatchBox has to have at least 10 matches at the start of the game. ");
    }
}

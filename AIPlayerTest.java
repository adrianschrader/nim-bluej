 

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Adrian Schrader
 * @version 1.0.0
 */
public class AIPlayerTest {

    @Test
    public void drawMatches() throws Exception {
        AIPlayer player = new AIPlayer("");
        MatchBox matchBox = new MatchBox(15);

        // Remove matches from box
        matchBox.draw(player.drawMatches(matchBox));

        // The number of matches after the turn should be a key number
        assertEquals(player.getNextKeyNumber(matchBox.count()), matchBox.count());
    }

    @Test
    public void generateMatchBox() throws Exception {
        AIPlayer player = new AIPlayer("");
        MatchBox matchBox = player.generateMatchBox();

        // The number of matches should be a key number
        assertEquals(player.getNextKeyNumber(matchBox.count()), matchBox.count());
    }

    @Test
    public void getNextKeyNumber() throws Exception {
        AIPlayer player = new AIPlayer("");
        assertEquals(player.getNextKeyNumber(14), 13);
        assertEquals(player.getNextKeyNumber(10), 9);
        assertEquals(player.getNextKeyNumber(3), 1);
    }

    @Test
    public void getNextKeyNumberVerifies() throws Exception {
        AIPlayer player = new AIPlayer("");
        assertEquals(player.getNextKeyNumber(5), 5);
    }
}
 
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The test class MatchBoxTest.
 *
 * @author Adrian Schrader
 * @version 1.0.0
 */
public class MatchBoxTest {
    /**
     * Default constructor for test class MatchBoxTest
     */
    public MatchBoxTest() {
    }

    @Test
    public void countHasUpperBound() {
        MatchBox matchBox1 = new MatchBox(MatchBox.MATCHES_MAX + 1);
        assertEquals(matchBox1.count(), MatchBox.MATCHES_MAX);
    }

    @Test
    public void countHasLowerBound() {
        MatchBox matchBox2 = new MatchBox(MatchBox.MATCHES_MIN - 1);
        assertEquals(matchBox2.count(), MatchBox.MATCHES_MIN);
    }

    @Test
    public void countNormal() {
        MatchBox matchBox3 = new MatchBox(25);
        assertEquals(matchBox3.count(), 25);
    }

    @Test
    public void countNotBelowZero() {
        MatchBox matchbox1 = new MatchBox(10);
        matchbox1.draw(3);
        matchbox1.draw(3);
        matchbox1.draw(3);
        matchbox1.draw(3);
        assertEquals(matchbox1.count(), 0); // would otherwise equal -2
    }

    @Test
    public void drawAtLeastOne() {
        MatchBox matchbox1 = new MatchBox(25);
        matchbox1.draw(0);
        assertEquals(matchbox1.count(), 24);
    }

    @Test
    public void drawNotMoreThanThree() {
        MatchBox matchbox2 = new MatchBox(25);
        matchbox2.draw(4);
        assertEquals(matchbox2.count(), 22);
    }

    @Test
    public void duplicate() {
        MatchBox original = new MatchBox(10);
        MatchBox copy = original.clone();

        // Manipulate copy and original differently
        original.draw(1);
        copy.draw(3);

        // The objects should be independent
        assertEquals(original.count(), 9);
        assertEquals(copy.count(), 7);
    }
}




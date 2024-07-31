import api.*;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Η κλάση {@code ReviewTest} περιέχει περιπτώσεις δοκιμών JUnit για την επικύρωση της λειτουργικότητας της κλάσης {@link Review}.
 * Επικεντρώνεται στον έλεγχο του κατασκευαστή, των getters και των setters για διάφορα χαρακτηριστικά μιας αξιολόγησης.
 *
 * @author ioannismaredis
 */
public class ReviewTest {

    @Test
    public void testConstructorAndGetters() {
        Review review = new Review("Great review!", 4, "2022-01-01", "user1", 3.0);

        assertEquals("Great review!", review.getText());
        assertEquals(4, review.getRating());
        assertEquals("2022-01-01", review.getDate());
        assertEquals("user1", review.getUsername());
        assertEquals(3.0, review.getMinOverallRating(), 0.01);
    }

    @Test
    public void testSetters() {
        Review review = new Review("Nice review!", 3, "2022-01-02", "user2", 2.5);

        review.setText("Updated review");
        assertEquals("Updated review", review.getText());

        review.setRating(5);
        assertEquals(5, review.getRating());

        review.setUsername("updatedUser");
        assertEquals("updatedUser", review.getUsername());

        review.setMinOverallRating(4.0);
        assertEquals(4.0, review.getMinOverallRating(), 0.01);
    }
}

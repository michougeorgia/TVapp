import api.*;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Η κλάση {@code SubscriberTest} περιέχει περιπτώσεις δοκιμών JUnit για την επικύρωση της λειτουργικότητας της κλάσης {@link Subscriber}.
 * Επικεντρώνεται στη δοκιμή του κατασκευαστή, των μεθόδων για την προσθήκη/αφαίρεση αγαπημένων, την προσθήκη/επεξεργασία/διαγραφή κριτικών,
 * και την προβολή λεπτομερειών εμφάνισης για έναν συνδρομητή.
 *
 * @author georgiamichou
 */

public class SubscriberTest {

    @Test
    public void testConstructor() {
        Subscriber subscriber = new Subscriber("user1", "password1");
        assertNotNull(subscriber);
        assertEquals("user1", subscriber.getUsername());
        assertEquals("password1", subscriber.getPassword());
        assertNotNull(subscriber.getFavorites());
        assertNotNull(subscriber.getReviews());
    }

    @Test
    public void testAddToFavorites() {
        Subscriber subscriber = new Subscriber("user1", "password1");
        Show show = new Movies("The Lord of the Rings: The Fellowship of the Ring", "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.", true, 2001, 178, Category.ACTION, Arrays.asList("Elijah Wood", "Ian McKellen", "Orlando Bloom"));
        subscriber.addToFavorites(show);

        assertEquals(1, subscriber.getFavorites().size());
        assertTrue(subscriber.getFavorites().contains(show));
    }

    @Test
    public void testRemoveFromFavorites() {
        Subscriber subscriber = new Subscriber("user1", "password1");
        Show show = new Movies("The Lord of the Rings: The Fellowship of the Ring", "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.", true, 2001, 178, Category.ACTION, Arrays.asList("Elijah Wood", "Ian McKellen", "Orlando Bloom"));
        subscriber.addToFavorites(show);
        subscriber.removeFromFavorites(show);

        assertEquals(0, subscriber.getFavorites().size());
        assertFalse(subscriber.getFavorites().contains(show));
    }

    @Test
    public void testAddReview() {
        Subscriber subscriber = new Subscriber("user1", "password1");
        Show show = new Movies("The Lord of the Rings: The Fellowship of the Ring", "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.", true, 2001, 178, Category.ACTION, Arrays.asList("Elijah Wood", "Ian McKellen", "Orlando Bloom"));
        Review movies1_review1 = new Review("Fantastic", 5, "24/04/2015", "user2", 5);
        subscriber.addReview(movies1_review1);
        assertEquals(1, subscriber.getReviews().size());
        assertTrue(subscriber.getReviews().contains(movies1_review1));
    }

    @Test
    public void testEditReview() {
        Subscriber subscriber = new Subscriber("subscriber1", "password1");
        Show show = new Movies("Movie 1", "Description", true, 2022, 120, Category.ACTION, new ArrayList<>());
        Review review = new Review("Great movie!", 5, "2022-01-01", subscriber.getUsername(), 0.0);
        subscriber.addReview(review);
        Review existingReview = subscriber.getReviews().get(0);
        subscriber.editReview(existingReview, "Amazing movie!", 4);

        assertEquals("Amazing movie!", existingReview.getText());
        assertEquals(4, existingReview.getRating());
        assertEquals(subscriber.getUsername(), existingReview.getUsername());
    }


    @Test
    public void testDeleteReview() {
        Subscriber subscriber = new Subscriber("user1", "password1");
        Show show = new Movies("The Lord of the Rings: The Fellowship of the Ring", "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.", true, 2001, 178, Category.ACTION, Arrays.asList("Elijah Wood", "Ian McKellen", "Orlando Bloom"));
        Review movies1_review1 = new Review("Fantastic", 5, "24/04/2015", "user2", 5);
        subscriber.addReview(movies1_review1);
        subscriber.deleteReview(movies1_review1);
        assertEquals(0, subscriber.getReviews().size());
    }

    @Test
    public void testViewShowDetails() {
        Subscriber subscriber = new Subscriber("user1", "password1");
        Show movie = new Movies("The Lord of the Rings: The Fellowship of the Ring", "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.", true, 2001, 178, Category.ACTION, Arrays.asList("Elijah Wood", "Ian McKellen", "Orlando Bloom"));
        Show series = new Series("Mindhunter", "In the late 1970s, two FBI agents broaden the realm of criminal science by investigating the psychology behind murder and end up getting too close to real-life monsters.", false,
                Category.DRAMA, Arrays.asList("Jonathan Groff", "Holt McCallany", "Anna Torv"), 1, 2017,
                new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9", "Episode 10")),
                new ArrayList<>(Arrays.asList(60, 56, 45, 54, 42, 34, 53, 54, 48, 53)));

        subscriber.viewShowDetails(movie);
        subscriber.viewShowDetails(series);
    }

    @Test
    public void testIsInFavorites() {
        Subscriber subscriber = new Subscriber("user1", "password1");
        Show movie = new Movies("The Lord of the Rings: The Fellowship of the Ring", "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.", true, 2001, 178, Category.ACTION, Arrays.asList("Elijah Wood", "Ian McKellen", "Orlando Bloom"));
        Show series = new Series("Mindhunter", "In the late 1970s, two FBI agents broaden the realm of criminal science by investigating the psychology behind murder and end up getting too close to real-life monsters.", false,
                Category.DRAMA, Arrays.asList("Jonathan Groff", "Holt McCallany", "Anna Torv"), 1, 2017,
                new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9", "Episode 10")),
                new ArrayList<>(Arrays.asList(60, 56, 45, 54, 42, 34, 53, 54, 48, 53)));

        assertFalse(subscriber.isInFavorites(movie));
        assertFalse(subscriber.isInFavorites(series));

        subscriber.addToFavorites(movie);
        assertTrue(subscriber.isInFavorites(movie));
        assertFalse(subscriber.isInFavorites(series));

        subscriber.addToFavorites(series);
        assertTrue(subscriber.isInFavorites(movie));
        assertTrue(subscriber.isInFavorites(series));

        subscriber.removeFromFavorites(movie);
        assertFalse(subscriber.isInFavorites(movie));
        assertTrue(subscriber.isInFavorites(series));

        subscriber.removeFromFavorites(series);
        assertFalse(subscriber.isInFavorites(movie));
        assertFalse(subscriber.isInFavorites(series));
    }
}


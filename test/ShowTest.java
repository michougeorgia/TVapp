
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import api.*;
import static org.junit.Assert.*;

/**
 * Κλάση δοκιμής για την κλάση {@link Show}.
 * Περιέχει διάφορες μεθόδους δοκιμής για την επαλήθευση της λειτουργικότητας της κλάσης Show.
 *
 * @author ioannismaredis, georgiamichou
 */


public class ShowTest {
    private Show movie;
    private Show series;
    private Subscriber subscriber;

    @Before
    public void setUp() {
        // Initialize the subscriber before each test
        subscriber = new Subscriber("user1", "password1");

        // Initialize the shows
        List<String> movieCast = Arrays.asList("Elijah Wood", "Ian McKellen", "Orlando Bloom");
        movie = new Show("The Lord of the Rings: The Fellowship of the Ring", "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.", true, Category.ACTION, movieCast, ShowType.MOVIE);

        List<String> seriesCast = Arrays.asList("Jonathan Groff", "Holt McCallany", "Anna Torv");
        ArrayList<String> episodes = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9", "Episode 10"));
        ArrayList<Integer> episodeDurations = new ArrayList<>(Arrays.asList(60, 56, 45, 54, 42, 34, 53, 54, 48, 53));
        series = new Show("Mindhunter", "In the late 1970s, two FBI agents broaden the realm of criminal science by investigating the psychology behind murder and end up getting too close to real-life monsters.", true, Category.DRAMA, seriesCast, ShowType.SERIES);
    }

    @Test
    public void testShowInformation() {
        assertEquals("The Lord of the Rings: The Fellowship of the Ring", movie.getTitle());
        assertEquals("A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.", movie.getDescription());
        assertTrue(movie.isForMinors());
        assertEquals(Category.ACTION, movie.getCtg());
        assertEquals(Arrays.asList("Elijah Wood", "Ian McKellen", "Orlando Bloom"), movie.getCast());
        assertEquals(0, movie.getReviews().size());
        assertEquals(0.0, movie.getAverageRating(), 0.001);

        // Assertions for series
        assertEquals("Mindhunter", series.getTitle());
        assertEquals("In the late 1970s, two FBI agents broaden the realm of criminal science by investigating the psychology behind murder and end up getting too close to real-life monsters.", series.getDescription());
        assertTrue(series.isForMinors());
        assertEquals(Category.DRAMA, series.getCtg());
        assertEquals(Arrays.asList("Jonathan Groff", "Holt McCallany", "Anna Torv"), series.getCast());
        assertEquals(0, series.getReviews().size());
        assertEquals(0.0, series.getAverageRating(), 0.001);
    }
    @Test
    public void testAddReview() {
        // Assuming you have a subscriber instance, set it for both shows
        movie.setSubscriber(subscriber);
        series.setSubscriber(subscriber);

        Review movieReview = new Review("Amazing", 5, "24/11/2017", "user1", 5.0);
        Review seriesReview = new Review("Funny series!", 5, "2022-01-02", "user2", 5.0);

        movie.addReview(movieReview);
        series.addReview(seriesReview);
        subscriber.addReview(movieReview);
        subscriber.addReview(seriesReview);

        assertEquals(1, movie.getReviews().size());
        assertEquals(1, series.getReviews().size());

        assertEquals(5.0, movie.getAverageRating(), 0.01);
        assertEquals(5.0, series.getAverageRating(), 0.01);

        assertEquals(2, subscriber.getReviews().size()); // Assuming the subscriber is set correctly
    }



    @Test
    public void testCalculateAverageRating() {
        Review movieReview1 = new Review("Amazing", 4, "24/11/2017", "user1", 4.5);
        Review movieReview2 = new Review("Fantastic", 5, "24/04/2015", "user2", 4);

        movie.addReview(movieReview1);
        movie.addReview(movieReview2);

        assertEquals(2, movie.getReviews().size());
        assertEquals(4.5, movie.getAverageRating(), 0.01); // Specify delta value (0.01) for floating-point comparison
    }



    @Test
    public void testAddToFavorites() {
        subscriber.addToFavorites(movie);
        subscriber.addToFavorites(series);

        assertEquals(2, subscriber.getFavorites().size());

        assertTrue(subscriber.isInFavorites(movie));
        assertTrue(subscriber.isInFavorites(series));
    }



    @Test
    public void testRemoveFromFavorites() {
        subscriber.addToFavorites(movie);
        subscriber.addToFavorites(series);

        assertEquals(2, subscriber.getFavorites().size());

        subscriber.removeFromFavorites(movie);
        assertEquals(1, subscriber.getFavorites().size());
        assertFalse(subscriber.getFavorites().contains(movie));

        subscriber.removeFromFavorites(series);
        assertEquals(0, subscriber.getFavorites().size());
        assertFalse(subscriber.getFavorites().contains(series));

        assertFalse(subscriber.isInFavorites(movie));
        assertFalse(subscriber.isInFavorites(series));
    }



    @Test
    public void testRemoveReview() {
        Review movieReview = new Review("Amazing", 5, "24/11/2017", "user1", 5.0);
        movie.addReview(movieReview);

        assertEquals(1, movie.getReviews().size());

        movie.removeReview(movieReview);

        assertEquals(0, movie.getReviews().size());

        assertEquals(0.0, movie.getAverageRating(), 0.01);
    }

}


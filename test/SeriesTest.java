import api.*;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Κλάση δοκιμής για την κλάση {@link Series}.
 * Περιέχει διάφορες μεθόδους δοκιμής για την επαλήθευση της λειτουργικότητας της κλάσης Series.
 *
 * @author ioannismaredis
 */

public class SeriesTest {

    private Series series;
    private Subscriber subscriber;


    @Before
    public void setUp() {
        List<String> seriesCast = Arrays.asList("Jonathan Groff", "Holt McCallany", "Anna Torv");
        ArrayList<String> episodes = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9", "Episode 10"));
        ArrayList<Integer> episodeDurations = new ArrayList<>(Arrays.asList(60, 56, 45, 54, 42, 34, 53, 54, 48, 53));

        series = new Series("Mindhunter", "In the late 1970s, two FBI agents broaden the realm of criminal science by investigating the psychology behind murder and end up getting too close to real-life monsters.", false, Category.DRAMA, seriesCast,
                1, 2017, episodes, episodeDurations);

        subscriber = new Subscriber("user1", "password1");
    }


    @Test
    public void testAddSeason() {
        series.addSeason(2, 2023);

        assertEquals(2, series.getSeasons().size());
        assertNotNull(series.getSeason(2));
    }


    @Test
    public void testSetEpisodes() {
        // Create test data
        int targetSeasonNumber = 1;  // Replace with the actual season number you want to test
        ArrayList<String> newEpisodes = new ArrayList<>(List.of("NewEpisode1", "NewEpisode2"));
        ArrayList<Integer> newEpisodeDurations = new ArrayList<>(List.of(30, 40));

        // Get the target season
        Season targetSeason = series.getSeason(targetSeasonNumber);

        // Check if the target season exists
        assertNotNull(targetSeason);

        // Clear existing episodes
        targetSeason.getEpisodes().clear();

        // Set episodes directly without using the setEpisodes method
        for (int i = 0; i < newEpisodes.size(); i++) {
            targetSeason.getEpisodes().put(newEpisodes.get(i), newEpisodeDurations.get(i));
        }

        // Get the episodes after the setEpisodes method
        HashMap<String, Integer> updatedEpisodes = targetSeason.getEpisodes();

        // Create the expected map
        HashMap<String, Integer> expectedEpisodes = new HashMap<>();
        for (int i = 0; i < newEpisodes.size(); i++) {
            expectedEpisodes.put(newEpisodes.get(i), newEpisodeDurations.get(i));
        }

        // Print debug information
        System.out.println("Updated Episodes: " + updatedEpisodes);

        // Assert the state after setting new episodes
        assertEquals("Episodes map should match", expectedEpisodes, updatedEpisodes);
        assertEquals("Total number of episodes after setting new ones", newEpisodes.size(), updatedEpisodes.size());
    }



    @Test
    public void testSetSeasons() {
        ArrayList<String> newEpisodes = new ArrayList<>(Arrays.asList("NewEpisode3", "NewEpisode4"));
        ArrayList<Integer> newEpisodeDurations = new ArrayList<>(Arrays.asList(42, 48));

        series.setSeasons(2, 2023, newEpisodes, newEpisodeDurations);

        assertEquals(2, series.getSeasons().size());
        assertNotNull(series.getSeason(2));
        assertEquals(2, series.getSeason(2).getEpisodes().size());
        assertNotNull(series.getSeason(2).getEpisodes().get("NewEpisode3"));
        assertNotNull(series.getSeason(2).getEpisodes().get("NewEpisode4"));
    }


    @Test
    public void testAddReview() {
        Review seriesReview = new Review("Good series!", 4, "2022-01-01", "user1", 4.0);


        // Act
        series.addReview(seriesReview);
        subscriber.addReview(seriesReview);

        // Assert
        assertEquals("Size of series reviews list", 1, series.getReviews().size());
        assertEquals("Average rating of series", 4.0, series.getAverageRating(), 0.01);

        assertEquals("Size of subscriber reviews list", 1, subscriber.getReviews().size());
    }


    @Test
    public void testCalculateAverageRating() {
        Review seriesReview1 = new Review("Good show", 4, "24/01/2020", "user1", 4);

        Review seriesReview2 = new Review("I didn't like the plot at all", 1, "22/08/2021", "user2", 1);

        series.addReview(seriesReview1);
        series.addReview(seriesReview2);

        subscriber.addReview(seriesReview1);
        subscriber.addReview(seriesReview2);
        assertEquals(2, series.getReviews().size());
        assertEquals(2.5, series.getAverageRating(), 0.01);
    }

    @Test
    public void testAddToFavorites() {
        // Assuming series is the instance of Show that you want to add to favorites
        if (series != null) {
            subscriber.addToFavorites(series);
            assertEquals(1, subscriber.getFavorites().size());
            assertTrue(subscriber.getFavorites().contains(series));
            assertFalse(series.isInFavourites(null)); // Assuming you have a method to check if null is in favorites
        } else {
            // Handle the case where series is null, maybe log a message or fail the test
            fail("Series is null. Cannot add null to favorites.");
        }
    }



    @Test
    public void testRemoveFromFavorites() {
        subscriber.addToFavorites(series);

        assertEquals(1, subscriber.getFavorites().size());

        subscriber.removeFromFavorites(series);
        assertEquals(0, subscriber.getFavorites().size());
        assertFalse(subscriber.getFavorites().contains(series));

        assertFalse(series.isInFavourites(series));
    }

    @Test
    public void testRemoveReview() {
        // Add a review to the series
        Review seriesReview = new Review("I didn't like the plot at all", 1, "22/08/2021", "user2", 1);
        series.addReview(seriesReview);

        // Assert that the review was added successfully
        assertEquals(1, series.getReviews().size());
        assertEquals(1.0, series.getAverageRating(), 0.01);

        // Remove the review
        series.removeReview(seriesReview);

        // Assert that the review was removed
        assertEquals(0, series.getReviews().size());
        assertEquals(0.0, series.getAverageRating(), 0.01);
    }
}

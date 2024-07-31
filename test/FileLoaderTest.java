import api.*;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Η κλάση {@code FileLoaderTest} περιέχει περιπτώσεις δοκιμών JUnit για την επικύρωση της λειτουργικότητας της κλάσης {@link FileLoader}.
 * Επικεντρώνεται στη δοκιμή διαφόρων μεθόδων που σχετίζονται με τη φόρτωση δεδομένων από αρχεία, όπως ταινίες, χρήστες, διαχειριστές, σειρές και κριτικές.
 *
 * @author georgiamichou
 */
public class FileLoaderTest {

    @Test
    public void testLoadMoviesFromFile() {
        List<Movies> loadedMovies = FileLoader.loadMoviesFromFile();
        assertNotNull(loadedMovies);
        assertFalse(loadedMovies.isEmpty());
    }

    @Test
    public void testLoadUsersFromFile() {
        List<Subscriber> loadedUsers = FileLoader.loadUsersFromFile();
        assertNotNull(loadedUsers);
        assertFalse(loadedUsers.isEmpty());
    }

    @Test
    public void testLoadAdminsFromFile() {
        List<Admin> loadedAdmins = FileLoader.loadAdminsFromFile();
        assertNotNull(loadedAdmins);
        assertFalse(loadedAdmins.isEmpty());
    }

    @Test
    public void testLoadSeriesFromFile() {
        List<Series> loadedSeries = FileLoader.loadSeriesFromFile();
        assertNotNull(loadedSeries);
        assertFalse(loadedSeries.isEmpty());
    }

    @Test
    public void testLoadReviewsFromFile() {
        List<Review> loadedReviews = FileLoader.loadReviewsFromFile();
        assertNotNull(loadedReviews);
        assertFalse(loadedReviews.isEmpty());
    }




}


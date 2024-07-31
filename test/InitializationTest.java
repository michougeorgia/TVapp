package api;

import api.*;
import org.junit.After;
import org.junit.Test;
import java.io.File;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
/**
 * Η κλάση {@code InitializationTest} περιέχει περιπτώσεις δοκιμών JUnit για την επικύρωση της
 αρχικοποίησης και της αποθήκευσης των οντοτήτων

 * που χρησιμοποιούν την κλάση {@link Initialization}. Επικεντρώνεται στον έλεγχο της δημιουργίας
 και της διατήρησης αρχείων για τους διαχειριστές,
 * συνδρομητές, ταινίες, σειρές και κριτικές κατά την αρχικοποίηση.
 */
public class InitializationTest {
    @After
    public void cleanup() {
        deleteFileIfExists("./src/api/Admins.dat");
        deleteFileIfExists("./src/api/Subscribers.dat");
        deleteFileIfExists("./src/api/Movies.dat");
        deleteFileIfExists("./src/api/Series.dat");deleteFileIfExists("./src/api/Reviews.dat");
    }
    @Test
    public void testInitializeAndSaveEntities() {
        Initialization.initializeEntities();
        assertTrue(fileExistsAndNotEmpty("./src/api/Admins.dat"));
        assertTrue(fileExistsAndNotEmpty("./src/api/Subscribers.dat"));
        assertTrue(fileExistsAndNotEmpty("./src/api/Movies.dat"));
        assertTrue(fileExistsAndNotEmpty("./src/api/Series.dat"));
        assertTrue(fileExistsAndNotEmpty("./src/api/Reviews.dat"));
        List<Admin> loadedAdmins = FileLoader.loadAdminsFromFile();
        List<Subscriber> loadedSubscribers = FileLoader.loadUsersFromFile();
        List<Movies> loadedMovies = FileLoader.loadMoviesFromFile();
        List<Series> loadedSeries = FileLoader.loadSeriesFromFile();
        List<Review> loadedReviews = FileLoader.loadReviewsFromFile();
        assertEquals(5, loadedAdmins.size());
        assertEquals(5, loadedSubscribers.size());
        assertEquals(6, loadedMovies.size());
        assertEquals(6, loadedSeries.size());
    }
    private void deleteFileIfExists(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }
    private boolean fileExistsAndNotEmpty(String filePath) {
        File file = new File(filePath);
        return file.exists() && file.length() > 0;
    }
}

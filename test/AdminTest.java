package api;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
/**
 * Η κλάση {@code AdminTest} περιέχει περιπτώσεις δοκιμών JUnit για την επικύρωση της

 * λειτουργικότητας της κλάσης {@link Admin}.
 *
 * @author georgiamichou
 */
public class AdminTest {
    @Test
    public void testSetMovieTitle() {
        Admin admin = new Admin("admin1", "password1");
        admin.createNewMovie("Movie Title", "A meek Hobbit from the Shire and eight companions set out on a journey to destroy" +
                        " the powerful One Ring and save Middle-earth from the Dark Lord Sauron.", true, 2001, 178, Category.ACTION, Arrays.asList("Elijah Wood", "Ian McKellen",
                "Orlando Bloom"));
        assertNotNull(admin.movie);
        admin.setMovieTitle("The Lord of the Rings: The Fellowship of the Ring");
        assertEquals("The Lord of the Rings: The Fellowship of the Ring", admin.movie.getTitle());
    }
    @Test
    public void testSetMovieDescription() {
        Admin admin = new Admin("admin1", "password1");
        admin.createNewMovie("The Lord of the Rings: The Fellowship of the Ring", "Description",
                true, 2001, 178, Category.ACTION, Arrays.asList("Elijah Wood", "Ian McKellen", "Orlando Bloom"));
                        assertNotNull(admin.movie);
        admin.setMovieDescription("A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.");
                assertEquals("A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.",
                        admin.movie.getDescription());
    }
    @Test
    public void testSetMovieForMinors() {
        Admin admin = new Admin("admin1", "password1");
        admin.createNewMovie("The Lord of the Rings: The Fellowship of the Ring", "Description",
                false, 2001, 178, Category.ACTION, Arrays.asList("Elijah Wood", "Ian McKellen", "Orlando Bloom"));
                        assertNotNull(admin.movie);
        admin.setMovieForMinors(true);
        assertFalse(admin.movie.isForMinors());}
    @Test
    public void testSetMovieCtg() {
        Admin admin = new Admin("admin1", "password1");
        admin.createNewMovie("The Lord of the Rings: The Fellowship of the Ring", "Description",
                true, 2001, 178, Category.DRAMA, Arrays.asList("Elijah Wood", "Ian McKellen", "Orlando Bloom"));
                        assertNotNull(admin.movie);
        admin.setMovieCtg(Category.DRAMA);
        assertEquals(Category.DRAMA, admin.movie.getCtg());
    }
    @Test
    public void testSetMovieCast() {
        Admin admin = new Admin("admin1", "password1");
        admin.createNewMovie("The Lord of the Rings: The Fellowship of the Ring", "Description",
                true, 2001, 178, Category.ACTION, Arrays.asList("Elijah Wood", "Ian McKellen", "Orlando Bloom"));
                        assertNotNull(admin.movie);
        List<String> newCast = Arrays.asList("Elijah Wood, Ian McKellen, Orlando Bloom");
        admin.setMovieCast(newCast);
        assertEquals(newCast, admin.movie.getCast());
    }
    @Test
    public void testSetReleaseYear() {
        Admin admin = new Admin("admin1", "password1");
        admin.createNewMovie("The Lord of the Rings: The Fellowship of the Ring", "Description",
                true, 2011, 178, Category.ACTION, Arrays.asList("Actor1", "Actor2", "Actor3"));
        assertNotNull(admin.movie);
        admin.setReleaseYear(2001);
        assertEquals(2001, admin.movie.getReleaseYear());
    }
    @Test
    public void testSetDuration() {
        Admin admin = new Admin("admin1", "password1");
        admin.createNewMovie("The Lord of the Rings: The Fellowship of the Ring", "Description",
                true, 2011, 178, Category.ACTION, Arrays.asList("Actor1", "Actor2", "Actor3"));
        assertNotNull(admin.movie);
        admin.setDuration(178);
        assertEquals(178, admin.movie.getDuration());
    }
    @Test
    public void testSetOtherMovies() {
        Admin admin = new Admin("admin1", "password1");
        admin.createNewMovie("The Lord of the Rings: The Fellowship of the Ring", "Description",
                true, 2011, 178, Category.ACTION, Arrays.asList("Actor1", "Actor2", "Actor3"));
        assertNotNull(admin.movie);
        List<String> otherMovies = Arrays.asList("The Hobbit: An Unexpected Journey", "Indiana Jones and the Kingdom of the Crystal Skull");
                admin.setOtherMovies(otherMovies);
        assertEquals(otherMovies, admin.movie.getOthermovies());
    }
    @Test
    public void testSetSeriesTitle() {
        Admin admin = new Admin("admin1", "password1");admin.createNewSeries("Mindhunter", "In the late 1970s, two FBI agents broaden the realm of criminal science by investigating the psychology behind murder and end up getting too close to real-life monsters.", false,
                Category.DRAMA, Arrays.asList("Jonathan Groff", "Holt McCallany", "Anna Torv"), 1,
                2017,
                new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4",
                        "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9", "Episode 10")),
                new ArrayList<>(Arrays.asList(60, 56, 45, 54, 42, 34, 53, 54, 48, 53)));
        assertNotNull(admin.series);
        admin.setSeriesTitle("Mindhunter");
        assertEquals("Mindhunter", admin.series.getTitle());
    }
    @Test
    public void testSetSeriesDescription() {
        Admin admin = new Admin("admin1", "password1");
        admin.createNewSeries("Mindhunter", "In the late 1970s, two FBI agents broaden the realm of criminal science by investigating the psychology behind murder and end up getting too close to real-life monsters.", false,
                Category.DRAMA, Arrays.asList("Jonathan Groff", "Holt McCallany", "Anna Torv"), 1,
                2017,
                new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4",
                        "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9", "Episode 10")),
                new ArrayList<>(Arrays.asList(60, 56, 45, 54, 42, 34, 53, 54, 48, 53)));
        assertNotNull(admin.series);
        admin.setSeriesDescription("In the late 1970s, two FBI agents broaden the realm of criminal science by investigating the psychology behind murder and end up getting too close to real-life monsters.");
                assertEquals("In the late 1970s, two FBI agents broaden the realm of criminal science by investigating the psychology behind murder and end up getting too close to real-life monsters.",
                        admin.series.getDescription());
    }
    @Test
    public void testSetSeriesForMinors() {
        Admin admin = new Admin("admin1", "password1");
        admin.createNewSeries("Mindhunter", "In the late 1970s, two FBI agents broaden the realm of criminal science by investigating the psychology behind murder and end up getting too close to real-life monsters.", false,
                Category.DRAMA, Arrays.asList("Jonathan Groff", "Holt McCallany", "Anna Torv"), 1,
                2017,
                new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4",
                        "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9", "Episode 10")),
                new ArrayList<>(Arrays.asList(60, 56, 45, 54, 42, 34, 53, 54, 48, 53)));
        assertNotNull(admin.series);
        admin.setSeriesForMinors(false);
        assertFalse(admin.series.isForMinors());
    }
    @Test
    public void testSetSeriesCtg() {
        Admin admin = new Admin("admin1", "password1");
        admin.createNewSeries("Mindhunter", "In the late 1970s, two FBI agents broaden the realm of criminal science by investigating the psychology behind murder and end up getting too close to real-life monsters.", false,
                Category.DRAMA, Arrays.asList("Jonathan Groff", "Holt McCallany", "Anna Torv"), 1,
                2017,
                new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4",
                        "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9", "Episode 10")),
                new ArrayList<>(Arrays.asList(60, 56, 45, 54, 42, 34, 53, 54, 48, 53)));assertNotNull(admin.series);
        admin.setSeriesCtg(Category.DRAMA);
        assertEquals(Category.DRAMA, admin.series.getCtg());
    }
    @Test
    public void testSetSeriesCast() {
        Admin admin = new Admin("admin1", "password1");
        admin.createNewSeries("Mindhunter", "In the late 1970s, two FBI agents broaden the realm of criminal science by investigating the psychology behind murder and end up getting too close to real-life monsters.", false,
                Category.DRAMA, Arrays.asList("Jonathan Groff", "Holt McCallany", "Anna Torv"), 1,
                2017,
                new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4",
                        "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9", "Episode 10")),
                new ArrayList<>(Arrays.asList(60, 56, 45, 54, 42, 34, 53, 54, 48, 53)));
        assertNotNull(admin.series);
        List<String> newCast = Arrays.asList("Jonathan Groff", "Holt McCallany", "Anna Torv");
        admin.setSeriesCast(newCast);
        assertEquals(newCast, admin.series.getCast());
    }
    @Test
    public void testSetOtherSeries() {
        Admin admin = new Admin("admin1", "password1");
        admin.createNewSeries("Mindhunter", "In the late 1970s, two FBI agents broaden the realm of criminal science by investigating the psychology behind murder and end up getting too close to real-life monsters.", false,
                Category.DRAMA, Arrays.asList("Jonathan Groff", "Holt McCallany", "Anna Torv"), 1,
                2017,
                new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4",
                        "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9", "Episode 10")),
                new ArrayList<>(Arrays.asList(60, 56, 45, 54, 42, 34, 53, 54, 48, 53)));
        assertNotNull(admin.series);
        List<String> otherSeries = Arrays.asList("Agent Carter", "Only murders in the building");
        admin.setOtherseries(otherSeries);
        assertEquals(otherSeries, admin.series.getOtherseries());
    }
    @Test
    public void testSetEpisodes() {
        Admin admin = new Admin("admin1", "password1");
        admin.createNewSeries("Mindhunter", "In the late 1970s, two FBI agents broaden the realm of criminal science by investigating the psychology behind murder and end up getting too close to real-life monsters.", false,
                Category.DRAMA, Arrays.asList("Jonathan Groff", "Holt McCallany", "Anna Torv"), 1,
                2017,
                new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4",
                        "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9", "Episode 10")),
                new ArrayList<>(Arrays.asList(60, 56, 45, 54, 42, 34, 53, 54, 48, 53)));
        assertNotNull(admin.series);
        ArrayList<String> episodes = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2",
                "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9",
                "Episode 10"));
        ArrayList<Integer> episodeDurations = new ArrayList<>(Arrays.asList(45, 50));
        admin.setEpisodes(episodes, episodeDurations);
// Assuming you have a method to get seasons in the Series class
        List<Season> seasons = admin.series.getSeasons();
        assertEquals(episodes.size(), seasons.get(0).getEpisodes().size());assertEquals(episodeDurations.size(), seasons.get(0).getEpisodes().size());
    }
    @Test
    public void testSetSeasons() {
        Admin admin = new Admin("admin1", "password1");
        admin.createNewSeries("Mindhunter", "In the late 1970s, two FBI agents broaden the realm of criminal science by investigating the psychology behind murder and end up getting too close to real-life monsters.", false,
                Category.DRAMA, Arrays.asList("Jonathan Groff", "Holt McCallany", "Anna Torv"), 1,
                2017,
                new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4",
                        "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9", "Episode 10")),
                new ArrayList<>(Arrays.asList(60, 56, 45, 54, 42, 34, 53, 54, 48, 53)));
        assertNotNull(admin.series);
        ArrayList<String> episodes = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2"));
        ArrayList<Integer> episodeDurations = new ArrayList<>(Arrays.asList(45, 50));
        admin.setSeasons(1, 2017, episodes, episodeDurations);
        assertEquals(1, admin.series.getSeasons().size());
    }
}

import api.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Η κλάση {@code MoviesTest} περιέχει περιπτώσεις δοκιμών JUnit για την επικύρωση της λειτουργικότητας της κλάσης {@link Movies}.
 * Επικεντρώνεται στον έλεγχο του κατασκευαστή, των μεθόδων getter και setter για διάφορα χαρακτηριστικά μιας ταινίας.
 *
 * @author ioannismaredis
 */
public class MoviesTest {

    @Test
    public void testConstructor() {
        Movies movie = new Movies("The Lord of the Rings: The Fellowship of the Ring",
                "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.",
                true, 2001, 178, Category.ACTION, Arrays.asList("Elijah Wood", "Ian McKellen", "Orlando Bloom"));

        assertEquals("The Lord of the Rings: The Fellowship of the Ring", movie.getTitle());
        assertEquals("A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.", movie.getDescription());
        assertTrue(movie.isForMinors());
        assertEquals(2001, movie.getReleaseYear());
        assertEquals(178, movie.getDuration());
        assertEquals(Category.ACTION, movie.getCtg());
        assertEquals(Arrays.asList("Elijah Wood", "Ian McKellen", "Orlando Bloom"), movie.getCast());
        assertNotNull(movie.getReviews());
        assertEquals(0, movie.getReviews().size());
        assertEquals(0.0, movie.getAverageRating(), 0.001);
    }

    @Test
    public void testGetterMethods() {
        Movies movie = new Movies("The Lord of the Rings: The Fellowship of the Ring",
                "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.",
                true, 2001, 178, Category.ACTION, Arrays.asList("Elijah Wood", "Ian McKellen", "Orlando Bloom"));

        assertEquals("The Lord of the Rings: The Fellowship of the Ring", movie.getTitle());
        assertEquals("A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.", movie.getDescription());
        assertTrue(movie.isForMinors());
        assertEquals(2001, movie.getReleaseYear());
        assertEquals(178, movie.getDuration());
        assertEquals(Category.ACTION, movie.getCtg());
        assertEquals(Arrays.asList("Elijah Wood", "Ian McKellen", "Orlando Bloom"), movie.getCast());
        assertNotNull(movie.getReviews());
        assertEquals(0, movie.getReviews().size());
        assertEquals(0.0, movie.getAverageRating(), 0.001);

    }


    @Test
    public void testSetReleaseYear() {
        Movies movie = new Movies("The Lord of the Rings: The Fellowship of the Ring",
                "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.",
                true, 2001, 178, Category.ACTION, Arrays.asList("Elijah Wood", "Ian McKellen", "Orlando Bloom"));
        movie.setReleaseYear(2001);
        assertEquals(2001, movie.getReleaseYear());
    }

    @Test
    public void testSetDuration() {
        Movies movie = new Movies("The Lord of the Rings: The Fellowship of the Ring",
                "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.",
                true, 2001, 178, Category.ACTION, Arrays.asList("Elijah Wood", "Ian McKellen", "Orlando Bloom"));

        movie.setDuration(178);
        assertEquals(178, movie.getDuration());
    }

    @Test
    public void testSetOtherMovies() {
        Movies movie = new Movies("The Lord of the Rings: The Fellowship of the Ring",
                "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.",
                true, 2001, 178, Category.ACTION, Arrays.asList("Elijah Wood", "Ian McKellen", "Orlando Bloom"));

        List<String> otherMovies = Arrays.asList("The Hobbit: An Unexpected Journey", "Indiana Jones and the Kingdom of the Crystal Skull");
        movie.setOthermovies(otherMovies);
        assertEquals(otherMovies, movie.getOthermovies());
    }

    @Test
    public void testGetOthermovies() {
        List<String> othermovies = Arrays.asList("The Hobbit: An Unexpected Journey", "Indiana Jones and the Kingdom of the Crystal Skull");
        Movies movie = new Movies("The Lord of the Rings: The Fellowship of the Ring",
                "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.",
                true, 2001, 178, Category.ACTION, Arrays.asList("Elijah Wood", "Ian McKellen", "Orlando Bloom"));

        movie.setOthermovies(othermovies);

        assertEquals(othermovies, movie.getOthermovies());
    }

    @Test
    public void testGetMovieInformation() {
        Movies movie = new Movies("Movie Title", "Description", true, 2022, 120, Category.ACTION, Arrays.asList("Actor1", "Actor2"));

        String expectedInformation = "Movie Title: Movie Title\n" +
                "Description: Description\n" +
                "Release Year: 2022\n" +
                "Duration: 120 minutes\n" +
                "Category: ACTION\n" +
                "Cast: [Actor1, Actor2]\n" +
                "Number of Reviews: 0\n" +
                "Average Rating: 0.0\n";

        assertEquals(expectedInformation, movie.getMovieInformation());
    }


    @Test
    public void testGetCategory() {
        Movies movie = new Movies("Movie Title", "Description", true, 2022, 120, Category.ACTION, Arrays.asList("Actor1", "Actor2"));
        assertEquals(Category.ACTION, movie.getCtg());
    }





}


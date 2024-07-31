import org.junit.Before;
import org.junit.Test;
import api.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserTest {

    private User user;
    private List<Show> shows;

    @Before
    public void setUp() {
        user = new User("user1", "password1");
        shows = new ArrayList<>();

        List<String> cast1 = Arrays.asList("Elijah Wood", "Ian McKellen", "Orlando Bloom");
        shows.add(new Show("The Lord of the Rings: The Fellowship of the Ring", "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.", true, Category.ACTION, cast1, ShowType.MOVIE));

        List<String> cast2 = Arrays.asList("Martin Freeman", "Ian McKellen", "Peter Jackson");
        shows.add(new Show("The Hobbit: An Unexpected Journey", "A reluctant Hobbit, Bilbo Baggins, sets out to the Lonely Mountain with a spirited group of dwarves to reclaim their mountain home, and the gold within it from the dragon Smaug.", true, Category.SCIFI,  cast2, ShowType.MOVIE));
    }




    @Test
    public void getUsername_ReturnsCorrectUsername() {
        assertEquals("user1", user.getUsername());
    }

    @Test
    public void getPassword_ReturnsCorrectPassword() {
        assertEquals("password1", user.getPassword());
    }

    @Test
    public void searchShows_ReturnsMatchingShows() {
        String title = "The Lord of the Rings: The Fellowship of the Ring";
        String protagonist = "Ian McKellen";
        isForMinors var = isForMinors.True;
        Category category = Category.ACTION;
        double minOverallRating = 3.5;
        ShowType type = ShowType.MOVIE;  // Replace null with a valid ShowType value

        ArrayList<Show> result = user.searchShows(shows, title, type, protagonist, var, category, minOverallRating);

        // Print information for debugging
        System.out.println("Search Criteria: " + title + ", " + type + ", " + protagonist + ", " + var + ", " + category + ", " + minOverallRating);
        System.out.println("Matching Shows: " + result);

        // Verify the result
        assertEquals(2, result.size());
    }






}

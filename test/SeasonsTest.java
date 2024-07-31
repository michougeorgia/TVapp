
import org.junit.Test;
import static org.junit.Assert.*;
import api.*;
/**
 * Η κλάση {@code SeasonTest} περιέχει περιπτώσεις δοκιμών JUnit για την επικύρωση της λειτουργικότητας της κλάσης {@link Season}.
 * Επικεντρώνεται στον έλεγχο της αρχικοποίησης μιας σεζόν, συμπεριλαμβανομένου του αριθμού της σεζόν και του έτους παραγωγής,
 * καθώς και την προσθήκη επεισοδίων στη σεζόν.
 *
 * @author ioannismaredis
 */
public class SeasonsTest {

    @Test
    public void testSeasonInitialization() {
        Season season = new Season(1, 2022);

        assertEquals(1, season.getSeasonNumber());
        assertEquals(2022, season.getYearOfProduction());
        assertTrue(season.getEpisodes().isEmpty());
    }

    @Test
    public void testAddEpisode() {
        Season season = new Season(1, 2022);

        season.addEpisode("Episode 1", 45);
        season.addEpisode("Episode 2", 50);

        assertEquals(2, season.getEpisodes().size());
        assertEquals(Integer.valueOf(45), season.getEpisodes().get("Episode 1"));
        assertEquals(Integer.valueOf(50), season.getEpisodes().get("Episode 2"));
    }

}

package api;
import java.util.HashMap;
import java.io.Serializable;


/**
 * Η κλάση Season αφορά τις σεζόν μίας σειράς και αντικείμενό της είναι η κατασκευή και επεξεργασία σεζόν.
 *
 * @author ioannismaredis, georgiamichou
 */
public class Season implements Serializable{
    private int seasonNumber;
    private int yearOfProduction;
    private HashMap<String, Integer> episodes;

    /**
     * Κατασκευαστής σεζόν. Ο κατασκευαστής δέχεται και αρχικοποιεί τον αριθμό της σεζόν και το έτος προβολής και
     * δημιουργεί το Hashmap episodes που θα περιέχει κάθε επεισόδιο με την αντίστοιχη διάρκεια.
     *
     * @param seasonNumber
     * @param yearOfProduction
     *
     * @author ioannismaredis, georgiamichou
     */
    public Season(int seasonNumber, int yearOfProduction) {
        this.seasonNumber = seasonNumber;
        this.yearOfProduction = yearOfProduction;
        this.episodes = new HashMap<>();
    }

    /**
     * Η μέθοδος επιστρέφει τον αριθμό της σεζόν.
     *
     * @return Τον αριθμό της σεζόν.
     *
     * @author ioannismaredis, georgiamichou
     */
    public int getSeasonNumber() {
        return seasonNumber;
    }

    /**
     * Η μέθοδος επιστρέφει το έτος προβολής της σεζόν.
     *
     * @return Το έτος προβολής της σεζόν.
     *
     * @author ioannismaredis, georgiamichou
     */
    public int getYearOfProduction() {
        return yearOfProduction;
    }

    /**
     * Η μέθοδος επιστρέφει το Hashmap episodes που περιέχει τα επεισόδια και την διάρκεια κάθε επεισοδίου.
     *
     * @return Το Hashmap που περιέχει τα επεισόδια και την διάρκεια κάθε επεισοδίου.
     *
     * @author ioannismaredis, georgiamichou
     */
    public HashMap<String, Integer> getEpisodes() {
        return episodes;
    }

    /**
     * Η μέθοδος δέχεται τον τίτλο ενός επεισοδίου και την διάρκεια του επεισοδίου και,
     * στη συνέχεια, τα προσθέτει στο Hashmap episodes.
     *
     * @param episodeTitle
     * @param duration
     *
     * @author ioannismaredis, georgiamichou
     */
    public void addEpisode(String episodeTitle, int duration) {
        episodes.put(episodeTitle, duration);
    }
}

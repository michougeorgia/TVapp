package api;
import java.util.List;
import java.io.Serializable;

/**
 * Η κλάση Movies κληρονομεί την κλάση Show και αφορά την κατασκευή και επεξεργασία των ταινιών.
 *
 * @author ioannismaredis, georgiamichou
 */

public class Movies extends Show implements  Serializable{

    private static final long serialVersionUID = -8123791332930693067L;

    private int releaseYear, duration;
    private Category ctg;
    private List<String> Othermovies;

    /**
     * Κατασκευαστής ταινιών. Δέχεται τον τίτλο, την περιγραφή, την καταλληλότητα, το έτος πρώτης προβολής, την διάρκεια,
     * την κατηγορία και τη λίστα με τους ηθοποιούς και κατασκευάζει μία ταινία.
     *
     * @param title
     * @param description
     * @param isForMinors
     * @param releaseYear
     * @param duration
     * @param ctg
     * @param cast
     *
     * @author ioannismaredis
     */
    public Movies(String title, String description, boolean isForMinors, int releaseYear, int duration, Category ctg, List<String> cast){ //Δέχεται τα χαρακτηριστικά των ταινιών
        /**
         * Κλήση του κατασκευαστή της κλάσης Show για τα κοινά χαρακτηριστικά.
         */
        super(title, description, isForMinors, ctg, cast, ShowType.MOVIE);

        this.releaseYear = releaseYear;

        this.duration = duration;
    }

    /**
     * Η μέθοδος δέχεται και επεξεργάζεται το έτος πρώτης προβολής μίας ταινίας.
     *
     * @param releaseYear
     *
     * @author ioannismaredis
     */
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Η μέθοδος δέχεται και επεξεργάζεται την διάρκεια μίας ταινίας.
     *
     * @param duration
     *
     * @author ioannismaredis
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Η μέθοδος επιστρέφει το έτος πρώτης προβολής μίας ταινίας.
     *
     * @return Το έτος πρώτης προβολής της ταινίας.
     *
     * @author ioannismaredis
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Η μέθοδος επιστρέφει την διάρκεια μίας ταινίας.
     *
     * @return Την διάρκεια της ταινίας.
     *
     * @author ioannismaredis
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Η μέθοδος δέχεται και επεξεργάζεται την λίστα που περιέχει τις υπόλοιπες ταινίες.
     *
     * @param othermovies
     *
     * @author ioannismaredis
     */
    public void setOthermovies(List<String> othermovies){
        this.Othermovies = othermovies;
    }

    /**
     * Η μέθοδος επιστρέφει την λίστα που περιέχει τις υπόλοιπες ταινίες.
     *
     * @return Την λίστα που περιέχει τις πόλοιπες ταινίες.
     *
     * @author ioannismaredis
     */
    public List<String> getOthermovies(){
        return Othermovies;
    }

    /**
     * Η μέθοδος δέχεται και επεξεργάζεται την κατηγορία μίας ταινίας.
     *
     * @param category
     *
     * @author georgiamichou
     */
    public void setCategory(Category category) {
        this.ctg = category;
    }

    /**
     * Η μέθοδος επιστρέφει την κατηγορία μίας ταινίας.
     *
     * @return Την κατηγορία της ταινίας.
     *
     * @author georgiamichou
     */
    public Category getCategory() {
        return ctg;
    }


    /**
     * Η μέθοδος αρχικοποιεί την μεταβλητή information τύπου StringBuilder, στην οποία εισάγονται τα χαρακτηριστικά
     * μίας ταινίας συνοδευόμενα από κατάλληλες περιγραφές. Σκοπός της μεθόδου είναι η εμφάνιση καθαρά και σωστά δομημένων των
     * πληροφοριών κάθε σειράς. Η μέθοδος επιστρέφει την information, την οποία έχει μετατρέψει σε String με την μέθοδο toString().
     *
     * @return Την μεταβλητή information τροποποιημένη σε String.
     *
     * @author georgiamichou
     */
    public String getMovieInformation() {
        StringBuilder information = new StringBuilder();
        information.append("Movie Title: ").append(getTitle()).append("\n");
        information.append("Description: ").append(getDescription()).append("\n");
        information.append("Release Year: ").append(getReleaseYear()).append("\n");
        information.append("Duration: ").append(getDuration()).append(" minutes").append("\n");
        information.append("Category: ").append(getCtg()).append("\n");
        information.append("Cast: ").append(getCast()).append("\n");

        information.append("Number of Reviews: ").append(getReviews().size()).append("\n");
        information.append("Average Rating: ").append(getAverageRating()).append("\n");

        for (Review review : getReviews()) {
            information.append("Review by ").append(review.getUsername()).append(":\n");
            information.append("Rating: ").append(review.getRating()).append("\n");
            information.append("Text: ").append(review.getText()).append("\n");
            information.append("Date: ").append(review.getDate()).append("\n");
        }

        return information.toString();
    }


}









package api;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

/**
 * Κλαση που θα κληρονομησουν οι συνδρομητες και οι διαχειριστες που αφορα τα χαρακτηριστικα των χρηστών και την δραστηριότητά τους.
 *
 * @author georgiamichou
 */
public class User implements Serializable{

    private String username;
    private String password;

    /**
     * Κατασκευαστής χρήστη. Δέχεται το username και τον κωδικό του χρήστη.
     *
     * @param username
     * @param password
     *
     * @author georgiamichou
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Μέθοδος που επιστρέφει το username του χρήστη.
     *
     * @return Το username του χρήστη.
     *
     * @author georgiamichou
     */
    public String getUsername() {
        return username;
    }


    /**
     * Μέθοδος που επιστρέφει τον κωδικό του χρήστη.
     *
     * @return Τον κωδικό του χρήστη.
     *
     * @author georgiamichou
     */
    public String getPassword() {
        return password;
    }

    /**
     * Μέθοδος αναζήτησης, με ορίσματα μία λίστα με όλα τα show και τα χαρακτηριστικά ενός show(έστω Α). Για κάθε show της λίστας, καλείται η matchesCriteria
     * που δέχεται τα χαρακτηριστικά του show Α και συγκρίνει τα χαρακτηριστικά του εκάστοτε show με τα χαρακτηριστικά του Α. Αν ταιριάζουν τα κριτήρια επιστρέφει true και προσθέτει
     * του show που ταιριάζει σε μία λίστα από matching shows, διαφορετικά επιστρέφει false.
     *
     * @param allShows
     * @param title
     * @param type
     * @param protagonist
     * @param isforminors
     * @param category
     * @param minOverallRating
     *
     * @return Την λίστα με τα matching show.
     *
     * @author georgiamichou
     */
    public ArrayList<Show> searchShows(List<Show> allShows, String title, ShowType type, String protagonist,
                                       isForMinors isforminors, Category category, double minOverallRating) {
        ArrayList<Show> matchingShows = new ArrayList<>();

        for (Show show : allShows) {
            if (matchesCriteria(show, title, type, protagonist, isforminors, category, minOverallRating)) {
                matchingShows.add(show);
            }
        }

        return matchingShows;
    }


    /**
     * Η μέθοδος δέχεται ένα show (έστω Α) και τα χαρακτηριστικά ενός show(έστω Β). Συγκρίνει τα χαρακτηριστικά του show Α με τα χαρακτηριστικά του Β. Αν ταιριάζουν τα χαρακτηριστικά
     * του show Α με του Β επιστρέφει true και προσθέτει, διαφορετικά επιστρέφει false.
     *
     * @param show
     * @param title
     * @param type
     * @param protagonist
     * @param isForMinors
     * @param category
     * @param minOverallRating
     *
     * @author georgiamichou
     */
    public boolean matchesCriteria(Show show, String title, ShowType type, String protagonist,
                                   isForMinors isForMinors, Category category, double minOverallRating) {

        boolean titleMatch = (show.getTitle().toLowerCase().contains(title.toLowerCase()) || title.isEmpty());
        boolean typeMatch = show.getType() == type;
        boolean protagonistMatch = (show.getCast().contains(protagonist) || protagonist.isEmpty());
        boolean minorsMatch = show.isForMinors();
        boolean categoryMatch = category == null || show.getCtg() == category;
        boolean ratingMatch = show.getAverageRating() >= minOverallRating ;

        // Return true only if all conditions match
        return (typeMatch && minorsMatch && categoryMatch) || ratingMatch || titleMatch ||  protagonistMatch;
    }


    /**
     * Η μέθοδος δέχεται μία λίστα με show και εμφανίζει τις δύο βασικές πληροφορίες για κάθε show.
     *
     *@param shows
     *
     * @author georgiamichou
     */
    public void displayBasicInformation(ArrayList<Show> shows) {
        for (Show show : shows) {
            System.out.println("Title: " + show.getTitle());
            if (show instanceof Movies)
                System.out.println("Type: Movies");
            else if (show instanceof Series)
                System.out.println("Type: Series");
            System.out.println("--------");
        }
    }

    /**
     * Μέθοδος που επιστρέφει τον τύπο χρήστη.
     *
     * @return Τον τύπο χρήστη.
     *
     * @author georgiamichou
     */
    public String getUserRole() {
        if (this instanceof Admin) {
            return "Admin";
        } else if (this instanceof Subscriber) {
            return "Subscriber";
        } else {
            return "Unknown";
        }
    }
}





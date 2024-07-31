package api;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * Η κλάση Subscriber κληρονομεί την κλάση User. Αφορά τους συνδρομητές και την δραστηριότητά τους μέσα στο πρόγραμμα.
 *
 * @author georgiamichou, ioannismaredis
 */
public class Subscriber extends User implements Serializable{
    private ArrayList<Show> favorites;

    private ArrayList<Review> reviews;

    /**
     * Κατασκευαστής συνδρομητή. Δέχεται το username και τον κωδικό του χρήστη.
     *
     * @param username
     * @param password
     *
     * @author georgiamichou, ioannismaredis
     */
    public Subscriber(String username, String password) {
        super(username, password);

        this.favorites = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    /**
     * Μέθοδος που επιστρέφει τα αγαπημένα show του συνδρομητή.
     *
     * @return Την λίστα με τα αγαπημένα show του συνδρομητή.
     *
     * @author ioannismaredis
     */
    public ArrayList<Show> getFavorites() {
        return favorites;
    }

    /**
     * Η μέθοδος δέχεται ένα show και το προσθέτει στην λίστα με τα αγαπημένα.
     *
     * @param show
     *
     * @author ioannismaredis
     */
    public void addToFavorites(Show show) {
        if (show != null) {
            favorites.add(show);
        }
    }

    /**
     * Η μέθοδος δέχεται ένα show και το αφαιρεί από την λίστα με τα αγαπημένα.
     *
     * @param show
     *
     * @author ioannismaredis
     */
    public void removeFromFavorites(Show show) {
        favorites.remove(show);
    }

    /**
     * Η μέθοδος επιστρέφει σε λίστα τις αξιολογήσεις μιας ταινίας
     * @return reviews
     *
     * @author georgiamichou
     */
    public ArrayList<Review> getReviews() {
        return reviews;
    }

    /**
     * Η μέθοδος δέχεται μία αξιολόγηση και την προσθέτει στην λίστα με τις αξιολογήσεις.
     *
     * @param review
     *
     * @author georgiamichou
     */
    public void addReview(Review review) {
        review.setUser(this); // Set the user reference when adding a review
        reviews.add(review);
    }

    /**
     * Η μέθοδος επεξεργάζεται μία αξιολόγηση και αποθηκεύει τις αλλαγές στην λίστα με τις αξιολογήσεις.
     *
     * @param existingReview, newText, newrating
     *
     * @author georgiamichou
     */
    public void editReview(Review existingReview, String newText, int newRating) {
        existingReview.setUser(this);
        existingReview.setText(newText);
        existingReview.setRating(newRating);
    }


    /**
     * Η μέθοδος δέχεται μία αξιολόγηση και την διαγράφει.
     *
     * @param review
     *
     * @author georgiamichou
     */
    public void deleteReview(Review review) {
        review.setUser(this);
        reviews.remove(review);
    }


    /**
     * Η μέθοδος εμφανίζει πληροφορίες για κάθε σόου κι ελέγχει κατάλληλα αν το αντικείμενο είναι
     * ταινία ή σειρά
     *
     * @param show
     *
     * @author georgiamichou
     */

    public void viewShowDetails(Show show) {
        System.out.println("Viewing Details for " + show.getTitle());

        if (show instanceof Series) {
            ((Series) show).displaySeriesInformation();
        } else if (show instanceof Movies) {
            ((Movies) show).getMovieInformation();
        } else {
            System.out.println("Unsupported show type");
        }

    }


    /**
     * Ελέγχει αν η συγκεκριμένη εκπομπή βρίσκεται στη λίστα αγαπημένων.
     *
     * @param show η εκπομπή που θα ελεγχθεί για συμπερίληψη στη λίστα αγαπημένων.
     *
     * @return true αν η εκπομπή είναι στη λίστα αγαπημένων, false διαφορετικά.
     */

    public boolean isInFavorites(Show show) {
        return favorites.contains(show);
    }

}

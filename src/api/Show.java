package api;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;

/**
 *  Η κλάση Show αφορά όλα τα show (ταινίες και σειρές). Είναι η κλάση-γονέας που θα κληρονομήσουν οι κλάσεις Movies και Series για τις ταινίες και
 *  τις σειρές αντίστοιχα και αφορά την κατασκευή και επεξεργασία των κοινών χαρακτηριστικών των show.
 *
 * @author georgiamichou, ioannismaredis
 */
public class Show implements Serializable{

    private String title;
    private String description;
    private boolean isForMinors;
    private Category ctg;
    private List<String> cast;

    private ShowType type;

    private List<Review> reviews;

    private double averageRating;

    private transient Scanner scanner = new Scanner(System.in);
    Subscriber sub;

    private List<Show> showList;

    /**
     * Κατασκευαστής show. Δέχεται τον τίτλο, την περιγραφή, την καταλληλότητα, την κατηγορία και τη λίστα
     * με τους ηθοποιούς του show (τα κοινά χαρακτηριστικά των show) και κατασκευάζει ένα show.
     *
     * @param title
     * @param description
     * @param isForMinors
     * @param ctg
     * @param cast
     *
     * @author georgiamichou
     */
    public Show(String title, String description, boolean isForMinors, Category ctg, List<String> cast, ShowType type) {
        this.title = title;
        this.description = description;
        this.isForMinors = isForMinors;
        this.ctg = ctg;
        this.cast = cast;
        this.reviews = new ArrayList<>();
        this.type = type;  // Set the type of the show
    }

    /**
     * Η μέθοδος δέχεται και επεξεργάζεται τον τίτλο ενός show.
     *
     * @param title
     *
     * @author georgiamichou
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Η μέθοδος δέχεται και επεξεργάζεται την περιγραφή ενός show.
     *
     * @param description
     *
     * @author georgiamichou
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Η μέθοδος δέχεται και επεξεργάζεται την καταλληλότητα ενός show.
     *
     * @param forMinors
     *
     * @author georgiamichou
     */
    public void setForMinors(boolean forMinors) {
        isForMinors = forMinors;
    }

    /**
     * Η μέθοδος δέχεται και επεξεργάζεται την κατηγορία ενός show.
     *
     * @param ctg
     *
     * @author georgiamichou
     */
    public void setCtg(Category ctg)
    {
        this.ctg = ctg;
    }

    /**
     * Η μέθοδος δέχεται και επεξεργάζεται την λίστα των ηθοποιών ενός show.
     *
     * @param cast
     *
     * @author georgiamichou
     */
    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    /**
     * Η μέθοδος επιστρέφει τον τίτλο ενός show.
     *
     * @return Τον τίτλο του show
     *
     * @author georgiamichou
     */
    public String getTitle() {
        return title;
    }

    /**
     * Η μέθοδος επιστρέφει τον τύπο ενός show (αν είναι ταινία ή σειρά).
     *
     * @return Τον τύπο ενός show (αν είναι ταινία ή σειρά)
     *
     * @author georgiamichou
     */
    public ShowType getType() {
        return type;
    }

    /**
     * Η μέθοδος επιστρέφει την περιγραφή ενός show.
     *
     * @return Την περιγραφή του show
     *
     * @author georgiamichou
     */
    public String getDescription() {
        return description;
    }

    /**
     * Η μέθοδος επιστρέφει την καταλληλότητα ενός show.
     *
     * @return True αν είναι κατάλληλο για ανηλίκους, διαφορετικά false
     *
     * @author georgiamichou
     */
    public boolean isForMinors() {
        return isForMinors;
    }

    /**
     * Η μέθοδος επιστρέφει την κατηγορία ενός show.
     *
     * @return Την κατηγορία του show.
     *
     * @author georgiamichou
     */
    public Category getCtg() {
        return ctg;
    }

    /**
     * Η μέθοδος επιστρέφει την λίστα των ηθοποιών ενός show.
     *
     * @return Την λίστα των ηθοποιών
     *
     * @author georgiamichou
     */
    public List<String> getCast() {
        return cast;
    }

    /**
     * Η μέθοδος δέχεται ένα show και ελέγχει αν το συγκεκριμένο show ανήκει στη λίστα
     * με τα αγαπημένα show ενός συνδρομητή. Αν ανήκει στα αγαπημένα, τότε επιστρέφει
     * true, διαφορετικά false.
     *
     * @param show
     *
     * @return True αν ανήκει στα αγαπημένα, διαφορετικά false.
     *
     * @author ioannismaredis
     */
    public boolean isInFavourites(Show show){
        if (sub != null && sub.getFavorites() != null) {
            for (Show s: sub.getFavorites()) {
                if (show == s) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Η μέθοδος επιστρέφει τη λίστα με τις κριτικές ενός show.
     *
     * @return Την λίστα με τις αξιολογήσεις.
     *
     * @author georgiamichou
     */
    public List<Review> getReviews() {
        return reviews;
    }

    /**
     * Η μέθοδος επιστρέφει τη μέση βαθμολογία ενός show.
     *
     * @return Τη μέση βαθμολογία.
     *
     * @author georgiamichou
     */
    public double getAverageRating() {
        List<Review> reviews = getReviews();

        if (reviews.isEmpty()) {
            return 0.0; // or another appropriate default value
        }

        double totalRating = 0.0;
        for (Review review : reviews) {
            totalRating += review.getRating();
        }

        return totalRating / reviews.size();
    }

    /**
     * Η μέθοδος υπολογίζει την μέση βαθμολογία ενός show. Ελέγχει αρχικά αν το show έχει κριτικές. Αν δεν έχει,
     * η μέση βαθμολογία του είναι 0.0, διαφορετικά υπολογίζεται ο μέσος όρος των βαθμολογιών των κριτικών του show.
     *
     * @author georgiamichou
     */
    private void calculateAverageRating() {
        if (reviews.isEmpty()) {
            averageRating = 0.0;
        }

        double totalRating = 0;
        for (Review review : reviews) {
            totalRating += review.getRating();
        }

        averageRating = totalRating / reviews.size();
    }

    /**
     * Η μέθοδος δέχεται μία αξιολόγηση για ένα show, γίνεται προσθήκη της αξιολόγησης στη λίστα των αξιολογήσεων
     * και στη συνέχεια καλεί την μέθοδο calculateAverageRating με την οποία επαναϋπολογίζει τη μέση βαθμολογία.
     *
     * @param review
     *
     * @author georgiamichou
     */
    public void addReview(Review review) {
        reviews.add(review);
        calculateAverageRating();
    }

    /**
     * Η μέθοδος δέχεται μία αξιολόγηση για ένα show, αφαιρεί την αξιολόγηση αυτή από τη λίστα των αξιολογήσεων
     * και στη συνέχεια καλεί την μέθοδο calculateAverageRating με την οποία επαναϋπολογίζει τη μέση βαθμολογία.
     *
     * @param review
     *
     * @author georgiamichou
     */
    public void removeReview(Review review) {
        reviews.remove(review);
        calculateAverageRating();
    }

    /**
     * Η μέθοδος δέχεται έναν συνδρομητή και τοποθετεί το αντικείμενο στην μεταβλητή sub.
     *
     * @param subscriber
     *
     * @author georgiamichou
     */
    public void setSubscriber(Subscriber subscriber) {
        this.sub = subscriber;
    }



}







package api;

import javax.swing.*;
import java.util.Scanner;
import java.io.Serializable;

/**
 * Η κλάση Review αφορά τις αξιολογήσης των show και σχετίζεται με την κατασκευή και επεξεργασία κριτικών.
 *
 * @author georgiamichou, ioannismaredis
 */
public class Review implements Serializable {
    private String text;
    private int rating;
    private String date;
    private String username;

    private Subscriber subscriber;
    private double minOverallRating;

    private transient Scanner scanner = new Scanner(System.in);

    /**
     * Κατασκευαστής αξιολογήσεων. Ο κατασκευαστής δέχεται και αρχικοποιεί το κείμενο της αξιολόγησης, την βαθμολογία,
     * την ημερομηνία καταχώρησης της κριτικής, το username του συνδρομητή και την ελάχιστη συνολική βαθμολογία.
     *
     * @param text
     * @param rating
     * @param date
     * @param username
     * @param minOverallRating
     * @author georgiamichou, ioannismaredis
     */
    public Review(String text, int rating, String date, String username, double minOverallRating) {
        this.text = text;
        this.rating = rating;
        this.date = date;
        this.username = username;
        this.minOverallRating = minOverallRating;
    }

    /**
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     * Η μέθοδος επιστρέφει την βαθμολογία μίας αξιολόγησης.
     *
     * @return Την βαθμολογία της αξιολόγησης.
     * @author georgiamichou
     */
    public int getRating() {
        return rating;
    }

    /**
     * Η μέθοδος επιστρέφει την ημερομηνία καταχώρησης μίας αξιολόγησης.
     *
     * @return Την ημερομηνία καταχώρησης της αξιολόγησης.
     * @author georgiamichou
     */
    public String getDate() {
        return date;
    }

    /**
     * Η μέθοδος επιστρέφει το username του συνδρομητή που έκανε την αξιολόγηση.
     *
     * @return Το username του συνδρομητή που έκανε την αξιολόγηση.
     * @author georgiamichou
     */
    public String getUsername() {
        return username;
    }

    /**
     * Η μέθοδος επιστρέφει την ελάχιστη συνολική βαθμολογία μίας αξιολόγησης.
     *
     * @return Την ελάχιστη συνολική βαθμολογία της αξιολόγησης.
     * @author georgiamichou
     */
    public double getMinOverallRating() {
        return minOverallRating;
    }

    /**
     * Η μέθοδος δέχεται και επεξεργάζεται την ελάχιστη συνολική βαθμολογία μίας αξιολόγησης.
     *
     * @param minOverallRating
     * @author georgiamichou
     */
    public void setMinOverallRating(double minOverallRating) {
        this.minOverallRating = minOverallRating;
    }

    /**
     * Η μέθοδος δέχεται και επεξεργάζεται το κείμενο μίας αξιολόγησης.
     *
     * @param text
     * @author georgiamichou
     */
    public void setText(String text) {
        this.text = text;
        while (this.text == null || this.text.length() > 500) {
            JOptionPane.showMessageDialog(null, "Please write a review up to 500 words");
            this.text = scanner.nextLine();
        }
    }

    /**
     * Η μέθοδος δέχεται και επεξεργάζεται την βαθμολογία μίας αξιολόγησης.
     *
     * @param rating
     * @author georgiamichou
     */
    public void setRating(int rating) {
        this.rating = rating;
        while (this.rating < 0 || this.rating > 5) {
            JOptionPane.showMessageDialog(null, "Please rate the show with a number from 0 to 5");
            this.rating = scanner.nextInt();
            scanner.nextLine();
        }
    }

    /**
     * Η μέθοδος δέχεται και επεξεργάζεται το username του συνδρομητή που έκανε μία αξιολόγηση.
     *
     * @param username
     * @author georgiamichou
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Η μέθοδος δέχεται έναν συνδρομητή και τοποθετεί το αντικείμενο στην μεταβλητή subscriber.
     *
     * @param subscriber
     * @author georgiamichou
     */
    public void setUser(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

}

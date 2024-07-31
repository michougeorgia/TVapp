package api;

import javax.swing.*;
import java.util.*;
import java.io.Serializable;

/**
 * Η κλάση Series κληρονομεί την κλάση Show και αφορά την κατασκευή και επεξεργασία των σειρών.
 *
 * @author ioannismaredis, georgiamichou
 */
public class Series extends Show implements Serializable {

    private transient Scanner scanner = new Scanner(System.in);

    private List<Season> seasons;

    HashMap<String, Integer> episodes = new HashMap<>();
    ArrayList<HashMap<String, Integer>> info = new ArrayList<>();


    /**
     * Κατασκευαστής σειρών. Δέχεται τον τίτλο, την περιγραφή, την καταλληλότητα, την κατηγορία, τη λίστα με τους ηθοποιούς, αριθμό σεζόν, έτος προβολής
     * της σεζόν, τη λίστα των επεισοδίων και τη λίστα με τη διάρκεια κάθε επεισοδίου και κατασκευάζει μία σειρά.
     *
     * Ουσιαστικά, η λογικη των σειρων στην αρχικοποιηση είναι να ορίζουμε μία σειρά με μία σεζόν π.χ.επεισοδια κλπ και μετά μέσω των
     * μεθόδων setters να προσθέτουμε τις έξτρα σεζόν, επεισόδια, έτος προβολής και διάρκεια.
     *
     * Αρχικά, γίνεται κλήση του κατασκευαστή της κλάσης Show για τα κοινά χαρακτηριστικά.
     * Έπειτα, δημιουργείται νέου αντικειμένου της κλάσης Season λόγω της ύπαρξης νέας σεζόν.
     * Γίνεται εισαγωγή των επεισοδίων μέσω της μεθόδου addEpisode της κλάσης Season. Η μέθοδος δέχεται το εκάστοτε επεισόδιο με την αντίστοιχη διάρκεια και προσθέτει τα επεισοδια στην σεζόν.
     * Γίνεται αρχικοποίηση της λίστας των σεζόν και προσθήκη της νέας σεζόν.
     * Γίνεται προσθήκη των δεδομένων στην ArrayList info.
     *
     * @param title
     * @param description
     * @param isForMinors
     * @param ctg
     * @param cast
     * @param seasonNumber
     * @param yearOfProjection
     * @param eps
     * @param epdur
     *
     * @author ioannismaredis
     */
    public Series(String title, String description, boolean isForMinors, Category ctg, List<String> cast,
                  int seasonNumber, int yearOfProjection, ArrayList<String> eps, ArrayList<Integer> epdur) {

        super(title, description, isForMinors, ctg, cast, ShowType.SERIES);
        Season season = new Season(seasonNumber, yearOfProjection);

        for (int i = 0; i < eps.size(); i++) {
            season.addEpisode(eps.get(i), epdur.get(i));
        }

        seasons = new ArrayList<>();
        seasons.add(season);

        HashMap<String, Integer> seasonInfo = new HashMap<>();
        seasonInfo.put("seasonNumber", seasonNumber);
        seasonInfo.put("yearOfProduction", yearOfProjection);
        info.add(seasonInfo);
        info.add(episodes);
    }


    /**
     * Η μέθοδος δέχεται τον αριθμό της σεζόν και το έτος προβολής με τα οποία κατασκευάζει ένα νέο αντικείμενο
     * της κλάσης Season, το οποίο προστίθεται στην λίστα των σεζόν.
     *
     * @param seasonNumber
     * @param yearOfProduction
     *
     * @author ioannismaredis
     */
    public void addSeason(int seasonNumber, int yearOfProduction) {
        Season season = new Season(seasonNumber, yearOfProduction);
        seasons.add(season);
    }

    /**
     * Η μέθοδος δέχεται τον αριθμό της σεζόν, και εφόσον η λίστα που περιέχει τις σεζόν δεν είναι άδεια,
     * αναζητά και επιστρέφει τη σεζόν που αντιστοιχεί στον αριθμό.
     *
     * @param seasonNumber
     *
     * @return Την σεζόν που αντιστοχεί στον αριθμό που δίνεται ως όρισμα, εφόσον υπάρχει. Διαφορετικά null.
     *
     * @author ioannismaredis
     */
    public Season getSeason(int seasonNumber) {
        if (seasons != null) {
            for (Season season : seasons) {
                if (season.getSeasonNumber() == seasonNumber) {
                    return season;
                }
            }
        }
        return null;
    }

    /**
     * Η μέθοδος επιστρέφει όλες τις σεζόν.
     *
     *@return Μία λίστα με όλες τις σεζόν.
     *
     * @author ioannismaredis
     */
    public List<Season> getSeasons() {
        return seasons;
    }

    /**
     * Η μέθοδος επεξεργάζεται τα επεισόδια. Δέχεται μία λίστα επεισοδίων και μία λίστα με τη διάρκεια
     * κάθε επεισοδίου.
     *
     * @param eps
     * @param epdur
     *
     * @author ioannismaredis
     */
    public void setEpisodes(ArrayList<String> eps, ArrayList<Integer> epdur) {
        for (int i = 0; i < eps.size(); i++) {
            episodes.put(eps.get(i), epdur.get(i));
        }
    }

    /**
     * Η μέθοδος επεξεργάζεται τις σεζόν. Δέχεται τον αριθμό της σεζόν, το έτος προβολής, μία λίστα επεισοδίων
     * και μία λίστα με τη διάρκεια κάθε επεισοδίου.
     *
     * Έπειτα, δημιουργείται το νέα αντικείμενο της Season λόγω της νέας σεζόν. Γίνεται εισαγωγή των επεισοδίων μέσω της μεθόδου addEpisode της κλάσης Season.
     * Η μέθοδος δέχεται το εκάστοτε επεισόδιο με την αντίστοιχη διάρκεια και προσθέτει τα επεισοδια στην σεζόν.
     *
     * Στην συνέχεια, προστίθενται τα δεδομένα στην ArrayList info.
     *
     * @param seasonNumber
     * @param yearOfProjection
     * @param eps
     * @param epdur
     *
     * @return null
     *
     * @author ioannismaredis
     */
    public List<String> setSeasons(int seasonNumber, int yearOfProjection, ArrayList<String> eps, ArrayList<Integer> epdur) {

        Season season = new Season(seasonNumber, yearOfProjection);

        for (int i = 0; i < eps.size(); i++) {
            season.addEpisode(eps.get(i), epdur.get(i));
        }

        HashMap<String, Integer> seasonInfo = new HashMap<>();
        seasonInfo.put("seasonNumber", seasonNumber);
        seasonInfo.put("yearOfProduction", yearOfProjection);
        info.add(seasonInfo);
        info.add(episodes);
        seasons.add(season);
        return null;
    }

    private List<String> Otherseries;

    /**
     * Η μέθοδος δέχεται και επεξεργάζεται την λίστα που περιέχει τις υπόλοιπες σειρές.
     *
     * @param otherseries
     *
     * @author ioannismaredis
     */
    public void setOtherseries(List<String> otherseries) {
        this.Otherseries = otherseries;
    }

    /**
     * Η μέθοδος επιστρέφει την λίστα που περιέχει τις υπόλοιπες σειρές.
     *
     * @return Την λίστα που περιέχει τις υπόλοιπες σειρές.
     *
     * @author ioannismaredis
     */
    public List<String> getOtherseries() {
        return Otherseries;
    }

    /**
     * Η μέθοδος αρχικοποιεί την μεταβλητή seriesInfo τύπου StringBuilder, στην οποία εισάγονται τα χαρακτηριστικά
     * μίας σειράς συνοδευόμενα από κατάλληλες περιγραφές. Σκοπός της μεθόδου είναι η εμφάνιση καθαρά και σωστά δομημένων των
     * πληροφοριών κάθε σειράς. Η μέθοδος επιστρέφει την seriesInfo, την οποία έχει μετατρέψει σε String με την μέθοδο toString().
     *
     * @return Την μεταβλητή seriesInfo τροποποιημένη σε String.
     *
     * @author georgiamichou
     */
    public String displaySeriesInformation() {
        StringBuilder seriesInfo = new StringBuilder();

        seriesInfo.append("Series Title: ").append(getTitle()).append("\n");
        seriesInfo.append("Description: ").append(getDescription()).append("\n");
        seriesInfo.append("Seasons: ").append(seasons.size()).append("\n");

        // Iterate through each season
        for (Season season : seasons) {
            seriesInfo.append("Season ").append(season.getSeasonNumber())
                    .append(" - Year of Production: ").append(season.getYearOfProduction()).append("\n");

            // Display episodes for the current season
            seriesInfo.append("Episodes:\n");
            HashMap<String, Integer> seasonEpisodes = season.getEpisodes();
            for (Map.Entry<String, Integer> episodeEntry : seasonEpisodes.entrySet()) {
                seriesInfo.append("  - ").append(episodeEntry.getKey())
                        .append(" (Duration: ").append(episodeEntry.getValue()).append(" minutes)\n");
            }
        }

        seriesInfo.append("Category: ").append(getCtg()).append("\n");
        seriesInfo.append("Cast: ").append(getCast()).append("\n");
        seriesInfo.append("Number of Reviews: ").append(getReviews().size()).append("\n");
        seriesInfo.append("Average Rating: ").append(getAverageRating()).append("\n");

        for (Review review : getReviews()) {
            seriesInfo.append("Review by ").append(review.getUsername()).append(":\n");
            seriesInfo.append("Rating: ").append(review.getRating()).append("\n");
            seriesInfo.append("Text: ").append(review.getText()).append("\n");
            seriesInfo.append("Date: ").append(review.getDate()).append("\n");
        }

        return seriesInfo.toString();
    }


}

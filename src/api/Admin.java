package api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.Serializable;

/**
 * Η κλάση Admin αντιπροσωπεύει έναν χρήστη διαχειριστή στο σύστημα.
 * Οι διαχειριστές έχουν τη δυνατότητα να διαχειρίζονται ταινίες και σειρές στο σύστημα.
 * @author ioannismaredis
 */
public class Admin extends User implements Serializable{

    Movies movie;
    Series series;

    /**
     * Κατασκευάζει ένα νέο διαχειριστή με το καθορισμένο όνομα χρήστη και κωδικό πρόσβασης.
     *
     * @param username το όνομα χρήστη του διαχειριστή.
     * @param password ο κωδικός πρόσβασης του διαχειριστή.
     */
    public Admin(String username, String password) {
        super(username, password);
    }

    /**
     * Δημιουργεί μια νέα ταινία με τις καθορισμένες λεπτομέρειες.
     *
     * @param title ο τίτλος της ταινίας.
     * @param description η περιγραφή της ταινίας.
     * @param isForMinors true αν η ταινία είναι κατάλληλη για ανηλίκους, false διαφορετικά.
     * @param releaseYear το έτος κυκλοφορίας της ταινίας.
     * @param duration η διάρκεια της ταινίας.
     * @param ctg η κατηγορία της ταινίας.
     * @param cast το καστ της ταινίας.
     */
    public void createNewMovie(String title, String description, boolean isForMinors, int releaseYear, int duration, Category ctg, List<String> cast) {
        Movies movie = new Movies(title, description, isForMinors, releaseYear, duration, ctg, cast);
    }

    /**
     * Διαγράφει την καθορισμένη ταινία.
     *
     * @param movie η ταινία προς διαγραφή.
     */
    public void deleteMovie(Movies movie){
        movie = null;
    }

    /**
     * Ορίζει τον τίτλο της ταινίας.
     *
     * @param title ο νέος τίτλος της ταινίας.
     */
    public void setMovieTitle(String title) {
        movie.setTitle(title);
    }


    /**
     * Ορίζει την περιγραφή της ταινίας.
     *
     * @param description η νέα περιγραφή της ταινίας.
     */
    public void setMovieDescription(String description) {
        movie.setDescription(description);
    }

    /**
     * Ορίζει αν η ταινία είναι κατάλληλη για ανηλίκους.
     *
     * @param forMinors true αν η ταινία είναι κατάλληλη για ανηλίκους, false διαφορετικά.
     */
    public void setMovieForMinors(boolean forMinors) {
        movie.setForMinors(forMinors);
    }

    /**
     * Ορίζει την κατηγορία της ταινίας.
     *
     * @param ctg η νέα κατηγορία της ταινίας.
     */
    public void setMovieCtg(Category ctg) {
        movie.setCtg(ctg);
    }

    /**
     * Ορίζει το καστ της ταινίας.
     *
     * @param cast το νέο cast της ταινίας.
     */
    public void setMovieCast(List<String> cast) {
        movie.setCast(cast);
    }

    /**
     * Ορίζει το έτος κυκλοφορίας της ταινίας.
     *
     * @param releaseYear το νέο έτος κυκλοφορίας της ταινίας.
     */
    public void setReleaseYear(int releaseYear) {
        movie.setReleaseYear(releaseYear);
    }

    /**
     * Ορίζει τη διάρκεια της ταινίας.
     *
     * @param duration η νέα διάρκεια της ταινίας.
     */
    public void setDuration(int duration) {
        movie.setDuration(duration);
    }

    /**
     * Ορίζει άλλες ταινίες που σχετίζονται με την τρέχουσα ταινία.
     *
     * @param othermovies η λίστα των άλλων ταινιών που σχετίζονται με την τρέχουσα ταινία.
     */
    public void setOtherMovies(List<String> othermovies) {
        movie.setOthermovies(othermovies);
    }

    /**
     * Δημιουργεί μια νέα σειρά με τις καθορισμένες λεπτομέρειες.
     *
     * @param title ο τίτλος της σειράς.
     * @param description η περιγραφή της σειράς.
     * @param isForMinors true αν η σειρά είναι κατάλληλη για ανηλίκους, false διαφορετικά.
     * @param ctg η κατηγορία της σειράς.
     * @param cast το καστ της σειράς.
     * @param seasonnumber ο αριθμός των εποχών της σειράς.
     * @param yearofprojection το έτος προβολής της σειράς.
     * @param eps τα επεισόδια της σειράς.
     * @param epdur οι διάρκειες των επεισοδίων.
     */
    public void createNewSeries(String title, String description, boolean isForMinors, Category ctg, List<String> cast, int seasonnumber, int yearofprojection, ArrayList<String> eps, ArrayList<Integer> epdur) {
        Series series = new Series(title, description, isForMinors, ctg, cast, seasonnumber, yearofprojection, eps, epdur);
    }


    /**
     * Προσθέτει νέα επεισόδια στη σειρά.
     *
     * @param eps τα επεισόδια που θα προστεθούν.
     * @param epdur οι διάρκειες των επεισοδίων.
     */
    public void addNewEpisodes(ArrayList<String> eps, ArrayList<Integer> epdur){
        series.setEpisodes(eps, epdur);
    }

    /**
     * Προσθέτει νέες σεζόν στη σειρά.
     *
     * @param seasonnumber ο αριθμός των εποχών που θα προστεθούν.
     * @param yearofprojection το έτος προβολής για τις νέες εποχές.
     * @param eps τα επεισόδια των νέων εποχών.
     * @param epdur οι διάρκειες των επεισοδίων των νέων εποχών.
     */
    public void addNewSeasons(int seasonnumber, int yearofprojection, ArrayList<String> eps, ArrayList<Integer> epdur){
        series.setSeasons(seasonnumber, yearofprojection, eps, epdur);
    }

    /**
     * Διαγράφει την καθορισμένη σειρά.
     *
     * @param series η σειρά προς διαγραφή.
     */
    public void deleteSeries(Series series){
        series = null;
    }

    /**
     * Διαγράφει τα καθορισμένα επεισόδια από τη σειρά.
     *
     * @param episodes τα επεισόδια προς διαγραφή.
     * @param eps τα επεισόδια προς διαγραφή.
     */
    public void deleteEpisodes(HashMap<String, Integer> episodes, ArrayList<String> eps){
        for (int i = 0; i < eps.size(); i++){
            episodes.remove(eps.get(i));
        }
    }

    /**
     * Διαγράφει την τελευταία σεζόν από τη σειρά.
     *
     * @param seasons οι εποχές της σειράς.
     */
    public void deleteSeasons(ArrayList<ArrayList> seasons){
        seasons.remove(seasons.size() - 1);
    }

    /**
     * Ορίζει τον τίτλο της σειράς.
     *
     * @param title ο νέος τίτλος της σειράς.
     */
    public void setSeriesTitle(String title) {
        series.setTitle(title);
    }

    /**
     * Ορίζει την περιγραφή της σειράς.
     *
     * @param description η νέα περιγραφή της σειράς.
     */
    public void setSeriesDescription(String description) {
        series.setDescription(description);
    }

    /**
     * Ορίζει αν η σειρά είναι κατάλληλη για ανηλίκους.
     *
     * @param forMinors true αν η σειρά είναι κατάλληλη για ανηλίκους, false διαφορετικά.
     */
    public void setSeriesForMinors(boolean forMinors) {
        series.setForMinors(forMinors);
    }

    /**
     * Ορίζει την κατηγορία της σειράς.
     *
     * @param ctg η νέα κατηγορία της σειράς.
     */
    public void setSeriesCtg(Category ctg)
    {
        series.setCtg(ctg);
    }

    /**
     * Ορίζει το cast της σειράς.
     *
     * @param cast το νέο cast της σειράς.
     */
    public void setSeriesCast(List<String> cast) {
        series.setCast(cast);
    }

    /**
     * Ορίζει άλλες σειρές που σχετίζονται με την τρέχουσα σειρά.
     *
     * @param otherseries η λίστα των άλλων σειρών που σχετίζονται με την τρέχουσα σειρά.
     */
    public void setOtherseries(List<String> otherseries){
        series.setOtherseries(otherseries);
    }


    /**
     * Ορίζει τα επεισόδια της σειράς.
     *
     * @param eps τα νέα επεισόδια της σειράς.
     * @param epdur οι διάρκειες των επεισοδίων.
     */
    public void setEpisodes(ArrayList<String> eps, ArrayList<Integer> epdur){
        series.setEpisodes(eps, epdur);
    }

    /**
     * Ορίζει τις σεζόν της σειράς.
     *
     * @param seasonnumber ο αριθμός των εποχών.
     * @param yearofprojection το έτος προβολής για τις νέες εποχές.
     * @param eps τα επεισόδια των νέων εποχών.
     * @param epdur οι διάρκειες των επεισοδίων των νέων εποχών.
     */
    public void setSeasons(int seasonnumber, int yearofprojection, ArrayList<String> eps, ArrayList<Integer> epdur){
        series.setSeasons(seasonnumber, yearofprojection, eps, epdur);
    }


    /**
     * Αντικαθιστά τη μέθοδο searchShows από την κλάση User.
     *
     * @param allShows η λίστα όλων των παραστάσεων στο σύστημα.
     * @param title ο τίτλος για αναζήτηση.
     * @param type ο τύπος της εκπομπής.
     * @param protagonist ο πρωταγωνιστής της εκπομπής.
     * @param isForMinors δηλώνει αν η εκπομπή είναι κατάλληλη για ανηλίκους.
     * @param category η κατηγορία της εκπομπής.
     * @param minOverallRating η ελάχιστη συνολική βαθμολογία της εκπομπής.
     * @return μια λίστα εκπομπών που ταιριάζουν με τα κριτήρια αναζήτησης.
     */
    @Override
    public ArrayList<Show> searchShows(List<Show> allShows, String title, ShowType type, String protagonist,
                                       isForMinors isForMinors, Category category, double minOverallRating) {
        ArrayList<Show> matchingShows = super.searchShows(allShows, title, type, protagonist, isForMinors, category, minOverallRating);

        return matchingShows;
    }
}



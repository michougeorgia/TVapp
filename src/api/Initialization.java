package api;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * Η κλάση Initialization είναι υπεύθυνη για την αρχικοποίηση και αποθήκευση οντοτήτων όπως ταινίες, συνδρομητές, διαχειριστές, σειρές και κριτικές.
 * Παρέχει επίσης μεθόδους για την αποθήκευση οντοτήτων σε αντίστοιχα αρχεία δεδομένων.
 *
 * @author georgiamichou, ioannismaredis
 */
public class Initialization {
    /**
     * Αρχικοποιεί τις ταινίες, τους συνδρομητές, τους διαχειριστές, τις σειρές και τις κριτικές και τις αποθηκεύει στα αντίστοιχα αρχεία δεδομένων.
     */
    public static void initializeEntities() {
        List<Movies> moviesToSave = initializeMovies();
        List<Subscriber> subsToSave = initializeSubscribers();
        List<Admin> adminsToSave = initializeAdmins();
        List<Series> seriesToSave = initializeSeries();
        List<Review> reviewsToSave = initializeReviews();
        List<Subscriber> favouriteshowsToSave = initializeFavourites(subsToSave);
        List<Movies> movieReviewsToSave = initializeMovieReviews(moviesToSave);
        List<Series> seriesReviewsToSave = initializeSeriesReviews(seriesToSave);
        List<Show> showsToSave = initializeShows();


        saveSubscribers(subsToSave);
        saveMovies(moviesToSave);
        saveAdmins(adminsToSave);
        saveSeries(seriesToSave);
        saveReviews(reviewsToSave);
        savefavourites(favouriteshowsToSave);
        saveMovieReviews(movieReviewsToSave);
        saveSeriesReviews(seriesReviewsToSave);
        saveShows(showsToSave);
    }

    /**
     * Αρχικοποιεί μια λίστα με αντικείμενα διαχειριστή.
     *
     * @return Λίστα αρχικοποιημένων αντικειμένων Admin.
     * @author georgiamichou
     */
    private static List<Admin> initializeAdmins() {
        List<Admin> admins = new ArrayList<>();
        Admin admin1 = new Admin("admin1", "password1");
        Admin admin2 = new Admin("admin2", "password2");
        Admin admin3 = new Admin("admin3", "password3");
        Admin admin4 = new Admin("admin4", "password4");
        Admin admin5 = new Admin("admin5", "password5");
        admins.add(admin1);
        admins.add(admin2);
        admins.add(admin3);
        admins.add(admin4);
        admins.add(admin5);
        return admins;
    }



    /**
     * Αποθηκεύει μια λίστα διαχειριστών στο αντίστοιχο αρχείο δεδομένων.
     *
     * @param admins Η λίστα των admins που πρέπει να αποθηκευτούν.
     *
     * @author georgiamichou
     */
    private static void saveAdmins(List<Admin> admins) {
        String filePath = "./src/api/Admins.dat";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (Admin ad : admins) {
                oos.writeObject(ad);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Αρχικοποιεί μια λίστα με οντότητες συνδρομητών.
     *
     * @return Λίστα αρχικοποιημένων αντικειμένων συνδρομητών.
     *
     * @author georgiamichou
     */
    private static List<Subscriber> initializeSubscribers() {
        List<Subscriber> subscribers = new ArrayList<>();
        Subscriber user1 = new Subscriber("user1", "password1");
        Subscriber user2 = new Subscriber("user2", "password2");
        Subscriber user3 = new Subscriber("user3", "password3");
        Subscriber user4 = new Subscriber("user4", "password4");
        Subscriber user5 = new Subscriber("user5", "password5");
        subscribers.add(user1);
        subscribers.add(user2);
        subscribers.add(user3);
        subscribers.add(user4);
        subscribers.add(user5);
        return subscribers;
    }

    /**
     * Αποθηκεύει μια λίστα συνδρομητών στο αντίστοιχο αρχείο δεδομένων.
     *
     * @param subscribers Η λίστα των συνδρομητών που θα αποθηκευτούν.
     *
     * @author georgiamichou
     */
    private static void saveSubscribers(List<Subscriber> subscribers) {
        String filePath = "./src/api/Subscribers.dat";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (Subscriber sub : subscribers) {
                oos.writeObject(sub);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Αρχικοποιεί μια λίστα με οντότητες ταινιών.
     *
     * @return Λίστα αρχικοποιημένων αντικειμένων Movies.
     * @author georgiamichou
     */
    private static List<Movies> initializeMovies() {
        List<Movies> movies = new ArrayList<>();

        Movies movie1 = new Movies("The Lord of the Rings: The Fellowship of the Ring", "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.", true, 2001, 178, Category.ACTION, Arrays.asList("Elijah Wood", "Ian McKellen", "Orlando Bloom"));
        Movies movie2 = new Movies("The Hobbit: An Unexpected Journey", "A reluctant Hobbit, Bilbo Baggins, sets out to the Lonely Mountain with a spirited group of dwarves to reclaim their mountain home, and the gold within it from the dragon Smaug.", true, 2012, 169, Category.SCIFI, Arrays.asList("Martin Freeman", "Ian McKellen", "Peter Jackson"));
        Movies movie3 = new Movies("Indiana Jones and the Kingdom of the Crystal Skull", "Indiana Jones becomes entangled in a Soviet plot to uncover the secret behind mysterious artifacts known as the Crystal Skulls.", true, 2008, 122, Category.ACTION, Arrays.asList("Harrison Ford", "Cate Blanchett", "Shia LaBeouf"));
        Movies movie4 = new Movies("Neighbors", "After they are forced to live next to a fraternity house, a couple with a newborn baby do whatever they can to take them down.", false, 2014, 97, Category.COMEDY, Arrays.asList("Andrew Jay Cohen", "Brendan O'Brian"));
        Movies movie5 = new Movies("Thanksgiving", "After a Black Friday riot ends in tragedy, a mysterious Thanksgiving-inspired killer terrorizes Plymouth, Massachusetts - the birthplace of the infamous holiday.", false, 2023, 106, Category.HORROR, Arrays.asList("Patrick Dempsey", "Ty Olsson", "Gina Gershon"));
        Movies movie6 = new Movies("Leave the World Behind", "A family's getaway to a luxurious rental home takes an ominous turn when a cyberattack knocks out their devices, and two strangers appear at their door.", false, 2023, 138, Category.DRAMA, Arrays.asList("Julia Roberts", "Mahershala Ali", "Ethan Hawke"));

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        movies.add(movie5);
        movies.add(movie6);

        return movies;
    }

    /**
     * Αποθηκεύει μια λίστα ταινιών στο αντίστοιχο αρχείο δεδομένων.
     *
     * @param movies Η λίστα των ταινιών που θα αποθηκευτούν.
     *
     * @author georgiamichou
     */
    private static void saveMovies(List<Movies> movies) {
        String filePath = "./src/api/Movies.dat";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (Movies movie : movies) {
                oos.writeObject(movie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Αρχικοποιεί μια λίστα με οντότητες σειράς.
     *
     * @return Λίστα αρχικοποιημένων αντικειμένων σειράς.
     *
     * @author ioannismaredis
     */
    private static List<Series> initializeSeries() {
        List<Series> series = new ArrayList<>();

        Series series1 = new Series("Mindhunter", "In the late 1970s, two FBI agents broaden the realm of criminal science by investigating the psychology behind murder and end up getting too close to real-life monsters.", false,
                Category.DRAMA, Arrays.asList("Jonathan Groff", "Holt McCallany", "Anna Torv"), 1, 2017,
                new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9", "Episode 10")),
                new ArrayList<>(Arrays.asList(60, 56, 45, 54, 42, 34, 53, 54, 48, 53)));
        ArrayList<String> series1_episodesSeason2 = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9"));
        ArrayList<Integer> series1_durationsSeason2 = new ArrayList<>(Arrays.asList(48, 47, 60, 51, 72, 59, 58, 53, 74));
        series1.setSeasons(2, 2019, series1_episodesSeason2, series1_durationsSeason2);

        series.add(series1);


        Series series2 = new Series("Agent Carter", "An agent who fought alongside Captain America during World War II, Peggy Carter is a naturally born leader and staunch supporter of justice.", true, Category.ACTION,
                Arrays.asList("Hayley Atwell", "James D'Arcy", "Chad Michael Murray"), 1, 2015,
                new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8")), new ArrayList<>(Arrays.asList(44, 43, 43, 43, 45, 43, 43, 45)));
        ArrayList<String> series2_episodesSeason2 = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9", "Episode 10"));
        ArrayList<Integer> series2_durationsSeason2 = new ArrayList<>(Arrays.asList(44, 45, 45, 45, 44, 45, 45, 45, 44, 43));
        series2.setSeasons(2, 2016, series2_episodesSeason2, series2_durationsSeason2);

        series.add(series2);


        Series series3 = new Series("Only murders in the building", "Three strangers share an obsession with true crime and suddenly find themselves wrapped up in one. " +
                "When a grisly death occurs inside their exclusive Upper West Side apartment building, the trio suspects murder and employs their precise knowledge of true crime to investigate the truth.",
                true, Category.COMEDY, Arrays.asList("Steven Martin", "Martin Short", "Selena Gomez"), 1, 2021,
                new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9", "Episode 10")), new ArrayList<>(Arrays.asList(35, 31, 36, 35, 28, 34, 33, 32, 33, 37)));
        ArrayList<String> series3_episodesSeason2 = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9", "Episode 10"));
        ArrayList<Integer> series3_durationsSeason2 = new ArrayList<>(Arrays.asList(37, 37, 32, 38, 37, 35, 34, 32, 31, 40));
        series3.setSeasons(2, 2022, series3_episodesSeason2, series3_durationsSeason2);
        ArrayList<String> series3_episodesSeason3 = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9", "Episode 10"));
        ArrayList<Integer> series3_durationsSeason3 = new ArrayList<>(Arrays.asList(39, 35, 41, 37, 37, 35, 38, 41, 39, 40));
        series3.setSeasons(3, 2023, series3_episodesSeason3, series3_durationsSeason3);

        series.add(series3);


        Series series4 = new Series("American Horror Stories", "A weekly show from the producers of 'American Horror Story'. Each episode is a different horror story.", false,
                Category.HORROR, Arrays.asList("Sara Paulson", "Emma Roberts", "Jessica Lang", "Evan Peters"), 1, 2021,
                new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7")), new ArrayList<>(Arrays.asList(50, 48, 43, 39, 51, 41, 49)));
        ArrayList<String> series4_episodesSeason2 = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8"));
        ArrayList<Integer> series4_durationsSeason2 = new ArrayList<>(Arrays.asList(44, 45, 41, 50, 48, 42, 45, 42));
        series4.setSeasons(2, 2022, series4_episodesSeason2, series4_durationsSeason2);
        ArrayList<String> series4_episodesSeason3 = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4"));
        ArrayList<Integer> series4_durationsSeason3 = new ArrayList<>(Arrays.asList(53, 44, 44, 40));
        series4.setSeasons(3, 2023, series4_episodesSeason3, series4_durationsSeason3);

        series.add(series4);


        Series series5 = new Series("War of the worlds", "When astronomers detect an emission from another galaxy, it proves the existence of alien life. " +
                "People wait with bated breath for the next communication. They won't have to wait long. Within days, humanity is wiped out, with few survivors scattered across a desolate new world.",
                true, Category.SCIFI, Arrays.asList("Lea Drucker", "Gabriel Byrne", "Natasha Little"), 1, 2019,
                new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8")), new ArrayList<>(Arrays.asList(49, 48, 49, 48, 48, 48, 49, 47)));
        ArrayList<String> series5_episodesSeason2 = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8"));
        ArrayList<Integer> series5_durationsSeason2 = new ArrayList<>(Arrays.asList(50, 49, 49, 49, 50, 49, 49, 50));
        series5.setSeasons(2, 2021, series5_episodesSeason2, series5_durationsSeason2);
        ArrayList<String> series5_episodesSeason3 = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8"));
        ArrayList<Integer> series5_durationsSeason3 = new ArrayList<>(Arrays.asList(49, 48, 45, 47, 46, 45, 48, 44));
        series5.setSeasons(3, 2022, series5_episodesSeason3, series5_durationsSeason3);

        series.add(series5);

        Series series6 = new Series("The bear", "A dysfunctional Italian beef sandwich shop in Chicago trying to recover following the death of its owner, but it's also so much more than that.", true, Category.DRAMA,
                Arrays.asList("Jeremy Allen White", "Ebon Moss-Baschrach", "Ayo Edebiri"), 1, 2022,
                new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8")), new ArrayList<>(Arrays.asList(28, 32, 30, 31, 26, 31, 21, 48)));
        ArrayList<String> series6_episodesSeason2 = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9", "Episode 10"));
        ArrayList<Integer> series6_durationsSeason2 = new ArrayList<>(Arrays.asList(30, 31, 27, 31, 35, 68, 36, 33, 40, 42));
        series6.setSeasons(2, 2023, series6_episodesSeason2, series6_durationsSeason2);

        series.add(series6);

        return series;

    }


    /**
     * Αρχικοποιεί μια λίστα με οντότητες show.
     *
     * @return Λίστα αρχικοποιημένων αντικειμένων show.
     *
     * @author ioannismaredis
     */
    private static List<Show> initializeShows() {
        List<Show> shows = new ArrayList<>();

        Movies movie1 = new Movies("The Lord of the Rings: The Fellowship of the Ring", "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.", true, 2001, 178, Category.ACTION, Arrays.asList("Elijah Wood", "Ian McKellen", "Orlando Bloom"));
        Movies movie2 = new Movies("The Hobbit: An Unexpected Journey", "A reluctant Hobbit, Bilbo Baggins, sets out to the Lonely Mountain with a spirited group of dwarves to reclaim their mountain home, and the gold within it from the dragon Smaug.", true, 2012, 169, Category.SCIFI, Arrays.asList("Martin Freeman", "Ian McKellen", "Peter Jackson"));
        Movies movie3 = new Movies("Indiana Jones and the Kingdom of the Crystal Skull", "Indiana Jones becomes entangled in a Soviet plot to uncover the secret behind mysterious artifacts known as the Crystal Skulls.", true, 2008, 122, Category.ACTION, Arrays.asList("Harrison Ford", "Cate Blanchett", "Shia LaBeouf"));
        Movies movie4 = new Movies("Neighbors", "After they are forced to live next to a fraternity house, a couple with a newborn baby do whatever they can to take them down.", false, 2014, 97, Category.COMEDY, Arrays.asList("Andrew Jay Cohen", "Brendan O'Brian"));
        Movies movie5 = new Movies("Thanksgiving", "After a Black Friday riot ends in tragedy, a mysterious Thanksgiving-inspired killer terrorizes Plymouth, Massachusetts - the birthplace of the infamous holiday.", false, 2023, 106, Category.HORROR, Arrays.asList("Patrick Dempsey", "Ty Olsson", "Gina Gershon"));
        Movies movie6 = new Movies("Leave the World Behind", "A family's getaway to a luxurious rental home takes an ominous turn when a cyberattack knocks out their devices, and two strangers appear at their door.", false, 2023, 138, Category.DRAMA, Arrays.asList("Julia Roberts", "Mahershala Ali", "Ethan Hawke"));


        Series series1 = new Series("Mindhunter", "In the late 1970s, two FBI agents broaden the realm of criminal science by investigating the psychology behind murder and end up getting too close to real-life monsters.", false,
                Category.DRAMA, Arrays.asList("Jonathan Groff", "Holt McCallany", "Anna Torv"), 1, 2017,
                new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9", "Episode 10")),
                new ArrayList<>(Arrays.asList(60, 56, 45, 54, 42, 34, 53, 54, 48, 53)));
        ArrayList<String> series1_episodesSeason2 = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9"));
        ArrayList<Integer> series1_durationsSeason2 = new ArrayList<>(Arrays.asList(48, 47, 60, 51, 72, 59, 58, 53, 74));
        series1.setSeasons(2, 2019, series1_episodesSeason2, series1_durationsSeason2);


        Series series2 = new Series("Agent Carter", "An agent who fought alongside Captain America during World War II, Peggy Carter is a naturally born leader and staunch supporter of justice.", true, Category.ACTION,
                Arrays.asList("Hayley Atwell", "James D'Arcy", "Chad Michael Murray"), 1, 2015,
                new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8")), new ArrayList<>(Arrays.asList(44, 43, 43, 43, 45, 43, 43, 45)));
        ArrayList<String> series2_episodesSeason2 = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9", "Episode 10"));
        ArrayList<Integer> series2_durationsSeason2 = new ArrayList<>(Arrays.asList(44, 45, 45, 45, 44, 45, 45, 45, 44, 43));
        series2.setSeasons(2, 2016, series2_episodesSeason2, series2_durationsSeason2);


        Series series3 = new Series("Only murders in the building", "Three strangers share an obsession with true crime and suddenly find themselves wrapped up in one. " +
                "When a grisly death occurs inside their exclusive Upper West Side apartment building, the trio suspects murder and employs their precise knowledge of true crime to investigate the truth.",
                true, Category.COMEDY, Arrays.asList("Steven Martin", "Martin Short", "Selena Gomez"), 1, 2021,
                new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9", "Episode 10")), new ArrayList<>(Arrays.asList(35, 31, 36, 35, 28, 34, 33, 32, 33, 37)));
        ArrayList<String> series3_episodesSeason2 = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9", "Episode 10"));
        ArrayList<Integer> series3_durationsSeason2 = new ArrayList<>(Arrays.asList(37, 37, 32, 38, 37, 35, 34, 32, 31, 40));
        series3.setSeasons(2, 2022, series3_episodesSeason2, series3_durationsSeason2);
        ArrayList<String> series3_episodesSeason3 = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9", "Episode 10"));
        ArrayList<Integer> series3_durationsSeason3 = new ArrayList<>(Arrays.asList(39, 35, 41, 37, 37, 35, 38, 41, 39, 40));
        series3.setSeasons(3, 2023, series3_episodesSeason3, series3_durationsSeason3);


        Series series4 = new Series("American Horror Stories", "A weekly show from the producers of 'American Horror Story'. Each episode is a different horror story.", false,
                Category.HORROR, Arrays.asList("Sara Paulson", "Emma Roberts", "Jessica Lang", "Evan Peters"), 1, 2021,
                new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7")), new ArrayList<>(Arrays.asList(50, 48, 43, 39, 51, 41, 49)));
        ArrayList<String> series4_episodesSeason2 = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8"));
        ArrayList<Integer> series4_durationsSeason2 = new ArrayList<>(Arrays.asList(44, 45, 41, 50, 48, 42, 45, 42));
        series4.setSeasons(2, 2022, series4_episodesSeason2, series4_durationsSeason2);
        ArrayList<String> series4_episodesSeason3 = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4"));
        ArrayList<Integer> series4_durationsSeason3 = new ArrayList<>(Arrays.asList(53, 44, 44, 40));
        series4.setSeasons(3, 2023, series4_episodesSeason3, series4_durationsSeason3);


        Series series5 = new Series("War of the worlds", "When astronomers detect an emission from another galaxy, it proves the existence of alien life. " +
                "People wait with bated breath for the next communication. They won't have to wait long. Within days, humanity is wiped out, with few survivors scattered across a desolate new world.",
                true, Category.SCIFI, Arrays.asList("Lea Drucker", "Gabriel Byrne", "Natasha Little"), 1, 2019,
                new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8")), new ArrayList<>(Arrays.asList(49, 48, 49, 48, 48, 48, 49, 47)));
        ArrayList<String> series5_episodesSeason2 = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8"));
        ArrayList<Integer> series5_durationsSeason2 = new ArrayList<>(Arrays.asList(50, 49, 49, 49, 50, 49, 49, 50));
        series5.setSeasons(2, 2021, series5_episodesSeason2, series5_durationsSeason2);
        ArrayList<String> series5_episodesSeason3 = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8"));
        ArrayList<Integer> series5_durationsSeason3 = new ArrayList<>(Arrays.asList(49, 48, 45, 47, 46, 45, 48, 44));
        series5.setSeasons(3, 2022, series5_episodesSeason3, series5_durationsSeason3);

        Series series6 = new Series("The bear", "A dysfunctional Italian beef sandwich shop in Chicago trying to recover following the death of its owner, but it's also so much more than that.", true, Category.DRAMA,
                Arrays.asList("Jeremy Allen White", "Ebon Moss-Baschrach", "Ayo Edebiri"), 1, 2022,
                new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8")), new ArrayList<>(Arrays.asList(28, 32, 30, 31, 26, 31, 21, 48)));
        ArrayList<String> series6_episodesSeason2 = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9", "Episode 10"));
        ArrayList<Integer> series6_durationsSeason2 = new ArrayList<>(Arrays.asList(30, 31, 27, 31, 35, 68, 36, 33, 40, 42));
        series6.setSeasons(2, 2023, series6_episodesSeason2, series6_durationsSeason2);

        shows.add(movie1);
        shows.add(movie2);
        shows.add(movie3);
        shows.add(movie4);
        shows.add(movie5);
        shows.add(movie6);
        shows.add(series1);
        shows.add(series2);
        shows.add(series3);
        shows.add(series4);
        shows.add(series5);
        shows.add(series6);

        return shows;
    }

    /**
     * Αρχικοποιεί μια λίστα με οντότητες show.
     *
     * @return Λίστα αρχικοποιημένων αντικειμένων show.
     *
     * @author ioannismaredis
     */
    private static void saveShows(List<Show> shows) {
        String filePath = "./src/api/Shows.dat";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (Show show : shows) {
                oos.writeObject(show);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**Αρχικοποιεί και επιστρέφει μια λίστα συνδρομητών με προκαθορισμένες αγαπημένες εκπομπές.
     * Η μέθοδος δημιουργεί συνδρομητές με συγκεκριμένες αγαπημένες ταινίες και σειρές,
     * προσθέτοντάς τους στη λίστα συνδρομητών.
     * @param subscribers Η λίστα των συνδρομητών για την αρχικοποίηση με αγαπημένες εκπομπές.
     * @return Η λίστα των συνδρομητών με προκαθορισμένες αγαπημένες σειρές.
     * @author ioannismaredis
     */
    private static List<Subscriber> initializeFavourites(List<Subscriber> subscribers) {

        Subscriber user1 = subscribers.get(0);
        Subscriber user2 = subscribers.get(1);
        Subscriber user3 = subscribers.get(2);
        Subscriber user4 = subscribers.get(3);
        Subscriber user5 = subscribers.get(4);

        Movies movie1 = new Movies("The Lord of the Rings: The Fellowship of the Ring", "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.", true, 2001, 178, Category.ACTION, Arrays.asList("Elijah Wood", "Ian McKellen", "Orlando Bloom"));
        Movies movie2 = new Movies("The Hobbit: An Unexpected Journey", "A reluctant Hobbit, Bilbo Baggins, sets out to the Lonely Mountain with a spirited group of dwarves to reclaim their mountain home, and the gold within it from the dragon Smaug.", true, 2012, 169, Category.SCIFI, Arrays.asList("Martin Freeman", "Ian McKellen", "Peter Jackson"));
        Movies movie3 = new Movies("Indiana Jones and the Kingdom of the Crystal Skull", "Indiana Jones becomes entangled in a Soviet plot to uncover the secret behind mysterious artifacts known as the Crystal Skulls.", true, 2008, 122, Category.ACTION, Arrays.asList("Harrison Ford", "Cate Blanchett", "Shia LaBeouf"));
        Movies movie4 = new Movies("Neighbors", "After they are forced to live next to a fraternity house, a couple with a newborn baby do whatever they can to take them down.", false, 2014, 97, Category.COMEDY, Arrays.asList("Andrew Jay Cohen", "Brendan O'Brian"));
        Movies movie5 = new Movies("Thanksgiving", "After a Black Friday riot ends in tragedy, a mysterious Thanksgiving-inspired killer terrorizes Plymouth, Massachusetts - the birthplace of the infamous holiday.", false, 2023, 106, Category.HORROR, Arrays.asList("Patrick Dempsey", "Ty Olsson", "Gina Gershon"));
        Movies movie6 = new Movies("Leave the World Behind", "A family's getaway to a luxurious rental home takes an ominous turn when a cyberattack knocks out their devices, and two strangers appear at their door.", false, 2023, 138, Category.DRAMA, Arrays.asList("Julia Roberts", "Mahershala Ali", "Ethan Hawke"));

        Series series1 = new Series("Mindhunter", "In the late 1970s, two FBI agents broaden the realm of criminal science by investigating the psychology behind murder and end up getting too close to real-life monsters.", false,
                Category.DRAMA, Arrays.asList("Jonathan Groff", "Holt McCallany", "Anna Torv"), 1, 2017,
                new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9", "Episode 10")),
                new ArrayList<>(Arrays.asList(60, 56, 45, 54, 42, 34, 53, 54, 48, 53)));
        ArrayList<String> series1_episodesSeason2 = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9"));
        ArrayList<Integer> series1_durationsSeason2 = new ArrayList<>(Arrays.asList(48, 47, 60, 51, 72, 59, 58, 53, 74));
        series1.setSeasons(2, 2019, series1_episodesSeason2, series1_durationsSeason2);


        Series series2 = new Series("Agent Carter", "An agent who fought alongside Captain America during World War II, Peggy Carter is a naturally born leader and staunch supporter of justice.", true, Category.ACTION,
                Arrays.asList("Hayley Atwell", "James D'Arcy", "Chad Michael Murray"), 1, 2015,
                new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8")), new ArrayList<>(Arrays.asList(44, 43, 43, 43, 45, 43, 43, 45)));
        ArrayList<String> series2_episodesSeason2 = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9", "Episode 10"));
        ArrayList<Integer> series2_durationsSeason2 = new ArrayList<>(Arrays.asList(44, 45, 45, 45, 44, 45, 45, 45, 44, 43));
        series2.setSeasons(2, 2016, series2_episodesSeason2, series2_durationsSeason2);


        Series series3 = new Series("Only murders in the building", "Three strangers share an obsession with true crime and suddenly find themselves wrapped up in one. " +
                "When a grisly death occurs inside their exclusive Upper West Side apartment building, the trio suspects murder and employs their precise knowledge of true crime to investigate the truth.",
                true, Category.COMEDY, Arrays.asList("Steven Martin", "Martin Short", "Selena Gomez"), 1, 2021,
                new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9", "Episode 10")), new ArrayList<>(Arrays.asList(35, 31, 36, 35, 28, 34, 33, 32, 33, 37)));
        ArrayList<String> series3_episodesSeason2 = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9", "Episode 10"));
        ArrayList<Integer> series3_durationsSeason2 = new ArrayList<>(Arrays.asList(37, 37, 32, 38, 37, 35, 34, 32, 31, 40));
        series3.setSeasons(2, 2022, series3_episodesSeason2, series3_durationsSeason2);
        ArrayList<String> series3_episodesSeason3 = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9", "Episode 10"));
        ArrayList<Integer> series3_durationsSeason3 = new ArrayList<>(Arrays.asList(39, 35, 41, 37, 37, 35, 38, 41, 39, 40));
        series3.setSeasons(3, 2023, series3_episodesSeason3, series3_durationsSeason3);


        Series series4 = new Series("American Horror Stories", "A weekly show from the producers of 'American Horror Story'. Each episode is a different horror story.", false,
                Category.HORROR, Arrays.asList("Sara Paulson", "Emma Roberts", "Jessica Lang", "Evan Peters"), 1, 2021,
                new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7")), new ArrayList<>(Arrays.asList(50, 48, 43, 39, 51, 41, 49)));
        ArrayList<String> series4_episodesSeason2 = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8"));
        ArrayList<Integer> series4_durationsSeason2 = new ArrayList<>(Arrays.asList(44, 45, 41, 50, 48, 42, 45, 42));
        series4.setSeasons(2, 2022, series4_episodesSeason2, series4_durationsSeason2);
        ArrayList<String> series4_episodesSeason3 = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4"));
        ArrayList<Integer> series4_durationsSeason3 = new ArrayList<>(Arrays.asList(53, 44, 44, 40));
        series4.setSeasons(3, 2023, series4_episodesSeason3, series4_durationsSeason3);


        Series series5 = new Series("War of the worlds", "When astronomers detect an emission from another galaxy, it proves the existence of alien life. " +
                "People wait with bated breath for the next communication. They won't have to wait long. Within days, humanity is wiped out, with few survivors scattered across a desolate new world.",
                true, Category.SCIFI, Arrays.asList("Lea Drucker", "Gabriel Byrne", "Natasha Little"), 1, 2019,
                new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8")), new ArrayList<>(Arrays.asList(49, 48, 49, 48, 48, 48, 49, 47)));
        ArrayList<String> series5_episodesSeason2 = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8"));
        ArrayList<Integer> series5_durationsSeason2 = new ArrayList<>(Arrays.asList(50, 49, 49, 49, 50, 49, 49, 50));
        series5.setSeasons(2, 2021, series5_episodesSeason2, series5_durationsSeason2);
        ArrayList<String> series5_episodesSeason3 = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8"));
        ArrayList<Integer> series5_durationsSeason3 = new ArrayList<>(Arrays.asList(49, 48, 45, 47, 46, 45, 48, 44));
        series5.setSeasons(3, 2022, series5_episodesSeason3, series5_durationsSeason3);


        Series series6 = new Series("The bear", "A dysfunctional Italian beef sandwich shop in Chicago trying to recover following the death of its owner, but it's also so much more than that.", true, Category.DRAMA,
                Arrays.asList("Jeremy Allen White", "Ebon Moss-Baschrach", "Ayo Edebiri"), 1, 2022,
                new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8")), new ArrayList<>(Arrays.asList(28, 32, 30, 31, 26, 31, 21, 48)));
        ArrayList<String> series6_episodesSeason2 = new ArrayList<>(Arrays.asList("Episode 1", "Episode 2", "Episode 3", "Episode 4", "Episode 5", "Episode 6", "Episode 7", "Episode 8", "Episode 9", "Episode 10"));
        ArrayList<Integer> series6_durationsSeason2 = new ArrayList<>(Arrays.asList(30, 31, 27, 31, 35, 68, 36, 33, 40, 42));
        series6.setSeasons(2, 2023, series6_episodesSeason2, series6_durationsSeason2);


        user1.addToFavorites(series1);
        user1.addToFavorites(series2);
        user1.addToFavorites(movie2);
        user1.addToFavorites(movie4);

        user2.addToFavorites(series3);
        user2.addToFavorites(series6);
        user2.addToFavorites(movie1);
        user2.addToFavorites(movie2);
        user2.addToFavorites(movie6);

        user3.addToFavorites(series1);
        user3.addToFavorites(series4);
        user3.addToFavorites(movie3);
        user3.addToFavorites(movie5);

        user4.addToFavorites(series3);
        user4.addToFavorites(series4);
        user4.addToFavorites(movie3);
        user4.addToFavorites(movie4);
        user4.addToFavorites(movie5);

        user5.addToFavorites(series3);
        user5.addToFavorites(series6);
        user5.addToFavorites(movie1);
        user5.addToFavorites(movie6);

        return subscribers;
    }

    /**
     * Saves a list of favourite shows into the corresponding data file.
     *
     * @param sub
     *
     * @author ioannismaredis
     */
    private static void savefavourites(List<Subscriber> sub) {
        String filePath = "./src/api/FavouriteShows.dat";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (Subscriber subscriber : sub) {
                oos.writeObject(subscriber.getFavorites());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Saves a list of series into the corresponding data file.
     *
     * @param series
     *
     * @author ioannismaredis
     */
    private static void saveSeries(List<Series> series) {
        String filePath = "./src/api/Series.dat";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (Series ser : series) {
                oos.writeObject(ser);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Αρχικοποιεί μια λίστα με αντικείμενα αξιολόγησης.
     *
     * @return Λίστα αρχικοποιημένων αντικειμένων αναθεώρησης.
     *
     * @author ioannismaredis
     */
    private static List<Review> initializeReviews() {

        List<Review> reviews = new ArrayList<>();

        Review series1_review1 = new Review("Good show", 4, "24/01/2020", "user1", 4.5);
        reviews.add(series1_review1);


        Review series1_review2 = new Review("I didn't like the plot at all", 1, "22/08/2021", "user2", 1);
        reviews.add(series1_review2);

        Review series1_review3 = new Review("I loved it!", 5, "27/05/2020", "user5", 5);
        reviews.add(series1_review3);

        Review series2_review1 = new Review("Amazing show!", 5, "24/10/2017", "user2", 5);
        reviews.add(series2_review1);

        Review series2_review2 = new Review("It's boring", 2, "18/12/2020", "user1", 1.8);
        reviews.add(series2_review2);

        Review series2_review3 = new Review("A fun show to watch!", 5, "29/03/2019", "user3", 5);
        reviews.add(series2_review3);

        Review series3_review1 = new Review("Amazing!", 5, "05/11/2022", "user1", 5);
        reviews.add(series3_review1);

        Review series3_review2 = new Review("Great show", 5, "09/08/2023", "user4", 4.5);
        reviews.add(series3_review2);

        Review series3_review3 = new Review("Meh", 3, "24/04/2022", "user3", 3);
        reviews.add(series3_review3);

        Review series4_review1 = new Review("I was captivated", 5, "24/01/2022", "user4", 4.8);
        reviews.add(series4_review1);

        Review series4_review2 = new Review("Awful show", 1, "02/01/2023", "user5", 1);
        reviews.add(series4_review2);

        Review series4_review3 = new Review("It's ok", 3, "4/01/2024", "user2", 4.5);
        reviews.add(series4_review3);

        Review series5_review1 = new Review("Interesting show", 4, "24/09/2023", "user2", 4.5);
        reviews.add(series5_review1);

        Review series5_review2 = new Review("Really bad", 1, "21/06/2021", "user5", 1);
        reviews.add(series5_review2);

        Review series5_review3 = new Review("Expected more", 2, "07/05/2023", "user1", 2);
        reviews.add(series5_review3);

        Review series6_review1 = new Review("A piece of art", 5, "03/07/2022", "user4", 5);
        reviews.add(series6_review1);

        Review series6_review2 = new Review("Nice work", 5, "22/06/2023", "user2", 5);
        reviews.add(series6_review2);

        Review series6_review3 = new Review("I loved it!", 5, "07/12/2023", "user3", 5);
        reviews.add(series6_review3);

        Review movies1_review1 = new Review("Fantastic", 5, "24/04/2015", "user2", 5);
        reviews.add(movies1_review1);

        Review movies1_review2 = new Review("Amazing", 5, "24/11/2017", "user1", 4.8);
        reviews.add(movies1_review2);

        Review movies1_review3 = new Review("Wow", 5, "01/03/2016", "user3", 5);
        reviews.add(movies1_review3);

        Review movies2_review1 = new Review("Nice", 4, "04/03/2020", "user1", 4.5);
        reviews.add(movies2_review1);

        Review movies2_review2 = new Review("Good movie", 4, "14/02/2017", "user4", 4.5);
        reviews.add(movies2_review2);

        Review movies2_review3 = new Review("Disturbing", 1, "24/12/2017", "user5", 4.5);
        reviews.add(movies2_review3);

        Review movies3_review1 = new Review("Great movie", 4, "06/02/2019", "user1", 4.5);
        reviews.add(movies3_review1);

        Review movies3_review2 = new Review("Ok", 3, "31/01/2021", "user4", 4.5);
        reviews.add(movies3_review2);

        Review movies3_review3 = new Review("Nice", 4, "04/09/2016", "user5", 4.5);
        reviews.add(movies3_review3);

        Review movies4_review1 = new Review("Funny", 5, "15/12/2015", "user2", 4.5);
        reviews.add(movies4_review1);

        Review movies4_review2 = new Review("Boring", 2, "04/11/2019", "user4", 4.5);
        reviews.add(movies4_review2);

        Review movies4_review3 = new Review("Interesting", 4, "24/10/2018", "user3", 4.5);
        reviews.add(movies4_review3);

        Review movies5_review1 = new Review("Waste of time", 1, "10/12/2023", "user1", 4.5);
        reviews.add(movies5_review1);

        Review movies5_review2 = new Review("Scary", 4, "20/12/2023", "user5", 4.5);
        reviews.add(movies5_review2);

        Review movies5_review3 = new Review("Meh", 2, "30/12/2023", "user3", 4.5);
        reviews.add(movies5_review3);

        Review movies6_review1 = new Review("Blunt", 3, "19/12/2023", "user4", 4.5);
        reviews.add(movies6_review1);

        Review movies6_review2 = new Review("Loved it", 5, "05/01/2024", "user2", 4.5);
        reviews.add(movies6_review2);

        Review movies6_review3 = new Review("I was touched", 5, "02/01/2024", "user4", 4.5);
        reviews.add(movies6_review3);

        return reviews;
    }


    /**
     * Αποθηκεύει μια λίστα αξιολογήσεων στο αντίστοιχο αρχείο δεδομένων.
     *
     * @param reviews
     *
     * @author ioannismaredis
     */
    private static void saveReviews(List<Review> reviews) {
        String filePath = "./src/api/Reviews.dat";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (Review r : reviews) {
                oos.writeObject(r);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    /**
     * Αποθηκεύει μια λίστα αξιολογήσεων ταινιών στο αντίστοιχο αρχείο δεδομένων.
     *
     * @param movies
     *
     * @author ioannismaredis
     */
    private static void saveMovieReviews(List<Movies> movies) {
        String filePath = "./src/api/Movies.dat";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (Movies movie : movies) {
                oos.writeObject(movie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Αρχικοποιεί μια λίστα με αντικείμενα αξιολόγησης σε ταινίες.
     *
     * @return Λίστα αρχικοποιημένων αντικειμένων αναθεώρησης.
     *
     * @author ioannismaredis
     */
    private static List<Movies> initializeMovieReviews(List<Movies> movie) {

        Movies movie1 = movie.get(0);
        Movies movie2 = movie.get(1);
        Movies movie3 = movie.get(2);
        Movies movie4 = movie.get(3);
        Movies movie5 = movie.get(4);
        Movies movie6 = movie.get(5);

        Review movies1_review1 = new Review("Fantastic", 5, "24/04/2015", "user2", 5);
        movie1.addReview(movies1_review1);

        Review movies1_review2 = new Review("Amazing", 5, "24/11/2017", "user1", 4.8);
        movie1.addReview(movies1_review2);

        Review movies1_review3 = new Review("Wow", 5, "01/03/2016", "user3", 5);
        movie1.addReview(movies1_review3);

        Review movies2_review1 = new Review("Nice", 4, "04/03/2020", "user1", 4.5);
        movie2.addReview(movies2_review1);

        Review movies2_review2 = new Review("Good movie", 4, "14/02/2017", "user4", 4.5);
        movie2.addReview(movies2_review2);

        Review movies2_review3 = new Review("Disturbing", 1, "24/12/2017", "user5", 4.5);
        movie2.addReview(movies2_review3);

        Review movies3_review1 = new Review("Great movie", 4, "06/02/2019", "user1", 4.5);
        movie3.addReview(movies3_review1);

        Review movies3_review2 = new Review("Ok", 3, "31/01/2021", "user4", 4.5);
        movie3.addReview(movies3_review2);

        Review movies3_review3 = new Review("Nice", 4, "04/09/2016", "user5", 4.5);
        movie3.addReview(movies3_review3);

        Review movies4_review1 = new Review("Funny", 5, "15/12/2015", "user2", 4.5);
        movie4.addReview(movies4_review1);

        Review movies4_review2 = new Review("Boring", 2, "04/11/2019", "user4", 4.5);
        movie4.addReview(movies4_review2);

        Review movies4_review3 = new Review("Interesting", 4, "24/10/2018", "user3", 4.5);
        movie4.addReview(movies4_review3);

        Review movies5_review1 = new Review("Waste of time", 1, "10/12/2023", "user1", 4.5);
        movie5.addReview(movies5_review1);

        Review movies5_review2 = new Review("Scary", 4, "20/12/2023", "user5", 4.5);
        movie5.addReview(movies5_review2);

        Review movies5_review3 = new Review("Meh", 2, "30/12/2023", "user3", 4.5);
        movie5.addReview(movies5_review3);

        Review movies6_review1 = new Review("Blunt", 3, "19/12/2023", "user4", 4.5);
        movie6.addReview(movies6_review1);

        Review movies6_review2 = new Review("Loved it", 5, "05/01/2024", "user2", 4.5);
        movie6.addReview(movies6_review2);

        Review movies6_review3 = new Review("I was touched", 5, "02/01/2024", "user4", 4.5);
        movie6.addReview(movies6_review3);

        return movie;
    }



    /**
     * Αποθηκεύει μια λίστα αξιολογήσεων σεορών στο αντίστοιχο αρχείο δεδομένων.
     *
     * @param series
     *
     * @author ioannismaredis
     */
    private static void saveSeriesReviews(List<Series> series) {
        String filePath = "./src/api/Series.dat";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (Series ser : series) {
                oos.writeObject(ser);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Αρχικοποιεί μια λίστα με αντικείμενα αξιολόγησης σε σειρές.
     *
     * @return Λίστα αρχικοποιημένων αντικειμένων αναθεώρησης.
     *
     * @author ioannismaredis
     */
    private static List<Series> initializeSeriesReviews(List<Series> series){

        Series series1 = series.get(0);
        Series series2 = series.get(1);
        Series series3 = series.get(2);
        Series series4 = series.get(3);
        Series series5 = series.get(4);
        Series series6 = series.get(5);


        Review series1_review1 = new Review("Good show", 4, "24/01/2020", "user1", 4.5);
        series1.addReview(series1_review1);

        Review series1_review2 = new Review("I didn't like the plot at all", 1, "22/08/2021", "user2", 1);
        series1.addReview(series1_review2);

        Review series1_review3 = new Review("I loved it!", 5, "27/05/2020", "user5", 5);
        series1.addReview(series1_review3);

        Review series2_review1 = new Review("Amazing show!", 5, "24/10/2017", "user2", 5);
        series2.addReview(series2_review1);

        Review series2_review2 = new Review("It's boring", 2, "18/12/2020", "user1", 1.8);
        series2.addReview(series2_review2);

        Review series2_review3 = new Review("A fun show to watch!", 5, "29/03/2019", "user3", 5);
        series2.addReview(series2_review3);

        Review series3_review1 = new Review("Amazing!", 5, "05/11/2022", "user1", 5);
        series3.addReview(series3_review1);

        Review series3_review2 = new Review("Great show", 5, "09/08/2023", "user4", 4.5);
        series3.addReview(series3_review2);

        Review series3_review3 = new Review("Meh", 3, "24/04/2022", "user3", 3);
        series3.addReview(series3_review3);

        Review series4_review1 = new Review("I was captivated", 5, "24/01/2022", "user4", 4.8);
        series4.addReview(series4_review1);

        Review series4_review2 = new Review("Awful show", 1, "02/01/2023", "user5", 1);
        series4.addReview(series4_review2);

        Review series4_review3 = new Review("It's ok", 3, "4/01/2024", "user2", 4.5);
        series4.addReview(series4_review3);

        Review series5_review1 = new Review("Interesting show", 4, "24/09/2023", "user2", 4.5);
        series5.addReview(series5_review1);

        Review series5_review2 = new Review("Really bad", 1, "21/06/2021", "user5", 1);
        series5.addReview(series5_review2);

        Review series5_review3 = new Review("Expected more", 2, "07/05/2023", "user1", 2);
        series5.addReview(series5_review3);

        Review series6_review1 = new Review("A piece of art", 5, "03/07/2022", "user4", 5);
        series6.addReview(series6_review1);

        Review series6_review2 = new Review("Nice work", 5, "22/06/2023", "user2", 5);
        series6.addReview(series6_review2);

        Review series6_review3 = new Review("I loved it!", 5, "07/12/2023", "user3", 5);
        series6.addReview(series6_review3);

        return series;
    }



}

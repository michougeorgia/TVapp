package api;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Η κλάση FileLoader παρέχει μεθόδους για να φορτώσει διάφορες οντότητες (Ταινίες, Συνδρομητές, Διαχειριστές, Σειρές, Κριτικές)
 * από τα αντίστοιχα αρχεία δεδομένων τους.
 *
 * @author georgiamichou
 */
public class FileLoader  {

    /**
     * Φορτώνει ταινίες από το αρχείο Movies.dat.
     *
     * @return Λίστα των ταινιών που έχουν φορτωθεί.
     */
    public static List<Movies> loadMoviesFromFile() {
        List<Movies> loadedMovies = new ArrayList<>();

        String filePath = "src/api/Movies.dat";
        File file = new File(filePath);

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    Object obj = ois.readObject();

                    if (obj instanceof Movies) {
                        Movies m = (Movies) obj;
                        loadedMovies.add(m);
                    } else if ("api.Movies".equals(obj)) {
                        ois.readObject();
                    }
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Loaded movies from file: " + loadedMovies);

        return loadedMovies;
    }


    /**
     * Φορτώνει συνδρομητές από το αρχείο Subscribers.dat.
     *
     * @return Λίστα των φορτωμένων συνδρομητών.
     */
    public static List<Subscriber> loadUsersFromFile() {
        List<Subscriber> loadedUsers = new ArrayList<>();

        String filePath = "src/api/Subscribers.dat";
        File file = new File(filePath);

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    Object obj = ois.readObject();

                    if (obj instanceof Subscriber) {
                        Subscriber sub = (Subscriber) obj;
                        loadedUsers.add(sub);
                    } else if ("api.Subscribers".equals(obj)) {
                        ois.readObject();
                    }
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Loaded subscribers from file: " + loadedUsers);

        return loadedUsers;
    }

    /**
     * Φορτώνει τους διαχειριστές από το αρχείο Admins.dat.
     *
     * @return Λίστα των φορτωμένων διαχειριστών.
     */
    public static List<Admin> loadAdminsFromFile() {
        List<Admin> loadedAdmins = new ArrayList<>();

        String filePath = "src/api/Admins.dat";
        File file = new File(filePath);

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    Object obj = ois.readObject();

                    if (obj instanceof Admin) {
                        Admin ad = (Admin) obj;
                        loadedAdmins.add(ad);
                    } else if ("api.Admins".equals(obj)) {
                        ois.readObject();
                    }
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Loaded admins from file: " + loadedAdmins);

        return loadedAdmins;
    }


    /**
     * Φορτώνει σειρές από το αρχείο Series.dat.
     *
     * @return Λίστα των φορτωμένων σειρών.
     */
    public static List<Series> loadSeriesFromFile() {
        List<Series> loadedSeries = new ArrayList<>();

        String filePath = "src/api/Series.dat";
        File file = new File(filePath);

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    Object obj = ois.readObject();
                    if (obj instanceof Series) {
                        Series ser = (Series) obj;
                        loadedSeries.add(ser);
                    } else if ("api.Series".equals(obj)) {
                        ois.readObject();
                    }
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Loaded series from file: " + loadedSeries);

        return loadedSeries;
    }

    /**
     * Φορτώνει κριτικές από το αρχείο Reviews.dat.
     *
     * @return Λίστα των κριτικών που έχουν φορτωθεί.
     */
    public static List<Review> loadReviewsFromFile() {
        List<Review> loadedReviews = new ArrayList<>();

        String filePath = "src/api/Reviews.dat";
        File file = new File(filePath);

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    Object obj = ois.readObject();
                    if (obj instanceof Review) {
                        Review r = (Review) obj;
                        loadedReviews.add(r);
                    } else if ("api.Series".equals(obj)) {
                        ois.readObject();
                    }
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Loaded reviews from file: " + loadedReviews);

        return loadedReviews;
    }

    /**
     * Φορτώνει κριτικές από το αρχείο Shows.dat.
     *
     * @return Λίστα των κριτικών που έχουν φορτωθεί.
     */

    public static List<Show> loadShowsFromFile() {
        List<Show> loadedShows = new ArrayList<>();

        String filePath = "src/api/Shows.dat";
        File file = new File(filePath);

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    Object obj = ois.readObject();
                    if (obj instanceof Show) {
                        Show s = (Show) obj;
                        loadedShows.add(s);
                    } else if ("api.Show".equals(obj)) {
                        ois.readObject();
                    }
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Loaded shows from file: " + loadedShows);

        return loadedShows;
    }
}

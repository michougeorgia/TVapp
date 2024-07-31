package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import api.isForMinors;
import gui.ReviewDialog;

import api.*;

public class ShowDialog extends JFrame {

    private List<Movies> moviesList;
    private List<Series> seriesList;
    private List<Show> showsList;
    private List<Subscriber> subsList;
    private List<Admin> adminsList;
    private List<Review> reviewMovies;
    private List<Review> reviewSeries;
    private List<Review> reviews;
    private JList<String> moviesJList;
    private JList<String> seriesJList;
    private JList<String> reviewsUsernameJList;
    private User user;
    private User currentUser;
    private String protagonist;
    private Category category;
    private String minOverallRating;
    private List<Review> reviewsFile;


    public ShowDialog(User user) {
        super("Show Operations");
        subsList = FileLoader.loadUsersFromFile();
        this.user = user;
        currentUser = user;
        moviesList = FileLoader.loadMoviesFromFile();
        seriesList = FileLoader.loadSeriesFromFile();
        reviewsFile = FileLoader.loadReviewsFromFile();
        showsList = FileLoader.loadShowsFromFile();
        //defineMovies();
        //defineSeries();
        setLocation(1250, 500);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildTypeSelectionUI();
        setVisible(true);
        pack();
    }


    private void buildTypeSelectionUI() {
        if (user instanceof Subscriber) {

            JPanel typeSelectionPanel = new JPanel(new GridLayout(4, 1));

            JButton searchShowsButton = new JButton("Search");
            JButton moviesButton = new JButton("Movies");
            JButton seriesButton = new JButton("Series");
            JButton favouriteShowsButton = new JButton("Your Favourite Shows");


            typeSelectionPanel.add(searchShowsButton);
            typeSelectionPanel.add(moviesButton);
            typeSelectionPanel.add(seriesButton);
            typeSelectionPanel.add(favouriteShowsButton);

            add(typeSelectionPanel, BorderLayout.CENTER);


            searchShowsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JDialog searchDialog = new JDialog(ShowDialog.this, "Search", true);
                    new SearchDialog(searchDialog, showsList, user);
                }
            });

            moviesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showMoviesEnvironment();
                }
            });

            seriesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showSeriesEnvironment();
                }
            });

            favouriteShowsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SubscriberDialog.displayFavouriteShows(((Subscriber) user).getFavorites());

                }
            });
        } else if (user instanceof Admin) {
            JPanel typeSelectionPanel = new JPanel(new GridLayout(3, 1));

            JButton searchShowsButton = new JButton("Search");
            JButton moviesButton = new JButton("Movies");
            JButton seriesButton = new JButton("Series");

            typeSelectionPanel.add(searchShowsButton);
            typeSelectionPanel.add(moviesButton);
            typeSelectionPanel.add(seriesButton);

            add(typeSelectionPanel, BorderLayout.CENTER);

            searchShowsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showSearchEnvironment();
                }
            });

            moviesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showMoviesEnvironment();
                }
            });

            seriesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showSeriesEnvironment();
                }
            });
        }
    }



    private void showSearchEnvironment() {
        getContentPane().removeAll();

        String title = JOptionPane.showInputDialog("Enter title to search:");
        ShowType type = (ShowType) JOptionPane.showInputDialog(null, "Select show type:",
                "Show Type", JOptionPane.QUESTION_MESSAGE, null, ShowType.values(), null);
        String protagonist = JOptionPane.showInputDialog("Enter the protagonist of the show:");
        isForMinors isforminors = (isForMinors) JOptionPane.showInputDialog(null, "Is the show for minors?",
                "Check for minors", JOptionPane.QUESTION_MESSAGE, null, isForMinors.values(), null);
        Category category = (Category) JOptionPane.showInputDialog(null, "Select the category of the show:",
                "Category", JOptionPane.QUESTION_MESSAGE, null, Category.values(), null);
        String minOverallRating = JOptionPane.showInputDialog("Enter the minimum overall rating of the show:");

        ArrayList<Show> searchResults = user.searchShows(showsList, title, type, protagonist, isforminors, category, Double.parseDouble(minOverallRating));

        JDialog resultsDialog = new JDialog(this, "Search Results", true);
        resultsDialog.setLayout(new BorderLayout());

        JTextArea resultsTextArea = new JTextArea();
        resultsTextArea.setEditable(false);

        for (Show show : searchResults) {
            resultsTextArea.append(show.toString() + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(resultsTextArea);
        resultsDialog.add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> resultsDialog.dispose());
        resultsDialog.add(closeButton, BorderLayout.SOUTH);

        resultsDialog.setSize(400, 300);
        resultsDialog.setLocationRelativeTo(this);
        resultsDialog.setVisible(true);

        revalidate();
        repaint();
        pack();
    }



    private void showMoviesEnvironment() {

        getContentPane().removeAll();

        if (user instanceof Admin) {

            JPanel panel = new JPanel(new GridLayout(4, 1));

            JButton addButton = new JButton("Add Movie");
            JButton editButton = new JButton("Edit Movie");
            JButton deleteButton = new JButton("Delete Movie");
            JButton showMoviesButton = new JButton("Show Movies");

            addButton.setEnabled(true);
            editButton.setEnabled(true);
            deleteButton.setEnabled(true);
            showMoviesButton.setEnabled(true);


            moviesJList = new JList<>();
            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (Movies movie : moviesList) {
                listModel.addElement(movie.getTitle());
            }
            moviesJList.setModel(listModel);

            JScrollPane scrollPane = new JScrollPane(moviesJList);

            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JDialog addDialog = new JDialog(ShowDialog.this, "Add Movie", true);
                    new MovieDialog(addDialog, null, moviesList); // Pass null for a new movie
                }
            });

            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedTitle = moviesJList.getSelectedValue();
                    if (selectedTitle != null) {
                        Movies selectedMovie = findMovieByTitle(selectedTitle);
                        JDialog editDialog = new JDialog(ShowDialog.this, "Edit Movie Details", true);
                        new MovieDialog(editDialog, selectedMovie, moviesList);
                    } else {
                        JOptionPane.showMessageDialog(ShowDialog.this, "Please select a movie to edit.", "Edit Movie", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedTitle = moviesJList.getSelectedValue();
                    if (selectedTitle != null) {
                        Movies selectedMovie = findMovieByTitle(selectedTitle);
                        int confirmation = JOptionPane.showConfirmDialog(ShowDialog.this, "Are you sure you want to delete the movie: " + selectedTitle + "?", "Delete Movie", JOptionPane.YES_NO_OPTION);
                        if (confirmation == JOptionPane.YES_OPTION) {
                            moviesList.remove(selectedMovie);
                            listModel.removeElement(selectedTitle);
                            JOptionPane.showMessageDialog(ShowDialog.this, "Movie deleted successfully.", "Delete Movie", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(ShowDialog.this, "Please select a movie to delete.", "Delete Movie", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            showMoviesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MovieDialog.displayMovies(moviesList);
                }
            });

            panel.add(addButton);
            panel.add(editButton);
            panel.add(deleteButton);
            panel.add(showMoviesButton);

            add(scrollPane, BorderLayout.CENTER);
            add(panel, BorderLayout.SOUTH);

            revalidate();
            repaint();

        } else if (user instanceof Subscriber) {

            JPanel panel = new JPanel(new GridLayout(3, 1));

            JButton showMoviesButton = new JButton("Show Movies");
            JButton editFavoriteMoviesButton = new JButton("Edit Favorite Movies List");
            JButton ReviewButton = new JButton("Review");

            editFavoriteMoviesButton.setEnabled(true);
            showMoviesButton.setEnabled(true);
            ReviewButton.setEnabled(true);


            moviesJList = new JList<>();
            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (Movies movie : moviesList) {
                listModel.addElement(movie.getTitle());
            }
            moviesJList.setModel(listModel);

            JScrollPane scrollPane = new JScrollPane(moviesJList);

            showMoviesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MovieDialog.displayMovies(moviesList);
                }
            });

            editFavoriteMoviesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedTitle = moviesJList.getSelectedValue();
                    if (selectedTitle != null) {
                        Movies selectedMovies = findMovieByTitle(selectedTitle);
                        JDialog editDialog = new JDialog(ShowDialog.this, "Edit Favorite Movies List", true);
                        new SubscriberDialog(editDialog, selectedMovies, moviesList);
                    } else {
                        JOptionPane.showMessageDialog(ShowDialog.this, "Please select for which movies you want to edit the list.", "Edit Favourite Movies List", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            ReviewButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedTitle = moviesJList.getSelectedValue();
                    if (selectedTitle != null) {
                        Movies selectedMovie = findMovieByTitle(selectedTitle);
                        if (selectedMovie != null) {
                            showReviewEnvironment(selectedMovie);
                        }
                    } else {
                        JOptionPane.showMessageDialog(ShowDialog.this, "Please select a movie to review.", "Add Review", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            panel.add(editFavoriteMoviesButton);
            panel.add(showMoviesButton);
            panel.add(ReviewButton);

            add(scrollPane, BorderLayout.CENTER);
            add(panel, BorderLayout.SOUTH);

        }
        revalidate();
        repaint();

    }

    private void showReviewEnvironment(Show show){
        getContentPane().removeAll();

        JPanel panel = new JPanel(new GridLayout(3, 1));

        JButton addReviewButton = new JButton("Add Review");
        JButton editReviewButton = new JButton("Edit Review");
        JButton deleteReviewButton = new JButton("Delete Review");

        addReviewButton.setEnabled(true);
        editReviewButton.setEnabled(true);
        deleteReviewButton.setEnabled(true);

        reviewsUsernameJList = new JList<>();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Review review : show.getReviews()) {
            listModel.addElement(review.getUsername());
        }
        reviewsUsernameJList.setModel(listModel);

        JScrollPane scrollPane = new JScrollPane(reviewsUsernameJList);


        addReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog reviewDialog = new JDialog(ShowDialog.this, "Add Review", true);
                new ReviewDialog(ShowDialog.this, show, null, user);
            }
        });

        editReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selectedUsername = reviewsUsernameJList.getSelectedValue();
                if (selectedUsername != null) {
                    if (selectedUsername.equals(user.getUsername())){
                        Review selectedReview = findReviewByUsername(selectedUsername);

                        new ReviewDialog(ShowDialog.this, show, selectedReview, user);
                    }else {
                        JOptionPane.showMessageDialog(ShowDialog.this, "Please select your review.", "Edit Review", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(ShowDialog.this, "Please select your review.", "Edit Review", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        deleteReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedUsername = reviewsUsernameJList.getSelectedValue();
                if (selectedUsername != null) {
                    if (selectedUsername.equals(user.getUsername())){
                        Review selectedReview = findReviewByUsername(selectedUsername);
                        int confirmation = JOptionPane.showConfirmDialog(ShowDialog.this, "Are you sure you want to delete your review?", "Delete Review", JOptionPane.YES_NO_OPTION);
                        if (confirmation == JOptionPane.YES_OPTION) {
                            reviewsFile.remove(selectedReview);
                            listModel.removeElement(selectedReview);
                            JOptionPane.showMessageDialog(ShowDialog.this, "Review deleted successfully.", "Delete Review", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }else {
                        JOptionPane.showMessageDialog(ShowDialog.this, "You can only delete your review.", "Edit Review", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(ShowDialog.this, "Please select your review to delete.", "Delete Review", JOptionPane.WARNING_MESSAGE);
                }
            }
        });


        deleteReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedUsername = reviewsUsernameJList.getSelectedValue();
                if (selectedUsername != null) {
                    if (selectedUsername.equals(user.getUsername())){
                        Review selectedReview = findReviewByUsername(selectedUsername);
                        int confirmation = JOptionPane.showConfirmDialog(ShowDialog.this, "Are you sure you want to delete your review?", "Delete Review", JOptionPane.YES_NO_OPTION);
                        if (confirmation == JOptionPane.YES_OPTION) {
                            reviewsFile.remove(selectedReview);
                            listModel.removeElement(selectedReview);
                            JOptionPane.showMessageDialog(ShowDialog.this, "Review deleted successfully.", "Delete Review", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }else {
                        JOptionPane.showMessageDialog(ShowDialog.this, "You can only delete your review.", "Edit Review", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(ShowDialog.this, "Please select your review to delete.", "Delete Review", JOptionPane.WARNING_MESSAGE);
                }
            }
        });


        panel.add(addReviewButton);
        panel.add(editReviewButton);
        panel.add(deleteReviewButton);

        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        revalidate();
        repaint();

    }


    private Review findReviewByUsername(String username) {
        for (Review review : reviewsFile) {
            if (review.getUsername().equals(username)) {
                return review;
            }
        }
        return null;
    }

    private Movies findMovieByTitle(String title) {
        for (Movies movie : moviesList) {
            if (movie.getTitle().equals(title)) {
                return movie;
            }
        }
        return null;
    }

    private List<Movies> loadMoviesFromFile() {
        return new ArrayList<>();
    }







    private void showSeriesEnvironment() {
        getContentPane().removeAll();

        if (user instanceof Admin) {
            JPanel panel = new JPanel(new GridLayout(4, 1));

            JButton addButton = new JButton("Add Series");
            JButton editButton = new JButton("Edit Series");
            JButton deleteButton = new JButton("Delete Series");
            JButton showSeriesButton = new JButton("Show Series");
            addButton.setEnabled(true);
            editButton.setEnabled(true);
            deleteButton.setEnabled(true);
            showSeriesButton.setEnabled(true);

            seriesJList = new JList<>();
            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (Series series : seriesList) {
                listModel.addElement(series.getTitle());
            }
            seriesJList.setModel(listModel);

            JScrollPane scrollPane = new JScrollPane(seriesJList);



            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JDialog addDialog = new JDialog(ShowDialog.this, "Add Series", true);
                    new SeriesDialog(addDialog, null, seriesList, user);
                }
            });

            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedTitle = seriesJList.getSelectedValue();
                    if (selectedTitle != null) {
                        Series selectedSeries = findSeriesByTitle(selectedTitle);
                        JDialog editDialog = new JDialog(ShowDialog.this, "Edit Series Details", true);
                        new SeriesDialog(editDialog, selectedSeries, seriesList, user);
                    } else {
                        JOptionPane.showMessageDialog(ShowDialog.this, "Please select which series you want to edit.", "Edit Series", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedTitle = seriesJList.getSelectedValue();
                    if (selectedTitle != null) {
                        Series selectedSeries = findSeriesByTitle(selectedTitle);
                        int confirmation = JOptionPane.showConfirmDialog(ShowDialog.this, "Are you sure you want to delete: " + selectedTitle + "?", "Delete Series", JOptionPane.YES_NO_OPTION);
                        if (confirmation == JOptionPane.YES_OPTION) {
                            seriesList.remove(selectedSeries);
                            listModel.removeElement(selectedTitle);
                            JOptionPane.showMessageDialog(ShowDialog.this, "Series deleted successfully.", "Delete Series", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(ShowDialog.this, "Please select series to delete.", "Delete Series", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            showSeriesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SeriesDialog.displaySeries(seriesList);
                }
            });

            panel.add(addButton);
            panel.add(editButton);
            panel.add(deleteButton);
            panel.add(showSeriesButton);


            add(scrollPane, BorderLayout.CENTER);
            add(panel, BorderLayout.SOUTH);
        }
        else if (user instanceof Subscriber) {

            JPanel panel = new JPanel(new GridLayout(3, 1));


            JButton showSeriesButton = new JButton("Show Series");
            JButton editFavoriteSeriesButton = new JButton("Edit Favorite Series List");
            JButton ReviewButton = new JButton("Review");

            editFavoriteSeriesButton.setEnabled(true);
            showSeriesButton.setEnabled(true);
            ReviewButton.setEnabled(true);

            seriesJList = new JList<>();
            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (Series series : seriesList) {
                listModel.addElement(series.getTitle());
            }
            seriesJList.setModel(listModel);

            JScrollPane scrollPane = new JScrollPane(seriesJList);


            editFavoriteSeriesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedTitle = seriesJList.getSelectedValue();
                    if (selectedTitle != null) {
                        Series selectedSeries = findSeriesByTitle(selectedTitle);
                        JDialog editDialog = new JDialog(ShowDialog.this, "Edit Favorite Series List", true);
                        new SubscriberDialog(editDialog, selectedSeries, seriesList);
                    } else {
                        JOptionPane.showMessageDialog(ShowDialog.this, "Please select for which series you want to edit the list.", "Edit Favourite Series List", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            ReviewButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedTitle = seriesJList.getSelectedValue();
                    if (selectedTitle != null) {
                        Series selectedSeries = findSeriesByTitle(selectedTitle);
                        if (selectedSeries != null) {
// Open the review dialog for the selected series
                            showReviewEnvironment(selectedSeries);
                        }
                    } else {
                        JOptionPane.showMessageDialog(ShowDialog.this, "Please select series to review.", "Review", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            panel.add(editFavoriteSeriesButton);
            panel.add(showSeriesButton);
            panel.add(ReviewButton);

            add(scrollPane, BorderLayout.CENTER);
            add(panel, BorderLayout.SOUTH);

        }
        revalidate();
        repaint();
    }

    private Series findSeriesByTitle(String title) {
        for (Series series : seriesList) {
            if (series.getTitle().equals(title)) {
                return series;
            }
        }
        return null;
    }

    private Show findShowByTitle(String title) {
        for (Show show : showsList) {
            if (show.getTitle().equals(title)) {
                return show;
            }
        }
        return null;
    }
    private static List<Review> initializeReviews(List<Subscriber> subscribers) {
        List<Review> reviews = new ArrayList<>();
        Subscriber user1 = subscribers.get(0);
        Subscriber user2 = subscribers.get(1);Subscriber user3 = subscribers.get(2);
        Subscriber user4 = subscribers.get(3);
        Subscriber user5 = subscribers.get(4);
        Review series1_review1 = new Review("Good show", 4, "24/01/2020", "user1", 4.5);
        user1.addReview(series1_review1);
        reviews.add(series1_review1);
        Review series1_review2 = new Review("I didn't like the plot at all", 1, "22/08/2021", "user2",
                1);
        user2.addReview(series1_review2);
        reviews.add(series1_review2);
        Review series1_review3 = new Review("I loved it!", 5, "27/05/2020", "user5", 5);
        user5.addReview(series1_review3);
        reviews.add(series1_review3);
        Review series2_review1 = new Review("Amazing show!", 5, "24/10/2017", "user2", 5);
        user2.addReview(series2_review1);
        reviews.add(series2_review1);
        Review series2_review2 = new Review("It's boring", 2, "18/12/2020", "user1", 1.8);
        user1.addReview(series2_review2);
        reviews.add(series2_review2);
        Review series2_review3 = new Review("A fun show to watch!", 5, "29/03/2019", "user3", 5);
        user3.addReview(series2_review3);
        reviews.add(series2_review3);
        Review series3_review1 = new Review("Amazing!", 5, "05/11/2022", "user1", 5);
        user1.addReview(series3_review1);
        reviews.add(series3_review1);
        Review series3_review2 = new Review("Great show", 5, "09/08/2023", "user4", 4.5);
        user4.addReview(series3_review2);
        reviews.add(series3_review2);
        Review series3_review3 = new Review("Meh", 3, "24/04/2022", "user3", 3);
        user3.addReview(series3_review3);
        reviews.add(series3_review3);
        Review series4_review1 = new Review("I was captivated", 5, "24/01/2022", "user4", 4.8);
        user4.addReview(series4_review1);
        reviews.add(series4_review1);
        Review series4_review2 = new Review("Awful show", 1, "02/01/2023", "user5", 1);
        user5.addReview(series4_review2);
        reviews.add(series4_review2);
        Review series4_review3 = new Review("It's ok", 3, "4/01/2024", "user2", 4.5);
        user2.addReview(series4_review3);
        reviews.add(series4_review3);
        Review series5_review1 = new Review("Interesting show", 4, "24/09/2023", "user2", 4.5);
        user2.addReview(series5_review1);
        reviews.add(series5_review1);
        Review series5_review2 = new Review("Really bad", 1, "21/06/2021", "user5", 1);
        user5.addReview(series5_review2);Review series5_review3 = new Review("Expected more", 2, "07/05/2023", "user1", 2);
        user1.addReview(series5_review3);
        reviews.add(series5_review3);
        Review series6_review1 = new Review("A piece of art", 5, "03/07/2022", "user4", 5);
        user4.addReview(series6_review1);
        reviews.add(series6_review1);
        Review series6_review2 = new Review("Nice work", 5, "22/06/2023", "user2", 5);
        user2.addReview(series6_review2);
        reviews.add(series6_review2);
        Review series6_review3 = new Review("I loved it!", 5, "07/12/2023", "user3", 5);
        user3.addReview(series6_review3);
        reviews.add(series6_review3);
        Review movies1_review1 = new Review("Fantastic", 5, "24/04/2015", "user2", 5);
        user2.addReview(movies1_review1);
        reviews.add(movies1_review1);
        Review movies1_review2 = new Review("Amazing", 5, "24/11/2017", "user1", 4.8);
        user1.addReview(movies1_review2);
        reviews.add(movies1_review2);
        Review movies1_review3 = new Review("Wow", 5, "01/03/2016", "user3", 5);
        user3.addReview(movies1_review3);
        reviews.add(movies1_review3);
        Review movies2_review1 = new Review("Nice", 4, "04/03/2020", "user1", 4.5);
        user1.addReview(movies2_review1);
        reviews.add(movies2_review1);
        Review movies2_review2 = new Review("Good movie", 4, "14/02/2017", "user4", 4.5);
        user4.addReview(movies2_review2);
        reviews.add(movies2_review2);
        Review movies2_review3 = new Review("Disturbing", 1, "24/12/2017", "user5", 4.5);
        user5.addReview(movies2_review3);
        reviews.add(movies2_review3);
        Review movies3_review1 = new Review("Great movie", 4, "06/02/2019", "user1", 4.5);
        user1.addReview(movies3_review1);
        reviews.add(movies3_review1);
        Review movies3_review2 = new Review("Ok", 3, "31/01/2021", "user4", 4.5);
        user4.addReview(movies3_review2);
        reviews.add(movies3_review2);
        Review movies3_review3 = new Review("Nice", 4, "04/09/2016", "user5", 4.5);
        user5.addReview(movies3_review3);
        reviews.add(movies3_review3);
        Review movies4_review1 = new Review("Funny", 5, "15/12/2015", "user2", 4.5);
        user2.addReview(movies4_review1);
        reviews.add(movies4_review1);
        Review movies4_review2 = new Review("Boring", 2, "04/11/2019", "user4", 4.5);
        user4.addReview(movies4_review2);
        reviews.add(movies4_review2);Review movies4_review3 = new Review("Interesting", 4, "24/10/2018", "user3", 4.5);
        user3.addReview(movies4_review3);
        reviews.add(movies4_review3);
        Review movies5_review1 = new Review("Waste of time", 1, "10/12/2023", "user1", 4.5);
        user1.addReview(movies5_review1);
        reviews.add(movies5_review1);
        Review movies5_review2 = new Review("Scary", 4, "20/12/2023", "user5", 4.5);
        user5.addReview(movies5_review2);
        reviews.add(movies5_review2);
        Review movies5_review3 = new Review("Meh", 2, "30/12/2023", "user3", 4.5);
        user3.addReview(movies5_review3);
        reviews.add(movies5_review3);
        Review movies6_review1 = new Review("Blunt", 3, "19/12/2023", "user4", 4.5);
        user4.addReview(movies6_review1);
        reviews.add(movies6_review1);
        Review movies6_review2 = new Review("Loved it", 5, "05/01/2024", "user2", 4.5);
        user2.addReview(movies6_review2);
        reviews.add(movies6_review2);
        Review movies6_review3 = new Review("I was touched", 5, "02/01/2024", "user4", 4.5);
        user4.addReview(movies6_review3);
        reviews.add(movies6_review3);
        return reviews;
    }


}

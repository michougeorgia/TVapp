package gui;

import api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchDialog {

    private JDialog dialog;
    private User user;

    private static List<Show> showsList;

    public SearchDialog(JDialog parent, List<Show> allshows, User user) {
        dialog = new JDialog(parent, "Search", true);
        showsList = allshows;
        this.user = user;


        dialog.setLocation(parent.getX() + 100, parent.getY() + 100);
        buildUI();
        dialog.setVisible(true);
        dialog.pack();
    }
    private void buildUI() {
        JPanel panel = new JPanel(new GridLayout(7, 2));

        JLabel titleLabel = new JLabel("Title:");
        String title = JOptionPane.showInputDialog("Enter title to search:").trim();
        JTextField titleField = new JTextField(title);

        JLabel typeLabel = new JLabel("Type:");
        ShowType type = (ShowType) JOptionPane.showInputDialog(null, "Select show type:",
                "Show Type", JOptionPane.QUESTION_MESSAGE, null, ShowType.values(), null);
        JComboBox<ShowType> typeComboBox = new JComboBox<>(ShowType.values());
        typeComboBox.setSelectedItem(type);


        JLabel protagonistLabel = new JLabel("Protagonist");
        String protagonist = JOptionPane.showInputDialog("Enter the protagonist of the show:").trim();
        JTextField protagonistField = new JTextField(protagonist);

        JLabel minorsLabel = new JLabel("For Minors:");
        isForMinors isforminors = (isForMinors) JOptionPane.showInputDialog(null, "Is the show for minors?",
                "Check for minors", JOptionPane.QUESTION_MESSAGE, null, isForMinors.values(), null);
        JCheckBox minorsCheckBox = new JCheckBox();
        if (isforminors == isForMinors.True) {
            minorsCheckBox.setSelected(true);
        }else{
            minorsCheckBox.setSelected(false);
        }

        JLabel categoryLabel = new JLabel("Category:");
        Category category = (Category) JOptionPane.showInputDialog(null, "Select the category of the show:",
                "Category", JOptionPane.QUESTION_MESSAGE, null, Category.values(), null);
        JTextField categoryField = new JTextField(String.valueOf(category));


        JLabel minOverallRatingLabel = new JLabel("Minimum Overall Rating:");
        String minOverallRating = JOptionPane.showInputDialog("Enter the minimum overall rating of the show:").trim();
        JTextField minOverallRatingField = new JTextField(minOverallRating);


        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(typeLabel);
        panel.add(typeComboBox);
        panel.add(protagonistLabel);
        panel.add(protagonistField);
        panel.add(minorsLabel);
        panel.add(minorsCheckBox);
        panel.add(categoryLabel);
        //panel.add(categoryComboBox);
        panel.add(categoryField);
        panel.add(minOverallRatingLabel);
        panel.add(minOverallRatingField);



        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText().trim();
                ShowType selectedType = (ShowType) typeComboBox.getSelectedItem();
                String protagonist = protagonistField.getText().trim();
                isForMinors isforminors = minorsCheckBox.isSelected() ? isForMinors.True : isForMinors.False;
                Category category = Category.valueOf(categoryField.getText().trim()); // Assuming Category has a String representation
                String minOverallRating = minOverallRatingField.getText().trim();

                ArrayList<Show> searchResults = user.searchShows(showsList, title, selectedType, protagonist, isforminors, category, Double.parseDouble(minOverallRating));



                ArrayList<Movies> movieResults = new ArrayList<>();
                ArrayList<Series> seriesResults = new ArrayList<>();
                for (Show show : searchResults) {
                    if (selectedType == ShowType.MOVIE && show instanceof Movies) {
                        if (user.matchesCriteria(show, title, selectedType, protagonist, isforminors, category, Double.parseDouble(minOverallRating))) {
                            movieResults.add((Movies) show);
                        }
                    } else if (selectedType == ShowType.SERIES && show instanceof Series) {
                        if (user.matchesCriteria(show, title, selectedType, protagonist, isforminors, category, Double.parseDouble(minOverallRating))) {
                            seriesResults.add((Series) show);
                        }
                    }
                }

                if (selectedType == ShowType.MOVIE && !movieResults.isEmpty()) {
                    MovieDialog.displayMovies(movieResults);
                } else if (selectedType == ShowType.SERIES && !seriesResults.isEmpty()) {
                    SeriesDialog.displaySeries(seriesResults);
                } else {
                    JOptionPane.showMessageDialog(dialog, "No results found.", "Search Results", JOptionPane.INFORMATION_MESSAGE);
                }

                dialog.dispose();
            }
        });



        JPanel buttonPanel = new JPanel(new GridLayout(1, 1));
        buttonPanel.add(searchButton);

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.PAGE_END);
    }


    public static void displayMovies(List<Show> showsList) {
        List<Movies> moviesList = new ArrayList<>();
        for (Show show : showsList) {
            if (show instanceof Movies) {
                moviesList.add((Movies) show);
            }
        }
        MovieDialog.displayMovies(moviesList);
    }
}
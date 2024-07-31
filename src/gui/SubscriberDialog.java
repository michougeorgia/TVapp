package gui;

import api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import api.Movies;

public class SubscriberDialog {

    private Subscriber sub;
    private JDialog dialog;
    private Show show;
    private Movies movie;
    private List<Movies> moviesList;
    private Series series;
    private List<Series> seriesList;


    public SubscriberDialog(JDialog parent, Movies movie, List<Movies> allmovies) {
        dialog = new JDialog(parent, "Series Operations", true);
        this.movie = movie;
        this.moviesList = allmovies;

        if (movie != null) {
            int index = moviesList.indexOf(movie);
            if (index != -1) {
                this.movie = moviesList.get(index);
            }
        }

        dialog.setLocation(parent.getX() + 100, parent.getY() + 100);
        buildUI();
        dialog.setVisible(true);
        dialog.pack();
    }

    public SubscriberDialog(JDialog parent, Series series, List<Series> allseries) {
        dialog = new JDialog(parent, "Series Operations", true);
        this.series = series;
        this.seriesList = allseries;

        if (series != null) {
            int index = seriesList.indexOf(series);
            if (index != -1) {
                this.series = seriesList.get(index);
            }
        }

        dialog.setLocation(parent.getX() + 100, parent.getY() + 100);
        buildUI();
        dialog.setVisible(true);
        dialog.pack();
    }

    private void buildUI() {
        JPanel panel = new JPanel(new GridLayout(1, 2));

        JLabel favesLabel = new JLabel("Favourites:");
        JCheckBox favesCheckBox = new JCheckBox();
        if (show != null) {
            favesCheckBox.setSelected(show.isInFavourites(show));
        }


        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (show != null) {

                    if (favesCheckBox.isSelected()) {
                        sub.addToFavorites(show);
                    } else {
                        sub.removeFromFavorites(show);
                    }

                }

                dialog.dispose();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        panel.add(favesLabel);
        panel.add(favesCheckBox);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.PAGE_END);

    }


    public static void displayFavouriteShows(List<Show> favesList) {
        JDialog displayDialog = new JDialog();
        displayDialog.setTitle("Favourite Shows List");
        displayDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> showJList = new JList<>(listModel);

        for (Show show : favesList) {
            listModel.addElement(show.getTitle());
        }

        JScrollPane scrollPane = new JScrollPane(showJList);
        panel.add(scrollPane);

        displayDialog.add(panel);
        displayDialog.setVisible(true);
        displayDialog.pack();
    }
}

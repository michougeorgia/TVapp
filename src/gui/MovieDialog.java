package gui;

import api.Category;
import api.Movies;
import api.Show;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieDialog {

    private JDialog dialog;
    private Movies movie;
    private DefaultListModel<String> castListModel;

    private static List<Movies> moviesList;

    public MovieDialog(JDialog parent, Movies movie, List<Movies> allMovies) {
        dialog = new JDialog(parent, "Movie Operations", true);
        this.movie = movie;
        this.moviesList = allMovies;

        if (movie != null) {
            int index = moviesList.indexOf(movie);
            if (index != -1) {
                this.movie = moviesList.get(index);
            }
        }

        dialog.setLocation(parent.getX() + 100, parent.getY() + 100);
        castListModel = new DefaultListModel<>();
        buildUI();
        dialog.setVisible(true);
        dialog.pack();
    }

    private void buildUI() {
        JPanel panel = new JPanel(new GridLayout(9, 2));

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField(movie != null ? movie.getTitle() : "");

        JLabel descriptionLabel = new JLabel("Description:");
        JTextField descriptionField = new JTextField(movie != null ? movie.getDescription() : "");

        JLabel minorsLabel = new JLabel("For Minors:");
        JCheckBox minorsCheckBox = new JCheckBox();
        if (movie != null) {
            minorsCheckBox.setSelected(movie.isForMinors());
        }

        JLabel releaseYearLabel = new JLabel("Release Year:");
        JTextField releaseYearField = new JTextField(movie != null ? String.valueOf(movie.getReleaseYear()) : "");

        JLabel durationLabel = new JLabel("Duration:");
        JTextField durationField = new JTextField(movie != null ? String.valueOf(movie.getDuration()) : "");

        JLabel castLabel = new JLabel("Cast:");
        JList<String> castList = new JList<>(castListModel);
        if (movie != null && movie.getCast() != null) {
            castListModel.addAll(movie.getCast());
        }

        JScrollPane castScrollPane = new JScrollPane(castList);

        JLabel categoryLabel = new JLabel("Category:");
        JComboBox<Category> categoryComboBox = new JComboBox<>(Category.values());
        if (movie != null) {
            categoryComboBox.setSelectedItem(movie.getCtg());
        }

        JLabel otherMoviesLabel = new JLabel("Other Movies:");
        JTextField otherMoviesField = new JTextField();
        if (movie != null && movie.getOthermovies() != null) {
            otherMoviesField.setText(String.join(", ", movie.getOthermovies()));
        }

        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText().trim();
                String description = descriptionField.getText().trim();
                String releaseYearText = releaseYearField.getText().trim();
                String durationText = durationField.getText().trim();
                String otherMoviesInput = otherMoviesField.getText().trim();

                if (title.isEmpty() || description.isEmpty() || releaseYearText.isEmpty() || durationText.isEmpty() || castListModel.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "All fields must be filled in.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (castListModel.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Cast information must be filled in.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (movie != null) {
                    movie.setTitle(title);
                    movie.setDescription(description);
                    movie.setForMinors(minorsCheckBox.isSelected());

                    try {
                        int releaseYear = Integer.parseInt(releaseYearText);
                        int duration = Integer.parseInt(durationText);


                        movie.setReleaseYear(releaseYear);
                        movie.setDuration(duration);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(dialog, "Release year and duration must be valid numbers.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    List<String> castList = new ArrayList<>();
                    for (int i = 0; i < castListModel.getSize(); i++) {
                        castList.add(castListModel.getElementAt(i));
                    }
                    movie.setCast(castList);

                    movie.setCtg((Category) categoryComboBox.getSelectedItem());

                    movie.setOthermovies(new ArrayList<>(Arrays.asList(otherMoviesInput.split(", "))));
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

        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(descriptionLabel);
        panel.add(descriptionField);
        panel.add(minorsLabel);
        panel.add(minorsCheckBox);
        panel.add(releaseYearLabel);
        panel.add(releaseYearField);
        panel.add(durationLabel);
        panel.add(durationField);
        panel.add(castLabel);
        panel.add(castScrollPane);
        panel.add(categoryLabel);
        panel.add(categoryComboBox);
        panel.add(otherMoviesLabel);
        panel.add(otherMoviesField);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.PAGE_END);
    }

    public static void displayMovies(List<Movies> moviesList) {
        JDialog displayDialog = new JDialog();
        displayDialog.setTitle("Movies List");
        displayDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> moviesJList = new JList<>(listModel);

        for (Movies movie : moviesList) {
            listModel.addElement(movie.getTitle());
        }

        JTextArea movieInfoTextArea = new JTextArea();
        movieInfoTextArea.setEditable(false);

        JScrollPane listScrollPane = new JScrollPane(moviesJList);
        JScrollPane infoScrollPane = new JScrollPane(movieInfoTextArea);

        moviesJList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = moviesJList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Movies selectedMovie = moviesList.get(selectedIndex);
                    if (selectedMovie != null) {
                        String movieInfo = selectedMovie.getMovieInformation();
                        movieInfoTextArea.setText(movieInfo);
                    }
                }
            }
        });

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScrollPane, infoScrollPane);
        splitPane.setResizeWeight(0.5);
        displayDialog.add(splitPane);

        displayDialog.setSize(800, 600);
        displayDialog.setLocationRelativeTo(null);
        displayDialog.setVisible(true);
    }
}
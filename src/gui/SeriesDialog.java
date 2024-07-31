package gui;

import api.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeriesDialog extends JFrame {

    private JDialog dialog;
    private Series series;
    private DefaultListModel<String> castListModel;

    private static List<Series> seriesList;

    private Season season;
    private User currentuser;
    private Subscriber sub;


    public SeriesDialog(JDialog parent, Series series, List<Series> allSeries, User user) {
        dialog = new JDialog(parent, "Series Operations", true);
        this.series = series;
        this.seriesList = allSeries;
        this.currentuser = user;

        if (series != null) {
            int index = seriesList.indexOf(series);
            if (index != -1) {
                this.series = seriesList.get(index);
            }
        }

        dialog.setLocation(parent.getX() + 100, parent.getY() + 100);
        castListModel = new DefaultListModel<>();
        buildUI();
        dialog.setVisible(true);
        dialog.pack();
    }

    private void buildUI() {
        JPanel panel = new JPanel(new GridLayout(10, 2));

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField(series != null ? series.getTitle() : "");

        JLabel descriptionLabel = new JLabel("Description:");
        JTextField descriptionField = new JTextField(series != null ? series.getDescription() : "");

        JLabel categoryLabel = new JLabel("Category:");
        JComboBox<Category> categoryComboBox = new JComboBox<>(Category.values());
        if (series != null) {
            categoryComboBox.setSelectedItem(series.getCtg());
        }

        JLabel minorsLabel = new JLabel("For Minors:");
        JCheckBox minorsCheckBox = new JCheckBox();
        if (series != null) {
            minorsCheckBox.setSelected(series.isForMinors());
        }

        JLabel castLabel = new JLabel("Cast:");
        JList<String> castList = new JList<>(castListModel);
        if (series != null && series.getCast() != null) {
            castListModel.addAll(series.getCast());
        }
        JScrollPane castScrollPane = new JScrollPane(castList);

        JLabel otherSeriesLabel = new JLabel("Other Series:");
        JTextField otherSeriesField = new JTextField();
        if (series != null && series.getOtherseries() != null) {
            otherSeriesField.setText(String.join(", ", series.getOtherseries()));
        }


        JButton editSeasonsButton = new JButton("Edit Seasons");
        editSeasonsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSeasonsEnvironment();
            }
        });

        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String title = titleField.getText().trim();
                String description = descriptionField.getText().trim();
                String otherSeriesInput = otherSeriesField.getText().trim();

                if (title.isEmpty() || description.isEmpty() || castListModel.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "All fields must be filled in.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (castListModel.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Cast information must be filled in.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (series != null) {
                    series.setTitle(title);
                    series.setDescription(description);
                    series.setForMinors(minorsCheckBox.isSelected());

                    List<String> castList = new ArrayList<>();
                    for (int i = 0; i < castListModel.getSize(); i++) {
                        castList.add(castListModel.getElementAt(i));
                    }
                    series.setCast(castList);

                    series.setCtg((Category) categoryComboBox.getSelectedItem());

                    series.setOtherseries(new ArrayList<>(Arrays.asList(otherSeriesInput.split(", "))));
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
        panel.add(castLabel);
        panel.add(castScrollPane);
        panel.add(minorsLabel);
        panel.add(minorsCheckBox);
        panel.add(categoryLabel);
        panel.add(categoryComboBox);
        panel.add(otherSeriesLabel);
        panel.add(otherSeriesField);


        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(editSeasonsButton);

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.PAGE_END);
    }

    public static void displaySeries(List<Series> seriesList) {
        JDialog displayDialog = new JDialog();
        displayDialog.setTitle("Series List");
        displayDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> seriesJList = new JList<>(listModel);

        for (Series series : seriesList) {
            listModel.addElement(series.getTitle());
        }

        JTextArea seriesInfoTextArea = new JTextArea();
        seriesInfoTextArea.setEditable(false);

        JScrollPane listScrollPane = new JScrollPane(seriesJList);
        JScrollPane infoScrollPane = new JScrollPane(seriesInfoTextArea);

        seriesJList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = seriesJList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Series selectedSeries = seriesList.get(selectedIndex);
                    if (selectedSeries != null) {
                        String seriesInfo = selectedSeries.displaySeriesInformation();
                        seriesInfoTextArea.setText(seriesInfo);
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


    private void showSeasonsEnvironment() {
        getContentPane().removeAll();

        JPanel panel = new JPanel(new GridLayout(4, 1));

        JButton addButton = new JButton("Add Seasons");
        JButton editButton = new JButton("Edit Seasons");
        JButton deleteButton = new JButton("Delete Seasons");
        JButton showSeasonsButton = new JButton("Show Seasons");

        addButton.setEnabled(true);
        editButton.setEnabled(true);
        deleteButton.setEnabled(true);
        showSeasonsButton.setEnabled(true);

        //seasonJList = new JList<>();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> seasonJList = new JList<>(listModel);

        for (Season season : series.getSeasons()) {
            listModel.addElement(String.valueOf(season.getSeasonNumber()));
        }
        seasonJList.setModel(listModel);

        JScrollPane scrollPane = new JScrollPane(seasonJList);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog addDialog = new JDialog(SeriesDialog.this, "Add Seasons", true);
                new SeasonsDialog(addDialog, null, series.getSeasons()); // Pass null for a new movie
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSeasonnumber = seasonJList.getSelectedValue();
                if (selectedSeasonnumber != null) {
                    Season selectedSeason = findSeasonByNumber(selectedSeasonnumber);
                    JDialog editDialog = new JDialog(SeriesDialog.this, "Edit Season and Episode Details", true);
                    new SeasonsDialog(editDialog, selectedSeason, series.getSeasons());
                } else {
                    JOptionPane.showMessageDialog(SeriesDialog.this, "Please select a season to edit.", "Edit Season", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSeasonnumber = seasonJList.getSelectedValue();
                if (selectedSeasonnumber != null) {
                    Season selectedSeason = findSeasonByNumber(selectedSeasonnumber);
                    int confirmation = JOptionPane.showConfirmDialog(SeriesDialog.this, "Are you sure you want to delete this season: " + selectedSeasonnumber + "?", "Delete Season", JOptionPane.YES_NO_OPTION);
                    if (confirmation == JOptionPane.YES_OPTION) {
                        series.getSeasons().remove(selectedSeason);
                        listModel.removeElement(selectedSeasonnumber);
                        JOptionPane.showMessageDialog(SeriesDialog.this, "Season deleted successfully.", "Delete Season", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(SeriesDialog.this, "Please select a season to delete.", "Delete Season", JOptionPane.WARNING_MESSAGE);
                }
            }
        });


        showSeasonsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SeasonsDialog.displaySeasonsinfo(series.getSeasons());
            }
        });

        panel.add(addButton);
        panel.add(editButton);
        panel.add(deleteButton);
        panel.add(showSeasonsButton);

        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    private Season findSeasonByNumber(String seasonnumber) {
        for (Season season : series.getSeasons()) {
            if (season.equals(seasonnumber)) {
                return season;
            }
        }
        return null;
    }
}
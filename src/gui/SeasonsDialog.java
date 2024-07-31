package gui;

import api.Season;
import api.Series;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SeasonsDialog extends JFrame{

        private JDialog dialog;
        private Season season;
        private Series series;
        private int seasonnumber;
        private DefaultListModel<String> episodesListModel;
        private DefaultListModel<Integer> epdurListModel;

        private HashMap<String, Integer> eps;
        ArrayList<HashMap<String, Integer>> info = new ArrayList<>();
        List<Integer> seasonsList = new ArrayList<>();

        public SeasonsDialog(JDialog parent, Season season, List<Season> allseasons) {
            dialog = new JDialog(parent, "Seasons Operations", true);
            this.season = season;
            //this.eps = episodes;

            if (season != null) {
                int index = seasonsList.indexOf(season.getSeasonNumber());
                if (index != -1) {
                    this.seasonnumber = seasonsList.get(index);
                }
            }

            dialog.setLocation(parent.getX() + 100, parent.getY() + 100);
            episodesListModel = new DefaultListModel<>();
            epdurListModel = new DefaultListModel<>();
            buildUI();
            dialog.setVisible(true);
            dialog.pack();
        }
        private void buildUI() {
            JPanel panel = new JPanel(new GridLayout(5, 2));

            JLabel seasonLabel = new JLabel("Season:");
            JTextField seasonField = new JTextField(season.getSeasonNumber());

            JLabel yearLabel = new JLabel("Year of production:");
            JTextField yearField = new JTextField(season.getYearOfProduction());

            JLabel episodesLabel = new JLabel("Episodes:");
            JList<String> episodesList = new JList<>(episodesListModel);
            if (eps != null) {
                episodesListModel.addAll(eps.keySet());
            }
            JScrollPane episodesScrollPane = new JScrollPane(episodesList);

            JLabel epdurLabel = new JLabel("Duration of each episode:");
            JList<Integer> epdurList = new JList<>(epdurListModel);
            if (season.getEpisodes() != null) {
                for (String episode: season.getEpisodes().keySet()) {
                    episodesListModel.add(season.getEpisodes().get(episode), episode);
                }
            }
            JScrollPane epdurScrollPane = new JScrollPane(epdurList);

            JButton saveButton = new JButton("Save Changes");
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String seasonnumber = seasonField.getText().trim();
                    String yearofproduction = yearField.getText().trim();
                    //Δεν μπορεί να γίνει εισαγωγή νέων επεισοδίων και η διάρειά τους αντίστοιχα

                    if (seasonnumber.isEmpty() || yearofproduction.isEmpty() || episodesListModel.isEmpty()) {
                        JOptionPane.showMessageDialog(dialog, "All fields must be filled in.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (epdurListModel.isEmpty()) {
                        JOptionPane.showMessageDialog(dialog, "Cast information must be filled in.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (season != null) {
                        ArrayList<String> episodesList = new ArrayList<>();
                        for (int i = 0; i < episodesListModel.getSize(); i++) {
                            episodesList.add(episodesListModel.getElementAt(i));
                        }
                        ArrayList<Integer> epdurList = new ArrayList<>();
                        for (int i = 0; i < epdurListModel.getSize(); i++) {
                            epdurList.add(epdurListModel.getElementAt(i));
                        }

                        series.setEpisodes(episodesList, epdurList);
                    }

                    dialog.dispose();
                }
            });

            HashMap<String, Integer> seasonInfo = new HashMap<>();
            seasonInfo.put("seasonNumber", seasonnumber);
            seasonInfo.put("yearOfProduction", season.getYearOfProduction());
            info.add(seasonInfo);
            info.add(eps);

            JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialog.dispose();
                }
            });

            panel.add(seasonLabel);
            panel.add(seasonField);
            panel.add(yearLabel);
            panel.add(yearField);
            panel.add(episodesLabel);
            panel.add(episodesScrollPane);
            panel.add(epdurLabel);
            panel.add(epdurScrollPane);

            JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
            buttonPanel.add(saveButton);
            buttonPanel.add(cancelButton);

            dialog.add(panel, BorderLayout.CENTER);
            dialog.add(buttonPanel, BorderLayout.PAGE_END);
        }


        public static void displaySeasonsinfo(List<Season> seasonList) {
            JDialog displayDialog = new JDialog();
            displayDialog.setTitle("Seasons and episodes List");
            displayDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);


            JPanel panel = new JPanel();
            DefaultListModel<String> listModel = new DefaultListModel<>();
            JList<String> infoJList = new JList<>(listModel);

            //for (int i=0; i<info.size(); i++) {
                for (Season season: seasonList) {
                    listModel.addElement(String.valueOf(season));
                }
            //}

            JScrollPane scrollPane = new JScrollPane(infoJList);
            panel.add(scrollPane);

            displayDialog.add(panel);
            displayDialog.pack();
            displayDialog.setVisible(true);
        }
}
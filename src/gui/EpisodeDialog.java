package gui;

import api.Season;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EpisodeDialog {

    private JDialog dialog;
    private JTextField titleField;
    private JTextField durationField;
    private Season season;

    public EpisodeDialog(JDialog parent, Season season) {
        this.season = season;
        dialog = new JDialog(parent, "Episode Details", true);
        dialog.setLocation(parent.getX() + 100, parent.getY() + 100);
        buildUI();
        dialog.pack();
        dialog.setVisible(true);
    }

    private void buildUI() {
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField();

        JLabel durationLabel = new JLabel("Duration (minutes):");
        durationField = new JTextField();

        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(durationLabel);
        panel.add(durationField);

        JButton saveButton = buildSaveButton();

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        panel.add(saveButton);
        panel.add(cancelButton);

        dialog.add(panel, BorderLayout.CENTER);
    }

    private JButton buildSaveButton() {
        JButton saveButton = new JButton("Save Episode");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText().trim();
                String durationText = durationField.getText().trim();

                if (title.isEmpty() || durationText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                    return;
                }

                try {
                    int duration = Integer.parseInt(durationText);

                    season.addEpisode(title, duration);

                    JOptionPane.showMessageDialog(null, "Episode added to the season.");
                    dialog.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid duration.");
                }
            }
        });

        return saveButton;
    }

}

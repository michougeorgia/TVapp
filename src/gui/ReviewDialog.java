package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import api.*;
import api.Show;


public class ReviewDialog extends JDialog {
    private static final String REVIEWS_FILE_NAME = "Reviews.dat";
    private static final int MIN_RATING = 0;
    private static final int MAX_RATING = 5;
    private static final String SHOWS_FILE_NAME = "Shows.dat";
    private Show selectedShow;
    private Review existingReview;
    private JTextField reviewTextField;
    private JTextField ratingTextField;
    private User user;
    private JDialog dialog;
    private JButton saveButton;
    private JButton cancelButton;


    public ReviewDialog(JFrame parent, Show show, Review existingReview, User user){
        super(parent, existingReview != null ? "Edit Review" : "Add Review", true);
        this.selectedShow = show;
        this.existingReview = existingReview;
        this.user = user;

        dialog = new JDialog(parent, existingReview != null ? "Edit Review" : "Add Review", true);
        dialog.setLocation(parent.getX() + 100, parent.getY() + 100);
        buildUI();
        dialog.pack();
        dialog.setVisible(true);
    }
    private void buildUI() {
        JPanel panel = new JPanel(new GridLayout(4, 2));

        JLabel reviewLabel = new JLabel("Review:");
        reviewTextField = new JTextField(existingReview != null ? existingReview.getText() : "");
        JLabel ratingLabel = new JLabel("Rating (0-5):");
        ratingTextField = new JTextField(existingReview != null ? String.valueOf(existingReview.getRating()) : "");

        saveButton = new JButton("Save Changes");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveReviewChanges();}
        });
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        panel.add(reviewLabel);
        panel.add(reviewTextField);
        panel.add(ratingLabel);
        panel.add(ratingTextField);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.PAGE_END);
    }
    private void saveReviewChanges() {
        try {
            String reviewText = reviewTextField.getText();
            String ratingText = ratingTextField.getText();
            if (ratingText.isEmpty()) {
                throw new IllegalArgumentException("Review cannot be empty.");
            }
            int rating = Integer.parseInt(ratingText);
            if (rating < MIN_RATING || rating > MAX_RATING) {
                throw new IllegalArgumentException("Rating must be between 0 and 5.");
            }
            String currentDate = java.time.LocalDate.now().toString();
            String username = user.getUsername();
            if (existingReview != null) {

                reviewTextField = new JTextField( existingReview.getText() + "");
                ratingTextField = new JTextField(existingReview.getRating() + "");
                reviewText = reviewTextField.getText();
                ratingText = ratingTextField.getText();
                existingReview.setText(reviewText);
                existingReview.setRating(rating);
                saveToFile(existingReview);
            } else {
                Review updatedReview = new Review(reviewText, rating, currentDate, username, 0.0);
                selectedShow.addReview(updatedReview);
                saveToFile(updatedReview);
            }
            selectedShow.getAverageRating();

            saveToFile(selectedShow);

            dialog.dispose();} catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(dialog, "Invalid rating. Please enter a number.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    private void saveToFile(Review review) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new
                FileOutputStream(REVIEWS_FILE_NAME, true))) {
            oos.writeObject(review);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(dialog, "Error saving review to file.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void saveToFile(Show show) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new
                FileOutputStream(SHOWS_FILE_NAME, true))) {
            oos.writeObject(show);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(dialog, "Error saving show to file.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}

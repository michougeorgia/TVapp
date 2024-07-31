import api.*;
import api.Initialization;
import gui.Login;
import gui.Registration;

import javax.swing.*;
import javax.swing.JOptionPane;

import api.UserRole;
import java.util.List;
import gui.ShowDialog;
import api.User;
import api.Admin;
import api.Subscriber;


/**
 * Το πρόγραμμά σας πρέπει να έχει μόνο μία main, η οποία πρέπει να είναι η παρακάτω.
 * <p>
 * <p>
 * ************* ΜΗ ΣΒΗΣΕΤΕ ΑΥΤΗ ΤΗΝ ΚΛΑΣΗ ************
 */


public class Main {
        public static void main(String[] args) {
                Initialization.initializeEntities();
                int choice = JOptionPane.showOptionDialog(
                        null,
                        "Choose an option:",
                        "Registration/Login",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new Object[]{"Login", "Register"},
                        "Login"
                );

                if (choice == JOptionPane.YES_OPTION) {

                        Login loginForm = new Login();

                        if (loginForm.isLoginSuccessful()) {
                                User loggedInUser = loginForm.getLoggedInUser();

                                if (loggedInUser instanceof Admin) {
                                        Admin adminUser = (Admin) loggedInUser;
                                        new ShowDialog(adminUser);
                                } else if (loggedInUser instanceof Subscriber) {

                                        Subscriber subscriberUser = (Subscriber) loggedInUser;
                                        new ShowDialog(subscriberUser);
                                }
                        }
                } else if (choice == JOptionPane.NO_OPTION) {
                        Registration registrationForm = new Registration();
                        if (registrationForm.isResSuccessful()){
                                Subscriber newSub = registrationForm.getNewSubscriber();
                                new ShowDialog(newSub);

                        }
                } else {
                }
        }



}
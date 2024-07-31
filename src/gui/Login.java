package gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import api.UserRole;
import api.Admin;
import api.User;
import api.Subscriber;

public class Login extends JFrame implements ActionListener {
    private Container c;
    private JLabel title, username, password, res;
    private JTextField tusername, tpassword;
    private JButton login, logout;
    private JTextArea tout;
    private JLabel resadd;
    private boolean loginSuccessful;
    private String loggedInUsername;
    private UserRole loggedUserRole;
    private Admin admin;
    private User loggedInUser;

    public Login() {
        setTitle("Login and Logout Form");
        setBounds(800, 300, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        c = getContentPane();
        c.setLayout(null);
        title = new JLabel("Login and Logout Form");
        title.setFont(new Font("Arial", Font.PLAIN, 28));
        title.setSize(300, 30);
        title.setLocation(300, 30);
        c.add(title);
        username = new JLabel("Username");
        username.setFont(new Font("Arial", Font.PLAIN, 20));
        username.setSize(100, 20);
        username.setLocation(100, 250);
        c.add(username);
        tusername = new JTextField();
        tusername.setFont(new Font("Arial", Font.PLAIN, 15));tusername.setSize(150, 20);
        tusername.setLocation(200, 250);
        c.add(tusername);
        password = new JLabel("Password");
        password.setFont(new Font("Arial", Font.PLAIN, 20));
        password.setSize(100, 20);
        password.setLocation(100, 300);
        c.add(password);
        tpassword = new JTextField();
        tpassword.setFont(new Font("Arial", Font.PLAIN, 15));
        tpassword.setSize(150, 20);
        tpassword.setLocation(200, 300);
        c.add(tpassword);
        login = new JButton("Login");
        login.setFont(new Font("Arial", Font.PLAIN, 15));
        login.setSize(100, 20);
        login.setLocation(150, 450);
        login.addActionListener(this);
        c.add(login);
        logout = new JButton("Logout");
        logout.setFont(new Font("Arial", Font.PLAIN, 15));
        logout.setSize(100, 20);
        logout.setLocation(270, 450);
        logout.addActionListener(this);
        c.add(logout);
        tout = new JTextArea();
        tout.setFont(new Font("Arial", Font.PLAIN, 15));
        tout.setSize(300, 400);
        tout.setLocation(500, 100);
        tout.setLineWrap(true);
        tout.setEditable(false);
        c.add(tout);
        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(100, 500);
        c.add(res);
        resadd = new JLabel();
        resadd.setFont(new Font("Arial", Font.PLAIN, 15));
        resadd.setSize(200, 75);
        resadd.setLocation(580, 175);
        c.add(resadd);
        setVisible(true);
    }
    public boolean isLoginSuccessful() {
        return loginSuccessful;
    }
    public User getLoggedInUser() {
        return loggedInUser;
    }
    public String getLoggedInUsername() {
        return loggedInUsername;
    }
    public UserRole getLoggedUserRole() {
        return loggedUserRole;
    }
    public Admin getAdminInstance() {
        return admin;
    }
    // Method to set the Admin instance
    public void setAdminInstance(Admin admin) {
        this.admin = admin;
    }
    private UserRole determineUserRole(String username) {
        if (username.length() >= 5 && username.substring(0, 5).equalsIgnoreCase("admin")) {
            return UserRole.ADMIN;
        } else if (username.length() >= 4 && username.substring(0, 4).equalsIgnoreCase("user")) {
            return UserRole.SUBSCRIBER;
        } else {
            return UserRole.UNKNOWN;
        }
    }
    private User authenticateUser(String username, String password) {

        if (username.length() >= 5 && username.substring(0, 5).equalsIgnoreCase("admin")) {
            return new Admin(username, password);
        } else if (username.length() >= 4 && username.substring(0, 4).equalsIgnoreCase("user")) {
            return new Subscriber(username, password);
        } else {
            return null;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            String enteredUsername = tusername.getText();
            String enteredPassword = tpassword.getText();

            // Append entered username and password to tout
            tout.append("Entered Username: " + enteredUsername + "\n");
            tout.append("Entered Password: " + enteredPassword + "\n\n");

            User user = authenticateUser(enteredUsername, enteredPassword);
            if (user != null) {
                res.setText("Επιτυχής σύνδεση: Τύπος Χρήστη - " + user.getUserRole());
                loginSuccessful = true;
                loggedInUsername = enteredUsername;
                loggedInUser = user;

                if (user instanceof Admin) {
                    user = new Admin(user.getUsername(), user.getPassword());
                    new ShowDialog(user);
                } else if (user instanceof Subscriber) {
                    user = new Subscriber(user.getUsername(), user.getPassword());
                    new ShowDialog(user);
                }
            } else {
                res.setText("Σφάλμα: Άγνωστος τύπος χρήστη ή μη έγκυρα στοιχεία");
                loginSuccessful = false;
                loggedInUsername = null;
                loggedInUser = null;
            }
        } else if (e.getSource() == logout) {
            dispose();
        }
    }

}
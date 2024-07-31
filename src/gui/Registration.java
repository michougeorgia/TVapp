package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import api.User;
import api.Subscriber;
/**
 * Registration form only for subscribers (not administrators).
 */
public class Registration extends JFrame implements ActionListener {
    private Container c;
    private JLabel name;
    private JTextField tname;
    private JLabel surname;
    private JTextField tsurname;
    private JLabel username;
    private JTextField tusername;
    private JLabel password;
    private JPasswordField tpassword;
    private JCheckBox term;
    private JButton sub;
    private JButton reset;
    private JTextArea tout;
    private JLabel res;
    private JTextArea resadd;
    private ArrayList<String> usernames;

    private boolean resSuccessful;
    private Subscriber subscriber;

    public Registration() {
        setTitle("Subscriber Registration Form");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        JLabel title = new JLabel("Subscriber Registration Form");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(400, 30);
        title.setLocation(250, 30);
        c.add(title);

        name = new JLabel("Όνομα");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(100, 100);
        c.add(name);

        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(190, 20);
        tname.setLocation(200, 100);
        c.add(tname);

        surname = new JLabel("Επίθετο");
        surname.setFont(new Font("Arial", Font.PLAIN, 20));
        surname.setSize(100, 20);
        surname.setLocation(100, 150);
        c.add(surname);

        tsurname = new JTextField();
        tsurname.setFont(new Font("Arial", Font.PLAIN, 15));
        tsurname.setSize(150, 20);
        tsurname.setLocation(200, 150);
        c.add(tsurname);

        username = new JLabel("Username");
        username.setFont(new Font("Arial", Font.PLAIN, 20));
        username.setSize(100, 20);
        username.setLocation(100, 200);
        c.add(username);

        tusername = new JTextField();
        tusername.setFont(new Font("Arial", Font.PLAIN, 15));
        tusername.setSize(75, 20);
        tusername.setLocation(200, 200);
        c.add(tusername);

        password = new JLabel("Password");
        password.setFont(new Font("Arial", Font.PLAIN, 20));
        password.setSize(100, 20);
        password.setLocation(100, 250);
        c.add(password);

        tpassword = new JPasswordField();
        tpassword.setFont(new Font("Arial", Font.PLAIN, 15));
        tpassword.setSize(75, 20);
        tpassword.setLocation(200, 250);
        c.add(tpassword);

        term = new JCheckBox("Accept Terms And Conditions.");
        term.setFont(new Font("Arial", Font.PLAIN, 15));
        term.setSize(250, 20);
        term.setLocation(150, 300);
        c.add(term);

        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 20);
        sub.setLocation(150, 350);
        sub.addActionListener(this);
        c.add(sub);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 20);
        reset.setLocation(270, 350);
        reset.addActionListener(this);
        c.add(reset);

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

        resadd = new JTextArea();
        resadd.setFont(new Font("Arial", Font.PLAIN, 15));
        resadd.setSize(200, 75);
        resadd.setLocation(580, 175);
        resadd.setLineWrap(true);
        c.add(resadd);

        usernames = new ArrayList<>();

        setVisible(true);
    }


    public boolean isResSuccessful() {
        return resSuccessful;
    }

    private boolean isUsernameExists(String username) {
        return usernames.contains(username);
    }

    public Subscriber getNewSubscriber() {
        return subscriber;
    }


    private String hashPassword(String password) {
        return password;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sub) {
            String data = "Name : " + tname.getText() + "\n";
            String data1 = "Surname : " + tsurname.getText() + "\n";
            String data2 = "Username : " + tusername.getText() + "\n";
            String data3 = "Password : " + hashPassword(new String(tpassword.getPassword())) + "\n";

            if (isUsernameExists(data2)) {
                res.setText("Error: Username already exists. Please choose a different username.");
            } else {
                if (data.trim().isEmpty() || data1.trim().isEmpty() || data2.trim().isEmpty() ||
                        data3.trim().isEmpty() || !term.isSelected()) {
                    tout.setText("");
                    resadd.setText("");
                    res.setText("Error: Please fill in all the fields!");
                } else {
                    usernames.add(data2);
                    tout.setText(data + data1 + data2 + data3);
                    tout.setEditable(false);
                    res.setText("Success: Account created successfully!");

                    Subscriber subscriber = new Subscriber(data2, data3);


                    new ShowDialog(subscriber);

                    dispose();
                }
            }
        } else if (e.getSource() == reset) {
            String def = "";
            tname.setText(def);
            tsurname.setText(def);
            tusername.setText(def);
            tpassword.setText(def);
            tout.setText(def);
            res.setText(def);
            resadd.setText(def);
            term.setSelected(false);
        }
    }


}

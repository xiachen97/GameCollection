import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;


public class RegisterPage extends JFrame {
    //initial RegisterPage panel font
    final private Font mainFont =new Font("Segoe print",Font.BOLD,30);
    final private Font secondFont =new Font("Segoe print",Font.BOLD,18);
    final private Font buttonFont =new Font("Segoe print",Font.BOLD,20);

    private JPanel bigPane;
    private JTextField firstname;
    private JTextField lastname;
    private JTextField email;
    private JTextField username;
    private JTextField phone;
    private JPasswordField passwordField;
    private JButton RegisterButton;
    private JButton CancelButton;

    public RegisterPage() {

        /////////////////////////////////set RegisterPage main frame
        setTitle("Register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1050, 800);
        setResizable(false);
        bigPane = new JPanel();
        bigPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(bigPane);
        bigPane.setLayout(null);


        /////////////////////////////////Create all attriable about RegisterPage content panel include TextField & PasswordField & Button' in RegisterPage
        JLabel lblNewUserRegister = new JLabel("Register", SwingConstants.CENTER);
        lblNewUserRegister.setFont(mainFont);
        lblNewUserRegister.setBounds(362, 52, 325, 50);
        bigPane.add(lblNewUserRegister);

        JLabel lblName = new JLabel("First name");
        lblName.setFont(secondFont);
        lblName.setBounds(58, 152, 99, 43);
        bigPane.add(lblName);

        JLabel lblNewLabel = new JLabel("Last name");
        lblNewLabel.setFont(secondFont);
        lblNewLabel.setBounds(58, 243, 110, 29);
        bigPane.add(lblNewLabel);

        JLabel lblEmailAddress = new JLabel("Email");
        lblEmailAddress.setFont(secondFont);
        lblEmailAddress.setBounds(58, 324, 124, 36);
        bigPane.add(lblEmailAddress);

        firstname = new JTextField();
        firstname.setFont(secondFont);
        firstname.setBounds(214, 151, 228, 50);
        bigPane.add(firstname);
        firstname.setColumns(10);

        lastname = new JTextField();
        lastname.setFont(secondFont);
        lastname.setBounds(214, 235, 228, 50);
        bigPane.add(lastname);
        lastname.setColumns(10);

        email = new JTextField();
        email.setFont(secondFont);
        email.setBounds(214, 320, 228, 50);
        bigPane.add(email);
        email.setColumns(10);

        username = new JTextField();
        username.setFont(secondFont);
        username.setBounds(707, 151, 228, 50);
        bigPane.add(username);
        username.setColumns(10);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(secondFont);
        lblUsername.setBounds(542, 159, 99, 29);
        bigPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(secondFont);
        lblPassword.setBounds(542, 245, 99, 24);
        bigPane.add(lblPassword);

        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setFont(secondFont);
        lblPhone.setBounds(542, 329, 139, 26);
        bigPane.add(lblPhone);

        phone = new JTextField();
        phone.setFont(secondFont);
        phone.setBounds(707, 320, 228, 50);
        bigPane.add(phone);
        phone.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(secondFont);
        passwordField.setBounds(707, 235, 228, 50);
        bigPane.add(passwordField);

        RegisterButton = new JButton("Register");
        RegisterButton.setFont(buttonFont);

        //When click register button, new user identity info will upload to connect sql database. we can success login
        //listen to register button clicks
        RegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstname.getText();
                String lastName = lastname.getText();
                String emailId = email.getText();
                String userName = username.getText();
                String Number = phone.getText();
                int len = Number.length();
                String password = String.valueOf(passwordField.getPassword());

                String info = "" + firstName;
                info += " \n";
                if (len != 10) {
                    JOptionPane.showMessageDialog(RegisterButton, "Valid mobile number!");
                }

                try {
                    ////Only for example; Here, someone need input new database url...
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/swing_demo", "root", "");

                    String query = "INSERT INTO account values('" + firstName + "','" + lastName + "','" + userName + "','" +
                        password + "','" + emailId + "','" + Number + "')";

                    Statement sta = connection.createStatement();
                    int x = sta.executeUpdate(query);
                    if (x == 0) {
                        JOptionPane.showMessageDialog(RegisterButton, "This is alredy exist!");
                    } else {
                        JOptionPane.showMessageDialog(RegisterButton,
                            "Congratulations! " + info + "Your account is sucessfully created!");
                    }
                    connection.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        CancelButton = new JButton("Cancel/Return");
        CancelButton.setFont(buttonFont);
        //When click cancel button, we will 'cancel register' || 'already success register then return login page'
        //listen to cancel button clicks
        CancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
            
        });

        //get location button and show Register & Cancel/return button
        RegisterButton.setBounds(199, 447, 259, 74);
        bigPane.add(RegisterButton);
        CancelButton.setBounds(559, 447, 259, 74);
        bigPane.add(CancelButton);

    }

}
    
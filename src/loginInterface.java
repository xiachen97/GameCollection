import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class loginInterface extends JFrame{
    //initial login panel font
    final private Font mainFont =new Font("Segoe print",Font.BOLD,18);
    final private Font secondFont =new Font("Segoe print",Font.ITALIC,15);
    JTextField tfEmail;
    JPasswordField pfPassword;

    public void initialize(){
        /////////////////////////////////Set Email and Password and register Panel
        JLabel lbLogin = new JLabel("Login",SwingConstants.CENTER);
        lbLogin.setFont(mainFont);

        JLabel lbEmail = new JLabel("Email");
        lbEmail.setFont(mainFont);

        tfEmail = new JTextField();
        tfEmail.setFont(mainFont);

        JLabel lbPassword = new JLabel("Password");
        lbPassword.setFont(mainFont);

        pfPassword = new JPasswordField();
        pfPassword.setFont(mainFont);

        JLabel lbRegister = new JLabel(">> New user? Create an account");
        lbRegister.setForeground(Color.BLUE);
        lbRegister.setFont(secondFont);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,1,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));//border indent
        panel.add(lbLogin);

        panel.add(lbEmail);
        panel.add(tfEmail);

        panel.add(lbPassword);
        panel.add(pfPassword);

        panel.add(lbRegister);
        
        //Here, we will connect to registerPage
        //add a mouselistener instead and listen to mouse clicks
        lbRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            RegisterPage frame = new RegisterPage();
                            frame.setVisible(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                
            } 

        });

        /////////////////////////////////Set 'Login' Button Panel
        JButton bLogin = new JButton("Login");
        bLogin.setFont(mainFont);

        bLogin.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //read user email and password
                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());

                //call method and get authenticated user
                UserCredentials user = getUserAuthentication(email,password);

                if(user != null){
                    SuccessLoginPage sp = new SuccessLoginPage();
                    sp.initialize(user);
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(loginInterface.this, "User Email or Passseord is Invaild", "Try again!", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        /////////////////////////////////Set 'Cancel' Button Panel
        JButton bCancel = new JButton("Cancel");
        bCancel.setFont(mainFont);
        bCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
            
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2, 10, 0));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        buttonsPanel.add(bLogin);
        buttonsPanel.add(bCancel);

        /////////////////////////////////Initialise LoginImage

        //show email & password panel and button panel
        add(panel,BorderLayout.NORTH);
        add(buttonsPanel,BorderLayout.SOUTH);

        /////////////////////////////////set LoginImage main frame
        setTitle("Login");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400,500);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    //By connect database to check if the user is authorized to use the app or not
    private UserCredentials getUserAuthentication(String em,String pw){
        UserCredentials user = null;
        final String url="jdbc:mysql://localhost/swing_demo";//Only for example; Here, someone need input new database url...
        final String userName="root";
        final String password="";

        try{
            Connection connection = DriverManager.getConnection(url, userName, password);
            /////////////////////////////////if connect success
            String sql = "SELECT * FROM account WHERE email_id=? AND password=?";
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1,em);
            pStatement.setString(2,pw);

            /////////////////////////////////execute and run db result
            ResultSet result = pStatement.executeQuery();


            if (result.next()) {
                user = new UserCredentials();
                user.username = result.getString("user_name");
                user.email = result.getString("email_id");
                user.password = result.getString("password");
                System.out.println("Success Login!");
            }

            pStatement.close();
            connection.close();

        }catch(Exception e){
            System.out.println("Database connection failed!");
        }

        return user;
    }


    public static void main(String[] args) {

        loginInterface login = new loginInterface();
        login.initialize();;
    }

}
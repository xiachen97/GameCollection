//On SuccessLoginPage is SecondPage, we might create some game buttons for user...


import javax.swing.*;


public class SuccessLoginPage extends JFrame {

    public void initialize(UserCredentials user) {
        /////////////////////////////////Successful access to second page board from login page







        //Create more functionality for this page...







        /////////////////////////////////set SecondPage main frame
        setTitle("Welcome! "+user.username);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        

    }

}

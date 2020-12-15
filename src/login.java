import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class login implements ActionListener{
	
	JFrame frmLogin;
	private JLabel lblUser, lblPassword, lblStatus;
	private JTextField txtUser;
	private JPasswordField txtPassword;
	private JButton btnLogin;
	private static String strUsername, strPassword;
	
	public login() {
		frmLogin = new JFrame("Fraction Game");	//Launches the login window.
		frmLogin.setSize(400, 150);
		frmLogin.setLocationRelativeTo(null);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pnlLogin = new JPanel();
		pnlLogin.setLayout(null);
		frmLogin.add(pnlLogin);
		
		lblUser = new JLabel("Username: ");	//Username label.
		lblUser.setBounds(10, 20, 80, 25);
		pnlLogin.add(lblUser);
		
		txtUser = new JTextField();	//Text field for taking the user's username.
		txtUser.setBounds(100, 20, 165, 25);
		pnlLogin.add(txtUser);
		
		btnLogin = new JButton("Login");	//Login button.
		btnLogin.setBounds(300, 20, 80, 25);
		btnLogin.addActionListener(this);
		pnlLogin.add(btnLogin);
		
		lblPassword = new JLabel("Password: ");	//Password label.
		lblPassword.setBounds(10, 50, 80, 25);
		pnlLogin.add(lblPassword);
		
		txtPassword = new JPasswordField();	//Text field for taking the user's password.
		txtPassword.setBounds(100, 50, 165, 25);
		pnlLogin.add(txtPassword);
		
		lblStatus = new JLabel("");	//Tells the user why their input is invalid.
		lblStatus.setBounds(110, 80, 200, 25);
		pnlLogin.add(lblStatus);
		
		
		frmLogin.getRootPane().setDefaultButton(btnLogin);	//Allow the user to activate the "loginbutton" with the ENTER key.
		
		frmLogin.setVisible(true);
	}
	
	private static int inputCheck() {	//Checks to make sure the user's input is valid.
		String strUser = "Test";
		String strPass = "test";
		
		if(strUsername.equals(strUser) && strPassword.equals(strPass))	//Login successful.
			return 1;
		if(strUsername.equals(strUser) != true && strUsername.equals("") != true) 	//Wrong username.
			return 2;
		if(strUsername.equals(strUser) && strPassword.equals(strPass) != true) 	//Username accepted, wrong password.
			return 3;
		if(strUsername.equals(strUser) && strPassword.equals("")) 	//Username accepted, empty password.
			return 4;
		if(strUsername.equals("") && strPassword.equals(""))  //Both empty.
			return 4;
		if(strUsername.equals(strUser) != true && strPassword.equals(strPass) != true) 	//Both invalid.
			return 5;

		return 5;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLogin) {	//Sets the logic for the "btnLogin"
			strUsername = txtUser.getText();
			strPassword = txtPassword.getText();
			
			int switchState = inputCheck();	//Check to make sure the user's username and password are valid.
			
			switch(switchState) {
			case 1: 
				frmLogin.dispose();
				startGame launchStartGame = new startGame();	//Start the game.
				break;
			case 2: 
				lblStatus.setText("Username invalid.");
				break;
			case 3: 
				lblStatus.setText("Password invalid.");
				break;
			case 4: 
				lblStatus.setText("Both fields require input.");
				break;
			case 5: 
				lblStatus.setText("Invalid username and password.");
				break;
			}	
		}
	}
	
}

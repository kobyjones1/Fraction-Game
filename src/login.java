import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class login implements ActionListener{
	
	JFrame frmLogin;
	public static Connection connDB;
	private JLabel lblUser, lblPassword, lblStatus;
	private JTextField txtUser;
	private JPasswordField txtPassword;
	private JButton btnLogin;
	public static String strUsername, strPassword, playerDB, dbURL;
	
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
	
	private static void inputCheck() {	//Checks to make sure the user's input is valid.
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLogin) {	//Sets the logic for the "btnLogin"
			strUsername = txtUser.getText();
			strPassword = String.valueOf(txtPassword.getPassword());
			
			PreparedStatement state = null;
			ResultSet result = null;

			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			}
			catch(ClassNotFoundException e1){
				e1.printStackTrace();
			}
			
			try {
				playerDB = "A:/Software/Java Projects/Fraction Game/playerInfo.accdb";
				dbURL = "jdbc:ucanaccess://" + playerDB;
				connDB = DriverManager.getConnection(dbURL);
				
				state = connDB.prepareStatement("SELECT Username, Password FROM Players WHERE Username = ? AND Password = ?");
				
				state.setString(1, strUsername);
				state.setString(2, strPassword);
				
				result = state.executeQuery();
				
				//System.out.println(strUsername);
				//System.out.println(strPassword);
				
				if(result.next()) {
					frmLogin.dispose(); 
					startGame launchStartGame = new startGame();
				}
				else {
					lblStatus.setText("Invalid input.");
				}
			}
			catch(SQLException e2) {
				e2.printStackTrace();
			}
			
			//int switchState = inputCheck();	//Check to make sure the user's username and password are valid.
			//inputCheck();
			/*
			 * switch(switchState) { case 1: frmLogin.dispose(); startGame launchStartGame =
			 * new startGame(); //Start the game. break; case 2:
			 * lblStatus.setText("Username invalid."); break; case 3:
			 * lblStatus.setText("Password invalid."); break; case 4:
			 * lblStatus.setText("Both fields require input."); break; case 5:
			 * lblStatus.setText("Invalid username and password."); break; }
			 */
		}
	}
	
}

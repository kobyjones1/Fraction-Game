import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class login implements ActionListener{
	
	private static JFrame frmLogin;
	public static Connection connDB;
	private JLabel lblUser, lblPassword, lblStatus;
	private JTextField txtUser;
	private JPasswordField txtPassword;
	private JButton btnLogin, btnMusic;
	private static boolean isPlaying = true;
	public static String strUsername, strPassword;
	
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
		lblStatus.setBounds(100, 80, 200, 25);
		pnlLogin.add(lblStatus);
		
		btnMusic = new JButton("Music");	//Control the music.
		btnMusic.setBounds(300, 50, 80, 25);
		btnMusic.addActionListener(this);
		pnlLogin.add(btnMusic);
		
		frmLogin.getRootPane().setDefaultButton(btnLogin);	//Allow the user to activate the "loginbutton" with the ENTER key.
		
		frmLogin.setVisible(true);
	}
	
	public static void toggleMusic() {
		if(isPlaying == true) {
			fracGame.pauseMusic();
			isPlaying = false;
		}
		else {
			fracGame.playMusic();
			isPlaying = true;
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLogin) {	//Sets the logic for the "btnLogin"
			strUsername = txtUser.getText();
			strPassword = String.valueOf(txtPassword.getPassword());
			
			PreparedStatement prepState = null;
			ResultSet resSet = null;

			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");	//Read the playerInfo database with Ucanaccess.
			}
			catch(ClassNotFoundException e1){
				e1.printStackTrace();
			}
			
			try {
				String strPlayerDB = "A:/Software/Java Projects/Fraction Game/playerInfo.accdb";
				String strURL = "jdbc:ucanaccess://" + strPlayerDB;
				connDB = DriverManager.getConnection(strURL);
				
				prepState = connDB.prepareStatement("SELECT Username, Password FROM Players WHERE Username = ? AND Password = ?");
				prepState.setString(1, strUsername);
				prepState.setString(2, strPassword);
				resSet = prepState.executeQuery();

				if(resSet.next()) {	//If the user's input matches a database entry, the game will start.
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
		}
		
		if(e.getSource() == btnMusic) {
			toggleMusic();
		}
		
	}
	
}

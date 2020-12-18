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
	private static JLabel lblUser, lblPassword, lblQues, lblStatus;
	private static JTextField txtUser, txtQues;
	private static JPasswordField txtPassword;
	private JButton btnLogin, btnMusic, btnSignUp, btnClose;
	private static boolean isPlaying = true;
	public static String strUsername, strPassword;
	public static int intQuesAmount;
	
	private static void startConnection() {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");	//Read the playerInfo database with Ucanaccess.
			
			String strPlayerDB = "A:/Software/Java Projects/Fraction Game/playerInfo.accdb";
			String strURL = "jdbc:ucanaccess://" + strPlayerDB;
			connDB = DriverManager.getConnection(strURL);
		}
		catch(ClassNotFoundException | SQLException e1){
			e1.printStackTrace();
		}
	}
	
	public login() {
		startConnection();

		frmLogin = new JFrame("Fraction Game");	//Launches the login window.
		frmLogin.setSize(400, 210);
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

		btnSignUp = new JButton("Sign Up");	//Sign up button.
		btnSignUp.setBounds(300, 20, 80, 25);
		btnSignUp.addActionListener(this);
		pnlLogin.add(btnSignUp);
		
		btnLogin = new JButton("Login");	//Login button.
		btnLogin.setBounds(300, 50, 80, 25);
		btnLogin.addActionListener(this);
		pnlLogin.add(btnLogin);
		
		lblPassword = new JLabel("Password: ");	//Password label.
		lblPassword.setBounds(10, 50, 80, 25);
		pnlLogin.add(lblPassword);
		
		txtPassword = new JPasswordField();	//Text field for taking the user's password.
		txtPassword.setBounds(100, 50, 165, 25);
		pnlLogin.add(txtPassword);
		
		lblQues = new JLabel("Amount of Questions (Default = 3): ");	//Username label.
		lblQues.setBounds(10, 80, 200, 25);
		pnlLogin.add(lblQues);
		
		txtQues = new JTextField();	//Text field for taking the user's username.
		txtQues.setBounds(210, 80, 55, 25);
		pnlLogin.add(txtQues);
		
		btnMusic = new JButton("Music");	//Control the music.
		btnMusic.setBounds(10, 140, 80, 25);
		btnMusic.addActionListener(this);
		pnlLogin.add(btnMusic);
		
		lblStatus = new JLabel("", JLabel.CENTER);	//Tells the user why their input is invalid.
		lblStatus.setBounds(65, 110, 250, 25);
		pnlLogin.add(lblStatus);
		
		btnClose = new JButton("Close");	//Control the music.
		btnClose.setBounds(300, 140, 80, 25);
		btnClose.addActionListener(this);
		pnlLogin.add(btnClose);
		
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
	
	private static void loadAccount() {
		strUsername = txtUser.getText();
		strPassword = String.valueOf(txtPassword.getPassword());
		
		PreparedStatement prepState = null;
		ResultSet resSet = null;

		try {
			prepState = connDB.prepareStatement("SELECT Username FROM Players WHERE Username = ?");
			prepState.setString(1, strUsername);
			resSet = prepState.executeQuery();
			
			if(resSet.next()) {	//If the user's input matches a database entry, the game will start.
				prepState = connDB.prepareStatement("SELECT Password FROM Players WHERE Password = ?");
				prepState.setString(1, strPassword);
				resSet = prepState.executeQuery();
				
				if(resSet.next()) {
					frmLogin.dispose(); 
					startGame launchStartGame = new startGame();
				}
				else {
					lblStatus.setText("Invalid password.");
				}
			}
			else {
				lblStatus.setText("Invalid username.");
			}
		}
		catch(SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSignUp) {
			frmLogin.dispose();
			signUp launchSignUp = new signUp();
		}
		
		if(e.getSource() == btnLogin) {	//Sets the logic for the "btnLogin"
			if(txtUser.getText().equals("") && String.valueOf(txtPassword.getPassword()).equals("") && txtQues.getText().equals("")) {
				lblStatus.setText("No input detected");
			}
			else if(txtQues.getText().equals("") != true) {
				try {
					intQuesAmount = Integer.parseInt(txtQues.getText());
					
					if(intQuesAmount <= 0) {
						lblStatus.setText("The amount of questions must be larger than 0.");
					}
					else {
						loadAccount();
					}
				}
				catch(NumberFormatException ex) {
					lblStatus.setText("The amount of questions must be a number.");
				}
			}
			else {
				intQuesAmount = 3; //Default number of questions.
				loadAccount();
			}
		}
		
		if(e.getSource() == btnMusic) {
			toggleMusic();
		}
		
		if(e.getSource() == btnClose) {
			frmLogin.dispose();
		}
		
	}
	
}

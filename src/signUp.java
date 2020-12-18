import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class signUp implements ActionListener{
	
	private static JFrame frmSignUp;
	private static JLabel lblUser, lblPass, lblConfirm, lblStatus;
	private static JTextField txtUser;
	private static JPasswordField txtPass, txtConfirm;
	private static JButton btnCreate, btnBack, btnMusic;
	
	public signUp(){
		frmSignUp = new JFrame("Fraction Game");	//Launches the signUp window.
		frmSignUp.setSize(320, 205);
		frmSignUp.setLocationRelativeTo(null);
		frmSignUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pnlSignUp = new JPanel();
		pnlSignUp.setLayout(null);
		frmSignUp.add(pnlSignUp);
		
		lblUser = new JLabel("Create Username: ");	//Create username label.
		lblUser.setBounds(10, 20, 150, 25);
		pnlSignUp.add(lblUser);
		
		txtUser = new JTextField();	//Text field for taking the user's new username.
		txtUser.setBounds(130, 20, 165, 25);
		pnlSignUp.add(txtUser);

		lblPass = new JLabel("Create Password: ");	//Create password label.
		lblPass.setBounds(10, 50, 150, 25);
		pnlSignUp.add(lblPass);
		
		txtPass = new JPasswordField();	//Text field for taking the user's new password.
		txtPass.setBounds(130, 50, 165, 25);
		pnlSignUp.add(txtPass);
		
		lblConfirm = new JLabel("Confirm Password: ");	//Password confirmation label.
		lblConfirm.setBounds(10, 80, 150, 25);
		pnlSignUp.add(lblConfirm);
		
		txtConfirm = new JPasswordField();	//Text field for checking the user's password.
		txtConfirm.setBounds(130, 80, 165, 25);
		pnlSignUp.add(txtConfirm);
		
		lblStatus = new JLabel("");	//Tells the user if their input is valid.
		lblStatus.setBounds(80, 110, 150, 25);
		pnlSignUp.add(lblStatus);
		
		btnCreate = new JButton("Create");	//Attempts to create a new account when clicked.
		btnCreate.setBounds(130, 135, 80, 25);
		btnCreate.addActionListener(this);
		pnlSignUp.add(btnCreate);
		
		btnBack = new JButton("Back");	//Returns to the login window.
		btnBack.setBounds(223, 135, 70, 25);
		btnBack.addActionListener(this);
		pnlSignUp.add(btnBack);
		
		btnMusic = new JButton("Music");	//Controls the music.
		btnMusic.setBounds(10, 135, 70, 25);
		btnMusic.addActionListener(this);
		pnlSignUp.add(btnMusic);
		
		frmSignUp.getRootPane().setDefaultButton(btnCreate);	//Allow the user to activate the "btnCreate" with the ENTER key.
		
		frmSignUp.setVisible(true);
	}
	
	private static int inputCheck() {	//Check to make sure the user's input is valid.
		if(txtUser.getText().equals("")) {	
			return 1;	//Empty username.	
		}
		else if(txtUser.getText().equals("") != true && String.valueOf(txtPass.getPassword()).equals("")) {	
			return 2;	//Empty password.
		}
		else if(txtUser.getText().equals("") != true && String.valueOf(txtPass.getPassword()).equals("") != true && String.valueOf(txtConfirm.getPassword()).equals("")) {	
			return 3;	//Empty password confirmation.
		}
		else if(txtUser.getText().equals("") != true && String.valueOf(txtPass.getPassword()).equals(String.valueOf(txtConfirm.getPassword())) != true) {	
			return 3;	//Passwords don't match.
		}
		else {
			return 4;	//Account creation successful.
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCreate) {	//Responsible for creating the user's new account.
			int switchState = inputCheck();
			
			switch(switchState) {	//Logic for responding to the user's input.
			case 1:
				lblStatus.setText("Username invalid.");
				break;
			case 2:
				lblStatus.setText("Password invalid.");
				break;
			case 3:
				lblStatus.setText("Passwords must match.");
				break;
			case 4:
				String strSQL;
				
				Connection connSign = login.connDB;
				PreparedStatement prepState = null;
				ResultSet resSet = null;

				boolean usernameCheck = true;	//Checks to make sure the username hasn't been used already.
				
				try {
					strSQL = "SELECT * FROM Players WHERE Username = ?";
					prepState = connSign.prepareStatement(strSQL);
					prepState.setString(1, txtUser.getText());
					resSet = prepState.executeQuery();
					
					if(resSet.next()) {	//If an account is found, the username is already in use.
						usernameCheck = false;
					}
					
					if(usernameCheck == false) {
						lblStatus.setText("Username unavailable.");
						break;
					}
					else {
						strSQL = "INSERT INTO Players (Username, Password, Score) VALUES (?,?,?)";	
						prepState = connSign.prepareStatement(strSQL);
						prepState.setString(1, txtUser.getText());
						prepState.setString(2, String.valueOf(txtConfirm.getPassword()));
						prepState.setInt(3, 0);
						prepState.executeUpdate();	//Add the new account to the database.

						frmSignUp.dispose();
						startGame launchStartGame = new startGame();	//Launch the game.
					}
				}
				catch(SQLException ex) {
					ex.printStackTrace();
				}
				
				break;
				
			}
		}
		
		if(e.getSource() == btnBack) {	//Responsible for returning to the login window.
			frmSignUp.dispose();
			login launchLogin = new login();
		}

		if(e.getSource() == btnMusic) {	//Pause or play the background music when clicked.
			login.toggleMusic();
		}
	}
	
}

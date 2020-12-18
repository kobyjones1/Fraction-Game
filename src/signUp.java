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
	private static String strUser, strPass, strConfirm;
	
	public signUp(){
		frmSignUp = new JFrame("Fraction Game");	//Launches the login window.
		frmSignUp.setSize(320, 205);
		frmSignUp.setLocationRelativeTo(null);
		frmSignUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pnlSignUp = new JPanel();
		pnlSignUp.setLayout(null);
		frmSignUp.add(pnlSignUp);
		
		lblUser = new JLabel("Create Username: ");	//Username label.
		lblUser.setBounds(10, 20, 150, 25);
		pnlSignUp.add(lblUser);
		
		txtUser = new JTextField();	//Text field for taking the user's username.
		txtUser.setBounds(130, 20, 165, 25);
		pnlSignUp.add(txtUser);

		lblPass = new JLabel("Create Password: ");	//Password label.
		lblPass.setBounds(10, 50, 150, 25);
		pnlSignUp.add(lblPass);
		
		txtPass = new JPasswordField();	//Text field for taking the user's password.
		txtPass.setBounds(130, 50, 165, 25);
		pnlSignUp.add(txtPass);
		
		lblConfirm = new JLabel("Confirm Password: ");	//Password label.
		lblConfirm.setBounds(10, 80, 150, 25);
		pnlSignUp.add(lblConfirm);
		
		txtConfirm = new JPasswordField();	//Text field for taking the user's password.
		txtConfirm.setBounds(130, 80, 165, 25);
		pnlSignUp.add(txtConfirm);
		
		lblStatus = new JLabel("");	//Password label.
		lblStatus.setBounds(80, 110, 150, 25);
		pnlSignUp.add(lblStatus);
		
		btnCreate = new JButton("Create");	//Login button.
		btnCreate.setBounds(130, 135, 80, 25);
		btnCreate.addActionListener(this);
		pnlSignUp.add(btnCreate);
		
		btnBack = new JButton("Back");	//Login button.
		btnBack.setBounds(223, 135, 70, 25);
		btnBack.addActionListener(this);
		pnlSignUp.add(btnBack);
		
		btnMusic = new JButton("Music");	//Login button.
		btnMusic.setBounds(10, 135, 70, 25);
		btnMusic.addActionListener(this);
		pnlSignUp.add(btnMusic);
		
		frmSignUp.getRootPane().setDefaultButton(btnCreate);	//Allow the user to activate the "loginbutton" with the ENTER key.
		
		frmSignUp.setVisible(true);
		
		//String strSQL = "INSERT INTO Players ";
	}
	
	private static int inputCheck() {
		if(txtUser.getText().equals("")) {	//Login successful.
			return 1;
		}
		else if(txtUser.getText().equals("") != true && String.valueOf(txtPass.getPassword()).equals("")) {	//Wrong username.
			return 2;
		}
		else if(txtUser.getText().equals("") != true && String.valueOf(txtPass.getPassword()).equals("") != true && String.valueOf(txtConfirm.getPassword()).equals("")) {	//Username accepted, wrong password.
			return 3;
		}
		else if(txtUser.getText().equals("") != true && String.valueOf(txtPass.getPassword()).equals(String.valueOf(txtConfirm.getPassword())) != true) {	//Username accepted, empty password.
			return 3;
		}
		else {
			return 4;
		}
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCreate) {
			//strUser = txtUser.getText();
			//strPass = String.valueOf(txtPass.getPassword());
			//strConfirm = String.valueOf(txtConfirm.getPassword());

			PreparedStatement prepState = null;
			
			int switchState = inputCheck();
			
			switch(switchState) {
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
				ResultSet resSet = null;
				int ID = 0;
				prepState = null;
				resSet = null;
				Connection connSign = login.connDB;
				boolean usernameCheck = true;
				
				try {
					strSQL = "SELECT * FROM Players WHERE Username = ?";	//Obtain the score from the database so that it can be updated.
					prepState = connSign.prepareStatement(strSQL);
					prepState.setString(1, txtUser.getText());
					resSet = prepState.executeQuery();
					
					if(resSet.next()) {
						usernameCheck = false;
					}
					
					if(usernameCheck == false) {
						lblStatus.setText("Username unavailable.");
						break;
					}
					else {
						strSQL = "INSERT INTO Players (Username, Password, Score) VALUES (?,?,?)";	//Updates the player's score in the database.
						prepState = connSign.prepareStatement(strSQL);
						prepState.setString(1, txtUser.getText());
						prepState.setString(2, String.valueOf(txtConfirm.getPassword()));
						prepState.setInt(3, 0);
						prepState.executeUpdate();

						frmSignUp.dispose();
						startGame launchStartGame = new startGame();
					}
				}
				catch(SQLException ex) {
					ex.printStackTrace();
				}
				
				break;
				
			}
		}
		
		if(e.getSource() == btnBack) {
			frmSignUp.dispose();
			login launchLogin = new login();
		}

		if(e.getSource() == btnMusic) {
			login.toggleMusic();
		}
	}
	
}

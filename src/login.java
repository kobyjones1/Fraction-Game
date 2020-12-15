import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class login implements ActionListener{
	
	JFrame loginFrame;
	private JLabel userLabel, passwordLabel, statusLabel;
	private JTextField userText;
	private JPasswordField passwordText;
	private JButton loginButton;
	private static String username, password;
	
	public login() {
		loginFrame = new JFrame("Fraction Game");
		loginFrame.setSize(400, 150);
		loginFrame.setLocationRelativeTo(null);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(null);
		loginFrame.add(loginPanel);
		
		userLabel = new JLabel("Username: ");
		userLabel.setBounds(10, 20, 80, 25);
		loginPanel.add(userLabel);
		
		userText = new JTextField();
		userText.setBounds(100, 20, 165, 25);
		loginPanel.add(userText);
		
		loginButton = new JButton("Login");
		loginButton.setBounds(300, 20, 80, 25);
		loginButton.addActionListener(this);
		loginPanel.add(loginButton);
		
		passwordLabel = new JLabel("Password: ");
		passwordLabel.setBounds(10, 50, 80, 25);
		loginPanel.add(passwordLabel);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(100, 50, 165, 25);
		loginPanel.add(passwordText);
		
		statusLabel = new JLabel("");
		statusLabel.setBounds(110, 80, 200, 25);
		loginPanel.add(statusLabel);
		
		
		loginFrame.getRootPane().setDefaultButton(loginButton);
		
		loginFrame.setVisible(true);
	}
	
	private static int inputCheck() {
		String userTest = "Test";
		String userPass = "test";
		
		if(username.equals(userTest) && password.equals(userPass))	//Login successful.
			return 1;
		if(username.equals(userTest) != true && username.equals("") != true) 	//Wrong username.
			return 2;
		if(username.equals(userTest) && password.equals(userPass) != true) 	//Username accepted, wrong password.
			return 3;
		if(username.equals(userTest) && password.equals("")) 	//Username accepted, empty password.
			return 4;
		if(username.equals("") && password.equals(""))  //Both empty.
			return 4;
		if(username.equals(userTest) != true && password.equals(userPass) != true) 	//Both invalid.
			return 5;

		return 5;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loginButton) {
			username = userText.getText();
			password = passwordText.getText();
			
			int statusSwitch = inputCheck();
			
			switch(statusSwitch) {
			case 1: 
				loginFrame.dispose();
				startGame start = new startGame();
				break;
			case 2: 
				statusLabel.setText("Username invalid.");
				break;
			case 3: 
				statusLabel.setText("Password invalid.");
				break;
			case 4: 
				statusLabel.setText("Both fields require input.");
				break;
			case 5: 
				statusLabel.setText("Invalid username and password.");
				break;
			}	
		}
	}
	
}

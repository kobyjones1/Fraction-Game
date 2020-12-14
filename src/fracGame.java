//import java.util.Scanner;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class fracGame implements ActionListener{
	
	private static JLabel userLabel;
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;
	private static JButton loginButton;
	private static JButton exitButton;
	private static JLabel success;
	private static JLabel statusLabel;
	
	private static int numerator1, numerator2;
	private static int denominator1, denominator2;
	private static int operator;
	
	private static String username, password, firstName, lastName;
	
	//static Scanner userInput = new Scanner(System.in);
	
	public static void main(String[] args) {
		testLogin();
		//randGen();
		
	}
	
	private static void startGame() {
		JFrame mainDisplay = new JFrame("Fraction Game");
		mainDisplay.setSize(1280, 720);
		mainDisplay.setLocationRelativeTo(null);
		mainDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel(new GridLayout(1,1));
		JPanel equationPanel = new JPanel();
		
		JLabel currentEquation = new JLabel();
		equationPanel.add(currentEquation);
		
		//JPanel processPanel = new JPanel();
		//JPanel answerPanel = new JPanel();
		
		mainDisplay.setContentPane(mainPanel);
		mainDisplay.setVisible(true);
	}
	
	private static void login(){
		JFrame mainDisplay = new JFrame("Fraction Game");
		mainDisplay.setSize(350, 200);
		mainDisplay.setLocationRelativeTo(null);
		mainDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pnlButtons = new JPanel();
		//GridBagConstraints pnlButtonCons = new GridBagConstraints();
		JLabel enterUsername = new JLabel("Username: ");
		enterUsername.setBounds(10, 20, 80, 25);
		pnlButtons.add(enterUsername);
		
		JTextField enterUser = new JTextField("Enter username...");
		enterUser.setBounds(100, 20, 165, 25);
		pnlButtons.add(enterUser);
		
		JLabel enterPassword = new JLabel("Password: ");
		enterPassword.setBounds(10, 50, 80, 25);
		pnlButtons.add(enterPassword);
		
		JTextField enterPass = new JTextField("Enter password...");
		enterPass.setBounds(100, 20, 165, 25);
		pnlButtons.add(enterPass);
		
		//JTextField enterPass = new JTextField("Enter password...");
		
		JButton login = new JButton("Log In");
		login.setPreferredSize(new Dimension(40, 40));
		
		JButton exit = new JButton("Exit");
		exit.setPreferredSize(new Dimension(40, 40));
		
		//pnlButtons.add(enterUser);
		//pnlButtons.add(enterPass);
		//pnlButtons.add(login);
		//pnlButtons.add(exit);
		//login.addActionListener((ActionListener) login);
		mainDisplay.add(pnlButtons);
		mainDisplay.setVisible(true);
	}
	
	private static void testLogin() {
		JFrame frame = new JFrame();
		frame.setSize(400, 150);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		frame.add(panel);
		
		userLabel = new JLabel("Username: ");
		userLabel.setBounds(10, 20, 80, 25);
		panel.add(userLabel);
		
		userText = new JTextField();
		userText.setBounds(100, 20, 165, 25);
		panel.add(userText);
		
		loginButton = new JButton("Login");
		loginButton.setBounds(300, 20, 80, 25);
		loginButton.addActionListener(new fracGame());
		panel.add(loginButton);
		
		passwordLabel = new JLabel("Password: ");
		passwordLabel.setBounds(10, 50, 80, 25);
		panel.add(passwordLabel);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(100, 50, 165, 25);
		panel.add(passwordText);
		
		statusLabel = new JLabel("");
		statusLabel.setBounds(110, 80, 200, 25);
		panel.add(statusLabel);
		
		exitButton = new JButton("Exit");
		exitButton.setBounds(300, 50, 80, 25);
		exitButton.addActionListener(new fracGame());
		panel.add(exitButton);
		
		frame.setVisible(true);
	}
	
	private static int inputCheck() {
		if(username.equals("Test") && password.equals("testPass123")) {	//Login successful.
			return 1;
		}
		else if(username.equals("Test") != true && username.equals("") != true) {	//Wrong username.
			return 2;
		}
		else if(username.equals("Test") && password.equals("testPass123") != true) {	//Username accepted, wrong password.
			return 3;
		}
		else if(username.equals("Test") && password.equals("")) {	//Username accepted, empty password.
			return 4;
		}
		else if(username.equals("") && password.equals("")) { //Both empty.
			return 4;
		}
		else if(username.equals("Test") != true && password.equals("testPass123") != true) {	//Both invalid.
			return 5;
		}
		else {
			return 5;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loginButton) {
			username = userText.getText();
			password = passwordText.getText();
			int statusSwitch = inputCheck();
			
			switch(statusSwitch) {
			case 1: 
				statusLabel.setText("Login successful.");
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
	
	
	
	//private static int randGen() {
	//Random randomGen = new Random();
		
	//numerator1 = randomGen.nextInt(9) + 1;
	//numerator2 = randomGen.nextInt(9) + 1;
	//denominator1 = randomGen.nextInt(9) + 1;
	//denominator2 = randomGen.nextInt(9) + 1;
	//operator = randomGen.nextInt(1);
	//}
}

import java.util.Scanner;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class fracGame implements ActionListener{
	
	//Login
	private static JLabel userLabel, passwordLabel, statusLabel;
	private static JTextField userText;
	private static JPasswordField passwordText;
	private static JButton loginButton;
	
	//Start
	private static JButton hint;
	private static JTextField txtNum1, txtNum2, txtDen1, txtDen2, txtAnswerNum, txtAnswerDen;
	private static JLabel currentEquation,lblOp;
	
	private static int numerator1, numerator2, denominator1, denominator2, answerNum, answerDen, operator;
	
	private static String username, password;
	
	//static Scanner userInput = new Scanner(System.in);
	
	public static void main(String[] args) {
		//login();
		startGame();
		//randGen();
		
	}
	
	private static void startGame() {
		JFrame gameFrame = new JFrame("Fraction Game");
		gameFrame.setSize(400, 300);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		gameFrame.add(mainPanel);
		mainPanel.setBackground(Color.blue);

		currentEquation = new JLabel("1/2 + 1/2");
		currentEquation.setFont(new Font("Serif", Font.BOLD, 30));
		currentEquation.setForeground(Color.white);
		currentEquation.setBounds(135, 35, 300, 25);
		mainPanel.add(currentEquation);
		
		hint = new JButton();
		hint = new JButton("Step 1: Find The Common Denominator");
		hint.setBounds(50, 70, 280, 25);
		mainPanel.add(hint);

		txtNum1 = new JTextField();
		txtNum1.setBounds(30, 150, 80, 25);
		mainPanel.add(txtNum1);
		
		txtDen1 = new JTextField();
		txtDen1.setBounds(30, 200, 80, 25);
		mainPanel.add(txtDen1);
		
		lblOp = new JLabel("+");
		lblOp.setFont(new Font("Serif", Font.BOLD, 30));
		lblOp.setForeground(Color.white);
		lblOp.setBounds(114, 175, 150, 25);
		mainPanel.add(lblOp);
		
		txtNum2 = new JTextField();
		txtNum2.setBounds(135, 150, 80, 25);
		mainPanel.add(txtNum2);

		txtDen2 = new JTextField();
		txtDen2.setBounds(135, 200, 80, 25);
		mainPanel.add(txtDen2);
		
		JLabel equalsSign = new JLabel("=");
		equalsSign.setFont(new Font("Serif", Font.BOLD, 30));
		equalsSign.setForeground(Color.white);
		equalsSign.setBounds(234, 175, 150, 25);
		mainPanel.add(equalsSign);
		
		txtAnswerNum = new JTextField();
		txtAnswerNum.setBounds(270, 150, 80, 25);
		mainPanel.add(txtAnswerNum);

		txtAnswerDen = new JTextField();
		txtAnswerDen.setBounds(270, 200, 80, 25);
		mainPanel.add(txtAnswerDen);
		
		gameFrame.setContentPane(mainPanel);
		gameFrame.setVisible(true);
	}
	
	private static void login() {
		JFrame loginFrame = new JFrame();
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
		loginButton.addActionListener(new fracGame());
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
		
		loginFrame.pack();
		loginFrame.setVisible(true);
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

}

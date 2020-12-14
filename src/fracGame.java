import java.util.Scanner;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class fracGame{
	
	//Login
	private static JLabel userLabel, passwordLabel, statusLabel;
	private static JTextField userText;
	private static JPasswordField passwordText;
	private static JButton loginButton;
	private static String username, password;
	
	//Start
	private static JButton findNumerator;
	private static JTextField txtNum1, txtNum2, txtDen1, txtDen2, txtAnswerNum, txtAnswerDen;
	private static JLabel currentEquation,lblOp;
	private static String currEq, currOp;
	private static int questionNum1, questionDen1, questionNum2, questionDen2; 
	private static int numerator1, numerator2, denominator1, denominator2, answerNum, answerDen, operator;
	
	
	
	//static Scanner userInput = new Scanner(System.in);
	
	public static void main(String[] args) {
		login loginScreen = new login();
		//startGame startScreen = new startGame();
		//randGen();
		
	}
	
}

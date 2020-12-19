import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class startGame implements ActionListener{
	
	public static int intScore = 100;	//Start every game with 100 points.

	private static JFrame frmGame;
	public static JButton btnDenominator, btnSubmit, btnMusic;
	public static JTextField txtUserNum1, txtUserNum2, txtUserDen1, txtUserDen2, txtUserAnswerNum, txtUserAnswerDen;
	public static JLabel lblCurrEq,lblOp, lblScore, lblCurrQuesNum;
	
	private static String strCurrEq, strCurrOp, strScoreRepeat = null;
	private static int intQuesNum1, intQuesDen1, intQuesNum2, intQuesDen2, intOp, intQuesCount, intQuesAmount; 
	private static int intNum1, intNum2, intANum, intLCD;
	
	public startGame() {
		intQuesAmount = login.intQuesAmount;	//Sets the amount of iterations for the program.
		intQuesCount = 0;	//	Responsible for maintaining the question iterations.
		
		frmGame = new JFrame("Fraction Game");	//Launches the startGame window.
		frmGame.setSize(400, 300);
		frmGame.setLocationRelativeTo(null);
		frmGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pnlMain = new JPanel();
		pnlMain.setLayout(null);
		frmGame.add(pnlMain);
		pnlMain.setBackground(Color.blue);
		
		lblScore = new JLabel(String.valueOf(intScore));	//Displays the current score.
		lblScore.setFont(new Font("Arial", Font.BOLD, 20));
		lblScore.setForeground(Color.white);
		lblScore.setBounds(10, 10, 150, 25);
		pnlMain.add(lblScore);
		
		lblCurrQuesNum = new JLabel(String.valueOf(intQuesCount + 1) + ":");	//Displays the current question number.
		lblCurrQuesNum.setFont(new Font("Serif", Font.BOLD, 30));
		lblCurrQuesNum.setForeground(Color.white);
		lblCurrQuesNum.setBounds(60, 35, 300, 25);
		pnlMain.add(lblCurrQuesNum);
		
		lblCurrEq = new JLabel();	//Displays the current equation.
		lblCurrEq.setFont(new Font("Serif", Font.BOLD, 30));
		lblCurrEq.setForeground(Color.white);
		lblCurrEq.setBounds(110, 35, 300, 25);
		pnlMain.add(lblCurrEq);
		
		btnDenominator = new JButton("Find The Least Common Denominator");	//Button responsible for launching findDenominator.
		btnDenominator.addActionListener(this);
		btnDenominator.setBounds(50, 70, 280, 25);
		pnlMain.add(btnDenominator);
		
		txtUserNum1 = new JTextField();	//Text field for the first numerator.
		txtUserNum1.setBounds(30, 150, 80, 25);
		pnlMain.add(txtUserNum1);
		
		txtUserDen1 = new JTextField();	//Text field for the first denominator.
		txtUserDen1.setBounds(30, 200, 80, 25);
		pnlMain.add(txtUserDen1);
		
		lblOp = new JLabel();	//Displays the current operator.
		lblOp.setFont(new Font("Serif", Font.BOLD, 30));
		lblOp.setForeground(Color.white);
		lblOp.setBounds(114, 175, 150, 25);
		pnlMain.add(lblOp);
		
		txtUserNum2 = new JTextField();	//Text field for the second numerator.
		txtUserNum2.setBounds(135, 150, 80, 25);
		pnlMain.add(txtUserNum2);

		txtUserDen2 = new JTextField();	//Text field for the second denominator.
		txtUserDen2.setBounds(135, 200, 80, 25);
		pnlMain.add(txtUserDen2);
		
		JLabel lblEqualSign = new JLabel("=");	//Displays the equal sign.
		lblEqualSign.setFont(new Font("Serif", Font.BOLD, 30));
		lblEqualSign.setForeground(Color.white);
		lblEqualSign.setBounds(234, 175, 150, 25);
		pnlMain.add(lblEqualSign);
		
		txtUserAnswerNum = new JTextField();	//Text field for the numerator answer.
		txtUserAnswerNum.setBounds(270, 150, 80, 25);
		pnlMain.add(txtUserAnswerNum);

		txtUserAnswerDen = new JTextField();	//Text field for the denominator answer.
		txtUserAnswerDen.setBounds(270, 200, 80, 25);
		pnlMain.add(txtUserAnswerDen);

		btnSubmit = new JButton("Submit");	//Button responsible for checking if the user's answers are correct.
		btnSubmit.addActionListener(this);
		btnSubmit.setBounds(270, 233, 80, 25);
		btnSubmit.setVisible(false);
		pnlMain.add(btnSubmit);		
		
		btnMusic = new JButton("Music");	//Controls the music.
		btnMusic.setBounds(305, 10, 70, 25);
		btnMusic.addActionListener(this);
		pnlMain.add(btnMusic);
		
		reset();	//Clear old data, then generate a new question.
		
		frmGame.getRootPane().setDefaultButton(btnSubmit);	//Allows the user to activate the "btnSubmit" button with the ENTER key.
		
		frmGame.setContentPane(pnlMain);
		frmGame.setVisible(true);
	}
	
	public static void addScore(int intIncrementVal) {	//Adds to the current score.
		intScore += intIncrementVal;
	}
	
	public static void subScore(int intIncrementVal) {	//Subtracts from the current score.
		int intSubScore = intScore - intIncrementVal;
		
		if(intSubScore >= 0)	//Prevents the score from becoming a negative value.
			intScore -= intIncrementVal;
	}
	
	private static int randGen(int intLimit) {	//Generate the numbers for the problem.
		Random randomNum = new Random();
		return randomNum.nextInt(intLimit) + 1;	//Adds 1 to the result to avoid obtaining 0;
	}

	private static void newQuestion() {	//Generates the problem for the user to solve.
		int intMaxNum = 9;	//Sets default max number.
		
		intQuesNum1 = randGen(intMaxNum);	//Generates a value between 1 and 10;
		intQuesDen1 = randGen(intMaxNum);
		intQuesNum2 = randGen(intMaxNum);
		intQuesDen2 = randGen(intMaxNum);
		
		intOp = randGen(2);	//Randomly determine whether the user will add or subtract the fractions.

		if(intOp == 1)	//Logic for switching the operator.
			strCurrOp = "+";
		else
			strCurrOp = "-";
		
		strCurrEq = intQuesNum1 + "/" + intQuesDen1 + " " + strCurrOp + " " + intQuesNum2 + "/" + intQuesDen2 + " = ?";	//Builds the equation.
	}
	
	private static void answerCheck(int intCurrQues) {	//Checks if the user's input is correct, incorrect, or non existent.
		//Correct answer: 3 = +75 (60 + 15 bonus points), 2 = +40, 1 = +20. Incorrect Answer: 3 = -50, 2 = -30, 1 = -15.
		int intCorrect3 = 75, intCorrect2 = 40, intCorrect1 = 20, intIncorrect3 = 50, intIncorrect2 = 30, intIncorrect1 = 15;

		switch(intCurrQues) {
		case 1: 
			String strGameNum1 = String.valueOf(intNum1);	//Convert the game's correct answer to a string to compare with the user's answer.
		
			if(txtUserNum1.getText().equals(strGameNum1)) {
				txtUserNum1.setBackground(Color.green);
				txtUserNum1.setEditable(false);
				txtUserNum2.setEditable(true);

				addScore(intCorrect1);

				SwingUtilities.invokeLater(new Runnable() {	//Places the focus (blinking cursor) on the txtUserNum2. 
					public void run() { 
						txtUserNum2.requestFocus(); 
					} 
				}); 
			}
			else if(txtUserNum1.getText().equals("")) {
				txtUserNum1.setBackground(Color.white);
			}
			else {
				if(txtUserNum1.getText().equals(strScoreRepeat)) {
					txtUserNum1.setBackground(Color.red);
				}
				else {
					txtUserNum1.setBackground(Color.red);
					strScoreRepeat = txtUserNum1.getText();
					subScore(intIncorrect1);
				}
			}
			
			break;
		case 2:
			String strGameNum2 = String.valueOf(intNum2);	//Convert the game's correct answer to a string to compare with the user's answer.
			
			if(txtUserNum2.getText().equals(strGameNum2)) {
				txtUserNum2.setBackground(Color.green);
				txtUserNum2.setEditable(false);
				txtUserAnswerNum.setEditable(true);
				
				addScore(intCorrect2);
				
				SwingUtilities.invokeLater(new Runnable() {	//Places the focus (blinking cursor) on the txtUserAnswerNum. 
					public void run() { 
						txtUserAnswerNum.requestFocus(); 
					} 
		        }); 
			}
			else if(txtUserNum2.getText().equals("")) {
				txtUserNum2.setBackground(Color.white);
			}
			else {
				if(txtUserNum2.getText().equals(strScoreRepeat)) {
					txtUserNum2.setBackground(Color.red);
				}
				else {
					txtUserNum2.setBackground(Color.red);
					strScoreRepeat = txtUserNum2.getText();
					subScore(intIncorrect2);
				}
			}
			
			break;
		case 3:
			String strGameANum = String.valueOf(intANum);	//Convert the game's correct answer to a string to compare with the user's answer.
			
			if(txtUserAnswerNum.getText().equals(strGameANum)) {
				
				addScore(intCorrect3);
				
				if(intQuesCount < intQuesAmount - 1) {	//Loops until intQuesCount reaches the amount of questions for the game.
					intQuesCount++;
					reset();
					
				}
				else {	//If the last question has been answered, the gameFrame will close and the gameOver screen will launch.
					frmGame.dispose();
					gameOver launchGameOver = new gameOver();	//Launch the gameOver window.
				}
			}
			else if(txtUserAnswerNum.getText().equals("")) {
				txtUserAnswerNum.setBackground(Color.white);
			}
			else {
				if(txtUserAnswerNum.getText().equals(strScoreRepeat)) {
					txtUserAnswerNum.setBackground(Color.red);
				}
				else {
					txtUserAnswerNum.setBackground(Color.red);
					strScoreRepeat = txtUserAnswerNum.getText();
					subScore(intIncorrect3);
				}
			}
			
			break;
		}
	}
	
	private static void reset() {	//Clears the question and the user's answers, then displays a new question.
		newQuestion();	//Generate a new question.
		
		lblCurrEq.setText(strCurrEq);	//Display the new question.
		
		btnDenominator.setEnabled(true);	//Makes btnDenominator available for the new question.
		
		lblScore.setText(String.valueOf(intScore));	//Update the score.
		
		lblCurrQuesNum.setText(String.valueOf(intQuesCount + 1) + ":");	//Update the question number.
		
		txtUserNum1.setText("");
		txtUserNum1.setEditable(false);	//Text fields not editable to make btnDenominator the main focus.
		txtUserNum1.setBackground(Color.lightGray);
		
		txtUserNum2.setText("");
		txtUserNum2.setEditable(false);
		txtUserNum2.setBackground(Color.lightGray);
		
		txtUserAnswerNum.setText("");
		txtUserAnswerNum.setEditable(false);
		txtUserAnswerNum.setBackground(Color.lightGray);
		
		txtUserDen1.setText("");
		txtUserDen1.setEditable(false);
		txtUserDen1.setBackground(Color.lightGray);
		
		txtUserDen2.setText("");
		txtUserDen2.setEditable(false);
		txtUserDen2.setBackground(Color.lightGray);
		
		txtUserAnswerDen.setText("");
		txtUserAnswerDen.setEditable(false);
		txtUserAnswerDen.setBackground(Color.lightGray);
		
		if(intOp == 1) {	//Switch the operator if necessary.
			lblOp.setText("+");
		}
		else {
			lblOp.setText("-");
		}
		
		btnSubmit.setVisible(false);	//Hide the submit button.
	}
	
	private static int findNumerator(int intNum1, int intNum2) {	//Finds the numerator based on the current operator.
		if(intOp == 1)	//Switches based on the current operator.
			return intNum1 + intNum2;
		else
			return intNum1 - intNum2;
	}
	
	public static void checkLCD() {	//Logic for multiplying the fractions based on the LCD.
		intLCD = findDenominator.findLCD(intQuesDen1, intQuesDen2);
		
		if(intQuesDen1 == intLCD && intQuesDen2 == intLCD) {	//Both denominators are the same.
			intNum1 = intQuesNum1;
			intNum2 = intQuesNum2;
		}
		
		if(intQuesDen1 == intLCD && intQuesDen2 != intLCD) {	//Den1 stays the same, den2 increases.
			intNum1 = intQuesNum1;
			intNum2 = intQuesNum2 * (intLCD/intQuesDen2);
		}
		
		if(intQuesDen1 != intLCD && intQuesDen2 == intLCD) {	//Den2 stays the same, den1 increases.
			intNum1 = intQuesNum1 * (intLCD/intQuesDen1);
			intNum2 = intQuesNum2;
		}
		
		if(intQuesDen1 != intLCD && intQuesDen2 != intLCD) {	//Both increase.
			intNum1 = intQuesNum1 * (intLCD/intQuesDen1);
			intNum2 = intQuesNum2 * (intLCD/intQuesDen2);
		}
		
		intANum = findNumerator(intNum1, intNum2);	//Finds the correct numerator answer based on the LCD.
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnDenominator) {	//Responsible for activated the findDenominator window
			btnDenominator.setEnabled(false);	//Disabled once the findDenominator window opens, reactivates if the game resets.
			findDenominator launchFindDen = new findDenominator(intQuesDen1, intQuesDen2);	//Launches the findDenominator window.
		}
		
		if(e.getSource() == btnMusic) {	//Pause or play the background music when clicked.
			login.toggleMusic();
		}
		
		if(e.getSource() == btnSubmit) {	//Checks if the user's input is correct.
			if(txtUserNum1.isEditable() == true)
				answerCheck(1);	//Checks if the user's answer is correct.
			if(txtUserNum2.isEditable() == true)
				answerCheck(2);	
			if(txtUserAnswerNum.isEditable() == true)
				answerCheck(3);
			
			lblScore.setText(String.valueOf(intScore));	//Updates the score.
		}
	}

}

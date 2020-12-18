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

public class startGame implements ActionListener{
	
	public static int intScore = 100;	//Start every game with 100 points.

	private static JFrame frmGame;
	public static JButton btnDenominator, btnSubmit, btnMusic;
	public static JTextField txtUserNum1, txtUserNum2, txtUserDen1, txtUserDen2, txtUserAnswerNum, txtUserAnswerDen;
	public static JLabel lblCurrEq,lblOp, lblStatus, lblScore, lblCurrQuesNum;
	private static String strCurrEq, strCurrOp;
	private static int intQuesNum1, intQuesDen1, intQuesNum2, intQuesDen2, intOp, intQuesCount, intQuesAmount; 
	private static int intNum1, intNum2, intANum, intLCD, intScoreRepeat = 0;
	
	public startGame() {
		intQuesAmount = login.intQuesAmount;	//Sets the amount of iterations for the program.
		intQuesCount = 0;	//	Responsible for maintaining the amountOfQuestions iterations.
		
		frmGame = new JFrame("Fraction Game");	//Launches the startGame window.
		frmGame.setSize(400, 300);
		frmGame.setLocationRelativeTo(null);
		frmGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pnlMain = new JPanel();
		pnlMain.setLayout(null);
		frmGame.add(pnlMain);
		pnlMain.setBackground(Color.blue);
		
		lblScore = new JLabel(String.valueOf(intScore));	//Tells the user if they didn't enter any data.
		lblScore.setFont(new Font("Arial", Font.BOLD, 20));
		lblScore.setForeground(Color.white);
		lblScore.setBounds(10, 10, 150, 25);
		pnlMain.add(lblScore);
		
		lblCurrQuesNum = new JLabel(String.valueOf(intQuesCount + 1) + ":");	//Displays the current equation.
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
		
		lblStatus = new JLabel();	//Tells the user if they didn't enter any data.
		lblStatus.setFont(new Font("Serif", Font.BOLD, 15));
		lblStatus.setForeground(Color.white);
		lblStatus.setBounds(30, 233, 400, 25);
		pnlMain.add(lblStatus);
		
		btnSubmit = new JButton("Submit");	//Button responsible for checking if the user's answers are correct.
		btnSubmit.addActionListener(this);
		btnSubmit.setBounds(270, 233, 80, 25);
		btnSubmit.setVisible(false);
		pnlMain.add(btnSubmit);		
		
		btnMusic = new JButton("Music");	//Control the music.
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
		return randomNum.nextInt(intLimit) + 1;	//+1 to avoid getting 0;
	}

	private static void newQuestion() {	//Generates the problem for the user to solve.
		intQuesNum1 = randGen(9);	//Generates a value between 1 and 10;
		intQuesDen1 = randGen(9);
		intQuesNum2 = randGen(9);
		intQuesDen2 = randGen(9);
		
		intOp = randGen(2);	//Randomly determine whether the user will add or subtract the fractions.

		if(intOp == 1)	
			strCurrOp = "+";
		else
			strCurrOp = "-";
		
		strCurrEq = intQuesNum1 + "/" + intQuesDen1 + " " + strCurrOp + " " + intQuesNum2 + "/" + intQuesDen2 + " = ?";	//Builds the equation.
	}
	
	private static int inputCheck() {	//Checks if the user's input is correct, incorrect, or non existent.
		String strGameNum1 = String.valueOf(intNum1);	//Convert the game's correct answer to a string to compare with the user's answer.
		String strGameNum2 = String.valueOf(intNum2);
		String strGameANum = String.valueOf(intANum);
		
		String strNum1 = txtUserNum1.getText();	//Convert the user's answer to a string.
		String strNum2 = txtUserNum2.getText();
		String strANum = txtUserAnswerNum.getText();
		
		boolean blNum1Correct = false;	//Finds out if any text fields are empty and if they contain the correct answer.
		boolean blNum2Correct = false;
		boolean blANumCorrect = false;
		boolean blEmptyNum1 = false;
		boolean blEmptyNum2 = false;
		boolean blEmptyANum = false;
		
		if(strNum1.equals(strGameNum1))	//Checks if the answer is correct.
			blNum1Correct = true;
		if(strNum1.equals(""))	//Checks if the user left the text field empty.
			blEmptyNum1 = true;	
		if(strNum2.equals(strGameNum2))
			blNum2Correct = true;		
		if(strNum2.equals(""))
			blEmptyNum2 = true;		
		if(strANum.equals(strGameANum))
			blANumCorrect = true;		
		if(strANum.equals(""))
			blEmptyANum = true;
		
		if(blNum1Correct == true && blNum2Correct == true && blANumCorrect == true)	//All correct.
			return 1;	
		if(blEmptyNum1 == true && blEmptyNum2 == true && blEmptyANum == true)	//All empty.
			return 2;	
		if(blNum1Correct == true && blEmptyNum2 == true && blEmptyANum == true)	//Num1 correct.	(EMPTY INPUT CHECK)
			return 3;		
		if(blNum2Correct == true && blEmptyNum1 == true && blEmptyANum == true)	//Num2 correct. (EMPTY INPUT CHECK)
			return 4;	
		if(blANumCorrect == true && blEmptyNum1 == true && blEmptyNum2 == true)	//aNum correct. (EMPTY INPUT CHECK)
			return 5;		
		if(blNum1Correct != true && blEmptyNum2 == true && blEmptyANum == true)	//Num1 incorrect. (EMPTY INPUT CHECK)
			return 11;		
		if(blNum2Correct != true && blEmptyNum1 == true && blEmptyANum == true)	//Num2 incorrect. (EMPTY INPUT CHECK)
			return 13;		
		if(blANumCorrect != true && blEmptyNum1 == true && blEmptyNum2 == true)	//aNum incorrect. (EMPTY INPUT CHECK)
			return 14;		
		if(blNum1Correct == true && blNum2Correct == true && blEmptyANum == true)	//Num1, Num2 correct. (EMPTY INPUT CHECK)
			return 6;		
		if(blNum1Correct == true && blEmptyNum2 == true && blANumCorrect == true)	//Num1, aNum correct. (EMPTY INPUT CHECK)
			return 8;	
		if(blNum2Correct == true && blEmptyNum1 == true && blANumCorrect == true)	//Num2, aNum correct. (EMPTY INPUT CHECK)
			return 7;
		if(blNum1Correct != true && blNum2Correct != true && blEmptyANum == true)	//Num1, Num2 incorrect. (EMPTY INPUT CHECK)
			return 10;		
		if(blNum1Correct != true && blEmptyNum2 == true && blANumCorrect != true)	//Num1, aNum incorrect. (EMPTY INPUT CHECK)
			return 15;	
		if(blNum2Correct != true && blEmptyNum1 == true && blANumCorrect != true)	//Num2, aNum incorrect. (EMPTY INPUT CHECK)
			return 12;
		
		if(blNum1Correct != true && blNum2Correct != true && blANumCorrect != true)	//All wrong.
			return 9;
		
		if(blNum1Correct == true && blNum2Correct != true && blANumCorrect != true)	//Num1 correct.	(WRONG ANSWER CHECK)
			return 16;
		if(blNum1Correct != true && blNum2Correct == true && blANumCorrect != true)	//Num2 correct. (WRONG ANSWER CHECK)
			return 17;
		if(blNum1Correct != true && blNum2Correct != true && blANumCorrect == true)	//aNum correct. (WRONG ANSWER CHECK)
			return 18;
		
		if(blNum1Correct == true && blNum2Correct == true && blANumCorrect != true)	//Num1 and Num2 correct. (WRONG ANSWER CHECK)
			return 19;
		if(blNum1Correct != true && blNum2Correct == true && blANumCorrect == true)	//Num2 and aNum correct. (WRONG ANSWER CHECK)
			return 20;
		if(blNum1Correct == true && blNum2Correct != true && blANumCorrect == true)	//Num1 and aNum correct. (WRONG ANSWER CHECK)
			return 21;

		return 2;	//Returns switch case 2 if something isn't working properly with this function.
	}
	
	private static void checkAnswerSwitch(int intSwitchState) {	//Switch state that determines how to respond to the user's input.
		int intCorrect3 = 75, intCorrect2 = 40, intCorrect1 = 20, intIncorrect3 = 50, intIncorrect2 = 30, intIncorrect1 = 15;
		//Correct answer: 3 = +75 (60 + 15 bonus points), 2 = +40, 1 = +20. Incorrect Answer: 3 = -50, 2 = -30, 1 = -15.
		
		boolean blScoreRepeat = false;
		
		if(intScoreRepeat == intSwitchState)	//Prevents the player from spamming the submit button.
			blScoreRepeat = true;
		else
			intScoreRepeat = intSwitchState;
		
		switch(intSwitchState) {	//Alters the data on the game window and increments the score based on the user's input.
		case 1:	//All correct.	
			
			addScore(intCorrect3);

			if(intQuesCount < intQuesAmount - 1) {	//Loops until intQuesCount reaches the amount of questions for the game.
				intQuesCount++;
				reset();
				break;
			}
			else {	//If the last question has been answered, the gameFrame will close and the gameOver screen will launch.
				frmGame.dispose();
				gameOver launchGameOver = new gameOver();	//Launch the gameOver window.
				break;
			}
		case 2:	//All fields are empty.	(EMPTY INPUT CHECK) 
			txtUserNum1.setBackground(Color.white);
			txtUserNum2.setBackground(Color.white);
			txtUserAnswerNum.setBackground(Color.white);
			lblStatus.setText("No input detected");
			break;
		case 3:	//Num1 only; correct.	(EMPTY INPUT CHECK)
			txtUserNum1.setBackground(Color.green);
			txtUserNum2.setBackground(Color.white);
			txtUserAnswerNum.setBackground(Color.white);
			
			txtUserNum1.setEditable(false);
			
			if(blScoreRepeat == false)
				addScore(intCorrect1);
			break;
		case 4:	//Num2 only; correct.	(EMPTY INPUT CHECK)
			txtUserNum1.setBackground(Color.white);
			txtUserNum2.setBackground(Color.green);
			txtUserAnswerNum.setBackground(Color.white);
			
			txtUserNum2.setEditable(false);
			
			if(blScoreRepeat == false)
				addScore(intCorrect1);
			break;
		case 5:	//aNum only; correct.	(EMPTY INPUT CHECK)
			txtUserNum1.setBackground(Color.white);
			txtUserNum2.setBackground(Color.white);
			txtUserAnswerNum.setBackground(Color.green);
		
			txtUserAnswerNum.setEditable(false);
			
			if(blScoreRepeat == false)
				addScore(intCorrect1);
			break;
		case 6:	//Num1 and Num2; correct.	(EMPTY INPUT CHECK)
			txtUserNum1.setBackground(Color.green);
			txtUserNum2.setBackground(Color.green);
			txtUserAnswerNum.setBackground(Color.white);
			
			txtUserNum1.setEditable(false);
			txtUserNum2.setEditable(false);
			
			if(blScoreRepeat == false) 
					addScore(intCorrect2);
			break;
		case 7:	//Num2 and aNum; correct.	(EMPTY INPUT CHECK)
			txtUserNum1.setBackground(Color.white);
			txtUserNum2.setBackground(Color.green);
			txtUserAnswerNum.setBackground(Color.green);

			txtUserNum2.setEditable(false);
			txtUserAnswerNum.setEditable(false);
			
			if(blScoreRepeat == false)
				addScore(intCorrect2);
			break;
		case 8:	//Num1 and aNum; correct.	(EMPTY INPUT CHECK)
			txtUserNum1.setBackground(Color.green);
			txtUserNum2.setBackground(Color.white);
			txtUserAnswerNum.setBackground(Color.green);

			txtUserNum1.setEditable(false);
			txtUserAnswerNum.setEditable(false);
			
			if(blScoreRepeat == false) 
				addScore(intCorrect2);
			break;
		case 9:	//All are wrong. (WRONG ANSWER CHECK)
			txtUserNum1.setBackground(Color.red);
			txtUserNum2.setBackground(Color.red);
			txtUserAnswerNum.setBackground(Color.red);
			
			if(blScoreRepeat == false) 
				subScore(intIncorrect3);
			break;
		case 10:	//Num1 and Num2; wrong.	(EMPTY INPUT CHECK)
			txtUserNum1.setBackground(Color.red);
			txtUserNum2.setBackground(Color.red);
			txtUserAnswerNum.setBackground(Color.white);

			if(blScoreRepeat == false)
				subScore(intIncorrect2);
			break;
		case 11:	//Num1; wrong.	(EMPTY INPUT CHECK)
			txtUserNum1.setBackground(Color.red);
			txtUserNum2.setBackground(Color.white);
			txtUserAnswerNum.setBackground(Color.white);
			
			if(blScoreRepeat == false)
				subScore(intIncorrect1);
			break;
		case 12:	//Num2 and aNum; wrong.	(EMPTY INPUT CHECK)
			txtUserNum1.setBackground(Color.white);
			txtUserNum2.setBackground(Color.red);
			txtUserAnswerNum.setBackground(Color.red);
			
			if(blScoreRepeat == false) 
				subScore(intIncorrect2);
			break;
		case 13:	//Num2; wrong.	(EMPTY INPUT CHECK)
			txtUserNum1.setBackground(Color.white);
			txtUserNum2.setBackground(Color.red);
			txtUserAnswerNum.setBackground(Color.white);
			
			if(blScoreRepeat == false)
				subScore(intIncorrect1);
			break;
		case 14:	//aNum; wrong.	(EMPTY INPUT CHECK)
			txtUserNum1.setBackground(Color.white);
			txtUserNum2.setBackground(Color.white);
			txtUserAnswerNum.setBackground(Color.red);
			
			if(blScoreRepeat == false)
				subScore(intIncorrect1);
			break;
		case 15:	//Num1 and aNum; incorrect.	(EMPTY INPUT CHECK)
			txtUserNum1.setBackground(Color.red);
			txtUserNum2.setBackground(Color.white);
			txtUserAnswerNum.setBackground(Color.red);

			if(blScoreRepeat == false) 
				subScore(intIncorrect2);
			break;
		case 16:	//Num1; correct.  (WRONG ANSWER CHECK)
			txtUserNum1.setBackground(Color.green);
			txtUserNum2.setBackground(Color.red);
			txtUserAnswerNum.setBackground(Color.red);

			txtUserNum1.setEditable(false);
			
			if(blScoreRepeat == false)
				subScore(intIncorrect2 - intCorrect1);
			break;
		case 17:	//Num2; correct. (WRONG ANSWER CHECK)
			txtUserNum1.setBackground(Color.red);
			txtUserNum2.setBackground(Color.green);
			txtUserAnswerNum.setBackground(Color.red);

			txtUserNum2.setEditable(false);
			
			if(blScoreRepeat == false) 
				subScore(intIncorrect2 - intCorrect1);
			break;
		case 18:	//aNum; correct. (WRONG ANSWER CHECK)
			txtUserNum1.setBackground(Color.red);
			txtUserNum2.setBackground(Color.red);
			txtUserAnswerNum.setBackground(Color.green);

			if(blScoreRepeat == false) 
				subScore(intIncorrect2 - intCorrect1);
			break;
		case 19:	//Num1 and Num2; correct. (WRONG ANSWER CHECK)
			txtUserNum1.setBackground(Color.green);
			txtUserNum2.setBackground(Color.green);
			txtUserAnswerNum.setBackground(Color.red);

			txtUserNum1.setEditable(false);
			txtUserNum2.setEditable(false);
			
			if(blScoreRepeat == false) 
				addScore(intCorrect2 - intIncorrect1);
			break;
		case 20:	//Num2 and aNum; correct. (WRONG ANSWER CHECK)
			txtUserNum1.setBackground(Color.red);
			txtUserNum2.setBackground(Color.green);
			txtUserAnswerNum.setBackground(Color.green);

			txtUserNum2.setEditable(false);
			txtUserAnswerNum.setEditable(false);
			
			if(blScoreRepeat == false) 
				addScore(intCorrect2 - intIncorrect1);
			break;
		case 21:	//Num1 and aNum; correct. (WRONG ANSWER CHECK)
			txtUserNum1.setBackground(Color.green);
			txtUserNum2.setBackground(Color.red);
			txtUserAnswerNum.setBackground(Color.green);
			
			txtUserNum1.setEditable(false);
			txtUserAnswerNum.setEditable(false);
			
			if(blScoreRepeat == false) 
				addScore(intCorrect2 - intIncorrect1);
			break;
		}
	}
	
	private static void reset() {	//Clear the question and the user's answers, then display a new question.
		newQuestion();	//Generate a new question.
		
		lblCurrEq.setText(strCurrEq);	//Display the new question.
		
		btnDenominator.setEnabled(true);	//Makes btnDenominator available for the new question.
		
		lblScore.setText(String.valueOf(intScore));	//Display the score.
		
		lblCurrQuesNum.setText(String.valueOf(intQuesCount + 1) + ":");
		
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
		
		lblStatus.setText("");	//Empty the status label.
		
		btnSubmit.setVisible(false);	//Hide the submit button.
	}
	
	private static int findNumerator(int intNum1, int intNum2) {	//Finds the numerator based on the current operator.
		if(intOp == 1)
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
		if(e.getSource() == btnDenominator) {
			btnDenominator.setEnabled(false);	//Not enabled once the findDenominator window opens, reactivates if the game resets.
			findDenominator launchFindDen = new findDenominator(intQuesDen1, intQuesDen2);	//Launches the findDenominator window.
		}
		
		if(e.getSource() == btnMusic) {
			login.toggleMusic();
		}
		
		if(e.getSource() == btnSubmit) {	//Checks if the user's input is correct.
			lblStatus.setText("");	//Empty the status label.
			
			checkAnswerSwitch(inputCheck());
			
			lblScore.setText(String.valueOf(intScore));	//Updates the score.
		}
		
	}

}

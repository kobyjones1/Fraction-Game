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
	
	public static int intScore; //Correct answer: 3 = +75 (60 + 15 bonus points), 2 = +40, 1 = +20; Incorrect Answer: 3 = -50, 2 = -30, 1 = -15.

	private static JFrame frmGame;
	public static JButton btnDenominator, btnSubmit;
	public static JTextField txtNum1, txtNum2, txtDen1, txtDen2, txtAnswerNum, txtAnswerDen;
	public static JLabel lblCurrEq,lblOp, lblStatus, lblScore;
	private static String strCurrEq, strCurrOp;
	private static int intQuesNum1, intQuesDen1, intQuesNum2, intQuesDen2, intLCD, intScoreRepeat = 0; 
	private static int intUserNum1, intUserNum2, intUserANum, intOp, intQuesCount, intQuesAmount;
	
	public startGame() {
		intQuesAmount = 1;	//Sets the amount of iterations for the program.
		intQuesCount = 0;	//	Responsible for maintaining the amountOfQuestions iterations.
		intScore = 100;	//Initialize the score variable.
		
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
		
		lblCurrEq = new JLabel();	//Displays the current equation.
		lblCurrEq.setFont(new Font("Serif", Font.BOLD, 30));
		lblCurrEq.setForeground(Color.white);
		lblCurrEq.setBounds(110, 35, 300, 25);
		pnlMain.add(lblCurrEq);
		
		btnDenominator = new JButton("Find The Least Common Denominator");	//Button responsible for launching findDenominator.
		btnDenominator.addActionListener(this);
		btnDenominator.setBounds(50, 70, 280, 25);
		pnlMain.add(btnDenominator);
		
		txtNum1 = new JTextField();	//Text field for the first numerator.
		txtNum1.setBounds(30, 150, 80, 25);
		pnlMain.add(txtNum1);
		
		txtDen1 = new JTextField();	//Text field for the first denominator.
		txtDen1.setBounds(30, 200, 80, 25);
		pnlMain.add(txtDen1);
		
		lblOp = new JLabel();	//Displays the current operator.
		lblOp.setFont(new Font("Serif", Font.BOLD, 30));
		lblOp.setForeground(Color.white);
		lblOp.setBounds(114, 175, 150, 25);
		pnlMain.add(lblOp);
		
		txtNum2 = new JTextField();	//Text field for the second numerator.
		txtNum2.setBounds(135, 150, 80, 25);
		pnlMain.add(txtNum2);

		txtDen2 = new JTextField();	//Text field for the second denominator.
		txtDen2.setBounds(135, 200, 80, 25);
		pnlMain.add(txtDen2);
		
		JLabel lblEqualSign = new JLabel("=");	//Displays the equal sign.
		lblEqualSign.setFont(new Font("Serif", Font.BOLD, 30));
		lblEqualSign.setForeground(Color.white);
		lblEqualSign.setBounds(234, 175, 150, 25);
		pnlMain.add(lblEqualSign);
		
		txtAnswerNum = new JTextField();	//Text field for the numerator answer.
		txtAnswerNum.setBounds(270, 150, 80, 25);
		pnlMain.add(txtAnswerNum);

		txtAnswerDen = new JTextField();	//Text field for the denominator answer.
		txtAnswerDen.setBounds(270, 200, 80, 25);
		pnlMain.add(txtAnswerDen);
		
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
		
		reset();	//Clear old data, then generate a new question.
		
		frmGame.getRootPane().setDefaultButton(btnSubmit);	//Allows the user to activate the "btnSubmit" button with the ENTER key.
		
		frmGame.setContentPane(pnlMain);
		frmGame.setVisible(true);
	}
	
	public static void addScore(int intIncrementVal) {
		intScore += intIncrementVal;
	}
	
	public static void subScore(int intIncrementVal) {
		int intSubScore = intScore - intIncrementVal;
		
		if(intSubScore >= 0)
			intScore -= intIncrementVal;
	}
	
	private static int randGen(int intLimit) {	//Generate the numbers for the problem.
		Random randomNum = new Random();
		return randomNum.nextInt(intLimit) + 1;	//+1 to avoid getting 0;
	}

	private static void newQuestion() {	//Generates the problem for the user to solve.
		intQuesNum1 = randGen(9);
		intQuesDen1 = randGen(9);
		intQuesNum2 = randGen(9);
		intQuesDen2 = randGen(9);
		intOp = randGen(2);

		if(intOp == 1)	//Randomly determine whether the user will add or subtract the fractions.
			strCurrOp = "+";
		else
			strCurrOp = "-";
		
		strCurrEq = intQuesNum1 + "/" + intQuesDen1 + " " + strCurrOp + " " + intQuesNum2 + "/" + intQuesDen2 + " = ?";	//Builds the equation string.
	}
	
	private static int inputCheck() {	//Checks if the user's input is correct, incorrect, or non existent.
		String strGameNum1 = String.valueOf(intUserNum1);	//Convert the game's correct answer to a string to compare with the user's answer.
		String strGameNum2 = String.valueOf(intUserNum2);
		String strGameANum = String.valueOf(intUserANum);
		
		String strNum1 = txtNum1.getText();	//Convert the user's answer to a string.
		String strNum2 = txtNum2.getText();
		String strANum = txtAnswerNum.getText();
		
		boolean blNum1Correct = false;	//Finds out if any text fields are empty and if they contain the correct answer.
		boolean blNum2Correct = false;
		boolean blANumCorrect = false;
		boolean blEmptyNum1 = false;
		boolean blEmptyNum2 = false;
		boolean blEmptyANum = false;
		
		if(strNum1.equals(strGameNum1))	
			blNum1Correct = true;
		if(strNum1.equals(""))
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
		if(blNum2Correct == true && blEmptyNum1 == true && blEmptyANum == true)	//Num2 correct.
			return 4;	
		if(blANumCorrect == true && blEmptyNum1 == true && blEmptyNum2 == true)	//aNum correct.
			return 5;		
		if(blNum1Correct != true && blEmptyNum2 == true && blEmptyANum == true)	//Num1 incorrect.
			return 11;		
		if(blNum2Correct != true && blEmptyNum1 == true && blEmptyANum == true)	//Num2 incorrect.
			return 13;		
		if(blANumCorrect != true && blEmptyNum1 == true && blEmptyNum2 == true)	//aNum incorrect.
			return 14;		
		if(blNum1Correct == true && blNum2Correct == true && blEmptyANum == true)	//Num1, Num2 correct. (EMPTY INPUT CHECK)
			return 6;		
		if(blNum1Correct == true && blEmptyNum2 == true && blANumCorrect == true)	//Num1, aNum correct.
			return 8;	
		if(blNum2Correct == true && blEmptyNum1 == true && blANumCorrect == true)	//Num2, aNum correct.
			return 7;
		if(blNum1Correct != true && blNum2Correct != true && blEmptyANum == true)	//Num1, Num2 incorrect.
			return 10;		
		if(blNum1Correct != true && blEmptyNum2 == true && blANumCorrect != true)	//Num1, aNum incorrect.
			return 15;	
		if(blNum2Correct != true && blEmptyNum1 == true && blANumCorrect != true)	//Num2, aNum incorrect.
			return 12;
		
		if(blNum1Correct != true && blNum2Correct != true && blANumCorrect != true)	//All wrong.
			return 9;
		
		if(blNum1Correct == true && blNum2Correct != true && blANumCorrect != true)	//Num1 correct.	(WRONG ANSWER CHECK)
			return 16;
		if(blNum1Correct != true && blNum2Correct == true && blANumCorrect != true)	//Num2 correct.
			return 17;
		if(blNum1Correct != true && blNum2Correct != true && blANumCorrect == true)	//aNum correct.
			return 18;
		
		if(blNum1Correct == true && blNum2Correct == true && blANumCorrect != true)	//Num1 and Num2 correct. (WRONG ANSWER CHECK)
			return 19;
		if(blNum1Correct != true && blNum2Correct == true && blANumCorrect == true)	//Num2 and aNum correct.
			return 20;
		if(blNum1Correct == true && blNum2Correct != true && blANumCorrect == true)	//Num1 and aNum correct.
			return 21;

		return 2;	//Returns switch case 2 if something isn't working properly with this function.
	}
	
	private static void checkAnswerSwitch(int intSwitchState) {
		int intCorrect3 = 75, intCorrect2 = 40, intCorrect1 = 20, intIncorrect3 = 50, intIncorrect2 = 30, intIncorrect1 = 15;
		
		boolean blScoreRepeat = false;
		
		if(intScoreRepeat == intSwitchState)	//Prevents the player from spamming the submit button.
			blScoreRepeat = true;
		else
			intScoreRepeat = intSwitchState;
		
		switch(intSwitchState) {	//Alters the data on the game window and increments the score based on the user's input.
		case 1:	//All correct.	
			addScore(intCorrect3); //Increase the score by 75.

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
		case 2:	//All fields are empty. 
			txtNum1.setBackground(Color.white);
			txtNum2.setBackground(Color.white);
			txtAnswerNum.setBackground(Color.white);
			lblStatus.setText("No input detected");
			break;
		case 3:	//Num1 only; correct.	(EMPTY INPUT CHECK)
			txtNum1.setBackground(Color.green);
			txtNum2.setBackground(Color.white);
			txtAnswerNum.setBackground(Color.white);
			
			if(blScoreRepeat == false)
				addScore(intCorrect1);
			break;
		case 4:	//Num2 only; correct.	(EMPTY INPUT CHECK)
			txtNum1.setBackground(Color.white);
			txtNum2.setBackground(Color.green);
			txtAnswerNum.setBackground(Color.white);
			
			if(blScoreRepeat == false)
				addScore(intCorrect1);
			break;
		case 5:	//aNum only; correct.	(EMPTY INPUT CHECK)
			txtNum1.setBackground(Color.white);
			txtNum2.setBackground(Color.white);
			txtAnswerNum.setBackground(Color.green);
		
			if(blScoreRepeat == false)
				addScore(intCorrect1);
			break;
		case 6:	//Num1 and Num2; correct.	(EMPTY INPUT CHECK)
			txtNum1.setBackground(Color.green);
			txtNum2.setBackground(Color.green);
			txtAnswerNum.setBackground(Color.white);
			
			if(blScoreRepeat == false) 
					addScore(intCorrect2);
			break;
		case 7:	//Num2 and aNum; correct.	(EMPTY INPUT CHECK)
			txtNum1.setBackground(Color.white);
			txtNum2.setBackground(Color.green);
			txtAnswerNum.setBackground(Color.green);

			if(blScoreRepeat == false)
				addScore(intCorrect2);
			break;
		case 8:	//Num1 and aNum; correct.	(EMPTY INPUT CHECK)
			txtNum1.setBackground(Color.green);
			txtNum2.setBackground(Color.white);
			txtAnswerNum.setBackground(Color.green);

			if(blScoreRepeat == false) 
				addScore(intCorrect2);
			break;
		case 9:	//All are wrong.
			txtNum1.setBackground(Color.red);
			txtNum2.setBackground(Color.red);
			txtAnswerNum.setBackground(Color.red);
			
			if(blScoreRepeat == false) 
				subScore(intIncorrect3);
			break;
		case 10:	//Num1 and Num2; wrong.	(EMPTY INPUT CHECK)
			txtNum1.setBackground(Color.red);
			txtNum2.setBackground(Color.red);
			txtAnswerNum.setBackground(Color.white);

			if(blScoreRepeat == false)
				subScore(intIncorrect2);
			break;
		case 11:	//Num1; wrong.	(EMPTY INPUT CHECK)
			txtNum1.setBackground(Color.red);
			txtNum2.setBackground(Color.white);
			txtAnswerNum.setBackground(Color.white);
			
			if(blScoreRepeat == false)
				subScore(intIncorrect1);
			break;
		case 12:	//Num2 and aNum; wrong.	(EMPTY INPUT CHECK)
			txtNum1.setBackground(Color.white);
			txtNum2.setBackground(Color.red);
			txtAnswerNum.setBackground(Color.red);
			
			if(blScoreRepeat == false) 
				subScore(intIncorrect2);
			break;
		case 13:	//Num2; wrong.	(EMPTY INPUT CHECK)
			txtNum1.setBackground(Color.white);
			txtNum2.setBackground(Color.red);
			txtAnswerNum.setBackground(Color.white);
			
			if(blScoreRepeat == false)
				subScore(intIncorrect1);
			break;
		case 14:	//aNum; wrong.	(EMPTY INPUT CHECK)
			txtNum1.setBackground(Color.white);
			txtNum2.setBackground(Color.white);
			txtAnswerNum.setBackground(Color.red);
			
			if(blScoreRepeat == false)
				subScore(intIncorrect1);
			break;
		case 15:	//Num1 and aNum; incorrect.	(EMPTY INPUT CHECK)
			txtNum1.setBackground(Color.red);
			txtNum2.setBackground(Color.white);
			txtAnswerNum.setBackground(Color.red);

			if(blScoreRepeat == false) 
				subScore(intIncorrect2);
			break;
		case 16:	//Num1; correct.
			txtNum1.setBackground(Color.green);
			txtNum2.setBackground(Color.red);
			txtAnswerNum.setBackground(Color.red);

			if(blScoreRepeat == false)
				subScore(intIncorrect2 - intCorrect1);
			break;
		case 17:	//Num2; correct.
			txtNum1.setBackground(Color.red);
			txtNum2.setBackground(Color.green);
			txtAnswerNum.setBackground(Color.red);

			if(blScoreRepeat == false) 
				subScore(intIncorrect2 - intCorrect1);
			break;
		case 18:	//aNum; correct.
			txtNum1.setBackground(Color.red);
			txtNum2.setBackground(Color.red);
			txtAnswerNum.setBackground(Color.green);

			if(blScoreRepeat == false) 
				subScore(intIncorrect2 - intCorrect1);
			break;
		case 19:	//Num1 and Num2; correct.
			txtNum1.setBackground(Color.green);
			txtNum2.setBackground(Color.green);
			txtAnswerNum.setBackground(Color.red);

			if(blScoreRepeat == false) 
				addScore(intCorrect2 - intIncorrect1);
			break;
		case 20:	//Num2 and aNum; correct.
			txtNum1.setBackground(Color.red);
			txtNum2.setBackground(Color.green);
			txtAnswerNum.setBackground(Color.green);

			if(blScoreRepeat == false) 
				addScore(intCorrect2 - intIncorrect1);
			break;
		case 21:	//Num1 and aNum; correct.
			txtNum1.setBackground(Color.green);
			txtNum2.setBackground(Color.red);
			txtAnswerNum.setBackground(Color.green);
			
			if(blScoreRepeat == false) 
				addScore(intCorrect2 - intIncorrect1);
			break;
		}
	}
	
	private static void checkAnswer(int intInputResult) {	//Switch state that determines how to respond to the user's input.
		lblScore.setText(String.valueOf(intScore));
		lblStatus.setText("");
		btnSubmit.setText("Submit");
		
		checkAnswerSwitch(inputCheck());
	}
	
	private static void reset() {	//Clear the question and the user's answers, then display a new question.
		newQuestion();	//Generate a new question.
		
		lblCurrEq.setText(strCurrEq);	//Display the new question.
		
		btnDenominator.setEnabled(true);
		
		lblScore.setText(String.valueOf(intScore));	//Display the score.
		
		txtNum1.setText("");
		txtNum1.setEditable(false);	//Text fields not editable to make btnDenominator the main focus.
		txtNum1.setBackground(Color.lightGray);
		
		txtNum2.setText("");
		txtNum2.setEditable(false);
		txtNum2.setBackground(Color.lightGray);
		
		txtAnswerNum.setText("");
		txtAnswerNum.setEditable(false);
		txtAnswerNum.setBackground(Color.lightGray);
		
		txtDen1.setText("");
		txtDen1.setEditable(false);
		txtDen1.setBackground(Color.lightGray);
		
		txtDen2.setText("");
		txtDen2.setEditable(false);
		txtDen2.setBackground(Color.lightGray);
		
		txtAnswerDen.setText("");
		txtAnswerDen.setEditable(false);
		txtAnswerDen.setBackground(Color.lightGray);
		
		if(intOp == 1) {
			lblOp.setText("+");
		}
		else {
			lblOp.setText("-");
		}
		
		lblStatus.setText("");
		
		btnSubmit.setVisible(false);
	}
	
	
	private static int findNumerator(int intNum1, int intNum2) {
		if(intOp == 1)
			return intNum1 + intNum2;
		else
			return intNum1 - intNum2;
	}
	
	private static void checkLCD() {
		intLCD = findDenominator.findLCD(intQuesDen1, intQuesDen2);
		
		if(intQuesDen1 == intLCD && intQuesDen2 == intLCD) {	//Both denominators are the same.
			intUserNum1 = intQuesNum1;
			intUserNum2 = intQuesNum2;
			intUserANum = findNumerator(intUserNum1, intUserNum2);
		}
		
		if(intQuesDen1 == intLCD && intQuesDen2 != intLCD) {	//Den1 stays the same, den2 increases.
			intUserNum1 = intQuesNum1;
			intUserNum2 = intQuesNum2 * (intLCD/intQuesDen2);
			intUserANum = findNumerator(intUserNum1, intUserNum2);
		}
		
		if(intQuesDen1 != intLCD && intQuesDen2 == intLCD) {	//Den2 stays the same, den1 increases.
			intUserNum1 = intQuesNum1 * (intLCD/intQuesDen1);
			intUserNum2 = intQuesNum2;
			intUserANum = findNumerator(intUserNum1, intUserNum2);
		}
		
		if(intQuesDen1 != intLCD && intQuesDen2 != intLCD) {	//Both increase.
			intUserNum1 = intQuesNum1 * (intLCD/intQuesDen1);
			intUserNum2 = intQuesNum2 * (intLCD/intQuesDen2);
			intUserANum = findNumerator(intUserNum1, intUserNum2);
		}
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnDenominator) {
			btnDenominator.setEnabled(false);
			findDenominator launchFindDen = new findDenominator(intQuesDen1, intQuesDen2);	//Launches the findDenominator window.
		}
		
		if(e.getSource() == btnSubmit) {	//Checks if the user's input is correct.
			checkLCD();
			checkAnswer(inputCheck());
			lblScore.setText(String.valueOf(intScore));
		}
		
	}

}

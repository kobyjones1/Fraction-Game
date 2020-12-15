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
	
	private static JFrame gameFrame;
	public static JButton btnDenominator, numSubmit;
	public static JTextField txtNum1, txtNum2, txtDen1, txtDen2, txtAnswerNum, txtAnswerDen;
	public static JLabel currentEquation,lblOp, status;
	private static String currEq, currOp;
	private static int questionNum1, questionDen1, questionNum2, questionDen2, lcd; 
	private static int numerator1, numerator2, answerNum, operator, count, amountOfQuestions;
	
	public startGame() {
		amountOfQuestions = 1;
		count = 0;
		
		gameFrame = new JFrame("Fraction Game");
		gameFrame.setSize(400, 300);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		gameFrame.add(mainPanel);
		mainPanel.setBackground(Color.blue);
		
		currentEquation = new JLabel();
		currentEquation.setFont(new Font("Serif", Font.BOLD, 30));
		currentEquation.setForeground(Color.white);
		currentEquation.setBounds(110, 35, 300, 25);
		mainPanel.add(currentEquation);
		
		btnDenominator = new JButton("Find The Least Common Denominator");
		btnDenominator.addActionListener(this);
		btnDenominator.setBounds(50, 70, 280, 25);
		btnDenominator.setEnabled(true);
		mainPanel.add(btnDenominator);
		
		txtNum1 = new JTextField();
		txtNum1.setBounds(30, 150, 80, 25);
		txtNum1.setEditable(false);
		txtNum1.setBackground(Color.lightGray);
		mainPanel.add(txtNum1);
		
		txtDen1 = new JTextField();
		txtDen1.setBounds(30, 200, 80, 25);
		txtDen1.setEditable(false);
		txtDen1.setBackground(Color.lightGray);
		mainPanel.add(txtDen1);
		
		lblOp = new JLabel(currOp);
		currOp = "+";
		lblOp.setFont(new Font("Serif", Font.BOLD, 30));
		lblOp.setForeground(Color.white);
		lblOp.setBounds(114, 175, 150, 25);
		mainPanel.add(lblOp);
		
		txtNum2 = new JTextField();
		txtNum2.setBounds(135, 150, 80, 25);
		txtNum2.setEditable(false);
		txtNum2.setBackground(Color.lightGray);
		mainPanel.add(txtNum2);

		txtDen2 = new JTextField();
		txtDen2.setBounds(135, 200, 80, 25);
		txtDen2.setEditable(false);;
		txtDen2.setBackground(Color.lightGray);
		mainPanel.add(txtDen2);
		
		JLabel equalsSign = new JLabel("=");
		equalsSign.setFont(new Font("Serif", Font.BOLD, 30));
		equalsSign.setForeground(Color.white);
		equalsSign.setBounds(234, 175, 150, 25);
		mainPanel.add(equalsSign);
		
		txtAnswerNum = new JTextField();
		txtAnswerNum.setBounds(270, 150, 80, 25);
		txtAnswerNum.setEditable(false);
		txtAnswerNum.setBackground(Color.lightGray);
		mainPanel.add(txtAnswerNum);

		txtAnswerDen = new JTextField();
		txtAnswerDen.setBounds(270, 200, 80, 25);
		txtAnswerDen.setEditable(false);
		txtAnswerDen.setBackground(Color.lightGray);
		mainPanel.add(txtAnswerDen);
		
		status = new JLabel();
		status.setFont(new Font("Serif", Font.BOLD, 15));
		status.setForeground(Color.white);
		status.setBounds(30, 233, 400, 25);
		status.setVisible(false);
		mainPanel.add(status);
		
		numSubmit = new JButton("Submit");
		numSubmit.addActionListener(this);
		numSubmit.setBounds(270, 233, 80, 25);
		numSubmit.setVisible(false);
		mainPanel.add(numSubmit);		
		
		reset();
		
		gameFrame.getRootPane().setDefaultButton(numSubmit);
		
		gameFrame.setContentPane(mainPanel);
		gameFrame.setVisible(true);
	}
	
	private static int randGen(int limit) {
		Random randomNum = new Random();
		return randomNum.nextInt(limit) + 1;
	}

	private static void newQuestion() {
		questionNum1 = randGen(9);
		questionDen1 = randGen(9);
		questionNum2 = randGen(9);
		questionDen2 = randGen(9);
		operator = randGen(2);

		if(operator == 1) {
			currOp = "+";
		}
		else {
			currOp = "-";
		}
		
		currEq = questionNum1 + "/" + questionDen1 + " " + currOp + " " + questionNum2 + "/" + questionDen2 + " = ?";
	}
	
	private static int inputCheck() {
		String num1 = String.valueOf(numerator1);
		String num2 = String.valueOf(numerator2);
		String aNum = String.valueOf(answerNum);
		
		String strNum1 = txtNum1.getText();
		String strNum2 = txtNum2.getText();
		String strANum = txtAnswerNum.getText();
		
		boolean num1Correct = false;
		boolean num2Correct = false;
		boolean aNumCorrect = false;
		boolean num1Empty = false;
		boolean num2Empty = false;
		boolean aNumEmpty = false;
		
		if(strNum1.equals(num1))
			num1Correct = true;
		if(strNum1.equals(""))
			num1Empty = true;	
		if(strNum2.equals(num2))
			num2Correct = true;		
		if(strNum2.equals(""))
			num2Empty = true;		
		if(strANum.equals(aNum))
			aNumCorrect = true;		
		if(strANum.equals(""))
			aNumEmpty = true;
		
		if(num1Correct == true && num2Correct == true && aNumCorrect == true)	//All correct.
			return 1;	
		if(num1Empty == true && num2Empty == true && aNumEmpty == true)	//All empty.
			return 2;	
		if(num1Correct == true && num2Empty == true && aNumEmpty == true)	//Num1 correct.	(EMPTY INPUT CHECK)
			return 3;		
		if(num2Correct == true && num1Empty == true && aNumEmpty == true)	//Num2 correct.
			return 4;	
		if(aNumCorrect == true && num1Empty == true && num2Empty == true)	//aNum correct.
			return 5;		
		if(num1Correct != true && num2Empty == true && aNumEmpty == true)	//Num1 incorrect.
			return 11;		
		if(num2Correct != true && num1Empty == true && aNumEmpty == true)	//Num2 incorrect.
			return 13;		
		if(aNumCorrect != true && num1Empty == true && num2Empty == true)	//aNum incorrect.
			return 14;		
		if(num1Correct == true && num2Correct == true && aNumEmpty == true)	//Num1, Num2 correct. (EMPTY INPUT CHECK)
			return 6;		
		if(num1Correct == true && num2Empty == true && aNumCorrect == true)	//Num1, aNum correct.
			return 8;	
		if(num2Correct == true && num1Empty == true && aNumCorrect == true)	//Num2, aNum correct.
			return 7;
		if(num1Correct != true && num2Correct != true && aNumEmpty == true)	//Num1, Num2 incorrect.
			return 10;		
		if(num1Correct != true && num2Empty == true && aNumCorrect != true)	//Num1, aNum incorrect.
			return 15;	
		if(num2Correct != true && num1Empty == true && aNumCorrect != true)	//Num2, aNum incorrect.
			return 12;
		
		if(num1Correct == true && num2Correct != true && aNumCorrect != true)	//Num1 correct.	(WRONG ANSWER CHECK)
			return 16;
		if(num1Correct != true && num2Correct == true && aNumCorrect != true)	//Num2 correct.
			return 17;
		if(num1Correct != true && num2Correct != true && aNumCorrect == true)	//aNum correct.
			return 18;
		
		if(num1Correct == true && num2Correct == true && aNumCorrect != true)	//Num1 and Num2 correct. (WRONG ANSWER CHECK)
			return 19;
		if(num1Correct != true && num2Correct == true && aNumCorrect == true)	//Num2 and aNum correct.
			return 20;
		if(num1Correct == true && num2Correct != true && aNumCorrect == true)	//Num1 and aNum correct.
			return 21;

		return 2;
	}
	
	private static void inputCheckSwitch(int switchState) {
		int switchNum = switchState;
		
		switch(switchNum) {
		case 1:	//All correct.
			if(count < amountOfQuestions) {
				count++;
				reset();
				break;
			}
			else {
				gameFrame.dispose();
				gameOver gameOverStart = new gameOver();
				break;
			}
		case 2:	//All fields empty. 
			txtNum1.setBackground(Color.white);
			txtNum2.setBackground(Color.white);
			txtAnswerNum.setBackground(Color.white);
			status.setText("No input detected");
			break;
		case 3:	//Num1 only, correct.	(EMPTY INPUT CHECK)
			txtNum1.setBackground(Color.green);
			txtNum2.setBackground(Color.white);
			txtAnswerNum.setBackground(Color.white);
			break;
		case 4:	//Num2 only, correct.
			txtNum1.setBackground(Color.white);
			txtNum2.setBackground(Color.green);
			txtAnswerNum.setBackground(Color.white);
			break;
		case 5:	//aNum only, correct.
			txtNum1.setBackground(Color.white);
			txtNum2.setBackground(Color.white);
			txtAnswerNum.setBackground(Color.green);
			break;
		case 6:	//Num1 and Num2 correct.
			txtNum1.setBackground(Color.green);
			txtNum2.setBackground(Color.green);
			txtAnswerNum.setBackground(Color.white);
			break;
		case 7:	//Num2 and aNum correct.
			txtNum1.setBackground(Color.white);
			txtNum2.setBackground(Color.green);
			txtAnswerNum.setBackground(Color.green);
			break;
		case 8:	//Num1 and aNum correct.
			txtNum1.setBackground(Color.green);
			txtNum2.setBackground(Color.white);
			txtAnswerNum.setBackground(Color.green);
			break;
		case 9:	//All wrong
			txtNum1.setBackground(Color.red);
			txtNum2.setBackground(Color.red);
			txtAnswerNum.setBackground(Color.red);
			break;
		case 10:	//Num1 and Num2 wrong.
			txtNum1.setBackground(Color.red);
			txtNum2.setBackground(Color.red);
			txtAnswerNum.setBackground(Color.white);
			break;
		case 11:	//Num1 wrong.
			txtNum1.setBackground(Color.red);
			txtNum2.setBackground(Color.white);
			txtAnswerNum.setBackground(Color.white);
			break;
		case 12:	//Num2 and aNum wrong.
			txtNum1.setBackground(Color.white);
			txtNum2.setBackground(Color.red);
			txtAnswerNum.setBackground(Color.red);
			break;
		case 13:	//Num2 wrong.
			txtNum1.setBackground(Color.white);
			txtNum2.setBackground(Color.red);
			txtAnswerNum.setBackground(Color.white);
			break;
		case 14:	//aNum wrong.
			txtNum1.setBackground(Color.white);
			txtNum2.setBackground(Color.white);
			txtAnswerNum.setBackground(Color.red);
			break;
		case 15:	//Num1 and aNum incorrect.
			txtNum1.setBackground(Color.red);
			txtNum2.setBackground(Color.white);
			txtAnswerNum.setBackground(Color.red);
			break;
		case 16:	//Num1 correct.	(WRONG ANSWER CHECK)
			txtNum1.setBackground(Color.green);
			txtNum2.setBackground(Color.red);
			txtAnswerNum.setBackground(Color.red);
			break;
		case 17:	//Num2 correct.
			txtNum1.setBackground(Color.red);
			txtNum2.setBackground(Color.green);
			txtAnswerNum.setBackground(Color.red);
			break;
		case 18:	//aNum correct.
			txtNum1.setBackground(Color.red);
			txtNum2.setBackground(Color.red);
			txtAnswerNum.setBackground(Color.green);
			break;
		case 19:	//Num1 and Num2 correct.
			txtNum1.setBackground(Color.green);
			txtNum2.setBackground(Color.green);
			txtAnswerNum.setBackground(Color.red);
			break;
		case 20:	//Num2 and aNum correct.
			txtNum1.setBackground(Color.red);
			txtNum2.setBackground(Color.green);
			txtAnswerNum.setBackground(Color.green);
			break;
		case 21:	//Num1 and aNum correct.
			txtNum1.setBackground(Color.green);
			txtNum2.setBackground(Color.red);
			txtAnswerNum.setBackground(Color.green);
			break;
		}
	}
	
	private static void reset() {
		newQuestion();
		
		currentEquation.setText(currEq);
		
		btnDenominator.setEnabled(true);
		
		txtNum1.setText("");
		txtNum1.setEditable(false);
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
		
		if(operator == 1) {
			lblOp.setText("+");
		}
		else {
			lblOp.setText("-");
		}
		
		status.setText("");
		
		numSubmit.setVisible(false);
	}
	
	private static int findNumerator(int num1, int num2) {
		if(operator == 1)
			return num1 + num2;
		else
			return num1 - num2;
	}
	
	private static void checkLCD() {
		lcd = findDenominator.findLCD(questionDen1, questionDen2);
		
		if(questionDen1 == lcd && questionDen2 == lcd) {	//Both denominators are the same.
			numerator1 = questionNum1;
			numerator2 = questionNum2;
			answerNum = findNumerator(numerator1, numerator2);
		}
		
		if(questionDen1 == lcd && questionDen2 != lcd) {	//Den1 stays the same, den2 increases.
			numerator1 = questionNum1;
			numerator2 = questionNum2 * (lcd/questionDen2);
			answerNum = findNumerator(numerator1, numerator2);
		}
		
		if(questionDen1 != lcd && questionDen2 == lcd) {	//Den2 stays the same, den1 increases.
			numerator1 = questionNum1 * (lcd/questionDen1);
			numerator2 = questionNum2;
			answerNum = findNumerator(numerator1, numerator2);
		}
		
		if(questionDen1 != lcd && questionDen2 != lcd) {	//Both increase.
			numerator1 = questionNum1 * (lcd/questionDen1);
			numerator2 = questionNum2 * (lcd/questionDen2);
			answerNum = findNumerator(numerator1, numerator2);
		}
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnDenominator) {
			btnDenominator.setEnabled(false);
			findDenominator findDen = new findDenominator(questionDen1, questionDen2);
		}
		
		if(e.getSource() == numSubmit) {
			checkLCD();
			status.setText("");
			inputCheckSwitch(inputCheck());
		}
		
	}

}

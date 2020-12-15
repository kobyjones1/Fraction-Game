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
	
	private JFrame gameFrame;
	public static JButton btnDenominator, numSubmit;
	public static JTextField txtNum1, txtNum2, txtDen1, txtDen2, txtAnswerNum, txtAnswerDen;
	public static JLabel currentEquation,lblOp, status;
	private static String currEq, currOp;
	private static int questionNum1, questionDen1, questionNum2, questionDen2, lcd; 
	private static int numerator1, numerator2, answerNum, operator, count, amountOfQuestions;
	
	public startGame() {
		amountOfQuestions = 3;
		count = 0;
		
		gameFrame = new JFrame("Fraction Game");
		gameFrame.setSize(400, 300);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		gameFrame.add(mainPanel);
		mainPanel.setBackground(Color.blue);
		
		newQuestion();
		
		currentEquation = new JLabel(currEq);
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
		operator = randGen(1);

		if(operator == 1) {
			currOp = "+";
		}
		else {
			currOp = "-";
		}
		
		currEq = questionNum1 + "/" + questionDen1 + " " + currOp + " " + questionNum2 + "/" + questionDen2 + " = ?";
	}
	
	private static int inputCheck() {
		String num1, num2, aNum;
		
		num1 = txtNum1.getText();
		num2 = txtNum1.getText();
		aNum = txtAnswerNum.getText();
		
		if(num1 == String.valueOf(numerator1) && num2 == String.valueOf(numerator2) && aNum == String.valueOf(answerNum))	//Correct answer.
			return 1;
		
		if(num1 == "" || num2 == "" || aNum == "")	//Empty values.
			return 2;
		
		if(num1 != String.valueOf(numerator1)) {	//Wrong num1, num2, and/or aNum.
			if(num2 != String.valueOf(numerator2)) {
				if(aNum != String.valueOf(answerNum)){
					return 3;
				}
				return 4;
			}
			return 5;
		}
		
		if(num2 != String.valueOf(numerator2)) { 	//Wrong num2 and/or aNum.
			if(aNum != String.valueOf(answerNum)){
				return 6;
			}
			return 7;
		}
		
		return 8;	//Wrong aNum.		
	}
	
	private static void reset() {
		newQuestion();
		
		btnDenominator.setEnabled(true);
		
		txtNum1.setEditable(false);
		txtNum1.setBackground(Color.lightGray);
		
		txtNum2.setEditable(false);
		txtNum2.setBackground(Color.lightGray);
		
		txtAnswerNum.setEditable(false);
		txtAnswerNum.setBackground(Color.lightGray);
		
		txtDen1.setEditable(false);
		txtDen1.setBackground(Color.lightGray);
		
		txtDen2.setEditable(false);
		txtDen2.setBackground(Color.lightGray);
		
		txtAnswerDen.setEditable(false);
		txtAnswerDen.setBackground(Color.lightGray);
		
		status.setText("");
		
		numSubmit.setVisible(false);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnDenominator) {
			btnDenominator.setEnabled(false);
			findDenominator findDen = new findDenominator(questionDen1, questionDen2);
			
			lcd = findDen.findLCD(questionDen1, questionDen2);
			numerator1 = questionNum1 * (lcd/questionDen1);
			numerator2 = questionNum2 * (lcd/questionDen2);
			
			if(operator == 1) {
				answerNum = numerator1 + numerator2;
			}
			else{
				answerNum = numerator1 - numerator2;
			}
		}
		
		if(e.getSource() == numSubmit) {
			int switchState = inputCheck();
			
			switch(switchState) {
			case 1: 
				if(count < amountOfQuestions) {
					count++;
					reset();
					break;
				}
				else {
					gameFrame.dispose();
					gameOver gameOver = new gameOver();
					break;
				}
			case 2: 
				status.setText("Each textbox requires an answer.");
				break;
			case 3: 
				txtNum1.setBackground(Color.red);
				txtNum2.setBackground(Color.red);
				txtAnswerNum.setBackground(Color.red);
				break;
			case 4: 
				txtNum1.setBackground(Color.red);
				txtNum2.setBackground(Color.red);
				break;
			case 5: 
				txtNum1.setBackground(Color.red);
				break;
			case 6: 
				txtNum2.setBackground(Color.red);
				txtAnswerNum.setBackground(Color.red);
				break;
			case 7: 
				txtNum2.setBackground(Color.red);
				break;
			case 8: 
				txtAnswerNum.setBackground(Color.red);
				break;
			}
		}
	}
}

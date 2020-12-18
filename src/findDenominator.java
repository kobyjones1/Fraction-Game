import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class findDenominator implements ActionListener{
	
	private String strScoreRepeat = null;
	
	private JFrame frmFindDen;
	private JButton btnSubmit, btnMusic;
	private JTextField txtUserAnswer;
	private JLabel lblStatus;
	private static int intDen1, intDen2;
	
	public findDenominator(int intQuesDen1, int intQuesDen2) {
		intDen1 = intQuesDen1;	//Obtain the current denominators from the startGame window.
		intDen2 = intQuesDen2;
		
		frmFindDen = new JFrame("Fraction Game");	//Launches the findDenominator window.
		frmFindDen.setSize(500, 300);
		frmFindDen.setLocationRelativeTo(null);
		frmFindDen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pnlFindDen = new JPanel();
		pnlFindDen.setLayout(null);
		frmFindDen.add(pnlFindDen);
		pnlFindDen.setBackground(Color.blue);
		
		String strLCD1 = "Enter the LCD of the two numbers shown.";
		String strLCD2 = "The LCD is the smallest number that both numbers will divide into evenly.";
		
		JLabel lblTip1 = new JLabel(strLCD1);	//Displays directions for obtaining the LCD here.
		lblTip1.setBounds(120, 20, 300, 25);
		lblTip1.setForeground(Color.white);
		pnlFindDen.add(lblTip1);
		
		JLabel lblTip2 = new JLabel(strLCD2);	//Displays a hint to the user.
		lblTip2.setBounds(35, 40, 430, 25);
		lblTip2.setForeground(Color.white);
		pnlFindDen.add(lblTip2);
		
		JTextField LCDDen1 = new JTextField(String.valueOf(intDen1));	//Displays the first denominator.
		LCDDen1.setEditable(false);
		LCDDen1.setBounds(160, 100, 50, 25);
		pnlFindDen.add(LCDDen1);
		
		JTextField LCDDen2 = new JTextField(String.valueOf(intDen2));	//Displays the second denominator.
		LCDDen2.setEditable(false);
		LCDDen2.setBounds(260, 100, 50, 25);
		pnlFindDen.add(LCDDen2);

		txtUserAnswer = new JTextField();	//Responsible for taking the user's input.
		txtUserAnswer.setBounds(198, 150, 75, 25);
		pnlFindDen.add(txtUserAnswer);

		SwingUtilities.invokeLater(new Runnable() {	//Places the focus (blinking cursor) on the text field. 
			public void run() { 
				txtUserAnswer.requestFocus(); 
			} 
        }); 
		
		btnSubmit = new JButton("Submit");	//Button responsible for checking if the user's answer is correct.
		btnSubmit.setBounds(198, 180, 75, 25);
		btnSubmit.addActionListener(this);
		pnlFindDen.add(btnSubmit);
		
		btnMusic = new JButton("Music");	//Control the music.
		btnMusic.setBounds(410, 10, 70, 25);
		btnMusic.addActionListener(this);
		pnlFindDen.add(btnMusic);
		
		lblStatus = new JLabel("");	//Tells the user if their answer is incorrect or if no input was detected.
		lblStatus.setBounds(190, 205, 200, 25);
		lblStatus.setForeground(Color.white);
		pnlFindDen.add(lblStatus);
		
		frmFindDen.getRootPane().setDefaultButton(btnSubmit);	//Allows the user to activate the "btnSubmit" button with the ENTER key.
		
		frmFindDen.add(pnlFindDen);
		frmFindDen.setVisible(true);
	}

	public static int findLCD(int intDen1, int intDen2) {	//Finds the least common denominator of the two denominator values.
		int intLargerNum = Math.max(intDen1, intDen2);
		int intSmallerNum = Math.min(intDen1, intDen2);
		int intLCD = intLargerNum;
		
		while(intLCD % intSmallerNum != 0) {	//Loops until the smaller number divides evenly into the larger number.
			intLCD += intLargerNum;
		}
		
		return intLCD;
	}
	
	private static void close(int intLCD) {	//Prepares the startGame window's numerator and denominator text fields when findDenominator closes.
		startGame.txtUserDen1.setText(String.valueOf(intLCD));	//Display the LCD as the denominator on each fraction.
		startGame.txtUserDen1.setBackground(Color.green);
		
		startGame.txtUserDen2.setText(String.valueOf(intLCD));
		startGame.txtUserDen2.setBackground(Color.green);
		
		startGame.txtUserAnswerDen.setText(String.valueOf(intLCD));
		startGame.txtUserAnswerDen.setBackground(Color.green);
		
		startGame.checkLCD();
		
		startGame.txtUserNum1.setBackground(Color.white);	//Activate the numerator text fields.
		startGame.txtUserNum1.setEditable(true);
		
		startGame.txtUserNum2.setBackground(Color.white);
		startGame.txtUserNum2.setEditable(true);
		
		startGame.txtUserAnswerNum.setBackground(Color.white);
		startGame.txtUserAnswerNum.setEditable(true);
		
		startGame.lblScore.setText(String.valueOf(startGame.intScore));	//Send the score to the game window.
		
		startGame.btnSubmit.setVisible(true);	//Displays the submit button.
	}
	
	private void checkAnswer(int intLCD, String strLCD) {	//Check if the player is correct.
		boolean blScoreRepeat = false;
		
		if(txtUserAnswer.getText().equals(strScoreRepeat))	//Prevents the player from spamming the submit button.
			blScoreRepeat = true;
		
		if(txtUserAnswer.getText().equals(strLCD)) {	//Closes findDenominator if the user's answer is correct.
			startGame.addScore(65);	//Gain 60 (+5 bonus points).
			frmFindDen.dispose();
			close(intLCD);
		}
		else if(txtUserAnswer.getText().equals("")) {	//Tells the user if there's no data in the text field.
			lblStatus.setText("No input detected.");
		}
		else if(blScoreRepeat == false) {
			startGame.subScore(25);	//Lose 15 (+10 bonus points).
			strScoreRepeat = txtUserAnswer.getText();
			txtUserAnswer.setBackground(Color.red);
			lblStatus.setText(txtUserAnswer.getText() + " is incorrect.");	//Tells the user that their answer is incorrect.
		}
		else {
			txtUserAnswer.setBackground(Color.red);
			lblStatus.setText(txtUserAnswer.getText() + " is incorrect.");	//Tells the user that their answer is incorrect.
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSubmit) {
			int intLCD = findLCD(intDen1, intDen2);	//Calculate the LCD to check the user's answer.
			String strLCD = String.valueOf(intLCD);

			checkAnswer(intLCD, strLCD);		
		}	
		
		if(e.getSource() == btnMusic) {
			login.toggleMusic();
		}
		
	}
		
}
	



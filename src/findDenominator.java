import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class findDenominator implements ActionListener{
	
	private JFrame denFrame;
	private JButton submit;
	private JTextField userAnswer;
	private JLabel status;
	private static int intDen1, intDen2;
	
	public findDenominator(int den1, int den2) {
		intDen1 = den1;
		intDen2 = den2;
		
		denFrame = new JFrame("Fraction Game");
		denFrame.setSize(500, 300);
		denFrame.setLocationRelativeTo(null);
		denFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel denPanel = new JPanel();
		denPanel.setLayout(null);
		denFrame.add(denPanel);
		denPanel.setBackground(Color.blue);
		
		String LCDTip1 = "Enter the LCD of the two numbers shown.";
		String LCDTip2 = "The LCD is the smallest number that both numbers will divide into evenly.";
		
		JLabel lblTip1 = new JLabel(LCDTip1);
		lblTip1.setBounds(120, 20, 300, 25);
		lblTip1.setForeground(Color.white);
		denPanel.add(lblTip1);
		
		JLabel lblTip2 = new JLabel(LCDTip2);
		lblTip2.setBounds(35, 40, 430, 25);
		lblTip2.setForeground(Color.white);
		denPanel.add(lblTip2);
		
		String strDen1 = String.valueOf(intDen1);
		String strDen2 = String.valueOf(intDen2);
		
		JTextField LCDDen1 = new JTextField(strDen1);
		LCDDen1.setEditable(false);
		LCDDen1.setBounds(160, 100, 50, 25);
		denPanel.add(LCDDen1);
		
		JTextField LCDDen2 = new JTextField(strDen2);
		LCDDen2.setEditable(false);
		LCDDen2.setBounds(260, 100, 50, 25);
		denPanel.add(LCDDen2);
		
		userAnswer = new JTextField();
		userAnswer.setBounds(198, 155, 75, 20);
		denPanel.add(userAnswer);

		submit = new JButton("Submit");
		submit.setBounds(198, 180, 75, 25);
		submit.addActionListener(this);
		denPanel.add(submit);
		
		status = new JLabel("");
		status.setBounds(185, 200, 200, 25);
		denPanel.add(status);
		
		denFrame.add(denPanel);
		denFrame.setVisible(true);
	}
	
	public int findLCD(int den1, int den2) {
		int larger = Math.max(den1, den2);
		int smaller = Math.min(den1, den2);
		int lcd = larger;
		
		while(lcd % smaller != 0) {
			lcd += larger;
		}
		
		return lcd;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == submit) {
			int lcd = findLCD(intDen1, intDen2);

			if(userAnswer.getText().equals(String.valueOf(lcd))) {
				denFrame.dispose();
				startGame.txtDen1.setText(String.valueOf(lcd));
				startGame.txtDen1.setBackground(Color.green);
				
				startGame.txtDen2.setText(String.valueOf(lcd));
				startGame.txtDen2.setBackground(Color.green);
				
				startGame.txtAnswerDen.setText(String.valueOf(lcd));
				startGame.txtAnswerDen.setBackground(Color.green);
				
				startGame.txtNum1.setBackground(Color.white);
				startGame.txtNum1.setEditable(true);
				
				startGame.txtNum2.setBackground(Color.white);
				startGame.txtNum2.setEditable(true);
				
				startGame.txtAnswerNum.setBackground(Color.white);
				startGame.txtAnswerNum.setEditable(true);
				
				startGame.numSubmit.setVisible(true);
				startGame.status.setVisible(true);
			}
			else if(userAnswer.getText().equals("")) {
				status.setText("No input detected.");
			}
			else {
				userAnswer.setBackground(Color.red);
				status.setText(userAnswer.getText() + " is incorrect.");
			}
		}
	}
}

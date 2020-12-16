import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class gameOver implements ActionListener {
	
	public static String strScore;
	
	private static JFrame frmGameOver;
	private static JButton btnStartOver, btnLogout, btnClose;
	
	public gameOver() {
		frmGameOver = new JFrame("Fraction Game");	//Launches the gameOver screen.
		frmGameOver.setSize(400, 300);
		frmGameOver.setLocationRelativeTo(null);
		frmGameOver.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pnlGameOver = new JPanel();
		pnlGameOver.setLayout(null);
		pnlGameOver.setBackground(Color.blue);
		frmGameOver.add(pnlGameOver);
		
		JLabel lblGameOver = new JLabel("Game Over!");	//Displays the "Game Over!" text on the screen.
		lblGameOver.setBounds(100, 60, 200, 25);
		lblGameOver.setFont(new Font("Arial", Font.BOLD, 30));
		lblGameOver.setForeground(Color.green);
		pnlGameOver.add(lblGameOver);
		
		strScore = "Final Score: " + startGame.intScore;
		
		JLabel lblScore = new JLabel(strScore);	//Displays the score.
		lblScore.setBounds(90, 90, 350, 40);
		lblScore.setFont(new Font("Arial", Font.BOLD, 25));
		lblScore.setForeground(Color.green);
		pnlGameOver.add(lblScore);
		
		btnStartOver = new JButton("Start Over");	//Starts the game over.
		btnStartOver.setBounds(30, 150, 95, 25);
		btnStartOver.addActionListener(this);
		pnlGameOver.add(btnStartOver);
		
		btnLogout = new JButton("Log Out");	//Takes the user back to the login window.
		btnLogout.setBounds(150, 150, 80, 25);
		btnLogout.addActionListener(this);
		pnlGameOver.add(btnLogout);
		
		btnClose = new JButton("Close");	//Exits the game.
		btnClose.setBounds(255, 150, 80, 25);
		btnClose.addActionListener(this);
		pnlGameOver.add(btnClose);

		frmGameOver.add(pnlGameOver);
		frmGameOver.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnStartOver) {	//Starts the game over.
			frmGameOver.dispose();
			startGame launchStartGame = new startGame();
		}
		
		if(e.getSource() == btnLogout) {	//Launches the login window.
			frmGameOver.dispose();
			login launchLogin = new login();
		}
		
		if(e.getSource() == btnClose) {	//Closes the game.
			frmGameOver.dispose();
		}
	}
	
}

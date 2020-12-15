import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class gameOver implements ActionListener {
	
	private static JFrame gameOverScreen;
	private static JButton startOver, logOut, close;
	
	public gameOver() {
		gameOverScreen = new JFrame("Fraction Game");
		gameOverScreen.setSize(400, 150);
		gameOverScreen.setLocationRelativeTo(null);
		gameOverScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel gameOverPanel = new JPanel();
		gameOverPanel.setLayout(null);
		gameOverScreen.add(gameOverPanel);
		
		JLabel gameOverMessage = new JLabel("Game Over!");
		gameOverMessage.setBounds(300, 100, 200, 25);
		gameOverMessage.setFont(new Font("Arial", Font.BOLD, 30));
		gameOverMessage.setForeground(Color.white);
		gameOverPanel.add(gameOverMessage);
		
		startOver = new JButton("Start Over");
		startOver.setBounds(200, 150, 80, 25);
		startOver.addActionListener(this);
		gameOverPanel.add(startOver);
		
		logOut = new JButton("Log Out");
		logOut.setBounds(300, 150, 80, 25);
		logOut.addActionListener(this);
		gameOverPanel.add(logOut);
		
		close = new JButton("Close");
		close.setBounds(400, 150, 80, 25);
		close.addActionListener(this);
		gameOverPanel.add(close);
		
		gameOverScreen.add(gameOverPanel);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startOver) {
			gameOverScreen.dispose();
			startGame start = new startGame();
		}
		
		if(e.getSource() == logOut) {
			gameOverScreen.dispose();
			login login = new login();
		}
		
		if(e.getSource() == close) {
			gameOverScreen.dispose();
		}
	}
}

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class gameOver implements ActionListener {
	
	public static String strScore;
	private static JFrame frmGameOver;
	private static JButton btnRestart, btnLogout, btnClose;
	private static PreparedStatement state;
	private static ResultSet result;
	private static Connection gameOverDB;
	
	public gameOver() {
		state = null;
		result = null;
		gameOverDB = login.connDB;
		int dataScore = 0, newScore = 0;
		String sql;
		
		try {
			sql = "SELECT Score FROM Players WHERE Username = ?";
			state = gameOverDB.prepareStatement(sql);
			state.setString(1, login.strUsername);
			result = state.executeQuery();
			
			if(result.next()) {
				dataScore = result.getInt(1);	
			}
			
			dataScore += startGame.intScore;
			
			sql = "UPDATE Players SET Score = ? WHERE Username = ?";
			state = gameOverDB.prepareStatement(sql);
			state.setLong(1, dataScore);
			state.setString(2, login.strUsername);
			state.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
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
		
		strScore = "Score: " + dataScore + " (+" + startGame.intScore + ")";
		
		JLabel lblScore = new JLabel(strScore);	//Displays the score.
		lblScore.setBounds(120, 90, 350, 40);
		lblScore.setFont(new Font("Arial", Font.BOLD, 25));
		lblScore.setForeground(Color.green);
		pnlGameOver.add(lblScore);
		
		btnRestart = new JButton("Restart");	//Starts the game over.
		btnRestart.setBounds(45, 150, 80, 25);
		btnRestart.addActionListener(this);
		pnlGameOver.add(btnRestart);
		
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
		if(e.getSource() == btnRestart) {	//Starts the game over.
			frmGameOver.dispose();
			startGame launchStartGame = new startGame();
		}
		
		if(e.getSource() == btnLogout) {	//Launches the login window.
			try {
				state.close();
				result.close();
				gameOverDB.close();
				login.connDB.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			frmGameOver.dispose();
			login launchLogin = new login();
		}
		
		if(e.getSource() == btnClose) {	//Closes the game.
			try {
				state.close();
				result.close();
				gameOverDB.close();
				login.connDB.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			frmGameOver.dispose();
		}
	}
	
}

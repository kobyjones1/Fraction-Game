import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class gameOver implements ActionListener {
	
	private static JFrame frmGameOver;
	private static JButton btnRestart, btnLogout, btnClose, btnMusic;
	private static PreparedStatement prepState;
	private static ResultSet resSet;
	private static Connection connGameOverDB;
	private static int intDatabaseScore = 0;
	
	public gameOver() {
		updateScore();	//Store the player's new score in the database.
		
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
		
		String strScore;	//Displays the player's new score + the amount of points earned while playing.
		strScore = "Score: " + intDatabaseScore + " (+" + startGame.intScore + ")";	
		
		JLabel lblScore = new JLabel(strScore);	//Displays the score.
		lblScore.setBounds(90, 90, 350, 40);
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
		
		btnMusic = new JButton("Music");	//Control the music.
		btnMusic.setBounds(305, 10, 70, 25);
		btnMusic.addActionListener(this);
		pnlGameOver.add(btnMusic);
		
		btnClose = new JButton("Close");	//Exits the game.
		btnClose.setBounds(255, 150, 80, 25);
		btnClose.addActionListener(this);
		pnlGameOver.add(btnClose);

		frmGameOver.add(pnlGameOver);
		frmGameOver.setVisible(true);
	}
	
	private void updateScore() {
		String strSQL;
		
		prepState = null;
		resSet = null;
		connGameOverDB = login.connDB;
		
		try {
			strSQL = "SELECT Score FROM Players WHERE Username = ?";	//Obtain the score from the database so that it can be updated.
			prepState = connGameOverDB.prepareStatement(strSQL);
			prepState.setString(1, login.strUsername);
			resSet = prepState.executeQuery();
			
			if(resSet.next()) 
				intDatabaseScore = resSet.getInt(1);	

			intDatabaseScore += startGame.intScore;	//Increases the player's total score by what they've earned while playing on their new session.
			
			strSQL = "UPDATE Players SET Score = ? WHERE Username = ?";	//Updates the player's score in the database.
			prepState = connGameOverDB.prepareStatement(strSQL);
			prepState.setLong(1, intDatabaseScore);
			prepState.setString(2, login.strUsername);
			prepState.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void closeDB() {	//Closes the connection to the database.
		try {
			if(connGameOverDB != null) {
			prepState.close();
			resSet.close();
			connGameOverDB.close();
			login.connDB.close();
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnRestart) {	//Starts the game over.
			frmGameOver.dispose();
			startGame launchStartGame = new startGame();
		}
		
		if(e.getSource() == btnLogout) {	//Launches the login window.
			closeDB();	//Closes the connection to the database, then restarts when the new player logs in.
			frmGameOver.dispose();
			login launchLogin = new login();
		}
		
		if(e.getSource() == btnMusic) {
			login.toggleMusic();
		}
		
		if(e.getSource() == btnClose) {	//Closes the game.
			closeDB();	//Closing the database when the game ends completely allows the player to continue adding to their score.
			frmGameOver.dispose();
		}
	}
	
}

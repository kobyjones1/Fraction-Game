//import java.util.Scanner;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class fracGame extends JFrame{
	
	//static Scanner userInput = new Scanner(System.in);
	
	public static void main(String[] args) {
		JFrame mainDisplay = new JFrame("Fraction Game");
		mainDisplay.setSize(500, 500);
		mainDisplay.setLocationRelativeTo(null);
		mainDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton startButton = new JButton("Start");
		
		JPanel mainPanel = new JPanel(new GridLayout(1,1));
		mainPanel.add(startButton);
		
		mainDisplay.setContentPane(mainPanel);
		mainDisplay.setVisible(true);
		
		//int numerator1, numerator2;
		
	}

}

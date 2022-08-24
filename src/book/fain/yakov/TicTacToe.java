package book.fain.yakov;

import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

public class TicTacToe extends Applet implements ActionListener {
	
	Button squares[];
	Button newGameButton;
	Label score;
	int emptySquaresLeft = 9;
	
	public void init() {
		
		this.setLayout(new BorderLayout());
		this.setBackground(Color.CYAN);
		
		Font appletFont = new Font("Monospased", Font.BOLD, 20);
		this.setFont(appletFont);
		
		newGameButton = new Button("New Game");
		newGameButton.addActionListener(this);
		
		Panel topPanel = new Panel();
		topPanel.add(newGameButton);
		
			this.add(topPanel, "North");
			
			Panel centerPanel = new Panel();
			centerPanel.setLayout(new GridLayout(3,3));
			this.add(centerPanel, "Center");
			
			score = new Label("Your turn!");
			this.add(score, "South");
			
			squares = new Button[9];
			
			for (int i = 0; i < squares.length; i++) {
				squares[i] = new Button();
				squares[i].addActionListener(this);
				squares[i].setBackground(Color.ORANGE);
				centerPanel.add(squares[i]);
			}
		
		
	}

}

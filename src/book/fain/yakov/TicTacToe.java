package book.fain.yakov;

import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

public class TicTacToe extends Applet implements ActionListener {
	
	Button squares[];
	Button newGameButton;
	Label score;
	int emptySquaresLeft = 9;
	int countWin = 0;
	int countLose = 0;
	Label youLose;
	Label youWin;
	
	public void init() {
		
		this.setLayout(new BorderLayout());
		this.setBackground(Color.CYAN);
		
		Font appletFont = new Font("Monospased", Font.BOLD, 20);
		this.setFont(appletFont);
		
		newGameButton = new Button("New Game");
		newGameButton.addActionListener(this);
		
		Panel topPanel = new Panel();
		
		youLose = new Label("You lose: " + countLose);
		topPanel.add(youLose);		
		
		topPanel.add(newGameButton);
		
		youWin = new Label("You won: " + countWin);
		topPanel.add(youWin);
		
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
	
	public void actionPerformed(ActionEvent e) {
		
		Button theButton = (Button) e.getSource();
		
		if(theButton == newGameButton) {
			
			for(int i = 0; i < squares.length; i++) {
				squares[i].setEnabled(true);
				squares[i].setLabel("");
				squares[i].setBackground(Color.ORANGE);
			}
			
			emptySquaresLeft = 9;
			score.setText("Your turn!");
			newGameButton.setEnabled(false);
			return;
		}
		
		String winner = "";
		
		for(int i = 0; i < squares.length; i++) {
			
			if(theButton == squares[i]) {
				squares[i].setLabel("X");
				winner = lookForWinner();
				
				if(!"".equals(winner)) {
					endGame();
				} else {
					computerMove();
					winner = lookForWinner();
					
					if(!"".equals(winner)) {
						endGame();
					}
				}
				
				break;
			}
		}
		
		
		if(winner.equals("X")) {
			score.setText("You won!");
			countWin++;
			youWin.setText("You won: " + String.valueOf(countWin));
		} else if(winner.equals("O")) {
			score.setText("You lost!");
			countLose++;
			youLose.setText("You lose: " + String.valueOf(countLose));
		} else if(winner.equals("T")) {
			score.setText("It's a tie!");
		}
	}
	
	String lookForWinner() {
		String theWinner = "";
		emptySquaresLeft--;
		
		if (emptySquaresLeft == 0) {
			return "T";
		}
		
		if (!squares[0].getLabel().equals("") &&
				squares[0].getLabel().equals(squares[1].getLabel()) &&
				squares[0].getLabel().equals(squares[2].getLabel())) {
			
			theWinner = squares[0].getLabel();
			highLightWinner(0, 1, 2);
			
		} else if (!squares[3].getLabel().equals("") &&
				squares[3].getLabel().equals(squares[4].getLabel()) &&
				squares[3].getLabel().equals(squares[5].getLabel())) {
			
			theWinner = squares[3].getLabel();
			highLightWinner(3, 4, 5);
			
		} else if (!squares[6].getLabel().equals("") &&
				squares[6].getLabel().equals(squares[7].getLabel()) &&
				squares[6].getLabel().equals(squares[8].getLabel())) {
			
			theWinner = squares[6].getLabel();
			highLightWinner(6, 7, 8);
			
		} else if (!squares[0].getLabel().equals("") &&
				squares[0].getLabel().equals(squares[3].getLabel()) &&
				squares[0].getLabel().equals(squares[6].getLabel())) {
			
			theWinner = squares[0].getLabel();
			highLightWinner(0, 3, 6);
			
		} else if (!squares[1].getLabel().equals("") &&
				squares[1].getLabel().equals(squares[4].getLabel()) &&
				squares[1].getLabel().equals(squares[7].getLabel())) {
			
			theWinner = squares[1].getLabel();
			highLightWinner(1, 4, 7);
			
		} else if (!squares[2].getLabel().equals("") &&
				squares[2].getLabel().equals(squares[5].getLabel()) &&
				squares[2].getLabel().equals(squares[8].getLabel())) {
			
			theWinner = squares[2].getLabel();
			highLightWinner(2, 5, 8);
			
		} else if (!squares[0].getLabel().equals("") &&
				squares[0].getLabel().equals(squares[4].getLabel()) &&
				squares[0].getLabel().equals(squares[8].getLabel())) {
			
			theWinner = squares[0].getLabel();
			highLightWinner(0, 4, 8);
			
		} else if (!squares[2].getLabel().equals("") &&
				squares[2].getLabel().equals(squares[4].getLabel()) &&
				squares[2].getLabel().equals(squares[6].getLabel())) {
			
			theWinner = squares[2].getLabel();
			highLightWinner(2, 4, 6);
			
		}
		
		return theWinner;
	}
	
	void computerMove() {
		
		int selectedSquare;
		
		selectedSquare = findEmptySquare("O");
		
		if (selectedSquare == -1) {
			selectedSquare = findEmptySquare("X");
		}
		
		if ( (selectedSquare == -1)
			&& (squares[4].getLabel().equals("")) ) {
			
			selectedSquare = 4;
		}
		
		if (selectedSquare == -1) {
			selectedSquare = getRandomSquare();
		}
		
		squares[selectedSquare].setLabel("O");
	}
	
	
	int findEmptySquare (String player) {
		
		int[] weight = new int[9];
		
		for (int i = 0; i < weight.length; i++) {
			
			if (squares[i].getLabel().equals("O")) {
				weight[i] = -1;
			} else if (squares[i].getLabel().equals("X")) {
				weight[i] = 1;
			} else {
				weight[i] = 0;
			}
		}
		
		int twoWeights = player.equals("O") ? -2 : 2 ;
		
		if (weight[0] + weight[1] + weight[2] == twoWeights) {
			if (weight[0] == 0) {
				return 0;
			} else if (weight[1] == 0) {
				return 1;
			} else {
				return 2;
			}
		}
		
		if (weight[3] + weight[4] + weight[5] == twoWeights) {
			if (weight[3] == 0) {
				return 3;
			} else if (weight[4] == 0) {
				return 4;
			} else {
				return 5;
			}
		}
		
		if (weight[6] + weight[7] + weight[8] == twoWeights) {
			if (weight[6] == 0) {
				return 6;
			} else if (weight[7] == 0) {
				return 7;
			} else {
				return 8;
			}
		}
		
		if (weight[0] + weight[3] + weight[6] == twoWeights) {
			if (weight[0] == 0) {
				return 0;
			} else if (weight[3] == 0) {
				return 3;
			} else {
				return 6;
			}
		}
		
		if (weight[1] + weight[4] + weight[7] == twoWeights) {
			if (weight[1] == 0) {
				return 1;
			} else if (weight[4] == 0) {
				return 4;
			} else {
				return 7;
			}
		}
		
		if (weight[2] + weight[5] + weight[8] == twoWeights) {
			if (weight[2] == 0) {
				return 2;
			} else if (weight[5] == 0) {
				return 5;
			} else {
				return 8;
			}
		}
		
		if (weight[0] + weight[4] + weight[8] == twoWeights) {
			if (weight[0] == 0) {
				return 0;
			} else if (weight[4] == 0) {
				return 4;
			} else {
				return 8;
			}
		}
		
		if (weight[2] + weight[4] + weight[6] == twoWeights) {
			if (weight[2] == 0) {
				return 2;
			} else if (weight[4] == 0) {
				return 4;
			} else {
				return 6;
			}
		}
		
		return -1;
		
	}
	
	int getRandomSquare() {
		
		boolean gotEmptySquare = false;
		
		int selectedSquare = -1;
		
		do {
			selectedSquare = (int) (Math.random() * 9);
			
			if (squares[selectedSquare].getLabel().equals("")) {
				gotEmptySquare = true;
			}
		} while (!gotEmptySquare);
		
		return selectedSquare;
	}
	
	void highLightWinner (int win1, int win2, int win3) {
		squares[win1].setBackground(Color.CYAN);
		squares[win2].setBackground(Color.CYAN);
		squares[win3].setBackground(Color.CYAN);
	}
	
	void endGame() {
		newGameButton.setEnabled(true);
		
		for (int i = 0; i < squares.length; i++) {
			squares[i].setEnabled(true);
		}
	}

}

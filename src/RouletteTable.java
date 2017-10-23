import java.awt.Color;

import javax.swing.JButton;


public class RouletteTable {

	private RouletteSpot[] spotArray = new RouletteSpot[44];
	
	public static final int INVALID_POSITION = -1;
	public static final int ODD = 37;
	public static final int EVEN = 38;
	public static final int RED = 39;
	public static final int BLACK = 40;
	public static final int FIRST_THIRD = 41;
	public static final int SECOND_THIRD = 42;
	public static final int THIRD_THIRD = 43;
	
	//RouletteGameGUI roulette = new RouletteGameGUI();

	double totalWon = 0.0;
	
	public void initializeBet(){
		
		for(int i = 0; i < 37; i++){
			spotArray[i] = new RouletteSpot(35);
		}
		
		spotArray[ODD] = new RouletteSpot(1);
		spotArray[EVEN] = new RouletteSpot(1);
		spotArray[RED] = new RouletteSpot(1);
		spotArray[BLACK] = new RouletteSpot(1);
		spotArray[FIRST_THIRD] = new RouletteSpot(2);
		spotArray[SECOND_THIRD] = new RouletteSpot(2);
		spotArray[THIRD_THIRD] = new RouletteSpot(2);
	}
	
	public int getPosition(String locn){
		
		switch(locn.toUpperCase()){
		
		case "O":
			return ODD;
		case "E":
			return EVEN;
		case "R":
			return RED;
		case "B":
			return BLACK;
		case "F":
			return FIRST_THIRD;
		case "S":
			return SECOND_THIRD;
		case "T":
			return THIRD_THIRD;
		}
		
		int numericVal = Integer.parseInt(locn);
		if(numericVal >= 0 && numericVal <= 36)
			return numericVal;
		
		return INVALID_POSITION;
	}
	public void assignBet(double bet, String locn){
		
		int position = getPosition(locn);
		if(position != INVALID_POSITION){
			spotArray[position].setBet(bet);
		}
	}
	
	public void clearBets(){
	
		for(int i = 0; i < spotArray.length; i++)
		{
			spotArray[i].clearBet();
		}
	
	}

	public double calcWinnings(int winNum, RouletteWheel.Colors color){
		double amountWon = 0.0;
		
		if(winNum != 0){
			if(color == RouletteWheel.Colors.red) {
				amountWon += spotArray[RED].returnWinnings();
			}
			else if(color == RouletteWheel.Colors.black) {
				amountWon += spotArray[BLACK].returnWinnings();
			}
			
			if(winNum % 2 == 0) {
				amountWon += spotArray[EVEN].returnWinnings();
			}
			else {
				amountWon += spotArray[ODD].returnWinnings();
			}
			
			if(winNum <= 12)
				amountWon += spotArray[FIRST_THIRD].returnWinnings();
			else if(winNum <= 24)
				amountWon += spotArray[SECOND_THIRD].returnWinnings();
			else
				amountWon += spotArray[THIRD_THIRD].returnWinnings();
		}
		
		amountWon += spotArray[winNum].returnWinnings();	
		totalWon += amountWon;
		return amountWon;
	}
}




/*Anastasia Glyantseva
 */
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;


public class RouletteGameGUI extends JFrame {

	public JLabel label = new JLabel("");
	JButton[] buttons = new JButton[45];
	public JLabel winoutput = new JLabel("");
	public JLabel winoutput2 = new JLabel("");
	public JLabel winoutput3 = new JLabel("");
	public JLabel winoutput4 = new JLabel("");

	public RouletteGameGUI(){
		super("Roulette Game");
		table = new RouletteTable();
		wheel = new RouletteWheel();
		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));
		for(int i = 0; i < 37; i++){
			buttons[i] = new JButton(Integer.toString(i));
			if(i % 2 == 0){
				buttons[i].setBackground(Color.LIGHT_GRAY);
			}
		}
		buttons[37] = new JButton("Odd");
		buttons[38] = new JButton("Even");
		buttons[39] = new JButton("Red");
		buttons[39].setBackground(Color.RED);
		buttons[40] = new JButton("Black");
		buttons[40].setBackground(Color.BLACK);
		buttons[40].setForeground(Color.WHITE);
		buttons[41] = new JButton("First 1/3 (1-12)");
		buttons[42] = new JButton("Second 1/3 (13-24)");
		buttons[43] = new JButton("Third 1/3 (25-36)");

		buttonClicked click = new buttonClicked(this);

		for(int i = 0; i < 44; i++)	{
			add(buttons[i]);
			buttons[i].addActionListener(click);
		}
		add(winoutput);
		add(winoutput2);
		add(winoutput3);
		add(winoutput4);
		buttons[44] = new JButton("Spin the Wheel!");
		buttons[44].setBackground(Color.YELLOW);
		add(buttons[44], BorderLayout.SOUTH);
		buttons[44].addActionListener(click);
		setVisible(true);
	}
	
	class betdialog extends JDialog{
		double result;
		JTextField betinput;
		betdialog(JFrame frame,String title,boolean yesno)	{
		  super(frame,title,yesno);
		  
		  setLayout(new FlowLayout());
		  add(new Label("Please enter a bet amount: "));
		  add(betinput = new JTextField(10));
		  add(new Button("Bet"));
		  add(new Button("Cancel"));
		
		}
		public boolean action(Event evt, Object whatAction)
		{
		   if (evt.target instanceof Button)
		     {
		      String buttonLabel = (String) whatAction;
		      if (buttonLabel == "Bet")
		       {
		    	  try{
		    		  result = Double.parseDouble(betinput.getText());
		    	  } catch(Exception e){
		    		  JOptionPane.showMessageDialog(null, "Please enter a valid bet!");
		    		  return false;
		    	  }
		       }
		      if (buttonLabel == "Cancel")
		       {
		    	  result = -1;
		       }
		      	hide();        
				return true;
		     }
		    return false;
		} 
		
		public double getValue(){
			return result;
		}
	}
	
	class buttonClicked implements ActionListener{
			betdialog dialog;
			JFrame parent;
			public buttonClicked(JFrame theParent){
				parent = theParent; 
				dialog = new betdialog(parent,"Place Your Bets",true);
				dialog.setSize(200,100);
				dialog.setLocationRelativeTo(null);
			}
			public void actionPerformed(ActionEvent click){
				JButton whatbutton = (JButton)click.getSource();
				String what = whatbutton.getText();
				if(what != "Spin the Wheel!"){
					whatbutton.setBackground(Color.CYAN);
				}
				if(what == "Spin the Wheel!"){
					//reset the button colors
					for(int i = 0; i < 37; i++){
						if(i % 2 == 0){
							buttons[i].setBackground(Color.LIGHT_GRAY);
						} else {
							buttons[i].setBackground(null);
						}
					}
					for(int i = 37; i < 44; i++){
						buttons[i].setBackground(null);
					}
					buttons[39].setBackground(Color.RED);
					buttons[40].setBackground(Color.BLACK);
					buttons[40].setForeground(Color.WHITE);
					
					playGame();
				}else {
					//clear previous info, but leave total winnings
					winoutput.setText("");
					winoutput2.setText("");
					winoutput3.setText("");
					dialog.show();
				  	double bet = dialog.getValue();
					table.assignBet(bet, getChoice(what));
		
				}

		}

	}

	public static void main(String[] args){
		RouletteGameGUI frame = new RouletteGameGUI();
		frame.initGame();
		frame.setSize(575,430);
		frame.setLocationRelativeTo(null);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
	
	private int winNum;
	private RouletteTable table;
	private RouletteWheel wheel;
	
	
	public void initGame(){
		table.initializeBet();
		winNum = 0;
	}
	
	public void playGame(){
		
		wheel.spinTheWheel();
		winNum = wheel.getTheNumber();
		displayResults();
		table.clearBets();
	}
	
	public void displayResults(){
		winoutput.setText("The winning number = " + winNum);
		winoutput2.setText("The color is " + wheel.getTheColor());
		winoutput3.setText(String.format("You won $%.2f\n", table.calcWinnings(winNum,wheel.getTheColor())));
		winoutput4.setText(String.format("Total winnings so far $%.2f\n", table.totalWon)); 
	}
	public String getChoice(String buttonName){
	
		String options = "OERBFST";
		if(options.indexOf(buttonName.charAt(0)) > -1)
			return buttonName.substring(0, 1);
		else
			return buttonName;
	}

}
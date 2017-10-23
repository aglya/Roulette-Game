
public class RouletteSpot {

	private double payoff = 1.0;
	private double userBet;
	
	
	public RouletteSpot(double payoff){
		this.payoff = payoff;
		userBet = 0;
		
	}
	public double getUserBet(){
		return userBet;
	}
	public double getPayOff(){
		return payoff;
	}
	public void setBet(double userBet) {
		    if (userBet >= 0) {
		       this.userBet = userBet; 
		    }
		}
	
	public void clearBet() {
	    
	      this.userBet = 0; 
	    
	}


	public double returnWinnings(){
		
		return payoff*userBet + userBet;
	}
	
}

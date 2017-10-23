
public class RouletteWheel {
	
	
	private int chosen;
	enum Colors {green, red,
 	   black};
    private static Colors[] color = { Colors.green, Colors.red, Colors.black, Colors.red, Colors.black, 
    							Colors.red, Colors.black, Colors.red ,Colors.black, Colors.red,
    							Colors.black, Colors.red, Colors.black, Colors.red, Colors.black,
    							Colors.red, Colors.black, Colors.red, Colors.black, Colors.red,
    							Colors.black, Colors.red, Colors.black, Colors.red, Colors.black,
    							Colors.red, Colors.black, Colors.red, Colors.black, Colors.red,
    							Colors.black, Colors.red, Colors.black, Colors.red, Colors.black,
    							Colors.red, Colors.black};
    public final void spinTheWheel(){
    	chosen = ((int)(Math.random() * (36 - 0) + 1)); 
    }
    
    public final int getTheNumber(){
    	return chosen;
    }
    public final Colors getTheColor(){
    	return color[chosen];
    }
     
}


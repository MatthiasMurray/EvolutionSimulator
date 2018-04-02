package evolution.simular.v2;

import java.util.ArrayList;
import java.awt.Color;

public class DataOfSquare {

	
	//ArrayList that'll contain the colors
	
	 
	SquarePanel square;
	public DataOfSquare(Color inputCol){
		
	
		square = new SquarePanel(inputCol);
	}
	public void lightMeUp(Color inputCol){
		square.ChangeColor(inputCol);
	}
}

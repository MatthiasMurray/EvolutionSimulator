/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evolution.simular.v2;

import java.util.ArrayList;

/**
 *
 * 
 */
public class Land {
        
        private int food;
        private int locationX;
        private int locationY;
        private ArrayList<Land> landArray = new ArrayList<>();

    public Land (int foodInput, int locX, int locY) {
        this.food=foodInput;
        this.locationX=locX;
        this.locationY=locY;
        
    }
    public int getLandFood(){
        return food;
    }
    
    public int getLandX(){
        return locationX;
}
    public int getLandY(){
        return locationY;
    }
    
    public int generateRandomFood(){
   
    return food = (int) (Math.random() * 10)+1; //generates a food value between 1 and 10
    }
    
    public ArrayList generateLand(int widthInput, int heightInput ){
       int width = widthInput;
       int height = heightInput;
          
            for (int widthCounter=0; widthCounter <= width; widthCounter++){
                for (int heightCounter = 0; heightCounter <= height; heightCounter++){ // Should add (0,0->10), Then (1,0->10)...
                    if (heightCounter <=  height) {
                    Tuple landLocation = new Tuple(widthCounter, heightCounter);
                    landArray.add(new Land(generateRandomFood(),landLocation.getX(), landLocation.getY()));
                    
                }
                    else {
                        break;
                    }
    
                }
    }
        return landArray;
}
    
}
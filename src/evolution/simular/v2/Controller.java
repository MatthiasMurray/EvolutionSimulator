/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evolution.simular.v2;

import static evolution.simular.v2.Window.Grid;
import java.awt.Color;
import static java.lang.Math.round;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import static javafx.scene.paint.Color.color;
import javax.swing.JOptionPane;


/**
 *
 * 
 */
public class Controller extends Thread{
//Animal Stuff
public static ArrayList<Color> animalColors = new ArrayList<>();
public static ArrayList<Animal> animalArray = new ArrayList<>();
private ArrayList<Animal> checkAnimalArray = new ArrayList<>();
public static int counter = 0;
 
private Color white = new Color(255,255,255); 
//Land Stuff
public static ArrayList<Land> landArray = new ArrayList<>();
private int width= 10;
private int height =10;

//Create window in driver class
ArrayList<ArrayList<DataOfSquare>> Squares= new ArrayList<ArrayList<DataOfSquare>>();
    Controller(){
        Squares = Window.Grid;
    }
    
    public int genRando1to1(){
	int testing;
        testing = (int) round(Math.random());
	int output = 0;
	if (testing == 1){
		output = 1;
	}
	else if (testing ==0){
		output = 0;
	}
	
	
	return output;
}
    public int genRando1to10(){
  
    int food = (int) (Math.random() * 10)+1; //generates a food value between 1 and 10
    return food;
    }
    
    public int genRando1to100(){ //generator used for: health, attack, energy growth
        return (int) (Math.random() * 100)+1;
    }
    
    public ArrayList randoNoneSameNumber0to9(int number){
      ArrayList<Integer> Xs = new ArrayList<>();
      
      ArrayList<Integer> newXs = new ArrayList<>();
      
        for (int i=0; i<number-1; i++) {
            Xs.add(i);
            }
        Collections.shuffle(Xs);
        return Xs;
    }
    
    //Animalarray Generator
    public void generateAnimals(int number){
            
            
            ArrayList <Integer> LocationX = new ArrayList<>(randoNoneSameNumber0to9(number+1));
            ArrayList <Integer> LocationY = new ArrayList<>(randoNoneSameNumber0to9(number+1));
            int [] Xs;
            int [] Ys;
            for (int i=0; i<number; i++){
              animalArray.add(new Animal(LocationX.get(i),LocationY.get(i),genRando1to100(),genRando1to100(),genRando1to100(),genRando1to100(),i));
              //System.out.println(animalArray.get(i).getHealth());
            }
    }
    
    //Makes animal Colors
    public void makeAnimalColors(ArrayList<Animal> animalArray){
       // for (Animal animalArray1 : animalArray ){
         for (int count =0; count<animalArray.size(); count++){   
            int R = (int) (Math.random( )*256);
            int G = (int)(Math.random( )*256);
            int B= (int)(Math.random( )*256);
            
            Color randomColor = new Color(R, G, B);
            animalColors.add(randomColor);
        }   
    
    }
    
    //LandGenerator
    public void generateLand(){
       //int width = widthInput;
       //int height = heightInput;
          
            for (int widthCounter=0; widthCounter <= width; widthCounter++){
                for (int heightCounter = 0; heightCounter <= height; heightCounter++){ // Should add (0,0->10), Then (1,0->10)...
                    if (heightCounter <=  height) {
                    Tuple landLocation = new Tuple(widthCounter, heightCounter);
                    landArray.add(new Land(genRando1to10()+100,landLocation.getX(), landLocation.getY()));
                    
                }
                    else {
                        break;
                    }
                    }
                }
        
    } 
    //Let the Games begin
    public void makeEnvironment(){
        generateLand();
        generateAnimals(10);
        makeAnimalColors(animalArray);
        
    }
    
    public void makeInitialBoard(){
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
            for (int counter =0;counter<=animalArray.size();counter++){
                for (Animal animal:animalArray){
                if (counter<animalArray.size() && animalArray.get(counter) == animal && animalArray.get(counter).getLocationX() == i && animalArray.get(counter).getLocationY() == j){
                            int x = animal.getLocationX();
                            int y = animal.getLocationY();
                    Squares.get(x).get(y).lightMeUp(animalColors.get(animal.getIdentity()));
                    //animalCounter ++;
                    //System.out.println("Animal " +animalCounter + "   Position in array: "+ counter+ "   Xpos: " + animalArray.get(counter).getLocationX() + "    YPos: " + animalArray.get(counter).getLocationY());
                    
                    break;
                    
                    }
               
                }
                
                }
           
        }
        
     
    
    }
    }
    //Animal Fighting methods
    public void fight (Animal attacker, Animal defender){
        //System.out.println(defender.getHealth());
        defender.changeHealth(attacker.getAttack());
        //System.out.println(defender.getHealth());
    }
    
    public void animalDeath(ArrayList<Animal> animalArray){
        ArrayList<Animal> livingAnimals = new ArrayList();
        //System.out.println(livingAnimals.size());
        //System.out.println(animalArray.size());
        for (Animal animal1: animalArray){
            if (animal1.getHealth()>0 && !anySameLocation(animal1,animalArray)){
               livingAnimals.add(animal1);
               
            }
            //else if(animalArray.contains())
            else{
			   int x = animal1.getLocationX();
			   int y = animal1.getLocationY();
			   Squares.get(x).get(y).lightMeUp(white); //if the animal dies it changes its color to white
            }
            
        }
        //System.out.println(livingAnimals.size());
        animalArray.clear();
        animalArray.addAll(livingAnimals);//Changes the current animal array to only include those currently alive
        livingAnimals.clear();
    }
    /*
    public void removeDoubles(){
        ArrayList<Animal> doubleAnimals = new ArrayList();
        for(Animal animal1: animalArray){
            if(!anySameLocation(animal1,animalArray)){
                doubleAnimals.add(animal1);
                System.out.println("test");
            }
        }
        for(Animal animal1: doubleAnimals){
            animalArray.remove(animal1);
        }
    }
    */
    
    public boolean adjacent(Animal animal1, Animal animal2){
        boolean areAdjacent = false;
        //outerloop:
        if (animal1.getLocationX() == animal2.getLocationX()+1){
            if (animal1.getLocationY() == animal2.getLocationY()){
                areAdjacent=true;
                //break outerloop;
            }
            else if (animal1.getLocationY() == animal2.getLocationY()+1){
                areAdjacent=true;
                //break outerloop;
            }
            else if (animal1.getLocationY() == animal2.getLocationY()-1){
                areAdjacent=true;
                //break outerloop;    
            }
        }
        outerloop:
        if (animal1.getLocationX() == animal2.getLocationX()){
            if (animal1.getLocationY() == animal2.getLocationY()+1){
                areAdjacent=true;
                //break outerloop; 
            }
            else if (animal1.getLocationY() == animal2.getLocationY()){
                areAdjacent=true;
                //break outerloop; 
            }
            else if (animal1.getLocationY() == animal2.getLocationY()-1){
                areAdjacent=true;
                //break outerloop; 
            }
            
        }
        outerloop:
        if (animal1.getLocationX() == animal2.getLocationX()-1){
            if (animal1.getLocationY() == animal2.getLocationY()){
                areAdjacent=true;
                //break outerloop;
            }
            else if (animal1.getLocationY() == animal2.getLocationY()+1){
                areAdjacent=true;
                //break outerloop;
            }
            else if (animal1.getLocationY() == animal2.getLocationY()-1){
                areAdjacent=true;
                //break outerloop;    
            }
        
        }
    //System.out.println(areAdjacent);
    return areAdjacent;
    }
    
    public void fightCycle(){
                for (Animal preAnimal: animalArray){    
                    for (Animal animal1: animalArray){
                        if (animal1.getIdentity() != preAnimal.getIdentity()){
                            if (adjacent(preAnimal,animal1)){
                                fight(preAnimal, animal1);
                            }
                        }
                    }
                }
                
           
    animalDeath(animalArray);
    
    
}
    public boolean anySameLocation(Animal animalToCheck, ArrayList<Animal> currentAnimals){
        for(Animal testAnimal:currentAnimals){
            if(animalToCheck.getLocationX() == testAnimal.getLocationX() && animalToCheck.getLocationY() == testAnimal.getLocationY() && animalToCheck != testAnimal){
                //System.out.println("test");
                return true;
            }
        }
        return false;
    }
    public boolean anyDifferentAdjacent(Animal inputAnimal){
        boolean someAdjacent = false;
        for (Animal otherAnimal: animalArray){
            if (inputAnimal.getIdentity() != otherAnimal.getIdentity()){
                
                if(adjacent(inputAnimal,otherAnimal)){
                    //System.out.println("test");
                    //System.out.println("TEST");
                    someAdjacent = true;
                    //System.out.println(someAdjacent);
                    return someAdjacent;
                    
                    
                }
                
            }
            
        }
    //System.out.println(someAdjacent);
    return someAdjacent;
    
    }
    public boolean anySameAdjacent(Animal inputAnimal){
        boolean someAdjacent = false;
        for (Animal otherAnimal: animalArray){
            if(inputAnimal.getIdentity() == otherAnimal.getIdentity() && !inputAnimal.equals(otherAnimal)){
               if(adjacent(inputAnimal,otherAnimal)){
                    //System.out.println("test");
                    //System.out.println("TEST");
                    someAdjacent = true;
                    //System.out.println(someAdjacent);
                    return someAdjacent; 
                }
            }
            
            
            
        }
    //System.out.println(someAdjacent);
    return someAdjacent;
    
    }
    public Tuple possibleGrowLocation(Animal inputAnimal){
        ArrayList<Tuple> currentAnimalLocations = new ArrayList<>();
        ArrayList<Tuple> possibleLocation = new ArrayList<>();
        ArrayList<Tuple> allLocation = new ArrayList<>();
        ArrayList<Tuple> finalLocations = new ArrayList<>();
        for(int i=0;i<10;i++){
            for (int j=0; j<10;j++){
                allLocation.add(new Tuple(i,j));
            }
        }
        
        for (Tuple Tuple1: allLocation){
            for(Animal animal:animalArray){
                if(Tuple1.getX()== animal.getLocationX() && Tuple1.getY()== animal.getLocationY()){
                    currentAnimalLocations.add(Tuple1);
                }
            }
        }
        
        
        for (Tuple Tuple1: allLocation){
        if(inputAnimal.getLocationX()+1<=9 && inputAnimal.getLocationX()>=0){
            if(Tuple1.getX()==inputAnimal.getLocationX()+1 && Tuple1.getY()==inputAnimal.getLocationY()){
                    possibleLocation.add(Tuple1);
            }
            if(inputAnimal.getLocationY()+1<=9 && inputAnimal.getLocationY()>=0){
                if(Tuple1.getX()==inputAnimal.getLocationX()+1 && Tuple1.getY()==inputAnimal.getLocationY()+1){
                    possibleLocation.add(Tuple1);
                }
            }
            
            if(inputAnimal.getLocationY()-1 >=0 && inputAnimal.getLocationY()<=9){
                
                if(Tuple1.getX()==inputAnimal.getLocationX()+1 && Tuple1.getY()==inputAnimal.getLocationY()-1){
                    possibleLocation.add(Tuple1);
                }
            }
            }
        
        if(inputAnimal.getLocationX()<=9 && inputAnimal.getLocationX()>=0){
            if(inputAnimal.getLocationY()+1 <=9 && inputAnimal.getLocationY()>=0){
                if(Tuple1.getX()==inputAnimal.getLocationX() && Tuple1.getY()==inputAnimal.getLocationY()+1){
                    possibleLocation.add(Tuple1);
                }
            }
            if(inputAnimal.getLocationY()-1 >=0 && inputAnimal.getLocationY()<=9){
                if(Tuple1.getX()==inputAnimal.getLocationX() && Tuple1.getY()==inputAnimal.getLocationY()-1){
                    possibleLocation.add(Tuple1);
                }
            }
        }
        
        if(inputAnimal.getLocationX()-1>=0 && inputAnimal.getLocationX()<=9){
            
            if(Tuple1.getX()==inputAnimal.getLocationX()-1 && Tuple1.getY()==inputAnimal.getLocationY()){
                    possibleLocation.add(Tuple1);
                }
            if(inputAnimal.getLocationY()+1<=9 && inputAnimal.getLocationY()>=0){
                if(Tuple1.getX()==inputAnimal.getLocationX()-1 && Tuple1.getY()==inputAnimal.getLocationY()+1){
                    possibleLocation.add(Tuple1);
                }    
            }
            if(inputAnimal.getLocationY()-1 >=0 && inputAnimal.getLocationY()<=9){
                //possibleLocation.add(new Tuple(inputAnimal.getLocationX()-1, inputAnimal.getLocationY()-1));
                if(Tuple1.getX()==inputAnimal.getLocationX()-1 && Tuple1.getY()==inputAnimal.getLocationY()-1){
                    possibleLocation.add(Tuple1);
                }    
            }
        }
        }
        
        for(Tuple Tuple1: possibleLocation){
            if(!currentAnimalLocations.contains(Tuple1)){
                finalLocations.add(Tuple1);
            }   
        }
        /*
        for(Tuple Tuple1:finalLocations){
            System.out.println(Tuple1.getX());
            System.out.println(Tuple1.getY());
            System.out.println("-------------");
        }
        */
    //Add random selection from finalLocations here
    //System.out.println(finalLocations.size());
    if(finalLocations.size()>0){
    double locate =  Math.random()*finalLocations.size();
    int locator = (int)locate;
    //System.out.println(finalLocations.get(locator).getX());
    //System.out.println(finalLocations.get(locator).getY());
    //System.out.println(possibleGrowLocation(animal1).getY());
    /*
    System.out.println("********************");
    System.out.println(finalLocations.get(locator).getX());
    System.out.println(finalLocations.get(locator).getY());
    System.out.println("********************");
    */
    return finalLocations.get(locator);
    }
    else {
        Tuple Tuple1 = new Tuple(11,11);
        //System.out.println(Tuple1.getX());
        //System.out.println(Tuple1.getY());
        return Tuple1;
    }
    //Add a way to pull x and y values into the grow() from the tuple
    }
    
    
    
    public void grow(){
	ArrayList<Animal> bornAnimals = new ArrayList();
            //if (animalArray.size()<100){        
            for (Animal animal1: animalArray){
                        
                            if (!anyDifferentAdjacent(animal1)){
                                if (animal1.getEnergy()<animal1.getCurrentEnergy()){
                                    
                                    //newX = animal1.getLocationX()+genRando1to1();
                                    //newY = animal1.getLocationY()+genRando1to1();
                                    //System.out.println(newX);
                                    //create new method top generate new locations
                                    int x = possibleGrowLocation(animal1).getX();
                                    int y = possibleGrowLocation(animal1).getY();
                                    if (x != 11){
                                        Animal bornAnimal = new Animal(x, y, animal1.getOriginalHealth()+5, animal1.getAttack()+genRando1to10(), animal1.getGrowth()+1, animal1.getEnergy()+genRando1to1(), animal1.getIdentity());
                                        if(bornAnimals.size()==0){ 
                                            bornAnimals.add(bornAnimal);
                                            Squares.get(x).get(y).lightMeUp(animalColors.get(animal1.getIdentity()));
                                            animal1.removeCurrentEnergy(animal1.getEnergy());
                                        }
                                        else if(!anySameLocation(bornAnimal,bornAnimals)){
                                            bornAnimals.add(bornAnimal);
                                            Squares.get(x).get(y).lightMeUp(animalColors.get(animal1.getIdentity()));
                                            animal1.removeCurrentEnergy(animal1.getEnergy());
                                        }
                                    }  
                                    
                              
                                }
                                
                                
                                
                            }
                           
            }
            //}
                        
                    
                
           
        //System.out.println(bornAnimals.size());
	
        animalArray.addAll(bornAnimals);
        bornAnimals.clear();
        
        
    //}
    }
    
    public void eat(){
	for(Land land1:landArray){
		for (Animal animal1:animalArray){
			if (land1.getLandX() == animal1.getLocationX() && land1.getLandY() == animal1.getLocationY()){
				if (animal1.getEnergy()>animal1.getCurrentEnergy()){
				animal1.addCurrentEnergy(land1.getLandFood());
			 	}
			}
	     }
	}
    
}
    public void growthCycle(){
        eat();
        grow();
        //System.out.println("test");
}
    
    public void pauser(){
        try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
				e.printStackTrace();
		 }

    }
    
    
    public void run(){
        makeEnvironment();
        makeInitialBoard();
        Test test1 = new Test();
        //test1.printAnimal(animalArray);
        pauser();
        int x = 0;
        
        int i = 1;
        winningloop:
        while (true){
            //System.out.println(animalArray.size());
            
            //System.out.println(animalArray.size());
            fightCycle();
            //System.out.println("Post Fight: " + animalArray.size());
            //System.out.println(animalArray.size());
            pauser();
            //System.out.println(animalArray.size());
            growthCycle();
            pauser();
            //removeDoubles();
            //System.out.println("Post Grow: " + animalArray.size());
            //test1.printAnimal(animalArray);
            //System.out.println("_____________________");
            //System.out.println(counter);
            //System.out.println("_____________________");
            if(animalArray.size()==100){
                int counter =0;
                
                for (Animal animal1: animalArray){
                    for (Animal animal2: animalArray){
                        if(animal1.getIdentity()==animal2.getIdentity()){
                            counter += 1;
                            //System.out.println(counter);
                            if(counter==10000){
                                JOptionPane.showMessageDialog(null, "WINNER!\n" + "Animal #" +animalArray.get(99).getIdentity() + "\nHealth: " +animalArray.get(99).getHealth() + "\nAttack: " +animalArray.get(99).getAttack() + "\n Growth: " +animalArray.get(99).getGrowth() + "\n Energy: " +animalArray.get(99).getEnergy());
                                
                                break winningloop;
                            }
                        }
                    }
                }
               
                
            }
            //pauser();
            i++;
            
            
        }
                
    }
}




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evolution.simular.v2;

/**
 *
 *
 */
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
public class Animal {
    private int health;
    private int originalHealth;
    private int attack;
    private int growth;
    private int energy;
    private int identity;
    private int locationX;
    private int locationY;
    private int currentEnergy;
    private ArrayList<Color> animalColors = new ArrayList<>();
    
    
    private ArrayList<Animal> animalArray = new ArrayList<>();
    private ArrayList<Animal> checkAnimalArray = new ArrayList<>();
    

    public Animal (int locX, int locY, int healthInput, int attackInput, int growthInput, int energyInput, int identityInput){
        this.locationX = locX;
        this.locationY = locY;
        this.health = healthInput;
        this.attack = attackInput;
        this.growth = growthInput;
        this.energy = energyInput;
        this.identity = identityInput;
        this.currentEnergy=0;
        this.originalHealth = this.health;
        
    }
    
    public int getHealth(){
        return health;
        
    }
    public int getOriginalHealth(){
        return originalHealth;
        
    }
    public void changeHealth(int reduceBy){
        
        this.health -= reduceBy;
    }
   
    public void addCurrentEnergy(int eat){
        this.currentEnergy += eat;
    }
    public void removeCurrentEnergy(int eat){
        this.currentEnergy -= eat;
    }
    public int getCurrentEnergy(){
        return currentEnergy;
    }
    public int getAttack(){
        return attack;
    }
    public int getGrowth(){
        return growth;
    }
    public int getEnergy(){
        return energy;
    }
    public int getIdentity(){
        return identity;
    }
    public int getLocationX(){
        return locationX;
    }
    public int getLocationY(){
        return locationY;
    }
    public int genRando1to100(){ //generator used for: health, attack, energy growth
        return (int) (Math.random() * 100)+1;
    }
    public int genRando1to10(){
        return (int) (Math.random() * 8)+1;
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
       
    
   
    public ArrayList generateAnimals(int number){
            ArrayList<Animal> animalArray = new ArrayList<>();
            
            ArrayList <Integer> LocationX = new ArrayList<>(randoNoneSameNumber0to9(number+1));
            ArrayList <Integer> LocationY = new ArrayList<>(randoNoneSameNumber0to9(number+1));
            int [] Xs;
            int [] Ys;
            for (int i=0; i<number; i++){
              animalArray.add(new Animal(LocationX.get(i),LocationY.get(i),genRando1to100(),genRando1to100(),genRando1to100(),genRando1to100(),i));
            }
            
        
        return animalArray;
    }
    
                 
                    
                
            
        
    
        
    

  
                
        
    
    public ArrayList makeAnimalColors(ArrayList<Animal> animalArray){
       // for (Animal animalArray1 : animalArray ){
         for (int count =0; count<animalArray.size(); count++){   
            int R = (int) (Math.random( )*256);
            int G = (int)(Math.random( )*256);
            int B= (int)(Math.random( )*256);
            
            Color randomColor = new Color(R, G, B);
            animalColors.add(randomColor);
        }
    return animalColors;
}
    public void fight (Animal attacker, Animal defender){
        defender.changeHealth(attacker.getAttack());
    }
    public ArrayList animalDeath(ArrayList<Animal> animalArray){
        ArrayList<Animal> livingAnimals = new ArrayList();
        for (Animal animal1: animalArray){
            if (animal1.getHealth()>0){
               livingAnimals.add(animal1);
               //.out.println(livingAnimals.get(0));
            }
            
            
        }
        return livingAnimals;
        //System.out.println(livingAnimals.get());
    }
    public boolean adjacent(Animal animal1, Animal animal2){
        boolean areAdjacent = false;
        outerloop:
        if (animal1.getLocationX() == animal2.getLocationX()+1){
            if (animal1.getLocationY() == animal2.getLocationY()){
                areAdjacent=true;
                break outerloop;
            }
            else if (animal1.getLocationY() == animal2.getLocationY()+1){
                areAdjacent=true;
                break outerloop;
            }
            else if (animal1.getLocationY() == animal2.getLocationY()-1){
                areAdjacent=true;
                break outerloop;    
            }
        }
        outerloop:
        if (animal1.getLocationX() == animal2.getLocationX()){
            if (animal1.getLocationY() == animal2.getLocationY()+1){
                areAdjacent=true;
                break outerloop; 
            }
            else if (animal1.getLocationY() == animal2.getLocationY()-1){
                areAdjacent=true;
                break outerloop; 
            }
            
        }
        outerloop:
        if (animal1.getLocationX() == animal2.getLocationX()-1){
            if (animal1.getLocationY() == animal2.getLocationY()){
                areAdjacent=true;
                break outerloop;
            }
            else if (animal1.getLocationY() == animal2.getLocationY()+1){
                areAdjacent=true;
                break outerloop;
            }
            else if (animal1.getLocationY() == animal2.getLocationY()-1){
                areAdjacent=true;
                break outerloop;    
            }
        
        }    
    return areAdjacent;
    }
    public ArrayList fightCycle(int width, int height, ArrayList<Animal> animalArray){
        int counter =-1;
        
        for (int i=0; i<width; i++){
            
            
            if (counter<animalArray.size()){
                counter +=1;
            } 
            for (int j=0; j<height; j++){
                for (Animal anAnimal: animalArray){
                    if (anAnimal != animalArray.get(counter)){
                        if (adjacent(animalArray.get(counter),anAnimal)){
                            fight(animalArray.get(counter), anAnimal);
                        }
                    }
                }
            }
        
    
    }
    return animalDeath(animalArray);
}
}



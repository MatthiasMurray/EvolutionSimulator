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

import java.util.ArrayList;
public class Test {    //**NOTE TO SELF** These will have to be removed, and the tests will have to be made to have an input of a general array
Land newLand = new Land(0,0,0);
//Animal newAnimal = new Animal(0,0,0,0,0,0,0);
public ArrayList<Land> landArray = new ArrayList<>(newLand.generateLand(10,5));

//public ArrayList<Animal> animalArray = new ArrayList<>(newAnimal.generateAnimals(10));

public void printLand(){
    
    for (Land landArray1 : landArray ) {
                System.out.println(landArray1.getLandFood());
                System.out.println(landArray1.getLandX());
                System.out.println(landArray1.getLandY());
                System.out.println("");
    }
}
public void printAnimal(ArrayList<Animal> animalArray){           
        /*animalArray.get(0).addLocationXs(7);  //Test to see if unique elements can be added.... Tests show they can
        animalArray.get(0).addLocationYs(2);*/
    //int counter =0;
    System.out.println("__________________________________");
    for (Animal newAnimal1: animalArray){
        for (Animal newAnimal2:animalArray){
            if (newAnimal1.getLocationX() == newAnimal2.getLocationX() && newAnimal1.getLocationY() == newAnimal2.getLocationY() && newAnimal1 != newAnimal2){
        
        System.out.println("Animal #" +newAnimal1.getIdentity());
        System.out.println("");
        System.out.println("Initial X Location: " + newAnimal1.getLocationX());
        System.out.println("Initial Y Location: " +newAnimal1.getLocationY());
        System.out.println("Health: " +newAnimal1.getHealth());
        System.out.println("Attack: " +newAnimal1.getAttack());
        System.out.println("Growth: " +newAnimal1.getGrowth());
        System.out.println("Energy: " +newAnimal1.getEnergy());
        System.out.println("Identity: " +newAnimal1.getIdentity());
        System.out.println("");
        System.out.println("__________________________________");
        System.out.println("Animal #" +newAnimal2.getIdentity());
        System.out.println("");
        System.out.println("Initial X Location: " + newAnimal2.getLocationX());
        System.out.println("Initial Y Location: " +newAnimal2.getLocationY());
        System.out.println("Health: " +newAnimal2.getHealth());
        System.out.println("Attack: " +newAnimal2.getAttack());
        System.out.println("Growth: " +newAnimal2.getGrowth());
        System.out.println("Energy: " +newAnimal2.getEnergy());
        System.out.println("Identity: " +newAnimal2.getIdentity());
        System.out.println("");
        System.out.println("__________________________________");
        Controller.counter +=1;
        /*                              //Test for adding more location
        newAnimal1.addLocationXs(2);
        newAnimal1.addLocationYs(2);
        newAnimal1.addLocationXs(3);
        newAnimal1.addLocationYs(1);
        newAnimal1.addLocationXs(4);
        newAnimal1.addLocationYs(1);
                */
        
    }
        }
    }
}
}
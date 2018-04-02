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
import java.awt.GridLayout;

import java.util.ArrayList;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Container;
class Window extends JFrame  {
 private static final long serialVersionUID = -2542001418764869760L;
	public static ArrayList<ArrayList<DataOfSquare>> Grid;
	public static int width = 10;
	public static int height = 10;
        private Color white = new Color(255,255,255); 
	public Window(){
		
		
		// Creates the arraylist that'll contain the threads
		Grid = new ArrayList<ArrayList<DataOfSquare>>();
		ArrayList<DataOfSquare> data;
		
		// Creates Threads and its data and adds it to the arrayList
		for(int i=0;i<width;i++){
			data= new ArrayList<DataOfSquare>();
			for(int j=0;j<height;j++){
                                   DataOfSquare c = new DataOfSquare(white);
				   data.add(c);
                                 }
                           
			
                        Grid.add(data);
		}
		
		// Setting up the layout of the panel
		getContentPane().setLayout(new GridLayout(10,10,0,0));
		
		// Start & pauses all threads, then adds every square of each thread to the panel
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				getContentPane().add(Grid.get(i).get(j).square);
                                
			}
		}
                Controller c = new Controller();
                c.start();
}
}



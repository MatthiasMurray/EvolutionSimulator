/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evolution.simular.v2;

import javax.swing.JFrame;

/**
 *
 * 
 */
public class EvolutionSimularV2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                
                Window f1= new Window();
		
		//Setting up the window settings
		f1.setTitle("Evolution Simulator");
		f1.setSize(300,300);
		f1.setVisible(true);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
}
}
package ozlympicGames;

import java.awt.EventQueue;

import gui.GuiManager;

public class Ozlympic {
	// The main entry point.
	public static void main(String[] args) {
		// Create the driver to display the UI and open the main menu.

		  EventQueue.invokeLater(new Runnable() {

	            @Override
	            public void run() {
	            	GuiManager gui = new GuiManager();	
	            	
	            	//gui.cardLayout.show(gui.cards, "Current Game Results");
	            }
	        });
		
		//GuiManager gui = new GuiManager();	
		
	}
}

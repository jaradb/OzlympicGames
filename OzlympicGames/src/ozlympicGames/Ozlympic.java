package ozlympicGames;

import java.awt.EventQueue;

import gui.GuiManager;

public class Ozlympic {
	// The main entry point.
	public static void main(String[] args) {
		// Create the driver to display the UI and open the main menu.
		
		
		
		
		//DatabaseLoader dbController = new DatabaseLoader();

		  EventQueue.invokeLater(new Runnable() {

	            @Override
	            public void run() {
	            	GuiManager gui;// = new GuiManager();
	            	
	            	if(DatabaseLoader.TestDBConnectionIsValid())
	            	{
	            		gui = new GuiManager(new DatabaseLoader());
	            	}
	            	else
	            	{
	            		gui = new GuiManager(new FileLoader());
	            	}
	            	
	            	
	            	
	            	//gui.cardLayout.show(gui.cards, "Current Game Results");
	            }
	        });
		
		//GuiManager gui = new GuiManager();	
		
	}
}

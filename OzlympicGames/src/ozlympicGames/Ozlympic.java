package ozlympicGames;

import java.awt.EventQueue;

import gui.GuiManager;

public class Ozlympic {
	// The main entry point.
	public static void main(String[] args) {
		
		// Create the driver to display the UI and open the main menu.
		
		//Adding the creation of the GUI to the event queue is a safety measure for thread-safety.
		//The GUI will run on the AWT event-dispatching thread to prevent thread concurrency issues.
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				GuiManager gui = null;
				
				String databaseStatus = DatabaseLoader.TestDBConnectionIsValid();

				if (databaseStatus.equals("IsValid")) {
					gui = new GuiManager(new DatabaseLoader());
				} else {
					gui = new GuiManager(new FileLoader());
					gui.showMessage(databaseStatus);
				}

			}
		});
	}
}

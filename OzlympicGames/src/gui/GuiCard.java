package gui;

import javax.swing.JPanel;

// The base class that all GUI cards derive from.

@SuppressWarnings("serial")
public abstract class GuiCard extends JPanel {

	// The GUI manager this card is attached to.
	GuiManager guiManager = null;

	//Construct the GUI card and set a reference to the guiManager who is using it
	public GuiCard(GuiManager guiManager) {
		this.guiManager = guiManager;
		setName(getCardName());
	}

	// This method runs when the card is shown
	public void onShowCard() {
		
	}

	// This method runs when the card is replaced by another card
	public void onHideCard() {

	}

	public abstract String getCardName();
}

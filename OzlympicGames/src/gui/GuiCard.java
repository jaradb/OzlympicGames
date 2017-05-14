package gui;

import javax.swing.JPanel;

public abstract class GuiCard extends JPanel {

	//The GUI manager this card is attached to.
	GuiManager guiManager;
	
	public GuiCard(GuiManager guiManager)
	{
		setName(getCardName());
	}
	
	public abstract String getCardName();
}

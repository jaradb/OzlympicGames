package gui;

import javax.swing.JPanel;

public abstract class GuiCard extends JPanel {

	//The GUI manager this card is attached to.
	GuiManager guiManager = null;
	
	public GuiCard(GuiManager guiManager)
	{
		this.guiManager = guiManager;
		setName(getCardName());
	}
	
	public void OnShowCard()
	{
		
	}
	
	public void OnHideCard()
	{
		
	}
	
	public abstract String getCardName();
}

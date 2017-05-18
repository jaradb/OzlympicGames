package gui;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import games.Game;  

public class ChooseGameType  extends GuiCard {

	JList<String> gameListUI;
	
	public ChooseGameType(GuiManager guiManager)
	{
		super(guiManager);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		ArrayList<Game> gameList = guiManager.dataLoader.loadGameList();
		
		String[] gameTitles = new String[gameList.size()];
		
		for(int i = 0; i < gameList.size(); i++)
		{
			gameTitles[i] = gameList.get(i).getGameName();
		}
		
		gameListUI = new JList<String>(gameTitles);
		
		add(gameListUI);

    	JButton newButton = new JButton("Select game");
    	
    	newButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    	newButton.addActionListener(new ActionListener()
    	{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(gameListUI.getSelectedValue());
				
				if(gameListUI.getSelectedValue() != null)
				{
					guiManager.gameSelected = gameList.get(gameListUI.getSelectedIndex());
				}
				else
				{
					JOptionPane.showMessageDialog(null, "You need to choose a game type before continuing!", "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				guiManager.ShowCardByName("Choose Athletes");
			}
    		
    	});
    	
    	add(newButton);
	}
	
	@Override
	public String getCardName() {
		// TODO Auto-generated method stub
		return "Choose Game Type";
	}
	
	@Override
	public void OnShowCard()
	{
		gameListUI.clearSelection();
	}
	
	@Override
	public void OnHideCard()
	{
		
	}
}


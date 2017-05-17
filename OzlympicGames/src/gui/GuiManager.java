package gui;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import games.Game;
import ozlympicGames.DataLoaderInterface;
import persons.*;



public class GuiManager {


	JFrame frame;	//The main window frame
	JPanel cards; 	//a panel that uses CardLayout
	CardLayout cardLayout;
	
	HashMap<String, GuiCard> guiCardMap = new HashMap<String, GuiCard>();
	
	public DataLoaderInterface dataLoader;
	
	Game gameSelected = null;
	ArrayList<Athlete> athletesSelected = null;
	Official officialSelected = null;
	
	public GuiManager(DataLoaderInterface loader)
	{
		dataLoader = loader;

		createGUICards();
	}
	
	public GuiCard GetCardByName(String cardName)
	{
		return guiCardMap.get(cardName);
	}
	
	public void ShowCardByName(String cardName)
	{
		guiCardMap.get(cardName).OnShowCard();
		
		cardLayout.show(cards, cardName);
		
		frame.pack();
	}

	//ArrayList<GuiCard> guiCards = new ArrayList<GuiCard>();
	
	private void createGUICards()
	{
		GuiCard mainMenu = new MainMenu(this);
		GuiCard chooseGameType = new ChooseGameType(this);
		GuiCard chooseAthletes = new ChooseAthletes(this);
		GuiCard chooseOfficial = new ChooseOfficial(this);
		GuiCard gameInProgress = new GameInProgress(this);
		GuiCard currentGameResults = new GameResults(this);
		GuiCard allGameResults = new AllGameResults(this);
		GuiCard athleteResults = new AthleteResults(this);
		
		guiCardMap.put(mainMenu.getCardName(), mainMenu);
		guiCardMap.put(chooseGameType.getCardName(), chooseGameType);
		guiCardMap.put(chooseAthletes.getCardName(), chooseAthletes);
		guiCardMap.put(chooseOfficial.getCardName(), chooseOfficial);
		guiCardMap.put(gameInProgress.getCardName(), gameInProgress);
		guiCardMap.put(currentGameResults.getCardName(), currentGameResults);
		guiCardMap.put(allGameResults.getCardName(), allGameResults);
		guiCardMap.put(athleteResults.getCardName(), athleteResults);
		
		cardLayout = new CardLayout();
		cards = new JPanel(cardLayout);
		
		
		for(GuiCard card : guiCardMap.values()) cards.add(card, card.getName());
		
		
		
        frame = new JFrame("Ozlympic Games");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		frame.getContentPane().add(cards);

        frame.pack();

        frame.setVisible(true);
        
        
        cardLayout.show(cards, mainMenu.getCardName());

	}
	
	public JFrame getFrame()
	{
		return frame;
	}


}

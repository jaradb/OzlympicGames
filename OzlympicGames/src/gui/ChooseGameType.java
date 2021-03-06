package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import games.Game;

//The GUI Card that shows all available game types that can be played. 

@SuppressWarnings("serial")
public class ChooseGameType extends GuiCard {

	JList<String> gameListUI;

	public ChooseGameType(GuiManager guiManager) {
		super(guiManager);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel gameTypeMessage = new JLabel("Choose a game type:");
		gameTypeMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(gameTypeMessage);

		ArrayList<Game> gameList = guiManager.dataLoader.loadGameList();
		String[] gameTitles = new String[gameList.size()];

		for (int i = 0; i < gameList.size(); i++) {
			gameTitles[i] = gameList.get(i).getGameName();
		}

		gameListUI = new JList<String>(gameTitles);
		add(gameListUI);
		JButton newButton = new JButton("Select game");
		newButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		newButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (gameListUI.getSelectedValue() != null) {
					guiManager.gameSelected = gameList.get(gameListUI.getSelectedIndex());
				} else {
					JOptionPane.showMessageDialog(null, "You need to choose a game type before continuing!", "Warning",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				guiManager.showCardByName("Choose Athletes");
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
	public void onShowCard() {
		gameListUI.setVisible(true);
		gameListUI.clearSelection();
	}

	@Override
	public void onHideCard() {
		gameListUI.setVisible(false);
	}
}

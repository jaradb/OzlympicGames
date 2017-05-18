package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;

//The GUI Card that shows the result of the game that was just completed. 

@SuppressWarnings("serial")
public class GameResults extends GuiCard {

	JTextArea results;

	public GameResults(GuiManager guiManager) {
		super(guiManager);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		results = new JTextArea();
		JButton newButton = new JButton("Return to Menu");
		newButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		newButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				guiManager.showCardByName("Main Menu");
			}
		});

		add(results);
		add(newButton);
	}

	@Override
	public String getCardName() {
		// TODO Auto-generated method stub
		return "Current Game Results";
	}

	@Override
	public void onShowCard() {
		results.setVisible(true);
		results.setText(guiManager.gameSelected.getGameResult());
	}

	@Override
	public void onHideCard() {
		results.setVisible(false);
	}
}

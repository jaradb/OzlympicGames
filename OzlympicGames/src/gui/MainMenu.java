package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class MainMenu extends GuiCard {

	public MainMenu(GuiManager guiManager) {
		super(guiManager);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel welcomeMessage = new JLabel("Welcome to the Ozlympic Games!");
		welcomeMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(welcomeMessage);

		JButton startAGameButton = new JButton("Start a game");
		startAGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		startAGameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				guiManager.showCardByName("Choose Game Type");
			}

		});

		JButton viewGameResultsButton = new JButton("View game results");
		viewGameResultsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		viewGameResultsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				guiManager.showCardByName("All Game Results");
			}

		});

		JButton viewAthleteScoresButton = new JButton("View athlete scores");
		viewAthleteScoresButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		viewAthleteScoresButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				guiManager.showCardByName("Athlete Results");
			}

		});

		JButton quitButton = new JButton("Quit");
		quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		quitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}

		});

		// Add the buttons
		add(startAGameButton);
		add(viewGameResultsButton);
		add(viewAthleteScoresButton);
		add(quitButton);
	}

	@Override
	public String getCardName() {
		// TODO Auto-generated method stub
		return "Main Menu";
	}

	@Override
	public void onShowCard() {

	}

	@Override
	public void onHideCard() {

	}

}

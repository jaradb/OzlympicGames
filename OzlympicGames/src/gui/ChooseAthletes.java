package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import exceptions.*;
import games.Game;
import persons.*;

public class ChooseAthletes extends GuiCard {

	JList<String> UIathleteList;

	JLabel gameTypeMessage;

	public ChooseAthletes(GuiManager guiManager) {
		super(guiManager);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		gameTypeMessage = new JLabel();
		gameTypeMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(gameTypeMessage);

		JLabel selectAthletesMessage = new JLabel("Select up to 5 (but not more than 8) athletes:");
		selectAthletesMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(selectAthletesMessage);

		ArrayList<Athlete> allAthletes = new ArrayList<Athlete>();

		allAthletes.addAll(guiManager.dataLoader.getPersonCollection().getPersonsByType(Swimmer.class));
		allAthletes.addAll(guiManager.dataLoader.getPersonCollection().getPersonsByType(Cyclist.class));
		allAthletes.addAll(guiManager.dataLoader.getPersonCollection().getPersonsByType(Sprinter.class));
		allAthletes.addAll(guiManager.dataLoader.getPersonCollection().getPersonsByType(SuperAthlete.class));

		String[] athleteNames = new String[allAthletes.size()];
		String[] athleteIDs = new String[allAthletes.size()];

		for (int i = 0; i < allAthletes.size(); i++) {
			athleteNames[i] = "Name: " + allAthletes.get(i).getName() + " -- Athlete Type: "
					+ allAthletes.get(i).getClass().getSimpleName();
			athleteIDs[i] = allAthletes.get(i).getUniqueID();
		}

		UIathleteList = new JList<String>(athleteNames);

		UIathleteList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		add(UIathleteList);

		JLabel helpMessage1 = new JLabel("Select multiple athletes by holding shift and clicking,");
		helpMessage1.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(helpMessage1);

		JLabel helpMessage2 = new JLabel("or holding control and click multiple individuals.");
		helpMessage2.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(helpMessage2);

		JButton newButton = new JButton("ChooseAthletes");

		newButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		newButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				for (String athleteName : UIathleteList.getSelectedValuesList()) {
					System.out.println(athleteName);
				}

				try {

					if (UIathleteList.getSelectedIndices().length <= Game.MIN_PARTICIPANTS) {

						throw new TooFewAthleteException();

					}

					if (UIathleteList.getSelectedIndices().length > Game.MAX_PARTICIPANTS) {
						throw new GameFullException();
					}
				} catch (GameFullException | TooFewAthleteException e) {
					if (e.getClass() == TooFewAthleteException.class) {
						JOptionPane.showMessageDialog(null, "Not enough athletes selected to compete!", "Warning",
								JOptionPane.WARNING_MESSAGE);

					} else if (e.getClass() == GameFullException.class) {
						JOptionPane.showMessageDialog(null, "Too many athletes selected to compete!", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}

					return;
				}

				guiManager.athletesSelected = new ArrayList<Athlete>();

				try
				{
					for (int i : UIathleteList.getSelectedIndices()) {
						if (allAthletes.get(i).getClass() != SuperAthlete.class) {
							if (allAthletes.get(i).getClass() != guiManager.gameSelected.getAltheteClassTypeForGame()) {
								
								throw new WrongTypeException("Athlete " + allAthletes.get(i).getName() + " can't compete in "
												+ guiManager.gameSelected.getGameName() + "!");
							}
						}
	
						guiManager.athletesSelected.add(allAthletes.get(i));
	
					}
				}
				catch(WrongTypeException e)
				{
					JOptionPane.showMessageDialog(null,
							e.getErrorMessage(),
							"Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}

				guiManager.ShowCardByName("Choose Official");

			}

		});

		add(newButton);
	}

	@Override
	public String getCardName() {
		// TODO Auto-generated method stub
		return "Choose Athletes";
	}

	@Override
	public void OnShowCard() {
		gameTypeMessage.setText("Game type: " + guiManager.gameSelected.getGameName());
		UIathleteList.setVisible(true);
		UIathleteList.clearSelection();
	}

	@Override
	public void OnHideCard() {
		UIathleteList.setVisible(false);
	}
}

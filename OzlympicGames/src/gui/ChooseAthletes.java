package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import persons.*;

public class ChooseAthletes extends GuiCard {

	public ChooseAthletes(GuiManager guiManager)
	{
		super(guiManager);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		

		ArrayList<Athlete> allAthletes = new ArrayList<Athlete>();
		
		allAthletes.addAll(guiManager.dataLoader.getPersonCollection().getPersonsByType(Swimmer.class));
		allAthletes.addAll(guiManager.dataLoader.getPersonCollection().getPersonsByType(Cyclist.class));
		allAthletes.addAll(guiManager.dataLoader.getPersonCollection().getPersonsByType(Sprinter.class));
		allAthletes.addAll(guiManager.dataLoader.getPersonCollection().getPersonsByType(SuperAthlete.class));
		
		String[] athleteNames = new String[allAthletes.size()];
		String[] athleteIDs = new String[allAthletes.size()];
		
		for(int i = 0; i < allAthletes.size(); i++)
		{
			athleteNames[i] = "Name: " + allAthletes.get(i).getName() + " -- Athlete Type: " + allAthletes.get(i).getClass().getSimpleName();
			athleteIDs[i] = allAthletes.get(i).getUniqueID();
		}
		
		
		//String[] woo = {"mr mcgee", "bepis boy", "the best pet", "may it win", "i need more lines"};
		
		JList<String> UIathleteList = new JList<String>(athleteNames);
		
		UIathleteList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		add(UIathleteList);

    	JButton newButton = new JButton("ChooseAthletes");
    	
    	newButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		newButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				for (String athleteName : UIathleteList.getSelectedValuesList()) {
					System.out.println(athleteName);
				}
				
				if(UIathleteList.getSelectedIndices().length < 5)
				{
					JOptionPane.showMessageDialog(null, "Not enough athletes selected to compete!", "Warning", JOptionPane.WARNING_MESSAGE);
					return;
					
				}
				
				if(UIathleteList.getSelectedIndices().length > 8)
				{
					JOptionPane.showMessageDialog(null, "Too many athletes selected to compete!", "Warning", JOptionPane.WARNING_MESSAGE);
					return;
					
				}
				
				guiManager.athletesSelected = new ArrayList<Athlete>();
				
				
				for(int i : UIathleteList.getSelectedIndices())
				{
					if(allAthletes.get(i).getClass() != SuperAthlete.class)
					{
						if(allAthletes.get(i).getClass() != guiManager.gameSelected.getAltheteClassTypeForGame())
						{
							JOptionPane.showMessageDialog(	null, 
															"Athlete " + allAthletes.get(i).getName() + " can't compete in " + guiManager.gameSelected.getGameName() + "!", 
															"Warning", 
															JOptionPane.WARNING_MESSAGE);
							return;
						}
					}
					
					guiManager.athletesSelected.add(allAthletes.get(i));
					
					
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

}

package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import persons.Athlete;
import persons.Official;
import persons.Swimmer;

public class ChooseOfficial extends GuiCard {

	public ChooseOfficial(GuiManager guiManager)
	{
		super(guiManager);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		

		ArrayList<Official> allOfficials = new ArrayList<Official>();
		
		allOfficials.addAll(guiManager.dataLoader.getPersonCollection().getPersonsByType(Official.class));
		
		
		String[] officialNames = new String[allOfficials.size()];
		
		for(int i = 0; i < allOfficials.size(); i++)
		{
			officialNames[i] = "Name: " + allOfficials.get(i).getName() + " -- Official";
		}
		
		JList<String> officialListUI = new JList<String>(officialNames);
		
		add(officialListUI);

    	
    	JButton newButton = new JButton("Start Game");
    	
    	newButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    	newButton.addActionListener(new ActionListener()
    	{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(officialListUI.getSelectedValue());
				
				if(officialListUI.getSelectedValue() != null)
				{
					guiManager.officialSelected = allOfficials.get(officialListUI.getSelectedIndex());
				}
				else
				{
					JOptionPane.showMessageDialog(null, "You need to choose an Official before continuing!", "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				guiManager.ShowCardByName("Game in Progress");
			}
    		
    	});
    	
    	add(newButton);
	}
	
	@Override
	public String getCardName() {
		// TODO Auto-generated method stub
		return "Choose Official";
	}

}

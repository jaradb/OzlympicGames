package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class ChooseAthletes extends GuiCard {

	public ChooseAthletes(GuiManager guiManager)
	{
		super(guiManager);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		String[] woo = {"mr mcgee", "bepis boy", "the best pet", "may it win", "i need more lines"};
		
		JList<String> athleteList = new JList<String>(woo);
		
		athleteList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		add(athleteList);

    	JButton newButton = new JButton("ChooseAthletes");
    	
    	newButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    	newButton.addActionListener(new ActionListener()
    	{

			@Override
			public void actionPerformed(ActionEvent arg0) {

					for(String athleteName : athleteList.getSelectedValuesList())
					{
						System.out.println(athleteName);
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

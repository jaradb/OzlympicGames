package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;

public class ChooseAthletes extends GuiCard {

	public ChooseAthletes(GuiManager guiManager)
	{
		super(guiManager);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    	JButton newButton = new JButton("ChooseAthletes");
    	
    	newButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    	newButton.addActionListener(new ActionListener()
    	{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
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

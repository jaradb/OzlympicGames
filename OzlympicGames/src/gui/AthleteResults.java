package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class AthleteResults extends GuiCard {

	public AthleteResults(GuiManager guiManager)
	{
		super(guiManager);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JTextArea results = new JTextArea();

		
		results.setText("all athlete\n results\n yo");

    	JButton newButton = new JButton("Return to Menu");
    	
    	newButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    	newButton.addActionListener(new ActionListener()
    	{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				guiManager.ShowCardByName("Main Menu");
			}
    		
    	});
    	
    	add(results);
    	add(newButton);
        
	}
	@Override
	public String getCardName() {
		// TODO Auto-generated method stub
		return "Athlete Results";
	}

}
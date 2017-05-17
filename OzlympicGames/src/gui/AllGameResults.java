package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AllGameResults extends GuiCard {

	JTextArea results;
	
	public AllGameResults(GuiManager guiManager)
	{
		super(guiManager);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//setLayout(new GridBagLayout());

		
		results = new JTextArea();

		add(results);
		
		
		
		results.setText(guiManager.dataLoader.loadGameResults());

    	JButton newButton = new JButton("Return to Menu");
    	
    	newButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    	newButton.addActionListener(new ActionListener()
    	{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				guiManager.ShowCardByName("Main Menu");
			}
    		
    	});
    	
    	//add(results);
    	add(newButton);
        
	}
	
	@Override
	public String getCardName() {
		// TODO Auto-generated method stub
		return "All Game Results";
	}
	
	@Override
	public void OnShowCard()
	{
		results.setText(guiManager.dataLoader.loadGameResults());
	}

}

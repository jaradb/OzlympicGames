package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;

public class ChooseOfficial extends GuiCard {

	public ChooseOfficial(GuiManager guiManager)
	{
		super(guiManager);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		
		String[] woo = {"official 1", "official fun"};
		
		JList<String> gameList = new JList<String>(woo);
		
		add(gameList);

    	
    	JButton newButton = new JButton("Start Game");
    	
    	newButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    	newButton.addActionListener(new ActionListener()
    	{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(gameList.getSelectedValue());
				
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

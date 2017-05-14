package gui;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;  

public class ChooseGameType  extends GuiCard {

	public ChooseGameType(GuiManager guiManager)
	{
		super(guiManager);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		String[] woo = {"what", "yeah"};
		
		JList<String> gameList = new JList<String>(woo);
		
		add(gameList);

    	JButton newButton = new JButton("Select game");
    	
    	newButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    	newButton.addActionListener(new ActionListener()
    	{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(gameList.getSelectedValue());
				
				guiManager.ShowCardByName("Choose Athletes");
			}
    		
    	});
    	
    	add(newButton);
	}
	
	@Override
	public String getCardName() {
		// TODO Auto-generated method stub
		return "Choose Game Type";
	}
}

package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class GameInProgress extends GuiCard implements ActionListener, PropertyChangeListener {
	
	Task task;
	JProgressBar athleteProgress;
	
	 class Task extends SwingWorker<Void, Void> {
	        /*
	         * Main task. Executed in background thread.
	         */
	        @Override
	        public Void doInBackground() {
	            int progress = 0;
	            //Initialize progress property.
	            setProgress(0);
	            while (progress < 100) {
	                //Sleep for up to one second.
	                try {
	                    Thread.sleep(250);
	                } catch (InterruptedException ignore) {}
	                //Make random progress.
	                progress += 10;
	                setProgress(Math.min(progress, 100));
	            }
	            return null;
	        }
	 
	        /*
	         * Executed in event dispatching thread
	         */
	        @Override
	        public void done() {
	            //startButton.setEnabled(true);
	            setCursor(null); //turn off the wait cursor
	            //taskOutput.append("Done!\n");
	        }
	    }

	public GameInProgress(GuiManager guiManager)
	{
		super(guiManager);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    	JButton startGameButton = new JButton("Start Game");
    	
    	athleteProgress = new JProgressBar();
    	
    	athleteProgress.setMinimum(0);
    	athleteProgress.setMaximum(100);
    	athleteProgress.setValue(0);
    	athleteProgress.setStringPainted(true);
    	
    	add(athleteProgress);
    	
    	startGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    	startGameButton.addActionListener(this);
    	
    	//task = new Task();
		//task.addPropertyChangeListener(listener);
    	

    	
    	JButton nextButton = new JButton("See Results");
    	nextButton.addActionListener(new ActionListener()
    	{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				guiManager.ShowCardByName("Current Game Results");	
			}
    		
    	});
    	
    	add(startGameButton);
    	add(nextButton);
	}
	
	@Override
	public String getCardName() {
		// TODO Auto-generated method stub
		return "Game in Progress";
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) 
	{
		if ("progress" == evt.getPropertyName()) 
		{
            int progress = (Integer) evt.getNewValue();
            athleteProgress.setValue(progress);
		}
	}

	@Override
	public void actionPerformed(ActionEvent evt) 
	{
		task = new Task();
		task.addPropertyChangeListener(this);
		task.execute();
		
	}

}

package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import games.Game;
import persons.Athlete;
import persons.Cyclist;
import persons.Sprinter;
import persons.SuperAthlete;
import persons.Swimmer;

@SuppressWarnings("serial")
public class GameInProgress extends GuiCard implements ActionListener, PropertyChangeListener {
	
	Task task;
	//JProgressBar athleteProgress;
	
	
	JLabel[] athleteLabels;
	JProgressBar[] athleteProgress;
	int athletesFinished = 0;
	
	JButton seeResultsButton = null;
	
	 class Task extends SwingWorker<Void, Void> {
		 
		 JProgressBar progressBar;
		 public int progressBarSpeed;
		 Athlete athlete;
		 
		 
		 
		 public Task(JProgressBar progressBar, Athlete athlete)
		 {
			 this.progressBar = progressBar;
			 this.athlete = athlete;
			 
		
		 }
		 
	        /*
	         * Main task. Executed in background thread.
	         */
	        @Override
	        public Void doInBackground() {
	            int progress = 0;
	            float athleteSpeed = athlete.getLastTimeRecorded();
	            float maxCompeteTime = athlete.maxCompeteTime();
	            float normalizedTime = athleteSpeed / (maxCompeteTime);
	            long waitTime = (long)(normalizedTime * 500.0f);

	            //Initialize progress property.
	            setProgress(0);
	            while (progress < 100) {
	                //Sleep for up to one second.
	                try {
	                    Thread.sleep(waitTime);
	                } catch (InterruptedException ignore) {}
	                //Make random progress.
	                progress += 5;
	                setProgress(Math.min(progress, 100));
	                progressBar.setValue(Math.min(progress, 100));
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
    	

    	
    	startGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    	startGameButton.addActionListener(this);
    	
    	
    	add(startGameButton);
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
            
            if(progress == 100)
            {
            	athletesFinished++;
            	
            	if(athletesFinished == guiManager.athletesSelected.size())
            	{
            		seeResultsButton.setEnabled(true);
            	}
            }
		}
	}

	@Override
	public void actionPerformed(ActionEvent evt) 
	{
		Game game = guiManager.gameSelected;
		
		game.setCompetitors(guiManager.athletesSelected);
		game.setReferee(guiManager.officialSelected);
		
		game.runGame();
		
		guiManager.dataLoader.addGameResult(game);
		
		ArrayList<Athlete> allAthletes = new ArrayList<Athlete>();
		
		allAthletes.addAll(guiManager.dataLoader.getPersonCollection().getPersonsByType(Swimmer.class));
		allAthletes.addAll(guiManager.dataLoader.getPersonCollection().getPersonsByType(Cyclist.class));
		allAthletes.addAll(guiManager.dataLoader.getPersonCollection().getPersonsByType(Sprinter.class));
		allAthletes.addAll(guiManager.dataLoader.getPersonCollection().getPersonsByType(SuperAthlete.class));
		
		guiManager.dataLoader.setAthleteResults(allAthletes);
		
		for(int i = 0; i < guiManager.athletesSelected.size(); i++)
		{
			Task task = new Task(athleteProgress[i], guiManager.athletesSelected.get(i));
			task.addPropertyChangeListener(this);
			task.execute();
		}		
	}
	
	@Override
	public void OnShowCard()
	{
		athletesFinished = 0;
		
		
		athleteProgress = new JProgressBar[guiManager.athletesSelected.size()];
		athleteLabels = new JLabel[guiManager.athletesSelected.size()];
		
		for(int i = 0; i < guiManager.athletesSelected.size(); i++)
		{

			athleteLabels[i] = new JLabel();
			athleteLabels[i].setText(guiManager.athletesSelected.get(i).getName() + " progress:");
			add(athleteLabels[i]);
			
	    	athleteProgress[i] = new JProgressBar();
	    	
	    	athleteProgress[i].setMinimum(0);
	    	athleteProgress[i].setMaximum(100);
	    	athleteProgress[i].setValue(0);
	    	athleteProgress[i].setStringPainted(false);
	    	
	    	add(athleteProgress[i]);
		}
		
		if(seeResultsButton != null)
		{
			remove(seeResultsButton);
		}
		
		seeResultsButton = new JButton("See Results");
		seeResultsButton.setEnabled(false);
		
		seeResultsButton.addActionListener(new ActionListener()
    	{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				guiManager.ShowCardByName("Current Game Results");	
			}
    		
    	});
		
		seeResultsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		add(seeResultsButton);

	}
	
	@Override
	public void OnHideCard()
	{
		if(athleteProgress != null)
		{
			for(int i = 0; i < athleteProgress.length; i++)
			{
				remove(athleteProgress[i]);
			}
		}
		
		if(athleteLabels != null)
		{
			for(int i = 0; i < athleteLabels.length; i++)
			{
				remove(athleteLabels[i]);
			}
		}
		
		athleteLabels = null;
		athleteProgress = null;
	}

}


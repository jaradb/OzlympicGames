package ozlympicGames;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import games.*;
import persons.*;

public class Driver {
	
	private GameCollection games;
	private PersonCollection people;
	private HardcodedDataLoader loader;
	
	private Scanner input;
	
	private boolean applicationRunning = true;
	
	private Game gameSelectedToRun = null;
	private Athlete predictedWinner = null;
	private String totalGameResults = new String();

	public Driver()
	{
		input = new Scanner(System.in);
		
		loader = new HardcodedDataLoader();
		
		InitialiseData();
	}
	
	public void InitialiseData()
	{
		games = new GameCollection(loader);
		people = new PersonCollection(loader);
	}
	
	public String getUserInputAsString()
	{
		return input.nextLine();
	}
	
	public int getUserInputAsInt()
	{
		int inputAsInt = -1;
		String userInputChoice = input.nextLine();
		try
		{
			inputAsInt = Integer.parseInt(userInputChoice);
		}
		catch(NumberFormatException numberException)
		{
			inputAsInt = -1;
		}
		
		return inputAsInt;
	}
	
	public boolean getUserInputAsBoolean()
	{
		String userInputChoice = input.nextLine();
		
		if(userInputChoice.toUpperCase().equals("y".toUpperCase()) ||
			userInputChoice.toUpperCase().equals("yes".toUpperCase()))
		{
			//User said either 'y' or 'yes' (case insensitive), so return true.
			return true;
		}
		
		//Lets assume any other answer is a 'no'.
		return false;
	}
	
	public void DisplayMenu()
	{
		while(applicationRunning)
		{
			System.out.println("Welcome to the Ozlympic Games");
			System.out.println("===================================");
			System.out.println("1. Select a game to run");
			System.out.println("2. Predict the winner of the game");
			System.out.println("3. Start the game. " + 
								(gameSelectedToRun != null ? "(" + gameSelectedToRun.getGameName() + " selected)" : "(No game selected)"));
			System.out.println("4. Display the final results of all games");
			System.out.println("5. Display the points of all athletes");
			System.out.println("6. Exit");
			System.out.println("Type a number between 1 and 6, then press enter to select an option.");
			System.out.print("Please input an option: ");
			
			switch(getUserInputAsInt())
			{
			case 1:
				SelectAGame();
				break;
				
			case 2:
				PredictAWinner();
				break;
				
			case 3:
				StartTheGame();
				break;
				
			case 4:
				DisplayGameResults();
				break;
				
			case 5:
				DisplayAthletePoints();
				break;
				
			case 6:
				System.out.println("Exiting...");
				applicationRunning = false;
				break;
				
			default:
				System.out.println("That wasn't a valid option.");
				break;
			}
		}
	}
	
	public void SelectAGame()
	{
		boolean validGameChosen = false;
		int gameChosen = -1;
		
		while(!validGameChosen)
		{
			System.out.println("Here's the available games:");
			
			//Starting the list at 1 for user readability
			for(int i = 0; i < games.getGameList().size(); i++)
			{
				//Adding one to the iterator to make it more user readable.
				//since starting the menu at zero is inconsistent with the main menu.
				System.out.println((i + 1) + ": " + games.getGameList().get(i).getGameName());
			}
			System.out.println("Please choose a game: ");
			
			//Have to subtract one to return the index back to starting at zero
			//for the purposes of the arrayList.
			gameChosen = getUserInputAsInt() - 1;
			
			if(gameChosen >= games.getGameList().size() || gameChosen < 0)
			{
				System.out.println("That's not a valid option");
				continue;
			}
			else
			{
				validGameChosen = true;
			}
		}
		
		
		gameSelectedToRun = games.getGameList().get(gameChosen);
		
		System.out.println("Setting " + gameSelectedToRun.getGameName() + " to be played as the next game.");
		
		AssignOfficialToRefereeGame();
		
		System.out.println("Would you like to select the participants manually? (y/n): ");
			
		if(getUserInputAsBoolean())
		{
			ChooseParticipants();
		}
		else
		{
			AutomaticallyDraftParticipants();
		}
	}
	
	public void AssignOfficialToRefereeGame()
	{
		ArrayList<Person> officials = people.GetPersonsByType(Official.class);
		Random officialSelected = new Random();
		gameSelectedToRun.setReferee((Official)officials.get(officialSelected.nextInt(officials.size())));
	}
	
	public void ChooseParticipants()
	{
		//Get all the specialist athletes for the game selected.
		ArrayList<Person> validParticipants = people.GetPersonsByType(gameSelectedToRun.getAltheteClassTypeForGame());
		
		//Add the super athletes as they can compete in anything.
		validParticipants.addAll(people.GetPersonsByType(SuperAthlete.class));
		
		boolean stillDrafting = true;
		
		while(stillDrafting)
		{
			System.out.println("Here's the available participants for " + gameSelectedToRun.getGameName() + ":");
			
			for(int i = 0; i < validParticipants.size(); i++)
			{
				System.out.print((i + 1) + ": " + validParticipants.get(i).getName());
				System.out.println(" representing " + ((Athlete)validParticipants.get(i)).getRepresentingState().toString());
			}
			
			System.out.println((validParticipants.size() + 1) + ": Stop selecting.");
			
			System.out.println("Select an athlete to participate (or stop selecting): ");
			int athleteSelected = getUserInputAsInt() - 1;

			if(athleteSelected < 0 || athleteSelected > validParticipants.size())
			{
				System.out.println("That wasn't a valid participant option.");
				continue;
			}
			
			//Check if they selected the final option, which is 'stop selecting'.
			if(athleteSelected == validParticipants.size())
			{
				stillDrafting = false;
			}
			else
			{
				boolean alreadySelected = false;
				//Let's make sure they didn't select somebody they already drafted.
				Athlete athleteObject = (Athlete)validParticipants.get(athleteSelected);
				
				for(Athlete athlete : gameSelectedToRun.getCompetitors())
				{
					if(athlete.equals(athleteObject))
					{
						alreadySelected = true;
						break;
					}
				}
				
				if(alreadySelected)
				{
					System.out.println("That player is already in the competitors list for this game.");
					continue;	
				}
				
				//Add the athlete to the participants list.
				gameSelectedToRun.getCompetitors().add((Athlete)validParticipants.get(athleteSelected));
				System.out.println("Added " + validParticipants.get(athleteSelected).getName() + " to the game.");
			}
		}
	}
	
	public void AutomaticallyDraftParticipants()
	{
		System.out.println("Automatically adding maximum participants available.");
		
		//Get all the specialist athletes for the game selected.
		ArrayList<Person> validParticipants = people.GetPersonsByType(gameSelectedToRun.getAltheteClassTypeForGame());
		
		//Add the super athletes as they can compete in anything.
		validParticipants.addAll(people.GetPersonsByType(SuperAthlete.class));

		ArrayList<Integer> potentialParticipantIndices = new ArrayList<Integer>();
		for(int i = 0; i < validParticipants.size(); i++)
		{
			potentialParticipantIndices.add(i);
		}
		
		Random randomDraft = new Random();
		
		for(int i = 0; i < Game.MAX_PARTICIPANTS; i++)
		{
			int randomAthleteIndex = randomDraft.nextInt(potentialParticipantIndices.size());
			
			Athlete athleteSelected = (Athlete)validParticipants.get(potentialParticipantIndices.get(randomAthleteIndex));
			
			potentialParticipantIndices.remove(randomAthleteIndex);
			
			gameSelectedToRun.getCompetitors().add(athleteSelected);
			
			System.out.println("Added " + athleteSelected.getName() + " to the game.");
			
			if(potentialParticipantIndices.isEmpty())
			{
				//We ran out of valid participants before hitting the max participants allowed.
				break;
			}
		}

	}
	
	public void PredictAWinner()
	{
		boolean predictingAWinner = true;
		
		if(gameSelectedToRun == null)
		{
			System.out.println("You need to select a game to run before you can predict a winner.");
			return;
		}
		
		while(predictingAWinner)
		{
			System.out.println(gameSelectedToRun.getGameName() + " has the following participants: ");
			
			for(int i = 0; i < gameSelectedToRun.getCompetitors().size(); i++)
			{
				System.out.print((i + 1) + ": " + gameSelectedToRun.getCompetitors().get(i).getName());
				System.out.println(" representing " + gameSelectedToRun.getCompetitors().get(i).getRepresentingState().toString());
			}
			
			System.out.print("Predict a winner: ");
			
			int athleteSelected = getUserInputAsInt() - 1;
			
			if(athleteSelected < 0 || athleteSelected > gameSelectedToRun.getCompetitors().size())
			{
				System.out.println("That wasn't a valid prediction option.");
			}
			else
			{	
				predictedWinner = gameSelectedToRun.getCompetitors().get(athleteSelected);	
				predictingAWinner = false;
				System.out.println("You're predicting " + predictedWinner.getName() + " will win.");
			}
		}
	}
	
	public void StartTheGame()
	{
		if(gameSelectedToRun == null)
		{
			System.out.println("You have to select a game before running it!");
			return;
		}
		
		System.out.println("The game is " + gameSelectedToRun.getGameName());
		
		gameSelectedToRun.runGame();
		
		System.out.println(gameSelectedToRun.getGameResult());
		
		totalGameResults += gameSelectedToRun.getGameResult();
		
		if(predictedWinner != null)
		{
			System.out.println("You predicted " + predictedWinner.getName() + " would win.");
			System.out.println(gameSelectedToRun.GetWinner().getName() + " won.");
			if(predictedWinner.equals(gameSelectedToRun.GetWinner()))
			{
				System.out.println("Congratulations on picking the winner!");
			}
			else
			{
				System.out.println("Better luck next time!");
			}
		}
		
		//Clear out the game that just ran.
		gameSelectedToRun = null;
	}
	
	public void DisplayGameResults()
	{
		System.out.println(totalGameResults);
	}
	
	public void DisplayAthletePoints()
	{
		
		System.out.println("Current point tally for all athletes: ");
		
		ArrayList<Person> allAthletes = new ArrayList<Person>();
		
		allAthletes.addAll(people.GetPersonsByType(Swimmer.class));
		allAthletes.addAll(people.GetPersonsByType(Cyclist.class));
		allAthletes.addAll(people.GetPersonsByType(Sprinter.class));
		allAthletes.addAll(people.GetPersonsByType(SuperAthlete.class));
		
		
		
		
		for(Person athlete : allAthletes)
		{
			
			System.out.println(	athlete.getName() + 
								" representing " + ((Athlete)athlete).getRepresentingState().toString() +
								" with a score of: " + ((Athlete)athlete).GetCurrentPointCount());
		}
	}

}

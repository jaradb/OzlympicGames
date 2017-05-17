package ozlympicGames;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import games.*;
import persons.*;

public class Driver {

	private GameCollection games;
	private PersonCollection people;
	private DatabaseLoader loader;
	private PredictionTracker predictionTracker;

	private Scanner input;

	private boolean applicationRunning = true;

	private Game gameSelectedToRun = null;
	private String totalGameResults = new String();

	public Driver() {
		input = new Scanner(System.in);
		loader = new DatabaseLoader();
		predictionTracker = new PredictionTracker();
		initialiseData();
	}

	public void initialiseData() {
		games = new GameCollection(loader);
		people = new PersonCollection(loader);
	}

	// Queries the user for textual information.
	public String getUserInputAsString() {
		return input.nextLine();
	}

	// Queries the user for a number. Does some basic error handling to ensure a
	// valid result.
	// Returns -1 if the user supplies bad input (for example, the word "hello"
	// when we were expecting an integer).
	public int getUserInputAsInt() {
		int inputAsInt = -1;
		String userInputChoice = input.nextLine();
		try {
			inputAsInt = Integer.parseInt(userInputChoice);
		} catch (NumberFormatException numberException) {
			inputAsInt = -1;
		}

		return inputAsInt;
	}

	// Returns a true/false depending on the users response to a yes/no question
	public boolean getUserInputAsBoolean() {
		String userInputChoice = input.nextLine();

		if (userInputChoice.toUpperCase().equals("y".toUpperCase())
				|| userInputChoice.toUpperCase().equals("yes".toUpperCase())) {
			// User said either 'y' or 'yes' (case insensitive), so return true.
			return true;
		}

		// Lets assume any other answer is a 'no'.
		return false;
	}

	// Displays the main menu into the console, then runs a method depending on
	// their input.
	public void displayMenu() {
		while (applicationRunning) {
			System.out.println("Welcome to the Ozlympic Games");
			System.out.println("===================================");
			System.out.println("1. Select a game to run");
			System.out.println("2. Predict the winner of the game. " + (predictionTracker.getPredictedWinner() != null
					? "(" + predictionTracker.getPredictedWinner().getName() + " selected)"
					: "(No prediction selected)"));
			System.out.println("3. Start the game. " + (gameSelectedToRun != null
					? "(" + gameSelectedToRun.getGameName() + " selected)" : "(No game selected)"));
			System.out.println("4. Display the final results of all games and predictions");
			System.out.println("5. Display the points of all athletes");
			System.out.println("6. Exit");
			System.out.println("Type a number between 1 and 6, then press enter to select an option.");
			System.out.print("Please input an option: ");

			switch (getUserInputAsInt()) {
			case 1:
				selectAGame();
				break;

			case 2:
				predictAWinner();
				break;

			case 3:
				startTheGame();
				break;

			case 4:
				displayGameResults();
				break;

			case 5:
				displayAthletePoints();
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

	public void selectAGame() {
		boolean validGameChosen = false;
		int gameChosen = -1;

		while (!validGameChosen) {
			System.out.println("Here's the available games:");

			// Starting the list at 1 for user readability
			for (int i = 0; i < games.getGameList().size(); i++) {
				// Adding one to the iterator to make it more user readable.
				// since starting the menu at zero is inconsistent with the main
				// menu.
				System.out.println((i + 1) + ": " + games.getGameList().get(i).getGameName());
			}
			System.out.println("Please choose a game: ");

			// Have to subtract one to return the index back to starting at zero
			// for the purposes of the arrayList.
			gameChosen = getUserInputAsInt() - 1;

			if (gameChosen >= games.getGameList().size() || gameChosen < 0) {
				System.out.println("That's not a valid option");
				continue;
			} else {
				validGameChosen = true;
			}
		}

		gameSelectedToRun = games.getGameList().get(gameChosen);
		System.out.println("Setting " + gameSelectedToRun.getGameName() + " to be played as the next game.");

		// Assign an official to be a referee.
		assignOfficialToRefereeGame();

		System.out.println("Would you like to select the participants manually? (y/n): ");
		if (getUserInputAsBoolean()) {
			chooseParticipants();
		} else {
			automaticallyDraftParticipants();
		}

		// Clear the currently selected predicted winner (if set), since a new
		// roster was assigned.
		predictionTracker.clearPrediction();
	}

	// The assigned official is randomly picked from the pool of officials in
	// the personnel list.
	public void assignOfficialToRefereeGame() {
		ArrayList<Person> officials = people.getPersonsByType(Official.class);
		Random officialSelected = new Random();
		gameSelectedToRun.setReferee((Official) officials.get(officialSelected.nextInt(officials.size())));
	}

	public void chooseParticipants() {
		// Get all the specialist athletes for the game selected.
		ArrayList<Person> validParticipants = people.getPersonsByType(gameSelectedToRun.getAltheteClassTypeForGame());

		// Add the super athletes as they can compete in anything.
		validParticipants.addAll(people.getPersonsByType(SuperAthlete.class));

		boolean stillDrafting = true;
		while (stillDrafting) {
			System.out.println("Here's the available participants for " + gameSelectedToRun.getGameName() + ":");

			for (int i = 0; i < validParticipants.size(); i++) {
				System.out.print((i + 1) + ": " + validParticipants.get(i).getName());
				System.out.println(
						" representing " + ((Athlete) validParticipants.get(i)).getRepresentingState().toString());
			}

			System.out.println((validParticipants.size() + 1) + ": Stop selecting.");

			System.out.println("Select an athlete to participate (or stop selecting): ");
			int athleteSelected = getUserInputAsInt() - 1;

			if (athleteSelected < 0 || athleteSelected > validParticipants.size()) {
				System.out.println("That wasn't a valid participant option.");
				continue;
			}

			// Check if they selected the final option, which is 'stop
			// selecting'.
			if (athleteSelected == validParticipants.size()) {
				stillDrafting = false;
			} else {
				boolean alreadySelected = false;
				// Let's make sure they didn't select somebody they already
				// drafted.
				Athlete athleteObject = (Athlete) validParticipants.get(athleteSelected);

				for (Athlete athlete : gameSelectedToRun.getCompetitors()) {
					if (athlete.equals(athleteObject)) {
						alreadySelected = true;
						break;
					}
				}

				if (alreadySelected) {
					System.out.println("That player is already in the competitors list for this game.");
					continue;
				}

				// Add the athlete to the participants list.
				gameSelectedToRun.getCompetitors().add((Athlete) validParticipants.get(athleteSelected));
				System.out.println("Added " + validParticipants.get(athleteSelected).getName() + " to the game.");
			}
		}
	}

	public void automaticallyDraftParticipants() {
		System.out.println("Automatically adding maximum participants available.");

		// Get all the specialist athletes for the game selected.
		ArrayList<Person> validParticipants = people.getPersonsByType(gameSelectedToRun.getAltheteClassTypeForGame());

		// Add the super athletes as they can compete in anything.
		validParticipants.addAll(people.getPersonsByType(SuperAthlete.class));

		// Generate a list of indices so we can randomly select one, then remove
		// it from the list
		// This prevents us from accidentally drafting the same participant over
		// and over.
		ArrayList<Integer> potentialParticipantIndices = new ArrayList<Integer>();
		for (int i = 0; i < validParticipants.size(); i++) {
			potentialParticipantIndices.add(i);
		}

		Random randomDraft = new Random();

		for (int i = 0; i < Game.MAX_PARTICIPANTS; i++) {
			int randomAthleteIndex = randomDraft.nextInt(potentialParticipantIndices.size());

			// Get an athlete out of the athlete pool using one of our random
			// indices.
			Athlete athleteSelected = (Athlete) validParticipants
					.get(potentialParticipantIndices.get(randomAthleteIndex));

			// Then remove the index from the indices list so we don't re-select
			// the same athlete.
			potentialParticipantIndices.remove(randomAthleteIndex);

			// And then add the athlete to the competitors list.
			gameSelectedToRun.getCompetitors().add(athleteSelected);

			System.out.println("Added " + athleteSelected.getName() + " to the game.");

			if (potentialParticipantIndices.isEmpty()) {
				// We ran out of valid participants before hitting the max
				// participants allowed.
				break;
			}
		}

	}

	public void predictAWinner() {
		boolean predictingAWinner = true;

		// We need to know who's participating before we predict a winner,
		// and the participation list is generated upon selecting the game.
		if (gameSelectedToRun == null) {
			System.out.println("You need to select a game to run before you can predict a winner.");
			return;
		}

		while (predictingAWinner) {
			System.out.println(gameSelectedToRun.getGameName() + " has the following participants: ");

			for (int i = 0; i < gameSelectedToRun.getCompetitors().size(); i++) {
				System.out.print((i + 1) + ": " + gameSelectedToRun.getCompetitors().get(i).getName());
				System.out.println(
						" representing " + gameSelectedToRun.getCompetitors().get(i).getRepresentingState().toString());
			}

			System.out.print("Predict a winner: ");

			int athleteSelected = getUserInputAsInt() - 1;

			if (athleteSelected < 0 || athleteSelected >= gameSelectedToRun.getCompetitors().size()) {
				System.out.println("That wasn't a valid prediction option.");
			} else {
				predictionTracker.setPredictedWinner(gameSelectedToRun.getCompetitors().get(athleteSelected));
				predictingAWinner = false;
				System.out.println(
						"You're predicting " + predictionTracker.getPredictedWinner().getName() + " will win.");
			}
		}
	}

	public void startTheGame() {
		if (gameSelectedToRun == null) {
			System.out.println("You have to select a game before running it!");
			return;
		}

		// Tell the user which game is running, then tell them the results, then
		// lastly
		// add the results to the master list of game results.
		System.out.println("The game is " + gameSelectedToRun.getGameName());
		gameSelectedToRun.runGame();
		System.out.println(gameSelectedToRun.getGameResult());
		totalGameResults += gameSelectedToRun.getGameResult();

		// If the user predicted a winner lets see if they won.
		if (predictionTracker.getPredictedWinner() != null) {
			System.out.println("You predicted " + predictionTracker.getPredictedWinner().getName() + " would win.");
			System.out.println(gameSelectedToRun.getWinner().getName() + " won.");
			if (predictionTracker.getPredictedWinner().equals(gameSelectedToRun.getWinner())) {
				System.out.println("Congratulations on picking the winner!");
				predictionTracker.recordWinningPrediction(gameSelectedToRun);
			} else {
				System.out.println("Better luck next time!");
			}
		}

		// Clear out the game that just ran.
		gameSelectedToRun = null;
		predictionTracker.clearPrediction();
	}

	// Display the results of every game run so far.
	public void displayGameResults() {
		System.out.println(totalGameResults);
		System.out.println(predictionTracker.getPredictionReport());
	}

	// Display the currently accumulated points for every athlete.
	public void displayAthletePoints() {
		System.out.println("Current point tally for all athletes: ");
		ArrayList<Person> allAthletes = new ArrayList<Person>();

		// Add all the athlete types.
		allAthletes.addAll(people.getPersonsByType(Swimmer.class));
		allAthletes.addAll(people.getPersonsByType(Cyclist.class));
		allAthletes.addAll(people.getPersonsByType(Sprinter.class));
		allAthletes.addAll(people.getPersonsByType(SuperAthlete.class));

		for (Person athlete : allAthletes) {
			System.out.println(
					athlete.getName() + " representing " + ((Athlete) athlete).getRepresentingState().toString()
							+ " with a score of: " + ((Athlete) athlete).getCurrentPointCount());
		}
	}

}

package persons;

import java.util.ArrayList;

import games.Game;

public class Official extends Person {

	static final int FIRST_PLACE_POINTS = 5;
	static final int SECOND_PLACE_POINTS = 2;
	static final int THIRD_PLACE_POINTS = 1;

	public Official(String uniqueID, String name, int age) {
		super(uniqueID, name, age);
	}

	public Official() {

	}

	public String confirmGameResults(String gameType, int roundNumber, Athlete firstPlaceWinner,
			Athlete secondPlaceWinner, Athlete thirdPlaceWinner, Game game) {
		
		// Allocate the points for winning
		firstPlaceWinner.addToPointCount(FIRST_PLACE_POINTS);
		secondPlaceWinner.addToPointCount(SECOND_PLACE_POINTS);
		thirdPlaceWinner.addToPointCount(THIRD_PLACE_POINTS);
		
	

		// Create a summary of the game and return it.
		String report = new String();

		report += "In " + gameType + " (round " + roundNumber + ") overseen by referee " + getName() + " (ID: " + getUniqueID()
				+ "), the results were -- \n";

		report += "1 (First place): " + firstPlaceWinner.getName() + " (ID: " + firstPlaceWinner.getUniqueID() + "), age "
				+ firstPlaceWinner.getAge() + " representing the state of "
				+ firstPlaceWinner.getRepresentingState().toString() + " with a current total of "
				+ firstPlaceWinner.getCurrentPointCount() + " point(s), finished in " + firstPlaceWinner.getLastTimeRecorded() + " seconds.\n";

		report += "2 (Second place): " + secondPlaceWinner.getName() + " (ID: " + secondPlaceWinner.getUniqueID() + "), age "
				+ secondPlaceWinner.getAge() + " representing the state of "
				+ secondPlaceWinner.getRepresentingState().toString() + " with a current total of "
				+ secondPlaceWinner.getCurrentPointCount() + " point(s), finished in " + secondPlaceWinner.getLastTimeRecorded() + " seconds.\n";
		
		report += "3 (Third place): " + thirdPlaceWinner.getName() + " (ID: " + thirdPlaceWinner.getUniqueID() + "), age "
				+ thirdPlaceWinner.getAge() + " representing the state of "
				+ thirdPlaceWinner.getRepresentingState().toString() + " with a current total of "
				+ thirdPlaceWinner.getCurrentPointCount() + " point(s), finished in " + thirdPlaceWinner.getLastTimeRecorded() + " seconds.\n";
		
		ArrayList<Athlete> remainingCompetitors = new ArrayList<Athlete>();
		for(Athlete athlete : game.getCompetitors())
		{
			remainingCompetitors.add(athlete);
		}
		
		remainingCompetitors.remove(firstPlaceWinner);
		remainingCompetitors.remove(secondPlaceWinner);
		remainingCompetitors.remove(thirdPlaceWinner);
		
		int remainingPositionsStartPoint = 3;
		
		for(Athlete athlete : remainingCompetitors)
		{
			remainingPositionsStartPoint++;
			
			report += remainingPositionsStartPoint + ": " + athlete.getName() + " (ID: " + athlete.getUniqueID() + "), age "
					+ athlete.getAge() + " representing the state of "
					+ athlete.getRepresentingState().toString() + " with a current total of "
					+ athlete.getCurrentPointCount() + " point(s), finished in " + athlete.getLastTimeRecorded() + " seconds.\n";
		}

		return report;
	}
	
}

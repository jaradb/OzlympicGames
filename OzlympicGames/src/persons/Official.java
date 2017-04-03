package persons;

public class Official extends Person {

	static final int FIRST_PLACE_POINTS = 5;
	static final int SECOND_PLACE_POINTS = 2;
	static final int THIRD_PLACE_POINTS = 1;

	public Official(String uniqueID, String name, int age) {
		super(uniqueID, name, age);
	}

	public Official() {

	}

	public String ConfirmGameResults(String gameType, int roundNumber, Athlete firstPlaceWinner,
			Athlete secondPlaceWinner, Athlete thirdPlaceWinner) {
		// Allocate the points for winning
		firstPlaceWinner.AddToPointCount(FIRST_PLACE_POINTS);
		secondPlaceWinner.AddToPointCount(SECOND_PLACE_POINTS);
		thirdPlaceWinner.AddToPointCount(THIRD_PLACE_POINTS);

		// Create a summary of the game and return it.
		String report = new String();

		report += "In " + gameType + " (round " + roundNumber + ") overseen by referee " + getName() + " (ID: " + getUniqueID()
				+ "), the results were -- \n";

		report += "First place: " + firstPlaceWinner.getName() + " (ID: " + firstPlaceWinner.getUniqueID() + "), age "
				+ firstPlaceWinner.getAge() + " representing the state of "
				+ firstPlaceWinner.getRepresentingState().toString() + " with a current total of "
				+ firstPlaceWinner.GetCurrentPointCount() + " point(s).\n";

		report += "Second place: " + secondPlaceWinner.getName() + " (ID: " + secondPlaceWinner.getUniqueID() + "), age "
				+ secondPlaceWinner.getAge() + " representing the state of "
				+ secondPlaceWinner.getRepresentingState().toString() + " with a current total of "
				+ secondPlaceWinner.GetCurrentPointCount() + " point(s).\n";
		
		report += "Third place: " + thirdPlaceWinner.getName() + " (ID: " + thirdPlaceWinner.getUniqueID() + "), age "
				+ thirdPlaceWinner.getAge() + " representing the state of "
				+ thirdPlaceWinner.getRepresentingState().toString() + " with a current total of "
				+ thirdPlaceWinner.GetCurrentPointCount() + " point(s).\n";
		

		return report;
	}
}

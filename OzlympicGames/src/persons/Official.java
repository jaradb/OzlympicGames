package persons;

public class Official extends Person {

	public Official(String uniqueID, String name, int age)
	{
		super(uniqueID, name, age);
	}
	
	public Official()
	{
		
	}
	
	
	public String ConfirmGameResults(String gameType, int roundNumber, Athlete firstPlaceWinner, Athlete secondPlaceWinner, Athlete thirdPlaceWinner)
	{
		//Allocate the points for winning
		firstPlaceWinner.AddToPointCount(5);
		secondPlaceWinner.AddToPointCount(2);
		thirdPlaceWinner.AddToPointCount(1);
		
		String report = new String();
		
		report += 	"In Swimming (round " + roundNumber + ") overseen by referee " + getName() +", the results were -- \n";
		
		report += 	"First place: " + firstPlaceWinner.getName() + 
					" representing the state of " + firstPlaceWinner.getRepresentingState().toString() +
					" with a current total of " + firstPlaceWinner.GetCurrentPointCount() + " points.\n";
		
		report += 	"Second place: " + secondPlaceWinner.getName() + 
					" representing the state of " + secondPlaceWinner.getRepresentingState().toString() +
					" with a current total of " + secondPlaceWinner.GetCurrentPointCount() + " points.\n";
	
		report += 	"Third place: " + thirdPlaceWinner.getName() + 
					" representing the state of " + thirdPlaceWinner.getRepresentingState().toString() +
					" with a current total of " + thirdPlaceWinner.GetCurrentPointCount() + " points.\n";
	
		return report;
	}
}

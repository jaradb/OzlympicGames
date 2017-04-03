package games;

import java.util.ArrayList;

import persons.Athlete;

public abstract class Game {
	
	static final public int MAX_PARTICIPANTS = 8;
	static final public int MIN_PARTICIPANTS = 4;
	
	protected String uniqueGameID;
	protected ArrayList<Athlete> competitors;
	
	protected Athlete firstPlaceWinner, secondPlaceWinner, thirdPlaceWinner;
	
	protected String gameResult;
	
	//Number of times the game was run.
	protected int roundNumber = 0;
	
	public Game()
	{
		competitors = new ArrayList<Athlete>();
	}

	public ArrayList<Athlete> getCompetitors() {
		return competitors;
	}

	public void setCompetitors(ArrayList<Athlete> competitors) {
		this.competitors = competitors;
	}
	
	public void clearCompetitorList() {
		competitors.clear();
	}

	public String getUniqueGameID() {
		return uniqueGameID;
	}

	public void setUniqueGameID(String uniqueGameID) {
		this.uniqueGameID = uniqueGameID;
	}
	
	public String getGameResult()
	{
		return gameResult;
	}
	
	abstract public String getGameName();
	abstract public Class<?> getAltheteClassTypeForGame();
	
	
	public void runGame()
	{
		//Increment the number of times this game was run.
		roundNumber++;
		
		int competitorsCount = getCompetitors().size();
		
		if(competitorsCount < Game.MIN_PARTICIPANTS) {
			gameResult = getGameName() + " (round " + roundNumber + ") cancelled due to lack of participants. Game requires at least 4 participants to be played, only " + competitorsCount + " participants were scheduled.\n";
		
			//Cancel out if not enough players are present
			return;
		}
		
		if(competitorsCount > Game.MAX_PARTICIPANTS) {
			gameResult = getGameName() + " (round " + roundNumber + ") cancelled due to excess participants. Game cannot exceed 8 participants, " + competitorsCount + " participants were scheduled.\n";
			
			//Cancel out if too many players are present
			return;
		}
		
		ArrayList<Float> competitorsTimes = new ArrayList<Float>();
		
		for(Athlete athlete : getCompetitors())
		{
			competitorsTimes.add(athlete.compete());
		}
		
		//Figure out who came first.
		int firstPlaceListIndex = getBestTimeIndex(competitors, competitorsTimes);
		firstPlaceWinner = getCompetitors().get(firstPlaceListIndex);
		
		//Now remove the first place winner and check again to see who came second
		getCompetitors().remove(firstPlaceListIndex);
		competitorsTimes.remove(firstPlaceListIndex);
		
		int secondPlaceListIndex = getBestTimeIndex(competitors, competitorsTimes);
		secondPlaceWinner = getCompetitors().get(secondPlaceListIndex);
		
		//Now remove the second place winner and check one last time to see who came third.
		getCompetitors().remove(secondPlaceListIndex);
		competitorsTimes.remove(secondPlaceListIndex);
		
		int thirdPlaceListIndex = getBestTimeIndex(competitors, competitorsTimes);
		thirdPlaceWinner = getCompetitors().get(thirdPlaceListIndex);
		
		//Allocate the points for winning
		firstPlaceWinner.AddToPointCount(5);
		secondPlaceWinner.AddToPointCount(2);
		thirdPlaceWinner.AddToPointCount(1);
		
		//Game has finished, clear out the competitors list
		clearCompetitorList();		
		
		gameResult = generateGameResultReport();
	}
	
	private int getBestTimeIndex(ArrayList<Athlete> competitors, ArrayList<Float> competitorTimes)
	{
		float bestTime = Float.MAX_VALUE;
		int bestTimeIndex = 0;
		
		for(int i = 0; i < competitorTimes.size(); i++)
		{
			if(competitorTimes.get(i) < bestTime)
			{
				bestTime = competitorTimes.get(i);
				bestTimeIndex = i;
			}
		}
		
		return bestTimeIndex;
	}
	
	private String generateGameResultReport()
	{
		String report = new String();
		
		report += 	"In Swimming, round " + roundNumber + ", the results were -- \n";
		
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
	
	public Athlete GetWinner()
	{
		return firstPlaceWinner;
	}

}

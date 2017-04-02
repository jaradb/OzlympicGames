package games;

import java.util.ArrayList;

import persons.Athlete;

public abstract class Game {
	
	protected String uniqueGameID;
	protected ArrayList<Athlete> competitors;
	
	protected Athlete firstPlaceWinner, secondPlaceWinner, thirdPlaceWinner;
	
	protected String gameResult;

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
	
	public void runGame()
	{
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
		
		//Now remove the first place winner and check one last time to see who came third.
		getCompetitors().remove(secondPlaceListIndex);
		competitorsTimes.remove(secondPlaceListIndex);
		
		int thirdPlaceListIndex = getBestTimeIndex(competitors, competitorsTimes);
		thirdPlaceWinner = getCompetitors().get(thirdPlaceListIndex);
		
		//Game has finished, clear out the competitors list
		clearCompetitorList();
		
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

}

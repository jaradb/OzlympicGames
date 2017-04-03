package games;

import persons.*;

public class Swimming extends Game {

	@Override
	public void runGame() {
		
		for(Athlete competitor : getCompetitors())
		{
			if(competitor.getClass().equals(SuperAthlete.class))
			{
				((SuperAthlete)competitor).setCompetingAs(Swimmer.class);
			}
		}
		
		super.runGame();
	}

	@Override
	public String getGameName() {
		return "Swimming";
	}

	@Override
	public Class<?> getAltheteClassTypeForGame() {
		return Swimmer.class;
	}


}

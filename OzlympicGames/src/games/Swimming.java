package games;

import persons.Athlete;
import persons.SuperAthlete;

public class Swimming extends Game {

	@Override
	public void runGame() {
		
		for(Athlete competitor : getCompetitors())
		{
			if(competitor.getClass().equals(SuperAthlete.class))
			{
				
			}
		}
	}


}

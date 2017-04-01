package games;

import persons.Athlete;

public abstract class Game {
	String uniqueGameID;

	public Athlete[] getCompetitors() {
		return competitors;
	}

	public void setCompetitors(Athlete competitors[]) {
		this.competitors = competitors;
	}

	private Athlete competitors[];
}

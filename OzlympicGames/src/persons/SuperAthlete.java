package persons;

import java.util.Random;

public class SuperAthlete extends Athlete {

	private Class<?> competingAs;

	public SuperAthlete(String uniqueID, String name, int age, RepresentingState representingState) {
		super(uniqueID, name, age, representingState);
	}

	@Override
	public float compete() {

		Random randomTimeRange = new Random();

		float minimumTimeRange = 0, maximumTimeRange = 0;

		if (competingAs.getClass().equals(Cyclist.class)) {
			minimumTimeRange = Cyclist.getMinimumCycleTime();
			maximumTimeRange = Cyclist.getMaximumCycleTime();
		} else if (competingAs.getClass().equals(Swimmer.class)) {
			minimumTimeRange = Swimmer.getMinimumSwimTime();
			maximumTimeRange = Swimmer.getMaximumSwimTime();
		} else if (competingAs.getClass().equals(Sprinter.class)) {
			minimumTimeRange = Sprinter.getMinimumSprintTime();
			maximumTimeRange = Sprinter.getMaximumSprintTime();
		}

		return randomTimeRange.nextFloat() * (maximumTimeRange - minimumTimeRange) + minimumTimeRange;
	}

	public Class<?> getCompetingAs() {
		return competingAs;
	}

	public void setCompetingAs(Class<?> competingAs) {
		this.competingAs = competingAs;
	}

}

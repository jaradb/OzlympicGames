package persons;

import java.util.Random;

public class Sprinter extends Athlete {

	public Sprinter(String uniqueID, String name, int age, RepresentingState representingState) {
		super(uniqueID, name, age, representingState);
	}

	@Override
	public float compete() {
		Random randomTimeRange = new Random();
		return randomTimeRange.nextFloat() * (getMaximumSprintTime() - getMinimumSprintTime()) + getMinimumSprintTime();
	}

	static public float getMinimumSprintTime() {
		return 10.0f;
	}

	static public float getMaximumSprintTime() {
		return 20.0f;
	}
}

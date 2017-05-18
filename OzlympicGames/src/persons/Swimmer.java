package persons;

import java.util.Random;

public class Swimmer extends Athlete {

	public Swimmer(String uniqueID, String name, int age, RepresentingState representingState) {
		super(uniqueID, name, age, representingState);
	}

	@Override
	public float compete() {
		Random randomTimeRange = new Random();
		return randomTimeRange.nextFloat() * (getMaximumSwimTime() - getMinimumSwimTime()) + getMinimumSwimTime();
	}

	static public float getMinimumSwimTime() {
		return 100.0f;
	}

	static public float getMaximumSwimTime() {
		return 200.0f;
	}

	@Override
	public float maxCompeteTime() {
		return getMaximumSwimTime();
	}

	@Override
	public float minCompeteTime() {
		return getMinimumSwimTime();
	}
}

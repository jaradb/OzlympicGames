package persons;

import java.util.Random;

public class Cyclist extends Athlete {

	public Cyclist(String uniqueID, String name, int age, RepresentingState representingState) {
		super(uniqueID, name, age, representingState);
	}

	public Cyclist() {

	}

	@Override
	public float compete() {
		Random randomTimeRange = new Random();
		return randomTimeRange.nextFloat() * (getMaximumCycleTime() - getMinimumCycleTime()) + getMinimumCycleTime();
	}

	static public float getMinimumCycleTime() {
		return 500.0f;
	}

	static public float getMaximumCycleTime() {
		return 800.0f;
	}

	@Override
	public float maxCompeteTime() {
		return getMaximumCycleTime();
	}

	@Override
	public float minCompeteTime() {
		return getMinimumCycleTime();
	}

}

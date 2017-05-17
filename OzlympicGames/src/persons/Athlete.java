package persons;

public abstract class Athlete extends Person {

	public enum RepresentingState {
		VIC, ACT, NSW, NT, QLD, SA, TAS, WA,
	}

	private RepresentingState representingState;
	private int pointCount = 0;
	private float lastTimeRecorded = 0.0f;

	public Athlete(String uniqueID, String name, int age, RepresentingState representingState) {
		super(uniqueID, name, age);
		this.representingState = representingState;
	}

	public Athlete() {
		
	}

	public void addToPointCount(int totalPointsAdded) {
		pointCount += totalPointsAdded;
	}

	public int getCurrentPointCount() {
		return pointCount;
	}
	
	public void setLastTimeRecorded(float lastTimeRecorded)
	{
		this.lastTimeRecorded = lastTimeRecorded;
	}
	
	public float getLastTimeRecorded()
	{
		return lastTimeRecorded;
	}

	public RepresentingState getRepresentingState() {
		return representingState;
	}

	public void setRepresentingState(RepresentingState representingState) {
		this.representingState = representingState;
	}

	abstract public float compete();
	

}

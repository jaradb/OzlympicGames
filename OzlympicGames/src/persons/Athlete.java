package persons;

public abstract class Athlete extends Person {

	public enum RepresentingState
	{
		VIC,
		ACT,
		NSW,
		NT,
		QLD,
		SA,
		TAS,
		WA,
	}
	
	private RepresentingState representingState;
	
	private int pointCount = 0;

	public Athlete(String uniqueID, String name, int age, RepresentingState representingState)
	{
		super(uniqueID, name, age);
		this.representingState = representingState;
	}
	
	public Athlete()
	{
		
	}
	
	public void AddToPointCount(int totalPointsAdded)
	{
		pointCount += totalPointsAdded;
	}
	
	public int GetCurrentPointCount()
	{
		return pointCount;
	}
	
	public RepresentingState getRepresentingState() {
		return representingState;
	}

	public void setRepresentingState(RepresentingState representingState) {
		this.representingState = representingState;
	}  
	
	abstract public float compete();
	
}

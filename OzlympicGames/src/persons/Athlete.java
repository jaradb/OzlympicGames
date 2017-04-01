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

	public Athlete(String uniqueID, String name, int age, RepresentingState representingState)
	{
		super(uniqueID, name, age);
		this.representingState = representingState;
	}
	
	public Athlete()
	{
		
	}
	
	public RepresentingState getRepresentingState() {
		return representingState;
	}

	public void setRepresentingState(RepresentingState representingState) {
		this.representingState = representingState;
	}  
	
	abstract public void compete();
	
}

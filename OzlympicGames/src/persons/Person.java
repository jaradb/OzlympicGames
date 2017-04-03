package persons;

public class Person {
	protected int age;
	protected String uniqueID;
	protected String name;

	// Empty constructor to create a person with no details.
	public Person() {

	}

	// Constructor to create person with all attributes set at creation.
	public Person(String uniqueID, String name, int age) {
		this.uniqueID = uniqueID;
		this.name = name;
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

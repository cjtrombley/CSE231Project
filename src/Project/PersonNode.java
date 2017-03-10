package Project;

public class PersonNode {

	private String name;
	private String address;
	private String phoneNumber;
	
	private PersonNode nextPerson;
	
	public PersonNode() {
		this.name = null;
		this.address = null;
		this.phoneNumber = null;
		
		this.nextPerson = null;
	}
	

	public String getName() {
		return name;
	}
	//getAddress()
	public String getAddress() {
		return address;
	}
	//getPhoneNumber();
	public String getPhoneNumber() {
		return phoneNumber;
	}
	//getNextPerson()
	public PersonNode getNext() {
		return nextPerson;
	}
	//setNextPerson()
	public void setNext(PersonNode personNode) {
		this.nextPerson = personNode;
	}
	//toString()
	public String toString() {
		return name + ", " + address + ", " + phoneNumber;
	}
}
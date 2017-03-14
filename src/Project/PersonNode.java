/**
 * Created by cjt on 3/10/2017.
 */

package Project;

public class PersonNode {

	private String name;
    private String address;
	private String phoneNumber;
	private PersonNode nextPerson;

	private int key;

	public PersonNode() {
		this.name = null;
		this.address = null;
		this.phoneNumber = null;
		this.nextPerson = null;
		this.key = -1;
	}

	public PersonNode(String line, PersonNode nextPerson) {
        String fields[] = line.split(",");
        this.name = fields[0];
        this.address = fields[1];
        this.phoneNumber = fields[2];

        this.nextPerson = nextPerson;

        this.key = Math.abs(name.hashCode());
     }

	public String getName() { return name; }

	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public PersonNode getNext() { return nextPerson; }

	public void setNext(PersonNode personNode) { this.nextPerson = personNode; }

	public int getKey() { return this.key; }

	public String toString() { return name + ", " + address + ", " + phoneNumber; }
}
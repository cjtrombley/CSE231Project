/**
 * Created by cjt on 3/10/2017.
 */

package Project;

class PersonNode {

	protected String name;
    protected String address;
	protected String phoneNumber;
	protected PersonNode nextPerson;

	int key;

	PersonNode() {
		this.name = null;
		this.address = null;
		this.phoneNumber = null;
		this.nextPerson = null;
		this.key = -1;
	}

	PersonNode(String line, PersonNode nextPerson) {
        String fields[] = line.split(",");
        this.name = fields[0];
        this.address = fields[1];
        this.phoneNumber = fields[2];
        this.nextPerson = nextPerson;
        this.key = Math.abs(name.hashCode());
     }



	public String toString() { return key + ", " + address + ", " + phoneNumber; }
}
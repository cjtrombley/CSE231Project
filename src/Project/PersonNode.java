package Project;


/**
 * PersonNode
 *
 * This class represents a single node of data to be
 * used by all phone book types.
 *
 * @author      Cody Trombley
 *
 */
class PersonNode {

    //Class members
    String name;
    String address;
	String phoneNumber;
	PersonNode nextPerson;

	int key;



    /**
     * Default constructor. Set all object fields
     * to null and key to -1.
     */
	PersonNode() {
		this.name = null;
		this.address = null;
		this.phoneNumber = null;
		this.nextPerson = null;
		this.key = -1;
	}


    /**
     * Create a PersonNode with values parsed from
     * the input file. The input file should be
     * a csv file with 3 columns: column 1 is
     * the name, column 2 is the address, and
     * column 3 is the phone number.
     *
     * @param line Line of input from file
     */
	PersonNode(String line) {
        String fields[] = line.split(",");
        this.name = fields[0];
        this.address = fields[1];
        this.phoneNumber = fields[2];
        this.nextPerson = null;
        this.key = Math.abs(name.hashCode());
     }


    /**
     * String representation of PersonNode object.
     *
     * @return toString() representation of a PersonNode
     */
	public String toString() { return name + ", " + address + ", " + phoneNumber; }
}
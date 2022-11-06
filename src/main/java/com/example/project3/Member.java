package com.example.project3;
/**
 * Defines a member object to be a part of a gym.
 * Implements compareTo(), toString(), and equals() methods for comparing and representing members,
 * as well as getter methods for all instance variables.
 * Also includes three constructors, one with all instance variables being initialized, one
 * constructor with only first and last name as well as Date of Birth being initialized, and one
 * to initialize either a standard, family, or premium member.
 * @author Adwait Ganguly, Kennan Guan
 */
public class Member implements Comparable<Member> {
    private String fname;
    private String lname;
    private Date dob;
    private Date expire;
    private Location location;

    private static final double ONETIMEFEE = 29.99;

    private static final double STANDARDMONTHLYFEE = 39.99;

    private static final int QUARTERLY = 3;

    /**
     * Constructs a member object based on String parsed from the command line and then split.
     * @param newMember is the string from the command line detailing the member information.
     */
    public Member (String newMember) {
        String[] splitMember = newMember.split("\\s+");
        this.fname = splitMember[0];
        this.lname = splitMember[1];
        this.dob = new Date(splitMember[2]);
        this.expire = new Date(splitMember[3]);
        this.location = Location.valueOf(splitMember[4].toUpperCase());
    }

    /**
     * This is the constructor used to create a Member object that will be removed from the
     * database.
     * @param firstName is the first name of the member.
     * @param lastName is the last name of the member.
     * @param DOB is the date of birth of the member.
     */
    public Member (String firstName, String lastName, String DOB) {
        this.fname = firstName;
        this.lname = lastName;
        this.dob = new Date(DOB);
        this.expire = null;
    }

    /**
     * Constructor for adding a new standard, family, or premium membership member dependent on expiration date.
     * @param fName is the first name of the member.
     * @param lName is the last name of the member
     * @param DOB is the date of birth of the member.
     * @param location is the city location of gym that is associated with the member.
     * @param expirationMonthsLater is the number of months after today that the membership will expire.
     */
    public Member (String fName, String lName, String DOB, String location, int expirationMonthsLater) {
        this.fname = fName;
        this.lname = lName;
        this.dob = new Date(DOB);
        this.location = Location.valueOf(location.toUpperCase());
        this.expire = new Date(expirationMonthsLater); //need to implement a new data constructor that sets expiration to x months from today
    }

    /**
     * Getter method for Member first name.
     * @return a String representing the first name of the Member.
     */
    public String getFname() {
        return this.fname;
    }

    /**
     * Getter method for Member last name.
     * @return a String representing the last name of the =Member.
     */
    public String getLname() {
        return this.lname;
    }

    /**
     * Getter method for Member date of birth.
     * @return a Date object representing the member's date of birth.
     */
    public Date getDob() {
        return this.dob;
    }

    /**
     * Getter method for Member's gym membership expiration date.
     * @return a Date object representing the member's date of membership expiration.
     */
    public Date getExpire() {
        return this.expire;
    }

    /**
     * Getter method for Member's gym location.
     * @return a Location object representing the member's gym location.
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Helper method for the compareTo method that compares the lengths of either the first or last
     * names of a member.
     * @param name1 is either the first or last name of this member object.
     * @param name2 is either the first or last name of the member object being compared.
     * @return the integer length of the longest name out of the two being compared.
     */
    private int getLongerName (String name1, String name2) {
        if (name2.length() > name1.length()) {
            return name2.length();
        }
        else {
            return name1.length();
        }
    }

    /**
     * This method defines the membership fee for a standard Member.
     * This includes the one time fee and next statement fee for the quarter.
     * @return the current membership fee of the standard member.
     */
    public double membershipFee() {
        return ONETIMEFEE + (QUARTERLY * STANDARDMONTHLYFEE);
    }

    /**
     * Getter method for the ONETIMEFEE final constant.
     * @return the ONETIMEFEE final constant variable.
     */
    public double getOneTimeFee() {
        return ONETIMEFEE;
    }

    /**
     * Getter for the QUARTERLY final constant.
     * @return the QUARTERLY final constant variable.
     */
    public int getQuarterly() {
        return QUARTERLY;
    }

    /**
     * Constructs a textual representation of a Member object.
     * @return a String in the format: "firstName lastname, DOB: mm/dd/yyyy, Membership expires
     * mm/dd/yyyy, Location: City, Zip Code, County".
     */
    @Override
    public String toString() {
        return(fname + " " + lname + ", DOB: " + dob.toString() + ", Membership expires "
                + expire.toString() + ", Location: " + location);
    }

    /**
     * Checks if two objects are of the same class or if either is null, then checks equality.
     * @param obj an object that is not necessarily a Member object, and is used for comparison.
     * @return true if first name, last name, and DOB of objects are all equal, otherwise returns false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Member)) {
            return false;
        }

        Member mem = (Member) obj;
        if (this.fname.equalsIgnoreCase(mem.fname.toLowerCase()) && this.lname.equalsIgnoreCase(mem.lname.toLowerCase())
                && this.dob.compareTo(mem.dob) == 0) {
            return true;
        }
        return false;
    }

    /**
     * Compares two members based on last name alphabetically, and then first name alphabetically.
     * @param member is the member object whose last and first name will be compared to this member
     * object.
     * @return positive number, 0, or negative number based on if this object's name is
     * alphabetically greater than, equal to, or less than the member parameters name, respectively.
     */
    @Override
    public int compareTo(Member member) {
        if (member == null) {
            return 1;
        }

        int maxLength = getLongerName(this.lname, member.lname); // variable that will be assigned the longer last or first name
        char[] thisLastName = this.lname.toCharArray();
        char[] memLastName = member.lname.toCharArray();
        for (int x = 0; x < maxLength; x++) {
            if (Character.toLowerCase(thisLastName[x]) != Character.toLowerCase(memLastName[x])) {
                return thisLastName[x] - memLastName[x];
            }
        }

        maxLength = getLongerName(this.fname, member.fname);
        char[] thisFirstName = this.fname.toCharArray();
        char[] memFirstName = member.fname.toCharArray();
        for (int y = 0; y < maxLength; y++) {
            if (Character.toLowerCase(thisFirstName[y]) != Character.toLowerCase(memFirstName[y])) {
                return thisFirstName[y] - memFirstName[y];
            }
        }
        return 0;
    }

    /**
     * Testbed main to exercise the compareTo() method in the Member class.
     * @param args is the command line input of the user.
     */
    public static void main(String[] args) {
        //TEST 1
        Member member1 = new Member("John Doe 1/20/2004 3/30/2023 BRIDGEWATER");
        Member member2 = null;
        int expectedOutput = 1;
        int actualOutput = member1.compareTo(member2);
        System.out.println("**Test case #1: comparing to member object that is null");
        System.out.print("compareTo() returns: ");
        if (actualOutput == expectedOutput) {
            System.out.println(actualOutput + " PASS");
        }
        else {
            System.out.println(actualOutput + " FAIL");
        }
        System.out.println();

        //TEST 2
        member1 = new Member("John Doe 1/20/2004 3/30/2023 BRIDGEWATER");
        member2 = new Member("John Doe 1/20/2004 3/30/2023 BRIDGEWATER");
        expectedOutput = 0;
        actualOutput = member1.compareTo(member2);
        System.out.println("**Test case #2: comparing member objects that have the exact same name");
        System.out.print("compareTo() returns: ");
        if (actualOutput == expectedOutput) {
            System.out.println(actualOutput + " PASS");
        }
        else {
            System.out.println(actualOutput + " FAIL");
        }
        System.out.println();

        //TEST 3
        member1 = new Member("John Doe 1/20/2004 3/30/2023 BRIDGEWATER");
        member2 = new Member("Jane Doe 5/1/1996 3/30/2023 Edison");
        actualOutput = member1.compareTo(member2);
        System.out.println("**Test case #3: comparing member objects that have the same last name but different first names");
        System.out.print("compareTo() returns: ");
        if (actualOutput > 0) {
            System.out.println(actualOutput + " PASS");
        }
        else {
            System.out.println(actualOutput + " FAIL");
        }
        System.out.println();

        //TEST 4
        member1 = new Member("Roy Brooks 8/8/1977 9/30/2020 somerville");
        member2 = new Member("Carl Brown 10/7/1991 3/31/2023 piscataway");
        actualOutput = member1.compareTo(member2);
        System.out.println("**Test case #4: comparing member objects whose last name have the same first three letters");
        System.out.print("compareTo() returns: ");
        if (actualOutput < 0) {
            System.out.println(actualOutput + " PASS");
        }
        else {
            System.out.println(actualOutput + " FAIL");
        }
        System.out.println();

        //TEST 5
        member1 = new Member("John Doe 1/20/2004 3/30/2023 BRIDGEWATER");
        member2 = new Member("john doe 1/20/2003 3/30/2023 BRIDGEWATER");
        expectedOutput = 0;
        actualOutput = member1.compareTo(member2);
        System.out.println("**Test case #5: comparing member objects who have the same name, but the parameter member name is fully lowercase");
        System.out.print("compareTo() returns: ");
        if (actualOutput == expectedOutput) {
            System.out.println(actualOutput + " PASS");
        }
        else {
            System.out.println(actualOutput + " FAIL");
        }
        System.out.println();

        //TEST 6
        member1 = new Member("John Doe 1/20/2004 3/30/2023 BRIDGEWATER");
        member2 = new Member("JOHN DOE 1/20/2003 3/30/2023 BRIDGEWATER");
        expectedOutput = 0;
        actualOutput = member1.compareTo(member2);
        System.out.println("**Test case #6: comparing member objects who have the same name, but the parameter member name is fully uppercase");
        System.out.print("compareTo() returns: ");
        if (actualOutput == expectedOutput) {
            System.out.println(actualOutput + " PASS");
        }
        else {
            System.out.println(actualOutput + " FAIL");
        }
        System.out.println();

        //TEST 7
        member1 = new Member("John Doe 1/20/2004 3/30/2023 BRIDGEWATER");
        member2 = new Member("Roy Brooks 8/8/1977 9/30/2020 somerville");
        actualOutput = member1.compareTo(member2);
        System.out.println("**Test case #7: comparing member objects who have the same name, " +
                            "but the parameter member has an alphabetically lesser/earlier last name");
        System.out.print("compareTo() returns: ");
        if (actualOutput > 0) {
            System.out.println(actualOutput + " PASS");
        }
        else {
            System.out.println(actualOutput + " FAIL");
        }
        System.out.println();

        //TEST 8
        member1 = new Member("Roy Brooks 8/8/1977 9/30/2020 somerville");
        member2 = new Member("John Doe 1/20/2004 3/30/2023 BRIDGEWATER");
        actualOutput = member1.compareTo(member2);
        System.out.println("**Test case #8: comparing member objects who have the same name, " +
                "but the parameter member has an alphabetically later/greater last name");
        System.out.print("compareTo() returns: ");
        if (actualOutput < 0) {
            System.out.println(actualOutput + " PASS");
        }
        else {
            System.out.println(actualOutput + " FAIL");
        }
        System.out.println();
    }
}
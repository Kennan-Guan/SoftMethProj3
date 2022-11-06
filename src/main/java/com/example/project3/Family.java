package com.example.project3;
/**
 * Defines a Family member to be a part of the gym.
 * This class is a Generalization subclass of the Member class. The additional instance variable
 * that is implemented is guestPasses which is used to track the number of guest passes for an instance
 * of this class.
 * This class has two constructors, one to construct a Family member and one to construct a Premium member
 * which is called in the Premium subclass.
 * This class also overrides the membershipFee() and toString() methods from the Member parent class.
 * @author Adwait Ganguly, Kennan Guan
 */
public class Family extends Member {
    private int guestPasses;

    private static final double FAMILYMONTHLYFEE = 59.99; //static final constant for family monthly fee

    private static final int EXPIREINTHREEMONTHS = 4;

    /**
     * Constructor for a Family member instance based on the command line input string.
     * @param newFamilyMem is the string parsed from the command line with the information of the new Family member.
     */
    public Family (String newFamilyMem) {
        super(newFamilyMem.split(" ")[0], newFamilyMem.split(" ")[1], newFamilyMem.split(" ")[2], newFamilyMem.split(" ")[3], EXPIREINTHREEMONTHS);
        this.guestPasses = 1;
    }

    /**
     * Constructor for a new premium member based on the command line input string. This method is called in the Premium subclass.
     * @param newFamMem is the string parsed from the command line with the information of the new Premium member.
     * @param premiumPasses is the number of guest passes that a Premium member is given.
     * @param expireMonthsLater is the number of months after today that the premium membership expires.
     */
    public Family (String newFamMem, int premiumPasses, int expireMonthsLater) {
        super(newFamMem.split(" ")[0], newFamMem.split(" ")[1], newFamMem.split(" ")[2], newFamMem.split(" ")[3], expireMonthsLater);
        this.guestPasses = premiumPasses;
    }


    /**
     * Method that represents a guest pass being used, thus decreasing the object's guest pass count by one.
     */
    public void useGuestPass() {
        this.guestPasses--;
    }

    /**
     * Method that increases the number of guest passes of a member once a guest has checked out of a class.
     */
    public void returnGuestPasses() {
        this.guestPasses++;
    }

    /**
     * Getter for the member's current number of guest passes.
     * @return the member's current guest pass count.
     */
    public int getGuestPasses() {
        return this.guestPasses;
    }

    /**
     * Getter method for private static final FAMILYMONTHLYFEE constant variable.
     * @return the private static final FAMILYMONTHLYFEE constant variable.
     */
    public double getFamilyMonthlyFee() {
        return FAMILYMONTHLYFEE;
    }

    /**
     * This method overrides the membershipFee method in the Member parent class, incorporating the change in monthly fee price.
     * @return the membership fee for the next billing statement for a Family member.
     */
    @Override
    public double membershipFee() {
        return getOneTimeFee() + (getQuarterly() * FAMILYMONTHLYFEE);
    }

    /**
     * This method overrides the toString method in the Family parent class.
     * @return a string representation of a Family member in the format: "firstName lastname, DOB: mm/dd/yyyy, Membership expires mm/dd/yyyy, Location: City, Zip Code, County, (Family) guest-pass remaining: x"
     */
    @Override
    public String toString() {
        return(this.getFname() + " " + this.getLname() + ", DOB: " + this.getDob().toString() + ", Membership expires "
                + this.getExpire().toString() + ", Location: " + this.getLocation() + ", (Family) guest-pass remaining: " + guestPasses);
    }

    /**
     * Super call to the equals method in the Member parent class.
     * @param obj an object that is not necessarily a Member object, and is used for comparison.
     * @return true if first name, last name, and DOB of objects are all equal, otherwise returns false.
     */
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Super call to the compareTo method in the Member parent class.
     * @param member the object to be compared.
     * @return positive number, 0, or negative number based on if this object's name is alphabetically greater than, equal to, or less than the member parameters name, respectively.
     */
    public int compareTo(Member member) {
        return super.compareTo(member);
    }
}

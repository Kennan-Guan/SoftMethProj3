package com.example.project3;
/**
 * Defines a Premium member to be a part of the gym.
 * This class is a Generalization subclass of the Family class. There are no additional instance variables in this subclass.
 * This class has one constructor to construct a Premium member. This construct contains a super call to a Family constructor.
 * This class also overrides the membershipFee() and toString() methods from the Member parent class. This class also inherits the equals() and compareTo() methods from the parent class.
 * @author Adwait Ganguly, Kennan Guan
 */
public class Premium extends Family {

    private static final int ANNUALFEEMONTHS = 11;

    private static final int PREMIUMPASSES = 3;

    private static final int SETEXPIREYEARLATER = 13;

    /**
     * Constructor for a Premium member instance based on the command line input string. Implements a super() call to Family along with constants that define expiration months and number of guest passes.
     * @param newPremiumMember is the string parsed from the command line with the information of the new Premium member.
     */
    public Premium (String newPremiumMember) {
        super(newPremiumMember, PREMIUMPASSES, SETEXPIREYEARLATER);
    }

    /**
     * This method overrides the membershipFee method in the Family parent class, incorporating the change to an annual fee price and dropping of the one time fee.
     * @return the membership fee for the next billing statement for a Premium member.
     */
    @Override
    public double membershipFee() {
        return getFamilyMonthlyFee() * ANNUALFEEMONTHS;
    }

    /**
     * This method overrides the toString method in the Family parent class.
     * @return a string representation of a Family member in the format: "firstName lastname, DOB: mm/dd/yyyy, Membership expires mm/dd/yyyy, Location: City, Zip Code, County, (Premium) guest-pass remaining: x"
     */
    @Override
    public String toString() {
        return(this.getFname() + " " + this.getLname() + ", DOB: " + this.getDob().toString() + ", Membership expires "
                + this.getExpire().toString() + ", Location: " + this.getLocation() +", (Premium) Guess-pass remaining: " + this.getGuestPasses());
    }

    /**
     * Super call to the equals method in the Family parent class.
     * @param obj an object that is not necessarily a Member object, and is used for comparison.
     * @return true if first name, last name, and DOB of objects are all equal, otherwise returns false.
     */
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Super call to the compareTo method in the Family parent class.
     * @param member is the member object whose last and first name will be compared to this member object.
     * @return positive number, 0, or negative number based on if this object's name is alphabetically greater than, equal to, or less than the member parameters name, respectively.
     */
    public int compareTo(Member member) {
        return super.compareTo(member);
    }

}

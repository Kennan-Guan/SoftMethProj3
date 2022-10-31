package fitness2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test method for FitnessClass.
 * Tests add and removal of guests and members.
 */
class FitnessClassTest {
    @Test
    void add_null_guest() {
        FitnessClass class1 = new FitnessClass(Time.EVENING, "Test", "Test", Location.PISCATAWAY);
        Member guest1 = null;
        assertFalse(class1.addGuest(guest1));

        Member guest2 = new Premium("Jonnathan Wei 9/21/1992 bridgewater");
        assertTrue(class1.addGuest(guest2));
    }

    @Test
    void remove_null_guest() {
        FitnessClass class1 = new FitnessClass(Time.EVENING, "Test", "Test", Location.PISCATAWAY);
        Member guest1 = null;
        assertFalse(class1.removeGuest(guest1));

        Member guest2 = new Premium("Jonnathan Wei 9/21/1992 bridgewater");
        assertFalse(class1.removeGuest(guest2));
        class1.addGuest(guest2);
        assertTrue(class1.removeGuest(guest2));
    }

    @Test
    void add_null_member() {
        FitnessClass class1 = new FitnessClass(Time.EVENING, "Test", "Test", Location.PISCATAWAY);
        Member member1 = null;
        assertFalse(class1.addMember(member1));

        Member member2 = new Member("John Doe 1/20/2004 2/15/2023 BRIDGEWATER");
        assertTrue(class1.addMember(member2));
    }

    @Test
    void removeMember() {
        FitnessClass class1 = new FitnessClass(Time.EVENING, "Test", "Test", Location.PISCATAWAY);
        Member member1 = null;
        assertFalse(class1.removeMember(member1));

        Member member2 = new Member("John Doe 1/20/2004 2/15/2023 BRIDGEWATER");
        assertFalse(class1.removeMember(member2));
        class1.addMember(member2);
        assertTrue(class1.removeMember(member2));
    }
}
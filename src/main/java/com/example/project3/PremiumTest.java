package com.example.project3;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * JUnit test for the Premium class.
 * Tests the membership fee to ensure value is correct.
 */
class PremiumTest {

    @Test
    void membership_fee_constant() {
        Premium member1 = new Premium("Gary Smith 7/2/1989 bridgewater");
        assertEquals(59.99*11, member1.membershipFee());
    }
}
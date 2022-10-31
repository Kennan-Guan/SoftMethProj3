package fitness2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * JUnit test method for the Date class.
 * Tests the isValid method for appropriate values.
 */
public class DateTest {

    @Test
    void valid_month_in_year() {
        Date date1 = new Date("-1/31/2003");
        assertFalse(date1.isValid());

        Date date2 = new Date("13/31/2003");
        assertFalse(date2.isValid());

        Date date3 = new Date("100/8/1977");
        assertFalse(date3.isValid());

        Date date4 = new Date("-135/1/1966");
        assertFalse(date4.isValid());
    }

    @Test
    void day_not_zero_or_negative() {
        Date date1 = new Date("10/-24/2002");
        assertFalse(date1.isValid());

        Date date2 = new Date("13/0/2003");
        assertFalse(date2.isValid());

        Date date3 = new Date("6/10/2000");
        assertTrue(date3.isValid());
    }

    @Test
    void day_in_month_with_31_days() {
        Date date1 = new Date("1/32/1998");
        assertFalse(date1.isValid());

        Date date2 = new Date("3/32/1977");
        assertFalse(date2.isValid());

        Date date3 = new Date("5/32/2001");
        assertFalse(date3.isValid());

        Date date4 = new Date("7/32/1982");
        assertFalse(date4.isValid());

        Date date5 = new Date("8/32/1998");
        assertFalse(date5.isValid());

        Date date6 = new Date("10/32/2005");
        assertFalse(date6.isValid());

        Date date7 = new Date("12/32/1989");
        assertFalse(date7.isValid());

    }

    @Test
    void day_in_month_with_30_days() {
        Date date1 = new Date("4/31/1998");
        assertFalse(date1.isValid());

        Date date2 = new Date("6/31/2002");
        assertFalse(date2.isValid());

        Date date3 = new Date("9/31/1987");
        assertFalse(date3.isValid());

        Date date4 = new Date("11/31/1969");
        assertFalse(date4.isValid());
    }

    @Test
    void year_non_negative() {
        Date date1 = new Date("2/17/-2003");
        assertFalse(date1.isValid());

        Date date2 = new Date("1/14/2003");
        assertTrue(date2.isValid());
    }

    @Test
    void days_in_leap_year() {
        Date date1 = new Date("2/29/2020");
        assertTrue(date1.isValid());

        Date date2 = new Date("2/29/2003");
        assertFalse(date2.isValid());
    }


}
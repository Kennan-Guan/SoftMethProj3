package com.example.project3;
import java.util.Calendar;
/**
 * Defines the date object by splitting a string into three integer values - day, month, and year.
 * This class offers two constructors to create a date with today's date and a date with a specified date.
 * Date also overrides toString and compareTo to allow an accurate string representation and
 * the ability to compare two dates.
 * @author Kennan Guan, Adwait Ganguly
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    private static final int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; //constant array containing days of month corresponding to each month

    private static final int QUADRENNIAL = 4;

    private static final int CENTENNIAL = 100;

    private static final int QUATERCENTENNIAL = 400;

    private static final int DAYSINYEAR = 12;

    private static final int FEBRUARYMONTHNUMBER = 2; //constant used to define number of months in february

    private static final int INDICATESFALSE = -1; //returned in compareTo for when object's year, day, or month, is less than when parameter date

    private static final int CHECKIFEIGHTEEN = 18; //used for subtracting 18 years from today's date

    private static final int EXPIRATIONSMONTHS = 3;

    /**
     * Constructs a date object with today's date.
     * Today's date is define by a year, month, and day of month.
     */
    public Date() {
        Calendar today = Calendar.getInstance();
        year = today.get(Calendar.YEAR);
        month = today.get(Calendar.MONTH) + 1;
        day = today.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Constructs a date object given a date in format "mm/dd/yyyy".
     * @param date a date in format "mm/dd/yyyy".
     */
    public Date(String date) {
        String[] split_date = date.split("/");
        month = Integer.parseInt(split_date[0]);
        day = Integer.parseInt(split_date[1]);
        year = Integer.parseInt(split_date[2]);
    }

    /**
     * Constructs a date object given a date in format "YYYY-MM-DD".
     * @param date a date in format "YYYY-MM-DD"
     * @param DatePickerTrue variable for constructing a date based on the formatting of DatePicker toString() method.
     */
    public Date(String date, int DatePickerTrue) {
        String[] split_date = date.split("-");
        month = Integer.parseInt(split_date[1]);
        day = Integer.parseInt(split_date[2]);
        year = Integer.parseInt(split_date[0]);
    }

    /**
     * Constructs a date object given an expiration.
     * @param setExpiration the expiration timeframe
     */
    public Date(int setExpiration) {
        Calendar today = Calendar.getInstance();
        if (setExpiration > DAYSINYEAR) {
            today.add(Calendar.YEAR, 1);
            year = today.get(Calendar.YEAR);
            month = today.get(Calendar.MONTH) + 1;
            day = today.get(Calendar.DAY_OF_MONTH);
        }
        else {
            today.add(Calendar.MONTH, EXPIRATIONSMONTHS);
            year = today.get(Calendar.YEAR);
            month = today.get(Calendar.MONTH) + 1;
            day = today.get(Calendar.DAY_OF_MONTH);
        }
    }

    /**
     * Compares two dates for ordering and returns an integer value of -1, 0, or 1
     * -1 indicates less than, 0 indicates equality, 1 indicates greater than.
     * @param date a date object with a day, month, and year.
     * @return an integer value of -1, 0, or 1 when object is less than, equal to, or greater than the passed object.
     */
    @Override
    public int compareTo(Date date) {

        if (this.year > date.year) {
            return 1;
        }
        else if (this.year < date.year) {
            return INDICATESFALSE;
        }
        else if (this.month > date.month) {
            return 1;
        }
        else if (this.month < date.month) {
            return INDICATESFALSE;
        }
        else if (this.day > date.day) {
            return 1;
        }
        else if (this.day < date.day) {
            return INDICATESFALSE;
        }

        return 0;
    }

    /**
     * Determines if the date object is valid.
     * @return true is valid, false otherwise.
     */
    public boolean isValid() {
        if (month < 1 || month > DAYSINYEAR || day < 1 || year < 1) {
            return false;
        }

        boolean isLeapYear = (year % QUADRENNIAL == 0 && (year % CENTENNIAL != 0 || year % QUATERCENTENNIAL == 0));

        if (month == FEBRUARYMONTHNUMBER && isLeapYear) {
            return days[1]+1 == day;
        }
        else {
            return day <= days[month-1];
        }
    }

    /**
     * Textual representation of the .Date object.
     * @return a string in the format month/day/year.
     */
    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    /** This method checks if the Date of Birth of the member is the same as the current day or the future.
     * @param dob is the date of birth of the member that is being checked.
     * @return true if the date is in fact today, false otherwise.
     */
    public boolean isFuture(Date dob) {
        Date today = new Date();
        return today.compareTo(dob) == 1;
    }

    /** This method checks that the Member to be added is at least 18 years.
     * @param dob is the date of birth of the member that is being checked.
     * @return true if the Member is eighteen or older, false otherwise.
     */
    public boolean isEighteen(Date dob) {
        Date today = new Date();
        if (dob.year < (today.year - CHECKIFEIGHTEEN)) {
            return true;
        }
        else if (dob.year == (today.year - CHECKIFEIGHTEEN) && dob.month < today.month) {
            return true;
        }
        else if (dob.year == (today.year - CHECKIFEIGHTEEN) && dob.month == today.month && dob.day <= today.day) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Testbed main to test the isValid method
     * @param args arguments from command line, but is unused.
     */
    public static void main(String[] args) {
        Date date = new Date("-1/31/2003"); //Test case #1
        boolean actual = date.isValid();
        System.out.println("**Test case #1a: month must be between 1 and 12**");
        System.out.println("Input: " + date);
        System.out.print("isValid() returns " + actual);
        System.out.println(", " + ((actual == false) ? "PASS" : "FAIL"));

        System.out.println();

        date = new Date("13/31/2003"); //Test case #1
        actual = date.isValid();
        System.out.println("**Test case #1b: month must be between 1 and 12**");
        System.out.println("Input: " + date);
        System.out.print("isValid() returns " + actual);
        System.out.println(", " + ((actual == false) ? "PASS" : "FAIL"));

        System.out.println();

        date = new Date("13/8/1977"); //Test case #1
        actual = date.isValid();
        System.out.println("**Test case #1c: month must be between 1 and 12**");
        System.out.println("Input: " + date);
        System.out.print("isValid() returns " + actual);
        System.out.println(", " + ((actual == false) ? "PASS" : "FAIL"));

        System.out.println();

        date = new Date("10/-24/2002"); //Test case #2
        actual = date.isValid();
        System.out.println("**Test case #2: Day must be greater than 0**");
        System.out.println("Input: " + date);
        System.out.print("isValid() returns " + actual);
        System.out.println(", " + ((actual == false) ? "PASS" : "FAIL"));

        System.out.println();

        date = new Date("3/32/2003"); //Test case #3
        actual = date.isValid();
        System.out.println("**Test case #3a: January, March, May, July, August, October, and December must not have days above 31**");
        System.out.println("Input: " + date);
        System.out.print("isValid() returns " + actual);
        System.out.println(", " + ((actual == false) ? "PASS" : "FAIL"));

        System.out.println();

        date = new Date("12/32/1989"); //Test case #3
        actual = date.isValid();
        System.out.println("**Test case #3b: January, March, May, July, August, October, and December must not have days above 31**");
        System.out.println("Input: " + date);
        System.out.print("isValid() returns " + actual);
        System.out.println(", " + ((actual == false) ? "PASS" : "FAIL"));

        System.out.println();

        date = new Date("4/31/2003"); //Test case #3
        actual = date.isValid();
        System.out.println("**Test case #3: April, June, September, and November must not have days above 30**");
        System.out.println("Input: " + date);
        System.out.print("isValid() returns " + actual);
        System.out.println(", " + ((actual == false) ? "PASS" : "FAIL"));

        System.out.println();

        date = new Date("4/31/2003"); //Test case #4
        actual = date.isValid();
        System.out.println("**Test case #4: April, June, September, and November must not have days above 30**");
        System.out.println("Input: " + date);
        System.out.print("isValid() returns " + actual);
        System.out.println(", " + ((actual == false) ? "PASS" : "FAIL"));

        System.out.println();

        date = new Date("10/24/-2002"); //Test case #5
        actual = date.isValid();
        System.out.println("**Test case #5: Year must be greater than 0**");
        System.out.println("Input: " + date);
        System.out.print("isValid() returns " + actual);
        System.out.println(", " + ((actual == false) ? "PASS" : "FAIL"));

        System.out.println();

        date = new Date("2/29/2020"); //Test case #6
        actual = date.isValid();
        System.out.println("**Test case #6: For a leap year, the day for February can be 29");
        System.out.println("Input: " + date);
        System.out.print("isValid() returns " + actual);
        System.out.println(", " + ((actual == true) ? "PASS" : "FAIL"));

        System.out.println();

        date = new Date("2/29/2003"); //Test case #7
        actual = date.isValid();
        System.out.println("**Test case #7: For a non-leap year, the day for February cannot be 29");
        System.out.println("Input: " + date);
        System.out.print("isValid() returns " + actual);
        System.out.println(", " + ((actual == false) ? "PASS" : "FAIL"));

    }
}
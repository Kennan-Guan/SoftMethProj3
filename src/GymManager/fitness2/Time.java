package fitness2;
import java.text.DecimalFormat;
/**
 * Defines the times of the set fitness classes as morning and afternoon fitness class sessions.
 * Time is defined by hour and minutes
 * This class also provides getter methods for each attribute assigned to the sessions.
 * Format is hours:minutes
 * @author Kennan Guan, Adwait Ganguly
 */
public enum Time {
    MORNING(9, 30),
    AFTERNOON(14,00),
    EVENING(18,30);

    private final int hour;
    private final int minute;

    /**
     * Constructs the Time object with hours and minutes
     * @param hour the hour of day a class takes place.
     * @param minute the minutes into an hour a class takes place.
     */
    Time (int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * Reformats the current Time object's hours and minutes into a clock format.
     * Format is hh:mm, where h is hours and m is minutes.
     * @return the clock representation.
     */
    public String getClock() {
        DecimalFormat formatted = new DecimalFormat("#00");
        return hour + ":" + formatted.format(minute);
    }
}

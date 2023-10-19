package seedu.address.model.person;

/**
 * The `Attendance` class represents a student's attendance record for a 12-week period.
 * It provides methods for managing and querying attendance information.
 */
public class Attendance {
    public static final String TUTORIAL_ERROR_MSG = "Tutorial number is out of range, should be integer between 1-12";
    public static final String ORIGINAL_ATD = "0,0,0,0,0,0,0,0,0,0,0,0";
    private int totalTut;
    private final boolean[] attendanceList = new boolean[12];


    /**
     * Constructs an `Attendance` object from a comma-separated attendance string.
     *
     * @param atd A comma-separated string representing attendance for 1-12 weeks (e.g., "0,1,0,1,0,1,0,1,0,1,0,1").
     */
    public Attendance(String atd) {
        String[] atdArr = atd.split(",");
        this.totalTut = atdArr.length;
        for (int i = 0; i < totalTut; i++) {
            if (atdArr[i].equals("1")) {
                this.attendanceList[i] = true;
            }
        }
    }

    /**
     * Checks if a given week is a valid tutorial number (between 1 and 12).
     *
     * @param tutorial A string representing a tutorial number.
     * @return `true` if the week is valid; otherwise, `false`.
     */
    public static boolean isValidTutorial(String tutorial) {
        if (!tutorial.matches("[0-9]+")) {
            return false;
        }
        int intTutorial = Integer.parseInt(tutorial);
        if (intTutorial <= 0 || intTutorial > 12) {
            System.out.println(tutorial);
            return false;
        }
        return true;
    }

    /**
     * Returns the number of weeks present based on the attendance record.
     *
     * @return The count of weeks marked as present.
     */
    public int getWeeksPresent() {
        int count = 0;
        for (int i = 0; i < 12; i++) {
            if (this.attendanceList[i]) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the total number of tutorial weeks.
     *
     * @return the total number of tutorial weeks.
     */
    public int getTotalWeeks() {
        return this.totalTut;
    }

    /**
     * Marks a specific tutorial as attended.
     *
     * @param tutorial The week to mark as attended (1-12).
     */
    public void markAttendance(int tutorial) {
        this.attendanceList[tutorial] = true;
    }

    /**
     * Unmarks a specific tutorial as attended.
     *
     * @param tutorial The week to unmark (1-12).
     */
    public void unmarkAttendance(int tutorial) {
        this.attendanceList[tutorial] = false;
    }

    /**
     * Checks if a specific tutorial is marked as attended.
     *
     * @param tutorial The week to check (1-12).
     * @return `true` if the week is marked as attended; otherwise, `false`.
     */
    public boolean isMarkedWeek(int tutorial) {
        return this.attendanceList[tutorial];
    }

    /**
     * Converts a tutorial represented as a string to an integer.
     *
     * @param tutorial A string representing a week number.
     * @return The week number as an integer.
     */
    public static int convertToIntegerWeek(String tutorial) {
        return Integer.parseInt(tutorial);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Attendance)) {
            return false;
        }

        Attendance otherAttendance = (Attendance) other;
        return toString().equals(otherAttendance.toString());
    }

    /**
     * Converts attendanceList to a string.
     *
     * @return string version of attendancelist
     */
    public String toString() {
        String s = "";
        for (boolean atd : this.attendanceList) {
            if (atd) {
                s += "1,";
            } else {
                s += "0,";
            }
        }
        return s.substring(0, 22);
    }
}

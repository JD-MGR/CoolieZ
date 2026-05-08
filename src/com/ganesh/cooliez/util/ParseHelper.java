package com.ganesh.cooliez.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ParseHelper {
    private ParseHelper() {}

    public static final String DATE_PATTERN = "dd-MM-yy";
    public static final String DATE_TIME_PATTERN = "dd-MM-yy HH:mm";
    private static final String EMPTY_PLACE_HOLDER = "-";

    /**
     * Parses a string into a non-negative Integer.
     * Trims the input and returns null if the input is not a valid integer or is negative.
     */
    public static Integer parseNonNegativeInt(String input) {
        if (!isValidInput(input)) {
            return null;
        }
        try {
            int value = Integer.parseInt(input.trim());
            return value >= 0 ? value : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Checks if the given user string input is valid (not null, not empty, and not just whitespace).
     */
    public static boolean isValidInput(String input) {
        return input != null && !input.trim().isEmpty();
    }

    /**
     * Parses a string date into a long (milliseconds).
     * Uses the DATE_PATTERN defined in this class.
     */
    public static long parseDateToLong(String dateStr) {
        if (!isValidInput(dateStr)) {
            return -1L;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
            Date date = sdf.parse(dateStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal.getTimeInMillis();
        } catch (ParseException e) {
            return -1L;
        }
    }

    /**
     * Formats milliseconds into a date string using Calendar and DATE_PATTERN.
     */
    public static String formatDate(long millis) {
        if (millis <= 0) {
            return EMPTY_PLACE_HOLDER;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        return sdf.format(cal.getTime());
    }

    /**
     * Formats milliseconds into a date-time string using Calendar and DATE_TIME_PATTERN.
     */
    public static String formatDateTime(long millis) {
        if (millis <= 0) {
            return EMPTY_PLACE_HOLDER;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_PATTERN);
        return sdf.format(cal.getTime());
    }

    /**
     * Calculates age in years between birth date (in millis) and current date.
     */
    public static int calculateAge(long birthDateMillis) {
        Calendar birthDate = Calendar.getInstance();
        birthDate.setTimeInMillis(birthDateMillis);
        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

        if (today.get(Calendar.MONTH) < birthDate.get(Calendar.MONTH) ||
            (today.get(Calendar.MONTH) == birthDate.get(Calendar.MONTH) &&
             today.get(Calendar.DAY_OF_MONTH) < birthDate.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }

        return age;
    }

    /**
     * Checks whether a given date (in millis) is today or in the future.
     */
    public static boolean isTodayOrFuture(long millis) {
        Calendar target = Calendar.getInstance();
        target.setTimeInMillis(millis);
        
        Calendar today = Calendar.getInstance();
        // Set today to start of day for accurate "is today" check
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        return !target.before(today);
    }
}

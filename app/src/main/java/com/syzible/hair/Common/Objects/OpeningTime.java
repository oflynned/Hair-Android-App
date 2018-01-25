package com.syzible.hair.Common.Objects;

import java.util.Calendar;

public class OpeningTime {
    private String day, openingTime, closingTime;

    public OpeningTime(String day, String openingTime, String closingTime) throws OpeningTimeNotFoundException {
        this.day = day;
        this.openingTime= openingTime;
        this.closingTime= closingTime;
    }

    public Calendar getOpeningTime() throws OpeningTimeNotFoundException {
        return getTimeAsDate(day, openingTime);
    }

    public Calendar getClosingTime() throws OpeningTimeNotFoundException {
        return getTimeAsDate(day, closingTime);
    }

    private Calendar getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        return calendar;
    }

    public String getFormattedOpeningTime() {
        return day.substring(0, 1).toUpperCase() + day.substring(1).toLowerCase() + ": " +
                openingTime + " - " + closingTime;
    }

    public boolean isOpenNow() throws OpeningTimeNotFoundException {
        Calendar currentTime = getCurrentTime();
        return currentTime.getTime().getDay() == getOpeningTime().getTime().getDay() &&
                currentTime.after(openingTime) &&
                currentTime.before(closingTime);
    }

    private Calendar getTimeAsDate(String day, String time) throws OpeningTimeNotFoundException {
        String[] separatedTime = time.split(":");
        int hour = Integer.parseInt(separatedTime[0]);
        int minutes = Integer.parseInt(separatedTime[1]);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.DAY_OF_WEEK, getDayOfWeek(day));

        return calendar;
    }

    private int getDayOfWeek(String dayName) throws OpeningTimeNotFoundException {
        switch (dayName) {
            case "sunday":
                return 1;
            case "monday":
                return 2;
            case "tuesday":
                return 3;
            case "wednesday":
                return 4;
            case "thursday":
                return 5;
            case "friday":
                return 6;
            case "saturday":
                return 7;
        }

        throw new OpeningTimeNotFoundException("Day name not found in map");
    }
}

package com.randy.masterspring.date;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by randy on 2017/7/17.
 */
public class USLocalDateFormatter implements Formatter<LocalDate> {

    public static final String US_PATTERN = "MM/DD/YYYY";
    public static final String NORMAL_PATTERN = "dd/MM/yyyy";

    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        return LocalDate.parse(text, DateTimeFormatter.ofPattern(getPattern(locale)));
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        return DateTimeFormatter.ofPattern(getPattern(locale)).format(object);
    }

    public static String getPattern(Locale locale){
        return isUnitedStates(locale)? US_PATTERN:NORMAL_PATTERN;
    }

    private static boolean isUnitedStates(Locale locale) {
        return Locale.US.getCountry().equals(locale.getCountry());
    }
}

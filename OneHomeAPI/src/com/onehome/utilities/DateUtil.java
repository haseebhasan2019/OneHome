package com.onehome.utilities;

import java.text.*;
import java.util.*;

public class DateUtil {
    public static final String DATEFORMAT_MM_DD_YYYY = "MM-dd-yyyy";
    public static final String DATEFORMAT_YYYYMMDD = "yyyy-MM-dd";

    public static Boolean isDateValid(String date)
    {
        try {
            DateFormat df = new SimpleDateFormat(DATEFORMAT_MM_DD_YYYY);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    public static Boolean areDatesInOrder(String movedIn, String movedOut)
    {
        try 
        {
            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            Date in = df.parse(movedIn);
            Date out = df.parse(movedOut);
            return in.before(out);
        } catch (ParseException e) {
            return false;
        }
    }
    public static String reformat(String date, String currentFormat, String finalFormat) 
    {
        DateFormat df = new SimpleDateFormat(currentFormat);
        DateFormat fd = new SimpleDateFormat(finalFormat);

        try {     
            Date d = df.parse(date);
            return fd.format(d);
        } catch (ParseException e) {
            return "";
        }
         
    }
}
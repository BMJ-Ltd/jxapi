package gov.adlnet.xapi;

import static org.junit.Assert.*;
import gov.adlnet.xapi.model.Statement;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;

public class StatementTest
{
    @Test
    public void testGetTimestampAsDate() throws ParseException
    {
        Statement statement = new Statement();
        statement.setTimestamp("2015-09-25T14:20:25.745100+00:00");
        Date date = statement.getTimestampAsDate();

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+00:00"));
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);

        assertEquals(2015, year);
        assertEquals(9, month);
        assertEquals(25, day);
        assertEquals(14, hour);
        assertEquals(20, minute);
        assertEquals(25, second);
    }
}

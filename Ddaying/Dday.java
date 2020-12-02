import java.util.*;
import java.util.Calendar;
import java.io.*;

class Dday {
    Ddaying d;
    Dday(Ddaying d) {
        this.d = d;
    }
    int calcdate(int _year, int _month, int _day) {
        try {
            TimeZone tz = TimeZone.getTimeZone("Asia/Seoul");
            Calendar today = Calendar.getInstance(tz);
            Calendar dday = Calendar.getInstance(tz);

            dday.set(_year, _month - 1, _day);

            long cnt_dday = dday.getTimeInMillis() / 86400000;
            long cnt_today = today.getTimeInMillis() / 86400000;
            long sub = cnt_today - cnt_dday;

            return (int) sub;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
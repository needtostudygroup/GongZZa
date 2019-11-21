package com.dongkyoo.gongzza;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static final String convertDateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssSS");
        return simpleDateFormat.format(date);
    }
}

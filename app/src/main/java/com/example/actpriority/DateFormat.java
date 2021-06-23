package com.example.actpriority;

import android.os.Build;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

public class DateFormat {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @TypeConverter
    public static Date fromStringToDate(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate inputDate = LocalDate.parse(value,formatter);
        return Date.from(inputDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @TypeConverter
    public static String fromDateToString(Date value) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(value);
    }
}
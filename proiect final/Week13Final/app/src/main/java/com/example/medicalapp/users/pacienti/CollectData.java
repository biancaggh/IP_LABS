package com.example.medicalapp.users.pacienti;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class CollectData {
    public static int getAvgHeartrate(Connection c) throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT AVG(HEART_RATE) FROM MI_BAND_ACTIVITY_SAMPLE WHERE HEART_RATE NOT IN (-1, 0, 255);");
        String hr = "0";
        if (rs.next() == false) {
            Log.d("emptyResSet", "resset is empty!");
        } else {
            do {
                hr = rs.getString("AVG(HEART_RATE)");
            } while (rs.next());
        }
        int hr_average = (int) Float.parseFloat(hr);


//        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(Environment.getExternalStorageDirectory().getPath() + "/MibandExport/export.db", null);
//        Cursor cr = db.rawQuery("SELECT AVG(HEART_RATE) FROM MI_BAND_ACTIVITY_SAMPLE WHERE HEART_RATE NOT IN (-1, 0, 255);", null);
//        String hr_average_s = "0";
//        if(cr.moveToFirst())
//        {
//            do {
//                hr_average_s = cr.getString(cr.getColumnIndex("AVG(HEART_RATE)"));
//            }
//            while (cr.moveToNext());
//        }
//        cr.close();
//        int hr_average = Integer.parseInt(hr_average_s);
        return hr_average;
    }

    public static int getTodayAvgHeartrate(Connection c) throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("select avg(a) from (select strftime('%Y.%m.%d', datetime(timestamp, 'unixepoch')) as d, avg(HEART_RATE) as a from MI_BAND_ACTIVITY_SAMPLE where HEART_RATE NOT IN (-1, 0, 255) group by d order by d desc limit 1);");
        int hr_average = rs.getInt("avg(a)");
        return hr_average;
    }

    public static int getAvgSteps(Connection c) throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("select avg(a) from (select strftime('%Y.%m.%d', datetime(timestamp, 'unixepoch')) as d,sum(STEPS)as a from MI_BAND_ACTIVITY_SAMPLE group by d)");
        int stepsAverage = rs.getInt("avg(a)");
        return stepsAverage;
    }

    public static int getTodaySteps(Connection c) throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("select strftime('%Y.%m.%d', datetime(timestamp, 'unixepoch')) as d,sum(STEPS)as a from MI_BAND_ACTIVITY_SAMPLE group by d order by d desc LIMIT 1;");
        String steps = "0";
        if (rs.next() == false) {
            Log.d("emptyResSet", "resset is empty!");
        } else {
            do {
                steps = rs.getString("a");
            } while (rs.next());
        }
        int stepsAverage = (int) Float.parseFloat(steps);

        return stepsAverage;
    }

    public static int getTodayCalorie(Connection c) throws ClassNotFoundException, SQLException {
        int stepsAverage = getTodaySteps(c);
        return (int) (stepsAverage * 0.045);
    }

    public static String getActivityLevel(Connection c) throws ClassNotFoundException, SQLException {
        String result;
        int stepsAverage = getAvgSteps(c);
        if (stepsAverage < 2000)
            result = "Patient has a very low activity level.";
        else if (stepsAverage < 5000)
            result = "Patient has a low activity level.";
        else if (stepsAverage < 10000)
            result = "Patient has a medium activity level.";
        else
            result = "Patient has a high activity level.";
        return result;
    }

    public static List<String> getAbnormalHeartrate(Connection c) throws ClassNotFoundException, SQLException {
        List<String> abnormalHeartrates = new ArrayList<>();
        Statement stmt = null;
        stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT strftime('%Y.%m.%d and time %H:%M', datetime(timestamp, 'unixepoch')) AS Time, HEART_RATE FROM MI_BAND_ACTIVITY_SAMPLE;");
        while (rs.next()) {
            String timestamp = rs.getString("Time");
            int hr = rs.getInt("HEART_RATE");
            if (hr > 125 || hr < 55) {
                if (hr != -1 && hr != 0 && hr != 255)
                    abnormalHeartrates.add("Detected abnormal heart rate reading of " + hr + " at date " + timestamp);
            }
        }
        return abnormalHeartrates;
    }
}

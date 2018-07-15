package com.example.android.meetingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class db extends SQLiteOpenHelper {
    private static db obj;
    public db(Context context) {
        super(context,"meetings", null,1);
    }
    public static db getInstance(Context context) {
        if(obj == null) {
            obj = new db(context);
        }
        return  obj;
    }

    @Override
    public void onCreate(SQLiteDatabase dob) {
        dob.execSQL("CREATE TABLE meetings (id INTEGER PRIMARY KEY,topic TEXT, duration text,date text,timme text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase dob, int oldVersion, int newVersion) {
        dob.execSQL("DROP TABLE IF EXISTS meetings");

        // Create tables again
        onCreate(dob);
    }
    void addMeeting(Meeting meeting) {
        SQLiteDatabase dob = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("topic", meeting.getTopic());
        values.put("duration", meeting.getDuration());
        values.put("time", meeting.getTime());
        values.put("date", meeting.getDate());

        // Inserting Row
        dob.insert("meetings", null, values);
        //2nd argument is String containing nullColumnHack
        dob.close(); // Closing database connection
    }
    public ArrayList<Meeting> getAllMeetings() {
        ArrayList<Meeting> meetingList = new ArrayList<Meeting>();
        // Select All Query
        String selectQuery = "SELECT  * FROM meetings";

        SQLiteDatabase dob = this.getWritableDatabase();
        Cursor cursor = dob.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Meeting meeting = new Meeting();
                meeting.setTopic(cursor.getString(1));
                meeting.setDuration(cursor.getString(2));
                meeting.setDate(cursor.getString(3));
                meeting.setTime(cursor.getString(4));
                // Adding contact to list
                meetingList.add(meeting);
            } while (cursor.moveToNext());
        }

        // return meeting list
        return meetingList;
    }
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM meetings";
        SQLiteDatabase dob = this.getReadableDatabase();
        Cursor cursor = dob.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


}

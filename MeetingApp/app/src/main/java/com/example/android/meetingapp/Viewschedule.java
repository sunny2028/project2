package com.example.android.meetingapp;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.android.meetingapp.R.id.list;

public class Viewschedule extends AppCompatActivity  {

    private ListView listView;
    private adaptor meetingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view);
        db obj=db.getInstance(this);
        ArrayList<Meeting> meetings = obj.getAllMeetings();

        for (Meeting mtng : meetings) {
            String log = "Topic: " + mtng.getTopic() + " ,Duration: " + mtng.getDuration() + " ,Date: " +
                    mtng.getDate() + " ,Time: " + mtng.getTime();
            // Writing Contacts to log
            Log.d("TODOS: ", log);
        }

        listView = findViewById(list);
        meetingAdapter = new adaptor(this,meetings);
        listView.setAdapter(meetingAdapter);
    }


}
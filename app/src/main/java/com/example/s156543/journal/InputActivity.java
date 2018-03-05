package com.example.s156543.journal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

// creates a new entry that can be added to the database
public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    public void addEntry(View view){

        // get entered textvalues
        TextView titleView = findViewById(R.id.title);
        TextView moodView = findViewById(R.id.mood);
        TextView contentView = findViewById(R.id.content);

        String text = Objects.toString(titleView.getText());
        String mood = Objects.toString(moodView.getText());
        String content = Objects.toString(contentView.getText());


        // retrieve date and timestamp
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        Long timestamp = System.currentTimeMillis()/1000; // 1000 to convert from MilliSeconds
        cal.setTimeInMillis(timestamp);
        String date = DateFormat.format("dd-MM-yyyy hh:mm:ss", cal).toString();

        // a new Journal entry is created + inserted in the database, with entered values
        JournalEntry journalEntry = new JournalEntry(text, mood, content, date);

        EntryDatabase instance = EntryDatabase.getInstance(this);
        instance.insert(journalEntry);

    }
}

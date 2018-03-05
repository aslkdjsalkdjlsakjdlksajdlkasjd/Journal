package com.example.s156543.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

// displays the selected entry that the user wants the read
public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // all necessary information for a detailed look at the entry is retrieved from the intent
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String entryTitle = (String) intent.getSerializableExtra("entryTitle");
        String entryMood = (String) intent.getSerializableExtra("entryMood");
        String entryContent = (String) intent.getSerializableExtra("entryContent");

        TextView titleView = this.findViewById(R.id.title);
        TextView moodView = this.findViewById(R.id.mood);
        TextView contentView = this.findViewById(R.id.content);

        titleView.setText(entryTitle);
        moodView.setText(entryMood);
        contentView.setText(entryContent);

    }
}

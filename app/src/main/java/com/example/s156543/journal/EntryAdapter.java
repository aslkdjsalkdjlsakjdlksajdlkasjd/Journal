package com.example.s156543.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

// connects the database entries to the entryrows in the listview
 public class EntryAdapter extends ResourceCursorAdapter {

    public EntryAdapter(Context context, Cursor cursor){
        super(context, R.layout.entry_row, cursor);
        }

    // gets the values to the entries' columns and sets their views correspondingly
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        String title = cursor.getString(cursor.getColumnIndex("title"));
        String mood = cursor.getString(cursor.getColumnIndex("mood"));
        String timestamp = cursor.getString(cursor.getColumnIndex("timestamp"));

        TextView titleView = view.findViewById(R.id.title);
        titleView.setText(title);
        TextView moodView = view.findViewById(R.id.mood);
        moodView.setText(mood);
        TextView timestampView = view.findViewById(R.id.timestamp);
        timestampView.setText(timestamp);

    }
}

// Minor Programmeren, Unit4, Journal: an app in which users can write
//  and save notes about their lives, with a title, mood and date/time.
//  Nina Boelsums 10742670

package com.example.s156543.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    EntryAdapter adapter;
    EntryDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = EntryDatabase.getInstance(getApplicationContext());

        // attach listeners and adapter to the listview
        ListView entryListView = findViewById(R.id.entryList);
        entryListView.setOnItemClickListener(new ListViewClickListener());
        entryListView.setOnItemLongClickListener(new ListViewClickListener());

        Cursor cursor = db.selectAll();
        adapter = new EntryAdapter(this, cursor);

        ListView lv = findViewById(R.id.entryList);
        lv.setAdapter(adapter);

    }


    // triggers inputActivity when user hits FloatingActionButton
    public void clickFAB(View v) {
        View fab = v;
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }

    // updates cursor
    private void updateData(){
        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
        Cursor cursor = db.selectAll();

        adapter.swapCursor(cursor);
    }

    @Override
    // update data when user returns to the main page
    protected void onResume() {
        super.onResume();
        updateData();
        System.out.println("resume update");
    }

    // listeners for the list view
    private class ListViewClickListener implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

        // listener for normal click > triggers detailActivity
        // so the user can read the content of the entry
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor clickCursor = (Cursor) adapterView.getItemAtPosition(i);

            String entryTitle = clickCursor.getString(clickCursor.getColumnIndex("title"));
            String entryMood = clickCursor.getString(clickCursor.getColumnIndex("mood"));
            String entryContent = clickCursor.getString(clickCursor.getColumnIndex("content"));

            Intent intent = new Intent(MainActivity.this, DetailActivity.class);

            intent.putExtra("entryTitle", entryTitle);
            intent.putExtra("entryMood", entryMood);
            intent.putExtra("entryContent", entryContent);
            startActivity(intent);
        }

        @Override
        // listener for lonk click > triggers delete method
        // deletes selected entry
        public boolean onItemLongClick(AdapterView<?> LadapterView, View Lview, int Li, long Ll) {

            Cursor longClickCursor = (Cursor) LadapterView.getItemAtPosition(Li);
            long entryId = longClickCursor.getLong(longClickCursor.getColumnIndex("_id"));

            db.delete(entryId);
            System.out.println(entryId);
            updateData();

            return  true;
        }
    }
}

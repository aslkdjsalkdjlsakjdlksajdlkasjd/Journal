package com.example.s156543.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import java.util.Objects;

// this is where all the database magic happens
public class EntryDatabase extends SQLiteOpenHelper {
    private static EntryDatabase instance;

    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    // creates table for journal entries, with relevant columns
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        SQLiteDatabase db = sqLiteDatabase;
        String query = "CREATE TABLE entries (_id INTEGER PRIMARY KEY, title STRING, mood STRING, content STRING, timestamp TIMESTAMP);";
        db.execSQL(query);

        String samples = "INSERT INTO entries (title, mood, content, timestamp) VALUES ('voorbeeld', 'spannend', 'ja hoi dit is een voorbeeld', '5-3-2018');";
        db.execSQL(samples);

    }

    public static EntryDatabase getInstance(Context context){

        if(instance == null){
            instance = new EntryDatabase(context, null, null, 1);
        }
        return instance;

    }

    // grabs all journal entries
    public Cursor selectAll(){
        SQLiteDatabase selectAlldb = this.getWritableDatabase();
        Cursor cursor = selectAlldb.rawQuery("SELECT * FROM entries", null);
        return cursor;
    }

    @Override
    // cleans slate when updated
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        SQLiteDatabase db = sqLiteDatabase;

        String drop = "DROP DATABASE IF EXISTS entries";
        db.execSQL(drop);
        onCreate(db);

    }

    // insert new journal entry in the entries database
    public void insert(JournalEntry entry){

        SQLiteDatabase entrydb =  this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("title", entry.title);
        contentValues.put("mood", entry.mood);
        contentValues.put("content", entry.content);
        contentValues.put("timestamp", entry.timestamp);

        entrydb.insert("entries", null, contentValues);


    }
    // delete selected entry
    public void delete(long id){
        SQLiteDatabase entrydb =  this.getWritableDatabase();
        String idString = Objects.toString(id);
        entrydb.execSQL("DELETE FROM entries WHERE _id =" + idString + ";");
    }

}

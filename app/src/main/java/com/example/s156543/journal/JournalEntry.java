package com.example.s156543.journal;

import java.io.Serializable;

// class for the entries in the journal
public class JournalEntry implements Serializable{
        String title;
        String content;
        String mood;
        String timestamp;

    public JournalEntry(String title,  String mood, String content, String timestamp) {
        this.title = title;
        this.content = content;
        this.mood = mood;
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getMood() {
        return mood;
    }

    public String getTimestamp() {
        return timestamp;
    }
}

package com.example.vitaly.yandexapplication.AsyncTasks;

import android.os.AsyncTask;

import com.example.vitaly.yandexapplication.DatabaseHelper;
import com.example.vitaly.yandexapplication.ListNote;
import com.example.vitaly.yandexapplication.ListNoteAdapter;

import java.util.ArrayList;

public class NoteSaverAsync extends ListHandlerAsync<ListNote, Void> {

    public NoteSaverAsync(ArrayList<ListNote> listNote, ListNoteAdapter listNoteAdapter, DatabaseHelper databaseHelper) {
        super(listNote, listNoteAdapter, databaseHelper);
    }

    @Override
    protected Void doInBackground(ListNote... listNotes) {
        this.databaseHelper.addNote(listNotes[0]);
        return null;
    }
}

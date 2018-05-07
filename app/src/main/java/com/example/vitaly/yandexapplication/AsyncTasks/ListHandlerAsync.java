package com.example.vitaly.yandexapplication.AsyncTasks;

import android.os.AsyncTask;

import com.example.vitaly.yandexapplication.DatabaseHelper;
import com.example.vitaly.yandexapplication.ListNote;
import com.example.vitaly.yandexapplication.ListNoteAdapter;

import java.util.ArrayList;

public abstract class ListHandlerAsync <T, F> extends AsyncTask<T, Void, F> {

    protected final ArrayList<ListNote> listNote;
    protected final ListNoteAdapter listNoteAdapter;
    protected final DatabaseHelper databaseHelper;

    public ListHandlerAsync(
            ArrayList<ListNote> listNote,
            ListNoteAdapter listNoteAdapter,
            DatabaseHelper databaseHelper) {
        this.listNote = listNote;
        this.listNoteAdapter = listNoteAdapter;
        this.databaseHelper = databaseHelper;
    }

}

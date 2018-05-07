package com.example.vitaly.yandexapplication.AsyncTasks;

import com.example.vitaly.yandexapplication.DatabaseHelper;
import com.example.vitaly.yandexapplication.ListNote;
import com.example.vitaly.yandexapplication.ListNoteAdapter;

import java.util.ArrayList;

public class NoteUpdateAsync extends ListHandlerAsync<ListNote, Void> {
    public NoteUpdateAsync(ArrayList<ListNote> listNote, ListNoteAdapter listNoteAdapter, DatabaseHelper databaseHelper) {
        super(listNote, listNoteAdapter, databaseHelper);
    }

    @Override
    protected Void doInBackground(ListNote... listNotes) {
        databaseHelper.updateNote(listNotes[0]);
        return null;
    }
}

package com.example.vitaly.yandexapplication.AsyncTasks;

import com.example.vitaly.yandexapplication.DatabaseHelper;
import com.example.vitaly.yandexapplication.ListNote;
import com.example.vitaly.yandexapplication.ListNoteAdapter;

import java.util.ArrayList;

public class NoteRemoveAsync extends ListHandlerAsync<ListNote, Void> {
    public NoteRemoveAsync(ArrayList<ListNote> listNote, ListNoteAdapter listNoteAdapter, DatabaseHelper databaseHelper) {
        super(listNote, listNoteAdapter, databaseHelper);
    }

    @Override
    protected Void doInBackground(ListNote... listNotes) {
        databaseHelper.deleteNote(listNotes[0]);
        return null;
    }
}

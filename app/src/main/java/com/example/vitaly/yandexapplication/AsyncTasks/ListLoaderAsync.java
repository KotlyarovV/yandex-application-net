package com.example.vitaly.yandexapplication.AsyncTasks;

import android.os.AsyncTask;

import com.example.vitaly.yandexapplication.DatabaseHelper;
import com.example.vitaly.yandexapplication.ListNote;
import com.example.vitaly.yandexapplication.ListNoteAdapter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ListLoaderAsync extends ListHandlerAsync<Void, ArrayList<ListNote>>{

    public ListLoaderAsync(ArrayList<ListNote> listNote, ListNoteAdapter listNoteAdapter, DatabaseHelper databaseHelper) {
        super(listNote, listNoteAdapter, databaseHelper);
    }

    @Override
    protected ArrayList<ListNote> doInBackground(Void... voids) {
        ArrayList<ListNote> listNotes = new ArrayList<>();
        try {
            List<ListNote> dataBaseNotes = databaseHelper.getAllNodes();
            listNotes.addAll(dataBaseNotes);
        } catch (ParseException p) {

        }
        return listNotes;

    }

    @Override
    protected void onPostExecute(ArrayList<ListNote> listNotes) {
        this.listNote.addAll(listNotes);
        this.listNoteAdapter.notifyDataSetChanged();
    }
}

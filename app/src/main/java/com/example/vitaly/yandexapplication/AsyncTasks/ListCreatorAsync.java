package com.example.vitaly.yandexapplication.AsyncTasks;

import android.content.AsyncTaskLoader;
import android.graphics.Color;
import android.os.AsyncTask;

import com.example.vitaly.yandexapplication.DatabaseHelper;
import com.example.vitaly.yandexapplication.ListNote;
import com.example.vitaly.yandexapplication.ListNoteAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListCreatorAsync extends ListHandlerAsync<Void, ArrayList<ListNote>> {

    public ListCreatorAsync(ArrayList<ListNote> listNote, ListNoteAdapter listNoteAdapter, DatabaseHelper databaseHelper) {
        super(listNote, listNoteAdapter, databaseHelper);
    }

    @Override
    protected ArrayList<ListNote> doInBackground(Void... voids) {
        ArrayList<ListNote> notesToAdd = new ArrayList<>();
        for (int i = 0; i < 50000; i++) {
            if (i < 10000)
                notesToAdd.add(new ListNote(Color.LTGRAY, "aa", "bb", new Date()));
            if (i >= 10000 && i < 20000)
                notesToAdd.add(new ListNote(Color.BLACK, "ca", "bb", new Date(1525628000)));
            if (i >= 20000 && i < 30000)
                notesToAdd.add(new ListNote(Color.GREEN, "da", "bb", new Date(1525467600)));
            if (i >= 30000 && i < 40000)
                notesToAdd.add(new ListNote(Color.BLUE, "fa", "bb", new Date(1525381200)));
            if (i > 40000)
                notesToAdd.add(new ListNote(Color.BLUE, "fa", "bb", new Date(1525294800)));
        }
        databaseHelper.addNotes(notesToAdd);
      //  databaseHelper.deleteNotes(true);
        return notesToAdd;
    }

    public void unLink() {

    }


    @Override
    protected void onPostExecute(ArrayList<ListNote> listNotes) {
        //super.onPostExecute(listNotes);
        this.listNote.addAll(listNotes);
        this.listNoteAdapter.notifyDataSetChanged();
    }
}

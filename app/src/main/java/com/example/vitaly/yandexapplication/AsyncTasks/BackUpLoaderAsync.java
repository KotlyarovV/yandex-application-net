package com.example.vitaly.yandexapplication.AsyncTasks;

import com.example.vitaly.yandexapplication.DatabaseHelper;
import com.example.vitaly.yandexapplication.ListNote;
import com.example.vitaly.yandexapplication.ListNoteAdapter;
import com.example.vitaly.yandexapplication.MainActivity;
import com.example.vitaly.yandexapplication.MemoryHelper;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class BackUpLoaderAsync extends BackUpHandlerAsync<Void, Object> {

    private DatabaseHelper databaseHelper;
    private ArrayList<ListNote> listNotes;
    private ListNoteAdapter adapter;

    public BackUpLoaderAsync(
            MemoryHelper memoryHelper,
            MainActivity activity,
            DatabaseHelper databaseHelper,
            ArrayList<ListNote> listNotes,
            ListNoteAdapter adapter) {
        super(memoryHelper, activity);
        this.databaseHelper = databaseHelper;
        this.listNotes = listNotes;
        this.adapter = adapter;
    }

    @Override
    protected Object doInBackground(Void... voids) {
        try {
            ArrayList<ListNote> notesFromBackUp = memoryHelper.readFromBackUp();
            databaseHelper.deleteNotes(false);
            databaseHelper.addNotes(notesFromBackUp);
            return notesFromBackUp;
        } catch (ParseException e) {
            return "Can't parse file.";
        } catch (IOException a) {
            return "Can't read backup file.";
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        if (o instanceof ArrayList) {
            ArrayList<ListNote> listNotes = (ArrayList<ListNote>) o;
            this.listNotes.clear();
            this.listNotes.addAll(listNotes);
            this.adapter.notifyDataSetChanged();
            activity.showToast("Notes were downloaded from backup");
        }
        else if (o instanceof String) {
            activity.showToast((String) o);
        }
    }
}

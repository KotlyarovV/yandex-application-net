package com.example.vitaly.yandexapplication.AsyncNetTasks;

import android.os.AsyncTask;
import android.util.Pair;

import com.example.vitaly.yandexapplication.DatabaseHelper;
import com.example.vitaly.yandexapplication.ListNote;
import com.example.vitaly.yandexapplication.ListNoteAdapter;
import com.example.vitaly.yandexapplication.NetHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class NetLoaderAsync extends AsyncTask<Void, Void, HashMap<String, ListNote>> {

    protected final ArrayList<ListNote> listNotes;
    protected final ListNoteAdapter listNoteAdapter;
    protected final DatabaseHelper databaseHelper;
    protected final NetHelper netHelper;

    public NetLoaderAsync(
            ArrayList<ListNote> listNotes,
            ListNoteAdapter listNoteAdapter,
            DatabaseHelper databaseHelper,
            NetHelper netHelper) {
        this.listNotes = listNotes;
        this.listNoteAdapter = listNoteAdapter;
        this.databaseHelper = databaseHelper;
        this.netHelper = netHelper;
    }

    @Override
    protected HashMap<String, ListNote> doInBackground(Void... voids) {
        try {
            ArrayList<ListNote> notes = netHelper.getAllNotes();
            HashMap<String, ListNote> noteMap = new HashMap<>();
            for (ListNote note : notes) {
                noteMap.put(note.getUuid(), note);
                databaseHelper.saveOrUpdate(note);
            }
            return noteMap;
        } catch (IOException e) {}
        return null;
    }

    @Override
    protected void onPostExecute(HashMap<String, ListNote> notes) {
        if (notes != null) {
            for (ListNote listNote : listNotes) {
                String uuid = listNote.getUuid();
                if (notes.containsKey(uuid)) {
                    ListNote noteFromNet = notes.get(uuid);
                    listNote.setCaption(noteFromNet.getCaption());
                    listNote.setDescription(noteFromNet.getDescription());
                    listNote.setColor(noteFromNet.getColor());
                    notes.remove(uuid);
                }
            }
            listNotes.addAll(notes.values());
            listNoteAdapter.notifyDataSetChanged();
        }
    }
}

package com.example.vitaly.yandexapplication.AsyncTasks;

import com.example.vitaly.yandexapplication.DatabaseHelper;
import com.example.vitaly.yandexapplication.FiltrationMode;
import com.example.vitaly.yandexapplication.ListNote;
import com.example.vitaly.yandexapplication.ListNoteAdapter;
import com.example.vitaly.yandexapplication.SortOption;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ListSelecterAsync extends ListHandlerAsync<Object, List<ListNote>> {
    public ListSelecterAsync(ArrayList<ListNote> listNote, ListNoteAdapter listNoteAdapter, DatabaseHelper databaseHelper) {
        super(listNote, listNoteAdapter, databaseHelper);
    }

    @Override
    protected List<ListNote> doInBackground(Object... objects) {
        FiltrationMode filtrationMode = (FiltrationMode) objects[0];
        SortOption sortOption = (SortOption) objects[1];
        String[] filtrationArgs = (String[]) objects[2];

        try {
            return databaseHelper.getAllSelected(
                    filtrationMode,
                    sortOption,
                    filtrationArgs
            );
        } catch (ParseException p) {}
        return null;
    }

    @Override
    protected void onPostExecute(List<ListNote> listNotes) {
        this.listNote.clear();
        this.listNote.addAll(listNotes);
        listNoteAdapter.notifyDataSetChanged();
    }
}

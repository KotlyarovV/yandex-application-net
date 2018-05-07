package com.example.vitaly.yandexapplication.AsyncTasks;

import com.example.vitaly.yandexapplication.DatabaseHelper;
import com.example.vitaly.yandexapplication.ListNote;
import com.example.vitaly.yandexapplication.ListNoteAdapter;
import com.example.vitaly.yandexapplication.SortMode;
import com.example.vitaly.yandexapplication.SortOption;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ListSorterAsync extends ListHandlerAsync<Object, List<ListNote>> {
    public ListSorterAsync(ArrayList<ListNote> listNote, ListNoteAdapter listNoteAdapter, DatabaseHelper databaseHelper) {
        super(listNote, listNoteAdapter, databaseHelper);
    }

    @Override
    protected List<ListNote> doInBackground(Object... objects) {
        SortMode sortMode = (SortMode) objects[0];
        SortOption sortOption = (SortOption) objects[1];
        try {
            return databaseHelper.getAllSorted(sortOption, sortMode);
        } catch (ParseException e) {

        }
        return new ArrayList<>();
    }

    @Override
    protected void onPostExecute(List<ListNote> listNotes) {
        this.listNote.clear();
        this.listNote.addAll(listNotes);
        listNoteAdapter.notifyDataSetChanged();
    }
}

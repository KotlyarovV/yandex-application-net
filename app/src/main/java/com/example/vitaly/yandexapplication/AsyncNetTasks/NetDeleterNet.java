package com.example.vitaly.yandexapplication.AsyncNetTasks;

import android.os.AsyncTask;

import com.example.vitaly.yandexapplication.ListNote;
import com.example.vitaly.yandexapplication.NetHelper;

import org.json.JSONException;

import java.io.IOException;

public class NetDeleterNet extends AsyncTask<ListNote, Void, Void> {

    private NetHelper netHelper;

    public NetDeleterNet(NetHelper netHelper) {
        this.netHelper = netHelper;
    }

    @Override
    protected Void doInBackground(ListNote... listNotes) {
        try {
            netHelper.deleteNote(listNotes[0]);
        }
        catch (IOException e) {}
        catch (JSONException e) {}
        return null;
    }
}

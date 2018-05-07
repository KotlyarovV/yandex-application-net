package com.example.vitaly.yandexapplication.AsyncNetTasks;

import android.os.AsyncTask;

import com.example.vitaly.yandexapplication.ListNote;
import com.example.vitaly.yandexapplication.NetHelper;

import org.json.JSONException;

import java.io.IOException;

public class NetChangerAsync extends AsyncTask<ListNote, Void, Void> {

    private NetHelper netHelper;

    public NetChangerAsync(NetHelper netHelper) {
        this.netHelper = netHelper;
    }

    @Override
    protected Void doInBackground(ListNote... listNotes) {
        try {
            netHelper.changeNote(listNotes[0]);
        }
        catch (IOException e) {}
        catch (JSONException e) {}
        return null;
    }
}

package com.example.vitaly.yandexapplication.AsyncTasks;

import com.example.vitaly.yandexapplication.MainActivity;
import com.example.vitaly.yandexapplication.MemoryHelper;

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;

public class BackUpSaverAsync extends BackUpHandlerAsync<Void, String> {
    public BackUpSaverAsync(MemoryHelper memoryHelper, MainActivity activity) {
        super(memoryHelper, activity);
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            memoryHelper.makeBackUp();
        } catch (JSONException e) {
            return "Can't make backup. Format problem.";
        } catch (IOException a) {
            return "Can't write backup to file.";
        } catch (OutOfMemoryError a) {
            return "There is too many notes, can't backup";
        }
        return "Backup was made successfully";
    }

    @Override
    protected void onPostExecute(String s) {
        activity.showToast(s);
    }
}

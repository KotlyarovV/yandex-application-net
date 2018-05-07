package com.example.vitaly.yandexapplication.AsyncTasks;

import android.os.AsyncTask;

import com.example.vitaly.yandexapplication.MainActivity;
import com.example.vitaly.yandexapplication.MemoryHelper;

public abstract class BackUpHandlerAsync<T, I> extends AsyncTask<T, Void, I> {
    protected MemoryHelper memoryHelper;
    protected MainActivity activity;

    public BackUpHandlerAsync(MemoryHelper memoryHelper, MainActivity activity) {
        this.activity = activity;
        this.memoryHelper = memoryHelper;
    }
}

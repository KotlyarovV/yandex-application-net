package com.example.vitaly.yandexapplication;

import android.graphics.Color;
import android.os.Environment;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Vitaly on 01.05.2018.
 */

public class MemoryHelper {

    private final static String FILE_NAME = "itemlist.ili";
    private ArrayList<ListNote> notes;

    public MemoryHelper(ArrayList<ListNote> notes) {
        this.notes = notes;
    }
    private JsonHandler jsonHandler = new JsonHandler();

    private void writeFile(String text) throws IOException {

        File extStore = Environment.getExternalStorageDirectory();
        String path = extStore.getAbsolutePath() + "/" + FILE_NAME;

        File myFile = new File(path);
        myFile.createNewFile();
        FileOutputStream fOut = new FileOutputStream(myFile);
        OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
        myOutWriter.append(text);
        myOutWriter.close();
        fOut.close();
    }

    private String readFile() throws IOException{

        File extStore = Environment.getExternalStorageDirectory();
        String path = extStore.getAbsolutePath() + "/" + FILE_NAME;

        String s = "";
        String fileContent = "";
        File myFile = new File(path);
        FileInputStream fIn = new FileInputStream(myFile);
        BufferedReader myReader = new BufferedReader(
                new InputStreamReader(fIn));

        while ((s = myReader.readLine()) != null) {
            fileContent += s + "\n";
        }
        myReader.close();
        return fileContent.substring(0, fileContent.length() - 1);
    }

    public void makeBackUp() throws JSONException, IOException{
        String jsonString = jsonHandler.getJsonString(notes);
        writeFile(jsonString);
    }

    public ArrayList<ListNote> readFromBackUp() throws IOException, ParseException {
        String jsonString = readFile();
        return jsonHandler.getNotesFromJson(jsonString);
    }
}

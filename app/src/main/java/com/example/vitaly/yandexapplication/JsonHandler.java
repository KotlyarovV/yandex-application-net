package com.example.vitaly.yandexapplication;

import android.graphics.Color;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class JsonHandler {

    public JsonObject getJsonNote(ListNote listNote) throws JSONException {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("title", listNote.getCaption());
        jsonObject.addProperty("description", listNote.getDescription());
        String hexColor = String.format("#%06X", (0xFFFFFF & listNote.getColor()));
        jsonObject.addProperty("color", hexColor);
        jsonObject.addProperty("created", listNote.getCreationDateString());
        jsonObject.addProperty("edited", listNote.getEditingDateString());
        jsonObject.addProperty("viewed", listNote.getViewingDateString());
        jsonObject.addProperty("uuid", listNote.getUuid());
        return jsonObject;
    }

    public JsonObject getJsonNoteFromNet(ListNote listNote) throws JSONException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid", listNote.getUuid());
        jsonObject.addProperty("title", listNote.getCaption());
        jsonObject.addProperty("content", listNote.getDescription());
        String hexColor = String.format("#%06X", (0xFFFFFF & listNote.getColor()));
        jsonObject.addProperty("color", hexColor);
        return jsonObject;

    }

    public JsonArray getJsonNotes(List<ListNote> listNotes) throws JSONException {
        JsonArray jsonArray = new JsonArray();
        for (ListNote listNote : listNotes) {
            JsonObject jsonObject = getJsonNote(listNote);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    public String getJsonString(List<ListNote> listNotes) throws JSONException {
        return getJsonNotes(listNotes).toString();
    }

    public ArrayList<ListNote> getNotesFromJson(String jsonNotes) throws ParseException {
        ArrayList<ListNote> listNotes = new ArrayList<>();
        JsonParser parser = new JsonParser();
        JsonElement json = parser.parse(jsonNotes);
        JsonArray jsonArray = json.getAsJsonArray();

        Iterator<JsonElement> iterator =  jsonArray.iterator();
        while (iterator.hasNext()) {
            JsonElement jsonElement = iterator.next();

            int color = Color.parseColor(jsonElement.getAsJsonObject().get("color").getAsString());
            String viewed = jsonElement.getAsJsonObject().get("viewed").getAsString();
            String created = jsonElement.getAsJsonObject().get("created").getAsString();
            String edited = jsonElement.getAsJsonObject().get("edited").getAsString();
            String title = jsonElement.getAsJsonObject().get("title").getAsString();
            String description = jsonElement.getAsJsonObject().get("description").getAsString();
            String uuid = jsonElement.getAsJsonObject().get("uuid").getAsString();
            listNotes.add(new ListNote(color, title, description, created, edited, viewed, uuid));
        }
        return listNotes;
    }

    public ArrayList<ListNote> getNotesFromServerJson(String serverJson) throws ParseException {
        ArrayList<ListNote> listNotes = new ArrayList<>();
        JsonParser parser = new JsonParser();
        JsonElement json = parser.parse(serverJson);
        JsonArray jsonArray = json.getAsJsonArray();

        Iterator<JsonElement> iterator =  jsonArray.iterator();
        while (iterator.hasNext()) {
            JsonElement jsonElement = iterator.next();


            String title = jsonElement.getAsJsonObject().get("title").getAsString();
            String description = jsonElement.getAsJsonObject().get("content").getAsString();
            String uuid = jsonElement.getAsJsonObject().get("uid").getAsString();

            int color = Color.WHITE;
            if (jsonElement.getAsJsonObject().has("color"))
                color = Color.parseColor(jsonElement.getAsJsonObject().get("color").getAsString());

            ListNote listNote = new ListNote(color, title, description, new Date(), uuid);



            listNotes.add(listNote);
        }
        return listNotes;
    }
}

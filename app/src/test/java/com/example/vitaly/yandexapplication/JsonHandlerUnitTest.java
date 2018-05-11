package com.example.vitaly.yandexapplication;

import android.graphics.Color;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ Color.class })
public class JsonHandlerUnitTest {


    private JsonHandler jsonHandler;
    private ListNote standardNote;
    private JsonParser jsonParser;
    private String standardJsonStringFromArray = "[" +
            "{\"title\":\"aa\",\"description\":\"bb\",\"color\":\"#000000\",\"created\":\"2018-04-20T22:41:24+0500\",\"edited\":\"2018-04-20T22:41:24+0500\",\"viewed\":\"2018-04-20T22:41:24+0500\",\"uuid\":\"uuid\"}," +
            "{\"title\":\"ff\",\"description\":\"dd\",\"color\":\"#000000\",\"created\":\"2018-04-20T22:41:24+0500\",\"edited\":\"2018-04-20T22:41:24+0500\",\"viewed\":\"2018-04-20T22:41:24+0500\",\"uuid\":\"uuid\"}" +
            "]";

    @Before
    public void beforeAnyTest() throws ParseException{
        jsonHandler = new JsonHandler();
        String date = "2018-04-20T22:41:24+0500";
        standardNote = new ListNote(
                Color.BLACK,
                "aa",
                "bb",
                date,
                date,
                date,
                "uuid");

        jsonParser = new JsonParser();
        PowerMockito.mockStatic(Color.class);
        PowerMockito.when(Color.parseColor(any(String.class))).thenReturn(Color.BLACK);

    }

    private String getHexFromColor(int color) {
        return String.format("#%06X", (0xFFFFFF & color));
    }

    private ArrayList<ListNote> createArrayListWithNotes() throws ParseException{
        String date = "2018-04-20T22:41:24+0500";
        ListNote listNoteFirst = new ListNote(
                Color.BLACK,
                "aa",
                "bb",
                date,
                date,
                date,
                "uuid");
        ListNote listNoteSecond = new ListNote(
                Color.BLACK,
                "ff",
                "dd",
                date,
                date,
                date,
                "uuid");
        ArrayList<ListNote> listNotes = new ArrayList<>();
        listNotes.add(listNoteFirst);
        listNotes.add(listNoteSecond);
        return listNotes;
    }

    @Test
    public void serializeOneNote_shouldCreate_jsonObject() throws ParseException, JSONException {
        JsonObject json = jsonHandler.getJsonNote(standardNote);
        String expectedJson = "{\"title\":\"aa\",\"description\":\"bb\",\"color\":\"#000000\",\"created\":\"2018-04-20T22:41:24+0500\",\"edited\":\"2018-04-20T22:41:24+0500\",\"viewed\":\"2018-04-20T22:41:24+0500\",\"uuid\":\"uuid\"}";
        JsonObject jsonObject = jsonParser.parse(expectedJson).getAsJsonObject();

        assertEquals(json, jsonObject);
    }

    @Test
    public void serializeListOfNotes_shouldCreate_jsonArray()  throws ParseException, JSONException {

        ArrayList<ListNote> listNotes = createArrayListWithNotes();
        JsonArray jsonArray = jsonHandler.getJsonNotes(listNotes);

        JsonArray jsonArrayExpected = jsonParser.parse(standardJsonStringFromArray).getAsJsonArray();
        assertEquals(jsonArray, jsonArrayExpected);

    }

    @Test
    public void serializeJsonFromNet_shouldCreate_jsonObject() throws ParseException, JSONException {

        JsonObject jsonObject = jsonHandler.getJsonNoteFromNet(standardNote);
        String expectedJsonStringForServer = "{\"uid\":\"uuid\",\"title\":\"aa\",\"content\":\"bb\",\"color\":\"#000000\"}";
        JsonObject expectedJsonObject = jsonParser.parse(expectedJsonStringForServer).getAsJsonObject();
        assertEquals(jsonObject, expectedJsonObject);

    }

    @Test
    public void getJsonString_shouldCreate_jsonString() throws ParseException, JSONException{
        ArrayList<ListNote> listNotes = createArrayListWithNotes();
        String jsonString = jsonHandler.getJsonString(listNotes);
        assertEquals(jsonString, standardJsonStringFromArray);
    }



    @Test
    public void getNotesFromJson_shouldCreate_arrayListOfNotes () throws ParseException, JSONException {
        ArrayList<ListNote> listNotes = jsonHandler.getNotesFromJson(standardJsonStringFromArray);
        assertArrayEquals(listNotes.toArray(), createArrayListWithNotes().toArray());
    }



    @Test
    public void getNotesFromServerJson_shouldCreate_arrayListOfNotes() throws ParseException{
        String expectedJsonStringForServer = "[" +
                "{\"uid\":\"uuid\",\"title\":\"aa\",\"content\":\"bb\",\"color\":\"#000000\"}," +
                "{\"uid\":\"uuid\",\"title\":\"ff\",\"content\":\"dd\",\"color\":\"#000000\"}" +
                "]";

        ArrayList<ListNote> noteArrayList = jsonHandler.getNotesFromServerJson(expectedJsonStringForServer);
        assertArrayEquals(noteArrayList.toArray(), createArrayListWithNotes().toArray());
    }




}
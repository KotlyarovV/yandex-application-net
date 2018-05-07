package com.example.vitaly.yandexapplication;

import android.net.UrlQuerySanitizer;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.lang.reflect.GenericArrayType;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

public class NetHelper {

    private static final String GET_ALL_NOTES_URL = "http://notes.mrdekk.ru/notes";
    private static final String AUTHORIZATION = "Bearer Abracadabra";
    private JsonHandler jsonHandler = new JsonHandler();

    private String readFromInput (InputStream inputStream) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        String x = "";
        x = r.readLine();
        String total = "";

        while(x!= null){
            total += x;
            x = r.readLine();
        }
        return total;
    }

    public ArrayList<ListNote> getAllNotes()
            throws MalformedURLException, IOException {
        URL url = new URL(GET_ALL_NOTES_URL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        httpURLConnection.setRequestProperty("Authorization", AUTHORIZATION);

        try {
            InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
            String answer = readFromInput(in);
            return jsonHandler.getNotesFromServerJson(answer);

        } catch (ParseException e) {}
        finally {
            httpURLConnection.disconnect();
        }
        return null;
    }


    public boolean sendRequest(ListNote note, String method, String urlStr) throws MalformedURLException, IOException, JSONException{
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(method);
        con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        con.setRequestProperty("Authorization", AUTHORIZATION);
        String noteJson = jsonHandler.getJsonNoteFromNet(note).toString();

        if (!method.equals("DELETE")) {
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());

            byte[] buf = noteJson.getBytes("UTF-8");
            wr.write(buf, 0, buf.length);
            wr.flush();
            wr.close();
        }
        int responseCode = con.getResponseCode();
        return responseCode == 400;
    }

    public boolean putNewNote(ListNote note)  throws MalformedURLException, IOException, JSONException{
        return sendRequest(note, "POST", GET_ALL_NOTES_URL);
    }

    public boolean changeNote(ListNote note) throws MalformedURLException, IOException, JSONException {
        return sendRequest(note, "PUT", GET_ALL_NOTES_URL + "/" + note.getUuid());
    }

    public boolean deleteNote(ListNote note) throws MalformedURLException, IOException, JSONException {
        return sendRequest(note, "DELETE", GET_ALL_NOTES_URL + "/" + note.getUuid());
    }

}

package com.example.vitaly.yandexapplication;

import android.graphics.Color;
import android.graphics.Shader;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

/**
 * Created by Vitaly on 17.03.2018.
 */

public class ListNote implements Serializable{


    private String caption;
    private String description;
    private int color;
    private Date creationDate;
    private Date editingDate;
    private Date viewingDate;
    private String uuid;

    private transient SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm", Locale.getDefault());
    private transient SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",   Locale.getDefault());
    public transient static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");

    public ListNote(int color, String caption, String description, Date creationDate) {
        this.color = color;
        this.caption = caption;
        this.description = description;
        this.creationDate = creationDate;
        this.editingDate = creationDate;
        this.viewingDate = creationDate;
        this.uuid = UUID.randomUUID().toString();
    }

    public ListNote(int color, String caption, String description, Date creationDate, String uuid) {
        this.color = color;
        this.caption = caption;
        this.description = description;
        this.creationDate = creationDate;
        this.editingDate = creationDate;
        this.viewingDate = creationDate;
        this.uuid = uuid;
    }

    public ListNote(
            int color,
            String caption,
            String description,
            String creationDate,
            String editingDate,
            String viewingDate,
            String uuid
    ) throws ParseException {
        this.color = color;
        this.caption = caption;
        this.description = description;
        this.creationDate = format.parse(creationDate);
        this.editingDate = format.parse(editingDate);
        this.viewingDate = format.parse(viewingDate);
        this.uuid = uuid;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Date getCreationDate() {
        return creationDate;
    }
    public String getCreationDateString() {
        return format.format(creationDate);
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return timeFormat.format(creationDate);
    }

    public String getDate() {
        return dateFormat.format(creationDate);
    }

    public Date getEditingDate() { return editingDate; }
    public String getEditingDateString() { return format.format(editingDate); }
    public void setEditingDate(Date editingDate) {
        this.editingDate = editingDate;
        this.viewingDate = editingDate;
    }

    public Date getViewingDate() {return viewingDate;}
    public String getViewingDateString() { return format.format(viewingDate); }
    public void setViewingDate(Date viewingDate) { this.viewingDate = viewingDate; }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof  ListNote))
            return false;

        ListNote listNote = (ListNote) obj;

        if (listNote.uuid.equals(this.uuid)
                && listNote.caption.equals(this.caption)
                && listNote.description.equals(this.description)
                && listNote.color == this.color)

            return true;
        return false;
    }
}

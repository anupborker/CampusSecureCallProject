package com.avb.collageproject;

public class CampusEntry {
    String userID;
    String dateoofentry;
    String timeofentry;
    String timeofexit;
    int Image;

    public CampusEntry() {
    }

    public CampusEntry(String userID, String dateoofentry, String timeofentry, String timeofexit, int image) {
        this.userID = userID;
        this.dateoofentry = dateoofentry;
        this.timeofentry = timeofentry;
        this.timeofexit = timeofexit;
        Image = image;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDateoofentry() {
        return dateoofentry;
    }

    public void setDateoofentry(String dateoofentry) {
        this.dateoofentry = dateoofentry;
    }

    public String getTimeofentry() {
        return timeofentry;
    }

    public void setTimeofentry(String timeofentry) {
        this.timeofentry = timeofentry;
    }

    public String getTimeofexit() {
        return timeofexit;
    }

    public void setTimeofexit(String timeofexit) {
        this.timeofexit = timeofexit;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}

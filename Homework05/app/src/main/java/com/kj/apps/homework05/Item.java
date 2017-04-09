package com.kj.apps.homework05;

/**
 * Created by KJ Mok on 23/03/2017.
 */

public class Item {
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String LINK = "link";
    public static final String PUB_DATE = "pubDate";
    public static final String CREATOR = "dc:creator";
    private String title;
    private String link;
    private String date;
    private String content;
    private String link_img;
    private String creator;

    public Item() {
    }

    public Item(String title, String link, String date, String content, String link_img, String creator) {
        this.title = title;
        this.link = link;
        this.date = date;
        this.creator = creator;
        this.content = content;
        this.link_img = link_img;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink_img() {
        return link_img;
    }

    public void setLink_img(String link_img) {
        this.link_img = link_img;
    }
}

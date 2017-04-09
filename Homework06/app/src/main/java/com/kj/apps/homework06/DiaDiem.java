package com.kj.apps.homework06;

import java.io.Serializable;

/**
 * Created by KJ Mok on 08/04/2017.
 */

public class DiaDiem implements Serializable{
    private String ten;
    private String link;
    private double kinhDo;
    private double viDo;

    public DiaDiem() {
    }

    public DiaDiem(String ten, String link, double kinhDo, double viDo) {
        this.ten = ten;
        this.link = link;
        this.viDo = viDo;
        this.kinhDo = kinhDo;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public double getViDo() {
        return viDo;
    }

    public void setViDo(double viDo) {
        this.viDo = viDo;
    }

    public double getKinhDo() {
        return kinhDo;
    }

    public void setKinhDo(double kinhDo) {
        this.kinhDo = kinhDo;
    }
}

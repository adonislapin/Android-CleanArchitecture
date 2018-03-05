package com.globant.equattrocchio.data.db.pojo;


import io.realm.RealmObject;

public class ImageDAO extends RealmObject {

    public Integer id_img;
    public String url;
    public String largeUrl;
    public String sourceId;
    public String copyright;
    public String site;

    public Integer getId_img() {
        return id_img;
    }

    public void setId_img(Integer id_img) {
        this.id_img = id_img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLargeUrl() {
        return largeUrl;
    }

    public void setLargeUrl(String largeUrl) {
        this.largeUrl = largeUrl;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}

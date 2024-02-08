package com.itnation.computersciencebooks.ModelClass;

public class SubModel {

    String subName,imgLink;

    public SubModel() {
    }

    public SubModel(String subName, String imgLink) {
        this.subName = subName;
        this.imgLink = imgLink;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }
}

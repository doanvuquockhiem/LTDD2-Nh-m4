package com.ae4.nhom_4;



public class ContactModel {
    private int giaDatCuocChinh;
    private int imageCon;
    private String tenCon;

    public ContactModel(int giaDatCuocChinh, int imageCon, String tenCon) {
        this.giaDatCuocChinh = giaDatCuocChinh;
        this.imageCon = imageCon;
        this.tenCon = tenCon;
    }

    public int getGiaDatCuocChinh() {
        return giaDatCuocChinh;
    }

    public void setGiaDatCuocChinh(int giaDatCuocChinh) {
        this.giaDatCuocChinh = giaDatCuocChinh;
    }

    public int getImageCon() {
        return imageCon;
    }

    public void setImageCon(int imageCon) {
        this.imageCon = imageCon;
    }

    public String getTenCon() {
        return tenCon;
    }

    public void setTenCon(String tenCon) {
        this.tenCon = tenCon;
    }
}

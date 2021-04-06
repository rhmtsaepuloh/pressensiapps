
package com.folkatech.pressensiapps.model;

import com.google.gson.annotations.SerializedName;

public class AjukanReimburse {

    @SerializedName("bukti")
    private String mBukti;
    @SerializedName("keterangan")
    private String mKeterangan;
    @SerializedName("nominal")
    private Long mNominal;
    @SerializedName("tanggal")
    private String mTanggal;
    @SerializedName("tipe")
    private int mTipe;

    public String getBukti() {
        return mBukti;
    }

    public void setBukti(String bukti) {
        mBukti = bukti;
    }

    public String getKeterangan() {
        return mKeterangan;
    }

    public void setKeterangan(String keterangan) {
        mKeterangan = keterangan;
    }

    public Long getNominal() {
        return mNominal;
    }

    public void setNominal(Long nominal) {
        mNominal = nominal;
    }

    public String getTanggal() {
        return mTanggal;
    }

    public void setTanggal(String tanggal) {
        mTanggal = tanggal;
    }

    public int getTipe() {
        return mTipe;
    }

    public void setTipe(int tipe) {
        mTipe = tipe;
    }

}

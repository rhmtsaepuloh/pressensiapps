
package com.folkatech.pressensiapps.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DataDetailReimburse {

    @SerializedName("created_history_reimburse")
    private String mCreatedHistoryReimburse;
    @SerializedName("file_reimburse")
    private String mFileReimburse;
    @SerializedName("id_history_reimburse")
    private String mIdHistoryReimburse;
    @SerializedName("id_reimburse")
    private String mIdReimburse;
    @SerializedName("id_tipe_reimburse")
    private String mIdTipeReimburse;
    @SerializedName("keterangan_reimburse")
    private String mKeteranganReimburse;
    @SerializedName("nominal")
    private String mNominal;
    @SerializedName("tanggal_reimburse")
    private String mTanggalReimburse;
    @SerializedName("disetujui")
    private int disetujui;

    public int getDisetujui() {
        return disetujui;
    }

    public void setDisetujui(int disetujui) {
        this.disetujui = disetujui;
    }

    public String getCreatedHistoryReimburse() {
        return mCreatedHistoryReimburse;
    }

    public void setCreatedHistoryReimburse(String createdHistoryReimburse) {
        mCreatedHistoryReimburse = createdHistoryReimburse;
    }

    public String getFileReimburse() {
        return mFileReimburse;
    }

    public void setFileReimburse(String fileReimburse) {
        mFileReimburse = fileReimburse;
    }

    public String getIdHistoryReimburse() {
        return mIdHistoryReimburse;
    }

    public void setIdHistoryReimburse(String idHistoryReimburse) {
        mIdHistoryReimburse = idHistoryReimburse;
    }

    public String getIdReimburse() {
        return mIdReimburse;
    }

    public void setIdReimburse(String idReimburse) {
        mIdReimburse = idReimburse;
    }

    public String getIdTipeReimburse() {
        return mIdTipeReimburse;
    }

    public void setIdTipeReimburse(String idTipeReimburse) {
        mIdTipeReimburse = idTipeReimburse;
    }

    public String getKeteranganReimburse() {
        return mKeteranganReimburse;
    }

    public void setKeteranganReimburse(String keteranganReimburse) {
        mKeteranganReimburse = keteranganReimburse;
    }

    public String getNominal() {
        return mNominal;
    }

    public void setNominal(String nominal) {
        mNominal = nominal;
    }

    public String getTanggalReimburse() {
        return mTanggalReimburse;
    }

    public void setTanggalReimburse(String tanggalReimburse) {
        mTanggalReimburse = tanggalReimburse;
    }

}

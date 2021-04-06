
package com.folkatech.pressensiapps.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DataTipeReimburse {

    @SerializedName("created_tipe_reimburse")
    private String mCreatedTipeReimburse;
    @SerializedName("id_channel")
    private String mIdChannel;
    @SerializedName("id_tipe_reimburse")
    private String mIdTipeReimburse;
    @SerializedName("maximum_amount")
    private String mMaximumAmount;
    @SerializedName("nama_tipe_reimburse")
    private String mNamaTipeReimburse;
    @SerializedName("required_receipt")
    private String mRequiredReceipt;

    public String getCreatedTipeReimburse() {
        return mCreatedTipeReimburse;
    }

    public void setCreatedTipeReimburse(String createdTipeReimburse) {
        mCreatedTipeReimburse = createdTipeReimburse;
    }

    public String getIdChannel() {
        return mIdChannel;
    }

    public void setIdChannel(String idChannel) {
        mIdChannel = idChannel;
    }

    public String getIdTipeReimburse() {
        return mIdTipeReimburse;
    }

    public void setIdTipeReimburse(String idTipeReimburse) {
        mIdTipeReimburse = idTipeReimburse;
    }

    public String getMaximumAmount() {
        return mMaximumAmount;
    }

    public void setMaximumAmount(String maximumAmount) {
        mMaximumAmount = maximumAmount;
    }

    public String getNamaTipeReimburse() {
        return mNamaTipeReimburse;
    }

    public void setNamaTipeReimburse(String namaTipeReimburse) {
        mNamaTipeReimburse = namaTipeReimburse;
    }

    public String getRequiredReceipt() {
        return mRequiredReceipt;
    }

    public void setRequiredReceipt(String requiredReceipt) {
        mRequiredReceipt = requiredReceipt;
    }

}

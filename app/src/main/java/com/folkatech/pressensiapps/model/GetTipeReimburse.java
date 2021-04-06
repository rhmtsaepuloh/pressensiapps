
package com.folkatech.pressensiapps.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class GetTipeReimburse {

    @SerializedName("data")
    private List<DataTipeReimburse> mData;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("status")
    private Boolean mStatus;

    public List<DataTipeReimburse> getData() {
        return mData;
    }

    public void setData(List<DataTipeReimburse> data) {
        mData = data;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public Boolean getStatus() {
        return mStatus;
    }

    public void setStatus(Boolean status) {
        mStatus = status;
    }

}

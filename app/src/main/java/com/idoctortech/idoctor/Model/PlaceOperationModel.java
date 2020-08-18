package com.idoctortech.idoctor.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaceOperationModel {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("icon")
    @Expose
    public String icon;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("status")
    @Expose
    public int status;
    @SerializedName("deleted_at")
    @Expose
    public Object deletedAt;
    @SerializedName("created_at")
    @Expose
    public Object createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;

    public PlaceOperationModel(String icon, String name) {
        this.icon = icon;
        this.name = name;
    }
}

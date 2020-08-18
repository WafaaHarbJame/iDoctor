package com.idoctortech.idoctor.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ameer on 1/28/2018.
 */

public class VersionModel {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("v_app")
    @Expose
    public int vApp;
    @SerializedName("priority")
    @Expose
    public int priority;

}

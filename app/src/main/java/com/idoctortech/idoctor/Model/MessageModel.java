package com.idoctortech.idoctor.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageModel {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("sender_id")
    @Expose
    public int senderId;
    @SerializedName("receiver_id")
    @Expose
    public int receiverId;
    @SerializedName("body")
    @Expose
    public String body;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    public Object deletedAt;
    @SerializedName("key")
    @Expose
    public String key;
    @SerializedName("sender_name")
    @Expose
    public String senderName;
    @SerializedName("receiver_name")
    @Expose
    public String receiverName;
    @SerializedName("sender_logo")
    @Expose
    public String senderLogo;
    @SerializedName("receiver_logo")
    @Expose
    public String receiverLogo;

//    public MessageModel(String body, int id) {
//        this.message = body;
//        this.userId = id;
//    }
}

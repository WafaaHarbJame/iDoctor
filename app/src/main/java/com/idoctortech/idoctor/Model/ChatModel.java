package com.idoctortech.idoctor.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChatModel {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("sender_id")
    @Expose
    public int senderId;
    @SerializedName("receiver_id")
    @Expose
    public int receiverId;
    @SerializedName("last_msg")
    @Expose
    public String lastMsg;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("key")
    @Expose
    public String key;
    @SerializedName("user_key")
    @Expose
    public int userKey;
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

    public int getFriendId(int userId) {

        if (userId == senderId)
            return receiverId;
        else
            return senderId;

    }

    public String getFriendName(int userId) {

        if (userId == senderId)
            return receiverName;
        else
            return senderName;

    }

    public String getFriendLogo(int userId) {

        if (userId == senderId)
            return receiverLogo;
        else
            return senderLogo;

    }

}

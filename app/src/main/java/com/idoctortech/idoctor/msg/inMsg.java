package com.idoctortech.idoctor.msg;

public class inMsg {

    int chat_id;
    int chat_from_id;
    int chat_to_id;
    String chat_content;
    int chat_sender_type;
    String chat_created_at;
    int chat_read;
    int msg_type;

    public inMsg() {
    }

    public inMsg(int chat_id, int chat_from_id, int chat_to_id, String chat_content, int chat_sender_type, String chat_created_at, int chat_read, int msg_type) {
        this.chat_id = chat_id;
        this.chat_from_id = chat_from_id;
        this.chat_to_id = chat_to_id;
        this.chat_content = chat_content;
        this.chat_sender_type = chat_sender_type;
        this.chat_created_at = chat_created_at;
        this.chat_read = chat_read;
        this.msg_type = msg_type;
    }

    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    public int getChat_from_id() {
        return chat_from_id;
    }

    public void setChat_from_id(int chat_from_id) {
        this.chat_from_id = chat_from_id;
    }

    public int getChat_to_id() {
        return chat_to_id;
    }

    public void setChat_to_id(int chat_to_id) {
        this.chat_to_id = chat_to_id;
    }

    public String getChat_content() {
        return chat_content;
    }

    public void setChat_content(String chat_content) {
        this.chat_content = chat_content;
    }

    public int getChat_sender_type() {
        return chat_sender_type;
    }

    public void setChat_sender_type(int chat_sender_type) {
        this.chat_sender_type = chat_sender_type;
    }

    public String getChat_created_at() {
        return chat_created_at;
    }

    public void setChat_created_at(String chat_created_at) {
        this.chat_created_at = chat_created_at;
    }

    public int getChat_read() {
        return chat_read;
    }

    public void setChat_read(int chat_read) {
        this.chat_read = chat_read;
    }

    public int getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(int msg_type) {
        this.msg_type = msg_type;
    }
}

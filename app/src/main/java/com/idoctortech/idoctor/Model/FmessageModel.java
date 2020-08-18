package com.idoctortech.idoctor.Model;

import java.util.Date;

/**
 * Created by wokman on 2/24/2017.
 */

public class FmessageModel /*implements Comparator<FmessageModel>*/ {

    public int iduser;
    public String msg;
    public String type;
    public int delete_receiver;
    public int delete_sender;
    //    public String fcm;
//    @ServerTimestamp
    public Date date;


//    @Override
//    public int compare(FmessageModel o1, FmessageModel o2) {
//        return o1.id < o2.id ? -1 : o1.id == o2.id ? 0 : 1;
//    }
}

package com.idoctortech.idoctor.Model;

import java.io.Serializable;

/**
 * Created by ameer on 1/9/2018.
 */

public class RegisterUserModel implements Serializable {

    public String full_name;
//    public String first_name;
//    public String last_name;
    public String email;
    public String mobile;
    public String password;
    public String prefix;
    public int city_id;
    public String fcm_token;

}

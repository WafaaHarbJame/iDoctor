package com.idoctortech.idoctor.Classes;

import com.idoctortech.idoctor.Model.CityModel;
import com.idoctortech.idoctor.Model.PageModel;
import com.idoctortech.idoctor.Model.PlaceOperationModel;
import com.idoctortech.idoctor.Model.PlaceTypeModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class DBFunction {
//    static Realm realm = Realm.getDefaultInstance();


    public static List<CityModel> getCities() {

        String json = getFromDB(Constants.DB_Cities);
        if (json != null) {
            return new Gson().fromJson(json, new TypeToken<List<CityModel>>() {
            }.getType());
        } else {
            return null;
        }
    }

    public static List<PlaceTypeModel> getPlaceType() {

        String json = getFromDB(Constants.DB_PlaceType);
        if (json != null) {
            return new Gson().fromJson(json, new TypeToken<List<PlaceTypeModel>>() {
            }.getType());
        } else {
            return null;
        }
    }

    public static List<PlaceOperationModel> getPlaceOperation() {

        String json = getFromDB(Constants.DB_PlaceOperation);
        if (json != null) {
            return new Gson().fromJson(json, new TypeToken<List<PlaceOperationModel>>() {
            }.getType());
        } else {
            return null;
        }
    }

    public static PageModel getTerms() {

        String json = getFromDB(Constants.DB_TermsModel);
        if (json != null) {
            return new Gson().fromJson(json, new TypeToken<PageModel>() {
            }.getType());
        } else {
            return null;
        }
    }


    /*************************************************************/

//    public static boolean clearDB() {
//        final RealmResults<DBModel> dbModel = realm.where(DBModel.class)
//                .notEqualTo("type", Constants.DB_Countries)
//                .findAll();
//
//
//        if (dbModel != null) {
//            realm.executeTransaction(new Realm.Transaction() {
//                @Override
//                public void execute(Realm realm) {
//                    dbModel.deleteAllFromRealm();
//                }
//            });
//            return true;
//        } else {
//            return false;
//        }
//    }

    /**********************************************************************/
    private static String getFromDB(String type) {
        return UtilityApp.getFromShPref(type);
//        DBModel dbModel = realm.where(DBModel.class).equalTo("type", type).findFirst();
//        if (dbModel != null) {
//            return dbModel.dataModel;
//        } else {
//            return null;
//        }
    }


}

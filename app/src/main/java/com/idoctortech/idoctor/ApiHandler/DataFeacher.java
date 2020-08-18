package com.idoctortech.idoctor.ApiHandler;


import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.idoctortech.idoctor.Activity.LoginActivity;
import com.idoctortech.idoctor.Classes.Constants;
import com.idoctortech.idoctor.Classes.UtilityApp;
import com.idoctortech.idoctor.Dialogs.NoConnectionDialog;
import com.idoctortech.idoctor.Model.AllMessagesModel;
import com.idoctortech.idoctor.Model.AllPlacesModel;
import com.idoctortech.idoctor.Model.CreatePlaceModel;
import com.idoctortech.idoctor.Model.ErrorModel;
import com.idoctortech.idoctor.Model.MemberModel;
import com.idoctortech.idoctor.Model.RegisterUserModel;
import com.idoctortech.idoctor.Model.ResultAPIModel;
import com.idoctortech.idoctor.Model.SearchModel;
import com.idoctortech.idoctor.Utils.SharedPManger;

import java.net.NoRouteToHostException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.dreams.shamel.Dialogs.CustomeDialog;

//        import com.dreams.quickstore.NavModel.ResultAPIModel;

public class DataFeacher {
    Activity activity;
    DataFetcherCallBack dataFetcherCallBack;
    ApiInterface apiService;

    //    Realm realm;
    SharedPManger sharedPManger;
//    int city;

    final String TAG = "Log";

    String accessToken;
    String lang;
    Map<String, Object> headerMap = new HashMap<>();

    public DataFeacher(Activity activity, DataFetcherCallBack dataFetcherCallBack, boolean longTime) {
        this.activity = activity;
        this.dataFetcherCallBack = dataFetcherCallBack;
        if (longTime) {
            apiService = ApiClient.getLongClient().create(ApiInterface.class);
        } else {
            apiService = ApiClient.getClient().create(ApiInterface.class);
        }
//        realm = Realm.getDefaultInstance();
        sharedPManger = new SharedPManger(activity);

        headerMap.put("Accept", "application/json,*/*");
        headerMap.put("X-PLATFORM", "android");

        accessToken = UtilityApp.getUserToken();
        if (accessToken != null) {
            headerMap.put("Authorization", accessToken);
            headerMap.put("Auth-Role", "user");
        } else {
            headerMap.put("Auth-Role", "guest");
        }
        lang = UtilityApp.getLanguage();
        if (lang != null) {
            headerMap.put("Accept-Language", lang);
        }

    }

    public DataFeacher(Activity activity, DataFetcherCallBack dataFetcherCallBack) {
        this.activity = activity;
        this.dataFetcherCallBack = dataFetcherCallBack;
        apiService = ApiClient.getClient().create(ApiInterface.class);
//        apiServiceLong = ApiClient.getLongClient().create(ApiInterface.class);
//        realm = Realm.getDefaultInstance();
        sharedPManger = new SharedPManger(activity);

        headerMap.put("Accept", "application/json,*/*");
        headerMap.put("X-PLATFORM", "android");

        accessToken = UtilityApp.getUserToken();
        if (accessToken != null) {
            headerMap.put("Authorization", accessToken);
            headerMap.put("Auth-Role", "user");
        } else {
            headerMap.put("Auth-Role", "guest");
        }
        lang = UtilityApp.getLanguage();
        if (lang != null) {
            headerMap.put("Accept-Language", lang);
        }

    }

    public DataFeacher(Activity activity) {
        this.activity = activity;
        apiService = ApiClient.getClient().create(ApiInterface.class);
        this.dataFetcherCallBack = (obj, func, IsSuccess) -> {

        };
//        realm = Realm.getDefaultInstance();
        sharedPManger = new SharedPManger(activity);

        headerMap.put("Accept", "application/json,*/*");
        headerMap.put("X-PLATFORM", "android");

        accessToken = UtilityApp.getUserToken();
        if (accessToken != null) {
            headerMap.put("Authorization", accessToken);
            headerMap.put("Auth-Role", "user");
        } else {
            headerMap.put("Auth-Role", "guest");
        }
        lang = UtilityApp.getLanguage();
        if (lang != null) {
            headerMap.put("Accept-Language", lang);
        }
    }

    /*********************************** POST Feacheer *********************************/

    public void loginHandle(MemberModel memberModel, final boolean showLoading) {

        Map<String, Object> params = new HashMap<>();
        params.put("username", memberModel.mobile);
        params.put("password", memberModel.password);
        params.put("device_type", "android");
        params.put("device_token", memberModel.fcmToken);

        Log.i(TAG, "Log loginHandle");
        Log.i(TAG, "Log headerMap " + headerMap);
        Log.i(TAG, "Log username " + memberModel.mobile);
        Log.i(TAG, "Log password " + memberModel.password);
        Log.i(TAG, "Log device_type " + "android");
        Log.i(TAG, "Log device_token " + memberModel.fcmToken);

        Call<ResultAPIModel<MemberModel>> call = apiService.loginHandle(headerMap, params);
        call.enqueue(new Callback<ResultAPIModel<MemberModel>>() {
            @Override
            public void onResponse(Call<ResultAPIModel<MemberModel>> call, Response<ResultAPIModel<MemberModel>> response) {
                ResultAPIModel<MemberModel> result = response.body();
                if (response.isSuccessful()) {

                    dataFetcherCallBack.Result(result, "loginHandle", true);

                } else {
                    ResultAPIModel errorModel = null;
                    try {
                        String error = response.errorBody().string();
                        Log.e("Log", "Log error " + error);
                        if (error != null) {
                            errorModel = new Gson().fromJson(error, new TypeToken<ResultAPIModel>() {
                            }.getType());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    dataFetcherCallBack.Result(errorModel, Constants.ERROR, false);

                }

            }

            @Override
            public void onFailure(Call<ResultAPIModel<MemberModel>> call, Throwable t) {
                t.printStackTrace();
                if (t instanceof UnknownHostException || t instanceof NoRouteToHostException) {
                    NoConnectionDialog.with(activity);
                    dataFetcherCallBack.Result(null, Constants.NO_CONNECTION, false);
                } else {
                    dataFetcherCallBack.Result(null, Constants.FAIL, false);
                }

            }
        });
    }

    public void registerHandle(RegisterUserModel registerUserModel, final boolean showLoading) {

        Map<String, Object> params = new HashMap<>();
        params.put("full_name", registerUserModel.full_name);
        params.put("email", registerUserModel.email);
        params.put("mobile", registerUserModel.mobile);
        params.put("password", registerUserModel.password);
        params.put("password_confirmation", registerUserModel.password);
        params.put("city_id", registerUserModel.city_id);
        params.put("contact_mobile", registerUserModel.prefix);
        params.put("device_type", "android");
        if (registerUserModel.fcm_token != null && !registerUserModel.fcm_token.isEmpty())
            params.put("device_token", registerUserModel.fcm_token);

        Log.i(TAG, "Log registerHandle");
        Log.i(TAG, "Log headerMap " + headerMap);
        Log.i(TAG, "full_name " + registerUserModel.full_name);
        Log.i(TAG, "email " + registerUserModel.email);
        Log.i(TAG, "mobile " + registerUserModel.mobile);
        Log.i(TAG, "password " + registerUserModel.password);
        Log.i(TAG, "password_confirmation " + registerUserModel.password);
        Log.i(TAG, "city_id " + registerUserModel.city_id);
        Log.i(TAG, "contact_mobile " + registerUserModel.prefix);
        Log.i(TAG, "Log device_type " + "android");
        if (registerUserModel.fcm_token != null && !registerUserModel.fcm_token.isEmpty())
            Log.i(TAG, "Log device_token " + registerUserModel.fcm_token);

        Call<ResultAPIModel<MemberModel>> call = apiService.registerHandle(headerMap, params);
        call.enqueue(new Callback<ResultAPIModel<MemberModel>>() {
            @Override
            public void onResponse(Call<ResultAPIModel<MemberModel>> call, Response<ResultAPIModel<MemberModel>> response) {
                if (response.isSuccessful()) {
                    ResultAPIModel<MemberModel> result = response.body();

                    dataFetcherCallBack.Result(result, "registerHandle", true);

                } else {
                    ResultAPIModel errorModel = null;
                    try {
                        String error = response.errorBody().string();
                        Log.e("Log", "Log error " + error);
                        if (error != null) {
                            errorModel = new Gson().fromJson(error, new TypeToken<ResultAPIModel>() {
                            }.getType());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    dataFetcherCallBack.Result(errorModel, Constants.ERROR, false);

                }

            }

            @Override
            public void onFailure(Call<ResultAPIModel<MemberModel>> call, Throwable t) {
                t.printStackTrace();
                if (t instanceof UnknownHostException || t instanceof NoRouteToHostException) {
                    NoConnectionDialog.with(activity);
                    dataFetcherCallBack.Result(null, Constants.NO_CONNECTION, false);
                } else {
                    dataFetcherCallBack.Result(null, Constants.FAIL, false);
                }

            }
        });
    }

    public void logOut(final boolean showProgress) {

        Log.i(TAG, "Log logOut");
        Log.i(TAG, "Log AccessToken " + headerMap);

        Call<ResultAPIModel> call = apiService.logout(headerMap);
        call.enqueue(new Callback<ResultAPIModel>() {
            @Override
            public void onResponse(Call<ResultAPIModel> call, Response<ResultAPIModel> response) {
                ResultAPIModel<MemberModel> result = response.body();
                if (response.isSuccessful()) {
                    dataFetcherCallBack.Result(result, "changePassword", true);
                } else {
                    ResultAPIModel<ErrorModel> errorModel = null;
                    try {
                        String error = response.errorBody().string();
                        Log.e("Log", "Log error " + error);
                        if (error != null) {
                            errorModel = new Gson().fromJson(error, new TypeToken<ResultAPIModel<ErrorModel>>() {
                            }.getType());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (response.code() == 401) {
                        UtilityApp.logOut();
                        openLoginActivity();
                    } else {
                        dataFetcherCallBack.Result(errorModel, Constants.ERROR, false);
                    }
                }

            }

            @Override
            public void onFailure(Call<ResultAPIModel> call, Throwable t) {
                t.printStackTrace();
                if (t instanceof UnknownHostException || t instanceof NoRouteToHostException) {
                    NoConnectionDialog.with(activity);
                    dataFetcherCallBack.Result(null, Constants.NO_CONNECTION, false);
                } else {
                    dataFetcherCallBack.Result(null, Constants.FAIL, false);
                }
            }
        });
    }

    public void createPlace(CreatePlaceModel placeModel, final boolean showProgress) {

        Map<String, Object> params = new HashMap<>();
        params.put("real_estate_type_id", placeModel.getPlaceTypeId());
        params.put("real_estate_operation_id", placeModel.getPlaceOperationId());
        params.put("real_estate_title", placeModel.getTitle());
        params.put("area", placeModel.getArea());
        params.put("city_id", placeModel.getCityId());
        params.put("description", placeModel.getDesc());
        params.put("negotiable_status", placeModel.isCanNegotiate());
        params.put("installment_status", placeModel.isCanInstallment());
        params.put("bank_status", placeModel.isByBank());
        params.put("images", placeModel.getPhotosList());

        Log.i(TAG, "Log createPlace");
        Log.i(TAG, "Log AccessToken " + headerMap);
        Log.i(TAG, "Log real_estate_type_id " + placeModel.getPlaceTypeId());
        Log.i(TAG, "Log real_estate_operation_id " + placeModel.getPlaceOperationId());
        Log.i(TAG, "Log real_estate_title " + placeModel.getTitle());
        Log.i(TAG, "Log area " + placeModel.getArea());
        Log.i(TAG, "Log city_id " + placeModel.getCityId());
        Log.i(TAG, "Log description " + placeModel.getDesc());
        Log.i(TAG, "Log negotiable_status " + placeModel.isCanNegotiate());
        Log.i(TAG, "Log installment_status " + placeModel.isCanInstallment());
        Log.i(TAG, "Log bank_status " + placeModel.isByBank());
        Log.i(TAG, "Log images " + placeModel.getPhotosList());

        Call<ResultAPIModel<Integer>> call = apiService.createRealEstate(headerMap, params);
        call.enqueue(new Callback<ResultAPIModel<Integer>>() {
            @Override
            public void onResponse(Call<ResultAPIModel<Integer>> call, Response<ResultAPIModel<Integer>> response) {
                ResultAPIModel<Integer> result = response.body();
                if (response.isSuccessful()) {
                    dataFetcherCallBack.Result(result, "changePassword", true);
                } else {
                    ResultAPIModel<ErrorModel> errorModel = null;
                    try {
                        String error = response.errorBody().string();
                        Log.e("Log", "Log error " + error);
                        if (error != null) {
                            errorModel = new Gson().fromJson(error, new TypeToken<ResultAPIModel<ErrorModel>>() {
                            }.getType());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    dataFetcherCallBack.Result(errorModel, Constants.ERROR, false);

                }

            }

            @Override
            public void onFailure(Call<ResultAPIModel<Integer>> call, Throwable t) {
                t.printStackTrace();
                if (t instanceof UnknownHostException || t instanceof NoRouteToHostException) {
                    NoConnectionDialog.with(activity);
                    dataFetcherCallBack.Result(null, Constants.NO_CONNECTION, false);
                } else {
                    dataFetcherCallBack.Result(null, Constants.FAIL, false);
                }
            }
        });
    }

    /************************************  GET Feacher *************************************/

//    public void getCities(final boolean showProgress) {
//
//        Log.i(TAG, "Log getCities");
//        Log.i(TAG, "Log AccessToken " + headerMap);
//
//        Call<ResultAPIModel<List<CityModel>>> call = apiService.getCities(headerMap);
//        if (showProgress)
//            GlobalData.progressDialog(activity, activity.getResources().getString(R.string.please_wait_sending), true);
//        call.enqueue(new Callback<ResultAPIModel<List<CityModel>>>() {
//            @Override
//            public void onResponse(Call<ResultAPIModel<List<CityModel>>> call, Response<ResultAPIModel<List<CityModel>>> response) {
//                if (showProgress)
//                    GlobalData.progressDialog(activity, activity.getResources().getString(R.string.please_wait_sending), false);
//                ResultAPIModel<List<CityModel>> result = response.body();
//                if (response.isSuccessful()) {
//                    String json = new Gson().toJson(result.data);
//                    setDB(Constants.DB_Cities, json);
////                    UtilityApp.setToShPref(Constants.DB_Cities, json);
//
//                    dataFetcherCallBack.Result(result, "changePassword", true);
//                } else {
//                    ResultAPIModel<ErrorModel> errorModel = null;
//                    try {
//                        String error = response.errorBody().string();
//                        Log.e("Log", "Log error " + error);
//                        if (error != null) {
//                            errorModel = new Gson().fromJson(error, new TypeToken<ResultAPIModel<ErrorModel>>() {
//                            }.getType());
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    if (response.code() == 401) {
//                        UtilityApp.logOut();
//                        openLoginActivity();
//                    } else {
//                        dataFetcherCallBack.Result(errorModel, Constants.ERROR, false);
//                    }
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ResultAPIModel<List<CityModel>>> call, Throwable t) {
//                t.printStackTrace();
//                if (showProgress)
//                    GlobalData.progressDialog(activity, activity.getResources().getString(R.string.please_wait_sending), false);
//                if (t instanceof UnknownHostException || t instanceof NoRouteToHostException) {
//                    NoConnectionDialog.with(activity);
//                    dataFetcherCallBack.Result(null, Constants.FAIL, false);
//                } else {
//                    dataFetcherCallBack.Result(null, Constants.FAIL, false);
//                }
//            }
//        });
//    }
    public void getMainSection(int page, final boolean showProgress) {

        Log.i(TAG, "Log getMainSection");
        Log.i(TAG, "Log AccessToken " + accessToken);
        Log.i(TAG, "Log page " + page);

        Call<ResultAPIModel<AllPlacesModel>> call = apiService.getMainSection(headerMap);
        call.enqueue(new Callback<ResultAPIModel<AllPlacesModel>>() {
            @Override
            public void onResponse(Call<ResultAPIModel<AllPlacesModel>> call, Response<ResultAPIModel<AllPlacesModel>> response) {
                ResultAPIModel<AllPlacesModel> result = response.body();
                if (response.isSuccessful()) {

                    dataFetcherCallBack.Result(result, "getStarPeople", true);

                } else {
                    ResultAPIModel<ErrorModel> errorModel = null;
                    try {
                        String error = response.errorBody().string();
                        Log.e("Log", "Log error " + error);
                        errorModel = new Gson().fromJson(error, new TypeToken<ResultAPIModel<ErrorModel>>() {
                        }.getType());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (response.code() == 401) {
                        UtilityApp.logOut();
                        openLoginActivity();
                    } else {
                        dataFetcherCallBack.Result(errorModel, Constants.ERROR, false);
                    }
                }

            }

            @Override
            public void onFailure(Call<ResultAPIModel<AllPlacesModel>> call, Throwable t) {
                t.printStackTrace();
                if (t instanceof UnknownHostException || t instanceof NoRouteToHostException) {
                    dataFetcherCallBack.Result(null, Constants.NO_CONNECTION, false);
                } else {
                    dataFetcherCallBack.Result(null, Constants.FAIL, false);
                }
            }
        });
    }

    public void getPlaces(SearchModel searchModel, int page, final boolean showProgress) {

        Map<String, Object> params = new HashMap<>();
        if (searchModel != null) {
            if (searchModel.searchStr != null && !searchModel.searchStr.isEmpty())
                params.put("real_estate_title", searchModel.searchStr);
            if (searchModel.place_type_id != 0)
                params.put("real_estate_type", searchModel.place_type_id);
            if (searchModel.place_opertaion_id != 0)
                params.put("real_estate_operation", searchModel.place_opertaion_id);
            if (searchModel.cityVal != null)
                params.put("city", searchModel.cityVal.id);
            if (searchModel.date != null && !searchModel.date.isEmpty())
                params.put("date", searchModel.date);
            if (searchModel.sort_type != null && !searchModel.sort_type.isEmpty())
                params.put("sort_type", searchModel.sort_type);
        }
        params.put("page", page);

        Log.i(TAG, "Log getPlaces");
        Log.i(TAG, "Log headerMap " + headerMap);
        if (searchModel != null) {
            if (searchModel.searchStr != null && !searchModel.searchStr.isEmpty())
                Log.i(TAG, "Log real_estate_title " + searchModel.searchStr);
            if (searchModel.place_type_id != 0)
                Log.i(TAG, "Log real_estate_type " + searchModel.place_type_id);
            if (searchModel.place_opertaion_id != 0)
                Log.i(TAG, "Log real_estate_operation " + searchModel.place_opertaion_id);
            if (searchModel.cityVal != null)
                Log.i(TAG, "Log city_id " + searchModel.cityVal.id);
            if (searchModel.date != null && !searchModel.date.isEmpty())
                Log.i(TAG, "Log date " + searchModel.date);
            if (searchModel.sort_type != null && !searchModel.sort_type.isEmpty())
                Log.i(TAG, "Log sort_type " + searchModel.sort_type);
        }
        Log.i(TAG, "Log page " + page);

        Call<ResultAPIModel<AllPlacesModel>> call = apiService.getRealEstates(headerMap, params);
        call.enqueue(new Callback<ResultAPIModel<AllPlacesModel>>() {
            @Override
            public void onResponse(Call<ResultAPIModel<AllPlacesModel>> call, Response<ResultAPIModel<AllPlacesModel>> response) {
                ResultAPIModel<AllPlacesModel> result = response.body();
                if (response.isSuccessful()) {

                    dataFetcherCallBack.Result(result, "getStarPeople", true);

                } else {
                    ResultAPIModel<ErrorModel> errorModel = null;
                    try {
                        String error = response.errorBody().string();
                        Log.e("Log", "Log error " + error);
                        if (error != null) {
                            errorModel = new Gson().fromJson(error, new TypeToken<ResultAPIModel<ErrorModel>>() {
                            }.getType());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    dataFetcherCallBack.Result(errorModel, Constants.ERROR, false);

                }

            }

            @Override
            public void onFailure(Call<ResultAPIModel<AllPlacesModel>> call, Throwable t) {
                t.printStackTrace();
                if (t instanceof UnknownHostException || t instanceof NoRouteToHostException) {
                    dataFetcherCallBack.Result(null, Constants.NO_CONNECTION, false);
                } else {
                    dataFetcherCallBack.Result(null, Constants.FAIL, false);
                }
            }
        });
    }

    public void getChatMessages(int chatId, int page, final boolean showProgress) {

        Log.i(TAG, "Log getChatMessages");
        Log.i(TAG, "Log AccessToken " + accessToken);
        Log.i(TAG, "Log id " + chatId);
        Log.i(TAG, "Log page " + page);

        Call<ResultAPIModel<AllMessagesModel>> call = apiService.getChatMessages(headerMap, chatId, page);
        call.enqueue(new Callback<ResultAPIModel<AllMessagesModel>>() {
            @Override
            public void onResponse(Call<ResultAPIModel<AllMessagesModel>> call, Response<ResultAPIModel<AllMessagesModel>> response) {
                ResultAPIModel<AllMessagesModel> result = response.body();
                if (response.isSuccessful()) {

                    dataFetcherCallBack.Result(result, "getStarPeople", true);

                } else {
                    ResultAPIModel<ErrorModel> errorModel = null;
                    try {
                        String error = response.errorBody().string();
                        Log.e("Log", "Log error " + error);
                        if (error != null) {
                            errorModel = new Gson().fromJson(error, new TypeToken<ResultAPIModel<ErrorModel>>() {
                            }.getType());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    dataFetcherCallBack.Result(errorModel, Constants.ERROR, false);

                }

            }

            @Override
            public void onFailure(Call<ResultAPIModel<AllMessagesModel>> call, Throwable t) {
                t.printStackTrace();
                if (t instanceof UnknownHostException || t instanceof NoRouteToHostException) {
                    dataFetcherCallBack.Result(null, Constants.NO_CONNECTION, false);
                } else {
                    dataFetcherCallBack.Result(null, Constants.FAIL, false);
                }
            }
        });
    }

//    public void getMyProfile(final boolean showProgress) {
//
//        Log.i(TAG, "Log getMyProfile");
//        Log.i(TAG, "Log AccessToken " + headerMap);
//
//        Call<ResultAPIModel<MemberModel>> call = apiService.getMyProfile(headerMap);
//        if (showProgress)
//            GlobalData.progressDialog(activity, activity.getResources().getString(R.string.please_wait_loading), true);
//        call.enqueue(new Callback<ResultAPIModel<MemberModel>>() {
//            @Override
//            public void onResponse(Call<ResultAPIModel<MemberModel>> call, Response<ResultAPIModel<MemberModel>> response) {
//                if (showProgress)
//                    GlobalData.progressDialog(activity, activity.getResources().getString(R.string.please_wait_loading), false);
//                ResultAPIModel<MemberModel> result = response.body();
//                if (response.isSuccessful()) {
//
//                    dataFetcherCallBack.Result(result, "getMyProfile", true);
//
//                } else {
//                    ResultAPIModel errorModel = null;
//                    try {
//                        String error = response.errorBody().string();
//                        Log.e("Log", "Log error " + error);
//                        if (error != null) {
//                            errorModel = new Gson().fromJson(error, new TypeToken<ResultAPIModel>() {
//                            }.getType());
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    if (errorModel.error != null && errorModel.error.description != null && errorModel.error.description.equals(Constants.AUTH_EXCEPTION) || response.code() == 401) {
//                        dataFetcherCallBack.Result(errorModel, Constants.UNAUTHENTICATED, false);
//                    } else {
//                        dataFetcherCallBack.Result(errorModel, Constants.ERROR, false);
//                    }
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ResultAPIModel<MemberModel>> call, Throwable t) {
//                if (showProgress)
//                    GlobalData.progressDialog(activity, activity.getResources().getString(R.string.please_wait_loading), false);
//                t.printStackTrace();
//                if (t instanceof UnknownHostException || t instanceof NoRouteToHostException) {
//                    dataFetcherCallBack.Result(null, Constants.FAIL, false);
//                } else {
//                    dataFetcherCallBack.Result(null, Constants.FAIL, false);
//                }
//            }
//        });
//    }

    /********************************************************************************/

    private void openLoginActivity() {

        Intent intent = new Intent(activity, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    private void setDB(String type, String data) {

        UtilityApp.setToShPref(type, data);
//        DBModel dbModel = new DBModel();
//        dbModel.type = type;
//        dbModel.dataModel = data;
//        realm.beginTransaction();
//        realm.copyToRealmOrUpdate(dbModel);
//        realm.commitTransaction();
    }

    private void printLog(Object o) {
        Log.v("Log", "Log " + o.toString());
    }
}

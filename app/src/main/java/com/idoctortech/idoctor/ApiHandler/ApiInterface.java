package com.idoctortech.idoctor.ApiHandler;

import com.idoctortech.idoctor.Model.AllChatsModel;
import com.idoctortech.idoctor.Model.AllMessagesModel;
import com.idoctortech.idoctor.Model.AllPlacesModel;
import com.idoctortech.idoctor.Model.CityModel;
import com.idoctortech.idoctor.Model.MemberModel;
import com.idoctortech.idoctor.Model.PageModel;
import com.idoctortech.idoctor.Model.PhotoModel;
import com.idoctortech.idoctor.Model.PlaceModel;
import com.idoctortech.idoctor.Model.PlaceOperationModel;
import com.idoctortech.idoctor.Model.PlaceTypeModel;
import com.idoctortech.idoctor.Model.ResultAPIModel;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by wokman on 11/4/2016.
 */

public interface ApiInterface {

    /* ------------------------- POST Handle ------------------------- */

    @POST("v2/signup")
    Call<ResultAPIModel<MemberModel>> registerHandle(@HeaderMap() Map<String, Object> headerParams, @Body Map<String, Object> params);

    @POST("v2/login")
    Call<ResultAPIModel<MemberModel>> loginHandle(@HeaderMap() Map<String, Object> headerParams, @Body Map<String, Object> params);

    @POST("v2/mobile/sendConfimration")
    Call<ResultAPIModel<MemberModel>> sendConfirmCode(@HeaderMap() Map<String, Object> headerParams, @Body Map<String, Object> params);

    @POST("v2/mobile/confirm")
    Call<ResultAPIModel<MemberModel>> confirmRegister(@HeaderMap() Map<String, Object> headerParams, @Body Map<String, Object> params);

    @POST("v2/mobile/confirm")
    Call<ResultAPIModel<MemberModel>> confirmRegisterFirebase(@HeaderMap() Map<String, Object> headerParams, @Body Map<String, Object> params);

    @POST("v2/password/resetAsk")
    Call<ResultAPIModel> sendResetPassword(@HeaderMap() Map<String, Object> headerParams, @Body Map<String, Object> params);

    @POST("v2/password/resetToken")
    Call<ResultAPIModel> confirmResetPassword(@HeaderMap() Map<String, Object> headerParams, @Body Map<String, Object> params);

    @POST("v2/logout")
    Call<ResultAPIModel> logout(@HeaderMap() Map<String, Object> headerParams);

    @POST("v2/me/update")
    Call<ResultAPIModel<MemberModel>> updateProfilePost(@HeaderMap() Map<String, Object> headerParams, @Body RequestBody params);

    @POST("v2/me/update")
    Call<ResultAPIModel<MemberModel>> changePassword(@HeaderMap() Map<String, Object> headerParams, @Body Map<String, Object> params);

    @POST("v2/addRemove/{id}/FromFavorite")
    Call<ResultAPIModel> addToFavorite(@HeaderMap() Map<String, Object> headerParams, @Path("id") int id);

    @POST("v2/delete/realEstate")
    Call<ResultAPIModel> deleteAdv(@HeaderMap() Map<String, Object> headerParams, @Body Map<String, Object> params);

    @POST("v2/sendMsg")
    Call<ResultAPIModel> sendMessage(@HeaderMap() Map<String, Object> headerParams, @Body Map<String, Object> params);

    @POST("v2/RealEstate/{id}/show")
    Call<ResultAPIModel<PlaceModel>> getRealEstateDetails(@HeaderMap() Map<String, Object> headerParams, @Path("id") int id);

    @POST("v2/createRealEstate")
    Call<ResultAPIModel<Integer>> createRealEstate(@HeaderMap() Map<String, Object> headerParams, @Body Map<String, Object> params);

    @POST("v2/update/{id}/RealEstate")
    Call<ResultAPIModel> updateRealEstate(@HeaderMap() Map<String, Object> headerParams, @Path("id") int id, @Body Map<String, Object> params);

    @POST("v2/deleteImg")
    Call<ResultAPIModel> deletePhoto(@HeaderMap() Map<String, Object> headerParams, @Body Map<String, Object> params);

    @POST("v2/addImg")
    Call<ResultAPIModel<PhotoModel>> uploadPhoto(@HeaderMap() Map<String, Object> headerParams, @Body RequestBody params);

    @POST("v2/contact/us")
    Call<ResultAPIModel> sendContactUs(@HeaderMap() Map<String, Object> headerParams, @Body Map<String, Object> params);

    @POST("v2/sendReport")
    Call<ResultAPIModel> reportAdv(@HeaderMap() Map<String, Object> headerParams, @Body Map<String, Object> params);

    /* ------------------------- GET Handle ------------------------- */

    @GET("v2/getCities")
    Call<ResultAPIModel<List<CityModel>>> getCities(@HeaderMap() Map<String, Object> headerParams);

    @GET("v2/getRealEstateType")
    Call<ResultAPIModel<List<PlaceTypeModel>>> getRealEstateType(@HeaderMap() Map<String, Object> headerParams);

    @GET("v2/getRealEstateOperation")
    Call<ResultAPIModel<List<PlaceOperationModel>>> getRealEstateOperation(@HeaderMap() Map<String, Object> headerParams);

    @GET("getMainSection")
    Call<ResultAPIModel<AllPlacesModel>> getMainSection(@HeaderMap() Map<String, Object> headerParams);

    @GET("v2/me/favourites")
    Call<ResultAPIModel<AllPlacesModel>> getFavorite(@HeaderMap() Map<String, Object> headerParams, @Query("page") int page);

    @GET("v2/getRealEstates")
    Call<ResultAPIModel<AllPlacesModel>> getRealEstates(@HeaderMap() Map<String, Object> headerParams, @QueryMap Map<String, Object> queryParam);

    @GET("v2/myRealEstate")
    Call<ResultAPIModel<AllPlacesModel>> getMyRealEstates(@HeaderMap() Map<String, Object> headerParams, @Query("page") int page);

    @GET("v2/myMsg")
    Call<ResultAPIModel<AllChatsModel>> getChats(@HeaderMap() Map<String, Object> headerParams, @Query("page") int page);

    @GET("v2/MsgDetails")
    Call<ResultAPIModel<AllMessagesModel>> getChatMessages(@HeaderMap() Map<String, Object> headerParams, @Query("user_id") int userId, @Query("page") int page);

    @GET("v2/searchRealEstates")
    Call<ResultAPIModel<AllPlacesModel>> searchPlaces(@HeaderMap() Map<String, Object> headerParams, @QueryMap Map<String, Object> queryParam);

    @GET("v2/me")
    Call<ResultAPIModel<MemberModel>> getMyProfile(@HeaderMap() Map<String, Object> headerParams);

    @GET("v1/pages")
    Call<ResultAPIModel<List<PageModel>>> getPages(@HeaderMap() Map<String, Object> headerParams);

    @GET("v2/terms_and_conditions")
    Call<ResultAPIModel<PageModel>> getTerms(@HeaderMap() Map<String, Object> headerParams);

    @GET("v1/pages/{slug}")
    Call<ResultAPIModel<PageModel>> getPageBySlug(@HeaderMap() Map<String, Object> headerParams, @Path("slug") String slug);

    /* ------------------------- PUT Handle ------------------------- */

//    @PUT("v1/me/notifications/{notification_id}/mark-as-read/")
//    Call<ResultAPIModel> readNotification(@HeaderMap() Map<String, Object> headerParams, @Path("notification_id") Object id);

    /* ------------------------- PATCH Handle ------------------------- */

//    @PATCH("v1/me")
//    Call<ResultAPIModel<MemberModel>> updateProfilePatch(@HeaderMap() Map<String, Object> headerParams, @Body Map<String, Object> params);

    /* ------------------------- DELETE Handle ------------------------- */

//    @DELETE("v1/bookings/{id}/cancel")
//    Call<ResultAPIModel> cancelReservation(@HeaderMap() Map<String, Object> headerParams, @Path("id") int id);

//    @DELETE("v1/me/favorites/{id}")
//    Call<ResultAPIModel> removeUserFromWishlist(@HeaderMap() Map<String, Object> headerParams, @Path("id") int id);
//
//    @DELETE("v1/me/ignored-list/{id}")
//    Call<ResultAPIModel> removeUserFromIgnore(@HeaderMap() Map<String, Object> headerParams, @Path("id") int id);

//    @DELETE("v1/chats/{id}")
//    Call<ResultAPIModel> deleteChat(@HeaderMap() Map<String, Object> headerParams, @Path("id") int id);

}


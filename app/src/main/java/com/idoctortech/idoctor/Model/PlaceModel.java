package com.idoctortech.idoctor.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlaceModel {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("real_estate_title")
    @Expose
    public String title;
    @SerializedName("area")
    @Expose
    public String area;
    @SerializedName("city_id")
    @Expose
    public int cityId;
    @SerializedName("city_name")
    @Expose
    public String cityName;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("status_buy")
    @Expose
    public Object statusBuy;
    @SerializedName("first_img")
    @Expose
    public String first_img;
    @SerializedName("real_estate_operation_name")
    @Expose
    public String operationName;
    @SerializedName("real_estate_type_name")
    @Expose
    public String typeName;
    @SerializedName("real_estate_operation_id")
    @Expose
    public int operationId;
    @SerializedName("real_estate_type_id")
    @Expose
    public int typeId;
    @SerializedName("status")
    @Expose
    public int status;
    @SerializedName("real_estates_owner_type")
    @Expose
    public String ownerType;//person or company
    @SerializedName("in_favorite")
    @Expose
    public Object isFavorite;
    @SerializedName("negotiable_status")
    @Expose
    public Object isNegotiable;
    @SerializedName("installment_status")
    @Expose
    public Object isInstallable;
    @SerializedName("bank_status")
    @Expose
    public Object byBank;
    @SerializedName("deleted_at")
    @Expose
    public Object deletedAt;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("user_id")
    @Expose
    public int userId;
    @SerializedName("user_name")
    @Expose
    public String userName;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("user_logo")
    @Expose
    public String avatar;
    @SerializedName("share_link")
    @Expose
    public String shareLink;
    @SerializedName("real_estate_type")
    @Expose
    public PlaceTypeModel placeType;
    @SerializedName("real_estate_operation")
    @Expose
    public PlaceOperationModel placeOperation;
    @SerializedName("photos")
    @Expose
    public List<PhotoModel> photos = null;

    public static String OWNER_PERSON = "person", OWNER_COMPANY = "company";

    public boolean getIsFavorite() {
        if (isFavorite instanceof Boolean)
            return (boolean) isFavorite;
        else if (isFavorite instanceof Double)
            return (double) isFavorite == 1;
        else if (isFavorite instanceof Integer)
            return (int) isFavorite == 1;
        else
            return false;
    }

    public boolean getIsNegotiable() {
        if (isNegotiable instanceof Boolean)
            return (boolean) isNegotiable;
        else if (isNegotiable instanceof Double)
            return (double) isNegotiable == 1;
        else if (isNegotiable instanceof Integer)
            return (int) isNegotiable == 1;
        else
            return false;
    }

    public boolean getIsInstallable() {
        if (isInstallable instanceof Boolean)
            return (boolean) isInstallable;
        else if (isInstallable instanceof Double)
            return (double) isInstallable == 1;
        else if (isInstallable instanceof Integer)
            return (int) isInstallable == 1;
        else
            return false;
    }

    public boolean getByBank() {
        if (byBank instanceof Boolean)
            return (boolean) byBank;
        else if (byBank instanceof Double)
            return (double) byBank == 1;
        else if (byBank instanceof Integer)
            return (int) byBank == 1;
        else
            return false;
    }

}

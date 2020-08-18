package com.idoctortech.idoctor.Model;

import java.util.List;

public class CreatePlaceModel {

    private int placeId;
    private String title;
    private double area;
    private String desc;
    private int cityId;
    private int placeTypeId;
    private int placeOperationId;
    private boolean canNegotiate;
    private boolean canInstallment;
    private boolean byBank;
    private String photosList;

    public int getPlaceId() {
        return placeId;
    }

    public String getTitle() {
        return title;
    }

    public double getArea() {
        return area;
    }

    public String getDesc() {
        return desc;
    }

    public int getCityId() {
        return cityId;
    }

    public int getPlaceTypeId() {
        return placeTypeId;
    }

    public int getPlaceOperationId() {
        return placeOperationId;
    }

    public boolean isCanNegotiate() {
        return canNegotiate;
    }

    public boolean isCanInstallment() {
        return canInstallment;
    }

    public boolean isByBank() {
        return byBank;
    }

    public String getPhotosList() {
        return photosList;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setPlaceTypeId(int placeTypeId) {
        this.placeTypeId = placeTypeId;
    }

    public void setPlaceOperationId(int placeOperationId) {
        this.placeOperationId = placeOperationId;
    }

    public void setCanNegotiate(boolean canNegotiate) {
        this.canNegotiate = canNegotiate;
    }

    public void setByBank(boolean byBank) {
        this.byBank = byBank;
    }

    public void setCanInstallment(boolean canInstallment) {
        this.canInstallment = canInstallment;
    }

    public void setPhotosList(List<PhotoModel> photosList) {
        String res = "";
        for (int i = 0; i < photosList.size(); i++) {
            if (i == photosList.size() - 1)
                res += photosList.get(i).id;
            else
                res += photosList.get(i).id + ",";
        }
        this.photosList = res;
    }
}

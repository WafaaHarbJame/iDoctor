package com.idoctortech.idoctor.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GooglePayUtils {

    public static final int LOAD_PAYMENT_DATA_REQUEST_CODE = 10;

    private static JSONObject getBaseRequest() {
        try {
            return new JSONObject()
                    .put("apiVersion", 2)
                    .put("apiVersionMinor", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static JSONObject getTokenizationSpecification() {
        try {
            JSONObject tokenizationSpecification = new JSONObject();

            tokenizationSpecification.put("type", "PAYMENT_GATEWAY");

            tokenizationSpecification.put(
                    "parameters",
                    new JSONObject()
                            .put("gateway", "checkoutltd")
                            .put("gatewayMerchantId", "pk_test_51651ff3-b5aa-4ef6-96c3-897e05c4370d")
                            .put("stripe:version", "2018-11-08"));

            return tokenizationSpecification;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static JSONArray getAllowedCardNetworks() {
        return new JSONArray()
                .put("AMEX")
                .put("MASTERCARD")
                .put("VISA");
    }

    private static JSONArray getAllowedCardAuthMethods() {
        return new JSONArray()
                .put("PAN_ONLY");
//                .put("CRYPTOGRAM_3DS");
    }

    private static JSONObject getBaseCardPaymentMethod() {
        try {
            JSONObject cardPaymentMethod = new JSONObject();
            cardPaymentMethod.put("type", "CARD");
            cardPaymentMethod.put(
                    "parameters",
                    new JSONObject()
                            .put("allowedAuthMethods", getAllowedCardAuthMethods())
                            .put("allowedCardNetworks", getAllowedCardNetworks()));

            return cardPaymentMethod;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static JSONObject getCardPaymentMethod() {
        try {

            JSONObject cardPaymentMethod = getBaseCardPaymentMethod();
            cardPaymentMethod.put("tokenizationSpecification", getTokenizationSpecification());

            return cardPaymentMethod;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject getIsReadyToPayRequest() {
        try {
            JSONObject isReadyToPayRequest = getBaseRequest();
            isReadyToPayRequest.put(
                    "allowedPaymentMethods",
                    new JSONArray()
                            .put(getBaseCardPaymentMethod()));
            return isReadyToPayRequest;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static JSONObject getTransactionInfo(String price) {
        try {
            JSONObject transactionInfo = new JSONObject();
            transactionInfo.put("totalPrice", price);
            transactionInfo.put("totalPriceStatus", "FINAL");
            transactionInfo.put("currencyCode", "SAR");

            return transactionInfo;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static JSONObject getMerchantInfo() {
        try {
            return new JSONObject()
                    .put("merchantName", "Qulob Merchant");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject getPaymentDataRequest(String price) {
        try {
            JSONObject paymentDataRequest = getBaseRequest();

            paymentDataRequest.put(
                    "allowedPaymentMethods",
                    new JSONArray()
                            .put(getCardPaymentMethod()));
            paymentDataRequest.put("transactionInfo", getTransactionInfo(price));
            paymentDataRequest.put("merchantInfo", getMerchantInfo());

            return paymentDataRequest;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}

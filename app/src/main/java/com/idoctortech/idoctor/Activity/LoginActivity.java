package com.idoctortech.idoctor.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.idoctortech.idoctor.ApiHandler.DataFeacher;
import com.idoctortech.idoctor.Classes.Constants;
import com.idoctortech.idoctor.Classes.UtilityApp;
import com.idoctortech.idoctor.Dialogs.ErrorMessagesDialog;
import com.idoctortech.idoctor.Model.LoginMemberModel;
import com.idoctortech.idoctor.Model.MemberModel;
import com.idoctortech.idoctor.Model.ResultAPIModel;
import com.idoctortech.idoctor.R;
import com.idoctortech.idoctor.Utils.SharedPManger;

import java.util.ArrayList;

public class LoginActivity extends ActivityBase /*implements Validator.ValidationListener*/ {

//    @NotEmpty(messageResId = R.string.invalid_input)
//    @BindView(R.id.mobileTxt)
//    SAutoCompleteText mobileTxt;
//    @Password(messageResId = R.string.requaired_password, min = 3)
//    @BindView(R.id.passwordTxt)
//    STextInputEditText passwordTxt;
//    @BindView(R.id.forgetPasswordBtn)
//    STextView forgetPasswordBtn;
//    @BindView(R.id.loginBtn)
//    STextViewBold loginBtn;
//    @BindView(R.id.registerBtn)
//    STextView registerBtn;

//    Validator validator;

    private ArrayList<LoginMemberModel> historyModel;
    private ArrayList<String> history = new ArrayList<>();
    SharedPManger sharedPManger;

    int historyPosition = -1;

    String FCMToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPManger = new SharedPManger(getActiviy());

//        validator = new Validator(this);
//        validator.setValidationListener(this);

        String savedData = sharedPManger.getDataString(Constants.KEY_LOGIN_PREFERANCE);
        historyModel = new Gson().fromJson(savedData, new TypeToken<ArrayList<LoginMemberModel>>() {
        }.getType());
        if (historyModel != null) {
            setAutoCompleteSource();
        }

//        getDeviceToken();
    }

//    @Override
//    public void onValidationSucceeded() {
//        try {
//
//            final String mobileStr = NumberHandler.arabicToDecimal(mobileTxt.getText().toString());
//            final String passwordStr = NumberHandler.arabicToDecimal(passwordTxt.getText().toString());
//
//            if (!PhoneHandler.isValidPhoneNumber(mobileStr)) {
//                throw new Exception("phone");
//            }
//
//            loginUser(mobileStr, passwordStr);
//
////            String mobileWithCountryCode = "+970" + (!mobileStr.startsWith("0") ? mobileStr : "0" + mobileStr.replaceFirst("0", ""));
////            verifyMobile(mobileWithCountryCode);
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//
//    }

    private void loginUser(String mobileStr, String passwordStr) {

        final LoginMemberModel loginMemberModel = new LoginMemberModel();
        loginMemberModel.email = mobileStr;


        final MemberModel memberModel = new MemberModel();
        memberModel.mobile = mobileStr.startsWith("0") ? mobileStr : "0" + mobileStr;
        memberModel.password = passwordStr;
        memberModel.fcmToken = FCMToken;

        new DataFeacher(getActiviy(), (obj, func, IsSuccess) -> {
            if (func.equals(Constants.ERROR)) {
                ResultAPIModel result = (ResultAPIModel) obj;
                if (result != null && result.error != null) {
                    String[] errors = result.error.details;
                    ErrorMessagesDialog.with(getActiviy()).setMessages(errors).build();
                } else
                    ErrorMessagesDialog.with(getActiviy()).setMessages(getString(R.string.fail_signin)).build();
            } else {
                ResultAPIModel<MemberModel> result = (ResultAPIModel<MemberModel>) obj;
                if (IsSuccess) {

                    addSearchInput(loginMemberModel);

                    MemberModel user = result.data;

                    UtilityApp.setUserData(user);

                    if (getCallingActivity() != null) {
                        setResult(Activity.RESULT_OK);
                    } else {
                        Intent intent = new Intent(getActiviy(), Constants.MAIN_ACTIVITY_CLASS);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                    finish();

                } else {
                    Toast(getString(R.string.fail_signin));

                }
            }


        }).loginHandle(memberModel, true);

    }

//    @Override
//    public void onValidationFailed(List<ValidationError> errors) {
//
//        for (ValidationError error : errors) {
//            View view = error.getView();
//            String message = error.getCollatedErrorMessage(this);
////            System.out.println("Log error " + message);
//
//            // Display error messages ;)
//            if (view instanceof EditText) {
//                ((EditText) view).setError(message);
//            } else if (view instanceof AutoCompleteTextView) {
//                ((AutoCompleteTextView) view).setError(message);
//            }/*else if (view instanceof TextInputEditText) {
//                ((TextInputEditText) view).setError(message);
//            }*/
//        }
//
//    }

    private void setAutoCompleteSource() {
        for (LoginMemberModel loginMemberModel : historyModel) {
            history.add(loginMemberModel.email);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, history.toArray(new String[history.size()]));
//        mobileTxt.setAdapter(adapter);
    }

    private void addSearchInput(LoginMemberModel input) {
        if (historyModel == null) {
            System.out.println("Log history null");
            historyModel = new ArrayList<>();
        }
        if (!history.contains(input.email)) {
            historyModel.add(input);
        } else {
            if (historyPosition == -1) {
//                String str = mobileTxt.getText().toString();
//                historyPosition = history.indexOf(str);
            }
            historyModel.set(historyPosition, input);
        }
    }

    private void savePrefs() {
        String dataList = new Gson().toJson(historyModel);
        sharedPManger.SetData(Constants.KEY_LOGIN_PREFERANCE, dataList);
    }

    @Override
    protected void onStop() {
        savePrefs();

        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10 && resultCode == RESULT_OK) {
            if (getCallingActivity() != null) {
                setResult(RESULT_OK);
                finish();
            } else {
                Intent intent = new Intent(getActiviy(), Constants.MAIN_ACTIVITY_CLASS);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }

        }
    }

//    private void getDeviceToken() {
//
//        FCMToken = UtilityApp.getFCMToken();
//        if (FCMToken == null) {
////
//            FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(instanceIdResult -> {
//                FCMToken = instanceIdResult.getToken();
//                UtilityApp.setFCMToken(FCMToken);
//            });
//
//        }
//
//    }

//    @OnClick({R.id.forgetPasswordBtn, R.id.loginBtn, R.id.registerBtn})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//
//            case R.id.forgetPasswordBtn:
//
//                Intent intentForget = new Intent(getActiviy(), ForgetPasswordActivity.class);
//                startActivity(intentForget);
//
//                break;
//            case R.id.loginBtn:
//
////                Intent loginIntent = new Intent(getActiviy(), MainActivity.class);
////                startActivity(loginIntent);
////                finish();
//
//                validator.validate();
//
//                break;
//            case R.id.registerBtn:
//                Intent intent = new Intent(getActiviy(), RegisterActivity.class);
//                startActivity(intent);
//                break;
//        }
//    }
}

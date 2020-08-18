package com.idoctortech.idoctor.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idoctortech.idoctor.ApiHandler.DataFetcherCallBack;
import com.idoctortech.idoctor.R;


public class InfoDialog extends Dialog {
    TextView messageTxt;
    Activity activity;
    DataFetcherCallBack dataFetcherCallBack;
    private LinearLayout okBtn;


    public InfoDialog(Activity activity, String message, boolean isHtml, final DataFetcherCallBack dataFetcherCallBack) {
        super(activity);

        this.activity = activity;

        this.dataFetcherCallBack = dataFetcherCallBack;

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        requestWindowFeature(Window.FEATURE_NO_TITLE); //before
//        setTitle(title);
        setContentView(R.layout.dialog_info);

        messageTxt = findViewById(R.id.messageTxt);
        okBtn = findViewById(R.id.okBtn);

        if (isHtml) {
            messageTxt.setText(Html.fromHtml(message));
        } else {
            if (message != null && !message.equals("")) {
                messageTxt.setText(message);
            }

        }

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dataFetcherCallBack != null)
                    dataFetcherCallBack.Result(getDialog(), "InfoDialog", true);
                dismiss();
            }
        });


        try {
            if (activity != null && !activity.isFinishing())
                show();
        } catch (Exception e) {
            dismiss();
        }


    }

    private InfoDialog getDialog() {
        return this;
    }

}

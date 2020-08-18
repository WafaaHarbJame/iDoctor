package com.idoctortech.idoctor.SpichalViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

import com.idoctortech.idoctor.Classes.Constants;

/**
 * Created by ahmed barakat on 8/20/14.
 */
public class SButton extends AppCompatButton {
    public SButton(Context context) {
        super(context);
        init();
    }

    public SButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
       Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), Constants.NORMAL_FONT);

       setTypeface(typeface);
    }
}

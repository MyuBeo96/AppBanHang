package com.myubeo.appbanhang.CustomView;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.myubeo.appbanhang.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by as1 on 4/1/2018.
 */

public class CustomPassword extends EditText{

    Drawable eye;
    Drawable eyeStrike;
    Boolean visiable = true;
    Boolean useStrike = false;
    Boolean useValidate = false;
    Drawable drawable;
    Pattern pattern;
    Matcher matcher;
    int ALPHA = (int) (255 * 0.8f);
    String MATCHER_PATTERN = "((?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{6,20})";

    public CustomPassword(Context context) {
        super(context);
        KhoiTao(null);
    }

    public CustomPassword(Context context, AttributeSet attrs) {
        super(context, attrs);
        KhoiTao(attrs);
    }

    public CustomPassword(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        KhoiTao(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomPassword(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        KhoiTao(attrs);
    }

    private void KhoiTao(AttributeSet attrs){
        this.pattern = Pattern.compile(MATCHER_PATTERN);

        if(attrs != null){
            TypedArray array = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.CustomPassword, 0, 0);
            this.useStrike = array.getBoolean(R.styleable.CustomPassword_useStrike, false);
            this.useValidate = array.getBoolean(R.styleable.CustomPassword_useValidate, false);
        }

        eye = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_black).mutate();

        eyeStrike = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_off_black).mutate();

        if(this.useValidate){
            setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(!hasFocus){
                        String chuoi = getText().toString();
                        TextInputLayout textInputLayout = (TextInputLayout) v.getParent();
                        matcher = pattern.matcher(chuoi);

                        if(!matcher.matches()) {
//                            Log.d("Kiểm tra", matcher.matches() + "");
                            textInputLayout.setErrorEnabled(true);
                            textInputLayout.setError("Bạn nhập sai mật khẩu, vui lòng kiểm tra lại");
                        }else {
                            textInputLayout.setErrorEnabled(false);
                            textInputLayout.setError("");
                        }
                    }
                }
            });
        }

        CatDat();
    }

    private void CatDat(){
        setInputType(InputType.TYPE_CLASS_TEXT | (visiable? InputType.TYPE_TEXT_VARIATION_PASSWORD:InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD));
        Drawable[] drawables = getCompoundDrawables();
        drawable = useStrike && visiable? eyeStrike: eye;
        drawable.setAlpha(ALPHA);
        setCompoundDrawablesRelativeWithIntrinsicBounds(drawables[0], drawables[1], drawable, drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && event.getX() >= (getRight() - drawable.getBounds().width())){
            visiable = !visiable;
            CatDat();
            invalidate();

        }
        return super.onTouchEvent(event);
    }
}

package com.myubeo.appbanhang.CustomView;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.myubeo.appbanhang.R;

public class CustomClear extends EditText {

    Drawable crossx;
    Drawable noneCrossx;
    Boolean visible = false;
    Drawable drawable;


    public CustomClear(Context context) {
        super(context);
        KhoiTao();
    }

    public CustomClear(Context context, AttributeSet attrs) {
        super(context, attrs);
        KhoiTao();
    }

    public CustomClear(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        KhoiTao();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomClear(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        KhoiTao();
    }

    private void KhoiTao(){
        crossx = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_black).mutate();

        noneCrossx = ContextCompat.getDrawable(getContext(), android.R.drawable.screen_background_light_transparent).mutate();
        CatDat();
    }

    private void CatDat(){
        setInputType(InputType.TYPE_CLASS_TEXT);
        Drawable[] drawables = getCompoundDrawables();
        drawable =  visible? crossx: noneCrossx;
        setCompoundDrawablesRelativeWithIntrinsicBounds(drawables[0], drawables[1], drawable, drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && event.getX() >= (getRight() - drawable.getBounds().width())){
            setText("");

        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);

        if(lengthAfter == 0 && start == 0){
            visible = false;
            CatDat();
        }else {
            visible = true;
            CatDat();
        }
    }
}

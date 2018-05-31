package com.myubeo.appbanhang.Model.DangNhap;

import android.content.Context;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;

public class ModelDangNhap {
    AccessToken accessToken;
    AccessTokenTracker accessTokenTracker;

    public AccessToken LayTokenDangNhap(){


        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken = currentAccessToken;
            }
        };

        accessToken = AccessToken.getCurrentAccessToken();

        return accessToken;
    }

    public void Destroy(){
        accessTokenTracker.stopTracking();
    }
}

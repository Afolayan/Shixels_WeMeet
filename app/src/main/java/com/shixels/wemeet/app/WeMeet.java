package com.shixels.wemeet.app;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.shixels.wemeet.helper.FontChangeCrawler;


public class WeMeet extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setTextFonts();

        FacebookSdk.sdkInitialize(getApplicationContext());
    }


    private void setTextFonts(){
        FontChangeCrawler fontChanger = new FontChangeCrawler(getAssets(), "fonts/proxima-nova-regular.ttf");
        //fontChanger.replaceFonts((ViewGroup) this.getView());

        /*FontChangeCrawler fontChanger = new FontChangeCrawler(getActivity().getAssets(), "fonts/proxima-nova-regular.ttf");
        fontChanger.replaceFonts((ViewGroup) this.getView());*/
    }
}

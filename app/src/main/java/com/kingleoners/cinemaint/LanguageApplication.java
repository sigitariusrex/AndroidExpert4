package com.kingleoners.cinemaint;

import android.app.Application;
import android.content.Context;

public class LanguageApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base,"in"));
    }
}

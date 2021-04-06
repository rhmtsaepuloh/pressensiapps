package com.folkatech.pressensiapps.common.utils;

import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.folkatech.pressensiapps.R;

/**
 * Created by Rahmat Saefulloh on 10/01/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
public class Tools {
    public static void systemBarLolipop(Activity act){
        Window window = act.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(act.getResources().getColor(R.color.colorPrimaryDark));
        }
    }
}

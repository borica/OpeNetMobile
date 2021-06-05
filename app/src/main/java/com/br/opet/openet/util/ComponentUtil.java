package com.br.opet.openet.util;

import android.content.Context;
import android.os.Build;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.content.res.AppCompatResources;

import com.br.opet.openet.R;

public class ComponentUtil {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void changeButtonBackgroundDisabled(Context mContext, Button buttonToDisable){
        buttonToDisable.setBackgroundTintList(AppCompatResources.getColorStateList(mContext, R.color.light_gray));
        buttonToDisable.setTextColor(mContext.getResources().getColor(R.color.unselect_button_text));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void changeButtonBackgroundEnabled(Context mContext, Button buttonToEnable){
        buttonToEnable.setBackgroundTintList(AppCompatResources.getColorStateList(mContext, R.color.openet_green));
        buttonToEnable.setTextColor(mContext.getResources().getColor(R.color.white));
    }

}

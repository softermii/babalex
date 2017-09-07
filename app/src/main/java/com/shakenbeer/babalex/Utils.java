package com.shakenbeer.babalex;

import android.content.Context;

/**
 * Created by onos on 07.09.17.
 */

public class Utils {

    public static int getDrawableResIdByImageTitle(Context context, String imageTitle) {
        return context.getResources().getIdentifier("@drawable/" + imageTitle, null, context.getPackageName());
    }

}

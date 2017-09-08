package com.shakenbeer.babalex.common;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by onos on 07.09.17.
 */

public class Utils {

    public static int getDrawableResIdByImageTitle(Context context, String imageTitle) {
        return context.getResources().getIdentifier("@drawable/" + imageTitle, null, context.getPackageName());
    }

    public static String loadSweetsFromAssets(AssetManager assetManager) {
        String json = null;
        try {
            InputStream inputStream = assetManager.open("babalex_products.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

}

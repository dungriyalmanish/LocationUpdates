package app.manish.locationupdates.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by manish.dungriyal on 10-05-2018.
 */

public class FrontEndUtils {

    public static boolean isValidMobileNumber(String number) {
        if (!number.isEmpty() && number.length() == 10 && number.matches("^[0-9]*$")) {
            return true;
        }
        return false;
    }

    public static String getId() {
        return "";
    }

    public static boolean isInternetConnected(Context mContext) {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null) return networkInfo.isConnectedOrConnecting();
        else return false;

    }
}

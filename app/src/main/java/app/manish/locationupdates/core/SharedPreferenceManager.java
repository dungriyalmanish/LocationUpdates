package app.manish.locationupdates.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import app.manish.locationupdates.constants.DataConstants;

/**
 * Created by manish.dungriyal on 17-05-2018.
 */

public class SharedPreferenceManager {
    private static final String TAG = "SharedPreferenceManager";
    public Context mContext;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SharedPreferenceManager(Context context) {
        mContext = context;
        sharedPreferences = mContext.getSharedPreferences(DataConstants.SHARED_PREF, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public boolean updateSharedPreference(UserInformation userInformation) {
        Log.v(TAG, "Savinf information: " + userInformation.toString());
        editor.putString(DataConstants.USER_NAME, userInformation.getName());
        editor.putString(DataConstants.USER_PHONE, userInformation.getPhone());
        return editor.commit();
    }

    public UserInformation getUserData() {
        String name = sharedPreferences.getString(DataConstants.USER_NAME, "");
        String phone = sharedPreferences.getString(DataConstants.USER_PHONE, "");
        if (name.isEmpty() && phone.isEmpty()) {
            return null;
        }
        return new UserInformation(name, "", phone);
    }
}

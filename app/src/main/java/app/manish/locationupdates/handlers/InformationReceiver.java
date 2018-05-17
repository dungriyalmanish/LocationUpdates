package app.manish.locationupdates.handlers;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import app.manish.locationupdates.constants.DataConstants;
import app.manish.locationupdates.core.SharedPreferenceManager;
import app.manish.locationupdates.core.UserInformation;

/**
 * Created by manish.dungriyal on 16-05-2018.
 */

public class InformationReceiver extends BroadcastReceiver {
    private static final String TAG = "InformationReceiver";
    ProgressDialog progressDialog;

    public InformationReceiver(ProgressDialog progressDialog) {
        this.progressDialog = progressDialog;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.v(TAG, "onReceive action=" + action);
        switch (action) {
            case DataConstants.SHOW_PROGRESS_DIALOG:
                if (progressDialog != null) {
                    progressDialog.setMessage(intent.getStringExtra(DataConstants.PROGRESS_DATA));
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                }
                break;
            case DataConstants.HIDE_PROGRESS_DIALOG:
                Log.v(TAG, "hide progress dialog = " + (progressDialog != null) + " and " + progressDialog.isShowing());
                if (progressDialog != null && progressDialog.isShowing()) {
                    Log.v(TAG, "progressdialog.hide()");
                    progressDialog.hide();
                }
                break;
            case DataConstants.UPDATE_SHAREDPREF:
                boolean success = new SharedPreferenceManager(context)
                        .updateSharedPreference((UserInformation) intent.getSerializableExtra(DataConstants.USER_INFO));
                Log.v(TAG, "Shared Preference updated=" + success);
                if (success) {
                    if (progressDialog != null && progressDialog.isShowing()) {
                        progressDialog.hide();
                    }
                }
                break;
            default:
                break;
        }

    }
}

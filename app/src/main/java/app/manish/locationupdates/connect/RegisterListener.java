package app.manish.locationupdates.connect;

import android.content.Context;

import app.manish.locationupdates.constants.DataConstants;
import app.manish.locationupdates.core.DataManager;
import app.manish.locationupdates.core.UserInformation;
import app.manish.locationupdates.view.ISignUpView;

/**
 * Created by manish.dungriyal on 10-05-2018.
 */

public class RegisterListener implements IRegisterListener {
    ISignUpView mSignUp;
    DataManager mDataManager;

    public RegisterListener(ISignUpView iSignUpView, Context context) {
        mSignUp = iSignUpView;
        mDataManager = new DataManager(this, context);

    }

    @Override
    public void register(UserInformation userInformation) {
        mSignUp.showDialog("Validating User Details...");
        String result = null;
        if (mDataManager.isValidCredentials(userInformation.getName(), userInformation.getPassword())) {
            mSignUp.hideDialog();
            mSignUp.showDialog("Uploading User Details...");
            result = mDataManager.saveUserData(userInformation);
        }
        if (result != null && result.equals(DataConstants.LOGIN_SUCCESS)) {
            mSignUp.hideDialog();
            mSignUp.registerSuccess();
        }
    }
}

package app.manish.locationupdates.connect;

import app.manish.locationupdates.constants.DataConstants;
import app.manish.locationupdates.core.DataManager;
import app.manish.locationupdates.view.ISignUpView;

/**
 * Created by manish.dungriyal on 10-05-2018.
 */

public class RegisterListener implements IRegisterListener {
    ISignUpView mSignUp;
    DataManager mDataManager;

    public RegisterListener(ISignUpView signUpView) {
        mSignUp = signUpView;
        mDataManager = new DataManager(this);

    }

    @Override
    public void tryLogin(String username, String password) {
        String result = null;
        if (mDataManager.isValidCredentials(username, password)) {
            result = mDataManager.loginUsingCredentials(username, password);
        }
        if (result != null && result.equals(DataConstants.LOGIN_SUCCESS)) {
            mSignUp.registerSuccess();
        }
    }

    @Override
    public void tryRegister(String username, String password) {
        String result = null;
        if (mDataManager.isValidCredentials(username, password)) {
            result = mDataManager.loginUsingCredentials(username, password);
        }
        if (result != null && result.equals(DataConstants.LOGIN_SUCCESS)) {
            mSignUp.registerSuccess();
        }
    }
}

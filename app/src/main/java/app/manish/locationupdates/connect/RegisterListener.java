package app.manish.locationupdates.connect;

import app.manish.locationupdates.core.DataManager;
import app.manish.locationupdates.view.ISignUpView;

/**
 * Created by manish.dungriyal on 10-05-2018.
 */

public class RegisterListener implements IRegisterListener {
    ISignUpView mSignUp;
    DataManager mDataManager;

    RegisterListener(ISignUpView signUpView) {
        mSignUp = signUpView;

    }

    @Override
    public void tryLogin() {

    }

    @Override
    public void tryRegister() {

    }
}

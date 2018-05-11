package app.manish.locationupdates.view;

/**
 * Created by manish.dungriyal on 10-05-2018.
 */

public interface ISignUpView {
    void missingData(String data);
    void loginFailed(String reason);
    void registerFailed(String reason);
    void registerSuccess();
}

package app.manish.locationupdates.view;

/**
 * Created by manish.dungriyal on 10-05-2018.
 */

public interface ISignUpView {

    void registerFailed(String reason);

    void registerSuccess();

    void showDialog(String message);

    void hideDialog();
}

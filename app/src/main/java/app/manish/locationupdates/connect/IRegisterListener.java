package app.manish.locationupdates.connect;

/**
 * Created by manish.dungriyal on 10-05-2018.
 */

public interface IRegisterListener {
    void tryLogin(String username, String password);

    void tryRegister(String username, String password);


}

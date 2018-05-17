package app.manish.locationupdates.core;

import java.io.Serializable;

/**
 * Created by manish.dungriyal on 16-05-2018.
 */

public class UserInformation implements Serializable {
    String name, phone, password;

    public UserInformation() {

    }

    public UserInformation(String name, String password, String phone) {
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

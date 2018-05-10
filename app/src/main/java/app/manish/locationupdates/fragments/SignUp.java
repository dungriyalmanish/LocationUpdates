package app.manish.locationupdates.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.manish.locationupdates.R;
import app.manish.locationupdates.connect.IRegisterListener;
import app.manish.locationupdates.connect.RegisterListener;
import app.manish.locationupdates.view.ISignUpView;
import app.manish.locationupdates.view.IView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUp extends Fragment implements View.OnClickListener, ISignUpView {

    Button b_register, b_already_user;
    EditText name, password;
    boolean isUser = false;
    IRegisterListener registerListener;
    IView iView;

    public SignUp() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        iView = (IView) context;
        registerListener = new RegisterListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sign_up, container, false);
        b_register = v.findViewById(R.id.register);
        b_already_user = v.findViewById(R.id.already_user);
        name = v.findViewById(R.id.name);
        password = v.findViewById(R.id.password);
        b_register.setOnClickListener(this);
        b_already_user.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.register:
                String user = name.getText().toString();
                String pass = password.getText().toString();
                if (isUser) {
                    registerListener.tryLogin(user, pass);
                } else {
                    registerListener.tryRegister(user, pass);
                }
                break;
            case R.id.already_user:
                if (b_already_user.getText().toString().equalsIgnoreCase(getString(R.string.register))) {
                    b_already_user.setText(R.string.alreay_user);
                    b_register.setText(R.string.register);
                    isUser = false;
                } else {
                    b_already_user.setText(R.string.register);
                    b_register.setText(R.string.login);
                    isUser = true;
                }
                break;
        }
    }

    @Override
    public void missingData(String data) {
        Toast.makeText(getContext(), "Please fill: " + data, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFailed(String reason) {
        Toast.makeText(getContext(), "Login Failed: " + reason, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerFailed(String reason) {
        Toast.makeText(getContext(), "Register Failed: " + reason, Toast.LENGTH_SHORT).show();
    }
}

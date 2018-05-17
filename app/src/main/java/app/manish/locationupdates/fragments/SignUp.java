package app.manish.locationupdates.fragments;


import android.content.Context;
import android.content.Intent;
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
import app.manish.locationupdates.constants.DataConstants;
import app.manish.locationupdates.core.UserInformation;
import app.manish.locationupdates.view.ISignUpView;
import app.manish.locationupdates.view.IView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUp extends Fragment implements View.OnClickListener, ISignUpView {

    Button b_register, b_already_user;
    EditText name, password;
    IRegisterListener registerListener;
    IView iView;
    UserInformation mUI;
    Context mContext;

    public SignUp() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        iView = (IView) context;
        mContext = context;
        registerListener = new RegisterListener(this, mContext);
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
        mUI = new UserInformation();
        mUI.setPhone(getArguments().getString(DataConstants.USER_INFO, "null"));
        return v;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.register:
                String user = name.getText().toString();
                String pass = password.getText().toString();
                mUI.setName(user);
                mUI.setPassword(pass);
                registerListener.register(mUI);
                break;
        }
    }

    @Override
    public void registerFailed(String reason) {
        Toast.makeText(getContext(), "Register Failed: " + reason, Toast.LENGTH_SHORT).show();
        hideDialog();
    }

    @Override
    public void registerSuccess() {
        iView.moveToHome();
    }

    @Override
    public void showDialog(String message) {
        mContext.sendBroadcast(new Intent(DataConstants.SHOW_PROGRESS_DIALOG).putExtra(DataConstants.PROGRESS_DATA, message));
    }

    @Override
    public void hideDialog() {
        mContext.sendBroadcast(new Intent(DataConstants.HIDE_PROGRESS_DIALOG));
    }
}

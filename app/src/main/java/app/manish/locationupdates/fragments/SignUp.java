package app.manish.locationupdates.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import app.manish.locationupdates.ChangeFragmentListener;
import app.manish.locationupdates.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUp extends Fragment implements View.OnClickListener {

    Button b_register, b_already_user;
    EditText name, password;

    public SignUp() {
    }

    ChangeFragmentListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (ChangeFragmentListener) context;
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
                listener.changeFragment();
                break;
            case R.id.already_user:
                if (b_already_user.getText().toString().equalsIgnoreCase(getString(R.string.register))) {
                    b_already_user.setText(R.string.alreay_user);
                    b_register.setText(R.string.login);
                } else {
                    b_already_user.setText(R.string.register);
                    b_register.setText(R.string.login);
                }
                break;
        }
    }
}

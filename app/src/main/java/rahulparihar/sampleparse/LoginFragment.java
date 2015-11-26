package rahulparihar.sampleparse;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by Rahul on 18-11-2015.
 */
public class LoginFragment extends Fragment {

    Button btnLogin;
    Button btnSignUp;
    String username;
    String password;
    EditText edtPassword;
    EditText edtUsername;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtUsername = (EditText) view.findViewById(R.id.username);
        edtPassword = (EditText) view.findViewById(R.id.password);

        btnLogin = (Button) view.findViewById(R.id.btnLogin);
        btnSignUp = (Button) view.findViewById(R.id.btnSignup);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                username = edtUsername.getText().toString();
                password = edtPassword.getText().toString();

                ParseUser.logInInBackground(username, password,
                        new LogInCallback() {
                            public void done(ParseUser user, ParseException e) {
                                if (user != null) {
                                    Intent intent = new Intent(
                                            getActivity(),
                                            Dashboard.class);
                                    startActivity(intent);
                                    Toast.makeText(getActivity(),
                                            "Successfully Logged in",
                                            Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(
                                            getActivity(),
                                            "No such user exist, please signup",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // Retrieve the text entered from the EditText
                username = edtUsername.getText().toString();
                password = edtPassword.getText().toString();

                // Force user to fill up the form
                if (username.equals("") && password.equals("")) {
                    Toast.makeText(getActivity(),
                            "Please complete the sign up form",
                            Toast.LENGTH_LONG).show();

                } else {
                    // Save new user data into Parse.com Data Storage
                    ParseUser user = new ParseUser();
                    user.setUsername(username);
                    user.setPassword(password);
                    user.signUpInBackground(new SignUpCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                                // Show a simple Toast message upon successful registration
                                Toast.makeText(getActivity(),
                                        "Successfully Signed up, please log in.",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getActivity(),
                                        "Sign up Error", Toast.LENGTH_LONG)
                                        .show();
                            }
                        }
                    });
                }

            }
        });
    }
}

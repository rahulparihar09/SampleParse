package rahulparihar.sampleparse;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseUser;

/**
 * Created by manish on 18-11-2015.
 */
public class Dashboard extends Activity {

    // Declare Variable
    Button logout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        ParseUser currentUser = ParseUser.getCurrentUser();
        String struser = currentUser.getUsername().toString();

        TextView txtuser = (TextView) findViewById(R.id.txtuser);
        txtuser.setText("You are logged in as " + struser);

        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                ParseUser.logOut();
                finish();
            }
        });
    }
}

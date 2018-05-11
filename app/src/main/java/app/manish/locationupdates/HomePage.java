package app.manish.locationupdates;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import app.manish.locationupdates.connect.HomeConnector;
import app.manish.locationupdates.connect.IHomeListener;
import app.manish.locationupdates.view.IHomeView;

public class HomePage extends AppCompatActivity implements IHomeView {

    private static final String TAG = "HomePage";
    NavigationView navigationView;
    IHomeListener mHL;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        drawer = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        mHL = new HomeConnector(this);
        navigationView.setNavigationItemSelectedListener(mHL);
    }

    public void openDrawer(View v) {
        drawer.requestDisallowInterceptTouchEvent(true);
        drawer.openDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            Log.v(TAG, "drawer open -> close");
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Log.v(TAG, "finish");
            finish();
        }
    }
}

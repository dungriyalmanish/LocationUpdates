package app.manish.locationupdates;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import app.manish.locationupdates.connect.HomeConnector;
import app.manish.locationupdates.connect.IHomeListener;
import app.manish.locationupdates.view.IHomeView;

public class HomePage extends AppCompatActivity implements IHomeView, OnMapReadyCallback {

    private static final String TAG = "HomePage";
    NavigationView navigationView;
    IHomeListener mHL;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window w = getWindow(); // in Activity's onCreate() for instance
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_home_page);
        drawer = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        mHL = new HomeConnector(this);
        navigationView.setNavigationItemSelectedListener(mHL);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id._map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
    }
}

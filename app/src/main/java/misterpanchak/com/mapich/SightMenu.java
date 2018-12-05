package misterpanchak.com.mapich;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.sql.Connection;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class SightMenu extends AppCompatActivity implements LocationListener {


    List<Sight> sightList;
    private LocationManager locationManager;

    RecyclerViewSightAdapter recyclerViewSightAdapter;
    EditText editText;
    /* private static final int MY_PERMISSION_REQUEST_CODE = 7172;
     private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 7172;
     private boolean mRequestLocationUpdates = false;
     private LocationRequest mLocationRequest;
     private GoogleApiClient mGoogleApiClient;
     private Location mLastLocation;
     private static int UPDATE_INTERVAL = 5000;
     private static int FATEST_INTERVAL = 3000;
     private static int DISPLACEMENT =10;
 */
    RecyclerView recyclerSightView;
    double mylongtitude = 50.454978;
    double mylatitude = 30.445443;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sight_menu);
        sightList = new ArrayList<>();
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        switch (name) {
            case "Kyiv":

                sightList.add(new Sight("Igor Sikorsky Kyiv Polytechnic Institute", R.drawable.unnamed, R.drawable.unnamed, R.drawable.unnamed, "geo:50.454978,30.445443?q=Igor Sikorsky Kyiv Polytechnic Institute", "s", true, "vul. Saint Ostapuchi", 50.454978, 30.445443));
                sightList.add(new Sight("KPI2", R.drawable.unnamed, R.drawable.unnamed, R.drawable.unnamed, "geo:50.454978,30.445443?q=Igor Sikorsky Kyiv Polytechnic Institute", "s", true, "vul. Saint Ostapuchi", 40.454978, 30.445443));
                sightList.add(new Sight("KPI3", R.drawable.unnamed, R.drawable.unnamed, R.drawable.unnamed, "geo:50.454978,30.445443?q=Igor Sikorsky Kyiv Polytechnic Institute", "s", true, "vul. Saint Ostapuchi", 10.454978, 20.445443));
                break;
            case "Lviv":

                sightList.add(new Sight("Lviv High Castle", R.drawable.high, R.drawable.high1, R.drawable.high2, "geo:49.848289,24.039417?q=Lviv High Castle", "s", true, "Lviv High Castle, Lviv, Lviv region, 79000", 49.848289, 24.039417));
                sightList.add(new Sight("Hotel ibis Styles", R.drawable.ibis, R.drawable.ibis1, R.drawable.ibis2, "geo:49.837358,24.035014?q=Hotel ibis Styles", "s", true, " 3 Shukhevycha str., Lviv, Lviv region, 79000", 49.837358, 24.035014));
                sightList.add(new Sight("Black House", R.drawable.black_house, R.drawable.black_house1, R.drawable.black_house2, "geo:49.841562,24.031144?q=Black House", "s", true, "Market Square, 18, Lviv, Lviv Oblast, 79000 ", 49.841562, 24.031144));
                break;
        }

        recyclerSightView = findViewById(R.id.RecyclerSightView);
        recyclerViewSightAdapter = new RecyclerViewSightAdapter(this, sightList);
        recyclerSightView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerSightView.setAdapter(recyclerViewSightAdapter);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        editText = findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());


            }
        });

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);

        }





    private void filter(String text){
            ArrayList<Sight> filteredList = new ArrayList<>();
            for (Sight sight : sightList) {
                if (sight.getName().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(sight);

                }
            }
            recyclerViewSightAdapter.filteredListed(filteredList);
        }
        public boolean onCreateOptionsMenu (Menu menu){
            getMenuInflater().inflate(R.menu.actionbar, menu);
            return true;
        }
        public boolean onOptionsItemSelected (MenuItem item){
        onLocationChanged(location);
            int id = item.getItemId();
           if(id==R.id.analyze) {



               ArrayList<Sight> filteredList1 = new ArrayList<>();
               for (Sight sight : sightList) {

                       if (Math.abs(sight.getLongtitude() - mylongtitude)   < 9 && Math.abs(sight.getLatitude() - mylatitude) < 9) {
                           filteredList1.add(sight);
                       }

               }
               recyclerViewSightAdapter.filteredListed1(filteredList1);
               return true;

           }



            return super.onOptionsItemSelected(item);
        }


    @Override
    public void onLocationChanged(Location location) {
         mylatitude = location.getLatitude();
         mylongtitude = location.getLongitude();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}

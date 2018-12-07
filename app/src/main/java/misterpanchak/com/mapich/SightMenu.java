package misterpanchak.com.mapich;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
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
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.sql.Connection;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class SightMenu extends AppCompatActivity {


    List<Sight> sightList;

    RecyclerViewSightAdapter recyclerViewSightAdapter;
    EditText editText;
    private FusedLocationProviderClient mFusedLocationClient;
    RecyclerView recyclerSightView;
    double mylongtitude = 0;
    double mylatitude = 0;

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
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Intent intent1 = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);

        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            mylongtitude = location.getLongitude();
                            mylatitude = location.getLatitude();
                        }
                    }
                });

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

    }


    private void filter(String text) {
        ArrayList<Sight> filteredList = new ArrayList<>();
        for (Sight sight : sightList) {
            if (sight.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(sight);

            }
        }
        recyclerViewSightAdapter.filteredListed(filteredList);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.analyze) {
            //Toast.makeText(getApplicationContext(), ""+mylongtitude+"\n"+mylatitude, Toast.LENGTH_LONG).show();

            if(mylongtitude<0){
              mylongtitude = 180 - mylongtitude;
          }
               ArrayList<Sight> filteredList1 = new ArrayList<>();
               for (Sight sight : sightList) {
                 double  mylongtitude1 = mylongtitude - sight.getLatitude();
                  double  mylatitude1 = mylatitude - sight.getLongtitude();

                   Toast.makeText(getApplicationContext(), ""+mylongtitude1+"\n"+mylatitude1, Toast.LENGTH_LONG).show();


                       if (Math.abs(sight.getLongtitude() -mylatitude)   < 0.009 && Math.abs(sight.getLatitude() - mylongtitude) < 0.009) {
                           filteredList1.add(sight);
                       }

               }
               recyclerViewSightAdapter.filteredListed1(filteredList1);
               return true;

           }



            return super.onOptionsItemSelected(item);
        }




}

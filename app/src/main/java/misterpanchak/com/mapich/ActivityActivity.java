package misterpanchak.com.mapich;

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

import java.util.ArrayList;
import java.util.List;

public class ActivityActivity extends AppCompatActivity {
    List<City> cityList;
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerView;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        //add new City
        cityList = new ArrayList<>();
        cityList.add(new City("Kyiv", R.drawable.kiev,0,0,"", "s", false, "vul. Saint Ostapuchi",0,0));
        cityList.add(new City("Lviv", R.drawable.lviw,0,0,"", "s", false, "vul. Saint Ostapuchi",0,0));
        cityList.add(new City("Drohobych", R.drawable.drogobych,0,0,"", "s", false, "vul. Saint Ostapuchi",0,0));
        cityList.add(new City("Kharkiv", R.drawable.kharkiv,0,0,"", "s", false, "vul. Saint Ostapuchi",0,0));
        cityList.add(new City("London", R.drawable.london,0,0,"", "s", false, "vul. Saint Ostapuchi",0,0));


        //RecyclerView
        recyclerView = findViewById(R.id.cityRecyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(this, cityList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(recyclerViewAdapter);
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


    }
    private void filter(String text){
        ArrayList<City> filteredList = new ArrayList<>();
        for(City city: cityList){
            if(city.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(city);

            }
        }
        recyclerViewAdapter.filteredListed(filteredList);
    }

}


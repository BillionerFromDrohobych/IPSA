package misterpanchak.com.mapich;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class SightActivity extends AppCompatActivity implements View.OnClickListener{
    int imgurl, imgurl1, imgurl2;
    TextView textView;
    ImageView imageview;
    double latitude;
    double longtitude;

    ImageButton imageButton;
    Intent intent1;
    TextView tx3;
    ImageView imageView3, imageView5, imageView6;
    String geo;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sight);
        Intent intent = getIntent();
        name = intent.getExtras().getString("name");
        latitude = intent.getExtras().getDouble("latitude");
        longtitude = intent.getExtras().getDouble("longtitude");
        String adress = intent.getExtras().getString("adress");
        imgurl = intent.getExtras().getInt("imgurl");
        imgurl1 = intent.getExtras().getInt("imgUrl1");
        imgurl2 = intent.getExtras().getInt("imgUrl2");
        geo = intent.getExtras().getString("location");
        imageview = (ImageView) findViewById(R.id.imageView2);
        textView = (TextView) findViewById(R.id.textView);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        tx3 = (TextView) findViewById(R.id.textView3);
        textView.setText(name);

        imageview.setImageResource(imgurl);
        tx3.setText(adress);

        imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView5 = (ImageView) findViewById(R.id.imageView5);
        imageView6 = (ImageView) findViewById(R.id.imageView6);
        imageView3.setImageResource(imgurl1);
        imageView5.setImageResource(imgurl2);
        imageView6.setImageResource(imgurl);
        imageButton.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        imageView5.setOnClickListener(this);
        imageView6.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButton:
                intent1 = new Intent();
                intent1.setAction(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse("geo:" + longtitude + "," + latitude + "?q=" + name));
                startActivity(intent1);
                break;
            case R.id.imageView3:
                imageview.setImageResource(imgurl1);
                break;
            case R.id.imageView5:
                imageview.setImageResource(imgurl2);
                break;
            case R.id.imageView6:
                imageview.setImageResource(imgurl);
                break;
        }
    }

}
package misterpanchak.com.mapich;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewSightAdapter extends RecyclerView.Adapter<RecyclerViewSightAdapter.MySightHolder> {
    private Context SightContext;
    private List<Sight> sightList;
    double mylongtitude = 50.454978;
    double mylatitude = 30.445443;

    Intent intent;
    public RecyclerViewSightAdapter(Context SightContext, List<Sight> sightList) {
        this.SightContext = SightContext;
        this.sightList = sightList;
    }




    @NonNull
    @Override
    public RecyclerViewSightAdapter.MySightHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater cInflater = LayoutInflater.from(SightContext);
        view = cInflater.inflate(R.layout.cardview, viewGroup, false);

        return new RecyclerViewSightAdapter.MySightHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewSightAdapter.MySightHolder myViewHolder, final int i) {
        myViewHolder.CityTextView.setText(sightList.get(i).getName());
        myViewHolder.CityImageView.setImageResource(sightList.get(i).getImgUrl());
        myViewHolder.CityCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choise( sightList.get(i).getName(),
                        sightList.get(i).getImgUrl(),
                        sightList.get(i).getImgUrl1(),
                        sightList.get(i).getImgUrl2(),
                        sightList.get(i).getLocation(),
                        sightList.get(i).getDesctription(),
                        sightList.get(i).getSightorcity(),
                        sightList.get(i).getAdress(),
                        sightList.get(i).getLatitude(),
                        sightList.get(i).getLongtitude());

            }


        });


    }

    @Override
    public int getItemCount() {
        return sightList.size();
    }

    public void choise(String name, int imgurl, int imgUrl1, int imgUrl2, String location, String desctription, boolean sightorcity, String adress, double latitude, double longtitude) {
        intent = new Intent(SightContext, SightActivity.class);

            intent.putExtra("name",name);
            intent.putExtra("adress",adress);
            // intent.putExtra("description",description);
            intent.putExtra("imgurl",imgurl);
            intent.putExtra("imgUrl1",imgUrl1);
            intent.putExtra("imgUrl2",imgUrl2);
            intent.putExtra("location",location);
            intent.putExtra("longtitude", longtitude);
            intent.putExtra("latitude", latitude);


            SightContext.startActivity(intent);


        notifyDataSetChanged();
    }
  //Search
  public void filteredListed1(ArrayList<Sight> filteredList1){
        sightList = filteredList1;
        notifyDataSetChanged();
  }
    public void filteredListed(ArrayList<Sight> filteredList){
        sightList = filteredList;
        notifyDataSetChanged();
    }

    public static class MySightHolder extends RecyclerView.ViewHolder{
        TextView CityTextView;
        ImageView CityImageView;
        CardView CityCardView;

        public MySightHolder(@NonNull View itemView) {
            super(itemView);
            CityTextView = (TextView) itemView.findViewById(R.id.cityTextView);
            CityImageView = (ImageView) itemView.findViewById(R.id.cityImageView);
            CityCardView = (CardView) itemView.findViewById(R.id.CityCardView);

        }


    }
}

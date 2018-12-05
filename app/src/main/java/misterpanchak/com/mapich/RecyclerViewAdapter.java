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

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    double mylongtitude = 50.454978;
    double mylatitude = 30.445443;

    private Context cityContext;
    private List<City> cityList;

    Intent intent;
    public RecyclerViewAdapter(Context cityContext, List<City> cityList) {
        this.cityContext = cityContext;
        this.cityList = cityList;
    }




    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater cInflater = LayoutInflater.from(cityContext);
        view = cInflater.inflate(R.layout.cardview, viewGroup, false);

        return new RecyclerViewAdapter.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder myViewHolder, final int i) {
        myViewHolder.CityTextView.setText(cityList.get(i).getName());
        myViewHolder.CityImageView.setImageResource(cityList.get(i).getImgUrl());
        myViewHolder.CityCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choise( cityList.get(i).getName());


            }


        });


    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public void choise(String name) {
        intent = new Intent(cityContext, SightMenu.class);

            intent.putExtra("name",name);



            cityContext.startActivity(intent);


        notifyDataSetChanged();
    }
    //Search
    public void filteredListed(ArrayList<City> filteredList){
        cityList = filteredList;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView CityTextView;
        ImageView CityImageView;
        CardView CityCardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            CityTextView = (TextView) itemView.findViewById(R.id.cityTextView);
            CityImageView = (ImageView) itemView.findViewById(R.id.cityImageView);
            CityCardView = (CardView) itemView.findViewById(R.id.CityCardView);

        }


    }
}

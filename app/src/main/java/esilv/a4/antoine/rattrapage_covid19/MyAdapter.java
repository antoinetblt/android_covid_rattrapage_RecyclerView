package esilv.a4.antoine.rattrapage_covid19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context ct;
    List<CountryModel> countryModel;
    int rowLayout;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.country_name) TextView  country_name;
        @BindView(R.id.cases) TextView  cases;
        @BindView(R.id.deaths) TextView  deaths;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public MyAdapter( List<CountryModel> countryModel, int rowLayout, Context ct){
        this.ct = ct;
        this.countryModel = countryModel;
        this.rowLayout  = rowLayout;

    }


    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.country_name.setText(countryModel.get(position).getCountry());
        holder.cases.setText(countryModel.get(position).getCases());
        holder.deaths.setText(countryModel.get(position).getDeaths());
    }

    @Override
    public int getItemCount() {

        return countryModel.size();
    }




}

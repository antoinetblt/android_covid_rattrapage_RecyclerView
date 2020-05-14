package esilv.a4.antoine.rattrapage_covid19;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity {


    public static String EXTRA_ID = "extra_id";
    public static String EXTRA_COUNTRY = "extra_country";
    public static String EXTRA_CASES = "extra_cases";
    public static String EXTRA_DEATHS = "extra_deaths";

    @BindView(R.id.country_name) TextView country;
    @BindView(R.id.cases) TextView cases;
    @BindView(R.id.deaths) TextView deaths;

    Context context;



    public void onCreate(Bundle sis){
        setContentView(R.layout.country_detail);


        ButterKnife.bind(this);
        int id = getIntent().getIntExtra(EXTRA_ID, 0);
        String title = getIntent().getStringExtra(EXTRA_COUNTRY);
        String overview = getIntent().getStringExtra(EXTRA_CASES);
        String time = getIntent().getStringExtra(EXTRA_DEATHS);
        country.setText(country_name);
        cases.setText(cases);
        deaths.setText(deaths);



    }

    @Override
    public boolean onNavigateUp(){
        finish();
        return true;
    }







}

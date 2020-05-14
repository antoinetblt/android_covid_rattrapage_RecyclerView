package esilv.a4.antoine.rattrapage_covid19;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class FragmentCountry extends Fragment {

    public FragmentCountry() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadCountry();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_main, container, false);
        setRetainInstance(true);
        return view;
    }


    private void loadCountry() {
        ApiService apiService = ApiBuilder.getClient(getContext()).create(ApiService.class);

        final RecyclerView recyclerView = getActivity().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));


        Call<List<CountryModel>> call = apiService.getCountryModel();
        call.enqueue(new Callback<List<CountryModel>>() {
            @Override
            public void onResponse(Call<List<CountryModel>>  call, Response<List<CountryModel>> response) {
                final List<CountryModel> country = response.body();
                recyclerView.setAdapter(new MyAdapter(country, R.layout.my_row, getContext()));
                recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                    GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
                        public boolean onSingleTapUp(MotionEvent e){
                            return true;
                        }
                    });


                    @Override
                    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e ) {
                        View child = rv.findChildViewUnder(e.getX(), e.getY());
                        if (child != null && gestureDetector.onTouchEvent(e)){
                            int position = rv.getChildAdapterPosition(child);
                            Intent i = new Intent(getContext(), DetailActivity.class);
                            i.putExtra(DetailActivity.EXTRA_COUNTRY, country.get(position).getCountry());
                            i.putExtra(DetailActivity.EXTRA_CASES, country.get(position).getCases());
                            i.putExtra(DetailActivity.EXTRA_DEATHS, country.get(position).getDeaths());
                            getContext().startActivity(i);
                        }
                        return false;
                    }

                    @Override
                    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

                    }

                    @Override
                    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<CountryModel>> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
}

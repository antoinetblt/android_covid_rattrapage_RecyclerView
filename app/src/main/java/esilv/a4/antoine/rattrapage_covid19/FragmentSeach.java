package esilv.a4.antoine.rattrapage_covid19;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class FragmentSeach extends Fragment implements SearchView.OnQueryTextListener {

    SearchView editsearch;

    public FragmentSeach() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        editsearch = (SearchView) view.findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    private void loadMovie() {
        String input_country = editsearch.getQuery().toString();

        ApiService apiService = ApiBuilder.getClient(getContext()).create(ApiService.class);

        final RecyclerView recyclerView = getActivity().findViewById(R.id.country_search);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Call<CountryModel> call = apiService.getCountryDetails(input_country);
        call.enqueue(new Callback<CountryModel>() {
            @Override
            public void onResponse(Call<CountryModel> call, Response<CountryModel> response) {
                final List<CountryModel> model = response.body();
                recyclerView.setAdapter(new MyAdapter(model, R.layout.my_row, getContext()));
                recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                    GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
                        public boolean onSingleTapUp(MotionEvent e){
                            return true;
                        }
                    });

                    @Override
                    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                        View child = rv.findChildViewUnder(e.getX(), e.getY());
                        if (child != null && gestureDetector.onTouchEvent(e)){
                            int position = rv.getChildAdapterPosition(child);
                            Intent i = new Intent(getContext(), DetailActivity.class);
                            i.putExtra(DetailActivity.EXTRA_COUNTRY, model.get(position).getCountry());
                            i.putExtra(DetailActivity.EXTRA_CASES, model.get(position).getCases());
                            i.putExtra(DetailActivity.EXTRA_DEATHS, model.get(position).getDeaths());
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
            public void onFailure(Call<CountryModel> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        loadMovie();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}

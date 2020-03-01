package com.ebeyonds.myapplication.ui.main.favorite;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ebeyonds.myapplication.R;
import com.ebeyonds.myapplication.adapter.NewsAdapter;
import com.ebeyonds.myapplication.data.entity.NewsResponse;
import com.ebeyonds.myapplication.network.NewsService;
import com.ebeyonds.myapplication.network.RetrofitClientInstance;
import com.ebeyonds.myapplication.ui.main.PageViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteFragment extends Fragment {

    private ProgressDialog progressDialog;
    private FavoriteViewModel mViewModel;
    private View root;

    private NewsAdapter adapter;
    private RecyclerView recyclerView;

    private NewsService newsService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);
        newsService = RetrofitClientInstance.getRetrofitInstance().create(NewsService.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.favorite_fragment, container, false);

        Spinner spinner = root.findViewById(R.id.spinner);

        Button search = root.findViewById(R.id.search);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                mViewModel.setQuery(selectedItem);
            }
            public void onNothingSelected(AdapterView<?> parent)
            {
//                parent.setSelection(1);
            }
        });

        mViewModel.getQuery().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Loading....");
                progressDialog.show();

                Call<NewsResponse> call = newsService.getByFavorite(mViewModel.getQuery().getValue());
                call.enqueue(new Callback<NewsResponse>() {
                    @Override
                    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                        progressDialog.dismiss();
                        mViewModel.setNewsResponse(response.body());
                    }

                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        Log.e("GET NEWS ERROR", t.getMessage());
                        Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        mViewModel.getNewsResponse().observe(getViewLifecycleOwner(), new Observer<NewsResponse>() {
            @Override
            public void onChanged(NewsResponse newsResponse) {
                recyclerView = root.findViewById(R.id.customRecyclerView);
                adapter = new NewsAdapter(getActivity(),newsResponse.getArticles());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}

package com.ebeyonds.myapplication.ui.main.topheadlines;

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
import android.widget.Toast;

import com.ebeyonds.myapplication.R;
import com.ebeyonds.myapplication.adapter.NewsAdapter;
import com.ebeyonds.myapplication.data.entity.NewsResponse;
import com.ebeyonds.myapplication.network.NewsService;
import com.ebeyonds.myapplication.network.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopHeadlinesFragment extends Fragment {

    private ProgressDialog progressDoalog;
    private TopHeadlinesViewModel mViewModel;

    private NewsAdapter adapter;
    private RecyclerView recyclerView;

    private View mView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.top_headlines_fragment, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TopHeadlinesViewModel.class);
        // TODO: Use the ViewModel
        progressDoalog = new ProgressDialog(getActivity());
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        NewsService service = RetrofitClientInstance.getRetrofitInstance().create(NewsService.class);
        Call<NewsResponse> call = service.getAllNews();
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                progressDoalog.dismiss();
                mViewModel.setNewsResponse(response.body());
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                progressDoalog.dismiss();
                Log.e("GET NEWS ERROR", t.getMessage());
                Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

        mViewModel.getNewsResponse().observe(getViewLifecycleOwner(), new Observer<NewsResponse>() {
            @Override
            public void onChanged(NewsResponse newsResponse) {
                recyclerView = mView.findViewById(R.id.customRecyclerView);
                adapter = new NewsAdapter(getActivity(),newsResponse.getArticles());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }

}

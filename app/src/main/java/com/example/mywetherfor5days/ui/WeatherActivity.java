package com.example.mywetherfor5days.ui;

import android.app.Activity;

import android.os.Bundle;
import android.os.Handler;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mywetherfor5days.R;
import com.example.mywetherfor5days.data.Repository;
import com.example.mywetherfor5days.data.WeatherItem;
import com.example.mywetherfor5days.databinding.ActivityWeatherBinding;


import java.util.List;

public class WeatherActivity extends Activity implements WeatherContract.View {

    private ActivityWeatherBinding mBinding;
    private Handler mHandler;
    private WeatherPresenter mWeatherPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHandler = new Handler();

       // getSupportActionBar().setElevation(0f);

    //    mBinding = DataBindingUtil.setContentView(this, R.layout.activity_weather);

        mBinding=DataBindingUtil.setContentView(this, R.layout.activity_weather);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        Repository repository = new Repository();
        mWeatherPresenter = new WeatherPresenter(this, repository);
        mWeatherPresenter.init();
    }

    @Override
    public void initView() {
        mBinding.errorMessage.setVisibility(View.GONE);
    }

   @Override
    public void displayData(final List<WeatherItem> weatherItems) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                // remove progress bar and error message
                mBinding.errorMessage.setVisibility(View.GONE);
                mBinding.progressBar.setVisibility(View.GONE);

                // display data
                WeatherAdapter weatherAdapter = new WeatherAdapter(weatherItems);
                mBinding.recyclerView.setAdapter(weatherAdapter);
            }
        });
    }

    @Override
    public void displayError() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mBinding.progressBar.setVisibility(View.GONE);
                mBinding.errorMessage.setVisibility(View.VISIBLE);
            }
        });
    }
}

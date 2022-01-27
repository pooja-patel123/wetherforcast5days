package com.example.mywetherfor5days.ui;




import androidx.annotation.NonNull;

import com.example.mywetherfor5days.data.Repository;
import com.example.mywetherfor5days.data.RepositoryCallback;
import com.example.mywetherfor5days.data.WeatherItem;

import java.util.List;

public class WeatherPresenter implements WeatherContract.Presenter, RepositoryCallback {

    private WeatherContract.View mView;
    private Repository mRepository;

    public WeatherPresenter(@NonNull WeatherContract.View view,
                            @NonNull Repository repository) {
        mView = view;
        mRepository = repository;
    }

    @Override
    public void init() {
        mView.initView();
        mRepository.makeRequest(this);
    }

    @Override
    public void setData(List<WeatherItem> weatherItems) {
        mView.displayData(weatherItems);
    }

    @Override
    public void setError() {
        mView.displayError();
    }

    @Override
    public void onDataLoaded(List<WeatherItem> weatherItems) {
        setData(weatherItems);
    }

    @Override
    public void onDataFailed() {
        setError();
    }
}

package com.example.mywetherfor5days.ui;




import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.mywetherfor5days.R;
import com.example.mywetherfor5days.data.WeatherItem;

import java.text.SimpleDateFormat;
import java.util.List;

class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    class WeatherViewHolder extends RecyclerView.ViewHolder {

        private TextView mDate;
        private TextView mForecast;
        private TextView mHigh;
        private TextView mLow;

        WeatherViewHolder(View view) {
            super(view);
            mDate = (TextView) view.findViewById(R.id.date1);
            mForecast = (TextView) view.findViewById(R.id.forecast);
            mHigh = (TextView) view.findViewById(R.id.high);
            mLow = (TextView) view.findViewById(R.id.low);
        }

        void bindViewHolder(final WeatherItem weatherItem) {
            mDate.setText(formatDate(weatherItem.getTimestamp()));
            mForecast.setText(weatherItem.getForecast());
            mHigh.setText(formatTemperature(itemView.getContext(), weatherItem.getHigh()));
            mLow.setText(formatTemperature(itemView.getContext(), weatherItem.getLow()));
        }

        private String formatDate(long timestamp) {
            long timeInMillis = timestamp * 1000L;
            SimpleDateFormat shortenedDateFormat = new SimpleDateFormat("EEEE");
            return shortenedDateFormat.format(timeInMillis);
        }

        private String formatTemperature(Context context, double temperature) {
            return context.getString(R.string.format_temperature, temperature);
        }
    }

    private List<WeatherItem> mWeatherItems;

    WeatherAdapter(List<WeatherItem> weatherItems) {
        mWeatherItems = weatherItems;
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        holder.bindViewHolder(mWeatherItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mWeatherItems.size();
    }
}
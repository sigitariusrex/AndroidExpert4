package com.kingleoners.cinemaint;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Parcel;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieViewModel extends ViewModel {

    private static final String API_KEY = "5b6d881e3f28159c8de4b55c57a9a2c7";
    private MutableLiveData<ArrayList<Movie>> listMutableLiveData = new MutableLiveData<>();

    public void setListMutableLiveData(){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Movie> item = new ArrayList<>();
        String URL = "https://api.themoviedb.org/3/discover/movie?api_key="+API_KEY+"&language=en-US&page=1";

        client.get(URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject object = new JSONObject(result);
                    JSONArray array = object.getJSONArray("results");

                    for (int i = 0; i < array.length(); i++){
                        JSONObject movie = array.getJSONObject(i);
                        Movie movieItem = new Movie(movie);
                        item.add(movieItem);
                    }
                    listMutableLiveData.postValue(item);
                } catch (JSONException e){
                    Log.d("onFailure", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    public LiveData<ArrayList<Movie>> getMovies(){
        return listMutableLiveData;
    }

}

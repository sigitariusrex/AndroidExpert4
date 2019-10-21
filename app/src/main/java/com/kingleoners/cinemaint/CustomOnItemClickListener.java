package com.kingleoners.cinemaint;

import android.view.View;

public class CustomOnItemClickListener implements View.OnClickListener {

    private int position;
    private ListFilmAdapter.OnItemClickCallback onItemClickCallback;

    public CustomOnItemClickListener(int position, ListFilmAdapter.OnItemClickCallback onItemClickCallback) {
        this.position = position;
        this.onItemClickCallback = onItemClickCallback;
    }

    @Override
    public void onClick(View v) {
        onItemClickCallback.onItemClicked(v, position);
    }

    public interface OnItemClickCallback {
        void onItemClicked(View view, int position);
    }
}

package com.example.bob.crawlertutorialapp;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import java.util.List;

public class YoutubeVideoAdapter extends RecyclerView.Adapter<YoutubeVideoAdapter.VideoViewHolder> {
    List<YoutubeVideo> lista;

    public YoutubeVideoAdapter(List<YoutubeVideo> lista) {
        this.lista = lista;
    }

    public YoutubeVideoAdapter() {
    }

    @NonNull
    @Override
    public YoutubeVideoAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.video_view,parent,false);


        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YoutubeVideoAdapter.VideoViewHolder holder, int position) {
        holder.videoWeb.loadData(lista.get(position).getVideoUrl(), "text/html", "utf-8");
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
    public class VideoViewHolder extends RecyclerView.ViewHolder{
        WebView videoWeb;

        public VideoViewHolder(@NonNull View itemView)
        {
            super(itemView);
            videoWeb=(WebView)itemView.findViewById(R.id.videoWebView);
            videoWeb.getSettings().setJavaScriptEnabled(true);
            videoWeb.setWebChromeClient(new WebChromeClient(){


            } );
        }
    }
}

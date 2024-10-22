package com.example.bob.crawlertutorialapp;

public class YoutubeVideo {
    String videoUrl;

    public YoutubeVideo(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public YoutubeVideo() {
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
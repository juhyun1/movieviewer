package com.themovieviewer.presentation

import android.os.Bundle
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.themovieviewer.R
import com.themovieviewer.core.model.data.Trailer

class TrailerActivity : YouTubeBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trailer)

        val trailer: Trailer? = intent.getParcelableExtra<Trailer>("trailer")
        val youtubeView = findViewById<YouTubePlayerView>(R.id.youtubeView)

        youtubeView.initialize(trailer!!.id, object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess( provider: YouTubePlayer.Provider, player: YouTubePlayer, wasRestored: Boolean ) {
                if (!wasRestored) {
                    player.cueVideo(trailer.key)
                }

                player.setPlayerStateChangeListener(object : YouTubePlayer.PlayerStateChangeListener {
                    override fun onAdStarted() {}
                    override fun onLoading() {}
                    override fun onVideoStarted() {}
                    override fun onVideoEnded() {}
                    override fun onError(p0: YouTubePlayer.ErrorReason) {}
                    override fun onLoaded(videoId: String) {
                        player.play()
                    }
                })
            }

            override fun onInitializationFailure( provider: YouTubePlayer.Provider?, result: YouTubeInitializationResult? ) {

            }
        })
    }
}
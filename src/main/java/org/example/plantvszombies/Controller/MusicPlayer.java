package org.example.plantvszombies.Controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicPlayer {
    private static MusicPlayer instance;
    private MediaPlayer mediaPlayer;

    private MusicPlayer() {
        String musicPath = getClass().getResource("/music/Moongrains.mp3").toExternalForm();
        Media media = new Media(musicPath);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public static MusicPlayer getInstance() {
        if (instance == null) {
            instance = new MusicPlayer();
        }
        return instance;
    }

    public void play() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}

package com.fbo.media.radio.vlc;

import com.fbo.media.radio.RadioPlayer;
import com.fbo.media.radio.RadioStation;
import com.fbo.media.vlc.VLCMediaPlayer;

/**
 * Created by Fred on 07/06/2015.
 */
public class VLCRadioPlayer implements RadioPlayer {

    private static final VLCRadioPlayer INSTANCE = new VLCRadioPlayer();

    private static final VLCMediaPlayer VLC_MEDIA_PLAYER = VLCMediaPlayer.getInstance();

    private VLCRadioPlayer() {
    }

    public void start(RadioStation radioStation) {
        System.out.println("VLCRadioPlayer starts " + radioStation.name());
        VLC_MEDIA_PLAYER.start(radioStation.getMrl());
    }

    public void stop() {
        VLC_MEDIA_PLAYER.stop();
    }

    @Override
    public void setVolume(int volume) {
        VLC_MEDIA_PLAYER.setVolume(volume);
    }

    public static VLCRadioPlayer getInstance() {
        return INSTANCE;
    }
}

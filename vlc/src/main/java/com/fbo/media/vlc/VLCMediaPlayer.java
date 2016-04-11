package com.fbo.media.vlc;


import com.fbo.media.MediaPlayer;
import uk.co.caprica.vlcj.binding.LibVlcFactory;
import uk.co.caprica.vlcj.binding.internal.libvlc_media_t;
import uk.co.caprica.vlcj.component.AudioMediaPlayerComponent;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayerEventListener;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Fred on 07/06/2015.
 */
public class VLCMediaPlayer implements MediaPlayer<String> {

    private static final VLCMediaPlayer INSTANCE = new VLCMediaPlayer();

    private final AudioMediaPlayerComponent mediaPlayerComponent;

    private Optional<RadioThread> radioThreadOptional = Optional.empty();

    private VLCMediaPlayer() {
        LibVLC.load();
        mediaPlayerComponent = new AudioMediaPlayerComponent();
        mediaPlayerComponent.getMediaPlayer().setPlaySubItems(true);
        mediaPlayerComponent.getMediaPlayer().addMediaPlayerEventListener(new MediaPlayerEventListener() {
            @Override
            public void mediaChanged(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer, libvlc_media_t media, String mrl) {

            }

            @Override
            public void opening(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer) {

            }

            @Override
            public void buffering(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer, float newCache) {

            }

            @Override
            public void playing(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer) {

            }

            @Override
            public void paused(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer) {

            }

            @Override
            public void stopped(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer) {

            }

            @Override
            public void forward(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer) {

            }

            @Override
            public void backward(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer) {

            }

            @Override
            public void finished(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer) {

            }

            @Override
            public void timeChanged(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer, long newTime) {

            }

            @Override
            public void positionChanged(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer, float newPosition) {

            }

            @Override
            public void seekableChanged(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer, int newSeekable) {

            }

            @Override
            public void pausableChanged(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer, int newPausable) {

            }

            @Override
            public void titleChanged(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer, int newTitle) {

            }

            @Override
            public void snapshotTaken(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer, String filename) {

            }

            @Override
            public void lengthChanged(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer, long newLength) {

            }

            @Override
            public void videoOutput(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer, int newCount) {

            }

            @Override
            public void error(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer) {

            }

            @Override
            public void mediaMetaChanged(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer, int metaType) {

            }

            @Override
            public void mediaSubItemAdded(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer, libvlc_media_t subItem) {

            }

            @Override
            public void mediaDurationChanged(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer, long newDuration) {

            }

            @Override
            public void mediaParsedChanged(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer, int newStatus) {

            }

            @Override
            public void mediaFreed(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer) {

            }

            @Override
            public void mediaStateChanged(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer, int newState) {

            }

            @Override
            public void newMedia(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer) {

            }

            @Override
            public void subItemPlayed(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer, int subItemIndex) {

            }

            @Override
            public void subItemFinished(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer, int subItemIndex) {

            }

            @Override
            public void endOfSubItems(uk.co.caprica.vlcj.player.MediaPlayer mediaPlayer) {

            }
        });
    }

    @Override
    public void start(String mrl) {
        mediaPlayerComponent.getMediaPlayer().prepareMedia(mrl);
        radioThreadOptional = Optional.of(new RadioThread(mrl));
        radioThreadOptional.get().start();
    }

    @Override
    public void stop() {
        radioThreadOptional.ifPresent(radioThread -> radioThread.stopRadio());
        radioThreadOptional = Optional.empty();
    }

    @Override
    public void setVolume(int volume) {
        mediaPlayerComponent.getMediaPlayer().setVolume(volume);
    }


    public static VLCMediaPlayer getInstance() {
        return INSTANCE;
    }


    private class RadioThread extends Thread {

        private final String mrl;
        private AtomicBoolean isStopped = new AtomicBoolean(false);

        public RadioThread(String mrl) {
            this.mrl = mrl;
        }

        @Override
        public void run() {
            System.out.println("Radio thread starts");
            mediaPlayerComponent.getMediaPlayer().play();
            while (!isStopped.get()) {
                synchronized (this) {
                    try {
                        sleep(1000);
                        System.out.println("Radio running... " + (new Date()).toString());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            mediaPlayerComponent.getMediaPlayer().stop();
            System.out.println("Radio stopped");
        }

        public void stopRadio() {
            isStopped.set(true);
        }
    }
}

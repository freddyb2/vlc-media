package com.fbo.media;

/**
 * Media player - generic with T : media identifiant type
 * Created by Fred on 07/06/2015.
 */
public interface MediaPlayer<MIT> {

    void start(MIT mediaIdentifiant);

    void stop();

    void setVolume(int volume);
}

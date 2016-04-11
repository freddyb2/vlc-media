package com.fbo.media.radio;

/**
 * Created by Fred on 07/06/2015.
 */
public enum RadioStation {

    //TODO remplacer cette classe par un fichier '*.properties' avec KEY=>Nom radio et VALUE=MRL_Path

    FRANCE_INTER_MIDFI("http://audio.scdn.arkena.com/11008/franceinter-midfi128.mp3"),
    FRANCE_INFO("http://audio.scdn.arkena.com/11006/franceinfo-midfi128.mp3");

    private final String mrl;

    RadioStation(String mrl) {
        this.mrl = mrl;
    }

    public String getMrl() {
        return mrl;
    }
}

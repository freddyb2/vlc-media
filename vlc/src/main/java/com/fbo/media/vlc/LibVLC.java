package com.fbo.media.vlc;

import com.sun.jna.NativeLibrary;

/**
 * Created by Fred on 07/06/2015.
 */
class LibVLC {

    private static boolean isLoaded = false;
    private static final String LIB_VLC_PATH = "/usr/lib";
    private static final String LIB_JNA_PATH = "/usr/lib/jni";


    public static void load() {
        if (!isLoaded){
            NativeLibrary.addSearchPath("libvlc", LIB_VLC_PATH);
            System.setProperty("jna.library.path", LIB_JNA_PATH);
            isLoaded = true;
        }
    }

    public static boolean isLoaded() {
        return isLoaded;
    }

}

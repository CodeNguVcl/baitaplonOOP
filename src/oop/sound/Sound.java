package oop.sound;

import javafx.scene.media.AudioClip;
import javafx.scene.media.AudioTrack;

import java.io.File;
import java.util.Objects;

public class Sound {
    public static AudioClip audio;
    private static String lastSound;
    public static void play(String sound) {
        if (!Objects.equals(sound, lastSound)) {
            //new Thread(new Runnable() {
            //public void run() {
            File audioFile = new File("res/sound/" + sound + ".wav");
            try {
                audio = new AudioClip(audioFile.toURI().toString());
                if (Objects.equals(sound, "background")) {
                    audio.setPriority(1);
                    audio.setCycleCount(20);
                }
                audio.play();
                lastSound = sound;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //}//).start();
    //}
}

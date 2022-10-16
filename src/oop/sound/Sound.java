// package oop.sound;

// import javafx.scene.media.AudioClip;

// import java.io.File;
// import java.util.Objects;

// public class Sound {
// public static AudioClip audio;
// private static String lastSound;

// public static void play(String sound) {
// if (Objects.equals(sound, "bombPut") || Objects.equals(sound, "bombExploded")
// || !Objects.equals(sound, lastSound)) {
// File audioFile = new File("res/sound/" + sound + ".wav");
// try {
// audio = new AudioClip(audioFile.toURI().toString());
// if (Objects.equals(sound, "background") || Objects.equals(sound,
// "menuMusic")) {
// audio.setPriority(1);
// audio.setCycleCount(20);
// }
// audio.play();
// lastSound = sound;
// } catch (Exception e) {
// e.printStackTrace();
// }
// }
// }

// public static void stop(String sound) {
// File audioFile = new File("res/sound/" + sound + ".wav");
// try {
// audio = new AudioClip(audioFile.toURI().toString());
// audio.stop();
// } catch (Exception e) {
// e.printStackTrace();
// }
// }
// }

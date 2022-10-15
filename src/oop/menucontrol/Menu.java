package oop.menucontrol;

import java.util.Objects;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.SepiaTone;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import oop.BombermanGame;
import oop.graphics.Sprite;

public class Menu {
  public static Scene startScene() {

    final ClassLoader c = ClassLoader.getSystemClassLoader();
    final Font font = Font
        .loadFont(Objects.requireNonNull(c.getResource("fonts/CollegiateInsideFLF.ttf")).toString(), 20);

    Button play = new Button("PLAY");
    play.setPrefHeight(Sprite.SCALE * 16);
    play.setPrefWidth(Sprite.SCALE * 96);
    play.setFont(
        Font.loadFont(Objects.requireNonNull(c.getResource("fonts/CollegiateBlackFLF.ttf")).toString(),
            Sprite.SCALE * 12));
    play.setLayoutX((double) (Sprite.SCALED_SIZE * BombermanGame.w) / 2 - 50 * Sprite.SCALE);
    play.setLayoutY((double) (Sprite.SCALED_SIZE * BombermanGame.h) / 2);
    play.setStyle("-fx-text-fill: #ffffff;" +
        " -fx-background-radius: 50;" +
        "-fx-background-color: rgb(96,186,251)");
    play.setOnAction(actionEvent -> {
      // am thanh bam phim o day.
      BombermanGame.chooseScene = 0;
    });
    play.setOnMouseEntered(mouseEvent -> play.setStyle("-fx-text-fill: #ffffff;" +
        " -fx-background-radius: 50;" +
        "-fx-background-color: rgb(18,128,255)"));
    play.setOnMouseExited(mouseEvent -> play.setStyle("-fx-text-fill: #ffffff;" +
        " -fx-background-radius: 50;" +
        "-fx-background-color: rgb(96,186,251)"));

    Button help = new Button("HELP");
    help.setPrefHeight(Sprite.SCALE * 16);
    help.setPrefWidth(Sprite.SCALE * 96);
    help.setFont(
        Font.loadFont(Objects.requireNonNull(c.getResource("fonts/CollegiateBlackFLF.ttf")).toString(),
            Sprite.SCALE * 12));
    help.setLayoutX((double) (Sprite.SCALED_SIZE * BombermanGame.w) / 2 - Sprite.SCALE * 50);
    help.setLayoutY((double) (Sprite.SCALED_SIZE * BombermanGame.h) / 2 + Sprite.SCALE * 30);
    help.setStyle("-fx-text-fill: #ffffff;" +
        " -fx-background-radius: 50;" +
        "-fx-background-color: rgb(96,186,251)");
    help.setOnAction(actionEvent -> {
      Stage s = new Stage();
      AnchorPane a = new AnchorPane();
      a.setStyle("-fx-background-image: url('helpMenu.png');" +
          "-fx-background-repeat: no-repeat;" +
          "-fx-background-position: top left;" +
          "-fx-background-size: 100% 100%");
      Scene scene = new Scene(a);
      s.setScene(scene);
      s.setWidth(Sprite.SCALED_SIZE * BombermanGame.w);
      s.setHeight(Sprite.SCALED_SIZE * BombermanGame.h);
      s.setResizable(false);
      s.initModality(Modality.APPLICATION_MODAL);
      s.showAndWait();
    });
    help.setOnMouseEntered(mouseEvent -> help.setStyle("-fx-text-fill: #ffffff;" +
        " -fx-background-radius: 50;" +
        "-fx-background-color: rgb(18,128,255)"));
    help.setOnMouseExited(mouseEvent -> help.setStyle("-fx-text-fill: #ffffff;" +
        " -fx-background-radius: 50;" +
        "-fx-background-color: rgb(96,186,251)"));

    AnchorPane root = new AnchorPane(play, help);
    root.setBackground(new Background(new BackgroundFill(Color.rgb(10, 2, 1), null, null)));
    root.setStyle("-fx-background-image: url('img.png');" +
        "-fx-background-repeat: no-repeat;" +
        "-fx-background-position: top left;" +
        "-fx-background-size: 100% 100%");
    root.setPrefSize(Sprite.SCALED_SIZE * BombermanGame.w, Sprite.SCALED_SIZE * BombermanGame.h);
    Scene scene = new Scene(root);
    scene.setOnKeyPressed(keyEvent -> {
      if (keyEvent.getCode() == KeyCode.ENTER) {
        // am thanh bam phim o day.
        BombermanGame.chooseScene = 0;
      }
    });
    return scene;
  }
}

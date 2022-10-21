package oop.menucontrol;

import java.util.Objects;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import oop.BombermanGame;
import oop.graphics.Sprite;
import oop.sound.Sound;

public class Menu {

  public static final ClassLoader c = ClassLoader.getSystemClassLoader();
  public static final Font font = Font
      .loadFont(Objects.requireNonNull(c.getResource("fonts/CollegiateInsideFLF.ttf")).toString(), 10 * Sprite.SCALE);

  // START MENU.
  public static Scene startScene() {

    Sound.play("menuMusic");

    Button play = new Button("PLAY");
    play.setPrefHeight(Sprite.SCALE * 16);
    play.setPrefWidth(Sprite.SCALE * 96);
    play.setFont(
        Font.loadFont(Objects.requireNonNull(c.getResource("fonts/CollegiateBlackFLF.ttf")).toString(),
            Sprite.SCALE * 12));
    play.setLayoutX((double) (Sprite.SCALED_SIZE * BombermanGame.w) / 2 - 50 * Sprite.SCALE);
    play.setLayoutY((double) (Sprite.SCALED_SIZE * BombermanGame.h) / 2 - Sprite.SCALE * 20);
    play.setStyle("-fx-text-fill: #ffffff;" +
        " -fx-background-radius: 50;" +
        "-fx-background-color: rgb(96,186,251)");
    play.setOnAction(actionEvent -> {
      // am thanh bam phim o day.
      Sound.play("menuClicked");
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
    help.setLayoutY((double) (Sprite.SCALED_SIZE * BombermanGame.h) / 2 + Sprite.SCALE * 10);
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
      s.setHeight(Sprite.SCALED_SIZE * BombermanGame.h + BombermanGame.HEIGHT_MENU);
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

    Button exit = new Button("EXIT");
    exit.setPrefHeight(Sprite.SCALE * 16);
    exit.setPrefWidth(Sprite.SCALE * 96);
    exit.setFont(
        Font.loadFont(Objects.requireNonNull(c.getResource("fonts/CollegiateBlackFLF.ttf")).toString(),
            Sprite.SCALE * 12));
    exit.setLayoutX((double) (Sprite.SCALED_SIZE * BombermanGame.w) / 2 - Sprite.SCALE * 50);
    exit.setLayoutY((double) (Sprite.SCALED_SIZE * BombermanGame.h) / 2 + Sprite.SCALE * 40);
    exit.setStyle("-fx-text-fill: #ffffff;" +
        " -fx-background-radius: 50;" +
        "-fx-background-color: rgb(96,186,251)");
    exit.setOnAction(actionEvent -> {
      BombermanGame.logout(BombermanGame.gameStage);
    });

    exit.setOnMouseEntered(mouseEvent -> exit.setStyle("-fx-text-fill: #ffffff;" +
        " -fx-background-radius: 50;" +
        "-fx-background-color: rgb(18,128,255)"));
    exit.setOnMouseExited(mouseEvent -> exit.setStyle("-fx-text-fill: #ffffff;" +
        " -fx-background-radius: 50;" +
        "-fx-background-color: rgb(96,186,251)"));

    AnchorPane root = new AnchorPane(play, help, exit);
    root.setBackground(new Background(new BackgroundFill(Color.rgb(10, 2, 1), null, null)));
    root.setStyle("-fx-background-image: url('img.png');" +
        "-fx-background-repeat: no-repeat;" +
        "-fx-background-position: top left;" +
        "-fx-background-size: 100% 100%");
    root.setPrefSize(Sprite.SCALED_SIZE * BombermanGame.w,
        Sprite.SCALED_SIZE * BombermanGame.h + BombermanGame.HEIGHT_MENU);
    Scene scene = new Scene(root);

    scene.setOnKeyPressed(keyEvent -> {
      if (keyEvent.getCode() == KeyCode.ENTER) {
        // am thanh bam phim o day.
        Sound.play("menuClicked");
        BombermanGame.chooseScene = 0;
      }
    });
    return scene;
  }

  // PAUUSE GAME.
  public static Scene pauseScene() {

    Text t1 = new Text("PAUSE");
    t1.setFont(
        Font.loadFont(Objects.requireNonNull(c.getResource("fonts/CollegiateInsideFLF.ttf")).toString(),
            16 * Sprite.SCALE));
    t1.setFill(Color.rgb(30, 35, 128));

    Button button = new Button("Press P to continue!!!");
    button.setPrefHeight(24 * Sprite.SCALE);
    button.setPrefWidth(144 * Sprite.SCALE);
    button.setFont(font);
    button.setStyle("-fx-text-fill: #1e2380;" +
        " -fx-background-radius: 50;" +
        " -fx-background-color: rgba(00, 0, 0, 0)");
    button.setOnAction(actionEvent -> {
      // them anh thanh vao day.
      Sound.play("menuEntered");
      Sound.play("bg");
      BombermanGame.chooseScene = 1;
    });

    VBox root = new VBox(t1, button);
    root.setAlignment(Pos.CENTER);
    root.setSpacing(10 * Sprite.SCALE);
    root.setPrefSize(Sprite.SCALED_SIZE * BombermanGame.w,
        Sprite.SCALED_SIZE * BombermanGame.h + BombermanGame.HEIGHT_MENU);
    root.setBackground(new Background(new BackgroundFill(Color.rgb(2, 2, 1), null, null)));
    root.setStyle("-fx-background-image: url(img.png);" +
        "-fx-background-repeat: no-repeat;" +
        "-fx-background-position: top left;" +
        "-fx-background-size: 100% 100%");

    Scene scene = new Scene(root);
    scene.setOnKeyPressed(keyEvent -> {
      if (keyEvent.getCode() == KeyCode.P) {
        // them anh thhanh vao day.
        Sound.play("menuEntered");
        Sound.play("bg");
        BombermanGame.chooseScene = 0;
      }
    });
    return scene;
  }

  // LEVEL SCENE
  public static Scene levelScene() {

    Text text = new Text("Level " + BombermanGame.level);

    text.setWrappingWidth(96 * Sprite.SCALE);
    text.setFont(
        Font.loadFont(Objects.requireNonNull(c.getResource("fonts/CollegiateInsideFLF.ttf")).toString(),
            Sprite.SCALE * 24));
    text.setFill(Color.rgb(30, 35, 128));
    text.setLayoutX((double) (Sprite.SCALED_SIZE * BombermanGame.w) / 2 - text.getWrappingWidth() / 2);
    text.setLayoutY(100 * Sprite.SCALE);

    AnchorPane root = new AnchorPane(text);
    root.setPrefSize(Sprite.SCALED_SIZE * BombermanGame.w,
        Sprite.SCALED_SIZE * BombermanGame.h + BombermanGame.HEIGHT_MENU);
    root.setBackground(new Background(new BackgroundFill(Color.rgb(10, 2, 5), null, null)));
    root.setStyle("-fx-background-image: url(img.png);" +
        "-fx-background-repeat: no-repeat;" +
        "-fx-background-position: top left;" +
        "-fx-background-size: 100% 100%");
    Scene scene = new Scene(root);
    scene.setOnKeyPressed(keyEvent -> {
      if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.P
          || keyEvent.getCode() == KeyCode.SPACE ||
          keyEvent.getCode() == KeyCode.A || keyEvent.getCode() == KeyCode.D
          || keyEvent.getCode() == KeyCode.S) {
        BombermanGame.chooseScene = 0;
      }
    });

    return scene;
  }

  // WIN LOSE SCENE.
  public static Scene win_loseScene(boolean win) {
    // them am thanh vao day (if else).
    if (win) {
      Sound.play("win");
    } else {
      Sound.play("gameOver");
    }

    DropShadow ds = new DropShadow();
    ds.setOffsetY(2 * Sprite.SCALE);
    ds.setColor(Color.rgb(72, 71, 70));
    Text text;
    if (win) {
      text = new Text("     YOU WIN !!! \n YOUR SCORE: " + BombermanGame.playerPoint);
    } else {
      text = new Text("     YOU LOSE !!! \n YOUR SCORE: " + BombermanGame.playerPoint);
    }
    text.setFont(Font.loadFont(Objects.requireNonNull(c.getResource("fonts/CollegiateBlackFLF.ttf")).toString(),
        12 * Sprite.SCALE));
    text.setFill(Color.rgb(30, 35, 128));
    text.setEffect(ds);

    Button playAgain = new Button("Play again?");
    playAgain.setPrefHeight(16 * Sprite.SCALE);
    playAgain.setPrefWidth(105 * Sprite.SCALE);
    playAgain.setFont(Font.loadFont(Objects.requireNonNull(c.getResource("fonts/CollegiateBlackFLF.ttf")).toString(),
        13 * Sprite.SCALE));
    playAgain.setStyle("-fx-text-fill: #ffffff;" +
        " -fx-background-radius: 50;" +
        " -fx-background-color: rgb(96,186,251)");
    playAgain.setOnAction(actionEvent -> {
      // them anh thanh vao day.
      Sound.play("menuClicked");
      Sound.stop("gameOver");
      Sound.stop("win");
      BombermanGame.chooseScene = 0;
      BombermanGame.playerPoint = 0;
    });
    playAgain.setOnMouseEntered(mouseEvent -> playAgain.setStyle("-fx-text-fill: #ffffff;" +
        " -fx-background-radius: 50;" +
        "-fx-background-color: rgb(18,128,255)"));
    playAgain.setOnMouseExited(mouseEvent -> playAgain.setStyle("-fx-text-fill: #ffffff;" +
        " -fx-background-radius: 50;" +
        "-fx-background-color: rgb(96,186,251)"));

    Button mainMenu = new Button("Main menu");
    mainMenu.setPrefHeight(16 * Sprite.SCALE);
    mainMenu.setPrefWidth(105 * Sprite.SCALE);
    mainMenu.setFont(Font.loadFont(Objects.requireNonNull(c.getResource("fonts/CollegiateBlackFLF.ttf")).toString(),
        13 * Sprite.SCALE));
    mainMenu.setStyle("-fx-text-fill: #ffffff;" +
        " -fx-background-radius: 50;" +
        " -fx-background-color: rgb(96,186,251)");
    mainMenu.setOnAction(actionEvent -> {
      // them anh thanh vao day.
      Sound.play("menuClicked");
      Sound.stop("gameOver");
      Sound.stop("win");
      BombermanGame.chooseScene = 2;
    });
    mainMenu.setOnMouseEntered(mouseEvent -> mainMenu.setStyle("-fx-text-fill: #ffffff;" +
        " -fx-background-radius: 50;" +
        "-fx-background-color: rgb(18,128,255)"));
    mainMenu.setOnMouseExited(mouseEvent -> mainMenu.setStyle("-fx-text-fill: #ffffff;" +
        " -fx-background-radius: 50;" +
        "-fx-background-color: rgb(96,186,251)"));

    Label empty = new Label("");
    empty.setPrefHeight(33 * Sprite.SCALE);
    VBox root = new VBox(empty, text, playAgain, mainMenu);
    root.setAlignment(Pos.CENTER);
    root.setSpacing(13 * Sprite.SCALE);
    root.setBackground(new Background(new BackgroundFill(Color.rgb(6, 2, 1), null, null)));

    root.setStyle("-fx-background-image: url('img.png');" +
        "-fx-background-repeat: no-repeat;" +
        "-fx-background-position: top left;" +
        "-fx-background-size: 100% 100%");

    root.setPrefSize(Sprite.SCALED_SIZE * BombermanGame.w,
        Sprite.SCALED_SIZE * BombermanGame.h + BombermanGame.HEIGHT_MENU);

    Scene scene = new Scene(root);
    scene.setOnKeyPressed(keyEvent -> {
      if (keyEvent.getCode() == KeyCode.ENTER) {
        // them anh thanh vao day.
        Sound.play("menuClicked");
        Sound.stop("gameOver");
        Sound.stop("win");
        BombermanGame.chooseScene = 0;
      }
    });
    return scene;
  }
}

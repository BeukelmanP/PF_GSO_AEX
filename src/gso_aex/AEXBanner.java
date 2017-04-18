package gso_aex;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.IFonds;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import server.RMIServer;

public class AEXBanner extends Application {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 100;
    public static final int NANO_TICKS = 20000000;
    // FRAME_RATE = 1000000000/NANO_TICKS = 50;

    private Text text;
    private double textLength;
    private double textPosition = 1000;
    private BannerController controller;
    private AnimationTimer animationTimer;

    @Override
    public void start(Stage primaryStage) {
        
        controller = new BannerController(this, "192.168.225.68", 1099);

        Font font = new Font("Arial", HEIGHT);
        text = new Text();
        text.setFont(font);
        text.setFill(Color.BLACK);

        Pane root = new Pane();
        root.getChildren().add(text);
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        primaryStage.setTitle("AEX banner");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.toFront();

        // Start animation: text moves from right to left
        animationTimer = new AnimationTimer() {
            private long prevUpdate;

            @Override
            public void handle(long now) {
                long lag = now - prevUpdate;
                if (lag >= NANO_TICKS) {
                    if (textPosition >= (0 - textLength)) {
                        textPosition = textPosition - 5;
                    } else {
                        textPosition = 1000;
                    }
                    String sKoers = "";
                    for (IFonds F : controller.getKoersen()) {
                        sKoers = sKoers + F.getNaam() + ": " + F.getKoers() + "; ";
                    }
                    setKoersen(sKoers);
                    // lculate new location of text
                    // TODOca
                    text.relocate(textPosition, 0);
                    prevUpdate = now;
                }
            }

            @Override
            public void start() {
                prevUpdate = System.nanoTime();
                textPosition = WIDTH;
                text.relocate(textPosition, 0);
                String sKoers = "";
                for (IFonds F : controller.getKoersen()) {
                    sKoers = sKoers + F.getNaam() + ": " + F.getKoers() + "; ";
                }
                setKoersen(sKoers);
                super.start();
            }
        };
        animationTimer.start();
    }

    public void setKoersen(String koersen) {
        text.setText(koersen);
        textLength = text.getLayoutBounds().getWidth();
    }

    @Override
    public void stop() {
        animationTimer.stop();
        controller.stop();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

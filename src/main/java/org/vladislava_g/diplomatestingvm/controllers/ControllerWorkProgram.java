package org.vladislava_g.diplomatestingvm.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.vladislava_g.diplomatestingvm.utils.BackgroundUtil;
import org.vladislava_g.diplomatestingvm.utils.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ControllerWorkProgram {
    @FXML
    private AnchorPane anchorPaneWorkProgram;

    @FXML
    private Button buttonBackWorkProgram;

    @FXML
    private ScrollPane scrollPaneWorkProgram;

    @FXML
    private VBox vboxWorkProgram;

    @FXML
    void initialize (){
        initButtonBack();
        initBackground();
        initWorkProgram();
    }

    private void initBackground() {
        BackgroundUtil.applyBackground(anchorPaneWorkProgram, "background/backgroundWorkProgram.jpg");
    }

    private void initButtonBack() {
        Utils.initGoToAnotherSceneWithClosePreviousButton(buttonBackWorkProgram, "hello-view.fxml", "Главное меню");
    }

    private void initWorkProgram(){
        File file = new File("content/workProgram");
        vboxWorkProgram.setAlignment(Pos.CENTER);
        scrollPaneWorkProgram.setFitToWidth(true);

        String[] list = file.list((dir, name) -> name.endsWith(".jpg"));
        for (int i = 0; i < list.length; i++) {
            ImageView imageView = null;
            try {
                imageView = new ImageView(new Image(new FileInputStream(new File("content/workProgram/" + list[i]))));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            imageView.setFitHeight(1280D);
            imageView.setFitWidth(850D);

            vboxWorkProgram.getChildren().addAll(imageView);
        }
    }
}

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
import java.io.FilenameFilter;

public class ControllerFos {

    @FXML
    private AnchorPane anchorPaneFos;

    @FXML
    private Button buttonBackFos;

    @FXML
    private ScrollPane scrollPaneFos;

    @FXML
    private VBox vboxFos;

    @FXML
    void initialize (){
        initBackButton();
        initBackground();
        initPageFos();
    }

    private void initBackground() {
        BackgroundUtil.applyBackground(anchorPaneFos, "background/backgroundFos.jpg");
    }

    private void initBackButton(){
        Utils.initGoToAnotherSceneWithClosePreviousButton(buttonBackFos, "hello-view.fxml", "Главное меню");
    }

    private void initPageFos(){
        File file = new File("content/fosImage");
        vboxFos.setAlignment(Pos.CENTER);
        scrollPaneFos.setFitToWidth(true);

        String[] list = file.list((dir, name) -> name.endsWith(".jpg"));
        for (int i = 0; i < list.length; i++) {
            ImageView imageView = null;
            try {
                imageView = new ImageView(new Image(new FileInputStream(new File("content/fosImage/" + list[i]))));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            imageView.setFitHeight(1280D);
            imageView.setFitWidth(850D);

            vboxFos.getChildren().addAll(imageView);
        }
    }
}

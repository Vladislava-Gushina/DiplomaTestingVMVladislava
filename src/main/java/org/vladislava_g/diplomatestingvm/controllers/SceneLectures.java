package org.vladislava_g.diplomatestingvm.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.vladislava_g.diplomatestingvm.utils.BackgroundUtil;
import org.vladislava_g.diplomatestingvm.utils.LoaderSceneUtil;
import org.vladislava_g.diplomatestingvm.utils.Utils;
import org.vladislava_g.diplomatestingvm.utils.parsers.LecturesParser;

public class SceneLectures {
    @FXML
    private VBox vboxLectures;
    @FXML
    private Button buttonBackLectures;
    @FXML
    private AnchorPane lecturesAnchorPane;


    @FXML
    void initialize() {
        initBackground();
        initButtonBack();
        loadLectures();
    }

    private void initBackground() {
        BackgroundUtil.applyBackground(lecturesAnchorPane, "background/LecturesPage.jpg");
    }

    private void initButtonBack() {
        Utils.initGoToAnotherSceneWithClosePreviousButton(buttonBackLectures, "hello-view.fxml", "Главное меню");
    }

    private void loadLectures(){
        new LecturesParser().parsLecture("content/lectures/lectures.html").forEach(lectur -> {
            vboxLectures.getChildren().addAll(createLabel(lectur.header(), 19d, false, Color.TURQUOISE), createLabel(lectur.underHeader(), 18d, true, Color.TURQUOISE),
                    createLabel(lectur.content(), 16d, false, Color.WHITE));
        });
    }

    private Label createLabel(String text, double size, boolean isUnderline, Color color){
        Label label = new Label(text);
        label.setFont(new Font(size));
        label.setStyle("-fx-font-weight: bold; -fx-font-size: " + size + ";");
        label.setEffect(new DropShadow());
        label.setWrapText(true);
        label.setTextFill(color);
        label.setUnderline(isUnderline);

        return label;
    }
}

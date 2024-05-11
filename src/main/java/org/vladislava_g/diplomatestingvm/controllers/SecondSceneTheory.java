package org.vladislava_g.diplomatestingvm.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.vladislava_g.diplomatestingvm.utils.BackgroundUtil;
import org.vladislava_g.diplomatestingvm.utils.LoaderSceneUtil;
import org.vladislava_g.diplomatestingvm.utils.Utils;
import org.vladislava_g.diplomatestingvm.utils.parsers.LabWorksParser;
import org.vladislava_g.diplomatestingvm.utils.parsers.LecturesParser;

public class SecondSceneTheory {
    @FXML
    private AnchorPane theoryAnchorPane;
    @FXML
    private ScrollPane theoryScrollPane;
    @FXML
    private Button buttonBack;
    @FXML
    private VBox vboxTheory;

    @FXML
    void initialize (){
        initBackground();
        initButtonBack();
        loadLabWorks();
    }
    
    private void initBackground(){
        BackgroundUtil.applyBackground(theoryAnchorPane, "background/FirstPageDiploma.jpg");
    }
    
    private void initButtonBack(){
        Utils.initGoToAnotherSceneWithClosePreviousButton(buttonBack, "hello-view.fxml", "Главное меню");
    }

    private void loadLabWorks(){
        new LabWorksParser().parseLabWorks("content/lab-works/laboratory_works.html").forEach(labWork -> {
            vboxTheory.getChildren().addAll(createLabel(labWork.title(), 19, false, Color.TURQUOISE), createLabel(labWork.underHeader(), 18, true, Color.WHITE),
                    createLabel(labWork.text(), 16, false, Color.WHITE));

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


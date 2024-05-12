package org.vladislava_g.diplomatestingvm.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.vladislava_g.diplomatestingvm.utils.BackgroundUtil;
import org.vladislava_g.diplomatestingvm.utils.LoaderSceneUtil;
import org.vladislava_g.diplomatestingvm.utils.Utils;
import javafx.scene.control.Pagination;
import org.vladislava_g.diplomatestingvm.utils.lectures.LabWork;
import org.vladislava_g.diplomatestingvm.utils.parsers.LabWorksParser;
import javafx.scene.web.WebView;
import org.vladislava_g.diplomatestingvm.utils.parsers.LecturesParser;

import java.util.ArrayList;
import java.util.List;

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
    private HBox hBoxPagination;
    @FXML
    private Button buttonLabWork1;
    @FXML
    private Pagination mainPagination;

    @FXML
    private ScrollPane scrollPaneMainButton;

    @FXML
    private ScrollPane scrollPanePagination;

    @FXML
    private VBox vboxMainButton;


    @FXML
    void initialize (){
        initBackground();
        initButtonBack();
        pageTransitionImplementation();
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

    private void loadLabWork(LabWork labWork){
        vboxTheory.getChildren().clear();
        vboxTheory.getChildren().addAll(createLabel(labWork.title(), 19, false, Color.TURQUOISE), createLabel(labWork.underHeader(), 18, true, Color.WHITE),
                createLabel(labWork.text(), 16, false, Color.WHITE));
    }

    private void pageTransitionImplementation(){
        List<Button> buttonList = new ArrayList<>();
        vboxMainButton.setSpacing(10d);
        vboxMainButton.getChildren().forEach(node -> {
            if(node instanceof Button button){
                buttonList.add(button);
            }
        });
       List<LabWork> labWorkList = new LabWorksParser().parseLabWorks("content/lab-works/laboratory_works.html");
        for (int i = 0; i < labWorkList.size(); i++) {
            buttonList.get(i).setText(labWorkList.get(i).title());
            int finalI = i;
            buttonList.get(i).setOnAction(actionEvent -> {
                loadLabWork(labWorkList.get(finalI));
                Label label = new Label("Перейти на следующую страницу");
                label.setUnderline(true);
                label.setTextFill(Color.BLUE);
                label.setCursor(Cursor.HAND);
                label.setFont(new Font(16));
                label.setOnMouseClicked(mouseEvent -> {
                    int id = finalI;
                    if(id + 1 == labWorkList.size()){
                        id = 0;
                    }else{
                        id++;
                    }
                    loadLabWork(labWorkList.get(id));
                });
                VBox.setMargin(label, new Insets(20, 0, 0, 0));
                vboxTheory.getChildren().add(label);
            });
        }
    }
}


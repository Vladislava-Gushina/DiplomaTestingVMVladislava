package org.vladislava_g.diplomatestingvm.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.vladislava_g.diplomatestingvm.utils.BackgroundUtil;
import org.vladislava_g.diplomatestingvm.utils.LoaderSceneUtil;
import org.vladislava_g.diplomatestingvm.utils.Utils;

public class MainController {
    @FXML
    private Pane mainPane;
    @FXML
    private VBox mainVBox;
    @FXML
    private Button buttonTheory;
    @FXML
    private Button buttonLecture;
    @FXML
    private Button buttonTest;
    @FXML
    private Button buttonFos;
    @FXML
    private Button buttonWorkProgram;



    @FXML
    void initialize (){
        initBackground();
        initButtonTheory();
        initButtonLecture();
        initButtonTest();
        initButtonFos();
        initButtonWorkProgram();
    }

    private void initBackground(){
        BackgroundUtil.applyBackground(mainPane, "background/MainMenuPage.jpg");
    }

    private void initButtonTheory(){
        Utils.initGoToAnotherSceneWithClosePreviousButton(buttonTheory, "theory.fxml", "Лабораторные работы");
    }

    private void initButtonLecture(){
        Utils.initGoToAnotherSceneWithClosePreviousButton(buttonLecture, "lectures.fxml", "Лекции");
    }

    private void initButtonTest(){
        Utils.initGoToAnotherSceneWithClosePreviousButton(buttonTest, "test-scene.fxml", "Тесты");
    }

    private void  initButtonFos(){
        Utils.initGoToAnotherSceneWithClosePreviousButton(buttonFos, "fos-scene.fxml", "ФОС");
    }

    private void initButtonWorkProgram(){
        Utils.initGoToAnotherSceneWithClosePreviousButton(buttonWorkProgram, "work-program.fxml", "Рабочая программа");
    }
}
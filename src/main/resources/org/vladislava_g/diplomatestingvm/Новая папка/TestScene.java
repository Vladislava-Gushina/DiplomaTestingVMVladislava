package org.vladislava_g.diplomatestingvm.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.vladislava_g.diplomatestingvm.utils.parsers.QuestionsParser;
import org.vladislava_g.diplomatestingvm.utils.BackgroundUtil;
import org.vladislava_g.diplomatestingvm.utils.LoaderSceneUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class TestScene {
    @FXML
    private AnchorPane anchorPaneTest;
    @FXML
    private Button buttonBackTest;
    @FXML
    private AnchorPane anchorPaneQuestion;
    private List<Button> buttons;

    @FXML
    void initialize() {
        buttons = new ArrayList<>();

        findAllButtons();
        initBackground();
        initBackground();
        initButtonBackTest();
        workingTestButtonParse();
        addSpacesToButtons();
    }

    private void addSpacesToButtons() {
        buttons.forEach(button -> {
            String buttonText = button.getText();

            int numberIndex = buttonText.indexOf("№");

            StringBuilder firstPart = new StringBuilder();
            StringBuilder secondPart = new StringBuilder();

            for (int i = 0; i < numberIndex; i++) {
                firstPart.append(buttonText.charAt(i));
            }

            for (int i = numberIndex; i < buttonText.length(); i++) {
                secondPart.append(buttonText.charAt(i));
            }

            button.setText(firstPart + " " + secondPart);
        });
    }

    private void findAllButtons() {
        anchorPaneQuestion.getChildren().forEach(object -> {
            if(object instanceof Button button){
                buttons.add(button);
            } else if (object instanceof VBox vbox) {
                vbox.getChildren().forEach(children -> {
                    if(children instanceof Button button)
                        buttons.add(button);
                });
            }
        });
    }

    private void initBackground() {
        BackgroundUtil.applyBackground(anchorPaneTest, "background/TestMenuPage.jpg");
    }

    private void initButtonBackTest() {
        buttonBackTest.setOnMouseClicked(mouseEvent -> {
            LoaderSceneUtil.loadScene("hello-view.fxml", "Главное меню");
            ((Stage) buttonBackTest.getScene().getWindow()).close();
        });
    }

    private void workingTestButtonParse(){
        buttons.forEach(button -> button.setOnAction(actionEvent -> actionToTestButton(button)));
    }

    private void actionToTestButton(@NotNull Button button){
        String buttonText = button.getText();
        int numberIndex = buttonText.indexOf("№") + 1;

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = numberIndex; i < buttonText.length(); i++) {
            stringBuilder.append(buttonText.charAt(i));
        }
        openTestParsing(stringBuilder.toString());
    }

    private void openTestParsing(String testNumber){
        File file = new File("tests");
        List<String> fileList = new java.util.ArrayList<>(List.of(Objects.requireNonNull(file.list())));

        String nedentTestFileName = null;

        for(String fileName : fileList){
            if(fileName.contains("test" + testNumber)){
                nedentTestFileName = fileName;
                break;
            }
        }
        parseQuestions("tests/" + nedentTestFileName, testNumber);
    }

    private void parseQuestions(String fileName, String testNumber){
        QuestionsParser questionsParser = new QuestionsParser();

        ContentTest contentTest = (ContentTest) LoaderSceneUtil.loadScene("content-test.fxml", "Тест " + testNumber);
        contentTest.initQuestions(questionsParser.parseQuestions(fileName));

        ((Stage) anchorPaneQuestion.getScene().getWindow()).close();
    }
}

package org.vladislava_g.diplomatestingvm.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.jetbrains.annotations.NotNull;
import org.vladislava_g.diplomatestingvm.utils.Utils;
import org.vladislava_g.diplomatestingvm.utils.alerts.ErrorDialog;
import org.vladislava_g.diplomatestingvm.utils.questions.AnsweredQuestion;
import org.vladislava_g.diplomatestingvm.utils.questions.Question;
import org.vladislava_g.diplomatestingvm.utils.BackgroundUtil;
import org.vladislava_g.diplomatestingvm.utils.alerts.ConformDialog;

import java.util.ArrayList;
import java.util.List;

public class ContentTest {
    @FXML
    private AnchorPane anchorPaneContent;
    @FXML
    private Button buttonBackContent;
    @FXML
    private ScrollPane scrollPaneContent;
    @FXML
    private VBox vboxContent;
    @FXML
    private Button endTestButton;
    @FXML
    private Button buttonReset;
    @FXML
    private Label resultLabel;
    private List<ToggleGroup> toggleGroups;
    private List<AnsweredQuestion> answeredQuestionList;

    @FXML
    void initialize(){
        toggleGroups = new ArrayList<>();
        answeredQuestionList = new ArrayList<>();

        initBackground();
        initButtonBackContent();
        initButtons();
    }

    private void initButtons() {
        buttonReset.setOnAction(actionEvent -> resetTest());
        endTestButton.setOnAction(actionEvent -> checkTest());
    }

    private void resetTest(){
        if(requestPermissionToReset()) {
            toggleGroups.forEach(toggleGroup -> toggleGroup.selectToggle(null));
            resetShadowsEffectOnQuestionTiles();

            if(resultLabel.isVisible())
                resultLabel.setVisible(false);
        }
    }

    private void checkTest(){
        resetShadowsEffectOnQuestionTiles();

        if(!hasUnansweredQuestion()) {
            int rightAnswers = 0;

            for (AnsweredQuestion answeredQuestion : answeredQuestionList) {
                ToggleGroup answeredQuestionToggleGroup = answeredQuestion.getToggleGroup();
                int selectedToggleIndex = answeredQuestionToggleGroup.getToggles().indexOf(answeredQuestionToggleGroup.getSelectedToggle());

                Node currentQuestionTile = answeredQuestion.getQuestionTile();

                if (selectedToggleIndex == answeredQuestion.getQuestion().rightQuestion() - 1) {
                    rightAnswers++;
                    addShadowToQuestionTile(currentQuestionTile, false);
                }else {
                    addShadowToQuestionTile(currentQuestionTile, true);
                }
            }

            resultLabel.setVisible(true);
            int mark = (rightAnswers * 5) / answeredQuestionList.size();
            resultLabel.setText("Вы ответили верно на " + rightAnswers + " / " + answeredQuestionList.size() + " вопросов (" + Math.round((float) (rightAnswers * 100) / answeredQuestionList.size()) + "%)" + " Оценка: " + (Math.max(mark, 2)));
        }else {
            ErrorDialog.show(new Exception("Ответьте на все вопросы"));
        }
    }

    private void addShadowToQuestionTile(@NotNull Node questionTile, boolean isError){
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(isError ? Color.RED: Color.GREEN);

        questionTile.setEffect(dropShadow);
    }

    private boolean requestPermissionToReset() {
        return ConformDialog.show("Подтверждение действия", "Вы действительно хотите сбросить все ответы?", "Выберете действие");
    }

    private boolean hasUnansweredQuestion(){
        return toggleGroups.stream().allMatch(predicate -> predicate.getSelectedToggle() == null);
    }

    private void initBackground(){
        BackgroundUtil.applyBackground(anchorPaneContent, "background/DesignOfTestsPage.jpg");
    }

    private void initButtonBackContent(){
        Utils.initGoToAnotherSceneWithClosePreviousButton(buttonBackContent, "test-scene.fxml", "Тесты");
    }

    private void resetShadowsEffectOnQuestionTiles(){
        answeredQuestionList.forEach(answeredQuestion -> {
            Node questionTile = answeredQuestion.getQuestionTile();

            if(questionTile != null)
                questionTile.setEffect(new DropShadow());
        });
    }
    
    public void initQuestions(@NotNull List<Question> questions){
        for (int i = 0; i < questions.size(); i++) {
            VBox questionVbox = createVBoxTile(i+1, questions.get(i));
            vboxContent.getChildren().add(questionVbox);
        }
    }

    private @NotNull VBox createVBoxTile(int questionNumber, @NotNull Question question){
        VBox vBox = new VBox();
        vBox.setSpacing(10d);
        vBox.setStyle("-fx-background-color: #ccc; -fx-background-radius: 13px;");
        vBox.setEffect(new DropShadow());
        VBox.setMargin(vBox, new Insets(10d, 10d, 10d, 10d));

        Label questionTextLabel = new Label(questionNumber + ". " +
                question.questionText());
        questionTextLabel.setTextFill(Color.BLACK);
        questionTextLabel.setFont(new Font(14));
        questionTextLabel.setWrapText(true);
        questionTextLabel.setTooltip(new Tooltip(question.questionText()));
        VBox.setMargin(questionTextLabel, new Insets(0d, 0d, 0d, 10d));
        vBox.getChildren().add(questionTextLabel);

        VBox radioButtonsVbox = new VBox();
        HBox.setHgrow(radioButtonsVbox, Priority.ALWAYS);
        radioButtonsVbox.setPadding(new Insets(5d, 5d, 5d,5d));
        radioButtonsVbox.setSpacing(5d);
        radioButtonsVbox.setAlignment(Pos.CENTER_LEFT);

        ToggleGroup answersToggleGroup = new ToggleGroup();

        question.answers().forEach(answer -> {
            RadioButton radioButton = new RadioButton(answer);
            radioButton.setFont(new Font(14));
            radioButton.setTextFill(Color.BLACK);
            radioButton.setToggleGroup(answersToggleGroup);

            radioButtonsVbox.getChildren().add(radioButton);
        });

        vBox.getChildren().add(radioButtonsVbox);

        answeredQuestionList.add(new AnsweredQuestion(question, answersToggleGroup, vBox));
        toggleGroups.add(answersToggleGroup);

        return vBox;
    }
}

package org.vladislava_g.diplomatestingvm.utils.questions;

import javafx.scene.Node;
import javafx.scene.control.ToggleGroup;

public class AnsweredQuestion{
    private final Question question;
    private final ToggleGroup toggleGroup;
    private Node questionTile;

    public AnsweredQuestion(Question question, ToggleGroup toggleGroup) {
        this.question = question;
        this.toggleGroup = toggleGroup;
    }

    public AnsweredQuestion(Question question, ToggleGroup toggleGroup, Node questionTile) {
        this.question = question;
        this.toggleGroup = toggleGroup;
        this.questionTile = questionTile;
    }

    public Question getQuestion() {
        return question;
    }

    public ToggleGroup getToggleGroup() {
        return toggleGroup;
    }

    public Node getQuestionTile() {
        return questionTile;
    }
}

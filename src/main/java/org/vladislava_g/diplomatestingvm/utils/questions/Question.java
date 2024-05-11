package org.vladislava_g.diplomatestingvm.utils.questions;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record Question(String questionText, List<String> answers, byte rightQuestion) {
    @Contract(pure = true)
    @Override
    public @NotNull String toString() {
        return "Question{" +
                "questionText='" + questionText + '\'' +
                ", answers=" + answers +
                '}';
    }
}

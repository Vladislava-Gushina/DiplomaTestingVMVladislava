package org.vladislava_g.diplomatestingvm.utils;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public final class Utils {
    public static void initGoToAnotherSceneWithClosePreviousButton(@NotNull Button button, String goToSceneName, String title){
        button.setOnAction(actionEvent -> {
            LoaderSceneUtil.loadScene(goToSceneName, title);
            ((Stage) button.getScene().getWindow()).close();
        });
    }
}

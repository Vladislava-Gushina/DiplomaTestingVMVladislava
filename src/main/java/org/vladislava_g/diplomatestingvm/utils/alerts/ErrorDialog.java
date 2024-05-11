package org.vladislava_g.diplomatestingvm.utils.alerts;

import javafx.scene.control.Alert;
import org.jetbrains.annotations.NotNull;

public final class ErrorDialog {
    public static void show(@NotNull Exception e){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Возникла ошибка");
        alert.setHeaderText("Исключение: " + e);
        alert.setContentText("Причина: " + e.getCause());
        alert.showAndWait();
    }
}

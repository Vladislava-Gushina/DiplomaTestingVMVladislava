package org.vladislava_g.diplomatestingvm.utils.alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public final class ConformDialog {
    public static boolean show(String title, String headerText, String contextText){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contextText);

        Optional<ButtonType> optionalButtonType = alert.showAndWait();
        return optionalButtonType.filter(buttonType -> buttonType == ButtonType.OK).isPresent();
    }
}

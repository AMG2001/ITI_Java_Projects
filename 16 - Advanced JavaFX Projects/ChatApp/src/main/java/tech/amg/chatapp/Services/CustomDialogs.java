package tech.amg.chatapp.Services;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class CustomDialogs {

    static public void showInformativeDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.OK);
        alert.showAndWait();
    }

    static public void showWarningDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING, message, ButtonType.OK);
        alert.showAndWait();
    }
}
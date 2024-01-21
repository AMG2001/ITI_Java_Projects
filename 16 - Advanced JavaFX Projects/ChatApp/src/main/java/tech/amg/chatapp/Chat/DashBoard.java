package tech.amg.chatapp.Chat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tech.amg.chatapp.Services.CustomDialogs;
import tech.amg.chatapp.Users.User;

import java.io.File;
import java.io.IOException;

public class DashBoard {
    @FXML
    private TextField tf_userName;

    @FXML
    private ImageView imgView;

    @FXML
    private Button btn_pickImage;

    @FXML
    private Button btn_yalla;
    private Image userImage = new Image("/OIP.jpeg");

    public DashBoard() {

    }

    @FXML
    void btn_addUesrToRoom(ActionEvent event) {
        User newUser;
        if (tf_userName.getLength() == 0) {
            CustomDialogs.showWarningDialog("You can't leave Name Field Empty !!");
        } else {
            newUser = new User(tf_userName.getText().trim(), userImage);
            openNewChatForUser(newUser);
        }
    }


    private void openNewChatForUser(User user) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Room.fxml"));
        try {
            Parent otherFXML = loader.load();
            Room roomController = loader.getController();
            roomController.setUser(user);
            Stage newStage = new Stage();
            Scene newScene = new Scene(otherFXML);
            newStage.setScene(newScene);
            newStage.setTitle("Chat Room - " + user.name);
            newStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void pickAnImageFromDevice(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose an image");
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                try {
                    userImage = new Image(file.toURI().toString());
                    imgView.setImage(userImage);
                } catch (RuntimeException re) {
                    re.printStackTrace();
                }
            } else {
                throw new RuntimeException("No image selected.");
            }
        } catch (Exception e) {
            System.out.println("Image Not Taken !!");
        }
    }

}

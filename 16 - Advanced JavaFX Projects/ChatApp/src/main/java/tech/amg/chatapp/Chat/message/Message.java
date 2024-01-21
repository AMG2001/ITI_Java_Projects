package tech.amg.chatapp.Chat.message;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import tech.amg.chatapp.Users.User;

public class Message {
    @FXML
    public HBox msg_row;

    @FXML
    public ImageView img_sender;

    @FXML
    public Label msg_content;
    public User user;
    public String content;

    public Message(User user, String content) {
        this.user = user;
        this.content = content;
    }

    public Message() {

    }
}

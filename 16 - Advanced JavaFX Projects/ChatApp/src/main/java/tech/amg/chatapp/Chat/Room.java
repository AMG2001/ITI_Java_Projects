package tech.amg.chatapp.Chat;

import javafx.beans.property.SimpleListProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import tech.amg.chatapp.Chat.message.Message;
import tech.amg.chatapp.Chat.message.MessageLoader;
import tech.amg.chatapp.Users.User;

public class Room {

    @FXML
    private Button btn_sendMessage;

    @FXML
    private ListView<Message> lv_messages;

    @FXML
    private TextArea ta_message;

    User user;

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    public void initialize() {
        lv_messages.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Message message, boolean empty) {
                super.updateItem(message, empty);
                if (empty || message == null) {
                    setGraphic(null);
                } else {
                    ImageView userImage = new ImageView(message.user.image);
                    userImage.setFitWidth(50);
                    userImage.setFitHeight(50);
                    userImage.setPreserveRatio(true);
                    HBox hbox = new HBox();
                    if (message.user.name == user.name) {
                        hbox.getChildren().add(new Label(message.content));
                        hbox.getChildren().add(userImage);
                        hbox.setAlignment(Pos.CENTER_RIGHT);
                    } else {
                        hbox.getChildren().add(userImage);
                        hbox.getChildren().add(new Label(message.content));
                        hbox.setAlignment(Pos.CENTER_LEFT);
                    }
                    setGraphic(hbox);
                }
            }
        });
        lv_messages.itemsProperty().bind(new SimpleListProperty<>(RoomMessages.messagesList));
    }

    @FXML
    void sendMessage(ActionEvent event) {
        MessageLoader messageLoader = new MessageLoader();
        messageLoader.getMessage().content = ta_message.getText().trim();
        messageLoader.getMessage().user = user;
        RoomMessages.messagesList.add(messageLoader.getMessage());
    }

    public void loadRoomChat() {
        MessageLoader messageLoader = new MessageLoader();
        RoomMessages.messagesList.forEach(message -> {
            messageLoader.setMessage(message);

            if (message.user.name == user.name)
                message.msg_row.setAlignment(Pos.CENTER_RIGHT);
            else
                message.msg_row.setAlignment(Pos.CENTER_LEFT);
        });
        System.out.println("Room Chat is Loaded");
    }

}

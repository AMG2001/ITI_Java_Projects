package tech.amg.chatapp.Chat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tech.amg.chatapp.Chat.message.Message;

public class RoomMessages {
    public static ObservableList<Message> messagesList = FXCollections.observableArrayList();
}

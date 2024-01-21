package tech.amg.chatapp.Chat.message;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class MessageLoader {
    public void setMessage(Message message) {
        this.message = message;
    }

    private Message message;
    private Parent messageLoader;

    public MessageLoader() {
        loadMessageController();
    }

    public Message getMessage() {
        return message;
    }

    public Parent getMessageLoader() {
        return messageLoader;
    }

    private void loadMessageController() {
        FXMLLoader msgFXMLLoader = new FXMLLoader(getClass().getResource("/message.fxml"));
        try {
            messageLoader = msgFXMLLoader.load();
            message = msgFXMLLoader.getController();
        } catch (IOException e) {
            System.out.println("Error while loading Message FXML File in : " + getClass().getName());
            throw new RuntimeException(e);
        }

    }
}

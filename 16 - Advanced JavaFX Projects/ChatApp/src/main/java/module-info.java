module tech.amg.chatapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens tech.amg.chatapp to javafx.fxml;
    exports tech.amg.chatapp;
    exports tech.amg.chatapp.Users;
    opens tech.amg.chatapp.Users to javafx.fxml;

    // Add this line
    opens tech.amg.chatapp.Chat to javafx.fxml;
    opens tech.amg.chatapp.Chat.message to javafx.fxml;
}

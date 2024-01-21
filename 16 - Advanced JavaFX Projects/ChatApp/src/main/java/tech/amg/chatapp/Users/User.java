package tech.amg.chatapp.Users;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import java.io.File;

public class User {
    public String name;
    public Image image;

    public User(String name, Image image) {
        this.name = name;
        this.image = image;
    }
}

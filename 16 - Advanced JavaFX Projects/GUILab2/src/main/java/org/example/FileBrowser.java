package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;

public class FileBrowser extends Application {

    private TreeItem<File> createNode(final File file) {
        return new TreeItem<File>(file) {
            // used to define if the current item is leaf or not .
            private boolean isLeaf;
            // to check if the item visited before or not ,
            // used to track whether the children of the
            // current TreeItem have been loaded for the first time. .
            private boolean isFirstTimeChildren = true;
            //This variable is used to track whether the
            // leaf status of the current TreeItem
            // has been determined for the first time
            private boolean isFirstTimeLeaf = true;

            // Override getChildren Method in tree item .
            // This method returns the children of the current TreeItem

            @Override
            public ObservableList<TreeItem<File>> getChildren() {
                if (isFirstTimeChildren) {
                    isFirstTimeChildren = false;
                    super.getChildren().setAll(buildChildren(this));
                }
                return super.getChildren();
            }

            @Override
            public boolean isLeaf() {
                if (isFirstTimeLeaf) {
                    isFirstTimeLeaf = false;
                    isLeaf = file.isFile();
                }
                return isLeaf;
            }

            private ObservableList<TreeItem<File>> buildChildren(TreeItem<File> treeItem) {
                File file = treeItem.getValue();
                if (file != null && file.isDirectory()) {
                    File[] files = file.listFiles();
                    if (files != null) {
                        ObservableList<TreeItem<File>> children = FXCollections.observableArrayList();
                        for (File childFile : files) {
                            children.add(createNode(childFile));
                        }
                        return children;
                    }
                }
                return FXCollections.emptyObservableList();
            }
        };
    }

    @Override
    public void start(Stage stage) {
        TreeItem<File> root = createNode(new File("/")); // root directory
        TreeView<File> treeView = new TreeView<>(root);
        ListView<File> listView = new ListView<>();

        // Listen to selection changes in the TreeView
        treeView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            // Clear the ListView
            listView.getItems().clear();

            // Check if the new selected item is a directory
            if (newVal.getValue().isDirectory()) {
                // Add all files and directories in the selected directory to the ListView
                Arrays.stream(newVal.getValue().listFiles()).forEach(listView.getItems()::add);
            }
        });

        // Set up a custom cell factory to display file names and icons
        treeView.setCellFactory(tv -> new TreeCell<>() {
            @Override
            protected void updateItem(File item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.getName());
                    Image icon = new Image(item.isDirectory() ? getClass().getResource("/folder.png").toExternalForm() : "/file.png");
                    ImageView imageView = new ImageView(icon);
                    imageView.setFitWidth(25);
                    imageView.setFitHeight(25);
                    setGraphic(imageView);
                }
            }
        });

        listView.setCellFactory(tv -> new ListCell<>() {
            @Override
            protected void updateItem(File item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.getName());
                    Image icon = new Image(item.isDirectory() ? getClass().getResource("/folder.png").toExternalForm() : "/file.png");
                    ImageView imageView = new ImageView(icon);
                    imageView.setFitWidth(25);
                    imageView.setFitHeight(25);
                    setGraphic(imageView);
                }
            }
        });

        // Use BorderPane to place the TreeView and ListView side by side
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(listView);
        borderPane.setCenter(treeView);

        stage.setScene(new Scene(borderPane));
        stage.show();
    }

}

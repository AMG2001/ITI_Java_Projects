import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;

public class FileBrowser extends Application {

    private TreeItem<File> createNode(final File file) {
        return new TreeItem<File>(file) {
            private boolean isLeaf;
            private boolean isFirstTimeChildren = true;
            private boolean isFirstTimeLeaf = true;

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
        treeView.setCellFactory(tv -> new TreeCell<>() {
            @Override
            protected void updateItem(File item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.getName());
                    Image icon = new Image(item.isDirectory() ? getClass().getResource("resources/folder.png").toExternalForm() : "resources/file.png");
                    ImageView imageView = new ImageView(icon);
                    imageView.setFitWidth(25);
                    imageView.setFitHeight(25);
                    setGraphic(imageView);
                }
            }
        });
        stage.setScene(new Scene(treeView));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;

public class FileBrowser extends Application {

    private TreeItem<File> createNode(final File file) {
        return new TreeItem<File>(file) {
            private boolean isLeaf;
            private boolean isFirstTimeChildren = true;
            private boolean isFirstTimeLeaf = true;

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
        treeView.setCellFactory(tv -> new TreeCell<>() {
            @Override
            protected void updateItem(File item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.getName());
                    Image icon = new Image(item.isDirectory() ? getClass().getResource("resources/folder.png").toExternalForm() : "resources/file.png");
                    ImageView imageView = new ImageView(icon);
                    imageView.setFitWidth(25);
                    imageView.setFitHeight(25);
                    setGraphic(imageView);
                }
            }
        });
        stage.setScene(new Scene(treeView));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

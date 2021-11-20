package org.productivityApp.screen;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ScreenController {

    protected AnchorPane anchorPane;
    private void initAnchorPane() {
        anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(800.0);
        anchorPane.setPrefWidth(520.0);
    }

    protected Stage stage;

    public void initialize(Stage stage) {

        this.stage = stage;

        initAnchorPane();

        // set stage parameters
        Scene newScene = new Scene(anchorPane);
        stage.setScene(newScene);
        stage.setResizable(false);
        stage.show();
    }
}

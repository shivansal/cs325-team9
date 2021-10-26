package org.scenebuilder.scenebuilder;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class BasicFXMLController {

    public void initialize() {
        // TODO
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToTodoScene(MouseEvent event) throws IOException {
        switchScene(event, "todoFXML.fxml");
    }

    public void switchToMoneyScene(MouseEvent event) throws IOException {
        switchScene(event, "moneyFXML.fxml");
    }

    public void switchScene(MouseEvent event, String nextScene) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(nextScene));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void centerStage() {
        double width = stage.getScene().getWidth();
        double height = stage.getScene().getHeight();
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
    }
}
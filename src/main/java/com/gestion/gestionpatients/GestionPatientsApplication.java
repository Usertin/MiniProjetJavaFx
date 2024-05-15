package com.gestion.gestionpatients;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class GestionPatientsApplication extends Application {
    private static Stage stage;
    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(GestionPatientsApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        Image img = new Image("C:/Users/Telegrom/Downloads/hospital.png");
        stage.getIcons().add(img);
        primaryStage.setTitle("Hospital Management System!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stage.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch();
    }
}
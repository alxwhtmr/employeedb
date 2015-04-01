package com.github.alxwhtmr.employeedb;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created on 31.03.2015.
 */
public class Runner extends Application {
    private Scene scene;
    private GridPane mainPane;
    private EmployeeDB db;

    private void createAndShowGUI(Stage stage) {
        mainPane = new GridPane();
        mainPane.setId("pane");
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setVgap(10);

        stage.setTitle("Employee DB");
        stage.setWidth(500);
        stage.setHeight(500);
        scene = new Scene(mainPane);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage stage) throws Exception {
        db = new EmployeeDB();
        db.init();
        db.outputDbToConsole();
    }

    public static void main(String[] args) {
        launch();
    }
}





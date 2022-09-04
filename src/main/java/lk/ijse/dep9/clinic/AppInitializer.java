package lk.ijse.dep9.clinic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        AnchorPane container = FXMLLoader.load(this.getClass().getResource("/view/LoginForm.fxml"));
        Scene scene = new Scene(container);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }
}

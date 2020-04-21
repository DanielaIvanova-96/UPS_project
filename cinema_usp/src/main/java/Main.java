import controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{


        try {
            LoginController logInController = new LoginController();
            logInController.setPrimaryStage(primaryStage);
            FXMLLoader loader = new FXMLLoader();

            URL location = Main.class.getResource("login.fxml");
            loader.setLocation(location);
            loader.setController(logInController);
            loader.load();
            primaryStage.setTitle("Corona Log In");
            primaryStage.setScene(new Scene(loader.getRoot()));
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}

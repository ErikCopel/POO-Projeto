package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import java.io.IOException;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("editor_atividade.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 751, 527));
        primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

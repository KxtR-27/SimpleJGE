import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import simpleJGE.Label;
import simpleJGE.Scene;

public class HelloJGE extends Application {
    @Override
    public void start(Stage stage) {
        simpleJGE.Label label = new Label();
        label.setText("Hello world!");
        label.setFGColor(Color.RED);
        label.setCenter(100, 100);

		// You don't have to add package prefix,
		// but it explicitly specifies which "Scene" to use here.
		// Readability is key ✨
        simpleJGE.Scene scene = new Scene(640, 480);
		scene.fillBackground(Color.DARKRED);

        stage.setScene(scene);
        stage.show();
    }
}

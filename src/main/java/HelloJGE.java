import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import simpleJGE.Label;
import simpleJGE.Scene;

public class HelloJGE extends Application {
    @Override
    public void start(Stage stage) {
        Label label = new Label();
        label.setText("Hello world!");
        label.setFGColor(Color.RED);
        label.setCenter(100, 100);

        Scene scene = new Scene(label);

        stage.setScene(scene);
        stage.show();
    }
}

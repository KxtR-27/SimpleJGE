import javafx.application.Application;
import javafx.stage.Stage;
import simpleJGE.Scene;

public class HelloJGE extends Application {
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene();
        stage.setScene(scene);
        stage.show();
    }
}

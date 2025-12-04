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

		// `getResource()` function navigates Gradle structure to the corresponding resources folder
		//noinspection DataFlowIssue - purely for testing purposes. if it returns null, I intend it.
		String image = HelloJGE.class.getResource("simpleJGE/imageview-test.jpg").toString();

		// You don't have to add package prefix,
		// but it explicitly specifies which "Scene" to use here.
		// Readability is key ✨
        simpleJGE.Scene scene = Scene.newBasicScene();
		scene.setImage(image);
		scene.addNodes(label);
		scene.setCaption(stage, "test");

        stage.setScene(scene);
        stage.show();
    }
}

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import simpleJGE.Label;
import simpleJGE.Scene;
import simpleJGE.Sprite;

public class HelloJGE extends Application {
    @Override
    public void start(Stage stage) {
		simpleJGE.Scene scene = Scene.newBasicScene();

        simpleJGE.Label label = Label.newBasicLabel();
        label.setText("Hello world!");
        label.setFGColor(Color.RED);
        label.setCenter(100, 100);
		label.setBgColor(Color.BLUE);
		label.setClearBack(true);

		simpleJGE.Sprite sprite = Sprite.newBasicSprite(scene);

		// `getResource()` function navigates Gradle structure to the corresponding resources folder
		//noinspection DataFlowIssue - purely for testing purposes. if it returns null, I intend it.
		String bgImage = HelloJGE.class.getResource("simpleJGE/imageview-test.jpg").toString();

		// You don't have to add package prefix,
		// but it explicitly specifies which "Scene" to use here.
		// Readability is key ✨
		scene.setImage(bgImage);
		scene.addNodes(label, sprite);
		scene.setCaption(stage, "test");

        stage.setScene(scene.forStage());
        stage.show();

		sprite.setPoint(new Point2D(200, 200));
    }
}

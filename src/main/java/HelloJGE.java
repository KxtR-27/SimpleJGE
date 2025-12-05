import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import simpleJGE.Button;
import simpleJGE.Label;
import simpleJGE.Scene;
import simpleJGE.Sprite;

public class HelloJGE extends Application {
    @Override
    public void start(Stage stage) {
		simpleJGE.Scene scene = Scene.newBasicScene();

        simpleJGE.Label label = Label.newBasicLabel();
        label.setText("I'm a label!");
        label.setFGColor(Color.RED);
        label.setCenter(100, 100);
		label.setBGColor(Color.BLUE);
		label.setClearBack(true);

		simpleJGE.Sprite sprite = Sprite.newBasicSprite(scene);

		simpleJGE.Button button = Button.newBasicButton();
		button.setText("I'm a button!");
		button.setFGColor(Color.WHITE);
		button.setCenter(50, 50);
		button.setBGColor(Color.DARKBLUE);
		button.setClearBack(false);

		// `getResource()` function navigates Gradle structure to the corresponding resources folder
		//noinspection DataFlowIssue - purely for testing purposes. if it returns null, I intend it.
		String bgImage = HelloJGE.class.getResource("simpleJGE/imageview-test.jpg").toString();

		// You don't have to add package prefix,
		// but it explicitly specifies which "Scene" to use here.
		// Readability is key ✨
		scene.setImage(bgImage);
		scene.addNodes(label, sprite, button);
		scene.setCaption(stage, "test");

        stage.setScene(scene.forStage());
        stage.show();

		sprite.setPoint(new Point2D(200, 200));
    }
}

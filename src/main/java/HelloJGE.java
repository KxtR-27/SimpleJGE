import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import simpleJGE.Button;
import simpleJGE.Label;
import simpleJGE.Scene;
import simpleJGE.Sprite;

import java.util.Objects;

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


		simpleJGE.Sprite imageSprite = Sprite.newBasicSprite(scene);
		imageSprite.setImage(helpGetResource("catppuccin.png"));
		imageSprite.setSize(10, 10);
		imageSprite.setPoint(new Point2D(300, 300));

		simpleJGE.Sprite sprite = Sprite.newBasicSprite(scene);
		sprite.setOnClick(event -> imageSprite.setSize(50, 50));

		simpleJGE.Button button = Button.newBasicButton();
		button.setText("I'm a button!");
		button.setFGColor(Color.WHITE);
		button.setCenter(50, 50);
		button.setBGColor(Color.DARKBLUE);
		button.setClearBack(false);

		// `getResource()` function navigates Gradle structure to the corresponding resources folder
		String bgImage = helpGetResource("imageview-test.jpg");

		// You don't have to add package prefix,
		// but it explicitly specifies which "Scene" to use here.
		// Readability is key ✨
		scene.setImage(bgImage);
		scene.addNodes(label, sprite, imageSprite, button);
		scene.setCaption(stage, "test");

        stage.setScene(scene.forStage());
        stage.show();

		sprite.setPoint(new Point2D(200, 200));
    }

	private static String helpGetResource(String filename) {
		return Objects.requireNonNull(HelloJGE.class.getResource(filename)).toString();
	}
}

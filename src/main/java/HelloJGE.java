import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import simpleJGE.*;

import java.util.List;
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
		label.setFont("Times New Roman");

		simpleJGE.Sprite imageSprite = Sprite.newBasicSprite(scene);
		imageSprite.setImage(helpGetResource("catppuccin.png"));
		imageSprite.setSize(10, 10);
		imageSprite.setPoint(new Point2D(300, 300));

		simpleJGE.Sprite sprite = Sprite.newBasicSprite(scene);
		sprite.setOnClick(event -> imageSprite.setSize(50, 50));

		simpleJGE.TxtInput input = TxtInput.newBasicTxtInput();
		input.setFGColor(Color.RED);
		input.setBGColor(Color.BLACK);
		input.setOnAction(event -> System.out.printf("%s%n", input.getValue()));
		input.setCenter(300, 300);
		input.setFont("Times New Roman");

		simpleJGE.Scroller scroller = Scroller.newBasicScroller();
//		scroller.setStep(0.5);
		scroller.setValue(-5.0);
		scroller.setFGColor(Color.CORNFLOWERBLUE);
		scroller.setBGColor(Color.CHARTREUSE);
		scroller.setFont("Times New Roman");

		simpleJGE.MultiLabel multiLabel = MultiLabel.newBasicMultiLabel(List.of(
				"I have many lines",
				"and I must share them all",
				"something, something...uhh..",
				"yeah that's all I got."
		));
		multiLabel.addLines("actually wait I forgot this one");
		multiLabel.setCenter(150, 150);
		multiLabel.setSize(18);
		multiLabel.setClearBack(false);
		multiLabel.setBGColor(Color.CADETBLUE);

		simpleJGE.Button button = Button.newBasicButton();
		button.setText("I'm a button!");
		button.setFGColor(Color.WHITE);
		button.setCenter(50, 50);
		button.setBGColor(Color.DARKBLUE);
		button.setClearBack(false);
		button.setOnClick(event -> {
			input.setClearBack(true);
			scroller.setClearBack(true);
			multiLabel.setClearBack(true);
		});
		button.setFont("Times New Roman");

		// `getResource()` function navigates Gradle structure to the corresponding resources folder
		String bgImage = helpGetResource("imageview-test.jpg");

		// You don't have to add package prefix,
		// but it explicitly specifies which "Scene" to use here.
		// Readability is key ✨
		scene.setImage(bgImage);
		scene.addNodes(label, sprite, imageSprite, button, input, scroller, multiLabel);
		scene.setCaption(stage, "test");

        stage.setScene(scene.forStage());
        stage.show();

		sprite.setPoint(new Point2D(200, 200));
    }

	private static String helpGetResource(String filename) {
		return Objects.requireNonNull(HelloJGE.class.getResource(filename)).toString();
	}
}

package simpleJGE;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

@SuppressWarnings("unused")
// usage depends upon the game the user makes

public class Scene extends javafx.scene.Scene {
    @SuppressWarnings("FieldCanBeLocal")
    // Further implementation will use the window
    private final Pane screen;
	private final Background background;

    public Scene(double width, double height) {
        super(new Pane(), width, height);
        screen = (Pane) this.getRoot();

		if (screen.getBackground() == null)
			screen.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

		background = screen.getBackground();
    }

    public void fillBackground(Color color) {
		screen.setBackground(new Background(new BackgroundFill(color, null, null)));
    }
}
package simpleJGE;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

@SuppressWarnings("unused")
// usage depends upon the game the user makes
public class Scene extends javafx.scene.Scene {
    @SuppressWarnings("FieldCanBeLocal")
    // Further implementation will use the window
    private final Pane window = new Pane();

    public Scene(Parent parent, double v, double v1) {
        super(parent, v, v1);
    }

    public Scene(Node... nodes) {
        super(new Pane(/* arbitrary */), 640, 480);
        this.setRoot(window);

        window.getChildren().addAll(nodes);
    }
}
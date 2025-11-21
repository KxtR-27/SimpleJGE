package simpleJGE;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class Scene extends javafx.scene.Scene {
    public Scene(Parent parent, double v, double v1) {
        super(parent, v, v1);
    }

    public Scene() {
        super(new Pane(), 640, 480);
    }
}
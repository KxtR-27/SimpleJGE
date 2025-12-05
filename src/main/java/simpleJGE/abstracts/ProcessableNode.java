package simpleJGE.abstracts;

@SuppressWarnings("unused")
// This warning is incorrect.
// at time of writing, this has 10 implementations.

public abstract class ProcessableNode implements Processable {
	// intentionally package-private
	public abstract javafx.scene.Node fxNode();

	public void setCenter(double x, double y) {
		fxNode().relocate(x, y);
	}

	public void hide() {
		fxNode().setVisible(false);
	}

	public void show() {
		fxNode().setVisible(true);
	}
}

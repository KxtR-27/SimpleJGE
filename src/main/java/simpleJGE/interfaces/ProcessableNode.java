package simpleJGE.interfaces;

@SuppressWarnings("unused")
// This warning is incorrect.
// at time of writing, this has 8 implementations.

public interface ProcessableNode extends Processable {
	// intentionally package-private
	javafx.scene.Node fxNode();
}

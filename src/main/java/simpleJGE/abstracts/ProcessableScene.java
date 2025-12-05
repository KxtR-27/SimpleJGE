package simpleJGE.abstracts;

@SuppressWarnings("unused")
// This warning is incorrect.
// at time of writing, this has two implementations.

public interface ProcessableScene extends Processable {
	// intentionally package-private
	javafx.scene.Scene fxScene();
}

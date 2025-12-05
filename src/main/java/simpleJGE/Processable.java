package simpleJGE;

public interface Processable {
	void update();
	void process();
}

@SuppressWarnings("unused")
// This warning is incorrect.
// at time of writing, this has two implementations.
interface ProcessableNode extends Processable {
	// intentionally package-private
	javafx.scene.Node fxNode();
}

@SuppressWarnings("unused")
// This warning is incorrect.
// at time of writing, this has one implementation.
interface ProcessableScene extends Processable {
	// intentionally package-private
	javafx.scene.Scene fxScene();
}
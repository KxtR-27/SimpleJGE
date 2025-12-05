package simpleJGE.interfaces;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

@SuppressWarnings("unused")
// This warning is incorrect.
// at time of writing, this has four implementations.

public interface ClickableNode extends ProcessableNode {
	void setOnClick(EventHandler<? super MouseEvent> eventHandler);
}

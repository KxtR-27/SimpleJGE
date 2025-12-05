package simpleJGE.abstracts;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

@SuppressWarnings("unused")
// This warning is incorrect.
// at time of writing, this has four implementations.

public interface ClickableNode {
	void setOnClick(EventHandler<? super MouseEvent> eventHandler);
}

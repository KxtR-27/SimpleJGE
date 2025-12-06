package simpleJGE;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import simpleJGE.abstracts.ClickableNode;
import simpleJGE.abstracts.LabeledFXNode;

@SuppressWarnings("unused")
// usage depends upon user implementation

public class Button extends LabeledFXNode implements ClickableNode {
	public Button(String fontName) {
		super(fontName, new javafx.scene.control.Button());
	}

	public Button() {
		super(new javafx.scene.control.Button());
	}

	@Override
	public void setOnClick(EventHandler<? super MouseEvent> eventHandler) {
		fxNode().setOnMouseClicked(eventHandler);
	}
}
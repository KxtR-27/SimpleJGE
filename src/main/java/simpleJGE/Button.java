package simpleJGE;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import simpleJGE.interfaces.ClickableNode;

@SuppressWarnings("unused")
// usage depends upon user implementation

public abstract class Button extends LabeledFXNode implements ClickableNode {
	/**
	 * @return an anonymous {@link Button} subclass that has
	 * no implementation for {@link #process()}.
	 * Intended only for basic developmentary tests.
	 */
	public static Button newBasicButton() {
		Button button = new Button() {
			@Override
			public void process() {}
		};
		button.setOnClick(keyEvent -> System.out.printf("I, a button, was clicked!%n"));
		return button;
	}

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
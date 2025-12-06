package simpleJGE;

import simpleJGE.abstracts.ClickableNode;
import simpleJGE.abstracts.LabeledFXNode;

@SuppressWarnings("unused")
// usage depends upon user implementation

public class Button extends LabeledFXNode implements ClickableNode {
	public Button(String fontName) {
		super(fontName, new javafx.scene.control.Button());
		clickableSetup();
	}

	public Button() {
		super(new javafx.scene.control.Button());
		clickableSetup();
	}

	private void clickableSetup() {
		this.fxNode().setOnMouseClicked(ignored -> onClick());
	}

	public void onClick() {}

	@Override
	public void process() {
		super.process();
	}
}
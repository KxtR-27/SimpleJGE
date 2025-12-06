package simpleJGE;

import simpleJGE.abstracts.LabeledFXNode;
import simpleJGE.abstracts.Valuable;

@SuppressWarnings("unused")
// usage depends upon user implementation

public class Label extends LabeledFXNode implements Valuable<String> {
	public Label(String fontName) {
		super(fontName, new javafx.scene.control.Label());
	}

	public Label() {
		super(new javafx.scene.control.Label());
	}

	public String getValue() {
		return fxNode().getText();
	}

	public void setValue(String value) {
		fxNode().setText(value);
	}

	public void process() {}
}

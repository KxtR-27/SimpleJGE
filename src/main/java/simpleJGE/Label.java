package simpleJGE;

import simpleJGE.abstracts.LabeledFXNode;
import simpleJGE.abstracts.Valuable;

@SuppressWarnings("unused")
// usage depends upon user implementation

public abstract class Label extends LabeledFXNode implements Valuable<String> {
	/**
	 * @return an anonymous {@link Label} subclass that has
	 * no implementation for {@link #process()}.
	 * Intended only for basic developmentary tests.
	 */
	public static Label newBasicLabel() {
		return new Label() {
			@Override
			public void process() {}
		};
	}

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

	public abstract void process();
}

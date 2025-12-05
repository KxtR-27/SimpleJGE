package simpleJGE;

@SuppressWarnings("unused")
// usage depends upon user implementation

public abstract class Label extends LabeledFXNode {
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

	public abstract void process();
}

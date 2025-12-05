package simpleJGE.abstracts;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

@SuppressWarnings("unused")
// usage depends upon user implementation

public abstract class LabeledFXNode extends ProcessableNode implements Labeled {
	private final javafx.scene.control.Labeled fxLabeled;

	private Color bgColor = Color.WHITE;
	protected boolean clearBack = false;

	public LabeledFXNode(String fontName, javafx.scene.control.Labeled fxLabeled) {
		this.fxLabeled = fxLabeled;
		fxLabeled.setFont(new Font(fontName, 16));
	}

	public LabeledFXNode(javafx.scene.control.Labeled fxLabeled) {
		this.fxLabeled = fxLabeled;
	}

	public javafx.scene.control.Labeled fxNode() {
		return this.fxLabeled;
	}

	public void setFont(String fontName) {
		fxLabeled.setFont(new Font(fontName, fxLabeled.getFont().getSize()));
	}

	public void setText(String text) {
		fxLabeled.setText(text);
	}

	public void setFGColor(Color fgColor) {
		fxLabeled.setTextFill(fgColor);
	}

	public void setBGColor(Color bgColor) {
		this.bgColor = bgColor;
		updateBackground();
	}

	public void setSize(double fontSize) {
		String currentFamily = fxLabeled.getFont().getFamily();
		fxLabeled.setFont(new Font(currentFamily, fontSize));
	}

	public void setClearBack(boolean clearBack) {
		this.clearBack = clearBack;
		this.updateBackground();
	}

	public void updateBackground() {
		fxLabeled.setBackground(new Background(new BackgroundFill(
				clearBack ? Color.TRANSPARENT : bgColor,
				null, null
		)));
	}

	public void update() {
		this.process();
		updateBackground();
	}

	// checkEvents() is redundant, as JavaFX actively listens for events

	public abstract void process();
}

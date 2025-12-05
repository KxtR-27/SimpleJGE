package simpleJGE;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import simpleJGE.interfaces.ProcessableNode;

@SuppressWarnings("unused")
// usage depends upon user implementation

abstract class Labeled implements ProcessableNode {
	private final javafx.scene.control.Labeled fxLabeled;

	private Color bgColor = Color.WHITE;
	private boolean clearBack = false;

	Labeled(String fontName, javafx.scene.control.Labeled fxLabeled) {
		this.fxLabeled = fxLabeled;
		fxLabeled.setFont(new Font(fontName, 16));
	}

	Labeled(javafx.scene.control.Labeled fxLabeled) {
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

	public void setCenter(double x, double y) {
		fxLabeled.relocate(x, y);
	}

	public void setSize(double fontSize) {
		String currentFamily = fxLabeled.getFont().getFamily();
		fxLabeled.setFont(new Font(currentFamily, fontSize));
	}

	public void setClearBack(boolean clearBack) {
		this.clearBack = clearBack;
		this.updateBackground();
	}

	private void updateBackground() {
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

	public void hide() {
		fxLabeled.setVisible(false);
	}

	public void show() {
		fxLabeled.setVisible(true);
	}
}

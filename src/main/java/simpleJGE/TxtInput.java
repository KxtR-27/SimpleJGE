package simpleJGE;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import simpleJGE.interfaces.Labeled;

/**
 * Since {@link javafx.scene.control.TextField}s are not {@link javafx.scene.control.Labeled},
 * I can only hope to implement the interface.
 */

@SuppressWarnings("unused")
// usage depends upon user implementation

public class TxtInput implements Labeled {
	private final TextField fxTextField = new TextField();

	private Color fgColor = Color.BLACK;
	private Color bgColor = Color.WHITE;
	private boolean clearBack = false;

	public String getText() {
		return fxTextField.getText();
	}

	@Override
	public void setFont(String fontName) {
		fxTextField.setFont(new Font(fontName, 16));
	}

	@Override
	public void setText(String text) {
		fxTextField.setPromptText(text);
	}

	@Override
	public void setFGColor(Color fgColor) {
		this.fgColor = fgColor;
		update();
	}

	@Override
	public void setBGColor(Color bgColor) {
		this.bgColor = bgColor;
		update();
	}

	@Override
	public void setCenter(double x, double y) {
		fxTextField.relocate(x, y);
	}

	@Override
	public void setSize(double fontSize) {
		fxTextField.setFont(new Font(fxTextField.getFont().getFamily(), fontSize));
	}

	@Override
	public void setClearBack(boolean clearBack) {
		this.clearBack = clearBack;
		update();
	}

	@Override
	public void updateBackground() {
		update();
	}

	@Override
	public Node fxNode() {
		return fxTextField;
	}

	@Override
	public void hide() {
		fxTextField.setVisible(false);
	}

	@Override
	public void show() {
		fxTextField.setVisible(true);
	}

	@Override
	public void update() {
		String bgCSS = String.format("-fx-background-color: %s;", clearBack ? "transparent" : extractColor(bgColor));
		String fgCSS = String.format("-fx-text-fill: %s;", extractColor(fgColor));

		fxTextField.setStyle(String.format("%s %s", bgCSS, fgCSS));
	}

	private String extractColor(Color color) {
		return String.format("#%s", color.toString().substring(2, 8));
	}

	@Override
	public void process() {}
}

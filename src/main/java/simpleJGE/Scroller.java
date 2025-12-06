package simpleJGE;

import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.paint.Color;
import simpleJGE.abstracts.Labeled;
import simpleJGE.abstracts.ProcessableNode;
import simpleJGE.abstracts.StyledFXNode;
import simpleJGE.abstracts.Valuable;

@SuppressWarnings("unused")
// usage depends upon user implementation

public class Scroller extends ProcessableNode implements Labeled, Valuable<Double> {
	private final javafx.scene.control.Spinner<Double> fxSpinner;
	private final ScrollerEditor editor;

	private double minValue = -100.0;
	private double maxValue = 100.0;
	private double step = 0.01;

	public Scroller(double initialValue) {
		fxSpinner = new Spinner<>();
		editor = new ScrollerEditor(fxSpinner.getEditor());
		updateValueFactory(initialValue);
	}

	public Scroller() {
		this(0);
	}


	public Double getValue() {
		return fxSpinner.getValue();
	}

	public void setValue(Double value) {
		fxSpinner.getValueFactory().setValue(value);
	}

	public void setMinValue(double minValue) {
		this.minValue = minValue;
		updateValueFactory(getValue());
	}

	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
		updateValueFactory(getValue());
	}

	public void setStep(double step) {
		this.step = step;
		updateValueFactory(getValue());
	}

	private void updateValueFactory(double value) {
		fxSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(
				minValue, maxValue, value, step
		));
	}

	public void process() {}

	@Override
	public void setFont(String fontName) {
		editor.setFont(fontName);
	}

	@Override
	public void setFGColor(Color fgColor) {
		editor.setFGColor(fgColor);
	}

	@Override
	public void setBGColor(Color bgColor) {
		editor.setBGColor(bgColor);
	}

	@Override
	public void setSize(double fontSize) {
		editor.setSize(fontSize);
	}

	@Override
	public void setClearBack(boolean clearBack) {
		editor.setClearBack(clearBack);
	}

	@Override
	public void updateBackground() {

	}

	public Node fxNode() {
		return fxSpinner;
	}

	private static class ScrollerEditor extends StyledFXNode {
		public ScrollerEditor(Node node) {
			super(node);
		}

		@Override
		public void process() {}

		@Override
		public void update() {
			super.update();
			if (clearBack) {
				String border = "-fx-border-width: 0;";
				fxNode().setStyle(String.format("%s%s", fxNode().getStyle(), border));
			}
		}
	}
}

package simpleJGE.abstracts;

import javafx.scene.Node;
import javafx.scene.paint.Color;

public abstract class StyledFXNode extends ProcessableNode implements Labeled {
	private final Node fxNode;

	private String fontName = "";
	private String fontSize = "";
	private String fgColor = "";
	private String bgColor = "";
	protected boolean clearBack = false;

	public StyledFXNode(Node fxNode) {
		this.fxNode = fxNode;
	}

	@Override
	public void setFont(String fontName) {
		this.fontName = String.format("-fx-font-family: %s;", fontName);
		update();
	}

	@Override
	public void setFGColor(Color fgColor) {
		this.fgColor = String.format("-fx-text-fill: #%s;", fgColor.toString().substring(2, 8));
		update();
	}

	@Override
	public void setBGColor(Color bgColor) {
		this.bgColor = String.format("-fx-background-color: #%s", bgColor.toString().substring(2, 8));
		update();
	}

	@Override
	public void setSize(double fontSize) {
		this.fontSize = String.format("fx-font-size: %fpt;", fontSize);
		update();
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
		return fxNode;
	}

	public void update() {
		String actualBGColor = clearBack ? "-fx-background-color: none;" : bgColor;
		fxNode.setStyle(String.format("%s%s%s%s", fontName, fontSize, fgColor, actualBGColor));
	}

	@Override
	public void process() {
		String actualBGColor = clearBack ? "-fx-background-color: none;" : bgColor;
		fxNode.setStyle(String.format("%s%s%s%s", fontName, fontSize, fgColor, actualBGColor));
	}
}

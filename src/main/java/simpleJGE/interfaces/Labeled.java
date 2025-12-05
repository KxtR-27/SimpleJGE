package simpleJGE.interfaces;

import javafx.scene.paint.Color;

@SuppressWarnings("unused")
// This warning is incorrect.
// at time of writing, this has 6 implementations.

public interface Labeled extends ProcessableNode {
	void setFont(String fontName);

	void setText(String text);

	void setFGColor(Color fgColor);

	void setBGColor(Color bgColor);

	void setCenter(double x, double y);

	void setSize(double fontSize);

	void setClearBack(boolean clearBack);

	void updateBackground();
}

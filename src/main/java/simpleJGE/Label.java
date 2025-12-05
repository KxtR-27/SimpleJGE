package simpleJGE;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

@SuppressWarnings("unused")
// usage depends upon user implementation

public class Label {
    // pygame           <--- simpleGE  ---> javafx

    // pygame.font.Font <---   font    ---> javafx.scene.text.Font
    // str              <---   text    ---> StringProperty
    // pygame.color     <---  fgColor  ---> textFill (javafx.scene.paint.Paint or Color)
    // pygame.color     <---  bgColor  ---> textFill (Background > BackgroundFill > Paint or Color)
    // tuple(x, y)      <---  center   ---> ...
    // boolean          <--- clearBack ---> boolean

    // font = this.font

	// intentionally package-private
	final javafx.scene.control.Label fxLabel;

	private Color bgColor = Color.WHITE;
    private boolean clearBack = false;

    public Label(String fontName) {
        this();
        fxLabel.setFont(new Font(fontName, 20));
    }

    public Label() {
        fxLabel = new javafx.scene.control.Label();
    }

	public void setText(String text) {
		fxLabel.setText(text);
	}

    public void setFGColor(Color fgColor) {
        fxLabel.setTextFill(fgColor);
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
		updateBackground();
    }

    public void setCenter(double x, double y) {
        fxLabel.relocate(x, y);
    }

    public void setSize(double fontSize) {
		String currentFamily = fxLabel.getFont().getFamily();
        fxLabel.setFont(new Font(currentFamily, fontSize));
    }

    public void setClearBack(boolean clearBack) {
        this.clearBack = clearBack;
        this.updateBackground();
    }

    private void updateBackground() {
        fxLabel.setBackground(new Background(new BackgroundFill(
                clearBack ? Color.TRANSPARENT : bgColor,
                null, null
        )));
    }
}

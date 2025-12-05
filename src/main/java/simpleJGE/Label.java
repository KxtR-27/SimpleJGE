package simpleJGE;

import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

@SuppressWarnings("unused")
// usage depends upon user implementation

public abstract class Label implements ProcessableNode {
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
        this();
        fxLabel.setFont(new Font(fontName, 20));
    }

    public Label() {
        fxLabel = new javafx.scene.control.Label();
    }

	@Override
	public Node fxNode() {
		return this.fxLabel;
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

	public void update() {
		this.process();
		updateBackground();
	}

	// checkEvents() is redundant, as JavaFX actively listens for events

	public abstract void process();

	public void hide() {
		fxLabel.setVisible(false);
	}

	public void show() {
		fxLabel.setVisible(true);
	}
}

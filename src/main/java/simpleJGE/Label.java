package simpleJGE;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

@SuppressWarnings("unused")
// usage depends upon the game the user makes
public class Label extends javafx.scene.control.Label {
    // pygame           <--- simpleGE  ---> javafx

    // pygame.font.Font <---   font    ---> javafx.scene.text.Font
    // str              <---   text    ---> StringProperty
    // pygame.color     <---  fgColor  ---> textFill (javafx.scene.paint.Paint or Color)
    // pygame.color     <---  bgColor  ---> textFill (Background > BackgroundFill > Paint or Color)
    // tuple(x, y)      <---  center   ---> ...
    // boolean          <--- clearBack ---> boolean

    // font = this.font

    private Color bgColor = Color.WHITE;
    private boolean clearBack = false;

    public Label(String fontName) {
        super();
        this.setFont(new Font(fontName, 20));
    }

    public Label() {
        super();
    }

    // setFont() native to JavaFX

    // setText() native to JavaFX

    public void setFGColor(Color fgColor) {
        this.setTextFill(fgColor);
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
        this.clearBack = false;
        this.updateBackground();
    }

    public void setCenter(double x, double y) {
        this.relocate(x, y);
    }

    public void setSize(double width, double height) {
        this.setWidth(width);
        this.setHeight(height);
    }

    public void setClearBack(boolean clearBack) {
        this.clearBack = clearBack;
        this.updateBackground();
    }

    private void updateBackground() {
        this.setBackground(new Background(new BackgroundFill(
                clearBack ? Color.TRANSPARENT : bgColor,
                null, null
        )));
    }
}

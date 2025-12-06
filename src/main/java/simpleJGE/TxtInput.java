package simpleJGE;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import simpleJGE.abstracts.StyledFXNode;
import simpleJGE.abstracts.Valuable;

/**
 * Since {@link javafx.scene.control.TextField}s are not {@link javafx.scene.control.Labeled},
 * I can only hope to implement the interface.
 */

@SuppressWarnings("unused")
// usage depends upon user implementation

public class TxtInput extends StyledFXNode implements Valuable<String> {
	private final TextField fxTextField = (TextField) fxNode();

	public static TxtInput newBasicTxtInput() {
		return new TxtInput() {
			@Override
			public void process() {}
		};
	}

	public TxtInput() {
		super(new TextField());
	}

	public String getValue() {
		return fxTextField.getText();
	}

	public void setValue(String text) {
		fxTextField.setText(text);
	}

	public void setPrompt(String prompt) {
		fxTextField.setPromptText(prompt);
	}

	public void setOnAction(EventHandler<ActionEvent> eventHandler) {
		fxTextField.setOnAction(eventHandler);
	}

	@Override
	public void setFont(String fontName) {
		fxTextField.setFont(new Font(fontName, fxTextField.getFont().getSize()));
	}
}

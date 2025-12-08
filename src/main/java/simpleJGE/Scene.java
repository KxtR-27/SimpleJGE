package simpleJGE;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import simpleJGE.abstracts.Processable;
import simpleJGE.abstracts.ProcessableNode;
import simpleJGE.abstracts.ProcessableScene;

import java.util.*;

@SuppressWarnings("unused")
// usage depends upon user implementation

public class Scene implements ProcessableScene {
	private static final int REFRESH_RATE = 30;

	/**
	 * The scene passed to the JavaFX {@link Stage}
	 * intentionally package-private
	 */
	final javafx.scene.Scene fxScene;

	/** Represents and is bound to the fxScene's root node. */
	private final Pane screen;

	/** List of all {@link Processable}s/{@link ProcessableNode}s */
	private final List<Processable> elements;

	/** A private inner class object that handles pressed keys for the Scene. */
	private final KeyManager keyManager;

	@Override
	public javafx.scene.Scene fxScene() {
		return fxScene;
	}

	/**
	 * @param width The width of the application window in pixels
	 * @param height The height of the application window in pixels
	 */
	public Scene(double width, double height) {
		fxScene = new javafx.scene.Scene(new Pane(), width, height);
		screen = (Pane) fxScene.getRoot();
		fillBackground(Color.WHITE);

		elements = new ArrayList<>();
		keyManager = new KeyManager(this.fxScene);

		Timeline processTimeline = new Timeline(new KeyFrame(
				Duration.seconds(1.0 / REFRESH_RATE),
				ignored -> this.process()
		));
		processTimeline.setCycleCount(Timeline.INDEFINITE);
		processTimeline.play();
	}

	/**
	 * Constructs a Scene with a default width of 640 pixels
	 * and a default height of 480 pixels
	 */
	public Scene() {
		this(640, 480);
	}

	public javafx.scene.Scene forStage() {
		return fxScene;
	}

	public double getWidth() {
		return fxScene.getWidth();
	}

	public double getHeight() {
		return fxScene.getHeight();
	}

	/**
	 * Displays a solid {@link Color} across the entire background.
	 *
	 * @param color The color of the background
	 */
	public void fillBackground(Color color) {
		screen.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
	}

	/**
	 * Displays an {@link ImageView} across the background.
	 *
	 * @param imagePathName The qualified filename of the image
	 * @param preserveRatio If true, image will resize to fit the window automatically
	 */
	public void setImage(String imagePathName, boolean preserveRatio) {
		ImageView image = new ImageView(imagePathName);

		screen.getChildren().addFirst(image);
		image.setFitWidth(screen.getWidth());
		image.setPreserveRatio(preserveRatio);
	}

	/**
	 * Displays an {@link ImageView} across the background.
	 * The image will resize to fit the window automatically.
	 *
	 * @param imagePathName The qualified filename of the image
	 */
	public void setImage(String imagePathName) {
		setImage(imagePathName, true);
	}

	/**
	 * Adds passed elements to the scene.
	 *
	 * @param elements SimpleJGE {@link Processable}(s) to add to the scene.
	 * {@link ProcessableNode}s added in this fashion also add their
	 * {@link ProcessableNode#fxNode()} to the screen.
	 */
	public void addElements(Processable... elements) {
		List.of(elements).forEach(element -> {
			this.elements.add(element);

			if (element instanceof ProcessableNode)
				screen.getChildren().add(((ProcessableNode) element).fxNode());
		});
	}

	/**
	 * Adds passed nodes to the scene.
	 *
	 * @param fxNodes JavaFX {@link Node}(s) to add to the scene
	 */
	public void addNodes(javafx.scene.Node... fxNodes) {
		this.screen.getChildren().addAll(List.of(fxNodes));
	}

	/**
	 * Adds a single element to the scene.
	 *
	 * @param element SimpleJGE {@link ProcessableNode} to add to the scene.
	 * 	 * {@link ProcessableNode}s added in this fashion also add their
	 * 	 * {@link ProcessableNode#fxNode()} to the screen.
	 */
	public void addElement(Processable element) {
		addElements(element);
	}

	/**
	 * Adds a single node to the scene.
	 *
	 * @param fxNode SimpleJGE {@link Node} to add to the scene
	 */
	public void addNode(javafx.scene.Node fxNode) {
		addNodes(fxNode);
	}

	/**
	 * Adds a group of nodes to the scene.
	 *
	 * @param fxGroup JavaFX {@link Group} of JavaFX {@link Node}(s) to add to the scene.
	 */
	public void addGroup(javafx.scene.Group fxGroup) {
		this.screen.getChildren().add(fxGroup);
	}

	public void process() {
		for (Processable element : elements)
			element.process();
	}

	/**
	 * Sets the application window title
	 *
	 * @param stage The {@link Stage of your application}
	 * @param title The string for the application window title
	 */
	// To consolidate most of the complexities to a place unseen to the user,
	// simply passing a stage *in* means they need not worry about what a stage *is*
	public void setCaption(Stage stage, String title) {
		stage.setTitle(title);
	}

	/**
	 * Checks whether a keyboard key is held down by its corresponding {@link KeyCode}
	 *
	 * @param keyCode identifier for the queried key
	 *
	 * @return true if the key is being held down;
	 * false if the key is not pressed
	 */
	public boolean isKeyPressed(KeyCode keyCode) {
		return keyManager.isKeyPressed(keyCode);
	}

	/**
	 * Injects keyboard listeners into its parent {@link Scene}.
	 * In doing so, it provides the ability for a key to be
	 * held continuously without spamming inputs.
	 */
	private static class KeyManager {
		/**
		 * The Set of keys that are currently being held down
		 */
		private final Set<KeyCode> pressedKeys;

		/**
		 * @param parent The parent into which to inject the key listeners
		 */
		private KeyManager(javafx.scene.Scene parent) {
			pressedKeys = new HashSet<>();
			parent.setOnKeyPressed(keyEvent -> {
				if (pressedKeys.contains(keyEvent.getCode()))
					return;

				pressedKeys.add(keyEvent.getCode());
				System.out.printf("Key '%s' pressed.%n", keyEvent.getCode());
			});
			parent.setOnKeyReleased(keyEvent -> {
				pressedKeys.remove(keyEvent.getCode());
				System.out.printf("Key '%s' released.%n", keyEvent.getCode());
			});
		}

		/**
		 * Checks whether a keyboard key is held down by its corresponding {@link KeyCode}
		 *
		 * @param keyCode identifier for the queried key
		 *
		 * @return true if the key is currently held down;
		 * false if the key is not pressed
		 */
		private boolean isKeyPressed(KeyCode keyCode) {
			return pressedKeys.contains(keyCode);
		}
	}
}

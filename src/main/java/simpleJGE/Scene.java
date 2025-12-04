package simpleJGE;

import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unused")
// usage depends upon the game the user makes

public abstract class Scene extends javafx.scene.Scene {
    private final Pane screen;
	private final List<Node> nodes;
	private final KeyManager keyManager;

	/**
	 * @return an anonymous {@link Scene} subclass that has
	 * no implementation for
	 * <ul>
	 * 	    <li>{@link #doEvents(Event)}</li>
	 * 		<li>{@link #processEvent(Event)}</li>
	 * 		<li>{@link #update()}</li>
	 * 		<li>{@link #process()}</li>
	 * </ul>
	 * <p>Intended only for basic developmentary tests.</p>
	 */
	public static Scene newBasicScene() {
		return new Scene() {
			@Override
			public void doEvents(Event event) {}

			@Override
			public void processEvent(Event event) {}

			@Override
			public void update() {}

			@Override
			public void process() {}
		};
	}

    public Scene(double width, double height) {
        super(new Pane(), width, height);

        screen = (Pane) this.getRoot();
		fillBackground(Color.WHITE);

		nodes = screen.getChildren();

		keyManager = new KeyManager(this);
    }

	public Scene() {
		this(640, 480);
	}


    public void fillBackground(Color color) {
		screen.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
    }

	public void setImage(String imagePathName, boolean preserveRatio) {
		ImageView image = new ImageView(imagePathName);

		screen.getChildren().addFirst(image);
		image.setFitWidth(screen.getWidth());
		image.setPreserveRatio(preserveRatio);
	}

	public void setImage(String imagePathName) {
		setImage(imagePathName, true);
	}


	public void addNodes(Node... nodes) {
		this.nodes.addAll(List.of(nodes));
	}

	public void addNode(Node node) {
		addNodes(node);
	}

	public void addGroup(Group group) {
		addNode(group);
	}


	public abstract void doEvents(Event event);

	public abstract void processEvent(Event event);

	public abstract void update();

	public abstract void process();


	// To consolidate most of the complexities to a place unseen to the user,
	// simply passing a stage *in* means they need not worry about what a stage *is*
	public void setCaption(Stage stage, String title) {
		stage.setTitle(title);
	}

	public boolean isKeyPressed(KeyCode keyCode) {
		return keyManager.isKeyPressed(keyCode);
	}


	private static class KeyManager {
		private final Set<KeyCode> pressedKeys;

		private KeyManager(Scene parent) {
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

		private boolean isKeyPressed(KeyCode keyCode) {
			return pressedKeys.contains(keyCode);
		}
	}
}

package simpleJGE;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import simpleJGE.abstracts.ClickableNode;
import simpleJGE.abstracts.ProcessableNode;

@SuppressWarnings("unused")
// usage depends upon user implementation

public abstract class Sprite extends ProcessableNode implements ClickableNode {
	// intentionally package-private
	private final Pane fxPane;
	private final Scene scene;

	public Node fxNode() {
		return fxPane;
	}

	public double getX() {return fxPane.getLayoutX();}

	public void setX(double x) {fxPane.setLayoutX(x);}

	public double getY() {return fxPane.getLayoutY();}

	public void setY(double y) {fxPane.setLayoutY(y);}

	public Point2D getPoint() {return new Point2D(getX(), getY());}

	public void setPoint(Point2D point) {fxPane.relocate(point.getX(), point.getY());}

	protected double dx = 0.0, dy = 0.0;    // 2D velocity direction
	protected double speed = 0.0;            // 2D velocity speed
	protected double moveAngle = 0.0;        // used for movement direction
	protected double imageAngle = 0.0;        // used for pane rotation

	protected BoundaryAction boundaryAction = BoundaryAction.WRAP;
	private Point2D previousPoint;

	/**
	 * @return an anonymous {@link Sprite} subclass that has
	 * no implementation for {@link #process()}
	 * and a print statement for {@link #setOnClick(EventHandler)}.
	 * Intended only for basic developmentary tests.
	 */
	public static Sprite newBasicSprite(Scene scene) {
		Sprite sprite = new Sprite(scene) {
			@Override
			public void process() {}
		};
		sprite.setOnClick(keyEvent -> System.out.printf("I, a sprite, was clicked!%n"));
		return sprite;
	}

	Sprite(Scene scene) {
		super();
		this.fxPane = new Pane();
		this.fxPane.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
		this.setSize(25, 25);
		this.scene = scene;
		this.previousPoint = this.getPoint();
	}

	public void setAngle(double angleDeg) {
		fxPane.setRotate(angleDeg);
	}

	public void turnBy(double angleDeg) {
		fxPane.setRotate(fxPane.getRotate() + angleDeg);
	}

	public void copyImage(Image image) {
		fxPane.getChildren().clear();
		fxPane.getChildren().add(new ImageView(image));
	}

	/** Calculates and sets {@link #dx} and {@link #dy} from {@link #speed} and {@link #moveAngle} */
	private void vectorFromSpeedAngle() {
		double theta = moveAngle / 180.0 * Math.PI;
		double normalizedDX = Math.cos(theta);
		double normalizedDY = Math.sin(theta);
		dx = normalizedDX * speed;
		dy = normalizedDY * speed;
	}

	/**
	 * Calculates and sets {@link #moveAngle} from {@link #dx} and {@link #dy}
	 * Might actually not be used???
	 */
	private void speedAngleFromVector() {
		speed = Math.hypot(dx, dy);

		double dy = this.dy * -1;
		double dx = this.dx;

		double radians = Math.atan2(dy, dx);
		fxPane.setRotate(radians / Math.PI * 180);
	}

	public void forward(double distance) {
		double theta = imageAngle / 180.0 * Math.PI;
		double dx = Math.cos(theta) * distance;
		double dy = Math.sin(theta) * distance * -1;

		fxPane.setLayoutX(fxPane.getLayoutX() + dx);
		fxPane.setLayoutY(fxPane.getLayoutY() + dy);
	}

	public void addForce(double amt, double angle) {
		double radians = angle * Math.PI / 180;
		double ddx = amt * Math.cos(radians);
		double ddy = amt * Math.sin(radians) * -1;

		dx += ddx;
		dy += ddy;
	}

	@Override
	public void setOnClick(EventHandler<? super MouseEvent> eventHandler) {
		fxPane.setOnMouseClicked(eventHandler);
	}

	public void update() {
		previousPoint = getPoint();
		setX(getX() + dx);
		setY(getY() + dy);
		checkBounds();
		// center myself
		// checkClicked();
		this.process();
	}

	public void setBoundaryAction(BoundaryAction action) {
		boundaryAction = action;
	}

	public void checkBounds() {
		if (!fxPane.isVisible())
			return;

		double screenW = scene.getWidth();
		double screenH = scene.getHeight();

		boolean offRight = getX() > screenW;
		boolean offLeft = getX() < 0;
		boolean offBottom = getY() > screenH;
		boolean offTop = getY() < 0;
		boolean offScreen = offRight
				|| offLeft
				|| offBottom
				|| offTop;

		if (!offScreen)
			return;

		switch (boundaryAction) {
			case BoundaryAction.WRAP -> {
				if (offRight)
					setX(0);
				if (offLeft)
					setX(screenW);
				if (offBottom)
					setY(0);
				if (offTop)
					setY(screenH);
			}
			case BoundaryAction.BOUNCE -> {
				if (offRight || offLeft)
					dx *= -1;
				if (offBottom || offTop)
					dy *= -1;
			}
			case BoundaryAction.STOP -> speed = 0;
			case BoundaryAction.HIDE -> hide();
			case BoundaryAction.CONTINUE -> {}
		}
	}

	public void setSize(double newW, double newH) {
		fxPane.setPrefSize(newW, newH);
		fxPane.setMaxSize(fxPane.getPrefWidth(), fxPane.getPrefHeight());
		fxPane.setMinSize(fxPane.getPrefWidth(), fxPane.getPrefHeight());

		fxPane.getChildren().forEach(node -> {
			if (node instanceof ImageView imageView) {
				imageView.setFitWidth(newW);
				imageView.setFitHeight(newH);
			}
		});
	}

	public void setImage(String imageFilename) {
		ImageView image = new ImageView(imageFilename);
		image.setPreserveRatio(true);
		image.setSmooth(true);
		image.setFitWidth(fxPane.getPrefWidth());
		image.setFitHeight(fxPane.getPrefHeight());

		fxPane.getChildren().clear();
		fxPane.getChildren().add(image);

		fxPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
	}

	public boolean collidesWith(Sprite target) {
		return fxPane.intersects(target.fxPane.getLayoutBounds());
	}

	public abstract void process();

	public void hide() {
		fxPane.setVisible(false);
	}

	public void show() {
		fxPane.setVisible(true);
	}

	public boolean isKeyPressed(KeyCode keyCode) {
		return scene.isKeyPressed(keyCode);
	}

	public double distanceTo(Point2D point) {
		return getPoint().distance(point);
	}

	public double distanceTo(double x, double y) {
		return distanceTo(new Point2D(x, y));
	}

	public double dirTo(Point2D point) {
		return getPoint().angle(point);
	}

	public void drawTrace(Color color) {
		Line trace = new Line(
				getX(), getY(),
				previousPoint.getX(), previousPoint.getY()
		);
		trace.setFill(color);
		scene.addNode(trace);
	}

	public void drawTrace() {
		drawTrace(Color.BLACK);
	}

	public enum BoundaryAction {
		WRAP, BOUNCE, STOP, HIDE, CONTINUE
	}
}
package dk.ninjabear.ants;

import javafx.scene.shape.Rectangle;

public class Obstacle extends Rectangle {
	public Obstacle(double x, double y, double w, double h) {
		super(x, y, w, h);
		setFill(Settings.OBSTACLE_COLOR);
	}
}

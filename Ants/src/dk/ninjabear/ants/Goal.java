package dk.ninjabear.ants;

import javafx.scene.shape.Circle;

public class Goal extends Circle {
	public Goal() {
		super(Settings.GOAL_X, Settings.GOAL_Y, Settings.GOAL_RADIUS);
		setFill(Settings.GOAL_COLOR);
	}
}

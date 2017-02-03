package dk.ninjabear.ants;

import javafx.scene.shape.Circle;

public class Goal extends Circle {
	public Goal() {
		super(Settings.getGoalX(), Settings.getGoalY(), Settings.getGoalRadius());
		setFill(Settings.getGoalColor());
	}
}

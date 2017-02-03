package dk.ninjabear.ants;

import javafx.scene.paint.Color;

public class Settings {
	public static final double SCREEN_WIDTH = 1000;
	public static final double SCREEN_HEIGHT = 600;
	public static final Color  SCREEN_BACKGROUND = Color.BLACK;

	public static final double GOAL_X = SCREEN_WIDTH * 0.9;
	public static final double GOAL_Y = SCREEN_HEIGHT / 2;
	public static final double GOAL_RADIUS = 30;
	public static final Color  GOAL_COLOR = Color.LIGHTGREEN;
	
	public static final Color  OBSTACLE_COLOR = Color.LIGHTGREY;
	
	public static final int    ANT_LIFETIME = 200;
	public static final int    ANT_POPULATION = 1000;
	public static final double ANT_START_X = SCREEN_WIDTH * 0.1;
	public static final double ANT_START_Y = SCREEN_HEIGHT / 2;
	public static final Color  ANT_COLOR = Color.RED;
	public static final double ANT_SIZE = 10;
	public static final double ANT_SPEED = 10;
	public static final double ANT_MUTATION_RATE = 0.02;
	public static final double ANT_HIT_PENALTY = 1.5;
}

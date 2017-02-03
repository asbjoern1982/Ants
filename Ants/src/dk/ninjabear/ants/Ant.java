package dk.ninjabear.ants;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Ant extends Line {
	
	private double x, y, d;
	private double[] genes;
	private int age;
	
	private boolean hasHitAnObstacle = false;
	private boolean hasReachedTheGoal = false;
	
	public Ant() {
		super(Settings.ANT_START_X, Settings.ANT_START_Y, Settings.ANT_START_X+Settings.ANT_SIZE, Settings.ANT_START_Y);
		setStroke(Settings.ANT_COLOR);
		setStrokeWidth(3);
		
		genes = new double[Settings.ANT_LIFETIME];
		for (int i = 0; i < Settings.ANT_LIFETIME; i++)
			genes[i] = Math.random() * 360;
	}
	
	private Ant(double[] genes) {
		super(Settings.ANT_START_X, Settings.ANT_START_Y, Settings.ANT_START_X+Settings.ANT_SIZE, Settings.ANT_START_Y);
		setStroke(Color.RED);
		setStrokeWidth(3);
		this.genes = genes;
	}
	
	public void move() {
		if (hasHitAnObstacle || hasReachedTheGoal) return;
		
		if (genes.length <= age) return; // hack >_> it runs over the age for some reason
		d = genes[age];
		age++;
	
		x += Math.cos(Math.toRadians(d)) * Settings.ANT_SPEED;
		y += Math.sin(Math.toRadians(d)) * Settings.ANT_SPEED;
		this.setTranslateX(x);
		this.setTranslateY(y);
		this.setRotate(d);
	}
	
	public void reachedGoal() {
		double x1 = Settings.ANT_START_X + x;
		double y1 = Settings.ANT_START_Y + y;
		double x2 = Settings.GOAL_X;
		double y2 = Settings.GOAL_Y;
		double distance = Math.sqrt((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1));
		if (distance < Settings.GOAL_RADIUS)
			hasReachedTheGoal = true;
	}
	
	public void hitAnObstacle(Obstacle obstacle) {
		double x1 = Settings.ANT_START_X + x;
		double y1 = Settings.ANT_START_Y + y;
		if (	x1 > obstacle.getX() && 
				x1 < obstacle.getX() + obstacle.getWidth() &&
				y1 > obstacle.getY() && 
				y1 < obstacle.getY() + obstacle.getHeight())
			hasHitAnObstacle = true;
	}
	
	public double calcFitness() {
		if (hasReachedTheGoal)
			return 1;
		
		double x1 = Settings.ANT_START_X + x;
		double y1 = Settings.ANT_START_Y + y;
		double x2 = Settings.GOAL_X;
		double y2 = Settings.GOAL_Y;

		// calculate distance
		double distance = Math.sqrt((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1));
		
		// if it has hit something, lower the fitness
		if (hasHitAnObstacle)
			distance *= Settings.ANT_HIT_PENALTY;

		// normalise
		return 1/distance;
	}
	
	public Ant reproduce(Ant other) {
		double[] childGenes = new double[genes.length];
		for (int i = 0; i < genes.length/2; i++) {
			childGenes[i] = genes[i];
		}
		for (int i = genes.length/2+1; i < genes.length; i++) {
			childGenes[i] = other.genes[i];
		}

		// mutate
		for (int i = 0; i < childGenes.length; i++) {
			if (Math.random() <= Settings.ANT_MUTATION_RATE)
				childGenes[i] = Math.random() * 360;
		}

		return new Ant(childGenes);
	}
}

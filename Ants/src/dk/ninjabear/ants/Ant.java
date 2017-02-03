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
		super(Settings.getAntStartX(), Settings.getAntStartY(), Settings.getAntStartX()+Settings.getAntSize(), Settings.getAntStartY());
		setStroke(Settings.getAntColor());
		setStrokeWidth(3);
		
		genes = new double[Settings.getAntLifetime()];
		for (int i = 0; i < Settings.getAntLifetime(); i++)
			genes[i] = Math.random() * 360;
	}
	
	private Ant(double[] genes) {
		super(Settings.getAntStartX(), Settings.getAntStartY(), Settings.getAntStartX()+Settings.getAntSize(), Settings.getAntStartY());
		setStroke(Color.RED);
		setStrokeWidth(3);
		this.genes = genes;
	}
	
	public void move() {
		if (hasHitAnObstacle || hasReachedTheGoal) return;
		
		if (genes.length <= age) return; // hack >_> it runs over the age for some reason
		d = genes[age];
		age++;
	
		x += Math.cos(Math.toRadians(d)) * Settings.getAntSpeed();
		y += Math.sin(Math.toRadians(d)) * Settings.getAntSpeed();
		this.setTranslateX(x);
		this.setTranslateY(y);
		this.setRotate(d);
	}
	
	public void reachedGoal() {
		double x1 = Settings.getAntStartX() + x;
		double y1 = Settings.getAntStartY() + y;
		double x2 = Settings.getGoalX();
		double y2 = Settings.getGoalY();
		double distance = Math.sqrt((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1));
		if (distance < Settings.getGoalRadius())
			hasReachedTheGoal = true;
	}
	
	public void hitAnObstacle(Obstacle obstacle) {
		double x1 = Settings.getAntStartX() + x;
		double y1 = Settings.getAntStartY() + y;
		if (	x1 > obstacle.getX() && 
				x1 < obstacle.getX() + obstacle.getWidth() &&
				y1 > obstacle.getY() && 
				y1 < obstacle.getY() + obstacle.getHeight())
			hasHitAnObstacle = true;
	}
	
	public double calcFitness() {
		if (hasReachedTheGoal)
			return 1;
		
		double x1 = Settings.getAntStartX() + x;
		double y1 = Settings.getAntStartY() + y;
		double x2 = Settings.getGoalX();
		double y2 = Settings.getGoalY();

		// calculate distance
		double distance = Math.sqrt((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1));
		
		// if it has hit something, lower the fitness
		if (hasHitAnObstacle)
			distance *= Settings.getAntHitPenalty();

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
			if (Math.random() <= Settings.getAntMutationRrate())
				childGenes[i] = Math.random() * 360;
		}

		return new Ant(childGenes);
	}
}

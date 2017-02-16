package dk.ninjabear.ants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javafx.scene.paint.Color;

public class Settings {
	private static final String SCREEN_WIDTH = "SCREEN_WIDTH";
	private static final String SCREEN_HEIGHT = "SCREEN_HEIGHT";
	private static final String SCREEN_BACKGROUND ="SCREEN_BACKGROUND";
	private static final String GOAL_X = "GOAL_X";
	private static final String GOAL_Y = "GOAL_Y";
	private static final String GOAL_RADIUS = "GOAL_RADIUS";
	private static final String GOAL_COLOR = "GOAL_COLOR";
	private static final String OBSTACLE_COLOR = "OBSTACLE_COLOR";
	private static final String ANT_LIFETIME = "ANT_LIFETIME";
	private static final String ANT_POPULATION = "ANT_POPULATION";
	private static final String ANT_START_X = "ANT_START_X";
	private static final String ANT_START_Y = "ANT_START_Y";
	private static final String ANT_COLOR = "ANT_COLOR";
	private static final String ANT_SIZE = "ANT_SIZE";
	private static final String ANT_SPEED = "ANT_SPEED";
	private static final String ANT_MUTATION_RATE = "ANT_MUTATION_RATE";
	private static final String ANT_HIT_PENALTY = "ANT_HIT_PENALTY";
	
	private static boolean notLoaded = true;

	private static double screenWidth;
	private static double screenHeight;
	private static Color  screenBackground;
	
	private static double goalX;
	private static double goalY;
	private static double goalRadius;
	private static Color  goalColor;

	private static Color  obstacleColor;
	
	private static int    antLifetime;
	private static int    antPopulation;
	private static double antStartX;
	private static double antStartY;
	private static Color  antColor;
	private static double antSize;
	private static double antSpeed;
	private static double antMutationRate;
	private static double antHitPenalty;
	
	public static void setup() {
		notLoaded = false;
		
		Properties p = new Properties();
		try (FileInputStream inStream = new FileInputStream(new File("settings.xml"))) {
			p.loadFromXML(inStream);
		} catch (IOException ioe) {
			System.err.println("File \"settings.xml\" not found, loading default settings");
			loadDefault();
			storeSettings();
			return;
		}
		
		try {
			screenWidth = 	Double.parseDouble((String) p.get(SCREEN_WIDTH));
			screenHeight = 	Double.parseDouble((String) p.get(SCREEN_HEIGHT));
			screenBackground = Color.valueOf((String) p.get(SCREEN_BACKGROUND));
			
			goalX = 		Double.parseDouble((String) p.get(GOAL_X));
			goalY = 		Double.parseDouble((String) p.get(GOAL_Y));
			goalRadius = 	Double.parseDouble((String) p.get(GOAL_RADIUS));
			goalColor = 	Color.valueOf((String) p.get(GOAL_COLOR));

			obstacleColor = Color.valueOf((String) p.get(OBSTACLE_COLOR));
			
			antLifetime = 	Integer.parseInt((String) p.get(ANT_LIFETIME));
			antPopulation = Integer.parseInt((String) p.get(ANT_POPULATION));
			antStartX = 	Double.parseDouble((String) p.get(ANT_START_X));
			antStartY = 	Double.parseDouble((String) p.get(ANT_START_Y)); 
			antColor = 		Color.valueOf((String) p.get(ANT_COLOR));
			antSize = 		Double.parseDouble((String) p.get(ANT_SIZE));
			antSpeed = 		Double.parseDouble((String) p.get(ANT_SPEED));
			antMutationRate = Double.parseDouble((String) p.get(ANT_MUTATION_RATE));
			antHitPenalty = Double.parseDouble((String) p.get(ANT_HIT_PENALTY));
		} catch (NumberFormatException nfe) {
			System.err.println(nfe);
			loadDefault();
		} catch (IllegalArgumentException iae) {
			System.err.println(iae);
			loadDefault();
		}
	}
	
	private static void loadDefault() {
		screenWidth = 1000;
		screenHeight = 600;
		screenBackground = Color.BLACK;
		goalX = getScreenWidth() * 0.9;
		goalY = getScreenHeight() / 2;
		goalRadius = 30;
		goalColor = Color.LIGHTGREEN;

		obstacleColor = Color.LIGHTGREY;
		
		antLifetime = 200;
		antPopulation = 1000;
		antStartX = getScreenWidth() * 0.1;
		antStartY = getScreenHeight() / 2; 
		antColor = Color.RED;
		antSize = 10;
		antSpeed = 10;
		antMutationRate = 0.02;
		antHitPenalty = 1.5;
	}
	
	public static double getScreenWidth() 		{if (notLoaded) setup(); return screenWidth;}
	public static double getScreenHeight() 		{if (notLoaded) setup(); return screenHeight;}
	public static Color  getScreenBackground() 	{if (notLoaded) setup(); return screenBackground;}

	public static double getGoalX() 			{if (notLoaded) setup(); return goalX;}
	public static double getGoalY() 			{if (notLoaded) setup(); return goalY;}
	public static double getGoalRadius() 		{if (notLoaded) setup(); return goalRadius;}
	public static Color  getGoalColor() 		{if (notLoaded) setup(); return goalColor;}
	
	public static Color  getObstacleColor() 	{if (notLoaded) setup(); return obstacleColor;}
	
	public static int    getAntLifetime() 		{if (notLoaded) setup(); return antLifetime;}
	public static int    getAntPopulation() 	{if (notLoaded) setup(); return antPopulation;}
	public static double getAntStartX() 		{if (notLoaded) setup(); return antStartX;}
	public static double getAntStartY() 		{if (notLoaded) setup(); return antStartY;}
	public static Color  getAntColor() 			{if (notLoaded) setup(); return antColor;}
	public static double getAntSize() 			{if (notLoaded) setup(); return antSize;}
	public static double getAntSpeed() 			{if (notLoaded) setup(); return antSpeed;}
	public static double getAntMutationRrate() 	{if (notLoaded) setup(); return antMutationRate;}
	public static double getAntHitPenalty() 	{if (notLoaded) setup(); return antHitPenalty;}
	
	
	private static void storeSettings() {
		Properties p = new Properties();
		
		p.put(SCREEN_WIDTH, 		"" + screenWidth);
		p.put(SCREEN_HEIGHT, 		"" + screenHeight);
		p.put(SCREEN_BACKGROUND, 	"" + screenBackground);
		p.put(GOAL_X, 				"" + goalX);
		p.put(GOAL_Y, 				"" + goalY);
		p.put(GOAL_RADIUS, 			"" + goalRadius);
		p.put(GOAL_COLOR, 			"" + goalColor);
		p.put(OBSTACLE_COLOR,		"" + obstacleColor);
		p.put(ANT_LIFETIME, 		"" + antLifetime);
		p.put(ANT_POPULATION, 		"" + antPopulation);
		p.put(ANT_START_X, 			"" + antStartX);
		p.put(ANT_START_Y, 			"" + antStartY);
		p.put(ANT_COLOR,			"" + antColor);
		p.put(ANT_SIZE, 			"" + antSize);
		p.put(ANT_SPEED, 			"" + antSpeed);
		p.put(ANT_MUTATION_RATE, 	"" + antMutationRate);
		p.put(ANT_HIT_PENALTY, 		"" + antHitPenalty);
		
		try (FileOutputStream outStream = new FileOutputStream(new File("settings.xml"))) {
			p.storeToXML(outStream, null);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	public static void set(double screenWidth,
			double screenHeight,
			Color  screenBackground,
			double goalX,
			double goalY,
			double goalRadius,
			Color  goalColor,
			Color  obstacleColor,
			int    antLifetime,
			int    antPopulation,
			double antStartX,
			double antStartY,
			Color  antColor,
			double antSize,
			double antSpeed,
			double antMutationRate,
			double antHitPenalty) {
		Settings.screenWidth = screenWidth;
		Settings.screenHeight = screenHeight;
		Settings.screenBackground = screenBackground;
		Settings.goalX = goalX;
		Settings.goalY = goalY;
		Settings.goalRadius = goalRadius;
		Settings.goalColor = goalColor;
		Settings.obstacleColor = obstacleColor;
		Settings.antLifetime = antLifetime;
		Settings.antPopulation = antPopulation;
		Settings.antStartX = antStartX;
		Settings.antStartY = antStartY;
		Settings.antColor = antColor;
		Settings.antSize = antSize;
		Settings.antSpeed = antSpeed;
		Settings.antMutationRate = antMutationRate;
		Settings.antHitPenalty = antHitPenalty;
		
		storeSettings();
	}
}

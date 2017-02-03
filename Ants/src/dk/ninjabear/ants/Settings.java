package dk.ninjabear.ants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javafx.scene.paint.Color;

public class Settings {
	public static final String[] KEYS = {
		"SCREEN_WIDTH",
		"SCREEN_HEIGHT",
		"SCREEN_BACKGROUND",
		"GOAL_X",
		"GOAL_Y",
		"GOAL_RADIUS",
		"GOAL_COLOR",
		"OBSTACLE_COLOR",
		"ANT_LIFETIME",
		"ANT_POPULATION",
		"ANT_START_X",
		"ANT_START_Y",
		"ANT_COLOR",
		"ANT_SIZE",
		"ANT_SPEED",
		"ANT_MUTATION_RATE",
		"ANT_HIT_PENALTY"
	};
	
	
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
			screenWidth = 	Double.parseDouble((String) p.get(KEYS[0]));
			screenHeight = 	Double.parseDouble((String) p.get(KEYS[1]));
			screenBackground = Color.valueOf((String) p.get(KEYS[2]));
			
			goalX = 		Double.parseDouble((String) p.get(KEYS[3]));
			goalY = 		Double.parseDouble((String) p.get(KEYS[4]));
			goalRadius = 	Double.parseDouble((String) p.get(KEYS[5]));
			goalColor = 	Color.valueOf((String) p.get(KEYS[6]));

			obstacleColor = Color.valueOf((String) p.get(KEYS[7]));
			
			antLifetime = 	Integer.parseInt((String) p.get(KEYS[8]));
			antPopulation = Integer.parseInt((String) p.get(KEYS[9]));
			antStartX = 	Double.parseDouble((String) p.get(KEYS[10]));
			antStartY = 	Double.parseDouble((String) p.get(KEYS[11])); 
			antColor = 		Color.valueOf((String) p.get(KEYS[12]));
			antSize = 		Double.parseDouble((String) p.get(KEYS[13]));
			antSpeed = 		Double.parseDouble((String) p.get(KEYS[14]));
			antMutationRate = Double.parseDouble((String) p.get(KEYS[15]));
			antHitPenalty = Double.parseDouble((String) p.get(KEYS[16]));
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
		
		p.put(KEYS[0], "" + screenWidth);
		p.put(KEYS[1], "" + screenHeight);
		p.put(KEYS[2], "" + screenBackground);
		p.put(KEYS[3], "" + goalX);
		p.put(KEYS[4], "" + goalY);
		p.put(KEYS[5], "" + goalRadius);
		p.put(KEYS[6], "" + goalColor);
		p.put(KEYS[7], "" + obstacleColor);
		p.put(KEYS[8], "" + antLifetime);
		p.put(KEYS[9], "" + antPopulation);
		p.put(KEYS[10], "" + antStartX);
		p.put(KEYS[11], "" + antStartY);
		p.put(KEYS[12], "" + antColor);
		p.put(KEYS[13], "" + antSize);
		p.put(KEYS[14], "" + antSpeed);
		p.put(KEYS[15], "" + antMutationRate);
		p.put(KEYS[16], "" + antHitPenalty);
		
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

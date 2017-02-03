package dk.ninjabear.ants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import javafx.scene.paint.Color;

public class Settings {
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
	private static double antMutationRrate;
	private static double antHitPenalty;
	
	public static void setup() {
		notLoaded = false;
		
//		screenWidth = 1000;
//		screenHeight = 600;
//		screenBackground = Color.BLACK;
//		goalX = getScreenWidth() * 0.9;
//		goalY = getScreenHeight() / 2;
//		goalRadius = 30;
//		goalColor = Color.LIGHTGREEN;
//
//		obstacleColor = Color.LIGHTGREY;
//		
//		antLifetime = 200;
//		antPopulation = 1000;
//		antStartX = getScreenWidth() * 0.1;
//		antStartY = getScreenHeight() / 2; 
//		antColor = Color.RED;
//		antSize = 10;
//		antSpeed = 10;
//		antMutationRrate = 0.02;
//		antHitPenalty = 1.5;
		
		boolean failed = false;
		
		Properties p = new Properties();
		try (FileInputStream inStream = new FileInputStream(new File("settings.xml"))) {
			p.loadFromXML(inStream);
		} catch (IOException e) {
			System.err.println(e);
			failed = true;
		}
		
		try {
			screenWidth = 	Double.parseDouble((String) p.get("SCREEN_WIDTH"));
			screenHeight = 	Double.parseDouble((String) p.get("SCREEN_HEIGHT"));
			screenBackground = Color.valueOf((String) p.get("SCREEN_BACKGROUND"));
			
			goalX = 		Double.parseDouble((String) p.get("GOAL_X"));
			goalY = 		Double.parseDouble((String) p.get("GOAL_Y"));
			goalRadius = 	Double.parseDouble((String) p.get("GOAL_RADIUS"));
			goalColor = 	Color.valueOf((String) p.get("GOAL_COLOR"));

			obstacleColor = Color.valueOf((String) p.get("OBSTACLE_COLOR"));
			
			antLifetime = 	Integer.parseInt((String) p.get("ANT_LIFETIME"));
			antPopulation = Integer.parseInt((String) p.get("ANT_POPULATION"));
			antStartX = 	Double.parseDouble((String) p.get("ANT_START_X"));
			antStartY = 	Double.parseDouble((String) p.get("ANT_START_Y")); 
			antColor = 		Color.valueOf((String) p.get("ANT_COLOR"));
			antSize = 		Double.parseDouble((String) p.get("ANT_SIZE"));
			antSpeed = 		Double.parseDouble((String) p.get("ANT_SPEED"));
			antMutationRrate = Double.parseDouble((String) p.get("ANT_MUTATION_RATE"));
			antHitPenalty = Double.parseDouble((String) p.get("ANT_HIT_PENALTY"));
		} catch (NumberFormatException nfe) {
			System.err.println(nfe);
			failed = true;
		} catch (IllegalArgumentException iae) {
			System.err.println(iae);
			failed = true;
		}
		
		if (failed) { // if anything went wrong, load default settings
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
			antMutationRrate = 0.02;
			antHitPenalty = 1.5;
		}
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
	public static double getAntMutationRrate() 	{if (notLoaded) setup(); return antMutationRrate;}
	public static double getAntHitPenalty() 	{if (notLoaded) setup(); return antHitPenalty;}
	
	
	public static void storeSettings() {
		Properties p = new Properties();
		
		p.put("SCREEN_WIDTH", "" + screenWidth);
		p.put("SCREEN_HEIGHT", "" + screenHeight);
		p.put("SCREEN_BACKGROUND", "" + screenBackground);
		
		p.put("GOAL_X", "" + goalX);
		p.put("GOAL_Y", "" + goalY);
		p.put("GOAL_RADIUS", "" + goalRadius);
		p.put("GOAL_COLOR", "" + goalColor);
		
		p.put("OBSTACLE_COLOR", "" + obstacleColor);
		
		p.put("ANT_LIFETIME", "" + antLifetime);
		p.put("ANT_POPULATION", "" + antPopulation);
		p.put("ANT_START_X", "" + antStartX);
		p.put("ANT_START_Y", "" + antStartY);
		p.put("ANT_COLOR", "" + antColor);
		p.put("ANT_SIZE", "" + antSize);
		p.put("ANT_SPEED", "" + antSpeed);
		p.put("ANT_MUTATION_RATE", "" + antMutationRrate);
		p.put("ANT_HIT_PENALTY", "" + antHitPenalty);
		
		try (FileOutputStream outStream = new FileOutputStream(new File("settings.xml"))) {
			p.storeToXML(outStream, null);
		} catch (IOException e) {
			System.err.println(e);
		}
		
	}
}

package dk.ninjabear.ants;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AntsApp extends Application {
	private final Controller controller = new Controller();
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	public void start(Stage primaryStage) {
		
		primaryStage.setTitle("Ants");
		BorderPane pane = new BorderPane();
		initContent(pane);
		primaryStage.setScene(new Scene(pane, Settings.getScreenWidth(), Settings.getScreenHeight()));
		primaryStage.show();
		
		controller.init();
	}
	
	public void initContent(BorderPane pane) {
		Pane root = new Pane();
		controller.root = root;
		root.setOnMousePressed(e -> controller.mousePressedAction(e));
		root.setOnMouseDragged(e -> controller.mouseMovedAction(e));
		root.setOnMouseReleased(e -> controller.mouseReleasedAction(e));
		
		// convert to color to hex so it can be used in css
		Color c = Settings.getScreenBackground();
		String hex = String.format( "%02X%02X%02X",
		            (int)( c.getRed() * 255 ),
		            (int)( c.getGreen() * 255 ),
		            (int)( c.getBlue() * 255 ) );
		root.setStyle("-fx-background-color: #" + hex + ";");
		pane.setCenter(root);
		
		MenuItem startMenuItem = new MenuItem("Start");
		startMenuItem.setOnAction(e -> controller.startAction());
		startMenuItem.setAccelerator(KeyCombination.keyCombination("F1"));
		
		MenuItem pauseMenuItem = new MenuItem("Pause");
		pauseMenuItem.setOnAction(e -> controller.pauseAction());
		pauseMenuItem.setAccelerator(KeyCombination.keyCombination("F2"));
		
		MenuItem resetMenuItem = new MenuItem("Reset Ants");
		resetMenuItem.setOnAction(e -> controller.reset());
		resetMenuItem.setAccelerator(KeyCombination.keyCombination("F3"));
		
		MenuItem clearMenuItem = new MenuItem("Clear");
		clearMenuItem.setOnAction(e -> controller.clearAction());
		clearMenuItem.setAccelerator(KeyCombination.keyCombination("F4"));
		
		Menu controlsMenu = new Menu("Controls");
		controlsMenu.getItems().addAll(startMenuItem, pauseMenuItem, resetMenuItem, clearMenuItem);
		

		MenuItem settingsViewMenuItem = new MenuItem("Settings View");
		settingsViewMenuItem.setOnAction(e -> controller.showSettingsViewAction());
		settingsViewMenuItem.setAccelerator(KeyCombination.keyCombination("F5"));
		
		Menu settingsMenu = new Menu("Settings");
		settingsMenu.getItems().add(settingsViewMenuItem);
		
		
		MenuBar bar = new MenuBar();
		bar.getMenus().addAll(controlsMenu, settingsMenu);
		pane.setTop(bar);
	}
	
	private class Controller {
		private Pane root;
		private boolean drawing = false;
		private double x,y;
		private Rectangle rect;
		private Goal goal;
		private ArrayList<Ant> ants = new ArrayList<>();
		private ArrayList<Obstacle> obstacles = new ArrayList<>();
		private AnimationTimer animationtimer;
		private boolean animationtimerrunning = false;
		
		private Text generationText = new Text("Generations: 0");
		private int generations;
		
		private SettingsDialog settingsDialog;
		
		public void mousePressedAction(MouseEvent e) {
			if (drawing) {
				drawing = false;
				root.getChildren().remove(rect);
			}
			
			drawing = true;
			x = e.getX();
			y = e.getY();
			rect = new Rectangle(x, y, 1, 1);
			rect.setStroke(Settings.getObstacleColor());
			rect.getStrokeDashArray().add(4.0);
			root.getChildren().add(rect);
		}
		
		public void mouseMovedAction(MouseEvent e) {
			if (!drawing)
				return;
			
			if (x < e.getX()) {
				rect.setX(x);
				rect.setWidth(e.getX() - x);
			} else {
				rect.setX(e.getX());
				rect.setWidth(x - e.getX());
			}
			
			if (y < e.getY()) {
				rect.setY(y);
				rect.setHeight(e.getY() - y);
			} else {
				rect.setY(e.getY());
				rect.setHeight(y - e.getY());
			}
		}
		
		public void mouseReleasedAction(MouseEvent e) {
			if (!drawing)
				return;
			drawing = false;
			root.getChildren().remove(rect);
			Obstacle obstacle = new Obstacle(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
			root.getChildren().add(obstacle);
			obstacles.add(obstacle);
			generationText.toFront();
		}
		
		public void reset() {
			animationtimer.stop();
			root.getChildren().removeAll(ants);
			ants.clear();
			initAnts();
			animationtimerrunning = false;
		}
		
		private void initAnts() {
			for (int i = 0; i < Settings.getAntPopulation(); i++)
				ants.add(new Ant());
			root.getChildren().addAll(ants);
			generations = 0;
		}
		
		public void init() {
			root.getChildren().clear();
			
			
			goal = new Goal();
			root.getChildren().add(goal);
			
			Circle antStart = new Circle(Settings.getAntStartX(), Settings.getAntStartY(), Settings.getGoalRadius());
			antStart.setStroke(Settings.getAntColor());
			antStart.getStrokeDashArray().add(4.0);
			root.getChildren().add(antStart);
			
			generationText.setX(5);
			generationText.setY(20);
			generationText.setFont(Font.font("Verdana", 18));
			generationText.setFill(Color.GREEN);
			root.getChildren().add(generationText);
			
			initAnts();

			animationtimer = new AnimationTimer() {
				int count = 0;
				@Override
				public void handle(long now) {
					for (Ant ant : ants) {
						ant.move();
						ant.reachedGoal();
						for (Obstacle obstacle : obstacles)
							ant.hitAnObstacle(obstacle);
					}
					
					if (count++ > Settings.getAntLifetime()) {
						ArrayList<Ant> matingpool = new ArrayList<Ant>();
						double lowestFitness = Double.MAX_VALUE;
						double higestFitness = 0;
						for (Ant ant : ants) {
							double fitness = ant.calcFitness();
							if (lowestFitness > fitness)
								lowestFitness = fitness;
							if (higestFitness < fitness)
								higestFitness = fitness;
						}

						for (Ant ant : ants) {
							double a = 100 / (higestFitness - lowestFitness);
							int number = (int) (ant.calcFitness() * a);
							number *= number;
							for (int i = 0; i < number; i++)
								matingpool.add(ant);
						}

						root.getChildren().removeAll(ants);
						ants.clear();
						for (int i = 0; i < Settings.getAntPopulation(); i++) {
							Ant parent1 = matingpool.get((int)(matingpool.size()*Math.random()));
							Ant parent2 = matingpool.get((int)(matingpool.size()*Math.random()));
							Ant child = parent1.reproduce(parent2);
							ants.add(child);
						}
						root.getChildren().addAll(ants);
						
						count = 0;
						generationText.setText("Generations: " + ++generations);
					}
				}
			};
		}
		
		public void clearAction() {
			animationtimer.stop();
			root.getChildren().clear();
			ants.clear();
			obstacles.clear();
			init();
			animationtimerrunning = false;
		}
		
		public void startAction() {
			animationtimer.start();
			animationtimerrunning = true;
		}
		
		public void pauseAction() {
			if (animationtimerrunning)
				animationtimer.stop();
			else
				animationtimer.start();
			animationtimerrunning = !animationtimerrunning;
		}
		
		public void showSettingsViewAction() {
			if (settingsDialog == null) {
				settingsDialog = new SettingsDialog();
			}
			settingsDialog.showAndWait();
		}
	}
}

package dk.ninjabear.ants;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SettingsDialog extends Stage {
	private final Controller controller = new Controller();
	public SettingsDialog(AntsApp app) {
		controller.app = app;
		initModality(Modality.APPLICATION_MODAL);
		setResizable(false);
		setTitle("Settings");

		GridPane pane = new GridPane();
		initContent(pane);

		setScene(new Scene(pane));
	}

	private final TextField screenWidthTextField = new DoubleTextField();
	private final TextField screenHeightTextField = new DoubleTextField();
	private final ColorPicker screenBackgroundColorPicker = new ColorPicker();

	private final TextField goalXTextField = new DoubleTextField();
	private final TextField goalYTextField = new DoubleTextField();
	private final TextField goalRadiusTextField = new DoubleTextField();
	private final ColorPicker goalColorColorPicker = new ColorPicker();

	private final ColorPicker obstacleColorColorPicker = new ColorPicker();

	private final TextField antLifetimeTextField = new AbsolutIntegerTextField();
	private final TextField antPopulationTextField = new AbsolutIntegerTextField();
	private final TextField antStartXTextField = new DoubleTextField();
	private final TextField antStartYTextField = new DoubleTextField();
	private final ColorPicker antColorColorPicker = new ColorPicker();
	private final TextField antSizeTextField = new DoubleTextField();
	private final TextField antSpeedTextField = new DoubleTextField();
	private final TextField antMutationRateTextField = new DoubleTextField();
	private final TextField antHitPenaltyTextField = new DoubleTextField();

	private void initContent(GridPane pane) {
		pane.setHgap(5);
		pane.setVgap(5);
		pane.setPadding(new Insets(10));
		
		int r = 0;
		pane.add(new Label("Screen Width:"), 0,r);
		pane.add(screenWidthTextField, 1, r);
		r++;
		pane.add(new Label("Screen Height:"), 0, r);
		pane.add(screenHeightTextField, 1, r);
		r++;
		pane.add(new Label("Screen Background:"), 0, r);
		pane.add(screenBackgroundColorPicker, 1, r);
		r++;
		pane.add(new Label("Goal X:"), 0, r);
		pane.add(goalXTextField, 1, r);
		r++;
		pane.add(new Label("Goal Y:"), 0, r);
		pane.add(goalYTextField, 1, r);
		r++;
		pane.add(new Label("Goal Radius:"), 0, r);
		pane.add(goalRadiusTextField, 1, r);
		r++;
		pane.add(new Label("Goal Color:"), 0, r);
		pane.add(goalColorColorPicker, 1, r);
		r++;
		pane.add(new Label("Obstacle Color:"), 0, r);
		pane.add(obstacleColorColorPicker, 1, r);
		r++;
		pane.add(new Label("Ant Lifetime:"), 0, r);
		pane.add(antLifetimeTextField, 1, r);
		r++;
		pane.add(new Label("Ant Population:"), 0, r);
		pane.add(antPopulationTextField, 1, r);
		r++;
		pane.add(new Label("Ant Start X:"), 0, r);
		pane.add(antStartXTextField, 1, r);
		r++;
		pane.add(new Label("Ant Start Y:"), 0, r);
		pane.add(antStartYTextField, 1, r);
		r++;
		pane.add(new Label("Ant Color:"), 0, r);
		pane.add(antColorColorPicker, 1, r);
		r++;
		pane.add(new Label("Ant Size:"), 0, r);
		pane.add(antSizeTextField, 1, r);
		r++;
		pane.add(new Label("Ant Speed:"), 0, r);
		pane.add(antSpeedTextField, 1, r);
		r++;
		pane.add(new Label("Ant Mutation Rate:"), 0, r);
		pane.add(antMutationRateTextField, 1, r);
		r++;
		pane.add(new Label("Ant Hit Penalty:"), 0, r);
		pane.add(antHitPenaltyTextField, 1, r);
		
		
		// --------------------------------------------------------------
		r++;
		Button cancelButton = new Button("Cancel");
		cancelButton.setOnAction(e -> controller.cancelAction());
		
		Button saveButton = new Button("Save");
		saveButton.setDefaultButton(true);
		saveButton.setOnAction(e -> controller.saveAction());
		
		HBox box = new HBox(2, cancelButton, saveButton);
		box.setAlignment(Pos.CENTER_RIGHT);
		pane.add(box, 0, r, 2, 1);
		
		// --------------------------------------------------------------
		
		controller.updateControls();
	}
	
	public void resetControls() {
		controller.updateControls();
	}
	
	private class Controller {
		private AntsApp app;
		
		public void updateControls() {
			screenWidthTextField.setText("" + Settings.getScreenWidth());
			screenHeightTextField.setText("" + Settings.getScreenHeight());
			screenBackgroundColorPicker.setValue(Settings.getScreenBackground());
			
			goalXTextField.setText("" + Settings.getGoalX());
			goalYTextField.setText("" + Settings.getGoalY());
			goalRadiusTextField.setText("" + Settings.getGoalRadius());
			goalColorColorPicker.setValue(Settings.getGoalColor());

			obstacleColorColorPicker.setValue(Settings.getObstacleColor());

			antLifetimeTextField.setText("" + Settings.getAntLifetime());
			antPopulationTextField.setText("" + Settings.getAntPopulation());
			antStartXTextField.setText("" + Settings.getAntStartX());
			antStartYTextField.setText("" + Settings.getAntStartY());
			antColorColorPicker.setValue(Settings.getAntColor());
			antSizeTextField.setText("" + Settings.getAntSize());
			antSpeedTextField.setText("" + Settings.getAntSpeed());
			antMutationRateTextField.setText("" + Settings.getAntMutationRrate());
			antHitPenaltyTextField.setText("" + Settings.getAntHitPenalty());
		}
		
		public void cancelAction() {
			SettingsDialog.this.hide();
		}

		private boolean parseSucces;
		public void saveAction() {
			parseSucces = true;
			double screenWidth = getDouble(screenWidthTextField);
			double screenHeight = getDouble(screenHeightTextField);
			Color screenBackground = screenBackgroundColorPicker.getValue();
			
			double goalX = getDouble(goalXTextField);
			double goalY = getDouble(goalYTextField);
			double goalRadius = getDouble(goalRadiusTextField);
			Color goalColor = goalColorColorPicker.getValue();
			
			Color obstacleColor = obstacleColorColorPicker.getValue();
			
			int antLifetime = (int) getDouble(antLifetimeTextField);
			int antPopulation = (int) getDouble(antPopulationTextField);
			double antStartX = getDouble(antStartXTextField);
			double antStartY = getDouble(antStartYTextField);
			Color antColor = antColorColorPicker.getValue();
			double antSize = getDouble(antSizeTextField);
			double antSpeed = getDouble(antSpeedTextField);
			double antMutationRate = getDouble(antMutationRateTextField);
			double antHitPenalty = getDouble(antHitPenaltyTextField);
			
			if (parseSucces) {
				if (screenWidth != Settings.getScreenWidth() || 
						screenHeight != Settings.getScreenHeight() || 
						!screenBackground.equals(Settings.getScreenBackground())) {
					app.getRoot().getScene().getWindow().setWidth(screenWidth);
					app.getRoot().getScene().getWindow().setHeight(screenHeight);
					Color c = screenBackground;
					String hex = String.format( "%02X%02X%02X",
					            (int)( c.getRed() * 255 ),
					            (int)( c.getGreen() * 255 ),
					            (int)( c.getBlue() * 255 ) );
					app.getRoot().setStyle("-fx-background-color: #" + hex + ";");
				}
				
				if (goalX != Settings.getGoalX() ||
						goalY != Settings.getGoalY() ||
						goalRadius != Settings.getGoalRadius() ||
						!goalColor.equals(Settings.getGoalColor())) {
					app.getGoal().setCenterX(goalX);
					app.getGoal().setCenterY(goalY);
					app.getGoal().setFill(goalColor);		
				}
				
				if (!obstacleColor.equals(Settings.getObstacleColor()))
					for (Obstacle obstacle : app.getObstacles())
						obstacle.setFill(obstacleColor);

				if (antStartX != Settings.getAntStartX() ||
						antStartY != Settings.getAntStartY() ||
						!antColor.equals(Settings.getAntColor())) {
					for (Ant ant : app.getAnts())
						ant.setStroke(antColor);;
					Circle c = app.getAntStart();
					c.setCenterX(antStartX);
					c.setCenterY(antStartY);
					c.setStroke(antColor);
				}
				
				Settings.set(screenWidth, screenHeight, screenBackground, goalX, goalY, goalRadius, goalColor, obstacleColor, antLifetime, antPopulation, antStartX, antStartY, antColor, antSize, antSpeed, antMutationRate, antHitPenalty);
				SettingsDialog.this.hide();
			}
		}
		
		private double getDouble(TextField textField) {
			if (textField.getText().isEmpty()) {
				textField.setStyle("-fx-background-color: red;");
				parseSucces = false;
				return 0.0;
			} else textField.setStyle(null);
			return Double.parseDouble(textField.getText());
		}
	}
	
	private class DoubleTextField extends TextField {
		public DoubleTextField() {
			super();
			textProperty().addListener((obs, oldValue, newValue) -> {
				if (!newValue.matches("-?[0-9]*\\.?[0-9]*"))
					setText(oldValue);
	        });
		}
	}
	
	private class AbsolutIntegerTextField extends TextField {
		public AbsolutIntegerTextField() {
			super();
			textProperty().addListener((obs, oldValue, newValue) -> {
				if (!newValue.matches("[0-9]*"))
					setText(oldValue);
	        });
		}
	}
}

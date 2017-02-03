package dk.ninjabear.ants;

import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SettingsDialog extends Stage {
	private final Controller controller = new Controller();
	public SettingsDialog() {
		initModality(Modality.APPLICATION_MODAL);
		//ssetResizable(false);
		setTitle("Settings");

		GridPane pane = new GridPane();
		initContent(pane);

		setScene(new Scene(pane));
	}
	
	private final TextArea textArea = new TextArea();

	private void initContent(GridPane pane) {
		textArea.setEditable(false);
		textArea.setFocusTraversable(false);
		pane.add(textArea, 0, 0);
		
		Button closeButton = new Button("Close");
		closeButton.setDefaultButton(true);
		closeButton.setOnAction(e -> controller.closeAction());
		GridPane.setHalignment(closeButton, HPos.RIGHT);
		pane.add(closeButton, 0, 1);
		
		controller.updateControls();
	}
	
	private class Controller {
		public void updateControls() {
			StringBuilder sb = new StringBuilder();
			sb.append("SCREEN_WIDTH: " + Settings.getScreenWidth() + "\n");
			sb.append("SCREEN_HEIGHT: " + Settings.getScreenHeight() + "\n");
			sb.append("SCREEN_BACKGROUND: " + Settings.getScreenBackground() + "\n");
			sb.append("GOAL_X: " + Settings.getGoalX() + "\n");
			sb.append("GOAL_Y: " + Settings.getGoalY() + "\n");
			sb.append("GOAL_RADIUS: " + Settings.getGoalRadius() + "\n");
			sb.append("GOAL_COLOR: " + Settings.getGoalColor() + "\n");
			sb.append("OBSTACLE_COLOR: " + Settings.getObstacleColor() + "\n");
			sb.append("ANT_LIFETIME: " + Settings.getAntLifetime() + "\n");
			sb.append("ANT_POPULATION: " + Settings.getAntPopulation() + "\n");
			sb.append("ANT_START_X: " + Settings.getAntStartX() + "\n");
			sb.append("ANT_START_Y: " + Settings.getAntStartY() + "\n");
			sb.append("ANT_COLOR: " + Settings.getAntColor() + "\n");
			sb.append("ANT_SIZE: " + Settings.getAntSize() + "\n");
			sb.append("ANT_SPEED: " + Settings.getAntSpeed() + "\n");
			sb.append("ANT_MUTATION_RATE: " + Settings.getAntMutationRrate() + "\n");
			sb.append("ANT_HIT_PENALTY: " + Settings.getAntHitPenalty());
			
			
			
			
			textArea.setText(sb.toString());
		}
		
		public void closeAction() {
			SettingsDialog.this.hide();
		}
	}
}

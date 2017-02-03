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
			sb.append("SCREEN_WIDTH: " + Settings.SCREEN_WIDTH + "\n");
			sb.append("SCREEN_HEIGHT: " + Settings.SCREEN_HEIGHT + "\n");
			sb.append("SCREEN_BACKGROUND: " + Settings.SCREEN_BACKGROUND + "\n");
			sb.append("GOAL_X: " + Settings.GOAL_X + "\n");
			sb.append("GOAL_Y: " + Settings.GOAL_Y + "\n");
			sb.append("GOAL_RADIUS: " + Settings.GOAL_RADIUS + "\n");
			sb.append("GOAL_COLOR: " + Settings.GOAL_COLOR + "\n");
			sb.append("OBSTACLE_COLOR: " + Settings.OBSTACLE_COLOR + "\n");
			sb.append("ANT_LIFETIME: " + Settings.ANT_LIFETIME + "\n");
			sb.append("ANT_POPULATION: " + Settings.ANT_POPULATION + "\n");
			sb.append("ANT_START_X: " + Settings.ANT_START_X + "\n");
			sb.append("ANT_START_Y: " + Settings.ANT_START_Y + "\n");
			sb.append("ANT_COLOR: " + Settings.ANT_COLOR + "\n");
			sb.append("ANT_SIZE: " + Settings.ANT_SIZE + "\n");
			sb.append("ANT_SPEED: " + Settings.ANT_SPEED + "\n");
			sb.append("ANT_MUTATION_RATE: " + Settings.ANT_MUTATION_RATE + "\n");
			sb.append("ANT_HIT_PENALTY: " + Settings.ANT_HIT_PENALTY);
			
			
			
			
			textArea.setText(sb.toString());
		}
		
		public void closeAction() {
			SettingsDialog.this.hide();
		}
	}
}

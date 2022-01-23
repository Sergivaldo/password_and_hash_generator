package application.controller.util;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class Screen {
	
	public static void load_screen(String path, VBox box) throws IOException {
		set_screen(path, box);
	}
	
	private static void set_screen(String path, VBox box) throws IOException {
		Parent screen = FXMLLoader.load(Screen.class.getResource(path));
		box.getChildren().clear();
		box.getChildren().add(screen);
	}
	
	
}

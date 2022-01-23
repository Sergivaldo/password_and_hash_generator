package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.controller.util.Screen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GeneratorController implements Initializable {
	@FXML
	private Button close_btt;

	@FXML
	private VBox screen_box;
	
	@FXML
    private Button pass_screen_btt;

    @FXML
    private Button hash_screen_btt;
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			pass_gen_screen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void pass_gen_screen() throws IOException {
		String path = "/application/view/fxml/password_view.fxml";
		Screen.load_screen(path, screen_box);
		set_color_btt(pass_screen_btt, hash_screen_btt);
	}
	
	public void hash_gen_screen() throws IOException {
		String path = "/application/view/fxml/hash_view.fxml";
		Screen.load_screen(path, screen_box);
		set_color_btt(hash_screen_btt, pass_screen_btt);
	}
	
	@FXML
	public void close(ActionEvent event) {
		Button button = (Button) event.getSource();
		Stage stage = (Stage) button.getScene().getWindow();
		stage.close();
	}
	
	private void set_color_btt(Button btt_pressed, Button unpressed_btt){
		btt_pressed.setStyle("-fx-background-color:#454D80; -fx-background-radius:0;");
		unpressed_btt.setStyle("-fx-background-color:#0C1133; -fx-background-radius:0;");
	}
	

}
